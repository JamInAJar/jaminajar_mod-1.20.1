package io.github.jaminajar.jaminajarmod.items.custom;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

public class AmmoItem extends Item {
    public int maxAmmo;
    public Item ammoItem;
    public AmmoItem(Settings settings, Item ammoItem, int maxAmmo) {
        super(settings);
        this.ammoItem = ammoItem;
        this.maxAmmo = maxAmmo;
    }

    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        ItemStack stack = user.getStackInHand(hand);
        int ammoToBeStored = Math.min(maxAmmo,getItemCount(user,ammoItem));
        if (user.isSneaking()) {
            stack = user.getStackInHand(hand);
            int currentAmmo = getAmmo(stack);
            if (currentAmmo < maxAmmo && playerHasItem(user, ammoItem)) {
                setAmmo(stack, currentAmmo + ammoToBeStored);
                reduceItem(user, ammoItem, ammoToBeStored);
                return TypedActionResult.success(stack);
            }
            return TypedActionResult.pass(stack);
        }
        return TypedActionResult.fail(stack);
    }
    public int getAmmo(ItemStack stack){
        return stack.getOrCreateNbt().getInt("Ammo");
    }
    public void setAmmo(ItemStack stack, int storedAmmo){
        stack.getOrCreateNbt().putInt("Ammo", MathHelper.clamp(storedAmmo,0,maxAmmo));
    }
    public static boolean playerHasItem(PlayerEntity player, Item itemToCheck) {
        for (ItemStack stack : player.getInventory().main) {
            if (stack.getItem() == itemToCheck) {
                return true;
            }
        }
        return false;
    }
    public static int getItemCount(PlayerEntity player, Item itemToCheck) {
        int count = 0;
        for (ItemStack stack : player.getInventory().main) {
            if (stack.getItem() == itemToCheck) {
                count += stack.getCount();
            }
        }
        for (ItemStack stack : player.getInventory().offHand) {
            if (stack.getItem() == itemToCheck) {
                count += stack.getCount();
            }
        }
        return count;
    }
    public static void reduceItem(PlayerEntity player, Item itemToReduce, int amount) {
        for (ItemStack stack : player.getInventory().main) {
            if (stack.getItem() == itemToReduce) {
                int currentCount = stack.getCount();
                if (currentCount >= amount) {
                    stack.decrement(amount);
                }
                return;
            }
        }
    }
    public boolean isItemBarVisible(ItemStack stack) {
        return getAmmo(stack) > 0;
    }
    @Override
    public int getItemBarStep(ItemStack stack) {
        int charge = getAmmo(stack);
        return MathHelper.ceil(charge * 13.0F / maxAmmo);
    }
    @Override
    public int getItemBarColor(ItemStack stack) {
        int charge = getAmmo(stack);
        return charge == 0 ? 0x220000 : 0xFFFF00;
    }
}
