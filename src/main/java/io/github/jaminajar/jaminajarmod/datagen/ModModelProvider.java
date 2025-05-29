package io.github.jaminajar.jaminajarmod.datagen;

import io.github.jaminajar.jaminajarmod.items.ModItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.minecraft.data.client.BlockStateModelGenerator;
import net.minecraft.data.client.ItemModelGenerator;
import net.minecraft.data.client.Models;

public class ModModelProvider extends FabricModelProvider {
    public ModModelProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockStateModelGenerator blockStateModelGenerator) {
        ///blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.block);
        //BlockStateModelGenerator.BlockTexturePool marshmallowPool = blockStateModelGenerator.registerCubeAllModelTexturePool(ModBlocks.MARSHMALLOW_BLOCK);

        //marshmallowPool.slab((ModBlocks.MARSHMALLOW_SLAB));

    }

    @Override
    public void generateItemModels(ItemModelGenerator itemModelGenerator) {
        itemModelGenerator.register(ModItems.COMFY_HANDLE, Models.GENERATED);
        itemModelGenerator.register(ModItems.FORGED_BLADE, Models.GENERATED);
        itemModelGenerator.register(ModItems.RAVAGER_SCREW, Models.GENERATED);
        itemModelGenerator.register(ModItems.RAVAGER_TOOTH, Models.GENERATED);
        itemModelGenerator.register(ModItems.SCISSOR_BLADE, Models.GENERATED);
        itemModelGenerator.register(ModItems.UNTREATED_BLADE, Models.GENERATED);
        itemModelGenerator.register(ModItems.BIOSPEAKER,Models.GENERATED);
        itemModelGenerator.register(ModItems.INCANDESCENT_BLADE,Models.GENERATED);
        itemModelGenerator.register(ModItems.NOTE_PROJECTILE,Models.GENERATED);
        itemModelGenerator.register(ModItems.MARSHMALLOW,Models.GENERATED);
        itemModelGenerator.register(ModItems.COOKED_MARSHMALLOW,Models.GENERATED);
        itemModelGenerator.register(ModItems.BOOM_TUBE,Models.GENERATED);
        itemModelGenerator.register(ModItems.SOLBRAND,Models.GENERATED);
        itemModelGenerator.register(ModItems.BLADE_CORE,Models.GENERATED);
        itemModelGenerator.register(ModItems.CACOPHONY,Models.GENERATED);
        itemModelGenerator.register(ModItems.CRIMSON_BLADE,Models.GENERATED);
        itemModelGenerator.register(ModItems.MARSHMALLOW_STICK,Models.GENERATED);
        itemModelGenerator.register(ModItems.COOKED_MARSHMALLOW_STICK,Models.GENERATED);
        itemModelGenerator.register(ModItems.NETHERITE_MARSHMALLOW_STICK,Models.GENERATED);
        itemModelGenerator.register(ModItems.COOKED_NETHERITE_MARSHMALLOW_STICK,Models.GENERATED);
        itemModelGenerator.register(ModItems.DRIPSTONER_UPGRADE_TEMPLATE, Models.GENERATED);
        itemModelGenerator.register(ModItems.EMPTY_SOUL_CANISTER, Models.GENERATED);
        itemModelGenerator.register(ModItems.FULL_SOUL_CANISTER, Models.GENERATED);
    }
}
