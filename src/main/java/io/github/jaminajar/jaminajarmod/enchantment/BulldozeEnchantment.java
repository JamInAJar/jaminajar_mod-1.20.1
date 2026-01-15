package io.github.jaminajar.jaminajarmod.enchantment;

import io.github.jaminajar.jaminajarmod.items.custom.GigatonHammerItem;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.ItemStack;

public class BulldozeEnchantment extends Enchantment {
    protected BulldozeEnchantment() {
        super(Rarity.RARE, EnchantmentTarget.WEAPON, new EquipmentSlot[]{EquipmentSlot.MAINHAND});
    }
    public boolean isAcceptableItem(ItemStack stack){
        return stack.getItem() instanceof GigatonHammerItem || super.isAcceptableItem(stack);
    }
    @Override
    public int getMinPower(int level){
        return 20;
    }
    @Override
    public int getMaxLevel(){
        return 5;
    }
}
