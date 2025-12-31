package com.blockypenguin.mods.enderbeds.datagen;

import com.blockypenguin.mods.enderbeds.block.EnderBedModBlocks;
import com.blockypenguin.mods.enderbeds.gamerule.EnderBedModGameRules;
import com.blockypenguin.mods.enderbeds.item.EnderBedModItems;
import com.blockypenguin.mods.enderbeds.sound.EnderBedModSounds;
import com.blockypenguin.mods.enderbeds.tag.EnderBedModItemTags;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider;
import net.minecraft.core.HolderLookup;

import java.util.concurrent.CompletableFuture;

class EnglishLangGen extends FabricLanguageProvider {
    protected EnglishLangGen(FabricDataOutput dataOutput, CompletableFuture<HolderLookup.Provider> registryLookup) {
        super(dataOutput, "en_us", registryLookup);
    }

    @Override
    public void generateTranslations(HolderLookup.Provider registryLookup, TranslationBuilder translationBuilder) {
        translationBuilder.add(EnderBedModBlocks.ENDER_BED, "Ender Bed");
        translationBuilder.add(EnderBedModItems.ENDER_BED, "Ender Bed");
        translationBuilder.add(EnderBedModItems.ENDER_FABRIC, "Ender Fabric");
        translationBuilder.add(EnderBedModItems.ECHO_DUST, "Echo Dust");
        translationBuilder.add(EnderBedModSounds.ENDER_BED_TELEPORT_SOUND, "Spacetime bends to the will of a dreamer");
        translationBuilder.add(EnderBedModGameRules.WAKE_BEHAVIOUR.getDescriptionId(), "Ender Bed wake behaviour");
        translationBuilder.add(EnderBedModGameRules.WAKE_BEHAVIOUR.getDescriptionId() + ".do_nothing", "Do nothing");
        translationBuilder.add(EnderBedModGameRules.WAKE_BEHAVIOUR.getDescriptionId() + ".destroy_bed", "Destroy bed");
        translationBuilder.add(EnderBedModGameRules.WAKE_BEHAVIOUR.getDescriptionId() + ".return_item_to_player", "Return to player");
        translationBuilder.add(EnderBedModGameRules.ENDERMITE_SPAWN_CHANCE.getDescriptionId(), "Percentage chance to spawn an Endermite from an Ender Bed");
        translationBuilder.add(EnderBedModItemTags.ECHO_DUSTS, "Echo Dusts");
        translationBuilder.add(EnderBedModItemTags.CONVENTIONAL_ECHO_DUSTS, "Echo Dusts");
    }
}
