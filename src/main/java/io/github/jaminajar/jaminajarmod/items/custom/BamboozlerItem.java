package io.github.jaminajar.jaminajarmod.items.custom;

import io.github.jaminajar.jaminajarmod.entity.BambooProjectileEntity;
import io.github.jaminajar.jaminajarmod.entity.ModEntities;
import net.minecraft.item.Items;

public class BamboozlerItem extends GunItem {
    public BamboozlerItem(Settings settings) {
        super(settings, Items.BAMBOO, 128,
                (world, user) -> new BambooProjectileEntity(ModEntities.BAMBOO_PROJECTILE, world, user),15,2.5f);
    }

}
