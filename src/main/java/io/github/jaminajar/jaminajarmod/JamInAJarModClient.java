package io.github.jaminajar.jaminajarmod;

import io.github.jaminajar.jaminajarmod.client.HudRenderHandler;
import io.github.jaminajar.jaminajarmod.entity.ModEntities;
import io.github.jaminajar.jaminajarmod.entity.renderer.*;
import io.github.jaminajar.jaminajarmod.items.ModItems;
import io.github.jaminajar.jaminajarmod.util.CacophonyItemRenderer;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.BuiltinItemRendererRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;



public class JamInAJarModClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        EntityRendererRegistry.register(ModEntities.NOTE_PROJECTILE, NoteProjectileEntityRenderer::new);
        EntityRendererRegistry.register(ModEntities.SOULER_SOUL_PROJECTILE, SoulerSoulEntityRenderer::new);
        EntityRendererRegistry.register(ModEntities.SOULER_BEAM_PROJECTILE, SoulerBeamProjectileRenderer::new);
        EntityRendererRegistry.register(ModEntities.HONK_PROJECTILE, HonkProjectileEntityRenderer::new);
        EntityRendererRegistry.register(ModEntities.BAMBOO_PROJECTILE, BambooProjectileRenderer::new);
        EntityRendererRegistry.register(ModEntities.DRIPSTONE_PROJECTILE, DripstoneProjectileRenderer::new);
        EntityRendererRegistry.register(ModEntities.SOUL_GRENADE, SoulerSoulGrenadeEntityRenderer::new);
        HudRenderCallback.EVENT.register(new HudRenderHandler());
        EntityModelLayerRegistry.registerModelLayer(
                ModModelLayers.DRIPSTONE_PROJECTILE,
                DripstoneProjectileModel::getTexturedModelData
        );
        EntityModelLayerRegistry.registerModelLayer(
                ModModelLayers.BAMBOO_PROJECTILE,
                BambooProjectileModel::getTexturedModelData
        );
        EntityModelLayerRegistry.registerModelLayer(
                ModModelLayers.SOUL_GRENADE,
                SoulerSoulGrenadeEntityModel::getTexturedModelData
        );
        BuiltinItemRendererRegistry.INSTANCE.register(ModItems.CACOPHONY, new CacophonyItemRenderer());

    }
}

