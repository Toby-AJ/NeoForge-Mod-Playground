package net.tobyaj.playgroundmod;

import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.tobyaj.playgroundmod.block.ModBlocks;
import net.tobyaj.playgroundmod.item.ModItems;

import java.util.function.Supplier;

public class ModCreativeModeTabs
{
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TAB =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, PlaygroundMod.MOD_ID);

    public static final Supplier<CreativeModeTab> PLAYGROUND_TAB = CREATIVE_MODE_TAB.register("playground_tab",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(ModItems.TIGER_SPIRIT.get()))
                    .title(Component.translatable("creativetab.playgroundmod.playground_items"))
                    .displayItems((parameters, output) -> {
                      output.accept(ModBlocks.LIGHTITE_ORE);
                      output.accept(ModBlocks.LIGHTITE_BLOCK);
                      output.accept(ModItems.RAW_LIGHTITE);
                      output.accept(ModItems.LIGHTITE);
                      output.accept(ModItems.RAW_SPIRIT);
                      output.accept(ModItems.TIGER_SPIRIT);
                      output.accept(ModItems.EAGLE_SPIRIT);
                      output.accept(ModItems.SHARK_SPIRIT);
                      output.accept(ModItems.BEAR_SPIRIT);
                      output.accept(ModItems.SNAKE_SPIRIT);
                    }).build());

    public static void register(IEventBus eventBus)
    {
        CREATIVE_MODE_TAB.register(eventBus);
    }
}
