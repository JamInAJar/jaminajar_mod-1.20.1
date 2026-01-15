package io.github.jaminajar.jaminajarmod.entity;

import io.github.jaminajar.jaminajarmod.effects.ModEffects;
import net.minecraft.entity.AreaEffectCloudEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.world.World;

public class SoulerSoulExplosiveEntity extends PersistentProjectileEntity {
    public SoulerSoulExplosiveEntity(EntityType<? extends PersistentProjectileEntity> type, World world) {
        super(type, world);
    }
    float explosionPower = 0;
    float soulEnergyCount;
    float scalingMult = 1;
    public void setSoulEnergyCount(float count){
        this.soulEnergyCount = count;
    }
    public void setScalingMult(float count){
        this.scalingMult = count;
    }
    @Override
    protected void onBlockHit(BlockHitResult blockHitResult) {
        explode();
        super.onBlockHit(blockHitResult);
    }

    @Override
    protected void onEntityHit(EntityHitResult entityHitResult) {
        explode();
        super.onEntityHit(entityHitResult);
    }

    protected void explode(){
        explosionPower = scalingMult*(4 * soulEnergyCount)/3;
        this.getWorld().createExplosion(this,this.getX(),this.getY(),this.getZ(),explosionPower,true, World.ExplosionSourceType.TNT);
        StatusEffectInstance statusEffectInstance = new StatusEffectInstance(ModEffects.SOULED, (int) (explosionPower*400), (int) Math.floor(explosionPower));
        AreaEffectCloudEntity soulCloud = new AreaEffectCloudEntity(this.getWorld(),this.getX(),this.getY(),this.getZ());
        soulCloud.setRadius(3.0F);
        soulCloud.setRadiusOnUse(-0.5F);
        soulCloud.setWaitTime(10);
        soulCloud.setRadiusGrowth(-soulCloud.getRadius() / (float)soulCloud.getDuration());
        soulCloud.setPotion(new Potion("Soul",statusEffectInstance));
        this.getWorld().spawnEntity(soulCloud);
        this.discard();
    }
    @Override
    protected ItemStack asItemStack() {
        return null;
    }
    public void setOwner(LivingEntity owner) {
        super.setOwner(owner);
        this.setPosition(owner.getX(), owner.getEyeY() - 0.1, owner.getZ());
    }
}
