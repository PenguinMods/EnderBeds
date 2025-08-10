package com.blockypenguin.mods.enderbeds.gamerule;

import net.fabricmc.fabric.api.gamerule.v1.GameRuleFactory;
import net.fabricmc.fabric.api.gamerule.v1.GameRuleRegistry;
import net.fabricmc.fabric.api.gamerule.v1.rule.EnumRule;
import net.minecraft.world.GameRules;

public class EnderBedModGameRules {
    public static final GameRules.Key<EnumRule<EnderBedWakeBehaviour>> ENDER_BED_WAKE_BEHAVIOUR = GameRuleRegistry.register(
        "enderBedWakeBehaviour",
        GameRules.Category.MISC,
        GameRuleFactory.createEnumRule(EnderBedWakeBehaviour.DO_NOTHING)
    );

    public static void init() {}
}
