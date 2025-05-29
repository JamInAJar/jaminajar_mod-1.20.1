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
            target.setFireTicks(10000);
        }
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
