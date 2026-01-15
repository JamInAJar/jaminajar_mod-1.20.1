package io.github.jaminajar.jaminajarmod.mixin;

import io.github.jaminajar.jaminajarmod.effects.ModEffects;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.RenderLayers;
import net.minecraft.client.render.TexturedRenderLayers;
import net.minecraft.client.render.item.ItemRenderer;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(ItemRenderer.class)
public abstract class PhantomHeldItemMixin {

    @Redirect(
            method = "renderItem(Lnet/minecraft/item/ItemStack;Lnet/minecraft/client/render/model/json/ModelTransformationMode;ZLnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/render/VertexConsumerProvider;IILnet/minecraft/client/render/model/BakedModel;)V",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/render/RenderLayers;getItemLayer(Lnet/minecraft/item/ItemStack;Z)Lnet/minecraft/client/render/RenderLayer;"
            )
    )
    private RenderLayer phantomItemLayer(ItemStack stack, boolean solid) {
        PlayerEntity player = MinecraftClient.getInstance().player;

        if (player != null && player.hasStatusEffect(ModEffects.PHANTOM)) {
            return TexturedRenderLayers.getItemEntityTranslucentCull();
        }
        return RenderLayers.getItemLayer(stack, solid);
    }
}
