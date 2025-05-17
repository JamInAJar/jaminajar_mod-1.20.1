package io.github.jaminajar.jaminajarmod.entity;

import io.github.jaminajar.jaminajarmod.entity.damage.ModDamageTypes;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.world.World;

public class SoulerBeamProjectile extends PersistentProjectileEntity {
    public SoulerBeamProjectile(EntityType<? extends SoulerBeamProjectile> entityType, World world) {
        super(entityType, world);
    }

    public void setOwner(LivingEntity owner) {
        super.setOwner(owner);
        this.setPosition(owner.getX(), owner.getEyeY() - 0.1, owner.getZ());
    }


    @Override
    public void tick(){
        super.tick();
        ///this.setNoGravity(true);
        if (!this.getWorld().isClient) {
            // physics or hit detection if needed
        }

        // Client-side: spawn particles
        if (this.getWorld().isClient) {
            this.getWorld().addParticle(
                    ParticleTypes.SOUL,
                    this.getX(), this.getY(), this.getZ(),
                    0.0, 0.0, 0.0
            );
        }
    }
    @Override
    protected void initDataTracker() {super.initDataTracker();}

    protected void onEntityHit(EntityHitResult entityHitResult){
        Entity owner = this.getOwner();
        Entity target = entityHitResult.getEntity();
        if(owner instanceof LivingEntity) {
            DamageSource damageSource = new DamageSource(
                    getWorld().getRegistryManager()
                            .get(RegistryKeys.DAMAGE_TYPE)
                            .entryOf(ModDamageTypes.SOULER_LASER));
            target.damage(damageSource, 10.0F);
            // Additional hit effects
        }
        this.discard();
    }
    @Override
    protected void onBlockHit(BlockHitResult blockHitResult){
        this.discard();
    }
    @Override
    protected ItemStack asItemStack() {
        return ItemStack.EMPTY;
    }
}
