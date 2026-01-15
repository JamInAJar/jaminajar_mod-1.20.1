package io.github.jaminajar.jaminajarmod.block.entity;

import io.github.jaminajar.jaminajarmod.JamInAJarMod;

public class ModBlockEntities {
//    public static final BlockEntityType<SoulBombBlockEntity> SOUL_BOMB_BLOCK_ENTITY =
//            Registry.register(Registries.BLOCK_ENTITY_TYPE, new Identifier(JamInAJarMod.MOD_ID,"soul_bomb"),
//                    FabricBlockEntityTypeBuilder.create(SoulBombBlockEntity::new,
//                    ModBlocks.SOUL_BOMB).build());
    public static void registerBlockEntities(){
        JamInAJarMod.LOGGER.info("Registering Block Entities for"+JamInAJarMod.MOD_ID);
    }
}
