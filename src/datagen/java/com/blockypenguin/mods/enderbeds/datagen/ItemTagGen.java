package com.blockypenguin.mods.enderbeds.datagen;

import com.blockypenguin.mods.enderbeds.item.EnderBedModItems;
import com.blockypenguin.mods.enderbeds.tag.EnderBedModItemTags;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.fabricmc.fabric.api.tag.convention.v2.ConventionalItemTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.tags.ItemTags;

import java.util.concurrent.CompletableFuture;

class ItemTagGen extends FabricTagProvider.ItemTagProvider {
    protected ItemTagGen(FabricDataOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected void addTags(HolderLookup.Provider registryLookup) {
        this.builder(ItemTags.BEDS)
            .add(BuiltInRegistries.ITEM.getResourceKey(EnderBedModItems.ENDER_BED).orElseThrow())
            .setReplace(false);

        this.builder(EnderBedModItemTags.ECHO_DUSTS)
            .add(BuiltInRegistries.ITEM.getResourceKey(EnderBedModItems.ECHO_DUST).orElseThrow())
            .setReplace(false);

        this.builder(EnderBedModItemTags.CONVENTIONAL_ECHO_DUSTS)
            .addTag(EnderBedModItemTags.ECHO_DUSTS)
            .setReplace(false);

        this.builder(ConventionalItemTags.DUSTS)
            .addTag(EnderBedModItemTags.CONVENTIONAL_ECHO_DUSTS)
            .setReplace(false);
    }
}
