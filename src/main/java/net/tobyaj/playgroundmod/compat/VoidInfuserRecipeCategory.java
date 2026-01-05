package net.tobyaj.playgroundmod.compat;

import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.RecipeIngredientRole;
import mezz.jei.api.recipe.RecipeType;
import mezz.jei.api.recipe.category.IRecipeCategory;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.tobyaj.playgroundmod.PlaygroundMod;
import net.tobyaj.playgroundmod.block.ModBlocks;
import net.tobyaj.playgroundmod.recipe.VoidInfuserRecipe;
import org.jetbrains.annotations.Nullable;

public class VoidInfuserRecipeCategory implements IRecipeCategory<VoidInfuserRecipe>
{
    public static final ResourceLocation UID = ResourceLocation.fromNamespaceAndPath(PlaygroundMod.MOD_ID, "void_infuser");
    public static final ResourceLocation TEXTURE = ResourceLocation.fromNamespaceAndPath(PlaygroundMod.MOD_ID,
            "textures/gui/void_infuser/void_infuser_gui.png");

    public static final RecipeType<VoidInfuserRecipe> VOID_INFUSER_RECIPE_RECIPE_TYPE = new RecipeType<>(UID, VoidInfuserRecipe.class);

    private final IDrawable background;
    private final IDrawable icon;

    public VoidInfuserRecipeCategory(IGuiHelper helper)
    {
        this.background = helper.createDrawable(TEXTURE,0,0,176,80);
        this.icon = helper.createDrawableIngredient(VanillaTypes.ITEM_STACK, new ItemStack(ModBlocks.VOID_INFUSER));
    }

    @Override
    public RecipeType<VoidInfuserRecipe> getRecipeType()
    {
        return VOID_INFUSER_RECIPE_RECIPE_TYPE;
    }

    @Override
    public Component getTitle()
    {
        return Component.translatable("block.playgroundmod.void_infuser");
    }

    @Override
    public IDrawable getBackground()
    {
        return background;
    }

    @Override
    public @Nullable IDrawable getIcon()
    {
        return icon;
    }

    @Override
    public void setRecipe(IRecipeLayoutBuilder builder, VoidInfuserRecipe recipe, IFocusGroup focuses)
    {
        builder.addSlot(RecipeIngredientRole.INPUT, 54,20).addIngredients(recipe.getIngredients().get(0));
        builder.addSlot(RecipeIngredientRole.INPUT, 54,48).addIngredients(recipe.getIngredients().get(1));

        builder.addSlot(RecipeIngredientRole.OUTPUT, 104,34).addItemStack(recipe.getResultItem(null));
    }
}
