package net.tobyaj.playgroundmod.compat;

import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.gui.handlers.IGuiContainerHandler;
import mezz.jei.api.registration.*;
import net.minecraft.client.Minecraft;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.item.crafting.RecipeManager;
import net.tobyaj.playgroundmod.PlaygroundMod;
import net.tobyaj.playgroundmod.block.ModBlocks;
import net.tobyaj.playgroundmod.recipe.ModRecipes;
import net.tobyaj.playgroundmod.recipe.NanoFormerRecipe;
import net.tobyaj.playgroundmod.screen.custom.NanoFormerScreen;

import java.util.List;

@JeiPlugin
public class JEIPlaygroundModPlugin implements IModPlugin
{
    @Override
    public ResourceLocation getPluginUid()
    {
        return ResourceLocation.fromNamespaceAndPath(PlaygroundMod.MOD_ID,"jei_plugin");
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
        registration.addGuiContainerHandler(NanoFormerScreen.class,new IGuiContainerHandler<NanoFormerScreen>() {});
    }

    @Override
    public void registerRecipeCatalysts(IRecipeCatalystRegistration registration)
    {
        registration.addRecipeCatalyst(new ItemStack(ModBlocks.NANO_FORMER.get().asItem()),
                NanoFormerRecipeCategory.NANO_FORMER_RECIPE_RECIPE_TYPE);
    }
}