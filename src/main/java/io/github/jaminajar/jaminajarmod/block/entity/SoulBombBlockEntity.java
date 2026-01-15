//package io.github.jaminajar.jaminajarmod.block.entity;
//
//import io.github.jaminajar.jaminajarmod.items.ModItems;
//import io.github.jaminajar.jaminajarmod.screen.SoulBombScreenHandler;
//import net.fabricmc.fabric.api.screenhandler.v1.ExtendedScreenHandlerFactory;
//import net.minecraft.block.BlockState;
//import net.minecraft.block.entity.BlockEntity;
//import net.minecraft.block.entity.BlockEntityType;
//import net.minecraft.entity.player.PlayerEntity;
//import net.minecraft.entity.player.PlayerInventory;
//import net.minecraft.inventory.Inventories;
//import net.minecraft.item.Item;
//import net.minecraft.item.ItemStack;
//import net.minecraft.nbt.NbtCompound;
//import net.minecraft.network.PacketByteBuf;
//import net.minecraft.screen.PropertyDelegate;
//import net.minecraft.screen.ScreenHandler;
//import net.minecraft.server.network.ServerPlayerEntity;
//import net.minecraft.text.Text;
//import net.minecraft.util.collection.DefaultedList;
//import net.minecraft.util.math.BlockPos;
//import net.minecraft.world.World;
//import org.jetbrains.annotations.Nullable;
//import org.spongepowered.asm.mixin.Implements;
//
//public class SoulBombBlockEntity extends BlockEntity implements ExtendedScreenHandlerFactory, ImplementedInventory {
//    private final DefaultedList<ItemStack> inventory = DefaultedList.ofSize(2, ItemStack.EMPTY);
//
//    private static final int INPUT_SLOT = 0;
//    private static final int OUTPUT_SLOT = 1;
//
//    protected final PropertyDelegate propertyDelegate;
//    private int progress=0;
//    private int maxProgress=72;
//    private int soulEnergy;
//
//    public SoulBombBlockEntity(BlockPos pos, BlockState state) {
//        super(ModBlockEntities.SOUL_BOMB_BLOCK_ENTITY, pos, state);
//        this.propertyDelegate = new PropertyDelegate() {
//            @Override
//            public int get(int index) {
//                return switch (index){
//                    case 0->SoulBombBlockEntity.this.progress;
//                    case 1->SoulBombBlockEntity.this.maxProgress;
//                    default ->0;
//                };
//            }
//
//            @Override
//            public void set(int index, int value) {
//                switch (index){
//                    case 0->SoulBombBlockEntity.this.progress = value;
//                    case 1->SoulBombBlockEntity.this.maxProgress = value;
//                };
//            }
//
//            @Override
//            public int size() {
//                return 2;
//            }
//        };
//    }
//
//    @Override
//    public DefaultedList<ItemStack> getItems() {
//        return inventory;
//    }
//
//    @Override
//    public void writeScreenOpeningData(ServerPlayerEntity serverPlayerEntity, PacketByteBuf packetByteBuf) {
//        packetByteBuf.writeBlockPos(this.pos);
//    }
//
//    @Override
//    public Text getDisplayName() {
//        return Text.translatable("gui.jaminajarmod.soul_bomb");
//    }
//
//    @Override
//    protected void writeNbt(NbtCompound nbt) {
//        super.writeNbt(nbt);
//        Inventories.writeNbt(nbt, inventory);
//        nbt.putInt("soul_bomb.progress",progress);
//    }
//
//    @Override
//    public void readNbt(NbtCompound nbt) {
//        super.readNbt(nbt);
//        Inventories.readNbt(nbt, inventory);
//        progress = nbt.getInt("soul_bomb.progress");
//    }
//
//    @Override
//    public @Nullable ScreenHandler createMenu(int syncId, PlayerInventory playerInventory, PlayerEntity player) {
//        return new SoulBombScreenHandler(syncId,playerInventory,this,this.propertyDelegate);
//    }
//
//    public void tick(World world, BlockPos pos, BlockState state) {
//        if(world.isClient()){
//            return;
//        }
//        if(!(this.soulEnergy >=10)){
//            if (isOutputSlotEmptyorReceivable()) {
//                if (this.hasRecipe()) {
//                    this.increaseCraftProgress();
//                    markDirty(world, pos, state);
//                    if (hasCraftingFinished()) {
//                        this.craftItem();
//                        this.resetProgress();
//                    }
//                } else {
//                    this.resetProgress();
//                }
//            } else {
//                this.resetProgress();
//                markDirty(world, pos, state);
//            }
//        }
//    }
//
//    private void resetProgress() {
//        this.progress=0;
//    }
//
//    private void craftItem() {
//        this.removeStack(INPUT_SLOT,1);
//        ItemStack result = new ItemStack(ModItems.EMPTY_SOUL_CANISTER);
//        this.soulEnergy++;
//        this.setStack(OUTPUT_SLOT, new ItemStack(result.getItem(), getStack(OUTPUT_SLOT).getCount()+result.getCount()));
//    }
//
//    private void increaseCraftProgress() {
//        progress++;
//    }
//
//    private boolean hasRecipe(){
//        ItemStack result = new ItemStack(ModItems.EMPTY_SOUL_CANISTER);
//        /// add dynamic thingy heere
//        boolean hasInput = getStack(INPUT_SLOT).getItem()==ModItems.FULL_SOUL_CANISTER;
//
//        return hasInput && canInsertAmountIntoOutputSlot(result)&&canInsertItemIntoOutputSlot(result.getItem());
//    }
//
//    private boolean canInsertItemIntoOutputSlot(Item item) {
//        return this.getStack(OUTPUT_SLOT).getItem() == item || this.getStack(OUTPUT_SLOT).isEmpty();
//    }
//
//    private boolean canInsertAmountIntoOutputSlot(ItemStack result) {
//        return this.getStack(OUTPUT_SLOT).getCount() + result.getCount() <= getStack(OUTPUT_SLOT).getMaxCount();
//    }
//
//    private boolean isOutputSlotEmptyorReceivable(){
//        return this.getStack(OUTPUT_SLOT).isEmpty()||this.getStack(OUTPUT_SLOT).getCount() < this.getStack(OUTPUT_SLOT).getMaxCount();
//    }
//    private boolean hasCraftingFinished(){
//        return progress >=maxProgress;
//    }
//
//    public int getSoulEnergy() {
//        return this.soulEnergy;
//    }
//}
