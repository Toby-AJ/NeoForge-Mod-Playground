package net.tobyaj.playgroundmod.block.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.Containers;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.items.ItemStackHandler;
import net.tobyaj.playgroundmod.recipe.ModRecipes;
import net.tobyaj.playgroundmod.recipe.VoidInfuserRecipe;
import net.tobyaj.playgroundmod.recipe.VoidInfuserRecipeInput;
import net.tobyaj.playgroundmod.screen.custom.VoidInfuserMenu;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Optional;

public class VoidInfuserBlockEntity extends BlockEntity implements MenuProvider {

    public final ItemStackHandler itemHandler = new ItemStackHandler(3) {
        @Override
        protected void onContentsChanged(int slot) {
            setChanged();
            if (!level.isClientSide()) {
                level.sendBlockUpdated(getBlockPos(), getBlockState(), getBlockState(), 3);
            }
        }
    };

    private static final int INPUT_SLOT_1 = 0;
    private static final int INPUT_SLOT_2 = 1;
    private static final int OUTPUT_SLOT = 2;

    protected final ContainerData data;
    private int progress = 0;

    public VoidInfuserBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.VOID_INFUSER_BE.get(), pos, state);
        data = new ContainerData() {
            @Override
            public int get(int index) {
                switch (index) {
                    case 0:
                        return progress;
                    case 1:
                        // Get maxProgress from the current recipe, default to 400 if no recipe
                        return getCurrentRecipe().map(r -> r.value().getMaxProgress()).orElse(400);
                    default:
                        return 0;
                }
            }

            @Override
            public void set(int index, int value) {
                if (index == 0) progress = value; // we only allow setting progress
            }

            @Override
            public int getCount() {
                return 2;
            }
        };
    }

    @Override
    public Component getDisplayName() {
        return Component.translatable("block.playgroundmod.void_infuser");
    }

    @Nullable
    @Override
    public AbstractContainerMenu createMenu(int containerId, Inventory playerInventory, Player player) {
        return new VoidInfuserMenu(containerId, playerInventory, this, this.data);
    }

    @Override
    protected void saveAdditional(CompoundTag pTag, HolderLookup.Provider pRegistries) {
        pTag.put("inventory", itemHandler.serializeNBT(pRegistries));
        pTag.putInt("void_infuser.progress", progress);

        super.saveAdditional(pTag, pRegistries);
    }

    @Override
    protected void loadAdditional(CompoundTag pTag, HolderLookup.Provider pRegistries) {
        super.loadAdditional(pTag, pRegistries);

        itemHandler.deserializeNBT(pRegistries, pTag.getCompound("inventory"));
        progress = pTag.getInt("void_infuser.progress");
    }

    public void drops() {
        SimpleContainer inv = new SimpleContainer(itemHandler.getSlots());
        for (int i = 0; i < itemHandler.getSlots(); i++) {
            inv.setItem(i, itemHandler.getStackInSlot(i));
        }
        Containers.dropContents(level, worldPosition, inv);
    }

    public void tick(Level level, BlockPos pos, BlockState state) {
        Optional<RecipeHolder<VoidInfuserRecipe>> recipeOpt = getCurrentRecipe();
        if(recipeOpt.isPresent() && canInsertRecipeOutput(recipeOpt.get().value())) {
            VoidInfuserRecipe recipe = recipeOpt.get().value();
            increaseCraftingProgress();
            setChanged(level, pos, state);

            if (progress >= recipe.getMaxProgress()) {
                craftItem();
                resetProgress();
            }
        } else {
            resetProgress();
        }
    }

    private boolean canInsertRecipeOutput(VoidInfuserRecipe recipe) {
        ItemStack output = recipe.getResultItem(null);
        return canInsertAmountIntoOutputSlot(output.getCount()) && canInsertItemIntoOutputSlot(output);
    }

    private Optional<RecipeHolder<VoidInfuserRecipe>> getCurrentRecipe() {
        VoidInfuserRecipeInput input = new VoidInfuserRecipeInput(
                List.of(itemHandler.getStackInSlot(INPUT_SLOT_1),
                        itemHandler.getStackInSlot(INPUT_SLOT_2))
        );

        return level.getRecipeManager()
                .getRecipeFor(ModRecipes.VOID_INFUSER_TYPE.get(), input, level);
    }

    private void craftItem() {
        Optional<RecipeHolder<VoidInfuserRecipe>> recipeOpt = getCurrentRecipe();
        if (recipeOpt.isEmpty()) return;

        ItemStack result = recipeOpt.get().value().getResultItem(null);

        // remove input items
        itemHandler.extractItem(INPUT_SLOT_1, 1, false);
        itemHandler.extractItem(INPUT_SLOT_2, 1, false);

        // add to output slot
        ItemStack current = itemHandler.getStackInSlot(OUTPUT_SLOT);
        if (current.isEmpty()) {
            itemHandler.setStackInSlot(OUTPUT_SLOT, result.copy());
        } else {
            current.grow(result.getCount());
            itemHandler.setStackInSlot(OUTPUT_SLOT, current);
        }
    }

    private void resetProgress() {
        progress = 0;
    }

    private void increaseCraftingProgress() {
        progress++;
    }

    private boolean canInsertItemIntoOutputSlot(ItemStack stack) {
        ItemStack current = itemHandler.getStackInSlot(OUTPUT_SLOT);
        return current.isEmpty() || current.getItem() == stack.getItem();
    }

    private boolean canInsertAmountIntoOutputSlot(int count) {
        ItemStack current = itemHandler.getStackInSlot(OUTPUT_SLOT);
        int max = current.isEmpty() ? 64 : current.getMaxStackSize();
        return current.getCount() + count <= max;
    }

    @Nullable
    @Override
    public Packet<ClientGamePacketListener> getUpdatePacket() {
        return ClientboundBlockEntityDataPacket.create(this);
    }

    @Override
    public CompoundTag getUpdateTag(HolderLookup.Provider registries) {
        return saveWithoutMetadata(registries);
    }
}