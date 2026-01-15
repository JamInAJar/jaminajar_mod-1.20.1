package io.github.jaminajar.jaminajarmod.mixin;

import io.github.jaminajar.jaminajarmod.items.custom.*;
import io.github.jaminajar.jaminajarmod.items.soul.*;
import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.client.render.entity.PlayerEntityRenderer;
import net.minecraft.client.render.entity.model.BipedEntityModel;
import net.minecraft.util.Hand;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(PlayerEntityRenderer.class)
public class PlayerArmPoseMixin {

    @Inject(
            method = "getArmPose(Lnet/minecraft/client/network/AbstractClientPlayerEntity;Lnet/minecraft/util/Hand;)Lnet/minecraft/client/render/entity/model/BipedEntityModel$ArmPose;",
            at = @At("TAIL"),
            cancellable = true
    )
    private static void twoHandedHold(
            AbstractClientPlayerEntity player,
            Hand hand,
            CallbackInfoReturnable<BipedEntityModel.ArmPose> cir
    ) {
        var item = player.getStackInHand(hand).getItem();

        if (item instanceof CacophonyItem
                || item instanceof BoomtubeItem
                || item instanceof BamboozlerItem
                || item instanceof DripstonerItem
                || item instanceof SoulerItem
                || item instanceof GigatonHammerItem) {

            cir.setReturnValue(BipedEntityModel.ArmPose.CROSSBOW_HOLD);
        }
    }
}