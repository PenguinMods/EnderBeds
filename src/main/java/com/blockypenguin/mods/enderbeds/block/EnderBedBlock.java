package com.blockypenguin.mods.enderbeds.block;

import com.blockypenguin.mods.enderbeds.EnderBeds;
import com.blockypenguin.mods.enderbeds.gamerule.EnderBedModGameRules;
import com.blockypenguin.mods.enderbeds.gamerule.EnderBedWakeBehaviour;
import com.blockypenguin.mods.enderbeds.item.EnderBedModItems;
import com.blockypenguin.mods.enderbeds.sound.EnderBedModSounds;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.block.BedBlock;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.mob.EndermiteEntity;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.DyeColor;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.GameRules;
import net.minecraft.world.TeleportTarget;
import net.minecraft.world.World;
import net.minecraft.world.event.GameEvent;

import java.util.function.BiConsumer;

public class EnderBedBlock extends BedBlock {
    public static final Identifier ID = EnderBeds.id("ender_bed");

    public EnderBedBlock() {
        super(
            DyeColor.BLACK,
            Settings.copy(Blocks.BLACK_BED)
                .registryKey(RegistryKey.of(RegistryKeys.BLOCK, ID))
        );
    }

    @Override
    public void randomDisplayTick(BlockState state, World world, BlockPos pos, Random random) {
        boolean isOccupied = state.get(OCCUPIED);
        displayAmbientParticles(pos, world, random, isOccupied);
    }

    @Environment(EnvType.CLIENT)
    private static void displayAmbientParticles(BlockPos blockPos, World world, Random random, boolean isOccupied) {
        Vec3d pos = blockPos.toBottomCenterPos();

        for (int i = 0; i < (isOccupied ? 5 : 3); i++) {
            int xModifier = random.nextBoolean() ? -1 : 1;
            int zModifier = random.nextBoolean() ? -1 : 1;

            double x = pos.getX() + (0.25 * xModifier);
            double y = pos.getY() + random.nextFloat();
            double z = pos.getZ() + (0.25 * zModifier);

            double xVelocity = isOccupied
                ? (random.nextFloat() - 0.5) * 0.125
                : random.nextFloat() * xModifier;
            double yVelocity = (random.nextFloat() - 0.5) * 0.125;
            double zVelocity = isOccupied
                ? (random.nextFloat() - 0.5) * 0.125
                : random.nextFloat() * zModifier;

            world.addParticleClient(
                isOccupied
                    ? ParticleTypes.REVERSE_PORTAL
                    : ParticleTypes.PORTAL,
                x, y, z,
                xVelocity, yVelocity, zVelocity
            );
        }
    }

    private static void displayTeleportParticles(BlockPos blockPos, ServerWorld world, Random random) {
        BlockState state = world.getBlockState(blockPos);
        Direction oppositeDirection = BedBlock.getOppositePartDirection(state);
        boolean isX = oppositeDirection.getAxis() == Direction.Axis.X;

        for (int i = 0; i < 64; i++) {
            double randomInBedWidth = 0.5 - random.nextDouble();
            double randomInBedLength = 1 - random.nextBetween(0, 200) / 100d;

            Vec3d pos = blockPos
                .toCenterPos()
                .offset(oppositeDirection, 0.5)
                .add(
                    isX ? randomInBedLength : randomInBedWidth,
                    0.1,
                    isX ? randomInBedWidth : randomInBedLength
                );

            double range = random.nextFloat() * 0.04;

            world.spawnParticles(
                ParticleTypes.REVERSE_PORTAL,
                pos.getX(), pos.getY(), pos.getZ(),
                3,
                range, range * 10, range,
                0.01
            );
        }
    }

    public static void onWake(ServerPlayerEntity player, BlockPos bedPos, ServerWorld bedWorld) {
        ServerWorld playerWorld = player.getWorld();

        displayTeleportParticles(bedPos, bedWorld, Random.create());
        player.teleportTo(player.getRespawnTarget(true, TeleportTarget.NO_OP));

        BiConsumer<BlockPos, ServerWorld> emitSound = (blockPos, world) ->
            world.playSound(null, blockPos, EnderBedModSounds.ENDER_BED_TELEPORT_SOUND, player.getSoundCategory(), 1.0F, 1.0F);

        bedWorld.emitGameEvent(GameEvent.TELEPORT, bedPos, GameEvent.Emitter.of(player)); // Emit TP event at previous location
        emitSound.accept(bedPos, bedWorld);                                               // Play sound at previous location
        emitSound.accept(BlockPos.ofFloored(player.getPos()), playerWorld);               // Play sound at current location

        EnderBedWakeBehaviour behaviour = bedWorld.getGameRules().get(EnderBedModGameRules.ENDER_BED_WAKE_BEHAVIOUR).get();

        switch (behaviour) {
            case RETURN_ITEM_TO_PLAYER:
                player.giveOrDropStack(EnderBedModItems.ENDER_BED.getDefaultStack());
            case DESTROY_BED:
                bedWorld.breakBlock(bedPos, false);
            case DO_NOTHING: {}
        }

        double endermiteSpawnChance = playerWorld.getGameRules().getInt(EnderBedModGameRules.ENDER_BED_SPAWN_ENDERMITE_CHANCE) / 100d;
        boolean canSpawnMobs = playerWorld.getGameRules().getBoolean(GameRules.DO_MOB_SPAWNING);

        if (player.getRandom().nextFloat() < endermiteSpawnChance && canSpawnMobs) {
            EndermiteEntity endermiteEntity = EntityType.ENDERMITE.create(playerWorld, SpawnReason.TRIGGERED);
            if (endermiteEntity != null) {
                endermiteEntity.refreshPositionAndAngles(player.getX(), player.getY(), player.getZ(), player.getYaw(), player.getPitch());
                playerWorld.spawnEntity(endermiteEntity);
            }
        }
    }
}