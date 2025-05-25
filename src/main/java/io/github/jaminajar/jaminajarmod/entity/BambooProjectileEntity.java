package io.github.jaminajar.jaminajarmod.entity;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.damage.DamageTypes;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.world.World;




public class BambooProjectileEntity extends PersistentProjectileEntity {
    StatusEffect statusEffect;

    public void setOwner(LivingEntity owner) {
        super.setOwner(owner);
        this.setPosition(owner.getX(), owner.getEyeY() - 0.1, owner.getZ());
    }
    public BambooProjectileEntity(EntityType<? extends BambooProjectileEntity> type, World world, LivingEntity owner) {
        super(type, world);
        this.setOwner(owner);
        this.setVelocity(owner, owner.getPitch(),owner.getYaw(),0.5F,0.1f,0.4f);
    }
    public BambooProjectileEntity(EntityType<? extends BambooProjectileEntity> type, World world) {
        super(type, world);
    }


    public void tick(){
        super.tick();
    }
    protected void onEntityHit(EntityHitResult entityHitResult){
        double roll = Math.random();
        if (roll < 0.33) {
            statusEffect = StatusEffects.BLINDNESS;
        } else if (roll < 0.75) {
            statusEffect = StatusEffects.POISON;
        } else {
            statusEffect = StatusEffects.NAUSEA;
        }
        StatusEffectInstance statusEffectInstance = new StatusEffectInstance(statusEffect, 200, 2);
        if (!this.getWorld().isClient()){
            Entity entity = entityHitResult.getEntity();
            DamageSource damageSource = new DamageSource(
                    getWorld().getRegistryManager()
                            .get(RegistryKeys.DAMAGE_TYPE)
                            .entryOf(DamageTypes.TRIDENT));
            entity.damage(damageSource, 3.0F);
            if(entity instanceof LivingEntity){
                ((LivingEntity) entity).addStatusEffect(statusEffectInstance);
            }
        }
        this.discard();
    }
    protected void onBlockHit(BlockHitResult blockHitResult){
        this.discard();
    }
    @Override
    protected ItemStack asItemStack() {
        return ItemStack.EMPTY;
    }
}
