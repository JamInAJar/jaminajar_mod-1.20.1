package io.github.jaminajar.jaminajarmod.entity;

import io.github.jaminajar.jaminajarmod.effects.SouledEffect;
import io.github.jaminajar.jaminajarmod.entity.damage.ModDamageTypes;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.projectile.ArrowEntity;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.entity.projectile.ProjectileEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class SoulerSoulProjectile extends ProjectileEntity {
    public SoulerSoulProjectile(EntityType<? extends ProjectileEntity> entityType, World world) {
        super(entityType, world);
    }

    @Override
    protected void initDataTracker() {

    }

    public void tick(){
        super.tick();
        this.setNoGravity(true);
    }
    public void takeSoulKnockback(double strength, double x, double y, double z) {
            this.velocityDirty = true;
            Vec3d vec3d = this.getVelocity();
            Vec3d vec3d2 = new Vec3d(x, y, z).normalize().multiply(strength);
            this.setVelocity(-vec3d.x / 2.0 - vec3d2.x, this.isOnGround() ? Math.min(0.4, vec3d.y / 2.0 + strength) : vec3d.y, -vec3d.z / 2.0 - vec3d2.z);
    }
    protected void onEntityHit(EntityHitResult entityHitResult){
        StatusEffectInstance statusEffectInstance = new StatusEffectInstance(new SouledEffect(StatusEffectCategory.HARMFUL,2551850));
        if (!this.getWorld().isClient()){
            Entity entity = entityHitResult.getEntity();
            Entity entity2 = this.getOwner();
            DamageSource damageSource = new DamageSource(
                    getWorld().getRegistryManager()
                            .get(RegistryKeys.DAMAGE_TYPE)
                            .entryOf(ModDamageTypes.SOULER_SOULED_DAMAGE));
            entity.damage(damageSource, 5.0F);
            takeSoulKnockback(5,entity.getX(),entity.getY(),entity.getZ());
            if (entity2 instanceof LivingEntity) {
                LivingEntity livingEntity= (LivingEntity) entity;
                livingEntity.addStatusEffect(statusEffectInstance);
                /// add +soul energy
            }
        }

    }}

