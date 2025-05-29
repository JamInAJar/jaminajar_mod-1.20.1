package io.github.jaminajar.jaminajarmod.items.custom;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.ProjectileEntity;
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
    private final float projectileVelocity;

    public GunItem(Settings settings, Item ammoItem, int maxAmmo, BiFunction<World, PlayerEntity, Entity> projectileFactory,int cooldownTime, float projectileVelocity) {
        super(settings, ammoItem, maxAmmo);
        this.projectileFactory = projectileFactory;
        this.cooldownTime = cooldownTime;
        this.projectileVelocity = projectileVelocity;
    }
    @Override
    public UseAction getUseAction(ItemStack stack) {
        return UseAction.NONE;
    }
    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        ItemStack stack = user.getStackInHand(hand);
        if(this.getAmmo(stack)!=0&&!user.isSneaking()) {
            if (!world.isClient) {
                Entity projectile = projectileFactory.apply(world, user);
                if(projectile instanceof ProjectileEntity proj){
                    proj.setVelocity(user, user.getPitch(), user.getYaw(), 0.0f, projectileVelocity, 0.2f);
                }
                world.spawnEntity(projectile);
            }
            setAmmo(stack,getAmmo(stack)-1);
        }
        user.getItemCooldownManager().set(this,cooldownTime);
        return super.use(world, user, hand);
    }
}
