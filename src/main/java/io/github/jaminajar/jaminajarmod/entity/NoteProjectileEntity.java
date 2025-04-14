package io.github.jaminajar.jaminajarmod.entity;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.FlyingItemEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.network.listener.ClientPlayPacketListener;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.s2c.play.EntitySpawnS2CPacket;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.world.World;

public class NoteProjectileEntity extends PersistentProjectileEntity implements FlyingItemEntity {
    public NoteProjectileEntity(EntityType<? extends NoteProjectileEntity> entityType, World world, LivingEntity owner) {
        super(entityType, world);
        this.setOwner(owner);
        this.setPosition(owner.getX(), owner.getEyeY() - 0.1, owner.getZ());
    }
    public NoteProjectileEntity(World world, LivingEntity owner){
        super(ModEntities.NOTE_PROJECTILE, owner, world);
    }
    @Override
    protected void initDataTracker() {

    }

    @Override
    public Packet<ClientPlayPacketListener> createSpawnPacket(){
        return new EntitySpawnS2CPacket(this);
    }
    public void tick(){
        this.setNoGravity(true);
    }
    @Override
    protected void onEntityHit(EntityHitResult entityHitResult){
        if (!this.getWorld().isClient()){
            Entity target = entityHitResult.getEntity();
            Entity owner = this.getOwner();
            target.damage(this.getDamageSources().sonicBoom(this), 0.5F);
            if (target instanceof LivingEntity) {
                this.applyDamageEffects((LivingEntity)owner, target);
            }

        }

    }

    protected void onCollision(HitResult hitResult){
        super.onCollision(hitResult);
    }

    @Override
    protected ItemStack asItemStack() {
        return ItemStack.EMPTY;
    }

    @Override
    public ItemStack getStack() {
        return ItemStack.EMPTY;
    }
}
