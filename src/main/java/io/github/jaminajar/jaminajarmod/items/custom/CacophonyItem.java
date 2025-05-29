package io.github.jaminajar.jaminajarmod.items.custom;

import io.github.jaminajar.jaminajarmod.entity.HonkProjectileEntity;
import io.github.jaminajar.jaminajarmod.entity.ModEntities;
import io.github.jaminajar.jaminajarmod.entity.NoteProjectileEntity;
import io.github.jaminajar.jaminajarmod.entity.damage.ModDamageTypes;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Vanishable;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.UseAction;
import net.minecraft.world.World;

public class CacophonyItem extends Item implements Vanishable {
    private static final int MAX_USE_TIME = 72000;

    public CacophonyItem(Settings settings) {
        super(settings);
    }

    @Override
    public UseAction getUseAction(ItemStack stack) {
        return UseAction.NONE;
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        ItemStack itemStack = user.getStackInHand(hand);
        user.setCurrentHand(hand);
        sonicBoom(user, world);

        return TypedActionResult.success(itemStack, world.isClient());
    }

    public void sonicBoom(PlayerEntity user, World world) {
        world.playSound(null, user.getX(), user.getY(), user.getZ(), SoundEvents.ENTITY_WARDEN_SONIC_BOOM, user.getSoundCategory(), 1.0F, 1.0F);
        if (!world.isClient) {
            HonkProjectileEntity honkProjectileEntity = new HonkProjectileEntity(ModEntities.HONK_PROJECTILE, world);
            honkProjectileEntity.setVelocity(user, user.getPitch(), user.getYaw(), 0.0f, 5.0f, 0.2f);
            honkProjectileEntity.setOwner(user);
            honkProjectileEntity.setNoGravity(true);
            honkProjectileEntity.setCritical(false);
            world.spawnEntity(honkProjectileEntity);

        }
    }

    @Override
    public void usageTick(World world, LivingEntity user, ItemStack stack, int remainingUseTicks) {
        var nbt = stack.getOrCreateNbt();
        int ticks = nbt.getInt("CacophonyTicks") + 1;
        nbt.putInt("CacophonyTicks", ticks);

        int semitone = (int) Math.round(((-user.getPitch() + 90) / 180.0) * 24);
        semitone = Math.max(0, Math.min(24, semitone));
        float notePitch = (float) Math.pow(2.0D, (semitone - 12) / 12.0D);
        float adjustedNotePitch = (float) Math.pow(2.0D, (semitone - 18) / 12.0D);

        world.playSound(null, user.getX(), user.getY(), user.getZ(),
                SoundEvents.BLOCK_NOTE_BLOCK_FLUTE, SoundCategory.RECORDS, 1.0F, notePitch, 0);
        world.playSound(null, user.getX(), user.getY(), user.getZ(),
                SoundEvents.BLOCK_NOTE_BLOCK_FLUTE, SoundCategory.RECORDS, 1.0F, adjustedNotePitch, 0);

        if (!world.isClient()) {
            NoteProjectileEntity note = new NoteProjectileEntity(ModEntities.NOTE_PROJECTILE, world);
            note.setVelocity(user, user.getPitch(), user.getYaw(), 0.0F, 0.7f, 6.7f);
            note.setOwner(user);
            note.setNoGravity(true);
            note.setCritical(false);
            world.spawnEntity(note);
        }

        if (ticks >= 20 && ticks % 20 == 0) {
            if (!world.isClient() && user instanceof PlayerEntity player) {
                sonicBoom(player, world);

                if (ticks >= 200) {
                    float damage = 3.0f + (ticks / 1000f);

                    user.damage(new DamageSource(
                            user.getWorld().getRegistryManager()
                                    .get(RegistryKeys.DAMAGE_TYPE)
                                    .entryOf(ModDamageTypes.CACOPHONY_OVERBLOW)
                    ), damage);

                    world.playSound(null, user.getX(), user.getY(), user.getZ(),
                            SoundEvents.ENTITY_WARDEN_HEARTBEAT,
                            SoundCategory.PLAYERS,
                            3.0F,
                            (float) Math.random());
                }
            }
        }
    }


    @Override
    public void onStoppedUsing(ItemStack stack, World world, LivingEntity user, int remainingUseTicks) {
        if (!world.isClient && user instanceof PlayerEntity player) {

            int ticks = stack.getOrCreateNbt().getInt("CacophonyTicks");
            int cooldown = Math.max(2*user.getItemUseTime()/3, 30);

            player.getItemCooldownManager().set(this, cooldown);

            int darkness = 2 * cooldown;

            if (darkness >= 200) {
                player.addStatusEffect(new StatusEffectInstance(StatusEffects.DARKNESS, cooldown / 2));
                player.damage(new DamageSource(
                        world.getRegistryManager()
                                .get(RegistryKeys.DAMAGE_TYPE)
                                .entryOf(ModDamageTypes.CACOPHONY_OVERBLOW)
                ), 4 + (ticks / 1000f));
            }
        }
        stack.getOrCreateNbt().putInt("CacophonyTicks", 0);
    }


    @Override
    public int getMaxUseTime(ItemStack stack) {
        return MAX_USE_TIME;
    }
}
