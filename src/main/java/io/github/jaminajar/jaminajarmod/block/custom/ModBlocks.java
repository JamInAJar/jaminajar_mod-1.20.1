package io.github.jaminajar.jaminajarmod.block.custom;

import io.github.jaminajar.jaminajarmod.JamInAJarMod;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.MapColor;
import net.minecraft.block.enums.Instrument;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;

public class ModBlocks {
    //public static final Block MARSHMALLOW_BLOCK = registerBlock("marshmallow_block",new MarshmallowBlock(FabricBlockSettings.copyOf(Blocks.HONEY_BLOCK)));
    //public static final Block MARSHMALLOW_SLAB = registerBlock("marshmallow_slab",new MarshmallowSlabBlock(FabricBlockSettings.copyOf(Blocks.HONEY_BLOCK)));
    ///public static final Block SOUL_BOMB = registerBlock("soul_bomb",new SoulBombBlock(FabricBlockSettings.copyOf(Blocks.TNT)));
    public static final Block SOULSTEEL_BLOCK = registerBlock("soulsteel_block",new Block(FabricBlockSettings.create()
            .mapColor(MapColor.DEEPSLATE_GRAY)
            .instrument(Instrument.IRON_XYLOPHONE)
            .requiresTool()
            .strength(50.0F, 100.0F)
            .sounds(BlockSoundGroup.METAL)
            .luminance(1)));

    private static Block registerBlock(String name, Block block){
        registerBlockItem(name, block);
        return Registry.register(Registries.BLOCK, new Identifier(JamInAJarMod.MOD_ID,name),block);
    }
    private static Item registerBlockItem(String name, Block block){
        return Registry.register(Registries.ITEM, new Identifier(JamInAJarMod.MOD_ID,name),
                new BlockItem(block, new FabricItemSettings()));
    }
    public static void registerModBlocks(){
        JamInAJarMod.LOGGER.info("Registering ModBlocks for "+JamInAJarMod.MOD_ID);
    }
}
