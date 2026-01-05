package net.tobyaj.tokuheroes.compat;

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
import net.tobyaj.tokuheroes.TokuHeroes;
import net.tobyaj.tokuheroes.block.ModBlocks;
import net.tobyaj.tokuheroes.recipe.NanoFormerRecipe;
import org.jetbrains.annotations.Nullable;

public class NanoFormerRecipeCategory implements IRecipeCategory<NanoFormerRecipe>
{
    public static final ResourceLocation UID = ResourceLocation.fromNamespaceAndPath(TokuHeroes.MOD_ID, "nano_former");
    public static final ResourceLocation TEXTURE = ResourceLocation.fromNamespaceAndPath(TokuHeroes.MOD_ID,
            "textures/gui/nano_former/nano_former_gui.png");

    public static final RecipeType<NanoFormerRecipe> NANO_FORMER_RECIPE_RECIPE_TYPE = new RecipeType<>(UID, NanoFormerRecipe.class);

    private final IDrawable background;
    private final IDrawable icon;

    public NanoFormerRecipeCategory(IGuiHelper helper)
    {
        this.background = helper.createDrawable(TEXTURE,0,0,176,120);
        this.icon = helper.createDrawableIngredient(VanillaTypes.ITEM_STACK, new ItemStack(ModBlocks.NANO_FORMER));
    }

    @Override
    public RecipeType<NanoFormerRecipe> getRecipeType()
    {
        return NANO_FORMER_RECIPE_RECIPE_TYPE;
    }

    @Override
    public Component getTitle()
    {
        return Component.translatable("block.tokuheroes.nano_former");
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
    public void setRecipe(IRecipeLayoutBuilder builder, NanoFormerRecipe recipe, IFocusGroup focuses)
    {
        // Input grid (5x5, corners skipped)
        int ingredientIndex = 0;

        for (int row = 0; row < 5; row++) {
            for (int col = 0; col < 5; col++) {

                boolean isCorner =
                        (row == 0 && col == 0) ||
                                (row == 0 && col == 4) ||
                                (row == 4 && col == 0) ||
                                (row == 4 && col == 4);

                if (isCorner) {
                    ingredientIndex++; // still advance to stay aligned with recipe
                    continue;
                }

                // Match your menu slot positions
                int x = 16 + col * 18;
                int y = 18 + row * 18;

                builder.addSlot(RecipeIngredientRole.INPUT, x, y)
                        .addIngredients(recipe.getIngredients().get(ingredientIndex));

                ingredientIndex++;
            }
        }

        // Output slot
        builder.addSlot(RecipeIngredientRole.OUTPUT, 124, 54)
                .addItemStack(recipe.getResultItem(null));
    }
}
