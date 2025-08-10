package com.blockypenguin.mods.enderbeds.mixinhelpers;

import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;

public record EnderBedData(BlockPos bedPos, ServerWorld bedWorld) {}