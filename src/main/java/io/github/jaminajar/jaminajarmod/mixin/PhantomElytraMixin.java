package io.github.jaminajar.jaminajarmod.mixin;

import io.github.jaminajar.jaminajarmod.effects.ModEffects;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.entity.feature.ElytraFeatureRenderer;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArgs;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.invoke.arg.Args;

@Mixin(ElytraFeatureRenderer.class)
public abstract class PhantomElytraMixin {

    @Redirect(
            method = "render",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/render/RenderLayer;getArmorCutoutNoCull(Lnet/minecraft/util/Identifier;)Lnet/minecraft/client/render/RenderLayer;"
            )
    )
    private RenderLayer redirectElytraLayer(Identifier texture) {
        if (MinecraftClient.getInstance().player.hasStatusEffect(ModEffects.PHANTOM)) {
            return RenderLayer.getEntityTranslucent(texture);
        }
        return RenderLayer.getArmorCutoutNoCull(texture);
    }

    @ModifyArgs(
            method = "render",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/render/entity/model/ElytraEntityModel;render(" +
                            "Lnet/minecraft/client/util/math/MatrixStack;" +
                            "Lnet/minecraft/client/render/VertexConsumer;IIFFFF)V"
            )
    )
    private void modifyElytraAlpha(Args args) {
        if (MinecraftClient.getInstance().player.hasStatusEffect(ModEffects.PHANTOM)) {
            args.set(7, 0.4f);
        }
    }
}
