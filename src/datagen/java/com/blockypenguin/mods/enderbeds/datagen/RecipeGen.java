package com.blockypenguin.mods.enderbeds.datagen;

import com.blockypenguin.mods.enderbeds.item.EnderBedModItems;
import com.blockypenguin.mods.enderbeds.tag.EnderBedModItemTags;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Items;

import java.util.concurrent.CompletableFuture;

class RecipeGen extends FabricRecipeProvider {
    protected RecipeGen(FabricDataOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected RecipeProvider createRecipeProvider(HolderLookup.Provider registryLookup, RecipeOutput exporter) {
        return new RecipeProvider(registryLookup, exporter) {
            @Override
            public void buildRecipes() {
                shapeless(RecipeCategory.MISC, EnderBedModItems.ENDER_FABRIC, 3)
                    .requires(EnderBedModItemTags.CONVENTIONAL_ECHO_DUSTS)
                    .requires(EnderBedModItemTags.CONVENTIONAL_ECHO_DUSTS)
                    .requires(Items.ENDER_EYE)
                    .requires(Items.BLACK_WOOL)
                    .unlockedBy(getHasName(Items.ECHO_SHARD), has(Items.ECHO_SHARD))
                    .save(exporter);

                oneToOneConversionRecipe(EnderBedModItems.ECHO_DUST, Items.ECHO_SHARD, null, 2);

                shaped(RecipeCategory.DECORATIONS, EnderBedModItems.ENDER_BED)
                    .pattern("fff")
                    .pattern("www")
                    .define('f', EnderBedModItems.ENDER_FABRIC)
                    .define('w', ItemTags.PLANKS)
                    .unlockedBy(getHasName(EnderBedModItems.ECHO_DUST), has(EnderBedModItemTags.CONVENTIONAL_ECHO_DUSTS))
                    .save(exporter);
            }
        };

    }

    @Override
    public String getName() {
        return "Recipes for EnderBeds";
    }
}
