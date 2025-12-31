package com.blockypenguin.mods.enderbeds.mixin;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(ServerLevel.class)
public abstract class ServerLevelMixin {
    @WrapOperation(method = "method_18773", at = @At(value = "INVOKE", target = "Lnet/minecraft/server/level/ServerPlayer;stopSleepInBed(ZZ)V"))
    private static void onAllPlayersWakeUp(ServerPlayer instance, boolean skipSleepTimer, boolean updateSleepingPlayers, Operation<Void> original) {
        original.call(instance, skipSleepTimer, updateSleepingPlayers);

        if(instance.enderbeds_isInEnderBed())
            instance.enderbeds_wakeFromEnderBed();
    }
}
