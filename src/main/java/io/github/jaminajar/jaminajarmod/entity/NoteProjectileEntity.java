package io.github.jaminajar.jaminajarmod.entity;

import io.github.jaminajar.jaminajarmod.entity.damage.ModDamageTypes;
import io.github.jaminajar.jaminajarmod.util.Utils;
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
import net.minecraft.util.hit.HitResult;
import net.minecraft.world.World;

public class NoteProjectileEntity extends PersistentProjectileEntity {

    public void setOwner(LivingEntity owner) {
        super.setOwner(owner);
        this.setPosition(owner.getX(), owner.getEyeY() - 0.25, owner.getZ());
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
        if (this.age>200){
            this.discard();}
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
                            .entryOf(ModDamageTypes.NOTE_PROJECTILE),owner);
            if (target instanceof LivingEntity livingTarget) {
                Utils.CombatUtils.damageIgnoringIFrames(livingTarget,damageSource,1.5f);
            } else{
                target.damage(damageSource, 1.5f);
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
