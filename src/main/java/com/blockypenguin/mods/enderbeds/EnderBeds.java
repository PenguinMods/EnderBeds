package com.blockypenguin.mods.enderbeds;

import com.blockypenguin.mods.enderbeds.block.EnderBedModBlocks;
import com.blockypenguin.mods.enderbeds.gamerule.EnderBedModGameRules;
import com.blockypenguin.mods.enderbeds.item.EnderBedModItems;
import com.blockypenguin.mods.enderbeds.sound.EnderBedModSounds;
import com.mojang.logging.LogUtils;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.entity.BlockEntityType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EnderBeds implements ModInitializer {
    private static final Logger LOGGER = LogUtils.getLogger();
    static { LOGGER.info("Loading EnderBeds..."); }

    public static final String MOD_ID = "ender_beds";

    @Override
    public void onInitialize() {
        LOGGER.info("Registering content...");
        EnderBedModBlocks.init();
        EnderBedModItems.init();
        EnderBedModGameRules.init();
        EnderBedModSounds.init();

        LOGGER.info("Modifying item groups...");

        ItemGroupEvents.modifyEntriesEvent(CreativeModeTabs.FUNCTIONAL_BLOCKS)
            .register(itemGroup -> itemGroup.addAfter(
                Items.PINK_BED,
                EnderBedModItems.ENDER_BED
            ));

        ItemGroupEvents.modifyEntriesEvent(CreativeModeTabs.INGREDIENTS)
            .register(itemGroup -> {
                itemGroup.addAfter(Items.ENDER_EYE, EnderBedModItems.ENDER_FABRIC);
                itemGroup.addAfter(Items.ECHO_SHARD, EnderBedModItems.ECHO_DUST);
            });

        LOGGER.info(
            "Adding {} to {}...",
            BuiltInRegistries.BLOCK.getResourceKey(EnderBedModBlocks.ENDER_BED).orElseThrow(),
            BuiltInRegistries.BLOCK_ENTITY_TYPE.getResourceKey(BlockEntityType.BED).orElseThrow()
        );
        BlockEntityType.BED.addSupportedBlock(EnderBedModBlocks.ENDER_BED);

        LOGGER.info("Done. EnderBeds is ready.");
    }

    public static Identifier id(String path) {
        return Identifier.fromNamespaceAndPath(MOD_ID, path);
    }

    @SuppressWarnings("unused")
    public static Logger logger(String path) {
        return LoggerFactory.getLogger(LOGGER.getName() + "/" + path);
    }
}
