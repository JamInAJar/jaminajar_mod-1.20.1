package io.github.jaminajar.jaminajarmod.effects;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.player.PlayerEntity;


public class PhantomEffect extends StatusEffect {
    protected PhantomEffect(StatusEffectCategory category, int color) {
        super(category, color);
    }

    @Override
    public boolean canApplyUpdateEffect(int duration, int amplifier) {
        return true;
    }
    public void applyCooldown(LivingEntity entity){
        if (!entity.getWorld().isClient()) {
            entity.addStatusEffect(new StatusEffectInstance(
                    ModEffects.PHANTOM_COOLDOWN,
                    200,
                    0,
                    false,
                    false
            ));
        }
    }
    @Override
    public void applyUpdateEffect(LivingEntity entity, int amplifier) {
        if (entity instanceof PlayerEntity player) {
            if (player.handSwinging || player.isUsingItem()) {
                player.removeStatusEffect(ModEffects.PHANTOM);
                applyCooldown(player);
            }
        }
    }
    public static void onDamaged(LivingEntity entity) {
        entity.removeStatusEffect(ModEffects.PHANTOM);
    }

}