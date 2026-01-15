package io.github.jaminajar.jaminajarmod.mixin;

import io.github.jaminajar.jaminajarmod.effects.ModEffects;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.mob.MobEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(MobEntity.class)
public abstract class PhantomAggroMixin {

    @Inject(
            method = "setTarget",
            at = @At("HEAD"),
            cancellable = true
    )
    private void reduceAggroRange(LivingEntity target, CallbackInfo ci) {

        // If the mob is trying to target a Phantom player, block it
        if (target != null && target.hasStatusEffect(ModEffects.PHANTOM)) {

            MobEntity mob = (MobEntity)(Object)this;

            // Get the mob's follow range
            double followRange = mob.getAttributeValue(EntityAttributes.GENERIC_FOLLOW_RANGE);

            // Reduce effective aggro range (20% of normal)
            double phantomRange = followRange * 0.5;

            // Distance check
            double dist = mob.squaredDistanceTo(target);

            if (dist > phantomRange * phantomRange) {
                // Cancel targeting — mob will not aggro
                ci.cancel();
            }
        }
    }
}