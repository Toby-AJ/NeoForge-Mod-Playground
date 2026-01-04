package net.tobyaj.playgroundmod.datagen;

import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.client.model.generators.BlockStateProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.tobyaj.playgroundmod.PlaygroundMod;
import net.tobyaj.playgroundmod.block.ModBlocks;

public class ModBlockStateProvider extends BlockStateProvider {
    public ModBlockStateProvider(PackOutput output, ExistingFileHelper exFileHelper) {
        super(output, PlaygroundMod.MOD_ID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        blockWithItem(ModBlocks.VOID_STONE_ORE);
        blockWithItem(ModBlocks.DEEPSLATE_VOID_STONE_ORE);
        blockWithItem(ModBlocks.VOID_STONE_BLOCK);
        blockWithItem(ModBlocks.REFINED_VOID_STONE_BLOCK);
        blockWithItem(ModBlocks.LIGHTITE_ORE);
        blockWithItem(ModBlocks.DEEPSLATE_LIGHTITE_ORE);
        blockWithItem(ModBlocks.LIGHTITE_BLOCK);
        blockWithItem(ModBlocks.VOID_INFUSER);
        blockWithItem(ModBlocks.VOID_GLASS);
        blockWithItem(ModBlocks.NANO_FORMER);
    }

    private void blockWithItem(DeferredBlock<?> deferredBlock) {
        simpleBlockWithItem(deferredBlock.get(), cubeAll(deferredBlock.get()));
    }
}
