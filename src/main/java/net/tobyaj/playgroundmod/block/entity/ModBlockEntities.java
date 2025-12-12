package net.tobyaj.playgroundmod.block.entity;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.tobyaj.playgroundmod.PlaygroundMod;
import net.tobyaj.playgroundmod.block.ModBlocks;

import java.util.function.Supplier;

public class ModBlockEntities
{
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES =
            DeferredRegister.create(BuiltInRegistries.BLOCK_ENTITY_TYPE, PlaygroundMod.MOD_ID);

    public static final Supplier<BlockEntityType<VoidRefineryBlockEntity>> VOID_REFINERY_BE =
            BLOCK_ENTITIES.register("void_refinery_be", () -> BlockEntityType.Builder.of(
                    VoidRefineryBlockEntity::new, ModBlocks.VOID_REFINERY.get()).build(null));

    public static void register (IEventBus eventBus)
    {
        BLOCK_ENTITIES.register(eventBus);
    }
}
