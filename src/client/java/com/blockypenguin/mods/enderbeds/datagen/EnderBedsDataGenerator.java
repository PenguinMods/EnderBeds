package com.blockypenguin.mods.enderbeds.datagen;

import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;

public class EnderBedsDataGenerator implements DataGeneratorEntrypoint {

    @Override
    public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
        FabricDataGenerator.Pack pack = fabricDataGenerator.createPack();
        pack.addProvider(EnglishLangProvider::new);
        pack.addProvider(BlockLootTableProvider::new);
        pack.addProvider(BlockTagProvider::new);
        pack.addProvider(ItemTagProvider::new);
        pack.addProvider(RecipeProvider::new);
    }
}