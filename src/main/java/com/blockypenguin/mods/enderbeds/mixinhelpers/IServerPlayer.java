package com.blockypenguin.mods.enderbeds.mixinhelpers;

public interface IServerPlayer {
    default boolean enderbeds_isInEnderBed() { throw new AssertionError("Methods in IServerPlayerEntityMixin must be overridden!"); }
    default void enderbeds_wakeFromEnderBed() { throw new AssertionError("Methods in IServerPlayerEntityMixin must be overridden!"); }
}
