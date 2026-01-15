package io.github.jaminajar.jaminajarmod.items.custom;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

public class InstrumentItem extends Item {
    RegistryEntry<SoundEvent> soundEvent;
    public InstrumentItem(Settings settings, RegistryEntry<SoundEvent> soundEvent) {
        super(settings);
        this.soundEvent = soundEvent;
    }
    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        ItemStack itemStack = user.getStackInHand(hand);
        user.setCurrentHand(hand);

        return TypedActionResult.success(itemStack, world.isClient());
    }
    @Override
    public void usageTick(World world, LivingEntity user, ItemStack stack, int remainingUseTicks) {
        super.usageTick(world, user, stack, remainingUseTicks);
        int semitone = (int) Math.round(((-user.getPitch() + 90) / 180.0) * 24);
        semitone = Math.max(0, Math.min(24, semitone));
        float notePitch = (float) Math.pow(2.0D, (semitone - 12) / 12.0D);


        world.playSound(null, user.getX(), user.getY(), user.getZ(),
                soundEvent, SoundCategory.RECORDS, 1.0F, notePitch, 0);
    }
}
