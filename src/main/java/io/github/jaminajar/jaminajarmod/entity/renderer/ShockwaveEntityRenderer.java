package io.github.jaminajar.jaminajarmod.entity.renderer;

import io.github.jaminajar.jaminajarmod.entity.ShockwaveEntity;
import io.github.jaminajar.jaminajarmod.entity.SoulerBeamProjectile;
import net.minecraft.client.render.entity.EntityRenderer;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.util.Identifier;

public class ShockwaveEntityRenderer extends EntityRenderer<ShockwaveEntity> {
    public ShockwaveEntityRenderer(EntityRendererFactory.Context context) {
        super(context);
    }

    @Override
    public Identifier getTexture(ShockwaveEntity entity) {
        return null;
    }




}
