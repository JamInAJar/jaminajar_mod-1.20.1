//package io.github.jaminajar.jaminajarmod.screen;
//
//import com.mojang.blaze3d.systems.RenderSystem;
//import io.github.jaminajar.jaminajarmod.JamInAJarMod;
//import net.minecraft.client.gui.DrawContext;
//import net.minecraft.client.gui.screen.ingame.HandledScreen;
//import net.minecraft.client.render.GameRenderer;
//import net.minecraft.entity.player.PlayerInventory;
//import net.minecraft.text.Text;
//import net.minecraft.util.Identifier;
//
//public class SoulBombScreen extends HandledScreen<SoulBombScreenHandler> {
//    private static final Identifier TEXTURE = new Identifier(JamInAJarMod.MOD_ID, "textures/gui/soul_bomb_gui.png");
//    public SoulBombScreen(SoulBombScreenHandler handler, PlayerInventory inventory, Text title) {
//        super(handler, inventory, title);
//    }
//
//    @Override
//    protected void init() {
//        super.init();
//    }
//
//    @Override
//    protected void drawBackground(DrawContext context, float delta, int mouseX, int mouseY) {
//        RenderSystem.setShader(GameRenderer::getPositionTexProgram);
//        RenderSystem.setShaderColor(1f,1f,1f,1f);
//        RenderSystem.setShaderTexture(0,TEXTURE);
//        int x = (width - backgroundWidth)/2;
//        int y = (height - backgroundHeight)/2;
//        context.drawTexture(TEXTURE,x,y,0,0,backgroundWidth,backgroundHeight);
//
//        renderProgressBar(context,x,y);
//    }
//    private void renderProgressBar(DrawContext context, int x, int y){
//        if(handler.isCrafting()){
//            context.drawTexture(TEXTURE,x+62,y+27,176,68,16,handler.getScaledProgress());
//        }
//    }
//
//    @Override
//    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
//        renderBackground(context);
//        super.render(context, mouseX, mouseY, delta);
//        drawMouseoverTooltip(context,mouseX,mouseY);
//    }
//}
