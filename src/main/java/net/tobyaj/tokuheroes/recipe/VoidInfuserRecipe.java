package net.tobyaj.tokuheroes.recipe;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.NonNullList;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.Level;

public record VoidInfuserRecipe(Ingredient input1, Ingredient input2, ItemStack output, int maxProgress)
        implements Recipe<VoidInfuserRecipeInput>
{

    @Override
    public NonNullList<Ingredient> getIngredients() {
        NonNullList<Ingredient> list = NonNullList.create();
        list.add(input1);
        list.add(input2);
        return list;
    }

    @Override
    public boolean matches(VoidInfuserRecipeInput input, Level level) {
        if (level.isClientSide()) return false;

        return input1.test(input.getItem(0)) &&
                input2.test(input.getItem(1));
    }

    @Override
    public ItemStack assemble(VoidInfuserRecipeInput input, HolderLookup.Provider registries) {
        return output.copy();
    }

    @Override
    public boolean canCraftInDimensions(int width, int height) {
        return true;
    }

    @Override
    public ItemStack getResultItem(HolderLookup.Provider registries) {
        return output.copy();
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return ModRecipes.VOID_INFUSER_SERIALIZER.get();
    }

    @Override
    public RecipeType<?> getType() {
        return ModRecipes.VOID_INFUSER_TYPE.get();
    }

    public int getMaxProgress()
    {
        return maxProgress;
    }

    public static class Serializer implements RecipeSerializer<VoidInfuserRecipe>
    {
        public static final MapCodec<VoidInfuserRecipe> CODEC = RecordCodecBuilder.mapCodec(inst -> inst.group(
                Ingredient.CODEC_NONEMPTY.fieldOf("input1").forGetter(VoidInfuserRecipe::input1),
                Ingredient.CODEC_NONEMPTY.fieldOf("input2").forGetter(VoidInfuserRecipe::input2),
                ItemStack.CODEC.fieldOf("result").forGetter(VoidInfuserRecipe::output),
                Codec.INT.fieldOf("progress").forGetter(VoidInfuserRecipe::getMaxProgress)
        ).apply(inst, VoidInfuserRecipe::new));

        public static final StreamCodec<RegistryFriendlyByteBuf, VoidInfuserRecipe> STREAM_CODEC =
                StreamCodec.composite(
                        Ingredient.CONTENTS_STREAM_CODEC, VoidInfuserRecipe::input1,
                        Ingredient.CONTENTS_STREAM_CODEC, VoidInfuserRecipe::input2,
                        ItemStack.STREAM_CODEC, VoidInfuserRecipe::output,
                        (input1, input2, output) -> new VoidInfuserRecipe(input1, input2, output, 400) // default maxProgress for network
                );

        @Override
        public MapCodec<VoidInfuserRecipe> codec() {
            return CODEC;
        }

        @Override
        public StreamCodec<RegistryFriendlyByteBuf, VoidInfuserRecipe> streamCodec() {
            return STREAM_CODEC;
        }
    }
}