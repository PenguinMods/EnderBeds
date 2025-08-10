package com.blockypenguin.mods.enderbeds.datagen;

import com.blockypenguin.mods.enderbeds.item.EnderBedModItems;
import com.blockypenguin.mods.enderbeds.tag.EnderBedModItemTags;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.fabricmc.fabric.api.tag.convention.v2.ConventionalItemTags;
import net.minecraft.registry.Registries;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.ItemTags;

import java.util.concurrent.CompletableFuture;

class ItemTagProvider extends FabricTagProvider.ItemTagProvider {
    protected ItemTagProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected void configure(RegistryWrapper.WrapperLookup wrapperLookup) {
        this.builder(ItemTags.BEDS)
            .add(Registries.ITEM.getKey(EnderBedModItems.ENDER_BED).orElseThrow())
            .setReplace(false);

        this.builder(EnderBedModItemTags.ECHO_DUSTS)
            .add(Registries.ITEM.getKey(EnderBedModItems.ECHO_DUST).orElseThrow())
            .setReplace(false);

        this.builder(EnderBedModItemTags.CONVENTIONAL_ECHO_DUSTS)
            .addTag(EnderBedModItemTags.ECHO_DUSTS)
            .setReplace(false);

        this.builder(ConventionalItemTags.DUSTS)
            .addTag(EnderBedModItemTags.CONVENTIONAL_ECHO_DUSTS)
            .setReplace(false);
    }
}
