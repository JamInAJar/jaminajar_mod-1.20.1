package io.github.jaminajar.jaminajarmod.effects;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.AttributeContainer;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import java.util.Objects;
import java.util.UUID;

public class SouledEffect extends StatusEffect {
    public static final UUID SOULED_UUID = UUID.fromString("d5f94b04-fb26-42ea-98bc-c114eabc3c99");
    public SouledEffect(StatusEffectCategory category, int color) {
        super(category, color);
    }

    @Override
    public void onApplied(LivingEntity entity, AttributeContainer attributes, int amplifier) {
        super.onApplied(entity, attributes, amplifier);

        int level = Objects.requireNonNull(entity.getStatusEffect(ModEffects.SOULED)).getAmplifier();

        Objects.requireNonNull(entity.getAttributeInstance(EntityAttributes.GENERIC_MAX_HEALTH))
                .addPersistentModifier(new EntityAttributeModifier(
                        SOULED_UUID,
                        "souledHealth",
                        Math.floor(Math.max(-Math.log10(level+2)*15,-19)),
                        EntityAttributeModifier.Operation.ADDITION
                ));
    }
    @Override
    public void onRemoved(LivingEntity entity, AttributeContainer attributes, int amplifier) {
        super.onRemoved(entity, attributes, amplifier);

        var attributeInstance = entity.getAttributeInstance(EntityAttributes.GENERIC_MAX_HEALTH);
        if (attributeInstance != null) {
            attributeInstance.removeModifier(SOULED_UUID);
        }
    }
}
