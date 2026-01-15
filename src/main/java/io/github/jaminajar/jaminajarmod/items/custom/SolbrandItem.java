package io.github.jaminajar.jaminajarmod.items.custom;

import io.github.jaminajar.jaminajarmod.effects.ModEffects;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
import net.minecraft.item.ToolMaterial;
import net.minecraft.util.Hand;

import java.util.Objects;
import java.util.UUID;

public class SolbrandItem extends SwordItem {
    public SolbrandItem(ToolMaterial toolMaterial, int attackDamage, float attackSpeed, Settings settings) {
        super(toolMaterial, attackDamage, attackSpeed, settings);
    }
    public boolean postHit(ItemStack stack, LivingEntity target, LivingEntity user){
        boolean superHit = super.postHit(stack, target, user);
        int amplifier = 0;
        if(target instanceof LivingEntity){
            if(target.hasStatusEffect(ModEffects.SEARING_FIRE)){
                amplifier =target.getStatusEffect(ModEffects.SEARING_FIRE).getAmplifier()+1;
            }
            target.addStatusEffect(
                    new StatusEffectInstance(
                            ModEffects.SEARING_FIRE,
                            100*(EnchantmentHelper.getLevel(Enchantments.FIRE_ASPECT,stack)+1), amplifier, true, false, true
                            ),user);

        }
        stack.damage(4, user, e -> e.sendEquipmentBreakStatus(
                user.getActiveHand() == Hand.OFF_HAND ? EquipmentSlot.OFFHAND : EquipmentSlot.MAINHAND));
        return superHit;
    }
//    @Override
//    public ActionResult useOnBlock(ItemUsageContext context) {
//        PlayerEntity playerEntity = context.getPlayer();
//        World world = context.getWorld();
//        BlockPos blockPos = context.getBlockPos();
//        BlockState blockState = world.getBlockState(blockPos);
//        if (!CampfireBlock.canBeLit(blockState) && !CandleBlock.canBeLit(blockState) && !CandleCakeBlock.canBeLit(blockState)) {
//            BlockPos blockPos2 = blockPos.offset(context.getSide());
//            if (AbstractFireBlock.canPlaceAt(world, blockPos2, context.getHorizontalPlayerFacing())) {
//                world.playSound(playerEntity, blockPos2, SoundEvents.ITEM_FLINTANDSTEEL_USE, SoundCategory.BLOCKS, 1.0F, world.getRandom().nextFloat() * 0.4F + 0.8F);
//                BlockState blockState2 = AbstractFireBlock.getState(world, blockPos2);
//                world.setBlockState(blockPos2, blockState2, Block.NOTIFY_ALL | Block.REDRAW_ON_MAIN_THREAD);
//                world.emitGameEvent(playerEntity, GameEvent.BLOCK_PLACE, blockPos);
//                ItemStack itemStack = context.getStack();
//                if (playerEntity instanceof ServerPlayerEntity) {
//                    Criteria.PLACED_BLOCK.trigger((ServerPlayerEntity)playerEntity, blockPos2, itemStack);
//                    itemStack.damage(1, playerEntity, p -> p.sendToolBreakStatus(context.getHand()));
//                }
//
//                return ActionResult.success(world.isClient());
//            } else {
//                return ActionResult.FAIL;
//            }
//        } else {
//            world.playSound(playerEntity, blockPos, SoundEvents.ITEM_FLINTANDSTEEL_USE, SoundCategory.BLOCKS, 1.0F, world.getRandom().nextFloat() * 0.4F + 0.8F);
//            world.setBlockState(blockPos, blockState.with(Properties.LIT, Boolean.valueOf(true)), Block.NOTIFY_ALL | Block.REDRAW_ON_MAIN_THREAD);
//            world.emitGameEvent(playerEntity, GameEvent.BLOCK_CHANGE, blockPos);
//            if (playerEntity != null) {
//                context.getStack().damage(1, playerEntity, p -> p.sendToolBreakStatus(context.getHand()));
//            }
//
//            return ActionResult.success(world.isClient());
//        }
//    }
}
