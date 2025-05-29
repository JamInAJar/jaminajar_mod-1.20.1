package io.github.jaminajar.jaminajarmod.effects;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.AttributeContainer;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;

import java.util.Objects;
import java.util.UUID;

import static java.lang.Math.log;

public class GooedEffect extends StatusEffect {
    public static final UUID GOOED_SPEED_MODIFIER_UUID = UUID.fromString("d5f94b04-fb26-42ea-98bc-c114eabc3c98");

    public GooedEffect(StatusEffectCategory category, int color) {
        super(category, color);
    }

    @Override
    public void onApplied(LivingEntity entity, AttributeContainer attributes, int amplifier) {
        super.onApplied(entity, attributes, amplifier);

        int gooedCoeff = (int) (4*(log(amplifier + 1))+1);
        EntityAttributeModifier speedModifier = new EntityAttributeModifier(
                GOOED_SPEED_MODIFIER_UUID,
                "gooedSpeed",
                -0.4f * gooedCoeff,
                EntityAttributeModifier.Operation.MULTIPLY_TOTAL
        );

        Objects.requireNonNull(entity.getAttributeInstance(EntityAttributes.GENERIC_MOVEMENT_SPEED))
                .addPersistentModifier(speedModifier);
    }

    @Override
    public void onRemoved(LivingEntity entity, AttributeContainer attributes, int amplifier) {
        super.onRemoved(entity, attributes, amplifier);

        var attributeInstance = entity.getAttributeInstance(EntityAttributes.GENERIC_MOVEMENT_SPEED);
        if (attributeInstance != null) {
            attributeInstance.removeModifier(GOOED_SPEED_MODIFIER_UUID);
        }
    }
}
