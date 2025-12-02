package io.github.jaminajar.jaminajarmod.entity.renderer;

import io.github.jaminajar.jaminajarmod.entity.SoulerSoulGrenadeEntity;
import net.minecraft.client.model.*;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.util.math.MatrixStack;

// Made with Blockbench 5.0.4
// Exported for Minecraft version 1.17+ for Yarn
// Paste this class into your mod and generate all required imports
public class SoulerSoulGrenadeEntityModel extends EntityModel<SoulerSoulGrenadeEntity> {
	private final ModelPart group;
	private final ModelPart bone;
	public SoulerSoulGrenadeEntityModel(ModelPart root) {
		this.group = root.getChild("group");
		this.bone = this.group.getChild("bone");
	}
	public static TexturedModelData getTexturedModelData() {
		ModelData modelData = new ModelData();
		ModelPartData modelPartData = modelData.getRoot();
		ModelPartData group = modelPartData.addChild("group", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 24.0F, -1.0F));

		ModelPartData cube_r1 = group.addChild("cube_r1", ModelPartBuilder.create().uv(0, 21).cuboid(-1.5F, -1.5F, 6.0F, 3.0F, 3.0F, 2.0F, new Dilation(-0.75F))
		.uv(16, 17).cuboid(-1.5F, -1.5F, 5.5F, 3.0F, 3.0F, 2.0F, new Dilation(-0.5F))
		.uv(10, 12).cuboid(-1.5F, -1.5F, 4.75F, 3.0F, 3.0F, 2.0F, new Dilation(0.0F))
		.uv(0, 0).cuboid(-1.5F, -1.5F, -3.75F, 3.0F, 3.0F, 9.0F, new Dilation(-0.25F))
		.uv(0, 12).cuboid(-1.5F, -1.5F, -5.25F, 3.0F, 3.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, 0.25F, 0.0F, 0.0F, 0.7854F));

		ModelPartData bone = group.addChild("bone", ModelPartBuilder.create().uv(0, 17).cuboid(-0.5F, -0.75F, -0.75F, 1.0F, 1.0F, 3.0F, new Dilation(-0.25F)), ModelTransform.of(0.0F, 0.0F, -5.6036F, 0.7854F, 0.0F, 0.0F));

		ModelPartData cube_r2 = bone.addChild("cube_r2", ModelPartBuilder.create().uv(8, 17).cuboid(-1.0F, -1.0F, -2.0F, 1.0F, 1.0F, 3.0F, new Dilation(-0.251F)), ModelTransform.of(0.5F, 0.25F, 0.25F, 1.5708F, 0.0F, 0.0F));
		return TexturedModelData.of(modelData, 32, 32);
	}
	@Override
	public void setAngles(SoulerSoulGrenadeEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
	}
	@Override
	public void render(MatrixStack matrices, VertexConsumer vertexConsumer, int light, int overlay, float red, float green, float blue, float alpha) {
		group.render(matrices, vertexConsumer, light, overlay, red, green, blue, alpha);
	}
}