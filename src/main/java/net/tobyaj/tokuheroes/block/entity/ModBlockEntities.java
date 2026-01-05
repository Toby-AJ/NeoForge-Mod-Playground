package net.tobyaj.tokuheroes.block.entity;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.tobyaj.tokuheroes.TokuHeroes;
import net.tobyaj.tokuheroes.block.ModBlocks;

import java.util.function.Supplier;

public class ModBlockEntities
{
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES =
            DeferredRegister.create(BuiltInRegistries.BLOCK_ENTITY_TYPE, TokuHeroes.MOD_ID);

    public static final Supplier<BlockEntityType<VoidInfuserBlockEntity>> VOID_INFUSER_BE =
            BLOCK_ENTITIES.register("void_infuser_be", () -> BlockEntityType.Builder.of(
                    VoidInfuserBlockEntity::new, ModBlocks.VOID_INFUSER.get()).build(null));

    public static final Supplier<BlockEntityType<NanoFormerBlockEntity>> NANO_FORMER_BE =
            BLOCK_ENTITIES.register("nano_former_be", () -> BlockEntityType.Builder.of(
                    NanoFormerBlockEntity::new, ModBlocks.NANO_FORMER.get()).build(null));

    public static void register (IEventBus eventBus)
    {
        BLOCK_ENTITIES.register(eventBus);
    }
}
