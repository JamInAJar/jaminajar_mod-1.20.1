//package io.github.jaminajar.jaminajarmod.entity.renderer;
//
//import io.github.jaminajar.jaminajarmod.entity.SoulerSoulBombEntity;
//import net.minecraft.client.render.OverlayTexture;
//import net.minecraft.client.render.RenderLayer;
//import net.minecraft.client.render.VertexConsumer;
//import net.minecraft.client.render.VertexConsumerProvider;
//import net.minecraft.client.render.entity.EntityRenderer;
//import net.minecraft.client.render.entity.EntityRendererFactory;
//import net.minecraft.client.util.math.MatrixStack;
//import net.minecraft.util.Identifier;
//
//public class SoulerSoulBombEntityRenderer extends EntityRenderer<SoulerSoulBombEntity> {
//    private static final Identifier BASE_TEXTURE =
//            new Identifier("jaminajarmod", "textures/entity/soul_bomb.png");
//
//    private static final Identifier[] UNDERLAY_TEXTURES = new Identifier[11];
//    static {
//        for (int i = 0; i <= 10; i++) {
//            UNDERLAY_TEXTURES[i] = new Identifier("jaminajarmod", "textures/entity/soul_bomb_" + i + ".png");
//        }
//    }
//
//    private final SoulerSoulBombEntityModel model;
//
//    public SoulerSoulBombEntityRenderer(EntityRendererFactory.Context ctx) {
//        super(ctx);
//        this.model = new SoulerSoulBombEntityModel(ctx.getPart(ModModelLayers.SOUL_BOMB));
//    }
//
//    @Override
//    public void render(SoulerSoulBombEntity entity, float yaw, float tickDelta,
//                       MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light) {
//        matrices.push();
//
//        // Position model correctly at entity origin
//        matrices.translate(0.0, 1.5, 0.0);
//        matrices.scale(1.0F, -1.0F, -1.0F);
//
//        int level = entity.soulEnergy;
//
//        // --- Base drawn first (opaque cutout layer) ---
//        VertexConsumer baseConsumer =
//                vertexConsumers.getBuffer(RenderLayer.getEntityCutout(BASE_TEXTURE));
//        model.render(matrices, baseConsumer, light, OverlayTexture.DEFAULT_UV,
//                1.0f, 1.0f, 1.0f, 1.0f);
//
//        // --- Then overlays (translucent, respect per‑pixel alpha) ---
//        for (int i = 0; i <= level; i++) {
//            VertexConsumer underlayConsumer =
//                    vertexConsumers.getBuffer(RenderLayer.getEntityTranslucent(UNDERLAY_TEXTURES[i]));
//            model.render(matrices, underlayConsumer, light, OverlayTexture.DEFAULT_UV,
//                    1.0f, 1.0f, 1.0f, 1.0f); // alpha=1.0f preserves PNG transparency
//        }
//
//        matrices.pop();
//        super.render(entity, yaw, tickDelta, matrices, vertexConsumers, light);
//    }
//
//    @Override
//    public Identifier getTexture(SoulerSoulBombEntity entity) {
//        return BASE_TEXTURE;
//    }
//}