package io.github.jaminajar.jaminajarmod.mixin;

import io.github.jaminajar.jaminajarmod.JamInAJarMod;
import io.github.jaminajar.jaminajarmod.items.ModItems;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.item.ItemRenderer;
import net.minecraft.client.render.model.BakedModel;
import net.minecraft.client.render.model.json.ModelTransformationMode;
import net.minecraft.client.util.ModelIdentifier;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Mixin(ItemRenderer.class)
public abstract class ItemRendererMixin {
    @ModifyVariable(method="renderItem",at=@At(value="HEAD"),argsOnly=true)
    public BakedModel useBoomtubeModel(BakedModel value, ItemStack stack, ModelTransformationMode renderMode, boolean leftHanded, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, int overlay){
        if(stack.isOf(ModItems.BOOM_TUBE)&&renderMode != ModelTransformationMode.GUI){
            return ((ItemRendererAccessor)this).mccourse$getModels().getModelManager().getModel(new ModelIdentifier(JamInAJarMod.MOD_ID,"boom_tube_3d","inventory"));
        }
        if(stack.isOf(ModItems.HELI_BLADE)){
            return ((ItemRendererAccessor)this).mccourse$getModels().getModelManager().getModel(new ModelIdentifier(JamInAJarMod.MOD_ID,"heli_blade_3d","inventory"));
        }
        if(stack.isOf(ModItems.SOULER)){
            return ((ItemRendererAccessor)this).mccourse$getModels().getModelManager().getModel(new ModelIdentifier(JamInAJarMod.MOD_ID,"souler","inventory"));
        }
        if(stack.isOf(ModItems.EMPTY_SOUL_CANISTER)){
            return ((ItemRendererAccessor)this).mccourse$getModels().getModelManager().getModel(new ModelIdentifier(JamInAJarMod.MOD_ID,"empty_soul_canister_3d","inventory"));
        }
        if(stack.isOf(ModItems.FULL_SOUL_CANISTER)){
            return ((ItemRendererAccessor)this).mccourse$getModels().getModelManager().getModel(new ModelIdentifier(JamInAJarMod.MOD_ID,"full_soul_canister_3d","inventory"));
        }
        if(stack.isOf(ModItems.EMPTY_SOUL_GRENADE)){
            return ((ItemRendererAccessor)this).mccourse$getModels().getModelManager().getModel(new ModelIdentifier(JamInAJarMod.MOD_ID,"empty_soul_grenade_3d","inventory"));
        }
        if(stack.isOf(ModItems.FULL_SOUL_GRENADE)){
            return ((ItemRendererAccessor)this).mccourse$getModels().getModelManager().getModel(new ModelIdentifier(JamInAJarMod.MOD_ID,"full_soul_grenade_3d","inventory"));
        }
        if(stack.isOf(ModItems.BAMBOOZLER)){
            return ((ItemRendererAccessor)this).mccourse$getModels().getModelManager().getModel(new ModelIdentifier(JamInAJarMod.MOD_ID,"bamboozler_3d","inventory"));
        }
        if(stack.isOf(ModItems.DRIPSTONER)){
            return ((ItemRendererAccessor)this).mccourse$getModels().getModelManager().getModel(new ModelIdentifier(JamInAJarMod.MOD_ID,"dripstoner","inventory"));
        }
        if(stack.isOf(ModItems.CACOPHONY)){
            return ((ItemRendererAccessor)this).mccourse$getModels().getModelManager().getModel(new ModelIdentifier(JamInAJarMod.MOD_ID,"cacophony_3d","inventory"));
        }
        return value;
    }
}
