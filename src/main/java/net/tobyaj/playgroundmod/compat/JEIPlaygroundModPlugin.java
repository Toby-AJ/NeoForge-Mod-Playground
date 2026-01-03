package net.tobyaj.playgroundmod.compat;

import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.registration.IGuiHandlerRegistration;
import mezz.jei.api.registration.IRecipeCategoryRegistration;
import mezz.jei.api.registration.IRecipeRegistration;
import net.minecraft.client.Minecraft;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.item.crafting.RecipeManager;
import net.tobyaj.playgroundmod.PlaygroundMod;
import net.tobyaj.playgroundmod.recipe.ModRecipes;
import net.tobyaj.playgroundmod.recipe.NanoFormerRecipe;

import java.util.List;

@JeiPlugin
public class JEIPlaygroundModPlugin implements IModPlugin
{

    @Override
    public ResourceLocation getPluginUid()
    {
        return ResourceLocation.fromNamespaceAndPath(PlaygroundMod.MOD_ID, "jei_plugin");
    }

    @Override
    public void registerCategories(IRecipeCategoryRegistration registration)
    {
        registration.addRecipeCategories(new NanoFormerRecipeCategory(registration.getJeiHelpers().getGuiHelper()));
    }

    @Override
    public void registerRecipes(IRecipeRegistration registration)
    {
        RecipeManager recipeManager = Minecraft.getInstance().level.getRecipeManager();

        List<NanoFormerRecipe> nanoFormerRecipes = recipeManager.getAllRecipesFor((ModRecipes.NANO_FORMER_TYPE).get())
                .stream().map(RecipeHolder::value).toList();

        registration.addRecipes(NanoFormerRecipeCategory.NANO_FORMER_RECIPE_RECIPE_TYPE, nanoFormerRecipes);
    }

    /*@Override
    public void registerGuiHandlers(IGuiHandlerRegistration registration)
    {
        IModPlugin.super.registerGuiHandlers(registration);
    }*/
}
