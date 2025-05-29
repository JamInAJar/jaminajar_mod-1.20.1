package io.github.jaminajar.jaminajarmod.items.custom;

import io.github.jaminajar.jaminajarmod.entity.DripstoneProjectileEntity;
import io.github.jaminajar.jaminajarmod.entity.ModEntities;
import net.minecraft.item.Items;

public class DripstonerItem extends GunItem{
    public DripstonerItem(Settings settings) {
        super(settings, Items.POINTED_DRIPSTONE, 128,
                (world, user) -> new DripstoneProjectileEntity(ModEntities.DRIPSTONE_PROJECTILE, world, user),10,8f);
    }
}
