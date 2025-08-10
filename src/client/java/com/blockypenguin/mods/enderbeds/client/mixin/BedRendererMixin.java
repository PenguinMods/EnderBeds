package com.blockypenguin.mods.enderbeds.client.mixin;

import com.blockypenguin.mods.enderbeds.EnderBeds;
import com.blockypenguin.mods.enderbeds.block.EnderBedModBlocks;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.block.entity.BedBlockEntity;
import net.minecraft.client.render.TexturedRenderLayers;
import net.minecraft.client.render.block.entity.BedBlockEntityRenderer;
import net.minecraft.client.render.block.entity.BlockEntityRenderer;
import net.minecraft.client.util.SpriteIdentifier;
import net.minecraft.util.DyeColor;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(BedBlockEntityRenderer.class)
public abstract class BedRendererMixin implements BlockEntityRenderer<BedBlockEntity> {
    @Unique
    private static final SpriteIdentifier ENDER_BED_MATERIAL = TexturedRenderLayers.BED_SPRITE_MAPPER.map(EnderBeds.id("ender"));

    @WrapOperation(method = "render(Lnet/minecraft/block/entity/BedBlockEntity;FLnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/render/VertexConsumerProvider;IILnet/minecraft/util/math/Vec3d;)V", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/render/TexturedRenderLayers;getBedTextureId(Lnet/minecraft/util/DyeColor;)Lnet/minecraft/client/util/SpriteIdentifier;"))
    public SpriteIdentifier useEnderBedSpriteWhenAppropriate(DyeColor colour, Operation<SpriteIdentifier> original, @Local(argsOnly = true) BedBlockEntity bedBlockEntity) {
        if (bedBlockEntity.getCachedState().isOf(EnderBedModBlocks.ENDER_BED))
            return ENDER_BED_MATERIAL;

        return original.call(colour);
    }
}
