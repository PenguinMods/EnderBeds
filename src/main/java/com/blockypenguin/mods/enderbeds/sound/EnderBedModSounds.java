package com.blockypenguin.mods.enderbeds.sound;

import com.blockypenguin.mods.enderbeds.EnderBeds;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;

public class EnderBedModSounds {
    public static final SoundEvent ENDER_BED_TELEPORT_SOUND = register("block.ender_bed.teleport");

    private static SoundEvent register(@SuppressWarnings("SameParameterValue") String name) {
        Identifier id = EnderBeds.id(name);
        return Registry.register(Registries.SOUND_EVENT, id, SoundEvent.of(id));
    }

    public static void init() {}
}
