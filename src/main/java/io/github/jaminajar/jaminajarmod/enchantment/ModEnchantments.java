package io.github.jaminajar.jaminajarmod.enchantment;

import io.github.jaminajar.jaminajarmod.JamInAJarMod;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModEnchantments {
    public static final Enchantment GOOEYNESS = register("gooeyness", new GooeynessEnchantment(Enchantment.Rarity.RARE));
    public static final Enchantment BLAST = register("blast", new BlastEnchantment());
    public static final Enchantment PENTABOOM = register("pentaboom", new PentaboomEnchantment());
    public static final Enchantment SMARTPITCH = register("smartpitch", new SmartPitchEnchantment());
    private static Enchantment register(String name, Enchantment enchantment) {
        return Registry.register(
                Registries.ENCHANTMENT,
                new Identifier(JamInAJarMod.MOD_ID, name),
                enchantment
        );
    }
    public static void registerModEnchantments() {
        System.out.println("Registering Enchantments for " + JamInAJarMod.MOD_ID);
    }
}
