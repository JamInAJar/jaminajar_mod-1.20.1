package io.github.jaminajar.jaminajarmod.mixin;

import io.github.jaminajar.jaminajarmod.effects.ModEffects;
import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.client.render.entity.EntityRenderer;
import net.minecraft.entity.Entity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(EntityRenderer.class)
public class PhantomLabelMixin {

    @Inject(
            method = "hasLabel(Lnet/minecraft/entity/Entity;)Z",
            at = @At("HEAD"),
            cancellable = true
    )
    private void hideLabelWhenPhantom(Entity entity, CallbackInfoReturnable<Boolean> cir) {
        if (entity instanceof AbstractClientPlayerEntity player
                && player.hasStatusEffect(ModEffects.PHANTOM)) {

            cir.setReturnValue(false);
        }
    }
}