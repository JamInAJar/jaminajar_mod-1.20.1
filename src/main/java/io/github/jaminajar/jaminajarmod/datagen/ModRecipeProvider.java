package io.github.jaminajar.jaminajarmod.datagen;

import io.github.jaminajar.jaminajarmod.items.ModItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.data.server.recipe.RecipeJsonProvider;
import net.minecraft.data.server.recipe.ShapedRecipeJsonBuilder;
import net.minecraft.data.server.recipe.ShapelessRecipeJsonBuilder;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.Items;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.registry.tag.ItemTags;

import java.util.List;
import java.util.function.Consumer;
import static io.github.jaminajar.jaminajarmod.items.ModItems.*;
import static net.minecraft.item.Items.*;

public class ModRecipeProvider extends FabricRecipeProvider {
    private static final List<ItemConvertible> FORGING_BLASTING = List.of(UNTREATED_BLADE);
    private static final List<ItemConvertible> FORGING_BLASTING2 = List.of(FORGED_BLADE);
    public ModRecipeProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generate(Consumer<RecipeJsonProvider> exporter) {


        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.COMFY_HANDLE,1)
                .pattern(" n ")
                .pattern("iwi")
                .pattern(" n ")
                .input('n', Items.NETHERITE_SCRAP)
                .input('i', Items.IRON_BARS)
                .input('w', Items.GRAY_WOOL)
                .criterion(hasItem(Items.NETHERITE_SCRAP),conditionsFromItem(Items.NETHERITE_SCRAP));
        ShapedRecipeJsonBuilder.create(RecipeCategory.COMBAT, ModItems.SOULER,1)
                .pattern(" rl")
                .pattern("csi")
                .pattern(" ng")
                .input('r', ModItems.RAVAGER_SCREW)
                .input('l',Items.LEVER)
                .input('c',Items.LIGHTNING_ROD)
                .input('s', ItemTags.SOUL_FIRE_BASE_BLOCKS)
                .input('i',Items.IRON_BLOCK)
                .input('n',Items.NETHERITE_SCRAP)
                .input('g',Items.IRON_INGOT);

        ShapedRecipeJsonBuilder.create(RecipeCategory.COMBAT, HELI_BLADE,1)
                .pattern("esr")
                .pattern("bhp")
                .pattern("esr")
                .input('e',REPEATER)
                .input('s',CRIMSON_BLADE)
                .input('r',RAVAGER_SCREW)
                .input('b',ItemTags.BUTTONS)
                .input('h',COMFY_HANDLE)
                .input('p',REDSTONE_BLOCK);

        offerBlasting(exporter, FORGING_BLASTING, RecipeCategory.MISC, ModItems.FORGED_BLADE,300f,6000, "forged_blade");
        offerBlasting(exporter, FORGING_BLASTING2, RecipeCategory.MISC, ModItems.INCANDESCENT_BLADE,300f,6000, "forged_blade");
        offerFoodCookingRecipe(exporter, "", RecipeSerializer.CAMPFIRE_COOKING,30, ModItems.MARSHMALLOW, ModItems.COOKED_MARSHMALLOW,0.3f);
        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.MACHETE,1)
                .pattern("  i")
                .pattern("li ")
                .pattern("nl ")
                .input('n', Items.IRON_NUGGET)
                .input('i', Items.IRON_BLOCK)
                .input('l', Items.LIGHTNING_ROD);
        ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC,ModItems.CRIMSON_BLADE,1)
                .input(REDSTONE_BLOCK,4)
                .input(PHANTOM_MEMBRANE,2)
                .input(INCANDESCENT_BLADE,1);
        offerStonecuttingRecipe(exporter, RecipeCategory.MISC, ModItems.RAVAGER_SCREW, ModItems.RAVAGER_TOOTH,2);
        //offerReversibleCompactingRecipes(exporter, RecipeCategory.FOOD, ModItems.MARSHMALLOW, RecipeCategory.BUILDING_BLOCKS,ModBlocks.MARSHMALLOW_SLAB);
        ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC,ModItems.SCISSOR_BLADE,1)
                .input(Items.BLUE_ICE,5)
                .input(Items.NETHERITE_INGOT,1)
                .input(Items.BLAZE_POWDER,2)
                .input(ModItems.FORGED_BLADE,1);
        ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, BLADE_CORE)
                        .input(BLAZE_ROD,1)
                                .input(DIAMOND,2);
        ShapelessRecipeJsonBuilder.create(RecipeCategory.FOOD, ModItems.MARSHMALLOW,3)
                        .input(Items.MILK_BUCKET,1)
                                .input(Items.SUGAR,2)
                                        .input(Items.SLIME_BALL,1);

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC,ModItems.SOLBRAND,1)
                .pattern("gbg")
                .pattern("gng")
                .pattern("srs")
                .input('b', INCANDESCENT_BLADE)
                .input('g',Items.TINTED_GLASS)
                .input('n',Items.NETHERITE_INGOT)
                .input('s',Items.NETHERITE_SCRAP)
                .input('r',Items.BLAZE_ROD);

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, UNTREATED_BLADE,1)
                .pattern("  n")
                .pattern(" i ")
                .pattern("b  ")
                .input('n', Items.NETHERITE_SCRAP)
                .input('i', Items.NETHERITE_INGOT)
                .input('b', BLADE_CORE);


        ShapedRecipeJsonBuilder.create(RecipeCategory.COMBAT, BOOM_TUBE,1)
                .pattern("pnp")
                .pattern("pnp")
                .pattern("pnp")
                .input('n', Items.NETHERITE_SCRAP)
                .input('p', Items.PAPER);
        ShapedRecipeJsonBuilder.create(RecipeCategory.COMBAT, CACOPHONY,1)
                .pattern("  n")
                .pattern("dg ")
                .pattern("bd ")
                .input('g', Items.GOAT_HORN)
                .input('d', Items.DIAMOND)
                .input('i', Items.NETHERITE_INGOT)
                .input('b', Items.BLAZE_ROD);
        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, SCULK_SENSOR,3)
                .pattern("   ")
                .pattern(" s ")
                .pattern("bbb")
                .input('s', ModItems.BIOSPEAKER)
                .input('b', Items.SCULK);

}}
