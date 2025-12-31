package com.blockypenguin.mods.enderbeds.mixin;

import com.blockypenguin.mods.enderbeds.block.EnderBedBlock;
import com.blockypenguin.mods.enderbeds.mixinhelpers.EnderBedData;
import com.blockypenguin.mods.enderbeds.mixinhelpers.IServerPlayer;
import com.llamalad7.mixinextras.injector.v2.WrapWithCondition;
import com.llamalad7.mixinextras.sugar.Local;
import com.mojang.authlib.GameProfile;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(ServerPlayer.class)
public abstract class ServerPlayerMixin extends Player implements IServerPlayer {
    @Shadow
    public abstract ServerLevel level();

    @Unique
    private EnderBedData ENDER_BED_DATA = null;

    public ServerPlayerMixin(Level world, GameProfile profile) {
        super(world, profile);
        throw new AssertionError("Mixin class instantiated - this shouldn't be possible!");
    }

    @WrapWithCondition(method = "startSleepInBed", at = @At(value = "INVOKE", target = "Lnet/minecraft/server/level/ServerPlayer;setRespawnPosition(Lnet/minecraft/server/level/ServerPlayer$RespawnConfig;Z)V"))
    public boolean shouldSetRespawn(ServerPlayer player, ServerPlayer.RespawnConfig respawn, boolean sendMessage, @Local(argsOnly = true) BlockPos blockPos) {
        // We are in a regular bed
        if(!(player.level().getBlockState(blockPos).getBlock() instanceof EnderBedBlock))
            return true;

        // We are in an EnderBedBlock
        ENDER_BED_DATA = new EnderBedData(blockPos, this.level());
        return false;
    }

    @Override
    public boolean enderbeds_isInEnderBed() {
        return ENDER_BED_DATA != null;
    }

    @Override
    public void enderbeds_wakeFromEnderBed() {
        EnderBedBlock.onWake(
            (ServerPlayer) (Player) this,
            ENDER_BED_DATA.bedPos(),
            ENDER_BED_DATA.bedWorld()
        );

        ENDER_BED_DATA = null;
    }
}