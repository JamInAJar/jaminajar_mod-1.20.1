package io.github.jaminajar.jaminajarmod.entity;

import io.github.jaminajar.jaminajarmod.entity.damage.ModDamageSources;
import io.github.jaminajar.jaminajarmod.entity.damage.ModDamageTypes;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;

import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.damage.DamageTypes;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.world.World;


public class DripstoneProjectileEntity extends PersistentProjectileEntity {


    public void setOwner(LivingEntity owner) {
        super.setOwner(owner);
        this.setPosition(owner.getX(), owner.getEyeY() - 0.1, owner.getZ());
    }
    public DripstoneProjectileEntity(EntityType<? extends DripstoneProjectileEntity> type, World world, LivingEntity owner) {
        super(type, world);
        this.setOwner(owner);
        this.setVelocity(owner, owner.getPitch(),owner.getYaw(),0.0F,2.0f,0.7f);
    }
    public DripstoneProjectileEntity(EntityType<? extends DripstoneProjectileEntity> type, World world) {
        super(type, world);
    }


    public void tick(){
        super.tick();
    }
    protected void onEntityHit(EntityHitResult entityHitResult){
        if (!this.getWorld().isClient()){
            Entity entity = entityHitResult.getEntity();
            DamageSource damageSource = new DamageSource(
                    getWorld().getRegistryManager()
                            .get(RegistryKeys.DAMAGE_TYPE)
                            .entryOf(DamageTypes.FALLING_STALACTITE));
            entity.damage(damageSource, 12.0F);
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
