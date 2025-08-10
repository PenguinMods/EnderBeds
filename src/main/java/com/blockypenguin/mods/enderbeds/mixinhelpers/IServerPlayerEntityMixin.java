package com.blockypenguin.mods.enderbeds.mixinhelpers;

import org.apache.commons.lang3.NotImplementedException;

public interface IServerPlayerEntityMixin {
    default boolean enderbeds_isInEnderBed() { throw new NotImplementedException("Methods in IServerPlayerEntity must be overridden!"); }
    default void enderbeds_wakeFromEnderBed() { enderbeds_isInEnderBed(); }
}
