package net.tobyaj.playgroundmod.recipe;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeInput;

import java.util.List;

public record NanoFormerRecipeInput(
        int width,
        int height,
        List<ItemStack> items
) implements RecipeInput
{

    @Override
    public ItemStack getItem(int index)
    {
        return items.get(index);
    }

    public ItemStack getItem(int row, int column)
    {
        return items.get(column + row * width);
    }

    @Override
    public int size()
    {
        return items.size();
    }

    @Override
    public boolean isEmpty()
    {
        for (ItemStack stack : items)
        {
            if (!stack.isEmpty()) return false;
        }
        return true;
    }
}