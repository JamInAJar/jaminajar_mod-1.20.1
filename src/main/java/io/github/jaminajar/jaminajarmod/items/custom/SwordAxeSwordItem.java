package io.github.jaminajar.jaminajarmod.items.custom;

import io.github.jaminajar.jaminajarmod.items.ModItems;

import io.github.jaminajar.jaminajarmod.util.Utils;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
import net.minecraft.item.ToolMaterial;

public class SwordAxeSwordItem extends SwordItem {
    public SwordAxeSwordItem(ToolMaterial toolMaterial, int attackDamage, float attackSpeed, Settings settings) {
        super(toolMaterial, attackDamage, attackSpeed, settings);
    }
    public void switchGrip(PlayerEntity player, ItemStack stack) {
        ItemStack newStack = new ItemStack(ModItems.SOULSTEEL_AXESWORD);
        Utils.ItemUtils.copyStackData(stack,newStack);
        newStack.setDamage(stack.getDamage());
        player.getInventory().setStack(player.getInventory().selectedSlot, newStack);
    }
}
