package io.github.jaminajar.jaminajarmod;

import io.github.jaminajar.jaminajarmod.effects.ModEffects;
import io.github.jaminajar.jaminajarmod.enchantment.BlastEnchantment;
import io.github.jaminajar.jaminajarmod.enchantment.GooeynessEnchantment;
import io.github.jaminajar.jaminajarmod.enchantment.SmartPitchEnchantment;
import io.github.jaminajar.jaminajarmod.items.ModItems;
import io.github.jaminajar.jaminajarmod.util.ModLootTableModifiers;
import net.fabricmc.api.ModInitializer;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static io.github.jaminajar.jaminajarmod.effects.ModEffects.GOOED;
import static io.github.jaminajar.jaminajarmod.effects.ModEffects.SOULED;

public class JamInAJarMod implements ModInitializer {
	public static final String MOD_ID = "jaminajarmod";
	public static Enchantment BLAST = new BlastEnchantment();
	public static Enchantment GOOEYNESS = new GooeynessEnchantment();
	public static Enchantment SMART_PITCH = new SmartPitchEnchantment();

	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {


		LOGGER.info("Hello Fabric world!");
		ModItems.registerModItems();
		ModLootTableModifiers.modifyLootTables();
		ModEffects.registerEffects();
		Registry.register(Registries.ENCHANTMENT,new Identifier(MOD_ID,"blast"),BLAST);
		Registry.register(Registries.ENCHANTMENT,new Identifier(MOD_ID,"gooeyness"),GOOEYNESS);
		Registry.register(Registries.ENCHANTMENT,new Identifier(MOD_ID,"smart_pitch"),SMART_PITCH);
	}
}