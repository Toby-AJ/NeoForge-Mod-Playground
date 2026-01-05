package net.tobyaj.playgroundmod.recipe;

import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.tobyaj.playgroundmod.PlaygroundMod;

public class ModRecipes {

    public static final DeferredRegister<RecipeSerializer<?>> SERIALIZERS =
            DeferredRegister.create(Registries.RECIPE_SERIALIZER, PlaygroundMod.MOD_ID);

    public static final DeferredRegister<RecipeType<?>> TYPES =
            DeferredRegister.create(Registries.RECIPE_TYPE, PlaygroundMod.MOD_ID);

    public static final DeferredHolder<RecipeSerializer<?>, RecipeSerializer<NanoFormerRecipe>> NANO_FORMER_SERIALIZER =
            SERIALIZERS.register("nano_former", NanoFormerRecipe.Serializer::new);

    public static final DeferredHolder<RecipeType<?>, RecipeType<NanoFormerRecipe>> NANO_FORMER_TYPE =
            TYPES.register("nano_former", () -> new RecipeType<>() {
                @Override
                public String toString() {
                    return "nano_former";
                }
            });

    public static final DeferredHolder<RecipeSerializer<?>, RecipeSerializer<VoidInfuserRecipe>> VOID_INFUSER_SERIALIZER =
            SERIALIZERS.register("void_infuser", VoidInfuserRecipe.Serializer::new);

    public static final DeferredHolder<RecipeType<?>, RecipeType<VoidInfuserRecipe>> VOID_INFUSER_TYPE =
            TYPES.register("void_infuser", () -> new RecipeType<>() {
                @Override
                public String toString() {
                    return "void_infuser";
                }
            });

    public static void register(IEventBus eventBus)
    {
        SERIALIZERS.register(eventBus);
        TYPES.register(eventBus);
    }
}