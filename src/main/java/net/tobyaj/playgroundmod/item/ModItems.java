package net.tobyaj.playgroundmod.item;

import net.minecraft.world.item.Item;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.tobyaj.playgroundmod.PlaygroundMod;

public class ModItems
{
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(PlaygroundMod.MOD_ID);

    public static final DeferredItem<Item> RAWLIGHTITE = ITEMS.register("rawlightite", () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> RAWSPIRIT = ITEMS.register("rawspirit", () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> TIGERSPIRIT = ITEMS.register("tigerspirit", () -> new Item(new Item.Properties()));

    public static void register(IEventBus eventBus)
    {
        ITEMS.register(eventBus);
    }
}
