package io.github.jaminajar.jaminajarmod.entity.renderer;

import io.github.jaminajar.jaminajarmod.entity.RockProjectileEntity;
import net.minecraft.client.model.*;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.util.math.MatrixStack;

// Made with Blockbench 5.0.4
// Exported for Minecraft version 1.17+ for Yarn
// Paste this class into your mod and generate all required imports
public class RockProjectileModel extends EntityModel<RockProjectileEntity> {
	private final ModelPart bb_main;
	public RockProjectileModel(ModelPart root) {
		this.bb_main = root.getChild("bb_main");
	}
	public static TexturedModelData getTexturedModelData() {
		ModelData modelData = new ModelData();
		ModelPartData modelPartData = modelData.getRoot();
		ModelPartData bb_main = modelPartData.addChild("bb_main", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 0.0F, 0.0F));

		ModelPartData cube_r1 = bb_main.addChild("cube_r1", ModelPartBuilder.create().uv(16, 0).cuboid(-0.9371F, -1.667F, -1.333F, 4.0F, 4.0F, 4.0F, new Dilation(-0.1F))
		.uv(0, 16).cuboid(-2.1083F, -1.9373F, -2.8305F, 4.0F, 4.0F, 4.0F, new Dilation(-0.1F)), ModelTransform.of(0.0F, 0.0F, 0.0F, -0.7854F, 0.0F, 0.3927F));

		ModelPartData cube_r2 = bb_main.addChild("cube_r2", ModelPartBuilder.create().uv(0, 8).cuboid(-1.9444F, -1.6815F, -2.0592F, 4.0F, 4.0F, 4.0F, new Dilation(0.1F)), ModelTransform.of(0.0F, 0.0F, 0.0F, -0.7543F, 0.2443F, 1.0373F));

		ModelPartData cube_r3 = bb_main.addChild("cube_r3", ModelPartBuilder.create().uv(0, 0).cuboid(-2.6056F, -1.0366F, -2.0366F, 4.0F, 4.0F, 4.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, 0.0F, 0.7854F, 0.0F, -0.7854F));
		return TexturedModelData.of(modelData, 32, 32);
	}
	@Override
	public void setAngles(RockProjectileEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
	}
	@Override
	public void render(MatrixStack matrices, VertexConsumer vertexConsumer, int light, int overlay, float red, float green, float blue, float alpha) {
		bb_main.render(matrices, vertexConsumer, light, overlay, red, green, blue, alpha);
	}
}