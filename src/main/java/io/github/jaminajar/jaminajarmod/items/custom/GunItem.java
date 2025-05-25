package io.github.jaminajar.jaminajarmod.items.custom;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.UseAction;
import net.minecraft.world.World;

import java.util.function.BiFunction;

public class GunItem extends AmmoItem {
    private final BiFunction<World, PlayerEntity, Entity> projectileFactory;
    private final int cooldownTime;

    public GunItem(Settings settings, Item ammoItem, int maxAmmo, BiFunction<World, PlayerEntity, Entity> projectileFactory,int cooldownTime) {
        super(settings, ammoItem, maxAmmo);
        this.projectileFactory = projectileFactory;
        this.cooldownTime = cooldownTime;
    }
    @Override
    public UseAction getUseAction(ItemStack stack) {
        return UseAction.NONE;
    }
    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        ItemStack stack = user.getStackInHand(hand);
        if(this.getAmmo(stack)!=0) {
            if (!world.isClient) {
                Entity projectile = projectileFactory.apply(world, user);
                world.spawnEntity(projectile);
            }
            setAmmo(stack,getAmmo(stack)-1);
        }
        user.getItemCooldownManager().set(this,cooldownTime);
        return super.use(world, user, hand);
    }
}
