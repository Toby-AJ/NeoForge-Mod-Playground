package net.tobyaj.playgroundmod.datagen;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.neoforged.neoforge.common.data.BlockTagsProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.tobyaj.playgroundmod.PlaygroundMod;
import net.tobyaj.playgroundmod.block.ModBlocks;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class ModBlockTagProvider extends BlockTagsProvider {
    public ModBlockTagProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, @Nullable ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, PlaygroundMod.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        tag(BlockTags.MINEABLE_WITH_PICKAXE)
                .add(ModBlocks.VOID_STONE_ORE.get())
                .add(ModBlocks.DEEPSLATE_VOID_STONE_ORE.get())
                .add(ModBlocks.VOID_STONE_BLOCK.get())
                .add(ModBlocks.REFINED_VOID_STONE_BLOCK.get())
                .add(ModBlocks.LIGHTITE_ORE.get())
                .add(ModBlocks.DEEPSLATE_LIGHTITE_ORE.get())
                .add(ModBlocks.LIGHTITE_BLOCK.get())
                .add(ModBlocks.VOID_INFUSER.get())
                .add(ModBlocks.VOID_GLASS.get())
                .add(ModBlocks.NANO_FORMER.get());

        tag(BlockTags.NEEDS_IRON_TOOL)
                .add(ModBlocks.LIGHTITE_ORE.get())
                .add(ModBlocks.LIGHTITE_BLOCK.get());

        tag(BlockTags.NEEDS_DIAMOND_TOOL)
                .add(ModBlocks.VOID_STONE_ORE.get())
                .add(ModBlocks.DEEPSLATE_VOID_STONE_ORE.get())
                .add(ModBlocks.VOID_STONE_BLOCK.get())
                .add(ModBlocks.REFINED_VOID_STONE_BLOCK.get())
                .add(ModBlocks.DEEPSLATE_LIGHTITE_ORE.get());

    }
}
