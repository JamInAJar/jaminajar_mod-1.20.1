package io.github.jaminajar.jaminajarmod.mixin;


import io.github.jaminajar.jaminajarmod.items.custom.BamboozlerItem;
import io.github.jaminajar.jaminajarmod.items.custom.BoomtubeItem;
import io.github.jaminajar.jaminajarmod.items.custom.CacophonyItem;
import io.github.jaminajar.jaminajarmod.items.custom.DripstonerItem;
import io.github.jaminajar.jaminajarmod.items.soul.SoulerItem;
import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.client.render.entity.PlayerEntityRenderer;
import net.minecraft.client.render.entity.model.BipedEntityModel;
import net.minecraft.client.render.entity.model.BipedEntityModel.ArmPose;
import net.minecraft.util.Hand;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(PlayerEntityRenderer.class)
public class PlayerEntityRendererMixin {
    @Inject(
            method = "getArmPose(Lnet/minecraft/client/network/AbstractClientPlayerEntity;Lnet/minecraft/util/Hand;)Lnet/minecraft/client/render/entity/model/BipedEntityModel$ArmPose;",
            at = @At("TAIL"),
            cancellable = true
    )
    private static void twoHandedHold(AbstractClientPlayerEntity player, Hand hand, CallbackInfoReturnable<BipedEntityModel.ArmPose> ci) {
        if(player.getStackInHand(hand).getItem() instanceof CacophonyItem) {
            ci.setReturnValue(ArmPose.CROSSBOW_HOLD);
            ci.cancel();
        }
        if(player.getStackInHand(hand).getItem() instanceof BoomtubeItem) {
            ci.setReturnValue(ArmPose.CROSSBOW_HOLD);
            ci.cancel();
        }
        if(player.getStackInHand(hand).getItem() instanceof BamboozlerItem) {
            ci.setReturnValue(ArmPose.CROSSBOW_HOLD);
            ci.cancel();
        }
        if(player.getStackInHand(hand).getItem() instanceof DripstonerItem) {
            ci.setReturnValue(ArmPose.CROSSBOW_HOLD);
            ci.cancel();
        }
        if(player.getStackInHand(hand).getItem() instanceof SoulerItem) {
            ci.setReturnValue(ArmPose.CROSSBOW_HOLD);
            ci.cancel();
        }
    }
}
//thx for the code DaFuqs