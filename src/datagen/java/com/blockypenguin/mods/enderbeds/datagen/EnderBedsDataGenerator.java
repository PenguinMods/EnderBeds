package com.blockypenguin.mods.enderbeds.datagen;

import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;

public class EnderBedsDataGenerator implements DataGeneratorEntrypoint {

    @Override
    public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
        FabricDataGenerator.Pack pack = fabricDataGenerator.createPack();
        pack.addProvider(EnglishLangGen::new);
        pack.addProvider(BlockLootTableGen::new);
        pack.addProvider(BlockTagGen::new);
        pack.addProvider(ItemTagGen::new);
        pack.addProvider(RecipeGen::new);
    }
}