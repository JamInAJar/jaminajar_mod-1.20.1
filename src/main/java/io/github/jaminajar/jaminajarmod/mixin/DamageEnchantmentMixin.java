package io.github.jaminajar.jaminajarmod.mixin;

import io.github.jaminajar.jaminajarmod.items.custom.PhantomFoilItem;
import net.minecraft.enchantment.DamageEnchantment;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(DamageEnchantment.class)
public abstract class DamageEnchantmentMixin {
    @Inject(method = "isAcceptableItem", at=@At("HEAD"),cancellable = true)
    private void blockDamageEnchantForPhantomFoil(ItemStack stack, CallbackInfoReturnable<Boolean> cir){
        if(stack.getItem() instanceof PhantomFoilItem){
            cir.setReturnValue(false);
        }
    }
}
