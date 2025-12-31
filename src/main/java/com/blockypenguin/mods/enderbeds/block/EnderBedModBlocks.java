package com.blockypenguin.mods.enderbeds.block;

import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;

public class EnderBedModBlocks {
    public static final EnderBedBlock ENDER_BED = Registry.register(
        BuiltInRegistries.BLOCK,
        EnderBedBlock.ID,
        new EnderBedBlock()
    );

    public static void init() {}
}
