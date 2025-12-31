package com.blockypenguin.mods.enderbeds.mixin;

import com.blockypenguin.mods.enderbeds.gamerule.EnderBedModGameRules;
import com.llamalad7.mixinextras.injector.wrapmethod.WrapMethod;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.mojang.serialization.Dynamic;
import net.minecraft.util.datafix.fixes.GameRuleRegistryFix;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(GameRuleRegistryFix.class)
public abstract class GameRuleRegistryFixMixin {
    @Invoker
    private static Dynamic<?> invokeConvertInteger(Dynamic<?> dynamic, int min, int max) {throw new AssertionError();}

    @WrapMethod(method = "method_76071")
    private static Dynamic<?> addGameRules(Dynamic<?> dynamicx, Operation<Dynamic<?>> original) {
        return original.call(dynamicx).renameField(
            "enderBedWakeBehaviour",
            EnderBedModGameRules.WAKE_BEHAVIOUR.getIdentifier().toString()
        ).renameAndFixField(
            "enderBedSpawnEndermiteChance",
            EnderBedModGameRules.ENDERMITE_SPAWN_CHANCE.getIdentifier().toString(),
            dynamic -> invokeConvertInteger(dynamic, 0, 100)
        );
    }
}
