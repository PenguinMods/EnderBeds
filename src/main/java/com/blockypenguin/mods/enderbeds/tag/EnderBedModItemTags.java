package com.blockypenguin.mods.enderbeds.tag;

import com.blockypenguin.mods.enderbeds.EnderBeds;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;

public final class EnderBedModItemTags {
    public static final TagKey<Item> ECHO_DUSTS = TagKey.create(Registries.ITEM, EnderBeds.id("echo_dusts"));
    public static final TagKey<Item> CONVENTIONAL_ECHO_DUSTS = TagKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath("c", "dusts/echo"));
}