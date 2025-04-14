package io.github.jaminajar.jaminajarmod.items.soul;

import io.github.jaminajar.jaminajarmod.entity.ModEntities;
import io.github.jaminajar.jaminajarmod.entity.SoulerBeamProjectile;
import io.github.jaminajar.jaminajarmod.entity.SoulerSoulProjectile;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;


public class SoulerItem extends Item {
    private final int maxSoulEnergyCount;
    public SoulerItem(Settings settings, int maxSoulEnergyCount) {
        super(settings);
        this.maxSoulEnergyCount = maxSoulEnergyCount;
    }
    public int getSoulEnergyCount(ItemStack stack){return stack.getOrCreateNbt().getInt("SoulEnergy");}
    public void ChangeSoulEnergy(ItemStack stack, int soulEnergyCount){
        stack.getOrCreateNbt().putInt("SoulEnergy", MathHelper.clamp(soulEnergyCount,0,maxSoulEnergyCount));

    }
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand){
        ItemStack itemStack = user.getStackInHand(hand);
        user.setCurrentHand(hand);
        ItemStack stack=user.getStackInHand(hand);
        double soulEnergyCount = this.getSoulEnergyCount(stack);
        if(user.isSneaking()&&soulEnergyCount!=0){
            ChangeSoulEnergy(stack, 0);
            SoulerBeamProjectile soulerBeamProjectile = new SoulerBeamProjectile(ModEntities.SOULER_BEAM_PROJECTILE, world);
            soulerBeamProjectile.setVelocity(user, user.getPitch(),user.getYaw(),0.0F,100f,0.001f);
            soulerBeamProjectile.setOwner(user);
            soulerBeamProjectile.setNoGravity(true);
            world.spawnEntity(soulerBeamProjectile);

        }else if(user.isSneaking()&&soulEnergyCount<=0){
            user.sendMessage(Text.literal("No Soul Energy!"),true);
        } else if(!user.isSneaking()){
            if(!world.isClient()) {
                SoulerSoulProjectile soulerSoulProjectile = new SoulerSoulProjectile(ModEntities.SOULER_SOUL_PROJECTILE, world);
                soulerSoulProjectile.setVelocity(user, user.getPitch(), user.getYaw(), 0.0F, 3.0F, 1.0F);
                soulerSoulProjectile.setOwner(user);
                soulerSoulProjectile.setNoGravity(true); // or false, depending on behavior
                world.spawnEntity(soulerSoulProjectile);
                /// <ChangeSoulEnergy(stack, (int) (soulEnergyCount+1));> upon entity hit
            }
        }
        user.getItemCooldownManager().set(this, 20);
        return TypedActionResult.success(itemStack);
    }

}
