package com.blockypenguin.mods.enderbeds.datagen;

import com.blockypenguin.mods.enderbeds.block.EnderBedModBlocks;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.BuiltInRegistries;

import java.util.concurrent.CompletableFuture;

class BlockTagGen extends FabricTagProvider.BlockTagProvider {
    protected BlockTagGen(FabricDataOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected void addTags(HolderLookup.Provider registryLookup) {
        this.builder(net.minecraft.tags.BlockTags.BEDS)
            .add(BuiltInRegistries.BLOCK.getResourceKey(EnderBedModBlocks.ENDER_BED).orElseThrow())
            .setReplace(false);
    }
}
