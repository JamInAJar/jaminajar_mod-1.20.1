package io.github.jaminajar.jaminajarmod;

//import io.github.jaminajar.jaminajarmod.block.renderer.SoulBombBlockEntityRenderer;
import io.github.jaminajar.jaminajarmod.client.HudRenderHandler;
import io.github.jaminajar.jaminajarmod.client.ModKeyBindings;
import io.github.jaminajar.jaminajarmod.entity.ModEntities;
//import io.github.jaminajar.jaminajarmod.entity.SoulerSoulBombEntity;
import io.github.jaminajar.jaminajarmod.entity.renderer.*;
import io.github.jaminajar.jaminajarmod.items.ModItems;
//import io.github.jaminajar.jaminajarmod.screen.SoulBombScreen;
import io.github.jaminajar.jaminajarmod.util.CacophonyItemRenderer;
import io.github.jaminajar.jaminajarmod.util.ModPackets;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.client.rendering.v1.*;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;


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
        EntityRendererRegistry.register(ModEntities.SHOCKWAVE, ShockwaveEntityRenderer::new);
        EntityRendererRegistry.register(ModEntities.ROCK_PROJECTILE, RockProjectileRenderer::new);
//        EntityRendererRegistry.register(ModEntities.SOUL_BOMB, SoulerSoulBombEntityRenderer::new);
//        BlockEntityRendererRegistry.register(ModBlockEntities.SOUL_BOMB_BLOCK_ENTITY, SoulBombBlockEntityRenderer::new);


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
//        BlockEntityRendererRegistry.register(ModBlockEntities.SOUL_BOMB_BLOCK_ENTITY, SoulBombBlockEntityRenderer::new);
        EntityModelLayerRegistry.registerModelLayer(
                ModModelLayers.SOUL_BOMB,
                SoulerSoulBombEntityModel::getTexturedModelData
        );
        EntityModelLayerRegistry.registerModelLayer(
                ModModelLayers.ROCK_PROJECTILE,
                RockProjectileModel::getTexturedModelData
        );
        BuiltinItemRendererRegistry.INSTANCE.register(ModItems.CACOPHONY, new CacophonyItemRenderer());
//        HandledScreens.register(ModScreenHandlers.SOUL_BOMB_SCREEN_HANDLER, SoulBombScreen::new);
        ModKeyBindings.register();
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            while (ModKeyBindings.SWITCHGRIP_KEY.wasPressed()) {
                if (client.player != null) {
                    ClientPlayNetworking.send(ModPackets.SWITCH_GRIP_ID,
                            PacketByteBufs.empty());
                }
            }
        });

    }
}

