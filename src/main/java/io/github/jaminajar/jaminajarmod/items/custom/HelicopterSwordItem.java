package io.github.jaminajar.jaminajarmod.items.custom;

import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSources;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
import net.minecraft.item.ToolMaterial;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

public class HelicopterSwordItem extends SwordItem {
    double sneakingCoeff;
    public HelicopterSwordItem(ToolMaterial toolMaterial, int attackDamage, float attackSpeed, Settings settings) {
        super(toolMaterial, attackDamage, attackSpeed, settings);
    }
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        ItemStack itemStack = user.getStackInHand(hand);
        System.out.println("Heliblade Used");
        user.setCurrentHand(hand);
        if (!isUsable(this.getDefaultStack())) {
            /// play sound
        }
        return TypedActionResult.success(itemStack, world.isClient());
    }
    @Override
    public void usageTick(World world, LivingEntity user, ItemStack stack, int remainingUseTicks) {
        super.usageTick(world, user, stack, remainingUseTicks);
        this.sneakingCoeff = 1;
        if(isUsable(stack)) {
            if (!user.isOnGround()) {
                float f = user.getYaw();
                float g = user.getPitch();
                float h = -MathHelper.sin(f * (float) (Math.PI / 180.0)) * MathHelper.cos(g * (float) (Math.PI / 180.0));
                float k = -MathHelper.sin(g * (float) (Math.PI / 180.0));
                float l = MathHelper.cos(f * (float) (Math.PI / 180.0)) * MathHelper.cos(g * (float) (Math.PI / 180.0));
                float m = MathHelper.sqrt(h * h + k * k + l * l);
                float n = 3.0F * ((1.0F + (float) 0.5) / 4.0F);
                h *= n / m;
                k *= n / m;
                l *= n / m;
                if (user.isSneaking()) {
                    this.sneakingCoeff = 1.3;
                }
                user.addVelocity(h / (9 * sneakingCoeff), k / (6 / sneakingCoeff), l / (9 * sneakingCoeff));
                user.handleFallDamage(0, 3f, user.getDamageSources().fall());

                stack.damage(5,user,e -> e.sendEquipmentBreakStatus(
                        user.getActiveHand() == Hand.OFF_HAND ? EquipmentSlot.OFFHAND : EquipmentSlot.MAINHAND));
            } else if (user.isOnGround()) {
                /// shield shred etc
            }
        }
    }
    @Override
    public int getMaxUseTime(ItemStack stack) {
        return 72000;
    }
    public static boolean isUsable(ItemStack stack) {
        return stack.getDamage() < stack.getMaxDamage() - 1;
    }
}
