package net.tobyaj.tokuheroes.datagen;

import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.client.model.generators.ItemModelProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.tobyaj.tokuheroes.TokuHeroes;
import net.tobyaj.tokuheroes.item.ModItems;

public class ModItemModelProvider extends ItemModelProvider {
    public ModItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, TokuHeroes.MOD_ID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        basicItem(ModItems.IMPERFECT_VOID_STONE.get());
        basicItem(ModItems.VOID_STONE.get());
        basicItem(ModItems.REFINED_VOID_STONE.get());
        basicItem(ModItems.HARD_VOID_STONE.get());
        basicItem(ModItems.FIRE_VOID_STONE.get());
        basicItem(ModItems.WATER_VOID_STONE.get());
        basicItem(ModItems.EARTH_VOID_STONE.get());
        basicItem(ModItems.AIR_VOID_STONE.get());

        basicItem(ModItems.LIGHTITE.get());
        basicItem(ModItems.VOIDITE.get());
        basicItem(ModItems.HARD_CRYSTAL.get());
        basicItem(ModItems.FIRE_CRYSTAL.get());
        basicItem(ModItems.WATER_CRYSTAL.get());
        basicItem(ModItems.EARTH_CRYSTAL.get());
        basicItem(ModItems.AIR_CRYSTAL.get());

        basicItem(ModItems.VOID_PEARL.get());
        basicItem(ModItems.HARD_VOID_PEARL.get());
        basicItem(ModItems.FIRE_VOID_PEARL.get());
        basicItem(ModItems.WATER_VOID_PEARL.get());
        basicItem(ModItems.EARTH_VOID_PEARL.get());
        basicItem(ModItems.AIR_VOID_PEARL.get());

        basicItem(ModItems.VOID_GLASS_DOME.get());
        basicItem(ModItems.POWER_CORE.get());
        basicItem(ModItems.BASIC_PROCESSOR.get());
        basicItem(ModItems.VOID_PROCESSOR.get());
        basicItem(ModItems.REFINED_VOID_PROCESSOR.get());
        basicItem(ModItems.LIGHTITE_PROCESSOR.get());
        basicItem(ModItems.HARDLIGHT_PROJECTION_CORE.get());
        basicItem(ModItems.HARDLIGHT_PROJECTOR.get());

        basicItem(ModItems.VOID_ROD.get());
        basicItem(ModItems.BASE_MELEE_WEAPON.get());
        basicItem(ModItems.BASE_PROJECTILE_WEAPON.get());

        basicItem(ModItems.BASE_POWER_ARMOUR_HELMET.get());
        basicItem(ModItems.BASE_POWER_ARMOUR_CHESTPLATE.get());
        basicItem(ModItems.BASE_POWER_ARMOUR_LEGGINGS.get());
        basicItem(ModItems.BASE_POWER_ARMOUR_BOOTS.get());

        basicItem(ModItems.RUBY_GEM_WATCH.get());
        basicItem(ModItems.AMETHYST_GEM_WATCH.get());
        basicItem(ModItems.QUARTZ_GEM_WATCH.get());
        basicItem(ModItems.AMBER_GEM_WATCH.get());
        basicItem(ModItems.EMERALD_GEM_WATCH.get());
        basicItem(ModItems.NANO_PROJECTION_CORE.get());
        basicItem(ModItems.NANO_PROJECTOR.get());
        basicItem(ModItems.NANO_PROCESSOR.get());
        basicItem(ModItems.COPPER_PIPE.get());
        basicItem(ModItems.ARMOUR_NANITE.get());
        basicItem(ModItems.WEAPON_NANITE.get());
        basicItem(ModItems.BULLET_NANITE.get());
        basicItem(ModItems.FIRE_NANITE.get());
        basicItem(ModItems.TELEPORT_NANITE.get());
        basicItem(ModItems.POISON_NANITE.get());
        basicItem(ModItems.EXPLODE_NANITE.get());
        basicItem(ModItems.STEAMPUNK_KNUCKLE.get());

        basicItem(ModItems.SPIRIT_ROD.get());
        basicItem(ModItems.RAW_SPIRIT.get());
        basicItem(ModItems.TIGER_SPIRIT.get());
        basicItem(ModItems.EAGLE_SPIRIT.get());
        basicItem(ModItems.SHARK_SPIRIT.get());
        basicItem(ModItems.BEAR_SPIRIT.get());
        basicItem(ModItems.SNAKE_SPIRIT.get());
    }
}
