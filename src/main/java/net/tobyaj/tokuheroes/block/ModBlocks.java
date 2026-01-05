package net.tobyaj.tokuheroes.block;

import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.DropExperienceBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.tobyaj.tokuheroes.TokuHeroes;
import net.tobyaj.tokuheroes.block.custom.NanoFormerBlock;
import net.tobyaj.tokuheroes.block.custom.VoidInfuserBlock;
import net.tobyaj.tokuheroes.item.ModItems;

import java.util.function.Supplier;

public class ModBlocks
{
    public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(TokuHeroes.MOD_ID);

    //Generic
    public static final DeferredBlock<Block> VOID_STONE_ORE = registerBlock("void_stone_ore",
            () -> new DropExperienceBlock(UniformInt.of(2, 4), BlockBehaviour.Properties.of()
                    .strength(3f).requiresCorrectToolForDrops().sound(SoundType.STONE)));

    public static final DeferredBlock<Block> DEEPSLATE_VOID_STONE_ORE = registerBlock("deepslate_void_stone_ore",
            () -> new DropExperienceBlock(UniformInt.of(3, 6), BlockBehaviour.Properties.of()
                    .strength(4f).requiresCorrectToolForDrops().sound(SoundType.DEEPSLATE)));

    public static final DeferredBlock<Block> VOID_STONE_BLOCK = registerBlock("void_stone_block",
            () -> new Block(BlockBehaviour.Properties.of()
                    .strength(4f).requiresCorrectToolForDrops().sound(SoundType.STONE)));

    public static final DeferredBlock<Block> REFINED_VOID_STONE_BLOCK = registerBlock("refined_void_stone_block",
            () -> new Block(BlockBehaviour.Properties.of()
                    .strength(4f).requiresCorrectToolForDrops().sound(SoundType.COPPER)));

    public static final DeferredBlock<Block> LIGHTITE_ORE = registerBlock("lightite_ore",
            () -> new DropExperienceBlock(UniformInt.of(2, 4), BlockBehaviour.Properties.of()
                    .strength(3f).requiresCorrectToolForDrops().sound(SoundType.STONE)));

    public static final DeferredBlock<Block> DEEPSLATE_LIGHTITE_ORE = registerBlock("deepslate_lightite_ore",
            () -> new DropExperienceBlock(UniformInt.of(3, 6), BlockBehaviour.Properties.of()
                    .strength(4f).requiresCorrectToolForDrops().sound(SoundType.DEEPSLATE)));

    public static final DeferredBlock<Block> LIGHTITE_BLOCK = registerBlock("lightite_block",
            () -> new Block(BlockBehaviour.Properties.of()
                    .strength(4f).requiresCorrectToolForDrops().lightLevel(state -> 15).sound(SoundType.CHAIN)));

    public static final DeferredBlock<Block> SPACE_STONE = registerBlock("space_stone",
            () -> new Block(BlockBehaviour.Properties.of()
                    .strength(2f).requiresCorrectToolForDrops().sound(SoundType.STONE)));

    public static final DeferredBlock<Block> VOID_INFUSER = registerBlock("void_infuser",
            () -> new VoidInfuserBlock(BlockBehaviour.Properties.of().strength(3f)
                    .requiresCorrectToolForDrops().sound(SoundType.STONE)));

    public static final DeferredBlock<Block> VOID_GLASS = registerBlock("void_glass",
            () -> new Block(BlockBehaviour.Properties.of()
                    .strength(1f).noOcclusion().lightLevel(state -> 0)
                    .isViewBlocking((state, level, pos) -> false)
                    .isSuffocating((state, level, pos) -> false)
                    .requiresCorrectToolForDrops().sound(SoundType.GLASS)));

    //STEAMPUNK
    public static final DeferredBlock<Block> NANO_FORMER = registerBlock("nano_former",
            () -> new NanoFormerBlock(BlockBehaviour.Properties.of().strength(4f)
                    .requiresCorrectToolForDrops().sound(SoundType.STONE)));

    //SPIRIT

    private static <T extends Block> DeferredBlock<T> registerBlock(String name, Supplier<T> block)
    {
        DeferredBlock<T> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name, toReturn);
        return toReturn;
    }

    private static <T extends Block> void registerBlockItem(String name, DeferredBlock<T> block)
    {
        ModItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties()));
    }

    public static void register(IEventBus eventBus)
    {
        BLOCKS.register(eventBus);
    }
}
