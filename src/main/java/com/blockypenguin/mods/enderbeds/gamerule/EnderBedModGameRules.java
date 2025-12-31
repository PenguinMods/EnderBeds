package com.blockypenguin.mods.enderbeds.gamerule;

import com.blockypenguin.mods.enderbeds.EnderBeds;
import net.fabricmc.fabric.api.gamerule.v1.GameRuleBuilder;
import net.minecraft.world.level.gamerules.GameRule;
import net.minecraft.world.level.gamerules.GameRuleCategory;

public class EnderBedModGameRules {
    public static final GameRule<EnderBedWakeBehaviour> WAKE_BEHAVIOUR =
        GameRuleBuilder.forEnum(EnderBedWakeBehaviour.DO_NOTHING)
            .buildAndRegister(EnderBeds.id("wake_behaviour"));

    public static final GameRule<Integer> ENDERMITE_SPAWN_CHANCE =
        GameRuleBuilder.forInteger(5).range(0, 100)
            .category(GameRuleCategory.SPAWNING)
            .buildAndRegister(EnderBeds.id("endermite_spawn_chance"));

    public static void init() {}
}
