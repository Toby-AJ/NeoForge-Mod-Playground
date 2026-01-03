package net.tobyaj.playgroundmod.screen.custom;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.*;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.neoforged.neoforge.items.SlotItemHandler;
import net.tobyaj.playgroundmod.block.ModBlocks;
import net.tobyaj.playgroundmod.block.entity.NanoFormerBlockEntity;
import net.tobyaj.playgroundmod.screen.ModMenuTypes;

public class NanoFormerMenu extends AbstractContainerMenu
{
    public final NanoFormerBlockEntity blockEntity;
    private final Level level;

    private static final int HOTBAR_SLOT_COUNT = 9;
    private static final int PLAYER_INVENTORY_ROW_COUNT = 3;
    private static final int PLAYER_INVENTORY_COLUMN_COUNT = 9;
    private static final int PLAYER_INVENTORY_SLOT_COUNT = PLAYER_INVENTORY_ROW_COUNT * PLAYER_INVENTORY_COLUMN_COUNT;
    private static final int VANILLA_SLOT_COUNT = HOTBAR_SLOT_COUNT + PLAYER_INVENTORY_SLOT_COUNT;
    private static final int NANO_FIRST_SLOT_INDEX = VANILLA_SLOT_COUNT;

    private static final int NANO_INPUT_COUNT = NanoFormerBlockEntity.INPUT_SLOT_COUNT;
    private static final int NANO_SLOT_COUNT = NANO_INPUT_COUNT + 1; // +1 output

    public NanoFormerMenu(int containerId, Inventory inv, FriendlyByteBuf extraData)
    {
        this(containerId, inv, inv.player.level().getBlockEntity(extraData.readBlockPos()));
    }

    public NanoFormerMenu(int containerId, Inventory inv, BlockEntity entity)
    {
        super(ModMenuTypes.NANO_FORMER_MENU.get(), containerId);
        this.blockEntity = (NanoFormerBlockEntity) entity;
        this.level = inv.player.level();

        addPlayerInventory(inv);
        addPlayerHotbar(inv);
        addNanoFormerSlots();
    }

    private void addNanoFormerSlots()
    {
        int index = 0;

        // Top row (5 slots, corners disabled)
        for (int col = 0; col < 5; col++)
        {
            boolean corner = col == 0 || col == 4;
            if (corner) addSlot(new DisabledSlot(16 + col * 18, 18));
            else addSlot(createInputSlot(index++, 16 + col * 18, 18));
        }

        // Middle 3 rows
        for (int row = 0; row < 3; row++)
        {
            for (int col = 0; col < 5; col++)
            {
                addSlot(createInputSlot(index++, 16 + col * 18, 36 + row * 18));
            }
        }

        // Bottom row
        for (int col = 0; col < 5; col++)
        {
            boolean corner = col == 0 || col == 4;
            if (corner) addSlot(new DisabledSlot(16 + col * 18, 90));
            else addSlot(createInputSlot(index++, 16 + col * 18, 90));
        }

        // --- OUTPUT SLOT ---
        addSlot(new SlotItemHandler(blockEntity.itemHandler, NanoFormerBlockEntity.OUTPUT_SLOT, 124, 54)
        {
            @Override
            public boolean mayPlace(ItemStack stack)
            {
                return false;
            }

            @Override
            public void onTake(Player player, ItemStack stack)
            {
                blockEntity.consumeInputsForCraft();
            }
        });
    }

    private Slot createInputSlot(int index, int x, int y)
    {
        return new SlotItemHandler(blockEntity.itemHandler, index, x, y);
    }

    private static class DisabledSlot extends Slot
    {
        public DisabledSlot(int x, int y)
        {
            super(new SimpleContainer(1), 0, x, y);
        }

        @Override
        public boolean mayPlace(ItemStack stack) { return false; }

        @Override
        public boolean mayPickup(Player player) { return false; }

        @Override
        public void set(ItemStack stack) { }

        @Override
        public ItemStack getItem() { return ItemStack.EMPTY; }
    }

    @Override
    public boolean stillValid(Player player)
    {
        return stillValid(ContainerLevelAccess.create(level, blockEntity.getBlockPos()), player, ModBlocks.NANO_FORMER.get());
    }

    @Override
    public ItemStack quickMoveStack(Player player, int index)
    {
        Slot sourceSlot = slots.get(index);
        if (sourceSlot == null || !sourceSlot.hasItem()) return ItemStack.EMPTY;

        ItemStack sourceStack = sourceSlot.getItem();
        ItemStack copy = sourceStack.copy();

        if (index < VANILLA_SLOT_COUNT)
        {
            if (!moveItemStackTo(sourceStack, NANO_FIRST_SLOT_INDEX, NANO_FIRST_SLOT_INDEX + NANO_INPUT_COUNT, false))
                return ItemStack.EMPTY;
        }
        else if (index < NANO_FIRST_SLOT_INDEX + NANO_SLOT_COUNT)
        {
            if (!moveItemStackTo(sourceStack, 0, VANILLA_SLOT_COUNT, false))
                return ItemStack.EMPTY;
        } else return ItemStack.EMPTY;

        if (sourceStack.isEmpty()) sourceSlot.set(ItemStack.EMPTY);
        else sourceSlot.setChanged();

        sourceSlot.onTake(player, sourceStack);
        return copy;
    }

    private void addPlayerInventory(Inventory inv)
    {
        int startY = 124;
        for (int row = 0; row < 3; ++row)
            for (int col = 0; col < 9; ++col)
                addSlot(new Slot(inv, col + row * 9 + 9, 8 + col * 18, startY + row * 18));
    }

    private void addPlayerHotbar(Inventory inv)
    {
        int startY = 182;
        for (int col = 0; col < 9; ++col)
            addSlot(new Slot(inv, col, 8 + col * 18, startY));
    }
}