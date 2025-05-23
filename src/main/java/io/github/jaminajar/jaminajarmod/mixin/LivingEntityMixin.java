package io.github.jaminajar.jaminajarmod.mixin;

import io.github.jaminajar.jaminajarmod.enchantment.SmartPitchEnchantment;
import io.github.jaminajar.jaminajarmod.items.ModItems;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin extends Entity {
    public LivingEntityMixin(EntityType<?> type, World world) {
        super(type, world);
    }
    @Inject(method = "handleFallDamage", at = @At("HEAD"), cancellable = true)
    private void amplifyFallDamage(float fallDistance, float damageMultiplier, DamageSource damageSource, CallbackInfoReturnable<Boolean> cir) {
        if ((Object) this instanceof PlayerEntity player) {
            ItemStack mainHandItem = player.getMainHandStack();

            float fallDamageHeliMultiplier = (float) (1.2 - EnchantmentHelper.getLevel(new SmartPitchEnchantment(),mainHandItem));

            if (mainHandItem.getItem() == ModItems.HELI_BLADE && player.isUsingItem() && !player.isOnGround()) {
                float amplifiedFallDistance = fallDistance * fallDamageHeliMultiplier;
                int damage = MathHelper.ceil((amplifiedFallDistance - 3.0F) * damageMultiplier);
                if (damage > 0) {
                    player.damage(player.getDamageSources().fall(), damage);
                }
                cir.setReturnValue(true);
            }
        }
    }
}
