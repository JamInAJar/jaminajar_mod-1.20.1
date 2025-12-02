package io.github.jaminajar.jaminajarmod.entity;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.world.World;

public class SoulerSoulGrenadeEntity extends SoulerSoulExplosiveEntity {

    public SoulerSoulGrenadeEntity(EntityType<SoulerSoulGrenadeEntity> type, LivingEntity owner, World world) {
        super(type, owner,world);
        this.setScalingMult(1);
        this.setSoulEnergyCount(1);
    }

    public SoulerSoulGrenadeEntity(EntityType<SoulerSoulGrenadeEntity> type, World world) {
        super(type,null,world);

    }
}