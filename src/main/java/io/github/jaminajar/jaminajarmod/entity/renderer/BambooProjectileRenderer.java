package io.github.jaminajar.jaminajarmod.entity.renderer;

import io.github.jaminajar.jaminajarmod.entity.BambooProjectileEntity;
import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.EntityRenderer;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.util.Identifier;

import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.util.math.RotationAxis;

public class BambooProjectileRenderer extends EntityRenderer<BambooProjectileEntity> {
    private final BambooProjectileModel model;

    public BambooProjectileRenderer(EntityRendererFactory.Context context) {
        super(context);
        this.model = new BambooProjectileModel(context.getPart(ModModelLayers.BAMBOO_PROJECTILE));
    }

    @Override
    public void render(BambooProjectileEntity entity, float yaw, float tickDelta, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light) {
        matrices.push();

        matrices.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(entity.getYaw()));
        matrices.multiply(RotationAxis.POSITIVE_X.rotationDegrees(entity.getPitch()-90));

        // Get model texture
        Identifier texture = this.getTexture(entity);
        VertexConsumer vertexConsumer = vertexConsumers.getBuffer(RenderLayer.getEntityCutout(texture));

        // Render the model
        this.model.render(matrices, vertexConsumer, light, OverlayTexture.DEFAULT_UV, 1.0F, 1.0F, 1.0F, 1.0F);

        matrices.pop();
    }


    @Override
    public Identifier getTexture(BambooProjectileEntity entity) {
        return new Identifier("jaminajarmod", "textures/entity/bamboo_projectile.png");
    }
}

