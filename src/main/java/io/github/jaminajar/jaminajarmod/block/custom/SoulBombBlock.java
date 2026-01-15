//package io.github.jaminajar.jaminajarmod.block.custom;
//
//import io.github.jaminajar.jaminajarmod.block.entity.ModBlockEntities;
//import io.github.jaminajar.jaminajarmod.block.entity.SoulBombBlockEntity;
//import net.minecraft.block.*;
//import net.minecraft.block.entity.BlockEntity;
//import net.minecraft.block.entity.BlockEntityTicker;
//import net.minecraft.block.entity.BlockEntityType;
//import net.minecraft.entity.player.PlayerEntity;
//import net.minecraft.screen.NamedScreenHandlerFactory;
//import net.minecraft.util.ActionResult;
//import net.minecraft.util.Hand;
//import net.minecraft.util.ItemScatterer;
//import net.minecraft.util.hit.BlockHitResult;
//import net.minecraft.util.math.BlockPos;
//import net.minecraft.util.shape.VoxelShape;
//import net.minecraft.world.BlockView;
//import net.minecraft.world.World;
//import org.jetbrains.annotations.Nullable;
//
//public class SoulBombBlock extends BlockWithEntity {
//    private static final VoxelShape SHAPE = Block.createCuboidShape(3,0,3,13,16,13);
//
//    public SoulBombBlock(Settings settings) {
//        super(settings);
//    }
//
//    @Override
//    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
//        return SHAPE;
//    }
//
//    @Override
//    public BlockRenderType getRenderType(BlockState state) {
//        return BlockRenderType.MODEL;
//    }
//
//    @Override
//    public @Nullable BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
//        return new SoulBombBlockEntity(pos, state);
//    }
//
//    @Override
//    public void onStateReplaced(BlockState state, World world, BlockPos pos, BlockState newState, boolean moved) {
//        if (state.getBlock() != newState.getBlock()) {
//            BlockEntity blockEntity = world.getBlockEntity(pos);
//            if (blockEntity instanceof SoulBombBlockEntity) {
//                ItemScatterer.spawn(world, pos, (SoulBombBlockEntity) blockEntity);
//                world.updateComparators(pos, this);
//            }
//        }
//        super.onStateReplaced(state, world, pos, newState, moved);
//    }
//
//    @Override
//    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
//        if (!world.isClient) {
//            NamedScreenHandlerFactory screenHandlerFactory = (SoulBombBlockEntity) world.getBlockEntity(pos);
//            if (screenHandlerFactory != null) {
//                player.openHandledScreen(screenHandlerFactory);
//            }
//        }
//        return ActionResult.SUCCESS;
//    }
//
//    @Nullable
//    @Override
//    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(World world, BlockState state, BlockEntityType<T> type) {
//        return checkType(type, ModBlockEntities.SOUL_BOMB_BLOCK_ENTITY,
//                (world1, pos, state1, blockEntity) -> blockEntity.tick(world1, pos, state1));
//    }
//}