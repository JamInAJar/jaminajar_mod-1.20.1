package io.github.jaminajar.jaminajarmod.entity;

import io.github.jaminajar.jaminajarmod.entity.damage.ModDamageTypes;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.FlyingItemEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.world.World;

public class NoteProjectileEntity extends PersistentProjectileEntity {

    public void setOwner(LivingEntity owner) {
        super.setOwner(owner);
        this.setPosition(owner.getX(), owner.getEyeY() - 0.1, owner.getZ());
    }
    public NoteProjectileEntity(EntityType<? extends NoteProjectileEntity> entityType, World world) {
        super(entityType, world);
    }


    @Override
    protected void initDataTracker() {
        super.initDataTracker();
    }

    @Override
    public void tick() {
        super.tick();
        if (this.getWorld().isClient) {
            this.getWorld().addParticle(
                    ParticleTypes.NOTE,
                    this.getX(), this.getY(), this.getZ(),
                    0.0, 0.0, 0.0
            );
        }
    }
    @Override
    protected void onEntityHit(EntityHitResult entityHitResult){
        if (!this.getWorld().isClient()){
            Entity target = entityHitResult.getEntity();
            Entity owner = this.getOwner();
            DamageSource damageSource = new DamageSource(
                    getWorld().getRegistryManager()
                            .get(RegistryKeys.DAMAGE_TYPE)
                            .entryOf(ModDamageTypes.NOTE_PROJECTILE));
            target.damage(damageSource, 0.5F);
            if (target instanceof LivingEntity) {
                this.applyDamageEffects((LivingEntity)owner, target);
            }
            this.discard();
        }

    }

    protected void onCollision(HitResult hitResult){
        super.onCollision(hitResult);
    }
    protected void onBlockHit(BlockHitResult blockHitResult){
        this.discard();
    }

    @Override
    protected ItemStack asItemStack() {
        return ItemStack.EMPTY;
    }

}
