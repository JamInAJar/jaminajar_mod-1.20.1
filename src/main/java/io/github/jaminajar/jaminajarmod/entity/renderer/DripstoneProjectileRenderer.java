package io.github.jaminajar.jaminajarmod.entity.renderer;

import io.github.jaminajar.jaminajarmod.entity.BambooProjectileEntity;
import io.github.jaminajar.jaminajarmod.entity.DripstoneProjectileEntity;
import net.minecraft.client.render.entity.EntityRenderer;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.util.Identifier;

import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.client.render.VertexConsumerProvider;

public class DripstoneProjectileRenderer extends EntityRenderer<DripstoneProjectileEntity> {
    private final DripstoneProjectileModel model;

    public DripstoneProjectileRenderer(EntityRendererFactory.Context context) {
        super(context);
        this.model = new DripstoneProjectileModel(context.getPart(ModModelLayers.DRIPSTONE_PROJECTILE));
    }

    @Override
    public void render(DripstoneProjectileEntity entity, float yaw, float tickDelta, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light) {
    }

    @Override
    public Identifier getTexture(DripstoneProjectileEntity entity) {
        return new Identifier("jaminajarmod", "textures/entity/dripstone_projectile.png");
    }
}

