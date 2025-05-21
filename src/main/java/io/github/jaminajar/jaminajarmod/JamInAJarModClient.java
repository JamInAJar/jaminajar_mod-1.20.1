package io.github.jaminajar.jaminajarmod;

import io.github.jaminajar.jaminajarmod.client.HudRenderHandler;
import io.github.jaminajar.jaminajarmod.entity.ModEntities;
import io.github.jaminajar.jaminajarmod.entity.renderer.HonkProjectileEntityRenderer;
import io.github.jaminajar.jaminajarmod.entity.renderer.SoulerBeamProjectileRenderer;
import io.github.jaminajar.jaminajarmod.entity.renderer.SoulerSoulEntityRenderer;
import io.github.jaminajar.jaminajarmod.entity.renderer.NoteProjectileEntityRenderer;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;



public class JamInAJarModClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        EntityRendererRegistry.register(ModEntities.NOTE_PROJECTILE, NoteProjectileEntityRenderer::new);
        EntityRendererRegistry.register(ModEntities.SOULER_SOUL_PROJECTILE, SoulerSoulEntityRenderer::new);
        EntityRendererRegistry.register(ModEntities.SOULER_BEAM_PROJECTILE, SoulerBeamProjectileRenderer::new);
        EntityRendererRegistry.register(ModEntities.HONK_PROJECTILE, HonkProjectileEntityRenderer::new);
        HudRenderCallback.EVENT.register(new HudRenderHandler());
    }
}
