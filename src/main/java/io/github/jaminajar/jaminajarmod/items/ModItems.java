package io.github.jaminajar.jaminajarmod.items;

import io.github.jaminajar.jaminajarmod.JamInAJarMod;
import io.github.jaminajar.jaminajarmod.items.custom.*;
import io.github.jaminajar.jaminajarmod.items.food.ModFoods;
import io.github.jaminajar.jaminajarmod.items.soul.SoulerItem;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;

import net.minecraft.item.*;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import io.github.jaminajar.jaminajarmod.items.custom.CacophonyItem;

public class ModItems{

	public static final Item MACHETE = registerItem("machete", new SwordItem(ModToolMaterials.MACHETE, 7, -3.1f, new Item.Settings()));
	public static final Item COMFY_HANDLE = registerItem("comfy_handle", new Item(new Item.Settings()));
	public static final Item UNTREATED_BLADE = registerItem("untreated_blade", new Item(new Item.Settings()));
	public static final Item FORGED_BLADE = registerItem("forged_blade",new Item(new Item.Settings()));
	public static final Item SCISSOR_BLADE = registerItem("scissor_blade", new Item(new Item.Settings()));
	public static final Item SCISSORS = registerItem("scissors", new ScissorbladeItem(ModToolMaterials.SCISSORS,12,-3.0f,new Item.Settings().fireproof()));
	public static final Item RAVAGER_TOOTH = registerItem("ravager_tooth", new Item(new Item.Settings()));
	public static final Item RAVAGER_SCREW = registerItem("ravager_screw", new Item(new Item.Settings()));
	public static final Item SOLBRAND = registerItem("solbrand", new SolbrandItem(ModToolMaterials.SOLBRAND, 10, -2.6f, new Item.Settings().fireproof()));
	public static final Item BOOM_TUBE = registerItem("boom_tube", new BoomtubeItem(ModToolMaterials.BOOM_TUBE,5, -2.6f,new Item.Settings(),32));
	public static final Item CACOPHONY = registerItem("cacophony",new CacophonyItem(new Item.Settings()));
	public static final Item BIOSPEAKER = registerItem("biospeaker", new Item(new Item.Settings()));
	public static final Item NOTEPROJECTILE = registerItem("noteprojectile",new Item(new Item.Settings()));
	public static final Item MARSHMALLOW = registerItem("marshmallow",new Item(new Item.Settings().food(ModFoods.MARSHMALLOW)));
	public static final Item COOKED_MARSHMALLOW = registerItem("cooked_marshmallow",new Item(new Item.Settings().food(ModFoods.COOKED_MARSHMALLOW)));
	public static final Item MARSHMALLOW_STICK =registerItem("marshmallow_stick",new MarshmallowStickItem(ModToolMaterials.MARSHMALLOW_STICK,4,-2.2f,new Item.Settings().fireproof().food(ModFoods.MARSHMALLOW),0,0));
	public static final Item NETHERITE_MARSHMALLOW_STICK =registerItem("netherite_marshmallow_stick",new MarshmallowStickItem(ModToolMaterials.NETHERITE_MARSHMALLOW_STICK,8,-2.2f,new Item.Settings().food(ModFoods.MARSHMALLOW),0,1));
	public static final Item COOKED_MARSHMALLOW_STICK = registerItem("cooked_marshmallow_stick",new MarshmallowStickItem(ModToolMaterials.MARSHMALLOW_STICK,5,-2.8f,new Item.Settings().food(ModFoods.COOKED_MARSHMALLOW),1,0));
	public static final Item COOKED_NETHERITE_MARSHMALLOW_STICK =registerItem("cooked_netherite_marshmallow_stick",new MarshmallowStickItem(ModToolMaterials.NETHERITE_MARSHMALLOW_STICK,9,-2.8f,new Item.Settings().fireproof().food(ModFoods.COOKED_MARSHMALLOW),1,1));
	public static final Item SOULER = registerItem("souler", new SoulerItem(new Item.Settings()));
	public static final Item INCANDESCENT_BLADE = registerItem("incandescent_blade",new Item(new Item.Settings()));
	public static final Item BLADE_CORE = registerItem("blade_core",new Item(new Item.Settings()));
	public static final Item HELICOPTER_SWORD = registerItem("helicopter_sword", new HelicopterSwordItem(ModToolMaterials.HELICOPTER_SWORD,0,-2.4f, new Item.Settings().fireproof()));
	public static final Item CRIMSON_BLADE = registerItem("crimson_blade",new Item(new Item.Settings().fireproof()));
	private static Item registerItem(String name, Item item){
		return Registry.register(Registries.ITEM, new Identifier(JamInAJarMod.MOD_ID, name), item);
	}
	public static void registerModItems(){
		JamInAJarMod.LOGGER.info("Registering Mod Items for "+ JamInAJarMod.MOD_ID);

		ItemGroupEvents.modifyEntriesEvent(ItemGroups.COMBAT).register(entries -> {
			entries.add(MACHETE);
			entries.add(SCISSORS);
			entries.add(BOOM_TUBE);
		});
		ItemGroupEvents.modifyEntriesEvent(ItemGroups.INGREDIENTS).register(entries -> {
			entries.add(COMFY_HANDLE);
			entries.add(UNTREATED_BLADE);
			entries.add(FORGED_BLADE);
			entries.add(SCISSOR_BLADE);
			entries.add(RAVAGER_TOOTH);
			entries.add(RAVAGER_SCREW);
		});
	}


}
