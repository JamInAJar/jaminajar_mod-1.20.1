package io.github.jaminajar.jaminajarmod.entity;

import io.github.jaminajar.jaminajarmod.entity.damage.ModDamageTypes;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.world.World;

public class SoulerBeamProjectile extends PersistentProjectileEntity {
    public SoulerBeamProjectile(EntityType<? extends SoulerBeamProjectile> entityType, World world, LivingEntity owner) {
        super(entityType, world);
        this.setOwner(owner);
        this.setPosition(owner.getX(), owner.getEyeY() - 0.1, owner.getZ());
    }
    public void tick(){
        super.tick();
        this.setNoGravity(true);
    }
    @Override
    protected void initDataTracker() {

    }

    protected void onEntityHit(EntityHitResult entityHitResult){
        Entity owner = this.getOwner();
        Entity target = entityHitResult.getEntity();
        if(owner instanceof LivingEntity shooter) {
            DamageSource damageSource = new DamageSource(
                    getWorld().getRegistryManager()
                            .get(RegistryKeys.DAMAGE_TYPE)
                            .entryOf(ModDamageTypes.SOULER_LASERED_DAMAGE));
            target.damage(damageSource, 10.0F);
            /// hud display???
        }
    }
    @Override
    protected ItemStack asItemStack() {
        return ItemStack.EMPTY;
    }
}
