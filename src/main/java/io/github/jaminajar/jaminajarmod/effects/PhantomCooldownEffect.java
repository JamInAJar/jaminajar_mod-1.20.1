package io.github.jaminajar.jaminajarmod.effects;


import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;

public class PhantomCooldownEffect extends StatusEffect {
    protected PhantomCooldownEffect(StatusEffectCategory category, int color) {
        super(category, color);
    }

    @Override
    public void applyUpdateEffect(LivingEntity entity, int amplifier) {
        super.applyUpdateEffect(entity, amplifier);
        entity.removeStatusEffect(ModEffects.PHANTOM);
    }
}