package io.github.jaminajar.jaminajarmod;

import io.github.jaminajar.jaminajarmod.block.custom.ModBlocks;
import io.github.jaminajar.jaminajarmod.block.entity.ModBlockEntities;
import io.github.jaminajar.jaminajarmod.effects.ModEffects;
import io.github.jaminajar.jaminajarmod.enchantment.*;
import io.github.jaminajar.jaminajarmod.items.ModItemGroups;
import io.github.jaminajar.jaminajarmod.items.ModItems;
import io.github.jaminajar.jaminajarmod.items.custom.PhantomFoilItem;
import io.github.jaminajar.jaminajarmod.screen.ModScreenHandlers;
import io.github.jaminajar.jaminajarmod.util.ModLootTableModifiers;
import io.github.jaminajar.jaminajarmod.util.ModPackets;
import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.entity.event.v1.ServerLivingEntityEvents;

import net.fabricmc.fabric.api.event.player.AttackEntityCallback;
import net.minecraft.entity.damage.DamageTypes;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class JamInAJarMod implements ModInitializer {
	public static final String MOD_ID = "jaminajarmod";


	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		AttackEntityCallback.EVENT.register((player, world, hand, entity, hitResult) -> {
			ItemStack stack = player.getStackInHand(hand);
			if (stack.getItem() instanceof PhantomFoilItem) {
				if (player.getItemCooldownManager().isCoolingDown(stack.getItem())) {
					return ActionResult.FAIL;
				}
			}

			return ActionResult.PASS;
		});
        LOGGER.info("Hello Fabric world!");
		ModItems.registerModItems();
        ModItemGroups.registerItemGroups();
		ModLootTableModifiers.modifyLootTables();
		ModEffects.registerEffects();
		ModEnchantments.registerModEnchantments();
        ModBlockEntities.registerBlockEntities();
        ModBlocks.registerModBlocks();
        ModScreenHandlers.registerScreenHandlers();
        ModPackets.registerC2SPackets();
	}
}