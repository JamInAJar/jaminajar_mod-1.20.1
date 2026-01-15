package io.github.jaminajar.jaminajarmod.entity;

import net.minecraft.entity.EntityType;
import net.minecraft.world.World;

public class SoulerSoulGrenadeEntity extends SoulerSoulExplosiveEntity {

    public SoulerSoulGrenadeEntity(EntityType<SoulerSoulGrenadeEntity> type, World world) {
        super(type, world);
        this.setScalingMult(1.5f);
        this.setSoulEnergyCount(1);
    }
}