package io.github.jaminajar.jaminajarmod.effects;

import io.github.jaminajar.jaminajarmod.JamInAJarMod;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModEffects {

    public static final StatusEffect SOULED = new SouledEffect(StatusEffectCategory.HARMFUL, 2551850);
    public static final StatusEffect GOOED = new GooedEffect(StatusEffectCategory.HARMFUL, 2551000);
}
