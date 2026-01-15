package io.github.jaminajar.jaminajarmod.items.soul;

import io.github.jaminajar.jaminajarmod.effects.ModEffects;
import io.github.jaminajar.jaminajarmod.entity.damage.ModDamageTypes;
import io.github.jaminajar.jaminajarmod.items.ModItems;
import io.github.jaminajar.jaminajarmod.util.Utils;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

public class SoulCanisterItem extends Item {
    public int fullness;
    public int capacity;
        public SoulCanisterItem(Settings settings, int fullness, int capacity) {
            super(settings);
            this.fullness=fullness;
            this.capacity=capacity;
        }
        @Override
        public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
            StatusEffectInstance souled = user.getStatusEffect(ModEffects.SOULED);
            int souledStrength = -1;
            if(souled!=null) {
                souledStrength = souled.getAmplifier();
            }
            ItemStack stack;
            if(user instanceof PlayerEntity && fullness==0) {
                DamageSource damageSource = new DamageSource(
                        user.getWorld().getRegistryManager()
                                .get(RegistryKeys.DAMAGE_TYPE)
                                .entryOf(ModDamageTypes.SOULER_SELFSOUL),user
                );
                StatusEffectInstance statusEffectInstance1 = new StatusEffectInstance(ModEffects.SOULED, 400, souledStrength+1);
                StatusEffectInstance statusEffectInstance2 = new StatusEffectInstance(StatusEffects.NAUSEA, 400, 7 + souledStrength);
                StatusEffectInstance statusEffectInstance3 = new StatusEffectInstance(StatusEffects.BLINDNESS, 200, 7 + souledStrength);
                StatusEffectInstance statusEffectInstance4 = new StatusEffectInstance(StatusEffects.DARKNESS, 300, 7 + souledStrength);
                Utils.CombatUtils.damageIgnoringIFrames(user,damageSource,5);
                user.addStatusEffect(statusEffectInstance1);
                user.addStatusEffect(statusEffectInstance2);
                user.addStatusEffect(statusEffectInstance3);
                user.addStatusEffect(statusEffectInstance4);
                stack = new ItemStack(ModItems.FULL_SOUL_CANISTER);
                user.getInventory().setStack(user.getInventory().selectedSlot, stack);
            }
            return super.use(world, user, hand);
        }

}