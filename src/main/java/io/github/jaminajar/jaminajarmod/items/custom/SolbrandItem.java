package io.github.jaminajar.jaminajarmod.items.custom;

import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
import net.minecraft.item.ToolMaterial;

public class SolbrandItem extends SwordItem {
    public SolbrandItem(ToolMaterial toolMaterial, int attackDamage, float attackSpeed, Settings settings) {
        super(toolMaterial, attackDamage, attackSpeed, settings);
    }
    public boolean postHit(ItemStack stack, LivingEntity target, LivingEntity user){
        boolean superHit = super.postHit(stack, target, user);
        if(target instanceof LivingEntity){
            target.setFireTicks(6000);
        }
        return superHit;
    }
}
