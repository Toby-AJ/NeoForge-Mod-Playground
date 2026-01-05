package net.tobyaj.playgroundmod.recipe;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeInput;

import java.util.List;

public record VoidInfuserRecipeInput(List<ItemStack> input) implements RecipeInput
{

    @Override
    public ItemStack getItem(int index)
    {
        return input.get(index);
    }

    @Override
    public int size()
    {
        return input.size();
    }

    public static VoidInfuserRecipeInput of(ItemStack input1, ItemStack input2) {
        return new VoidInfuserRecipeInput(List.of(input1, input2));
    }
}
