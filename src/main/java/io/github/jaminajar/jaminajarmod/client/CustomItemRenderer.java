package io.github.jaminajar.jaminajarmod.client;

import io.github.jaminajar.jaminajarmod.items.custom.BoomtubeItem;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;

public class CustomItemRenderer {
    private static final Identifier CHARGED_BAR_TEXTURE = new Identifier("jaminajar","textures/gui/charged_bar.png");
    public static void renderChargeBar(ItemStack stack, MatrixStack matrices, int x, int y){
        if (stack.getItem()instanceof BoomtubeItem){
            BoomtubeItem item = (BoomtubeItem) stack.getItem();
            int charge = item.getGunpowder(stack);
            int maxCharge=item.getMaxGunpowder();

            MinecraftClient.getInstance().getTextureManager().bindTexture(CHARGED_BAR_TEXTURE);
            drawTexturedBar(matrices,x,y,0,0,100,5);
            int chargeWidth = (int)(100*(charge/(float)maxCharge));
            drawTexturedBar(matrices,x,y,0,5,chargeWidth,5);
        }
    }
    private static void drawTexturedBar(MatrixStack matrices, int x, int y, int u, int v, int width, int height){

        MinecraftClient.getInstance().getBufferBuilders().getEntityVertexConsumers().getBuffer(RenderLayer.getEntitySolid(CHARGED_BAR_TEXTURE));
    }
}
