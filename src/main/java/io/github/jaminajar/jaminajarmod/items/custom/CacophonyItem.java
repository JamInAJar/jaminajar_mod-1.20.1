package io.github.jaminajar.jaminajarmod.items.custom;

import io.github.jaminajar.jaminajarmod.entity.HonkProjectileEntity;
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
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class CacophonyItem extends Item implements Vanishable {
    private static final int MAX_USE_TIME = 600;
    public CacophonyItem(Settings settings) {
        super(settings);
    }

    @Override
    public UseAction getUseAction(ItemStack stack) {
        return UseAction.SPEAR;
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        ItemStack itemStack = user.getStackInHand(hand);
        user.setCurrentHand(hand);

        int ticksUsed = 0;
        if (!world.isClient) {
            HonkProjectileEntity honkProjectileEntity = new HonkProjectileEntity(world, user);
            honkProjectileEntity.setVelocity(user, user.getPitch(), user.getYaw(), 0.0f, 10.0f, 0.2f);

            Vec3d aimPoint = user.getPos().add(user.getRotationVector().multiply(30));
            ServerWorld serverWorld = (ServerWorld) world;

            if (world instanceof ServerWorld) {
                for (int o = 1; o < MathHelper.floor(aimPoint.length()) + 7; o++) {
                    serverWorld.spawnParticles(ParticleTypes.SONIC_BOOM,
                            honkProjectileEntity.getX(),
                            honkProjectileEntity.getY(),
                            honkProjectileEntity.getZ(),
                            1,
                            0.0,
                            0.0,
                            0.0,
                            0.0);
                    serverWorld.spawnEntity(honkProjectileEntity);
                }
            }

            user.playSound(SoundEvents.ENTITY_WARDEN_SONIC_BOOM, 3.0F, 1.0F);
            ticksUsed++;
        }
        user.getItemCooldownManager().set(this, ticksUsed);
        return TypedActionResult.success(itemStack);
    }

    @Override
    public void usageTick(World world, LivingEntity user, ItemStack stack, int remainingUseTicks) {
        super.usageTick(world, user, stack, remainingUseTicks);
        for (int i = 0; i < 3; i++) {
            if(!world.isClient()) {
                NoteProjectileEntity noteProjectileEntity = new NoteProjectileEntity(world, user);
                noteProjectileEntity.setVelocity(user,
                        user.getPitch(),
                        user.getYaw(),
                        0.0F,
                        3.5F + (world.getRandom().nextFloat() - 0.5F),
                        2.0F + (world.getRandom().nextFloat() - 0.5F));
                world.spawnEntity(noteProjectileEntity);
            }
        }
    }

    @Override
    public int getMaxUseTime(ItemStack stack) {
        return MAX_USE_TIME;
    }
}
