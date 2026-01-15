package io.github.jaminajar.jaminajarmod.items;

import io.github.jaminajar.jaminajarmod.JamInAJarMod;
import io.github.jaminajar.jaminajarmod.items.custom.*;
import io.github.jaminajar.jaminajarmod.items.food.ModFoods;
import io.github.jaminajar.jaminajarmod.items.soul.*;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;

import net.minecraft.item.*;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

import java.util.List;

public class ModItems{
    /// Crafting Materials
	public static final Item COMFY_HANDLE = registerItem("comfy_handle", new Item(new Item.Settings()));
	public static final Item UNTREATED_BLADE = registerItem("untreated_blade", new Item(new Item.Settings()));
	public static final Item FORGED_BLADE = registerItem("forged_blade",new Item(new Item.Settings()));
	public static final Item SCISSOR_BLADE = registerItem("scissor_blade", new Item(new Item.Settings()));
	public static final Item RAVAGER_TOOTH = registerItem("ravager_tooth", new Item(new Item.Settings()));
	public static final Item RAVAGER_SCREW = registerItem("ravager_screw", new Item(new Item.Settings()));
	public static final Item BIOSPEAKER = registerItem("biospeaker", new Item(new Item.Settings().maxCount(1)));
	public static final Item INCANDESCENT_BLADE = registerItem("incandescent_blade",new Item(new Item.Settings()));
	public static final Item BLADE_CORE = registerItem("blade_core",new Item(new Item.Settings()));
    public static final Item SOULSTEEL_INGOT = registerItem("soulsteel_ingot", new Item(new Item.Settings()));

    /// Misc weapons
	public static final Item SCISSORS = registerItem("scissors", new ScissorbladeItem(ModToolMaterials.SCISSORS,12,-3.0f,new Item.Settings().fireproof()));
	public static final Item SOLBRAND = registerItem("solbrand", new SolbrandItem(ModToolMaterials.SOLBRAND, 6, -2.6f, new Item.Settings().fireproof()));
	public static final Item HELI_BLADE = registerItem("helicopter_blade", new HelicopterBladeItem(ModToolMaterials.HELI_BLADE,0,-2.7f, new Item.Settings().fireproof()));
	public static final Item CRIMSON_BLADE = registerItem("crimson_blade",new Item(new Item.Settings().fireproof()));
	public static final Item PHANTOM_FOIL = registerItem("phantom_foil",new PhantomFoilItem(ModToolMaterials.PHANTOM_FOIL,0,0f,new Item.Settings()));
    public static final Item GIGATON_HAMMER = registerItem("gigaton_hammer", new GigatonHammerItem(ModToolMaterials.GIGATON_HAMMER,11,-3.2f,new Item.Settings()));
    public static final Item ROCK = registerItem("rock", new RockItem(new Item.Settings().maxCount(16)));
    /// Marshmallow Stuff
	public static final Item MARSHMALLOW = registerItem("marshmallow",new Item(new Item.Settings().food(ModFoods.MARSHMALLOW)));
	public static final Item COOKED_MARSHMALLOW = registerItem("cooked_marshmallow",new Item(new Item.Settings().food(ModFoods.COOKED_MARSHMALLOW)));
	public static final Item MARSHMALLOW_STICK =registerItem("marshmallow_stick",new MarshmallowStickItem(ModToolMaterials.MARSHMALLOW_STICK,4,-2.2f,new Item.Settings().fireproof().food(ModFoods.MARSHMALLOW),0,0));
	public static final Item NETHERITE_MARSHMALLOW_STICK =registerItem("netherite_marshmallow_stick",new MarshmallowStickItem(ModToolMaterials.NETHERITE_MARSHMALLOW_STICK,8,-2.2f,new Item.Settings().food(ModFoods.MARSHMALLOW),0,1));
	public static final Item COOKED_MARSHMALLOW_STICK = registerItem("cooked_marshmallow_stick",new MarshmallowStickItem(ModToolMaterials.MARSHMALLOW_STICK,5,-2.8f,new Item.Settings().food(ModFoods.COOKED_MARSHMALLOW),1,0));
	public static final Item COOKED_NETHERITE_MARSHMALLOW_STICK =registerItem("cooked_netherite_marshmallow_stick",new MarshmallowStickItem(ModToolMaterials.NETHERITE_MARSHMALLOW_STICK,9,-2.8f,new Item.Settings().fireproof().food(ModFoods.COOKED_MARSHMALLOW),1,1));

    /// Souler
	public static final Item SOULER = registerItem("souler", new SoulerItem(new Item.Settings().maxCount(1)));
    public static final Item SOUL_GAUNTLET = registerItem("soul_gauntlet", new SoulGauntletItem(ModToolMaterials.SOUL_GAUNTLET,new Item.Settings().maxCount(1)));

	public static final Item EMPTY_SOUL_CANISTER = registerItem("empty_soul_canister", new SoulCanisterItem(new Item.Settings().maxCount(1),0,1));
	public static final Item FULL_SOUL_CANISTER = registerItem("full_soul_canister", new SoulCanisterItem(new Item.Settings().maxCount(1),1,1));
    public static final Item EMPTY_SOUL_GRENADE = registerItem("empty_soul_grenade", new SoulGrenadeItem(new Item.Settings().maxCount(1),0,1));
    public static final Item FULL_SOUL_GRENADE = registerItem("full_soul_grenade", new SoulGrenadeItem(new Item.Settings().maxCount(1),1,1));

    /// Soulsteel
    public static final Item SOULSTEEL_SWORDAXE = registerItem("soulsteel_swordaxe", new SwordAxeSwordItem(ModToolMaterials.SOULSTEEL,4,-2.4f,new Item.Settings().fireproof()));
    public static final Item SOULSTEEL_AXESWORD = registerItem("soulsteel_axesword", new SwordAxeAxeItem(ModToolMaterials.SOULSTEEL,7,-3.2f,new Item.Settings().fireproof()));
    public static final Item SOULSTEEL_PICKAXE = registerItem("soulsteel_pickaxe", new PickaxeItem(ModToolMaterials.SOULSTEEL,1,-2.8f,new Item.Settings()));
    public static final Item SOULSTEEL_SHOVEL = registerItem("soulsteel_shovel", new ShovelItem(ModToolMaterials.SOULSTEEL,1.5f,-3.0f,new Item.Settings()));
    public static final Item SOULSTEEL_HOE = registerItem("soulsteel_hoe", new HoeItem(ModToolMaterials.SOULSTEEL,0,-3.0f,new Item.Settings()));
    /// Music
    public static final Item CACOPHONY = registerItem("cacophony",new CacophonyItem(new Item.Settings().maxCount(1)));
    public static final Item FLUTE = registerItem("flute", new InstrumentItem(new Item.Settings().maxCount(1), SoundEvents.BLOCK_NOTE_BLOCK_FLUTE));

    /// Ammo items
    public static final Item BOOM_TUBE = registerItem("boom_tube", new BoomtubeItem(ModToolMaterials.BOOM_TUBE,5, -3.1f,new Item.Settings().maxCount(1),32));

    public static final Item BAMBOOZLER = registerItem("bamboozler",new BamboozlerItem(new Item.Settings().maxCount(1)));
	public static final Item DRIPSTONER_UPGRADE_TEMPLATE = registerItem("dripstoner_upgrade_template",
			new SmithingTemplateItem(
					Text.translatable("upgrade.dripstoner_upgrade_template.applies_to"),
					Text.translatable("upgrade.dripstoner_upgrade_template.ingredients"),
					Text.translatable("upgrade.dripstoner_upgrade_template.title"),
					Text.translatable("upgrade.dripstoner_upgrade_template.base_slot_description"),
					Text.translatable("upgrade.dripstoner_upgrade_template.additions_slot_description"),
					List.of(new Identifier("item/empty_slot_bamboozler"), new Identifier("item/empty_slot_helmet")),
					List.of(new Identifier("item/empty_slot_ingot"))));
	public static final Item DRIPSTONER = registerItem("dripstoner",new DripstonerItem(new Item.Settings().maxCount(1)));

	public static final Item NOTE_PROJECTILE = registerItem("note_projectile",new Item(new Item.Settings()));
	private static Item registerItem(String name, Item item){
		return Registry.register(Registries.ITEM, new Identifier(JamInAJarMod.MOD_ID, name), item);
	}
	public static void registerModItems(){
		JamInAJarMod.LOGGER.info("Registering Mod Items for "+ JamInAJarMod.MOD_ID);
		ItemGroupEvents.modifyEntriesEvent(ItemGroups.COMBAT).register(entries -> {
			entries.add(SCISSORS);
			entries.add(BOOM_TUBE);
			entries.add(PHANTOM_FOIL);
		});
		ItemGroupEvents.modifyEntriesEvent(ItemGroups.INGREDIENTS).register(entries -> {
			entries.add(COMFY_HANDLE);
			entries.add(UNTREATED_BLADE);
			entries.add(FORGED_BLADE);
			entries.add(SCISSOR_BLADE);
			entries.add(RAVAGER_TOOTH);
			entries.add(RAVAGER_SCREW);
            entries.add(BIOSPEAKER);
		});
	}


}
