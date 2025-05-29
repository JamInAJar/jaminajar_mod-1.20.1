package io.github.jaminajar.jaminajarmod.entity;

import io.github.jaminajar.jaminajarmod.entity.damage.ModDamageTypes;
import io.github.jaminajar.jaminajarmod.util.CombatUtils;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;

import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.item.ItemStack;
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
        if (this.age>200){
            this.discard();}
    }
    protected void onEntityHit(EntityHitResult entityHitResult){
        if (!this.getWorld().isClient()){
            Entity entity = entityHitResult.getEntity();
            Entity owner = this.getOwner();
            DamageSource damageSource = new DamageSource(
                    getWorld().getRegistryManager()
                            .get(RegistryKeys.DAMAGE_TYPE)
                            .entryOf(ModDamageTypes.DRIPSTONE_PROJECTILE),owner);
            if(entity instanceof LivingEntity livingTarget){
                CombatUtils.damageIgnoringIFrames(livingTarget,damageSource,25.0f);
            } else {
                entity.damage(damageSource,25.0f);
            }
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
