package io.github.jaminajar.jaminajarmod.client;

import io.github.jaminajar.jaminajarmod.items.custom.BoomtubeItem;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.item.ItemStack;

public class HudRenderHandler implements HudRenderCallback {


    public void onHudRender(MatrixStack matrices, float tickDelta) {
        MinecraftClient client = MinecraftClient.getInstance();
        if (client.player != null) {
            for (ItemStack stack : client.player.getInventory().main) {
                if (stack.getItem() instanceof BoomtubeItem) {
                    int x = 50;
                    int y = 50;
                    CustomItemRenderer.renderChargeBar(stack, matrices, x, y);
                }
            }
        }
    }

    @Override
    public void onHudRender(DrawContext drawContext, float v) {

    }
}
