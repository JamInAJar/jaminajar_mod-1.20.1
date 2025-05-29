package io.github.jaminajar.jaminajarmod.effects;

import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

import static io.github.jaminajar.jaminajarmod.JamInAJarMod.MOD_ID;

public class ModEffects {

    public static final StatusEffect SOULED = new SouledEffect(StatusEffectCategory.HARMFUL, 2551000);
    public static final StatusEffect GOOED = new GooedEffect(StatusEffectCategory.HARMFUL, 13816530);


    public static void registerEffects() {
        Registry.register(Registries.STATUS_EFFECT, new Identifier(MOD_ID, "souled"), SOULED);
        Registry.register(Registries.STATUS_EFFECT, new Identifier(MOD_ID, "gooed"), GOOED);
    }
}
