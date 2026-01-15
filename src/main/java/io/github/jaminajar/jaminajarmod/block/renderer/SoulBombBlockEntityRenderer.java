//package io.github.jaminajar.jaminajarmod.block.renderer;
//
//import io.github.jaminajar.jaminajarmod.block.entity.SoulBombBlockEntity;
//import io.github.jaminajar.jaminajarmod.entity.renderer.ModModelLayers;
//import net.minecraft.client.model.ModelPart;
//import net.minecraft.client.render.RenderLayer;
//import net.minecraft.client.render.VertexConsumer;
//import net.minecraft.client.render.VertexConsumerProvider;
//import net.minecraft.client.render.block.entity.BlockEntityRenderer;
//import net.minecraft.client.render.block.entity.BlockEntityRendererFactory;
//import net.minecraft.client.util.math.MatrixStack;
//import net.minecraft.util.Identifier;
//
//public class SoulBombBlockEntityRenderer implements BlockEntityRenderer<SoulBombBlockEntity> {
//    private static final Identifier BASE_TEXTURE =
//            new Identifier("jaminajarmod", "textures/block/soul_bomb.png");
//
//    private static final Identifier[] UNDERLAY_TEXTURES = new Identifier[11];
//    static {
//        for (int i = 0; i <= 10; i++) {
//            UNDERLAY_TEXTURES[i] = new Identifier("jaminajarmod", "textures/block/soul_bomb_" + i + ".png");
//        }
//    }
//
//    private final ModelPart model;
//
//    public SoulBombBlockEntityRenderer(BlockEntityRendererFactory.Context ctx) {
//        this.model = ctx.getLayerModelPart(ModModelLayers.SOUL_BOMB);
//    }
//
//    @Override
//    public void render(SoulBombBlockEntity entity, float tickDelta, MatrixStack matrices,
//                       VertexConsumerProvider vertexConsumers, int light, int overlay) {
//        matrices.push();
//
//        int level = entity.getSoulEnergy();
//        for (int i = 0; i <= level; i++) {
//            VertexConsumer underlayConsumer = vertexConsumers.getBuffer(RenderLayer.getEntityTranslucent(UNDERLAY_TEXTURES[i]));
//            model.render(matrices, underlayConsumer, light, overlay);
//        }
//        VertexConsumer baseConsumer = vertexConsumers.getBuffer(RenderLayer.getEntityCutout(BASE_TEXTURE));
//        model.render(matrices, baseConsumer, light, overlay);
//
//        matrices.pop();
//    }
//}