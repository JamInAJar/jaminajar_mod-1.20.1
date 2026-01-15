package io.github.jaminajar.jaminajarmod.items.custom;


import io.github.jaminajar.jaminajarmod.entity.RockProjectileEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.stat.Stats;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

public class RockItem extends Item {
    public RockItem(Settings settings) {
        super(settings);
    }
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        user.setCurrentHand(hand);
        ItemStack itemStack = user.getStackInHand(hand);
        world.playSound(
                null,
                user.getX(),
                user.getY(),
                user.getZ(),
                SoundEvents.ENTITY_SNOWBALL_THROW,
                SoundCategory.NEUTRAL,
                0.5F,
                0.4F / (world.getRandom().nextFloat() * 0.4F - 0.8F)
        );
        if (!world.isClient) {
            RockProjectileEntity rock = new RockProjectileEntity(world);
            rock.setOwner(user);
            rock.setPosition(user.getX(),user.getEyeY()-0.1,user.getZ());
            rock.setVelocity(user, user.getPitch(), user.getYaw(), 0.0F, 0.5F, 1.0F);
            world.spawnEntity(rock);
        }

        user.incrementStat(Stats.USED.getOrCreateStat(this));
        if (!user.getAbilities().creativeMode) {
            itemStack.decrement(1);
        }
        user.getItemCooldownManager().set(itemStack.getItem(), 5);
        return TypedActionResult.success(itemStack, world.isClient());
    }
}
