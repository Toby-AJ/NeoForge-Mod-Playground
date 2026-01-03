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

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.POWER_CORE.get(), 2)
                .pattern("LCL")
                .pattern("LRL")
                .pattern("LCL")
                .define('L', ModItems.LIGHTITE.get())
                .define('C', Items.COPPER_INGOT)
                .define('R', Items.REDSTONE)
                .unlockedBy("has_power_core", has(ModItems.POWER_CORE)).save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.BASIC_PROCESSOR.get())
                .pattern("RCR")
                .pattern("CLC")
                .pattern("RCR")
                .define('L', ModItems.LIGHTITE.get())
                .define('C', Items.COPPER_INGOT)
                .define('R', Items.REDSTONE)
                .unlockedBy("has_basic_processor", has(ModItems.BASIC_PROCESSOR)).save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.VOID_PROCESSOR.get())
                .pattern("GVG")
                .pattern("VBV")
                .pattern("GVG")
                .define('B', ModItems.BASIC_PROCESSOR.get())
                .define('V', ModItems.VOID_STONE.get())
                .define('G', Items.GOLD_INGOT)
                .unlockedBy("has_void_processor", has(ModItems.VOID_PROCESSOR)).save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.REFINED_VOID_PROCESSOR.get())
                .pattern("DRD")
                .pattern("RVR")
                .pattern("DRD")
                .define('V', ModItems.VOID_PROCESSOR.get())
                .define('R', ModItems.REFINED_VOID_STONE.get())
                .define('D', Items.DIAMOND)
                .unlockedBy("has_refined_void_processor", has(ModItems.REFINED_VOID_PROCESSOR)).save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.LIGHTITE_PROCESSOR.get())
                .pattern("GLG")
                .pattern("LBL")
                .pattern("GLG")
                .define('B', ModItems.BASIC_PROCESSOR.get())
                .define('L', ModItems.LIGHTITE.get())
                .define('G', Items.GOLD_INGOT)
                .unlockedBy("has_lightite_processor", has(ModItems.LIGHTITE_PROCESSOR)).save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.HARDLIGHT_PROJECTION_CORE.get())
                .pattern("GLG")
                .pattern("LDL")
                .pattern("GLG")
                .define('D', Items.DIAMOND)
                .define('L', ModItems.LIGHTITE.get())
                .define('G', Items.GLASS)
                .unlockedBy("has_hardlight_projection_core", has(ModItems.HARDLIGHT_PROJECTION_CORE)).save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.HARDLIGHT_PROJECTOR.get())
                .pattern("GHG")
                .pattern("RPR")
                .pattern("GRG")
                .define('H', ModItems.HARDLIGHT_PROJECTION_CORE.get())
                .define('P', ModItems.POWER_CORE.get())
                .define('R', ModItems.REFINED_VOID_STONE.get())
                .define('G', Items.GLASS)
                .unlockedBy("has_hardlight_projector", has(ModItems.HARDLIGHT_PROJECTOR)).save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.VOID_REFINERY.get())
                .pattern("VPV")
                .pattern("CSC")
                .pattern("VRV")
                .define('P', ModItems.VOID_PROCESSOR.get())
                .define('C', ModItems.POWER_CORE.get())
                .define('S', ModBlocks.VOID_STONE_BLOCK.get())
                .define('V', ModItems.VOID_STONE.get())
                .define('R', Items.REDSTONE)
                .unlockedBy("has_void_refinery", has(ModBlocks.VOID_REFINERY)).save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.VOID_ROD.get(), 4)
                .pattern("   ")
                .pattern(" R ")
                .pattern(" R ")
                .define('R', ModItems.REFINED_VOID_STONE.get())
                .unlockedBy("has_void_rod", has(ModItems.VOID_ROD)).save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.BASE_MELEE_WEAPON.get())
                .pattern(" RR")
                .pattern("RBR")
                .pattern("VR ")
                .define('R', ModItems.REFINED_VOID_STONE.get())
                .define('V', ModItems.VOID_ROD.get())
                .define('B', ModItems.BASIC_PROCESSOR.get())
                .unlockedBy("has_base_melee_weapon", has(ModItems.BASE_MELEE_WEAPON)).save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.BASE_PROJECTILE_WEAPON.get())
                .pattern("RRR")
                .pattern("GBR")
                .pattern("  V")
                .define('R', ModItems.REFINED_VOID_STONE.get())
                .define('V', ModItems.VOID_ROD.get())
                .define('B', ModItems.BASIC_PROCESSOR.get())
                .define('G', Items.GUNPOWDER)
                .unlockedBy("has_base_projectile_weapon", has(ModItems.BASE_PROJECTILE_WEAPON)).save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.BASE_POWER_ARMOUR_HELMET.get())
                .pattern("RRR")
                .pattern("R R")
                .pattern("   ")
                .define('R', ModItems.REFINED_VOID_STONE.get())
                .unlockedBy("has_base_power_armour_helmet", has(ModItems.BASE_POWER_ARMOUR_HELMET)).save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.BASE_POWER_ARMOUR_CHESTPLATE.get())
                .pattern("R R")
                .pattern("RRR")
                .pattern("RRR")
                .define('R', ModItems.REFINED_VOID_STONE.get())
                .unlockedBy("has_base_power_armour_chestplate", has(ModItems.BASE_POWER_ARMOUR_CHESTPLATE)).save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.BASE_POWER_ARMOUR_LEGGINGS.get())
                .pattern("RRR")
                .pattern("R R")
                .pattern("R R")
                .define('R', ModItems.REFINED_VOID_STONE.get())
                .unlockedBy("has_base_power_armour_leggings", has(ModItems.BASE_POWER_ARMOUR_LEGGINGS)).save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.BASE_POWER_ARMOUR_BOOTS.get())
                .pattern("   ")
                .pattern("R R")
                .pattern("R R")
                .define('R', ModItems.REFINED_VOID_STONE.get())
                .unlockedBy("has_base_power_armour_boots", has(ModItems.BASE_POWER_ARMOUR_BOOTS)).save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.SPIRIT_ROD.get())
                .pattern(" LA")
                .pattern(" SL")
                .pattern("W  ")
                .define('A', Items.AMETHYST_SHARD)
                .define('L', ModItems.LIGHTITE.get())
                .define('S', Items.SOUL_SAND)
                .define('W', Items.STICK)
                .unlockedBy("has_spirit_rod", has(ModItems.SPIRIT_ROD)).save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.NANO_PROJECTION_CORE.get())
                .pattern("GPG")
                .pattern("PAP")
                .pattern("GPG")
                .define('A', Items.AMETHYST_SHARD)
                .define('P', Items.PRISMARINE_CRYSTALS)
                .define('G', Items.GLASS)
                .unlockedBy("has_nano_projection_core", has(ModItems.NANO_PROJECTION_CORE)).save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.NANO_PROJECTOR.get())
                .pattern("GNG")
                .pattern("RPR")
                .pattern("GRG")
                .define('N', ModItems.NANO_PROJECTION_CORE.get())
                .define('P', ModItems.POWER_CORE.get())
                .define('R', ModItems.REFINED_VOID_STONE.get())
                .define('G', Items.GLASS)
                .unlockedBy("has_nano_projector", has(ModItems.NANO_PROJECTOR)).save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.NANO_FORMER.get())
                .pattern("CNC")
                .pattern("PRP")
                .pattern("BVB")
                .define('N', ModItems.NANO_PROJECTOR.get())
                .define('P', ModItems.POWER_CORE.get())
                .define('V', ModItems.REFINED_VOID_PROCESSOR.get())
                .define('B', ModBlocks.REFINED_VOID_STONE_BLOCK.get())
                .define('C', Items.PRISMARINE_CRYSTALS)
                .define('R', Items.REDSTONE)
                .unlockedBy("has_nano_former", has(ModBlocks.NANO_FORMER)).save(recipeOutput);

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
