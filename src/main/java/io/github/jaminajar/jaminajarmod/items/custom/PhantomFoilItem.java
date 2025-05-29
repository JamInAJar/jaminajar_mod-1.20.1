package io.github.jaminajar.jaminajarmod.items.custom;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
import net.minecraft.item.ToolMaterial;
import net.minecraft.world.World;

public class PhantomFoilItem extends SwordItem {
    public PhantomFoilItem(ToolMaterial toolMaterial, int attackDamage, float attackSpeed, Settings settings) {
        super(toolMaterial, attackDamage, attackSpeed, settings);
    }

    @Override
    public void inventoryTick(ItemStack stack, World world, Entity entity, int slot, boolean selected) {
        if (!world.isClient && entity instanceof PlayerEntity player && selected) {
            ItemStack mainHand = player.getMainHandStack();

            if (mainHand == stack && stack.getDamage() >= stack.getMaxDamage() - 1) {
                player.getInventory().setStack(slot, ItemStack.EMPTY);
                player.getInventory().setStack(slot, stack);
            }
        }
    }
    public Multimap<EntityAttribute, EntityAttributeModifier> getAttributeModifiers(EquipmentSlot slot, ItemStack stack) {
        Multimap<EntityAttribute, EntityAttributeModifier> modifiers = HashMultimap.create();

        if (slot == EquipmentSlot.MAINHAND) {
            boolean isOnLastDurability = stack.getDamage() >= stack.getMaxDamage() - 1;

            double damage = isOnLastDurability ? 0.0 : 5.0;

            modifiers.put(EntityAttributes.GENERIC_ATTACK_DAMAGE,
                    new EntityAttributeModifier(
                            ATTACK_DAMAGE_MODIFIER_ID,
                            "Weapon modifier",
                            damage,
                            EntityAttributeModifier.Operation.ADDITION
                    )
            );
        }

        return modifiers;
    }

    @Override
    public boolean postHit(ItemStack stack, LivingEntity target, LivingEntity attacker) {

        if (attacker instanceof PlayerEntity player){
            if (player.getItemCooldownManager().isCoolingDown(this)) {
                return false; // block swing during cooldown
            }
            stack.damage(1, attacker, e -> e.sendEquipmentBreakStatus(EquipmentSlot.MAINHAND));
            if(stack.getDamage() >= 9) {
                player.getItemCooldownManager().set(this,100);
                stack.setDamage(0);
            }
        }

        return true;
    }


}
