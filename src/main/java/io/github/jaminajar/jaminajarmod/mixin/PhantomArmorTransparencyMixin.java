package io.github.jaminajar.jaminajarmod.mixin;

import io.github.jaminajar.jaminajarmod.effects.ModEffects;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.entity.feature.ArmorFeatureRenderer;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArgs;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.invoke.arg.Args;

@Mixin(ArmorFeatureRenderer.class)
public abstract class PhantomArmorTransparencyMixin {

    // Force armor to use a translucent layer instead of cutout
    @Redirect(
            method = "renderArmorParts",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/render/RenderLayer;" +
                            "getArmorCutoutNoCull(Lnet/minecraft/util/Identifier;)" +
                            "Lnet/minecraft/client/render/RenderLayer;"
            )
    )
    private RenderLayer redirectArmorLayer(Identifier texture) {
        return RenderLayer.getEntityTranslucent(texture);
    }

    // Change the alpha passed to the armor model
    @ModifyArgs(
            method = "renderArmorParts",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/render/entity/model/BipedEntityModel;" +
                            "render(Lnet/minecraft/client/util/math/MatrixStack;" +
                            "Lnet/minecraft/client/render/VertexConsumer;IIFFFF)V"
            )
    )
    private void modifyArmorAlpha(Args args) {
        LivingEntity entity = net.minecraft.client.MinecraftClient.getInstance().player;
        if (entity != null && entity.hasStatusEffect(ModEffects.PHANTOM)) {
            args.set(7, 0.5f); // ARMOR alpha (50% opacity)
        }
    }
}
