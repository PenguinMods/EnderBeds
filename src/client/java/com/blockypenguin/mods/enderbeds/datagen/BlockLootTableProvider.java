package com.blockypenguin.mods.enderbeds.datagen;

import com.blockypenguin.mods.enderbeds.block.EnderBedModBlocks;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;
import net.minecraft.block.BedBlock;
import net.minecraft.block.enums.BedPart;
import net.minecraft.registry.RegistryWrapper;

import java.util.concurrent.CompletableFuture;

class BlockLootTableProvider extends FabricBlockLootTableProvider {
    protected BlockLootTableProvider(FabricDataOutput dataOutput, CompletableFuture<RegistryWrapper.WrapperLookup> registryLookup) {
        super(dataOutput, registryLookup);
    }

    @Override
    public void generate() {
        this.addDrop(EnderBedModBlocks.ENDER_BED, block -> this.dropsWithProperty(block, BedBlock.PART, BedPart.HEAD));
    }
}
