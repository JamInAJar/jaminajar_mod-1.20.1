package io.github.jaminajar.jaminajarmod.items.soul;

import io.github.jaminajar.jaminajarmod.entity.ModEntities;
import io.github.jaminajar.jaminajarmod.entity.SoulerSoulGrenadeEntity;




public class SoulGrenadeItem extends SoulExplosiveItem{
    public SoulGrenadeItem(Settings settings, int fullness, int capacity) {
        super(settings, fullness, capacity, (world, user) -> new SoulerSoulGrenadeEntity(ModEntities.SOUL_GRENADE, world));
    }
}
