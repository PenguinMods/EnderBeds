package com.blockypenguin.mods.enderbeds.mixin;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(ServerWorld.class)
public abstract class ServerWorldMixin {
    @WrapOperation(method = "method_18773", at = @At(value = "INVOKE", target = "Lnet/minecraft/server/network/ServerPlayerEntity;wakeUp(ZZ)V"))
    private static void onAllPlayersWakeUp(
        ServerPlayerEntity instance, boolean skipSleepTimer, boolean updateSleepingPlayers, Operation<Void> original
    ) {
        original.call(instance, skipSleepTimer, updateSleepingPlayers);

        if(instance.enderbeds_isInEnderBed())
            instance.enderbeds_wakeFromEnderBed();
    }
}
