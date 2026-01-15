package io.github.jaminajar.jaminajarmod.effects;

import io.github.jaminajar.jaminajarmod.entity.damage.ModDamageTypes;
import io.github.jaminajar.jaminajarmod.util.Utils;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.AttributeContainer;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.entity.effect.StatusEffects;

import java.util.UUID;

public class SearingFireEffect extends StatusEffect {
    public static final UUID FLAMMABILITY_MODIFIER_UUID = UUID.fromString("e3b2d7d0-8a4f-4c2a-9c8d-1f2b3c4d5e6f");
    protected SearingFireEffect(StatusEffectCategory category, int color) {
        super(category, color);
    }

    @Override
    public void onApplied(LivingEntity entity, AttributeContainer attributes, int amplifier) {
        super.onApplied(entity, attributes, amplifier);
        if (entity.hasStatusEffect(StatusEffects.FIRE_RESISTANCE)){
            entity.removeStatusEffect(StatusEffects.FIRE_RESISTANCE);
        }
//        entity.getAttributeInstance(ModEntityAttributes.FLAMMABILITY)
//                .addPersistentModifier(new EntityAttributeModifier(
//                        FLAMMABILITY_MODIFIER_UUID,
//                        "flammability",
//                        1.0,
//                        EntityAttributeModifier.Operation.ADDITION
//                ));
    }
    @Override
    public boolean canApplyUpdateEffect(int duration, int amplifier) {
        return duration % Math.max(20-amplifier,5) == 0;
    }
    @Override
    public void applyUpdateEffect(LivingEntity entity, int amplifier) {
        super.applyUpdateEffect(entity, amplifier);
        if (entity.hasStatusEffect(StatusEffects.FIRE_RESISTANCE)){
            entity.removeStatusEffect(StatusEffects.FIRE_RESISTANCE);
        }
        entity.setFireTicks(200);
        Utils.CombatUtils.damageIgnoringIFrames(entity,entity.getDamageSources().create(ModDamageTypes.SEARING_FIRE),(amplifier+1)*0.0625f);
        if (entity.isTouchingWater()||entity.inPowderSnow){
            entity.removeStatusEffect(this);
        }
    }
}
