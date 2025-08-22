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

    public static final GameRules.Key<GameRules.IntRule> ENDER_BED_SPAWN_ENDERMITE_CHANCE = GameRuleRegistry.register(
            "enderBedSpawnEndermiteChance",
            GameRules.Category.SPAWNING,
            GameRuleFactory.createIntRule(5, 0, 100)
    );

    public static void init() {}
}
