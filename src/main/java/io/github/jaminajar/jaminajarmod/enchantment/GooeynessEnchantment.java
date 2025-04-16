package io.github.jaminajar.jaminajarmod.enchantment;

import io.github.jaminajar.jaminajarmod.items.custom.MarshmallowStickItem;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.ItemStack;


public class GooeynessEnchantment extends Enchantment {
    public GooeynessEnchantment() {
        super(Rarity.RARE, EnchantmentTarget.WEAPON, new EquipmentSlot[]{EquipmentSlot.MAINHAND});
    }
    @Override
    public int getMinPower(int level){
        return 20;
    }
    @Override
    public boolean isAcceptableItem(ItemStack stack){
        return stack.getItem() instanceof MarshmallowStickItem ? true : super.isAcceptableItem(stack);
    }
    @Override
    public int getMaxLevel(){
        return 3;
    }
}
