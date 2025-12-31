package com.blockypenguin.mods.enderbeds.datagen;

import com.blockypenguin.mods.enderbeds.block.EnderBedModBlocks;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.world.level.block.BedBlock;
import net.minecraft.world.level.block.state.properties.BedPart;

import java.util.concurrent.CompletableFuture;

class BlockLootTableGen extends FabricBlockLootTableProvider {
    protected BlockLootTableGen(FabricDataOutput dataOutput, CompletableFuture<HolderLookup.Provider> registryLookup) {
        super(dataOutput, registryLookup);
    }

    @Override
    public void generate() {
        this.add(EnderBedModBlocks.ENDER_BED, block -> this.createSinglePropConditionTable(block, BedBlock.PART, BedPart.HEAD));
    }
}
