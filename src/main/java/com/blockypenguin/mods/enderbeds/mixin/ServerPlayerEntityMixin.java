package com.blockypenguin.mods.enderbeds.mixin;

import com.blockypenguin.mods.enderbeds.block.EnderBedBlock;
import com.blockypenguin.mods.enderbeds.mixinhelpers.EnderBedData;
import com.blockypenguin.mods.enderbeds.mixinhelpers.IServerPlayerEntityMixin;
import com.llamalad7.mixinextras.injector.v2.WrapWithCondition;
import com.llamalad7.mixinextras.sugar.Local;
import com.mojang.authlib.GameProfile;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(ServerPlayerEntity.class)
public abstract class ServerPlayerEntityMixin extends PlayerEntity implements IServerPlayerEntityMixin {
    @Shadow public abstract ServerWorld getWorld();

    @Unique
    private EnderBedData ENDER_BED_DATA = null;

    public ServerPlayerEntityMixin(World world, GameProfile profile) {
        super(world, profile);
        throw new AssertionError("Mixin class instantiated - this shouldn't be possible!");
    }

    @WrapWithCondition(method = "trySleep", at = @At(value = "INVOKE", target = "Lnet/minecraft/server/network/ServerPlayerEntity;setSpawnPoint(Lnet/minecraft/server/network/ServerPlayerEntity$Respawn;Z)V"))
    public boolean shouldSetRespawn(ServerPlayerEntity player, ServerPlayerEntity.Respawn respawn, boolean sendMessage, @Local(argsOnly = true) BlockPos blockPos) {
        // We are in a regular bed
        if(!(player.getWorld().getBlockState(blockPos).getBlock() instanceof EnderBedBlock))
            return true;

        // We are in an EnderBedBlock
        ENDER_BED_DATA = new EnderBedData(blockPos, this.getWorld());
        return false;
    }

    @Override
    public boolean enderbeds_isInEnderBed() {
        return ENDER_BED_DATA != null;
    }

    @Override
    public void enderbeds_wakeFromEnderBed() {
        EnderBedBlock.onWake(
            (ServerPlayerEntity) (PlayerEntity) this,
            ENDER_BED_DATA.bedPos(),
            ENDER_BED_DATA.bedWorld()
        );

        ENDER_BED_DATA = null;
    }
}