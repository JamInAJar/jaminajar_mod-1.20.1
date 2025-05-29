package io.github.jaminajar.jaminajarmod.items.soul;

import io.github.jaminajar.jaminajarmod.entity.damage.ModDamageTypes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

public class SoulCanisterItem extends Item {
    public SoulCanisterItem(Settings settings, int fullness) {
        super(settings);
    }



    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        ItemStack stack = user.getStackInHand(hand);
        NbtCompound nbt = stack.getNbt();
        if(user instanceof PlayerEntity && !nbt.getBoolean("Full")) {
            user.damage(new DamageSource(
                    user.getWorld().getRegistryManager()
                            .get(RegistryKeys.DAMAGE_TYPE)
                            .entryOf(ModDamageTypes.SOULER_SOUL)
            ), 3);
            stack.setNbt(nbt);
        }
        return super.use(world, user, hand);
    }
}
