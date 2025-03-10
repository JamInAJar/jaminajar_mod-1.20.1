package io.github.jaminajar.jaminajarmod;

import io.github.jaminajar.jaminajarmod.client.HudRenderHandler;
import io.github.jaminajar.jaminajarmod.entity.ModEntities;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;

import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.client.render.entity.FlyingItemEntityRenderer;

public class JamInAJarModClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        EntityRendererRegistry.register(ModEntities.NOTE_PROJECTILE, FlyingItemEntityRenderer::new);
        HudRenderCallback.EVENT.register(new HudRenderHandler());
    }
}
