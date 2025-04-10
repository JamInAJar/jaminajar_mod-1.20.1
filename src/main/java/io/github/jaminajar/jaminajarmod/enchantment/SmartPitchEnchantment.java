package io.github.jaminajar.jaminajarmod.enchantment;

import io.github.jaminajar.jaminajarmod.items.ModItems;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.ItemStack;

public class SmartPitchEnchantment extends Enchantment {
    public SmartPitchEnchantment() {
        super(Rarity.RARE, EnchantmentTarget.WEAPON, new EquipmentSlot[]{EquipmentSlot.MAINHAND});
    }
    @Override
    public int getMinPower(int level){
        return 20;
    }
    @Override
    public boolean isAcceptableItem(ItemStack stack){
        return this.target.isAcceptableItem(ModItems.HELI_BLADE);
    }
    @Override
    public int getMaxLevel(){
        return 5;
    }
}
