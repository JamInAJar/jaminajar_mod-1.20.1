package io.github.jaminajar.jaminajarmod.items;

import net.fabricmc.yarn.constants.MiningLevels;

import net.minecraft.item.Items;
import net.minecraft.item.ToolMaterial;
import net.minecraft.recipe.Ingredient;

import java.util.function.Supplier;

public enum ModToolMaterials implements ToolMaterial {

	SCISSORS(MiningLevels.NETHERITE, 1500, 5.0F, 0F, 10, () -> Ingredient.ofItems(Items.NETHERITE_INGOT)),
	SOLBRAND(MiningLevels.DIAMOND,991, 5.0F, 0F, 15, () -> Ingredient.ofItems(Items.BLAZE_ROD)),
	BOOM_TUBE(MiningLevels.WOOD,640,0.1f,5f,4,()->Ingredient.ofItems(Items.PAPER)),
	MARSHMALLOW_STICK(MiningLevels.STONE,1053,0.1f,0f,10,()->Ingredient.ofItems(ModItems.MARSHMALLOW)),
	NETHERITE_MARSHMALLOW_STICK(MiningLevels.NETHERITE,2537,0.1f,0f,25,()->Ingredient.ofItems(ModItems.COOKED_MARSHMALLOW)),
	HELI_BLADE(MiningLevels.DIAMOND,6321,0.1f,9f,10,()->Ingredient.ofItems(Items.REDSTONE)),
	PHANTOM_FOIL(MiningLevels.WOOD,10,0.1f,5,20,()->Ingredient.ofItems(Items.PHANTOM_MEMBRANE,Items.NETHERITE_SCRAP)),
    SOULSTEEL(MiningLevels.DIAMOND,3067,7.0f,5,60,()->Ingredient.ofItems(ModItems.SOULSTEEL_INGOT)),
    SOUL_GAUNTLET(MiningLevels.DIAMOND,1567,0.1f,4,20,()->Ingredient.ofItems(ModItems.SOULSTEEL_INGOT)),
    GIGATON_HAMMER(MiningLevels.IRON, 1543, 4.5f,0,10,()->Ingredient.ofItems(Items.RAW_IRON,Items.RAW_IRON_BLOCK));

	private final int miningLevel;
	private final int itemDurability;
	private final float miningSpeed;
	private final float attackDamage;
	private final int enchantability;
	private final Supplier<Ingredient> repairIngredient;

	ModToolMaterials(int miningLevel,int itemDurability, float miningSpeed, float attackDamage, int enchantability, Supplier<Ingredient> repairIngredient){
		this.miningLevel = miningLevel;
		this.itemDurability = itemDurability;
		this.miningSpeed = miningSpeed;
		this.attackDamage = attackDamage;
		this.enchantability = enchantability;
		this.repairIngredient = repairIngredient;
	}

	@Override
	public int getDurability() {
		return this.itemDurability;
	}

	@Override
	public float getMiningSpeedMultiplier() {
		return this.miningSpeed;
	}

	@Override
	public float getAttackDamage() {
		return this.attackDamage;
	}

	@Override
	public int getMiningLevel() {
		return this.miningLevel;
	}



	public int getEnchantability() {
		return this.enchantability;
	}

	@Override
	public Ingredient getRepairIngredient() {
		return this.repairIngredient.get();
	}

}

