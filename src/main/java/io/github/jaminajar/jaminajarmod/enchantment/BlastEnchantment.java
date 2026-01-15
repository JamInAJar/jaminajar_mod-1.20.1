package io.github.jaminajar.jaminajarmod.enchantment;

import io.github.jaminajar.jaminajarmod.items.custom.BoomtubeItem;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.ItemStack;


public class BlastEnchantment extends Enchantment {
    public BlastEnchantment() {
        super(Rarity.RARE, EnchantmentTarget.WEAPON, new EquipmentSlot[]{EquipmentSlot.MAINHAND});
    }
    @Override
    public int getMinPower(int level){
        return 20;
    }
    @Override
    public boolean isAcceptableItem(ItemStack stack){

        return stack.getItem() instanceof BoomtubeItem || super.isAcceptableItem(stack);
    }
    @Override
    public int getMaxLevel(){
        return 3;
    }
    @Override
    public boolean canAccept(Enchantment other) {
        return super.canAccept(other) && other != ModEnchantments.PENTABOOM;
    }
}
