package com.blockypenguin.mods.enderbeds.client.mixin;

import com.blockypenguin.mods.enderbeds.EnderBeds;
import com.blockypenguin.mods.enderbeds.block.EnderBedModBlocks;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.client.renderer.Sheets;
import net.minecraft.client.renderer.blockentity.BedRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.state.BedRenderState;
import net.minecraft.client.resources.model.Material;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.level.block.entity.BedBlockEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(BedRenderer.class)
public abstract class BedRendererMixin implements BlockEntityRenderer<BedBlockEntity, BedRenderState> {
    @Unique
    private static final Material ENDER_BED_MATERIAL = Sheets.BED_MAPPER.apply(EnderBeds.id("ender"));

    @WrapOperation(method = "submit(Lnet/minecraft/client/renderer/blockentity/state/BedRenderState;Lcom/mojang/blaze3d/vertex/PoseStack;Lnet/minecraft/client/renderer/SubmitNodeCollector;Lnet/minecraft/client/renderer/state/CameraRenderState;)V", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/renderer/Sheets;getBedMaterial(Lnet/minecraft/world/item/DyeColor;)Lnet/minecraft/client/resources/model/Material;"))
    public Material useEnderBedSpriteWhenAppropriate(DyeColor colour, Operation<Material> original, @Local(argsOnly = true) BedRenderState bedRenderState) {
        if (bedRenderState.blockState.is(EnderBedModBlocks.ENDER_BED))
            return ENDER_BED_MATERIAL;

        return original.call(colour);
    }
}
