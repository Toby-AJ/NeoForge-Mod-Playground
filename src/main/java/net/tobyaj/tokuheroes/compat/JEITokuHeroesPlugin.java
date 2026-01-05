package net.tobyaj.tokuheroes.compat;

import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.gui.handlers.IGuiContainerHandler;
import mezz.jei.api.registration.*;
import net.minecraft.client.Minecraft;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.item.crafting.RecipeManager;
import net.tobyaj.tokuheroes.TokuHeroes;
import net.tobyaj.tokuheroes.block.ModBlocks;
import net.tobyaj.tokuheroes.recipe.ModRecipes;
import net.tobyaj.tokuheroes.recipe.NanoFormerRecipe;
import net.tobyaj.tokuheroes.recipe.VoidInfuserRecipe;
import net.tobyaj.tokuheroes.screen.custom.NanoFormerScreen;
import net.tobyaj.tokuheroes.screen.custom.VoidInfuserScreen;

import java.util.List;

@JeiPlugin
public class JEITokuHeroesPlugin implements IModPlugin
{
    @Override
    public ResourceLocation getPluginUid()
    {
        return ResourceLocation.fromNamespaceAndPath(TokuHeroes.MOD_ID,"jei_plugin");
    }

    @Override
    public void registerCategories(IRecipeCategoryRegistration registration)
    {
        registration.addRecipeCategories(new VoidInfuserRecipeCategory(registration.getJeiHelpers().getGuiHelper()));
        registration.addRecipeCategories(new NanoFormerRecipeCategory(registration.getJeiHelpers().getGuiHelper()));
    }

    @Override
    public void registerRecipes(IRecipeRegistration registration)
    {
        RecipeManager recipeManager = Minecraft.getInstance().level.getRecipeManager();

        List<VoidInfuserRecipe> voidInfuserRecipes =
                recipeManager.getAllRecipesFor(ModRecipes.VOID_INFUSER_TYPE.get())
                        .stream()
                        .map(RecipeHolder::value)
                        .toList();

        registration.addRecipes(
                VoidInfuserRecipeCategory.VOID_INFUSER_RECIPE_RECIPE_TYPE,
                voidInfuserRecipes
        );

        List<NanoFormerRecipe> nanoFormerRecipes =
                recipeManager.getAllRecipesFor(ModRecipes.NANO_FORMER_TYPE.get())
                        .stream()
                        .map(RecipeHolder::value)
                        .toList();

        registration.addRecipes(
                NanoFormerRecipeCategory.NANO_FORMER_RECIPE_RECIPE_TYPE,
                nanoFormerRecipes
        );
    }

    @Override
    public void registerGuiHandlers(IGuiHandlerRegistration registration)
    {
        registration.addGuiContainerHandler(VoidInfuserScreen.class,new IGuiContainerHandler<VoidInfuserScreen>() {});
        registration.addGuiContainerHandler(NanoFormerScreen.class,new IGuiContainerHandler<NanoFormerScreen>() {});
    }

    @Override
    public void registerRecipeCatalysts(IRecipeCatalystRegistration registration)
    {
        registration.addRecipeCatalyst(new ItemStack(ModBlocks.VOID_INFUSER.get().asItem()),
                VoidInfuserRecipeCategory.VOID_INFUSER_RECIPE_RECIPE_TYPE);
        registration.addRecipeCatalyst(new ItemStack(ModBlocks.NANO_FORMER.get().asItem()),
                NanoFormerRecipeCategory.NANO_FORMER_RECIPE_RECIPE_TYPE);
    }
}