package io.github.jaminajar.jaminajarmod.entity;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.projectile.ProjectileEntity;
import net.minecraft.world.World;

public class SoulerBeamProjectile extends ProjectileEntity {
    public SoulerBeamProjectile(EntityType<? extends ProjectileEntity> entityType, World world) {
        super(entityType, world);
    }
    public void tick(){
        super.tick();
        this.setNoGravity(true);
    }
    @Override
    protected void initDataTracker() {

    }
}
