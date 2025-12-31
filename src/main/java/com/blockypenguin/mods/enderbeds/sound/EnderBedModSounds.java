package com.blockypenguin.mods.enderbeds.sound;

import com.blockypenguin.mods.enderbeds.EnderBeds;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.sounds.SoundEvent;

public class EnderBedModSounds {
    public static final SoundEvent ENDER_BED_TELEPORT_SOUND = register("block.ender_bed.teleport");

    private static SoundEvent register(@SuppressWarnings("SameParameterValue") String name) {
        Identifier id = EnderBeds.id(name);
        return Registry.register(BuiltInRegistries.SOUND_EVENT, id, SoundEvent.createVariableRangeEvent(id));
    }

    public static void init() {}
}
