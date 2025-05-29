package io.github.jaminajar.jaminajarmod.util;

import net.fabricmc.fabric.api.client.rendering.v1.BuiltinItemRendererRegistry;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.model.json.ModelTransformationMode;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.RotationAxis;

public class CacophonyItemRenderer implements BuiltinItemRendererRegistry.DynamicItemRenderer {
    @Override
    public void render(ItemStack stack, ModelTransformationMode mode,
                       MatrixStack matrices, VertexConsumerProvider vertexConsumers,
                       int light, int overlay) {
        MinecraftClient client = MinecraftClient.getInstance();
        PlayerEntity player = client.player;

        matrices.push();

        if (mode == ModelTransformationMode.THIRD_PERSON_RIGHT_HAND ||
                mode == ModelTransformationMode.THIRD_PERSON_LEFT_HAND) {
            matrices.translate(0.0F, 0.25F, 0.15F);
            matrices.multiply(RotationAxis.POSITIVE_X.rotationDegrees(15));
            matrices.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(-10));
        }

        client.getItemRenderer().renderItem(stack, mode, false, matrices,
                vertexConsumers, light, overlay,
                client.getItemRenderer().getModel(stack, null, player, 0));

        matrices.pop();
    }
}