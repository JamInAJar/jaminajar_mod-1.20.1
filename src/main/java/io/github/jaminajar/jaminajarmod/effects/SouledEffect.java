package io.github.jaminajar.jaminajarmod.effects;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.AttributeContainer;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import java.util.Objects;

public class SouledEffect extends StatusEffect {
    public SouledEffect(StatusEffectCategory category, int color) {
        super(category, color);
    }

    @Override
    public void onApplied(LivingEntity entity, AttributeContainer attributes, int amplifier) {
        super.onApplied(entity, attributes, amplifier);
        Objects.requireNonNull(entity.getAttributeInstance(EntityAttributes.GENERIC_MAX_HEALTH)).addPersistentModifier(new EntityAttributeModifier("souledHealth",
                2* Objects.requireNonNull(entity.getStatusEffect(new SouledEffect(StatusEffectCategory.HARMFUL, 2551850))).getAmplifier(),
                EntityAttributeModifier.Operation.MULTIPLY_TOTAL));

    }
}
