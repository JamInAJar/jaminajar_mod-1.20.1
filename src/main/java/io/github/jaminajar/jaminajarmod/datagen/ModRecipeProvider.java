package io.github.jaminajar.jaminajarmod.datagen;

import io.github.jaminajar.jaminajarmod.items.ModItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.block.SculkSensorBlock;
import net.minecraft.data.server.recipe.RecipeJsonProvider;
import net.minecraft.data.server.recipe.ShapedRecipeJsonBuilder;
import net.minecraft.data.server.recipe.ShapelessRecipeJsonBuilder;
import net.minecraft.item.Item;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.Items;
import net.minecraft.recipe.StonecuttingRecipe;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.util.Identifier;

import java.util.List;
import java.util.function.Consumer;

import static io.github.jaminajar.jaminajarmod.items.ModItems.*;
import static net.minecraft.item.Items.*;

public class ModRecipeProvider extends FabricRecipeProvider {
    private static final List<ItemConvertible> FORGING_BLASTING = List.of(UNTREATED_SCISSOR_BLADE);
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

        offerBlasting(exporter, FORGING_BLASTING, RecipeCategory.MISC, ModItems.FORGED_BLADE,3000f,6000, "forged_blade");

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.MACHETE,1)
                .pattern("  i")
                .pattern("li ")
                .pattern("nl ")
                .input('n', Items.IRON_NUGGET)
                .input('i', Items.IRON_BLOCK)
                .input('l', Items.LIGHTNING_ROD);

        offerStonecuttingRecipe(exporter, RecipeCategory.MISC, ModItems.RAVAGER_SCREW, ModItems.RAVAGER_TOOTH,2);

        ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC,ModItems.SCISSOR_BLADE,1)
                .input(Items.BLUE_ICE,5)
                .input(Items.NETHERITE_INGOT,1)
                .input(Items.BLAZE_POWDER,2)
                .input(ModItems.FORGED_BLADE,1);

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC,ModItems.SOLBRAND,1)
                .pattern("gbg")
                .pattern("gng")
                .pattern("srs")
                .input('b',ModItems.SUN_BLADE)
                .input('g',Items.TINTED_GLASS)
                .input('n',Items.NETHERITE_INGOT)
                .input('s',Items.NETHERITE_SCRAP)
                .input('r',Items.BLAZE_ROD);

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, UNTREATED_SCISSOR_BLADE,1)
                .pattern("  n")
                .pattern("di ")
                .pattern("bd ")
                .input('n', Items.NETHERITE_SCRAP)
                .input('d', Items.DIAMOND)
                .input('i', Items.NETHERITE_INGOT)
                .input('b', Items.BLAZE_ROD);

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
