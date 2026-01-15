package io.github.jaminajar.jaminajarmod.util;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;

public final class Utils {

    public static final class CombatUtils {
        private CombatUtils() {
        }

        public static void damageIgnoringIFrames(LivingEntity target, DamageSource source, float amount) {
            target.timeUntilRegen = 0;
            target.hurtTime = 0;
            target.damage(source, amount);
        }

        public static boolean isCriticalHit(PlayerEntity player) {
            if (player == null) return false;

            boolean falling = player.fallDistance > 0.0F;
            boolean notOnGround = !player.isOnGround();
            boolean notClimbing = !player.isClimbing();
            boolean notInWater = !player.isTouchingWater();
            boolean notBlind = !player.hasStatusEffect(StatusEffects.BLINDNESS);
            boolean sprinting = player.isSprinting();
            return falling && notOnGround && notClimbing && notInWater && !notBlind && !sprinting;
        }
    }

    public static final class ItemUtils {
        private ItemUtils() {
        }

        public static void copyStackData(ItemStack from, ItemStack to) {
            if (from.hasNbt()) {
                assert from.getNbt() != null;
                NbtCompound nbt = from.getNbt().copy();

                nbt.remove("AttributeModifiers");

                to.setNbt(nbt);
            }
        }

    }
    public static final class OtherUtils {
        private OtherUtils() {}
        public static int convertBooleanToBinary(boolean value) {
            return value ? 1 : 0;
        }
    }
}