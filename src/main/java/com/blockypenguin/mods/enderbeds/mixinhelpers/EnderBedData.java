package com.blockypenguin.mods.enderbeds.mixinhelpers;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;

public record EnderBedData(BlockPos bedPos, ServerLevel bedWorld) {}