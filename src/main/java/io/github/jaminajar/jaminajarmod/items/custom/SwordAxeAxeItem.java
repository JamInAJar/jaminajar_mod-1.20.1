package io.github.jaminajar.jaminajarmod.items.custom;

import io.github.jaminajar.jaminajarmod.items.ModItems;
import io.github.jaminajar.jaminajarmod.util.Utils;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.AxeItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ToolMaterial;

public class SwordAxeAxeItem extends AxeItem {
    public SwordAxeAxeItem(ToolMaterial material, float attackDamage, float attackSpeed, Settings settings) {
        super(material, attackDamage, attackSpeed, settings);
    }
    public void switchGrip(PlayerEntity player, ItemStack stack) {
        ItemStack newStack = new ItemStack(ModItems.SOULSTEEL_SWORDAXE);
        Utils.ItemUtils.copyStackData(stack,newStack);
        newStack.setDamage(stack.getDamage());
        player.getInventory().setStack(player.getInventory().selectedSlot, newStack);
    }
}
