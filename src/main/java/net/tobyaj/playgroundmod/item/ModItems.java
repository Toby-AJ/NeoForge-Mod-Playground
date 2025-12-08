package net.tobyaj.playgroundmod.item;

import net.minecraft.world.item.Item;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.tobyaj.playgroundmod.PlaygroundMod;

public class ModItems
{
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(PlaygroundMod.MOD_ID);

    public static final DeferredItem<Item> IMPERFECT_VOID_STONE = ITEMS.register("imperfect_void_stone", () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> VOID_STONE = ITEMS.register("void_stone", () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> REFINED_VOID_STONE = ITEMS.register("refined_void_stone", () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> LIGHTITE = ITEMS.register("lightite", () -> new Item(new Item.Properties()));

    public static final DeferredItem<Item> RAW_SPIRIT = ITEMS.register("raw_spirit", () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> TIGER_SPIRIT = ITEMS.register("tiger_spirit", () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> EAGLE_SPIRIT = ITEMS.register("eagle_spirit", () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> SHARK_SPIRIT = ITEMS.register("shark_spirit", () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> BEAR_SPIRIT = ITEMS.register("bear_spirit", () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> SNAKE_SPIRIT = ITEMS.register("snake_spirit", () -> new Item(new Item.Properties()));

    public static void register(IEventBus eventBus)
    {
        ITEMS.register(eventBus);
    }
}
