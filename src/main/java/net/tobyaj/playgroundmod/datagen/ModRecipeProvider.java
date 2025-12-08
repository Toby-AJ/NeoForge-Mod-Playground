package net.tobyaj.playgroundmod.datagen;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.ItemLike;
import net.neoforged.neoforge.common.conditions.IConditionBuilder;
import net.tobyaj.playgroundmod.PlaygroundMod;
import net.tobyaj.playgroundmod.block.ModBlocks;
import net.tobyaj.playgroundmod.item.ModItems;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public class ModRecipeProvider extends RecipeProvider implements IConditionBuilder {
    public ModRecipeProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries);
    }

    @Override
    protected void buildRecipes(RecipeOutput recipeOutput) {
        List<ItemLike> VOID_STONE_SMELTABLES = List.of(ModItems.IMPERFECT_VOID_STONE,
                ModBlocks.VOID_STONE_ORE, ModBlocks.DEEPSLATE_VOID_STONE_ORE);

        List<ItemLike> REFINED_VOID_STONE_SMELTABLES = List.of(ModItems.VOID_STONE);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.VOID_STONE_BLOCK.get())
                .pattern("VVV")
                .pattern("VVV")
                .pattern("VVV")
                .define('V', ModItems.VOID_STONE.get())
                .unlockedBy("has_void_stone", has(ModItems.VOID_STONE)).save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.REFINED_VOID_STONE_BLOCK.get())
                .pattern("RRR")
                .pattern("RRR")
                .pattern("RRR")
                .define('R', ModItems.REFINED_VOID_STONE.get())
                .unlockedBy("has_refined_void_stone", has(ModItems.REFINED_VOID_STONE)).save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.LIGHTITE_BLOCK.get())
                .pattern("LLL")
                .pattern("LLL")
                .pattern("LLL")
                .define('L', ModItems.LIGHTITE.get())
                .unlockedBy("has_lightite", has(ModItems.LIGHTITE)).save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.HARDLIGHT_PROJECTION_CORE.get())
                .pattern("GLG")
                .pattern("LDL")
                .pattern("GLG")
                .define('D', Items.DIAMOND)
                .define('L', ModItems.LIGHTITE.get())
                .define('G', Items.GLASS)
                .unlockedBy("has_hardlight_projection_core", has(ModItems.HARDLIGHT_PROJECTION_CORE)).save(recipeOutput);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.VOID_STONE.get(), 9)
                .requires(ModBlocks.VOID_STONE_BLOCK)
                .unlockedBy("has_void_stone_block", has(ModBlocks.VOID_STONE_BLOCK)).save(recipeOutput);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.REFINED_VOID_STONE.get(), 9)
                .requires(ModBlocks.REFINED_VOID_STONE_BLOCK)
                .unlockedBy("has_refined_void_stone_block", has(ModBlocks.REFINED_VOID_STONE_BLOCK)).save(recipeOutput);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.LIGHTITE.get(), 9)
                .requires(ModBlocks.LIGHTITE_BLOCK)
                .unlockedBy("has_lightite_block", has(ModBlocks.LIGHTITE_BLOCK)).save(recipeOutput);

        oreSmelting(recipeOutput, VOID_STONE_SMELTABLES, RecipeCategory.MISC, ModItems.VOID_STONE.get(), 0.25f, 200, "void_stone");
        oreBlasting(recipeOutput, VOID_STONE_SMELTABLES, RecipeCategory.MISC, ModItems.VOID_STONE.get(), 0.25f, 100, "void_stone");

        oreSmelting(recipeOutput, REFINED_VOID_STONE_SMELTABLES, RecipeCategory.MISC, ModItems.REFINED_VOID_STONE.get(), 0.25f, 400, "void_stone");
        oreBlasting(recipeOutput, REFINED_VOID_STONE_SMELTABLES, RecipeCategory.MISC, ModItems.REFINED_VOID_STONE.get(), 0.25f, 300, "void_stone");
    }

    protected static void oreSmelting(RecipeOutput recipeOutput, List<ItemLike> pIngredients, RecipeCategory pCategory, ItemLike pResult,
                                      float pExperience, int pCookingTIme, String pGroup) {
        oreCooking(recipeOutput, RecipeSerializer.SMELTING_RECIPE, SmeltingRecipe::new, pIngredients, pCategory, pResult,
                pExperience, pCookingTIme, pGroup, "_from_smelting");
    }

    protected static void oreBlasting(RecipeOutput recipeOutput, List<ItemLike> pIngredients, RecipeCategory pCategory, ItemLike pResult,
                                      float pExperience, int pCookingTime, String pGroup) {
        oreCooking(recipeOutput, RecipeSerializer.BLASTING_RECIPE, BlastingRecipe::new, pIngredients, pCategory, pResult,
                pExperience, pCookingTime, pGroup, "_from_blasting");
    }

    protected static <T extends AbstractCookingRecipe> void oreCooking(RecipeOutput recipeOutput, RecipeSerializer<T> pCookingSerializer, AbstractCookingRecipe.Factory<T> factory,
                                                                       List<ItemLike> pIngredients, RecipeCategory pCategory, ItemLike pResult, float pExperience, int pCookingTime, String pGroup, String pRecipeName)
    {
        for(ItemLike itemlike : pIngredients) {
            SimpleCookingRecipeBuilder.generic(Ingredient.of(itemlike), pCategory, pResult, pExperience, pCookingTime, pCookingSerializer, factory).group(pGroup).unlockedBy(getHasName(itemlike), has(itemlike))
                    .save(recipeOutput, PlaygroundMod.MOD_ID + ":" + getItemName(pResult) + pRecipeName + "_" + getItemName(itemlike));
        }
    }
}
