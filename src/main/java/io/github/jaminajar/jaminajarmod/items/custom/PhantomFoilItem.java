package io.github.jaminajar.jaminajarmod.items.custom;

import io.github.jaminajar.jaminajarmod.effects.ModEffects;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
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
        if (!world.isClient && selected && entity instanceof LivingEntity living) {
            if (!living.hasStatusEffect(ModEffects.PHANTOM_COOLDOWN)) {
                applyPhantom(living);
            }
        }
    }

    private void applyPhantom(LivingEntity target) {
        StatusEffectInstance status = new StatusEffectInstance(
                ModEffects.PHANTOM,
                1,
                0,
                false,
                false, true
        );
        target.addStatusEffect(status);
    }

    @Override
    public boolean postHit(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        StatusEffectInstance status = new StatusEffectInstance(
                ModEffects.PHANTOM_COOLDOWN,
                200,
                0,
                false,
                false);
        if (attacker instanceof PlayerEntity player) {
            if (player.getItemCooldownManager().isCoolingDown(this)) {
                return false;
            }
            stack.damage(1, attacker, e -> e.sendEquipmentBreakStatus(EquipmentSlot.MAINHAND));
            if (stack.getDamage() >= 9) {
                player.getItemCooldownManager().set(this, 200);
                stack.setDamage(0);
            }
        }
        if (attacker.hasStatusEffect(ModEffects.PHANTOM)) {
            attacker.removeStatusEffect(ModEffects.PHANTOM);
            if (!attacker.getWorld().isClient()) {
                attacker.addStatusEffect(status);
            }
        }

        return true;
    }
}