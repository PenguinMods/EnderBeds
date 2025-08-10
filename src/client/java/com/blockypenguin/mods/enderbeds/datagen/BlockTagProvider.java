package com.blockypenguin.mods.enderbeds.datagen;

import com.blockypenguin.mods.enderbeds.block.EnderBedModBlocks;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.registry.Registries;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.BlockTags;

import java.util.concurrent.CompletableFuture;

class BlockTagProvider extends FabricTagProvider.BlockTagProvider {
    protected BlockTagProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected void configure(RegistryWrapper.WrapperLookup wrapperLookup) {
        this.builder(BlockTags.BEDS)
            .add(Registries.BLOCK.getKey(EnderBedModBlocks.ENDER_BED).orElseThrow())
            .setReplace(false);
    }
}
