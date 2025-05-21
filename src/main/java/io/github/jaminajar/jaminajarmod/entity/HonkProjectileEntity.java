package io.github.jaminajar.jaminajarmod.entity;

import io.github.jaminajar.jaminajarmod.entity.damage.ModDamageSources;
import io.github.jaminajar.jaminajarmod.entity.damage.ModDamageTypes;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;

import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.world.World;


public class HonkProjectileEntity extends PersistentProjectileEntity {


    public void setOwner(LivingEntity owner) {
        super.setOwner(owner);
        this.setPosition(owner.getX(), owner.getEyeY() - 0.1, owner.getZ());
    }
    public HonkProjectileEntity(EntityType<? extends HonkProjectileEntity> entityType, World world) {
        super(entityType, world);
    }

    public void tick(){
        super.tick();
        ///this.setNoGravity(true);
            if (!this.getWorld().isClient) {
            }


            if (this.getWorld().isClient) {
                this.getWorld().addParticle(
                        ParticleTypes.SONIC_BOOM,
                        this.getX(), this.getY(), this.getZ(),
                        0.0, 0.0, 0.0
                );

        }
    }
    protected void onEntityHit(EntityHitResult entityHitResult){
        StatusEffectInstance statusEffectInstance = new StatusEffectInstance(StatusEffects.DARKNESS, 100, 0);
        if (!this.getWorld().isClient()){
            Entity entity = entityHitResult.getEntity();
            DamageSource damageSource = new DamageSource(
                    getWorld().getRegistryManager()
                            .get(RegistryKeys.DAMAGE_TYPE)
                            .entryOf(ModDamageTypes.NOTE_PROJECTILE));
            entity.damage(damageSource, 10.0F);
            assert entity instanceof LivingEntity;
            ((LivingEntity) entity).addStatusEffect(statusEffectInstance);
        }
}
    protected void onBlockHit(BlockHitResult blockHitResult){
        this.discard();
    }
    @Override
    protected ItemStack asItemStack() {
        return ItemStack.EMPTY;
    }
}
