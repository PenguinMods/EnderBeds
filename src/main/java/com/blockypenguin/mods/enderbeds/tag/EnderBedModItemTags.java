package com.blockypenguin.mods.enderbeds.tag;

import com.blockypenguin.mods.enderbeds.EnderBeds;
import net.minecraft.item.Item;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;

public final class EnderBedModItemTags {
    public static final TagKey<Item> ECHO_DUSTS = TagKey.of(RegistryKeys.ITEM, EnderBeds.id("echo_dusts"));
    public static final TagKey<Item> CONVENTIONAL_ECHO_DUSTS = TagKey.of(RegistryKeys.ITEM, Identifier.of("c", "dusts/echo"));
}