package com.blockypenguin.mods.enderbeds.item;

import com.blockypenguin.mods.enderbeds.EnderBeds;
import com.blockypenguin.mods.enderbeds.block.EnderBedModBlocks;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.level.block.Block;

import java.util.function.BiFunction;
import java.util.function.Function;

public class EnderBedModItems {
    public static final BlockItem ENDER_BED = register(
        EnderBedModBlocks.ENDER_BED,
        BlockItem::new,
        new Item.Properties()
            .stacksTo(1)
            .rarity(Rarity.UNCOMMON)
    );

    public static final Item ENDER_FABRIC = register("ender_fabric", new Item.Properties().rarity(Rarity.UNCOMMON));
    public static final Item ECHO_DUST = register("echo_dust", new Item.Properties().rarity(Rarity.UNCOMMON));

    private static <T extends Item> T register(@SuppressWarnings("SameParameterValue") Block block, BiFunction<Block, Item.Properties, T> itemFactory, Item.Properties settings) {
        return register(BuiltInRegistries.BLOCK.getResourceKey(block).orElseThrow().identifier(), itemSettings -> itemFactory.apply(block, itemSettings), settings);
    }

    private static Item register(String name, Item.Properties settings) {
        return register(name, Item::new, settings);
    }

    private static <T extends Item> T register(String name, Function<Item.Properties, T> itemFactory, Item.Properties settings) {
        return register(EnderBeds.id(name), itemFactory, settings);
    }

    private static <T extends Item> T register(Identifier id, Function<Item.Properties, T> itemFactory, Item.Properties settings) {
        ResourceKey<Item> itemKey = ResourceKey.create(Registries.ITEM, id);

        T item = itemFactory.apply(settings.setId(itemKey));
        Registry.register(BuiltInRegistries.ITEM, itemKey, item);
        return item;
    }

    public static void init() {}
}
