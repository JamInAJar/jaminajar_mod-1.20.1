package io.github.jaminajar.jaminajarmod.items.custom;

import io.github.jaminajar.jaminajarmod.entity.ModEntities;
import io.github.jaminajar.jaminajarmod.entity.ShockwaveEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.PickaxeItem;
import net.minecraft.item.ToolMaterial;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.UseAction;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class GigatonHammerItem extends PickaxeItem {


    public GigatonHammerItem(ToolMaterial material, int attackDamage, float attackSpeed, Settings settings) {
        super(material, attackDamage, attackSpeed, settings);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        user.setCurrentHand(hand);
        return TypedActionResult.consume(user.getStackInHand(hand));
    }

    @Override
    public void onStoppedUsing(ItemStack stack, World world, LivingEntity user, int remainingUseTicks) {
        if(world.isClient||!(user instanceof PlayerEntity player)) return;
        BlockPos target = blockRayCast(player);
        if(target==null) return;
        spawnShockwave(world,target,user,Math.min(getMaxUseTime(stack)-remainingUseTicks,200));
        player.getItemCooldownManager().set(stack.getItem(), 100);
        stack.damage(25,user,e -> e.sendEquipmentBreakStatus(
                user.getActiveHand() == Hand.OFF_HAND ? EquipmentSlot.OFFHAND : EquipmentSlot.MAINHAND));
    }
    public BlockPos blockRayCast(PlayerEntity player){
        double reach = player.isCreative() ? 5.0 : 4.5;
        HitResult hit = player.raycast(reach, 0.0f, false);
        if (hit.getType() == HitResult.Type.BLOCK) {
            return ((BlockHitResult) hit).getBlockPos();
        }
        return null;
    }

    @Override
    public UseAction getUseAction(ItemStack stack) {
        return UseAction.SPEAR;
    }

    public static void spawnShockwave(World world, BlockPos center, Entity user, int charge) {
        if (world.isClient) return;
        ShockwaveEntity shockwave = new ShockwaveEntity(ModEntities.SHOCKWAVE,world);
        shockwave.setCharge(charge);
        shockwave.setOwner(user);
        shockwave.setPosition(center.getX()+0.5,center.getY()+0.5,center.getZ()+0.5);
        world.spawnEntity(shockwave);
    }


    @Override
    public int getMaxUseTime(ItemStack stack) {
        return 72000;
    }
}
