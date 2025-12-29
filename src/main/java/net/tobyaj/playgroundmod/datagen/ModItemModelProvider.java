package net.tobyaj.playgroundmod.datagen;

import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.client.model.generators.ItemModelProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.tobyaj.playgroundmod.PlaygroundMod;
import net.tobyaj.playgroundmod.item.ModItems;

public class ModItemModelProvider extends ItemModelProvider {
    public ModItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, PlaygroundMod.MOD_ID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        basicItem(ModItems.IMPERFECT_VOID_STONE.get());
        basicItem(ModItems.VOID_STONE.get());
        basicItem(ModItems.REFINED_VOID_STONE.get());
        basicItem(ModItems.LIGHTITE.get());
        basicItem(ModItems.POWER_CORE.get());
        basicItem(ModItems.BASIC_PROCESSOR.get());
        basicItem(ModItems.VOID_PROCESSOR.get());
        basicItem(ModItems.REFINED_VOID_PROCESSOR.get());
        basicItem(ModItems.LIGHTITE_PROCESSOR.get());
        basicItem(ModItems.HARDLIGHT_PROJECTION_CORE.get());
        basicItem(ModItems.HARDLIGHT_PROJECTOR.get());
        basicItem(ModItems.BASE_POWER_ARMOUR_HELMET.get());
        basicItem(ModItems.BASE_POWER_ARMOUR_CHESTPLATE.get());
        basicItem(ModItems.BASE_POWER_ARMOUR_LEGGINGS.get());
        basicItem(ModItems.BASE_POWER_ARMOUR_BOOTS.get());
        basicItem(ModItems.SPIRIT_ROD.get());
        basicItem(ModItems.RAW_SPIRIT.get());
        basicItem(ModItems.TIGER_SPIRIT.get());
        basicItem(ModItems.EAGLE_SPIRIT.get());
        basicItem(ModItems.SHARK_SPIRIT.get());
        basicItem(ModItems.BEAR_SPIRIT.get());
        basicItem(ModItems.SNAKE_SPIRIT.get());
    }
}
