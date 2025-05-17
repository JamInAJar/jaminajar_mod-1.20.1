package io.github.jaminajar.jaminajarmod.items.custom;

import io.github.jaminajar.jaminajarmod.entity.HonkProjectileEntity;
import io.github.jaminajar.jaminajarmod.entity.ModEntities;
import io.github.jaminajar.jaminajarmod.entity.NoteProjectileEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Vanishable;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.UseAction;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
/// straight up not working rn will try to fix
/// if any advice or fixes pls lmk
public class CacophonyItem extends Item implements Vanishable {
    private static final int MAX_USE_TIME = 600;
    private static final String TICKS_USED_KEY = "TicksUsed";

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
        user.playSound(SoundEvents.BLOCK_AMETHYST_BLOCK_BREAK,1.0F,1.0F);
        itemStack.getOrCreateNbt().putInt(TICKS_USED_KEY, 0); // Reset on use
        user.setCurrentHand(hand);

        if (!world.isClient) {
            HonkProjectileEntity honkProjectileEntity = new HonkProjectileEntity(world, user);
            honkProjectileEntity.setVelocity(user, user.getPitch(), user.getYaw(), 0.0f, 10.0f, 0.2f);
            honkProjectileEntity.setCritical(false);
            world.spawnEntity(honkProjectileEntity);
            if (world instanceof ServerWorld serverWorld) {
                Vec3d pos = honkProjectileEntity.getPos();
                for (int i = 0; i < 8; i++) {
                    serverWorld.spawnParticles(ParticleTypes.SONIC_BOOM,
                            pos.x, pos.y, pos.z,
                            1, 0.0, 0.0, 0.0, 0.0);
                }
            }

            user.playSound(SoundEvents.ENTITY_WARDEN_SONIC_BOOM, 3.0F, 1.0F);
        }

        return TypedActionResult.success(itemStack, world.isClient());
    }

    @Override
    public void usageTick(World world, LivingEntity user, ItemStack stack, int remainingUseTicks) {
        if (!world.isClient()) {
            NoteProjectileEntity noteProjectileEntity = new NoteProjectileEntity(ModEntities.NOTE_PROJECTILE, world);
            noteProjectileEntity.setVelocity(user, user.getPitch(), user.getYaw(), 0.0F, 3.5F + (world.getRandom().nextFloat() - 0.5F), 2.0F + (world.getRandom().nextFloat() - 0.5F));
            noteProjectileEntity.setOwner(user);
            noteProjectileEntity.setNoGravity(true);
            noteProjectileEntity.setCritical(false);
            world.spawnEntity(noteProjectileEntity);

            // Increment ticks used in NBT
            ///NbtCompound nbt = stack.getOrCreateNbt();
            ///int ticksUsed = nbt.getInt(TICKS_USED_KEY);
            ///nbt.putInt(TICKS_USED_KEY, ticksUsed + 1);
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
