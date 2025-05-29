package io.github.jaminajar.jaminajarmod.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.data.server.recipe.RecipeJsonProvider;
import net.minecraft.data.server.recipe.ShapedRecipeJsonBuilder;
import net.minecraft.data.server.recipe.ShapelessRecipeJsonBuilder;
import net.minecraft.data.server.recipe.SmithingTransformRecipeJsonBuilder;
import net.minecraft.item.ItemConvertible;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.registry.tag.ItemTags;
import net.minecraft.util.Identifier;

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


        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, COMFY_HANDLE,1)
                .pattern(" n ")
                .pattern("iwi")
                .pattern(" n ")
                .input('n', NETHERITE_SCRAP)
                .input('i', IRON_BARS)
                .input('w', GRAY_WOOL)
                .criterion(hasItem(NETHERITE_SCRAP),conditionsFromItem(NETHERITE_SCRAP))
                .offerTo(exporter, new Identifier(getRecipeName(COMFY_HANDLE)));
        ShapedRecipeJsonBuilder.create(RecipeCategory.COMBAT, SOULER,1)
                .pattern(" rl")
                .pattern("csi")
                .pattern(" ng")
                .input('r', RAVAGER_SCREW)
                .input('l', LEVER)
                .input('c', LIGHTNING_ROD)
                .input('s', ItemTags.SOUL_FIRE_BASE_BLOCKS)
                .input('i', IRON_BLOCK)
                .input('n', NETHERITE_SCRAP)
                .input('g', IRON_INGOT)
                .criterion(hasItem(RAVAGER_SCREW),conditionsFromItem(RAVAGER_SCREW))
                .offerTo(exporter, new Identifier(getRecipeName(SOULER)));

        ShapedRecipeJsonBuilder.create(RecipeCategory.COMBAT, HELI_BLADE,1)
                .pattern("esr")
                .pattern("bhp")
                .pattern("esr")
                .input('e',REPEATER)
                .input('s',CRIMSON_BLADE)
                .input('r',RAVAGER_SCREW)
                .input('b',ItemTags.BUTTONS)
                .input('h',COMFY_HANDLE)
                .input('p',REDSTONE_BLOCK)
                .criterion(hasItem(CRIMSON_BLADE),conditionsFromItem(CRIMSON_BLADE))
                .offerTo(exporter, new Identifier(getRecipeName(HELI_BLADE)));

        offerBlasting(exporter, FORGING_BLASTING, RecipeCategory.MISC, FORGED_BLADE,300f,6000, "forged_blade");
        offerBlasting(exporter, FORGING_BLASTING2, RecipeCategory.MISC, INCANDESCENT_BLADE,300f,6000, "forged_blade");
        offerFoodCookingRecipe(exporter, "", RecipeSerializer.CAMPFIRE_COOKING,30, MARSHMALLOW, COOKED_MARSHMALLOW,0.3f);

        ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, CRIMSON_BLADE,1)
                .input(REDSTONE_BLOCK,4)
                .input(PHANTOM_MEMBRANE,2)
                .input(INCANDESCENT_BLADE,1)
                .criterion(hasItem(INCANDESCENT_BLADE),conditionsFromItem(INCANDESCENT_BLADE))
                .offerTo(exporter, new Identifier(getRecipeName(CRIMSON_BLADE)));
        offerStonecuttingRecipe(exporter, RecipeCategory.MISC, RAVAGER_SCREW, RAVAGER_TOOTH,2);
        //offerReversibleCompactingRecipes(exporter, RecipeCategory.FOOD, ModItems.MARSHMALLOW, RecipeCategory.BUILDING_BLOCKS,ModBlocks.MARSHMALLOW_SLAB);
        ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, SCISSOR_BLADE,1)
                .input(BLUE_ICE,5)
                .input(NETHERITE_INGOT,1)
                .input(BLAZE_POWDER,2)
                .input(FORGED_BLADE,1)
                .criterion(hasItem(NETHERITE_SCRAP),conditionsFromItem(FORGED_BLADE))
                .offerTo(exporter, new Identifier(getRecipeName(SCISSOR_BLADE)));
        ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, BLADE_CORE)
                        .input(BLAZE_ROD,1)
                        .input(DIAMOND,2)
                        .criterion(hasItem(BLAZE_ROD),conditionsFromItem(BLAZE_ROD))
                        .offerTo(exporter, new Identifier(getRecipeName(BLADE_CORE)));

        ShapelessRecipeJsonBuilder.create(RecipeCategory.FOOD, MARSHMALLOW,3)
                        .input(MILK_BUCKET,1)
                        .input(SUGAR,2)
                        .input(SLIME_BALL,1)
                        .criterion(hasItem(SLIME_BALL),conditionsFromItem(SLIME_BALL))
                        .offerTo(exporter, new Identifier(getRecipeName(MARSHMALLOW)));
        ShapelessRecipeJsonBuilder.create(RecipeCategory.COMBAT, MARSHMALLOW_STICK,1)
                .input(WOODEN_SWORD)
                .input(MARSHMALLOW,4)
                .input(IRON_INGOT,1)
                .criterion(hasItem(MARSHMALLOW),conditionsFromItem(MARSHMALLOW))
                .offerTo(exporter, new Identifier(getRecipeName(MARSHMALLOW_STICK)));
        offerFoodCookingRecipe(exporter, "", RecipeSerializer.CAMPFIRE_COOKING,30, MARSHMALLOW_STICK, COOKED_MARSHMALLOW_STICK,0.3f);
        ShapelessRecipeJsonBuilder.create(RecipeCategory.COMBAT, NETHERITE_MARSHMALLOW_STICK,1)
                .input(NETHERITE_SWORD)
                .input(MARSHMALLOW,4)
                .input(IRON_INGOT,1)
                .criterion(hasItem(MARSHMALLOW),conditionsFromItem(MARSHMALLOW))
                .offerTo(exporter, new Identifier(getRecipeName(NETHERITE_MARSHMALLOW_STICK)));
        offerFoodCookingRecipe(exporter, "", RecipeSerializer.CAMPFIRE_COOKING,300, NETHERITE_MARSHMALLOW_STICK, COOKED_NETHERITE_MARSHMALLOW_STICK,0.3f);
        offerNetheriteUpgradeRecipe(exporter,MARSHMALLOW_STICK, RecipeCategory.COMBAT,NETHERITE_MARSHMALLOW_STICK);
        offerNetheriteUpgradeRecipe(exporter,COOKED_MARSHMALLOW_STICK, RecipeCategory.COMBAT,COOKED_NETHERITE_MARSHMALLOW_STICK);


        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, SOLBRAND,1)
                .pattern("gbg")
                .pattern("gng")
                .pattern("srs")
                .input('b', INCANDESCENT_BLADE)
                .input('g', TINTED_GLASS)
                .input('n', NETHERITE_INGOT)
                .input('s', NETHERITE_SCRAP)
                .input('r', BLAZE_ROD)
                .criterion(hasItem(INCANDESCENT_BLADE),conditionsFromItem(INCANDESCENT_BLADE))
                .offerTo(exporter, new Identifier(getRecipeName(SOLBRAND)));
        ShapedRecipeJsonBuilder.create(RecipeCategory.COMBAT, PHANTOM_FOIL,1)
                .pattern(" p ")
                .pattern("pbp")
                .pattern("pdp")
                .input('p', PHANTOM_MEMBRANE)
                .input('b',BLAZE_ROD)
                .input('d',DIAMOND)
                .criterion(hasItem(PHANTOM_MEMBRANE),conditionsFromItem(PHANTOM_MEMBRANE))
                .offerTo(exporter, new Identifier(getRecipeName(PHANTOM_FOIL)));

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, UNTREATED_BLADE,1)
                .pattern("  n")
                .pattern(" i ")
                .pattern("b  ")
                .input('n', NETHERITE_SCRAP)
                .input('i', NETHERITE_INGOT)
                .input('b', BLADE_CORE)
                .criterion(hasItem(BLADE_CORE),conditionsFromItem(BLADE_CORE))
                .offerTo(exporter, new Identifier(getRecipeName(UNTREATED_BLADE)));


        ShapedRecipeJsonBuilder.create(RecipeCategory.COMBAT, BOOM_TUBE,1)
                .pattern("pnp")
                .pattern("pnp")
                .pattern("pnp")
                .input('n', NETHERITE_SCRAP)
                .input('p', PAPER)
                .criterion(hasItem(GUNPOWDER),conditionsFromItem(GUNPOWDER))
                .offerTo(exporter, new Identifier(getRecipeName(BOOM_TUBE)));
        ShapedRecipeJsonBuilder.create(RecipeCategory.COMBAT, CACOPHONY,1)
                .pattern("odo")
                .pattern("sgs")
                .pattern("ndn")
                .input('g', GOAT_HORN)
                .input('d', DIAMOND)
                .input('n', NETHERITE_INGOT)
                .input('s',BIOSPEAKER)
                .input('o',ECHO_SHARD)
                .criterion(hasItem(BIOSPEAKER),conditionsFromItem(BIOSPEAKER))
                .offerTo(exporter, new Identifier(getRecipeName(CACOPHONY)));
        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, SCULK_SHRIEKER,3)
                .pattern("   ")
                .pattern(" s ")
                .pattern("bbb")
                .input('s', BIOSPEAKER)
                .input('b', SCULK)
                .criterion(hasItem(SCULK),conditionsFromItem(SCULK))
                .offerTo(exporter, new Identifier(getRecipeName(SCULK_SHRIEKER)));
        ShapedRecipeJsonBuilder.create(RecipeCategory.COMBAT, BAMBOOZLER,1)
                .pattern("gie")
                .pattern("pbb")
                .pattern("li ")
                .input('p', PUFFERFISH)
                .input('b', BAMBOO)
                .input('l', BAMBOO_BLOCK)
                .input('g',GUNPOWDER)
                .input('i', IRON_INGOT)
                .input('e',SPIDER_EYE)
                .criterion(hasItem(PUFFERFISH),conditionsFromItem(PUFFERFISH))
                .offerTo(exporter, new Identifier(getRecipeName(BAMBOOZLER)));
        offerSmithingTemplateCopyingRecipe(exporter,DRIPSTONER_UPGRADE_TEMPLATE,DRIPSTONE_BLOCK);
        SmithingTransformRecipeJsonBuilder.create(
                        Ingredient.ofItems(DRIPSTONER_UPGRADE_TEMPLATE), // template
                        Ingredient.ofItems(BAMBOOZLER),          // base
                        Ingredient.ofItems(COPPER_BLOCK),
                        RecipeCategory.COMBAT,// addition
                        DRIPSTONER                           // result
                ).criterion(hasItem(DRIPSTONER_UPGRADE_TEMPLATE),conditionsFromItem(DRIPSTONER_UPGRADE_TEMPLATE))
                .offerTo(exporter, new Identifier(getRecipeName(DRIPSTONER)));

    }
}
