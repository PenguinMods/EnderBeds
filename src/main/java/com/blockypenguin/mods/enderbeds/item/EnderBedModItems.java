package com.blockypenguin.mods.enderbeds.item;

import com.blockypenguin.mods.enderbeds.EnderBeds;
import com.blockypenguin.mods.enderbeds.block.EnderBedModBlocks;
import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;
import net.minecraft.util.Rarity;

import java.util.function.BiFunction;
import java.util.function.Function;

public class EnderBedModItems {
    public static final BlockItem ENDER_BED = register(
        EnderBedModBlocks.ENDER_BED,
        BlockItem::new,
        new Item.Settings()
            .maxCount(1)
            .rarity(Rarity.UNCOMMON)
    );

    public static final Item ENDER_FABRIC = register("ender_fabric", new Item.Settings().rarity(Rarity.UNCOMMON));
    public static final Item ECHO_DUST = register("echo_dust", new Item.Settings().rarity(Rarity.UNCOMMON));

    private static <T extends Item> T register(@SuppressWarnings("SameParameterValue") Block block, BiFunction<Block, Item.Settings, T> itemFactory, Item.Settings settings) {
        return register(Registries.BLOCK.getKey(block).orElseThrow().getValue(), itemSettings -> itemFactory.apply(block, itemSettings), settings);
    }

    private static Item register(String name, Item.Settings settings) {
        return register(name, Item::new, settings);
    }

    private static <T extends Item> T register(String name, Function<Item.Settings, T> itemFactory, Item.Settings settings) {
        return register(EnderBeds.id(name), itemFactory, settings);
    }

    private static <T extends Item> T register(Identifier id, Function<Item.Settings, T> itemFactory, Item.Settings settings) {
        RegistryKey<Item> itemKey = RegistryKey.of(RegistryKeys.ITEM, id);

        T item = itemFactory.apply(settings.registryKey(itemKey));
        Registry.register(Registries.ITEM, itemKey, item);
        return item;
    }

    public static void init() {}
}
