package io.github.jaminajar.jaminajarmod.effects;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.AttributeContainer;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;

import java.util.Objects;

public class GooedEffect extends StatusEffect {
    public GooedEffect(StatusEffectCategory category, int color) {
        super(category, color);
    }

    @Override
    public void onApplied(LivingEntity entity, AttributeContainer attributes, int amplifier) {
        super.onApplied(entity, attributes, amplifier);
        int gooedCoeff=0;
        if(entity!=null){
            entity.getStatusEffect(new GooedEffect(StatusEffectCategory.HARMFUL, 2551000)).getAmplifier();
        }
        assert entity != null;
        Objects.requireNonNull(entity.getAttributeInstance(EntityAttributes.GENERIC_MOVEMENT_SPEED))
                .addPersistentModifier(new EntityAttributeModifier("gooedSpeed",
                -0.2f*gooedCoeff,
                EntityAttributeModifier.Operation.MULTIPLY_TOTAL));
    }
}
