package net.tobyaj.tokuheroes;

import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.tobyaj.tokuheroes.block.ModBlocks;
import net.tobyaj.tokuheroes.item.ModItems;

import java.util.function.Supplier;

public class ModCreativeModeTabs
{
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TAB =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, TokuHeroes.MOD_ID);

    public static final Supplier<CreativeModeTab> TOKU_HEROES_TAB = CREATIVE_MODE_TAB.register("toku_heroes_tab",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(ModItems.LIGHTITE.get()))
                    .title(Component.translatable("creativetab.tokuheroes.toku_heroes_items"))
                    .displayItems((parameters, output) -> {
                      output.accept(ModBlocks.VOID_STONE_ORE);
                      output.accept(ModBlocks.DEEPSLATE_VOID_STONE_ORE);
                      output.accept(ModBlocks.VOID_STONE_BLOCK);
                      output.accept(ModBlocks.REFINED_VOID_STONE_BLOCK);
                      output.accept(ModBlocks.LIGHTITE_ORE);
                      output.accept(ModBlocks.DEEPSLATE_LIGHTITE_ORE);
                      output.accept(ModBlocks.LIGHTITE_BLOCK);
                      output.accept(ModBlocks.SPACE_STONE);
                      output.accept(ModBlocks.VOID_INFUSER.get());
                      output.accept(ModBlocks.VOID_GLASS);
                      output.accept(ModBlocks.NANO_FORMER.get());

                      output.accept(ModItems.IMPERFECT_VOID_STONE);
                      output.accept(ModItems.VOID_STONE);
                      output.accept(ModItems.REFINED_VOID_STONE);
                      output.accept(ModItems.HARD_VOID_STONE);
                      output.accept(ModItems.FIRE_VOID_STONE);
                      output.accept(ModItems.WATER_VOID_STONE);
                      output.accept(ModItems.EARTH_VOID_STONE);
                      output.accept(ModItems.AIR_VOID_STONE);

                      output.accept(ModItems.LIGHTITE);
                      output.accept(ModItems.VOIDITE);
                      output.accept(ModItems.HARD_CRYSTAL);
                      output.accept(ModItems.FIRE_CRYSTAL);
                      output.accept(ModItems.WATER_CRYSTAL);
                      output.accept(ModItems.EARTH_CRYSTAL);
                      output.accept(ModItems.AIR_CRYSTAL);

                      output.accept(ModItems.VOID_PEARL);
                      output.accept(ModItems.HARD_VOID_PEARL);
                      output.accept(ModItems.FIRE_VOID_PEARL);
                      output.accept(ModItems.WATER_VOID_PEARL);
                      output.accept(ModItems.EARTH_VOID_PEARL);
                      output.accept(ModItems.AIR_VOID_PEARL);

                      output.accept(ModItems.VOID_GLASS_DOME);
                      output.accept(ModItems.POWER_CORE);
                      output.accept(ModItems.BASIC_PROCESSOR);
                      output.accept(ModItems.VOID_PROCESSOR);
                      output.accept(ModItems.REFINED_VOID_PROCESSOR);
                      output.accept(ModItems.LIGHTITE_PROCESSOR);
                      output.accept(ModItems.HARDLIGHT_PROJECTION_CORE);
                      output.accept(ModItems.HARDLIGHT_PROJECTOR);

                      output.accept(ModItems.VOID_ROD);
                      output.accept(ModItems.BASE_MELEE_WEAPON);
                      output.accept(ModItems.BASE_PROJECTILE_WEAPON);

                      output.accept((ModItems.BASE_POWER_ARMOUR_HELMET));
                      output.accept((ModItems.BASE_POWER_ARMOUR_CHESTPLATE));
                      output.accept((ModItems.BASE_POWER_ARMOUR_LEGGINGS));
                      output.accept((ModItems.BASE_POWER_ARMOUR_BOOTS));

                      output.accept(ModItems.RUBY_GEM_WATCH);
                      output.accept(ModItems.AMETHYST_GEM_WATCH);
                      output.accept(ModItems.QUARTZ_GEM_WATCH);
                      output.accept(ModItems.AMBER_GEM_WATCH);
                      output.accept(ModItems.EMERALD_GEM_WATCH);
                      output.accept(ModItems.NANO_PROJECTION_CORE);
                      output.accept(ModItems.NANO_PROJECTOR);
                      output.accept(ModItems.NANO_PROCESSOR);
                      output.accept(ModItems.COPPER_PIPE);
                      output.accept(ModItems.ARMOUR_NANITE);
                      output.accept(ModItems.WEAPON_NANITE);
                      output.accept(ModItems.BULLET_NANITE);
                      output.accept(ModItems.FIRE_NANITE);
                      output.accept(ModItems.TELEPORT_NANITE);
                      output.accept(ModItems.POISON_NANITE);
                      output.accept(ModItems.EXPLODE_NANITE);
                      output.accept(ModItems.STEAMPUNK_KNUCKLE);

                      output.accept(ModItems.SPIRIT_ROD);
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
