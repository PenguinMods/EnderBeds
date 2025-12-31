package com.blockypenguin.mods.enderbeds.block;

import com.blockypenguin.mods.enderbeds.EnderBeds;
import com.blockypenguin.mods.enderbeds.gamerule.EnderBedModGameRules;
import com.blockypenguin.mods.enderbeds.gamerule.EnderBedWakeBehaviour;
import com.blockypenguin.mods.enderbeds.item.EnderBedModItems;
import com.blockypenguin.mods.enderbeds.sound.EnderBedModSounds;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.EntitySpawnReason;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.monster.Endermite;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BedBlock;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.level.gamerules.GameRules;
import net.minecraft.world.level.portal.TeleportTransition;
import net.minecraft.world.phys.Vec3;

import java.util.function.BiConsumer;

public class EnderBedBlock extends BedBlock {
    public static final Identifier ID = EnderBeds.id("ender_bed");

    public EnderBedBlock() {
        super(
            DyeColor.BLACK,
            Properties.ofFullCopy(Blocks.BLACK_BED)
                .setId(ResourceKey.create(Registries.BLOCK, ID))
        );
    }

    @Override
    public void animateTick(BlockState state, Level world, BlockPos pos, RandomSource random) {
        boolean isOccupied = state.getValue(OCCUPIED);
        displayAmbientParticles(pos, world, random, isOccupied);
    }

    @Environment(EnvType.CLIENT)
    private static void displayAmbientParticles(BlockPos blockPos, Level world, RandomSource random, boolean isOccupied) {
        Vec3 pos = blockPos.getBottomCenter();

        for (int i = 0; i < (isOccupied ? 5 : 3); i++) {
            int xModifier = random.nextBoolean() ? -1 : 1;
            int zModifier = random.nextBoolean() ? -1 : 1;

            double x = pos.x() + (0.25 * xModifier);
            double y = pos.y() + random.nextFloat();
            double z = pos.z() + (0.25 * zModifier);

            double xVelocity = isOccupied
                ? (random.nextFloat() - 0.5) * 0.125
                : random.nextFloat() * xModifier;
            double yVelocity = (random.nextFloat() - 0.5) * 0.125;
            double zVelocity = isOccupied
                ? (random.nextFloat() - 0.5) * 0.125
                : random.nextFloat() * zModifier;

            world.addParticle(
                isOccupied
                    ? ParticleTypes.REVERSE_PORTAL
                    : ParticleTypes.PORTAL,
                x, y, z,
                xVelocity, yVelocity, zVelocity
            );
        }
    }

    private static void displayTeleportParticles(BlockPos blockPos, ServerLevel world, RandomSource random) {
        BlockState state = world.getBlockState(blockPos);
        Direction oppositeDirection = BedBlock.getConnectedDirection(state);
        boolean isX = oppositeDirection.getAxis() == Direction.Axis.X;

        for (int i = 0; i < 64; i++) {
            double randomInBedWidth = 0.5 - random.nextDouble();
            double randomInBedLength = 1 - random.nextIntBetweenInclusive(0, 200) / 100d;

            Vec3 pos = blockPos
                .getCenter()
                .relative(oppositeDirection, 0.5)
                .add(
                    isX ? randomInBedLength : randomInBedWidth,
                    0.1,
                    isX ? randomInBedWidth : randomInBedLength
                );

            double range = random.nextFloat() * 0.04;

            world.sendParticles(
                ParticleTypes.REVERSE_PORTAL,
                pos.x(), pos.y(), pos.z(),
                3,
                range, range * 10, range,
                0.01
            );
        }
    }

    public static void onWake(ServerPlayer player, BlockPos bedPos, ServerLevel bedWorld) {
        ServerLevel playerWorld = player.level();

        displayTeleportParticles(bedPos, bedWorld, RandomSource.create());
        player.teleport(player.findRespawnPositionAndUseSpawnBlock(true, TeleportTransition.DO_NOTHING));

        BiConsumer<BlockPos, ServerLevel> emitSound = (blockPos, world) ->
            world.playSound(null, blockPos, EnderBedModSounds.ENDER_BED_TELEPORT_SOUND, player.getSoundSource(), 1.0F, 1.0F);

        bedWorld.gameEvent(GameEvent.TELEPORT, bedPos, GameEvent.Context.of(player)); // Emit TP event at previous location
        emitSound.accept(bedPos, bedWorld);                                               // Play sound at previous location
        emitSound.accept(BlockPos.containing(player.position()), playerWorld);               // Play sound at current location

        EnderBedWakeBehaviour behaviour = bedWorld.getGameRules().get(EnderBedModGameRules.WAKE_BEHAVIOUR);

        switch (behaviour) {
            case RETURN_ITEM_TO_PLAYER:
                player.handleExtraItemsCreatedOnUse(EnderBedModItems.ENDER_BED.getDefaultInstance());
            case DESTROY_BED:
                bedWorld.destroyBlock(bedPos, false);
            case DO_NOTHING: {}
        }

        double endermiteSpawnChance = playerWorld.getGameRules().get(EnderBedModGameRules.ENDERMITE_SPAWN_CHANCE) / 100d;
        boolean canSpawnMobs = playerWorld.getGameRules().get(GameRules.SPAWN_MOBS);

        if (player.getRandom().nextFloat() < endermiteSpawnChance && canSpawnMobs) {
            Endermite endermiteEntity = EntityType.ENDERMITE.create(playerWorld, EntitySpawnReason.TRIGGERED);
            if (endermiteEntity != null) {
                endermiteEntity.snapTo(player.getX(), player.getY(), player.getZ(), player.getYRot(), player.getXRot());
                playerWorld.addFreshEntity(endermiteEntity);
            }
        }
    }
}