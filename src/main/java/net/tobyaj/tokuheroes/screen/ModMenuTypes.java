package net.tobyaj.tokuheroes.screen;

import net.minecraft.core.registries.Registries;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MenuType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.common.extensions.IMenuTypeExtension;
import net.neoforged.neoforge.network.IContainerFactory;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.tobyaj.tokuheroes.TokuHeroes;
import net.tobyaj.tokuheroes.screen.custom.NanoFormerMenu;
import net.tobyaj.tokuheroes.screen.custom.VoidInfuserMenu;

public class ModMenuTypes
{
    public static final DeferredRegister<MenuType<?>> MENUS =
            DeferredRegister.create(Registries.MENU, TokuHeroes.MOD_ID);

    public static final DeferredHolder<MenuType<?>, MenuType<VoidInfuserMenu>> VOID_INFUSER_MENU =
            registerMenuType("void_infuser_menu", VoidInfuserMenu::new);

    public static final DeferredHolder<MenuType<?>, MenuType<NanoFormerMenu>> NANO_FORMER_MENU =
            registerMenuType("nano_former_menu", NanoFormerMenu::new);

    private static <T extends AbstractContainerMenu> DeferredHolder<MenuType<?>, MenuType<T>> registerMenuType(String name,
                                                                                                               IContainerFactory<T> factory)
    {
        return MENUS.register(name, () -> IMenuTypeExtension.create(factory));
    }

    public static void register(IEventBus eventBus) {
        MENUS.register(eventBus);
    }
}
