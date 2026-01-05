package net.tobyaj.tokuheroes.block.custom;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.Containers;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.ItemInteractionResult;
import net.minecraft.world.SimpleMenuProvider;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.tobyaj.tokuheroes.block.entity.NanoFormerBlockEntity;
import org.jetbrains.annotations.Nullable;

public class NanoFormerBlock extends BaseEntityBlock
{
    public static final MapCodec<NanoFormerBlock> CODEC = simpleCodec(NanoFormerBlock::new);

    public NanoFormerBlock(Properties properties)
    {
        super(properties);
    }

    @Override
    protected MapCodec<? extends BaseEntityBlock> codec()
    {
        return CODEC;
    }

    @Override
    public @Nullable BlockEntity newBlockEntity(BlockPos pos, BlockState state)
    {
        return new NanoFormerBlockEntity(pos, state);
    }

    @Override
    protected RenderShape getRenderShape(BlockState state)
    {
        return RenderShape.MODEL;
    }

    @Override
    public void onRemove(BlockState pState, Level pLevel, BlockPos pPos, BlockState pNewState, boolean pIsMoving)
    {
        if (!pState.is(pNewState.getBlock()))
        {
            BlockEntity be = pLevel.getBlockEntity(pPos);
            if (be instanceof NanoFormerBlockEntity nanoFormer)
            {
                // Drop only the input slots, skip output
                for (int i = 0; i < NanoFormerBlockEntity.OUTPUT_SLOT; i++)
                {
                    ItemStack stack = nanoFormer.itemHandler.getStackInSlot(i);
                    if (!stack.isEmpty())
                    {
                        Containers.dropItemStack(pLevel, pPos.getX(), pPos.getY(), pPos.getZ(), stack);
                    }
                }
            }
            super.onRemove(pState, pLevel, pPos, pNewState, pIsMoving);
        }
    }

    @Override
    protected ItemInteractionResult useItemOn(
            ItemStack stack,
            BlockState state,
            Level level,
            BlockPos pos,
            Player player,
            InteractionHand hand,
            BlockHitResult hit
    )
    {
        if (!level.isClientSide)
        {
            BlockEntity be = level.getBlockEntity(pos);
            if (be instanceof NanoFormerBlockEntity nanoFormer)
            {
                ((ServerPlayer) player).openMenu(
                        new SimpleMenuProvider(nanoFormer, nanoFormer.getDisplayName()),
                        pos
                );
            }
        }
        return ItemInteractionResult.sidedSuccess(level.isClientSide);
    }

    // NO TICKER NEEDED for instant crafting
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level,BlockState state,BlockEntityType<T> type)
    {
        return null;
    }
}