package io.github.jaminajar.jaminajarmod.items.custom;

import io.github.jaminajar.jaminajarmod.entity.HonkProjectileEntity;
import io.github.jaminajar.jaminajarmod.entity.ModEntities;
import io.github.jaminajar.jaminajarmod.entity.NoteProjectileEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Vanishable;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.UseAction;
import net.minecraft.world.World;
/// straight up not working rn will try to fix
/// if any advice or fixes pls lmk
public class CacophonyItem extends Item implements Vanishable {
    private static final int MAX_USE_TIME = 600;

    public CacophonyItem(Settings settings) {
        super(settings);
    }

    @Override
    public UseAction getUseAction(ItemStack stack) {
        return UseAction.TOOT_HORN;
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        ItemStack itemStack = user.getStackInHand(hand);
        System.out.println("Cacophony Used");
        user.setCurrentHand(hand);
        user.playSound(SoundEvents.ENTITY_WARDEN_SONIC_BOOM, 300.0F, 1.0F);
        if (!world.isClient) {
            HonkProjectileEntity honkProjectileEntity = new HonkProjectileEntity(ModEntities.HONK_PROJECTILE, world);
            honkProjectileEntity.setVelocity(user, user.getPitch(), user.getYaw(), 0.0f, 5.0f, 0.2f);
            honkProjectileEntity.setOwner(user);
            honkProjectileEntity.setNoGravity(true);
            honkProjectileEntity.setCritical(false);
            world.spawnEntity(honkProjectileEntity);

        }

        return TypedActionResult.success(itemStack, world.isClient());
    }

    @Override
    public void usageTick(World world, LivingEntity user, ItemStack stack, int remainingUseTicks) {
        if (!world.isClient()) {
            NoteProjectileEntity noteProjectileEntity = new NoteProjectileEntity(ModEntities.NOTE_PROJECTILE, world);
            noteProjectileEntity.setVelocity(user, user.getPitch(),user.getYaw(),0.0F,0.4f,5.7f);
            noteProjectileEntity.setOwner(user);
            noteProjectileEntity.setNoGravity(true);
            noteProjectileEntity.setCritical(false);
            world.spawnEntity(noteProjectileEntity);

            // Increment ticks used in NBT
            ///NbtCompound nbt = stack.getOrCreateNbt();
            ///int ticksUsed = nbt.getInt(TICKS_USED_KEY);
            ///nbt.putInt(TICKS_USED_KEY, ticksUsed + 1);
            double randomPitch = Math.random();
            user.playSound(SoundEvents.BLOCK_AMETHYST_BLOCK_RESONATE, 3.0F, (float) randomPitch);
        }
    }

    @Override
    public void onStoppedUsing(ItemStack stack, World world, LivingEntity user, int remainingUseTicks) {
        if (!world.isClient && user instanceof PlayerEntity player) {
            ///int ticksUsed = stack.getOrCreateNbt().getInt(TICKS_USED_KEY);
            int cooldown = Math.max(user.getItemUseTime(), 20);
            player.getItemCooldownManager().set(this, cooldown);
        }
    }

    @Override
    public int getMaxUseTime(ItemStack stack) {
        return MAX_USE_TIME;
    }
}
