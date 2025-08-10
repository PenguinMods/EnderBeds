package com.blockypenguin.mods.enderbeds.datagen;

import com.blockypenguin.mods.enderbeds.item.EnderBedModItems;
import com.blockypenguin.mods.enderbeds.tag.EnderBedModItemTags;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.data.recipe.RecipeExporter;
import net.minecraft.data.recipe.RecipeGenerator;
import net.minecraft.item.Items;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.ItemTags;

import java.util.concurrent.CompletableFuture;

class RecipeProvider extends FabricRecipeProvider {
    protected RecipeProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected RecipeGenerator getRecipeGenerator(RegistryWrapper.WrapperLookup registryLookup, RecipeExporter exporter) {
        return new RecipeGenerator(registryLookup, exporter) {
            @Override
            public void generate() {
                createShapeless(RecipeCategory.MISC, EnderBedModItems.ENDER_FABRIC, 3)
                    .input(EnderBedModItemTags.CONVENTIONAL_ECHO_DUSTS)
                    .input(EnderBedModItemTags.CONVENTIONAL_ECHO_DUSTS)
                    .input(Items.ENDER_EYE)
                    .input(Items.BLACK_WOOL)
                    .criterion(hasItem(Items.ECHO_SHARD), conditionsFromItem(Items.ECHO_SHARD))
                    .offerTo(exporter);

                offerShapelessRecipe(EnderBedModItems.ECHO_DUST, Items.ECHO_SHARD, null, 2);

                createShaped(RecipeCategory.DECORATIONS, EnderBedModItems.ENDER_BED)
                    .pattern("fff")
                    .pattern("www")
                    .input('f', EnderBedModItems.ENDER_FABRIC)
                    .input('w', ItemTags.PLANKS)
                    .criterion(hasItem(EnderBedModItems.ECHO_DUST), this.conditionsFromTag(EnderBedModItemTags.CONVENTIONAL_ECHO_DUSTS))
                    .offerTo(this.exporter);
            }
        };

    }

    @Override
    public String getName() {
        return "Recipes for EnderBeds";
    }
}
