package io.github.jaminajar.jaminajarmod.items;

import io.github.jaminajar.jaminajarmod.block.custom.ModBlocks;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;


public class ModItemGroups {
    public static final ItemGroup SOUL_TAB = Registry.register(Registries.ITEM_GROUP,
            new Identifier("jaminajarmod", "soul_tab"),
            FabricItemGroup.builder()
            .icon(() -> new ItemStack(ModItems.EMPTY_SOUL_CANISTER))
                    .displayName(Text.translatable("itemgroup.souler_group"))
            .entries((displayContext, entries) -> {
              entries.add(ModItems.SOULER);
              entries.add(ModItems.EMPTY_SOUL_CANISTER);
              entries.add(ModItems.FULL_SOUL_CANISTER);
              entries.add(ModItems.EMPTY_SOUL_GRENADE);
              entries.add(ModItems.FULL_SOUL_GRENADE);
              entries.add(ModBlocks.SOULSTEEL_BLOCK);
              entries.add(ModItems.SOULSTEEL_INGOT);
              entries.add(ModItems.SOULSTEEL_SWORDAXE);
              entries.add(ModItems.SOULSTEEL_AXESWORD);
                entries.add(ModItems.SOULSTEEL_PICKAXE);
                entries.add(ModItems.SOULSTEEL_SHOVEL);
                entries.add(ModItems.SOULSTEEL_HOE);
            })
            .build());
    public static void registerItemGroups() {
    }
}
