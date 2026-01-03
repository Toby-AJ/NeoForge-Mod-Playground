package net.tobyaj.playgroundmod.recipe;

import com.mojang.serialization.Codec;
import com.mojang.serialization.DataResult;
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

import java.util.List;
import java.util.Map;

public class NanoFormerRecipe implements Recipe<NanoFormerRecipeInput>
{

    private final int width;
    private final int height;
    private final NonNullList<Ingredient> ingredients;
    private final ItemStack result;

    public NanoFormerRecipe(int width, int height,
                            NonNullList<Ingredient> ingredients,
                            ItemStack result)
    {
        this.width = width;
        this.height = height;
        this.ingredients = ingredients;
        this.result = result;
    }

    @Override
    public boolean matches(NanoFormerRecipeInput input, Level level)
    {
        if (input.width() != width || input.height() != height) return false;

        for (int i = 0; i < ingredients.size(); i++) {
            if (!ingredients.get(i).test(input.getItem(i))) {
                return false;
            }
        }
        return true;
    }

    @Override
    public ItemStack assemble(NanoFormerRecipeInput input, HolderLookup.Provider registries)
    {
        return result.copy();
    }

    @Override
    public boolean canCraftInDimensions(int w, int h)
    {
        return w == width && h == height;
    }

    @Override
    public ItemStack getResultItem(HolderLookup.Provider registries)
    {
        return result;
    }

    @Override
    public RecipeSerializer<?> getSerializer()
    {
        return ModRecipes.NANO_FORMER_SERIALIZER.get();
    }

    @Override
    public RecipeType<?> getType()
    {
        return ModRecipes.NANO_FORMER_TYPE.get();
    }

    public NonNullList<Ingredient> getIngredients()
    {
        return ingredients;
    }

    public static final Codec<NonNullList<Ingredient>> INGREDIENTS_CODEC =
            Codec.list(Ingredient.CODEC)
                    .flatXmap(
                            list ->
                            {
                                if (list.size() != 25)
                                {
                                    return DataResult.error(
                                            () -> "NanoFormer requires exactly 25 ingredients (5x5)"
                                    );
                                }

                                return DataResult.success(
                                        NonNullList.of(Ingredient.EMPTY,
                                                list.toArray(new Ingredient[0]))
                                );
                            },
                            DataResult::success
                    );

    public static class Serializer implements RecipeSerializer<NanoFormerRecipe>
    {

        private static final int WIDTH = 5;
        private static final int HEIGHT = 5;

        /* ---------------- CODEC ---------------- */

        public static final MapCodec<NanoFormerRecipe> CODEC =
                RecordCodecBuilder.mapCodec(instance -> instance.group(

                        Codec.STRING.listOf()
                                .fieldOf("pattern")
                                .flatXmap(Serializer::validatePattern, DataResult::success)
                                .forGetter(r -> patternFromIngredients(r.getIngredients())),

                        Codec.unboundedMap(
                                        Codec.STRING,
                                        Ingredient.CODEC
                                ).fieldOf("key")
                                .flatXmap(Serializer::validateKey, DataResult::success)
                                .forGetter(r -> Map.of()), // not used on write

                        ItemStack.STRICT_CODEC.fieldOf("result")
                                .forGetter(r -> r.result)

                ).apply(instance, Serializer::fromCodec));

        /* ---------------- NETWORK ---------------- */

        public static final StreamCodec<RegistryFriendlyByteBuf, NanoFormerRecipe> STREAM_CODEC =
                StreamCodec.of(Serializer::toNetwork, Serializer::fromNetwork);

        @Override
        public MapCodec<NanoFormerRecipe> codec()
        {
            return CODEC;
        }

        @Override
        public StreamCodec<RegistryFriendlyByteBuf, NanoFormerRecipe> streamCodec()
        {
            return STREAM_CODEC;
        }

        /* ---------------- CODEC HELPERS ---------------- */

        private static DataResult<List<String>> validatePattern(List<String> pattern)
        {
            if (pattern.size() != HEIGHT)
            {
                return DataResult.error(() -> "NanoFormer pattern must have exactly 5 rows");
            }

            for (String row : pattern)
            {
                if (row.length() != WIDTH)
                {
                    return DataResult.error(() -> "Each NanoFormer pattern row must be exactly 5 characters");
                }
            }
            return DataResult.success(pattern);
        }

        private static DataResult<Map<String, Ingredient>> validateKey(Map<String, Ingredient> key)
        {
            for (Map.Entry<String, Ingredient> entry : key.entrySet())
            {
                if (entry.getKey().length() != 1)
                {
                    return DataResult.error(() -> "NanoFormer key entries must be single characters");
                }
                if (entry.getKey().equals(" "))
                {
                    return DataResult.error(() -> "Space character cannot be defined in NanoFormer key");
                }
            }
            return DataResult.success(key);
        }

        private static NanoFormerRecipe fromCodec(
                List<String> pattern,
                Map<String, Ingredient> key,
                ItemStack result
        )
        {
            NonNullList<Ingredient> ingredients =
                    NonNullList.withSize(WIDTH * HEIGHT, Ingredient.EMPTY);

            for (int y = 0; y < HEIGHT; y++)
            {
                String row = pattern.get(y);
                for (int x = 0; x < WIDTH; x++)
                {
                    char c = row.charAt(x);
                    Ingredient ing = c == ' '
                            ? Ingredient.EMPTY
                            : key.getOrDefault(String.valueOf(c), Ingredient.EMPTY);

                    ingredients.set(x + y * WIDTH, ing);
                }
            }

            return new NanoFormerRecipe(WIDTH, HEIGHT, ingredients, result);
        }

        /* ---------------- NETWORK IO ---------------- */

        private static NanoFormerRecipe fromNetwork(RegistryFriendlyByteBuf buf)
        {
            NonNullList<Ingredient> ingredients =
                    NonNullList.withSize(WIDTH * HEIGHT, Ingredient.EMPTY);

            for (int i = 0; i < ingredients.size(); i++)
            {
                ingredients.set(i, Ingredient.CONTENTS_STREAM_CODEC.decode(buf));
            }

            ItemStack result = ItemStack.STREAM_CODEC.decode(buf);
            return new NanoFormerRecipe(WIDTH, HEIGHT, ingredients, result);
        }

        private static void toNetwork(RegistryFriendlyByteBuf buf, NanoFormerRecipe recipe)
        {
            for (Ingredient ing : recipe.getIngredients())
            {
                Ingredient.CONTENTS_STREAM_CODEC.encode(buf, ing);
            }
            ItemStack.STREAM_CODEC.encode(buf, recipe.result);
        }

        /* ---------------- WRITE SUPPORT ---------------- */

        private static List<String> patternFromIngredients(NonNullList<Ingredient> ingredients)
        {
            // Only needed if you plan to serialize back to JSON
            return List.of(
                    "     ",
                    "     ",
                    "     ",
                    "     ",
                    "     "
            );
        }
    }
}