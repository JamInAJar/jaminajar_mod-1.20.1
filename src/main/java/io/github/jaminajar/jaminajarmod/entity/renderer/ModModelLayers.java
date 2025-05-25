package io.github.jaminajar.jaminajarmod.entity.renderer;

import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.util.Identifier;

public class ModModelLayers {
    public static final EntityModelLayer BAMBOO_PROJECTILE =
            new EntityModelLayer(new Identifier("jaminajarmod", "bamboo_projectile"), "main");
    public static final EntityModelLayer DRIPSTONE_PROJECTILE =
            new EntityModelLayer(new Identifier("jaminajarmod", "dripstone_projectile"), "main");

    public static void registerModelLayers() {
        EntityModelLayerRegistry.registerModelLayer(BAMBOO_PROJECTILE, BambooProjectileModel::getTexturedModelData);
        EntityModelLayerRegistry.registerModelLayer(DRIPSTONE_PROJECTILE, DripstoneProjectileModel::getTexturedModelData);
    }
}