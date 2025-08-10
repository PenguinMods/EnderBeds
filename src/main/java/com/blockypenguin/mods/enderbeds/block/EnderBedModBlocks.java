package com.blockypenguin.mods.enderbeds.block;

import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;

public class EnderBedModBlocks {
    public static final EnderBedBlock ENDER_BED = Registry.register(
        Registries.BLOCK,
        EnderBedBlock.ID,
        new EnderBedBlock()
    );

    public static void init() {}
}
