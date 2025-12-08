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
        basicItem(ModItems.HARDLIGHT_PROJECTION_CORE.get());
        basicItem(ModItems.RAW_SPIRIT.get());
        basicItem(ModItems.TIGER_SPIRIT.get());
        basicItem(ModItems.EAGLE_SPIRIT.get());
        basicItem(ModItems.SHARK_SPIRIT.get());
        basicItem(ModItems.BEAR_SPIRIT.get());
        basicItem(ModItems.SNAKE_SPIRIT.get());
    }
}
