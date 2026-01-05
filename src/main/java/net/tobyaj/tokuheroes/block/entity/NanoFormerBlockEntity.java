package net.tobyaj.tokuheroes.block.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.NonNullList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.items.ItemStackHandler;
import net.tobyaj.tokuheroes.recipe.ModRecipes;
import net.tobyaj.tokuheroes.recipe.NanoFormerRecipe;
import net.tobyaj.tokuheroes.recipe.NanoFormerRecipeInput;
import net.tobyaj.tokuheroes.screen.custom.NanoFormerMenu;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class NanoFormerBlockEntity extends BlockEntity implements MenuProvider
{

    public static final int INPUT_SLOT_COUNT = 21;
    public static final int OUTPUT_SLOT = 21;
    public static final int SLOT_COUNT = 22;

    protected static final int[] INPUT_SLOTS = {
            0, 1, 2,
            3, 4, 5, 6, 7,
            8, 9, 10, 11, 12,
            13, 14, 15, 16, 17,
            18, 19, 20
    };

    public final ItemStackHandler itemHandler = new ItemStackHandler(SLOT_COUNT)
    {
        @Override
        protected void onContentsChanged(int slot)
        {
            setChanged();
            if (level != null && !level.isClientSide() && slot < OUTPUT_SLOT)
            {
                updateOutput();
            }
        }

        @Override
        public boolean isItemValid(int slot, ItemStack stack)
        {
            return slot < OUTPUT_SLOT;
        }
    };

    public NanoFormerBlockEntity(BlockPos pos, BlockState blockState)
    {
        super(ModBlockEntities.NANO_FORMER_BE.get(), pos, blockState);
    }

    @Override
    public Component getDisplayName()
    {
        return Component.translatable("block.tokuheroes.nano_former");
    }

    @Override
    public @Nullable AbstractContainerMenu createMenu(int containerId, Inventory playerInventory, Player player)
    {
        return new NanoFormerMenu(containerId, playerInventory, this);
    }

    @Override
    protected void saveAdditional(CompoundTag pTag, HolderLookup.Provider pRegistries)
    {
        pTag.put("inventory", itemHandler.serializeNBT(pRegistries));

        super.saveAdditional(pTag, pRegistries);
    }

    @Override
    protected void loadAdditional(CompoundTag pTag, HolderLookup.Provider pRegistries)
    {
        super.loadAdditional(pTag, pRegistries);

        itemHandler.deserializeNBT(pRegistries, pTag.getCompound("inventory"));
    }

    public void updateOutput()
    {
        if (level == null || level.isClientSide) return;

        Optional<RecipeHolder<NanoFormerRecipe>> recipeHolder = getCurrentRecipe();
        if (recipeHolder.isPresent())
        {
            NanoFormerRecipe recipe = recipeHolder.get().value();
            itemHandler.setStackInSlot(OUTPUT_SLOT, recipe.getResultItem(level.registryAccess()).copy());
        } else
        {
            itemHandler.setStackInSlot(OUTPUT_SLOT, ItemStack.EMPTY);
        }
    }

    public void consumeInputsForCraft()
    {
        Optional<RecipeHolder<NanoFormerRecipe>> recipeHolder = getCurrentRecipe();
        if (recipeHolder.isEmpty()) return;

        NanoFormerRecipe recipe = recipeHolder.get().value();
        NonNullList<Ingredient> ingredients = recipe.getIngredients();

        int realIndex = 0;
        for (int i = 0; i < ingredients.size(); i++)
        {
            boolean isCorner = (i == 0 || i == 4 || i == 20 || i == 24);
            if (isCorner) continue;

            Ingredient ingredient = ingredients.get(i);
            if (!ingredient.isEmpty())
            {
                int slot = INPUT_SLOTS[realIndex];
                itemHandler.extractItem(slot, 1, false);
            }

            realIndex++;
        }

        itemHandler.setStackInSlot(OUTPUT_SLOT, ItemStack.EMPTY);
        updateOutput();
    }

    public int[] getInputSlots()
    {
        return INPUT_SLOTS;
    }

    private Optional<RecipeHolder<NanoFormerRecipe>> getCurrentRecipe()
    {
        if (level == null) return Optional.empty();
        NanoFormerRecipeInput input = createRecipeInput();
        return level.getRecipeManager().getRecipeFor(ModRecipes.NANO_FORMER_TYPE.get(), input, level);
    }

    private NanoFormerRecipeInput createRecipeInput()
    {
        List<ItemStack> grid = new ArrayList<>(25);
        int realIndex = 0;
        for (int row = 0; row < 5; row++)
        {
            for (int col = 0; col < 5; col++)
            {
                if ((row == 0 && col == 0) || (row == 0 && col == 4) ||
                        (row == 4 && col == 0) || (row == 4 && col == 4))
                {
                    grid.add(ItemStack.EMPTY);
                } else
                {
                    grid.add(itemHandler.getStackInSlot(INPUT_SLOTS[realIndex++]));
                }
            }
        }
        return new NanoFormerRecipeInput(5, 5, grid);
    }
}