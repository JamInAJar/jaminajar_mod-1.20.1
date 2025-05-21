package io.github.jaminajar.jaminajarmod.entity.renderer;

import io.github.jaminajar.jaminajarmod.entity.NoteProjectileEntity;
import io.github.jaminajar.jaminajarmod.entity.SoulerBeamProjectile;
import net.minecraft.client.render.entity.EntityRenderer;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.util.Identifier;

public class NoteProjectileEntityRenderer extends EntityRenderer<NoteProjectileEntity> {
    public NoteProjectileEntityRenderer(EntityRendererFactory.Context context) {
        super(context);
    }

    @Override
    public Identifier getTexture(NoteProjectileEntity entity) {
        return null;
    }




}
