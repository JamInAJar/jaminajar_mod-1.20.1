package io.github.jaminajar.jaminajarmod.entity.renderer;


import io.github.jaminajar.jaminajarmod.entity.RockProjectileEntity;
import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.EntityRenderer;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.util.Identifier;

import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.util.math.RotationAxis;

public class RockProjectileRenderer extends EntityRenderer<RockProjectileEntity> {
    private final RockProjectileModel model;

    public RockProjectileRenderer(EntityRendererFactory.Context context) {
        super(context);
        this.model = new RockProjectileModel(context.getPart(ModModelLayers.ROCK_PROJECTILE));
    }

    @Override
    public void render(RockProjectileEntity entity, float yaw, float tickDelta, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light) {
        matrices.push();

        // Rotate to match entity orientation
        matrices.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(entity.getYaw()));
        matrices.multiply(RotationAxis.POSITIVE_X.rotationDegrees(entity.getPitch() - 90));
        matrices.scale(4,4,4);
        // Get texture and vertex consumer
        Identifier texture = this.getTexture(entity);
        VertexConsumer vertexConsumer = vertexConsumers.getBuffer(RenderLayer.getEntityCutout(texture));

        // Render the model
        this.model.render(matrices, vertexConsumer, light, OverlayTexture.DEFAULT_UV, 1.0F, 1.0F, 1.0F, 1.0F);

        matrices.pop();
    }

    @Override
    public Identifier getTexture(RockProjectileEntity entity) {
        return new Identifier("jaminajarmod", "textures/entity/rock.png");
    }
}

