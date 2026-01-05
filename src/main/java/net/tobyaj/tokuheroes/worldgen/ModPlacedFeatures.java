package net.tobyaj.tokuheroes.worldgen;

import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.placement.HeightRangePlacement;
import net.tobyaj.tokuheroes.TokuHeroes;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraft.world.level.levelgen.placement.PlacementModifier;

import java.util.List;

public class ModPlacedFeatures
{
    public static final ResourceKey<PlacedFeature> LIGHTITE_ORE_PLACED_KEY = registerKey("lightite_ore_placed");
    public static final ResourceKey<PlacedFeature> VOID_STONE_ORE_PLACED_KEY = registerKey("void_stone_ore_placed");

    public static void bootstrap(BootstrapContext<PlacedFeature> context)
    {
        var configuredFeatures = context.lookup(Registries.CONFIGURED_FEATURE);

        register(context, LIGHTITE_ORE_PLACED_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.LIGHTITE_ORE_KEY),
                ModOrePlacement.commonOrePlacement(3, HeightRangePlacement.triangle(VerticalAnchor.absolute(-32), VerticalAnchor.absolute(32))));

        register(context, VOID_STONE_ORE_PLACED_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.VOID_STONE_ORE_KEY),
                ModOrePlacement.commonOrePlacement(7, HeightRangePlacement.triangle(VerticalAnchor.aboveBottom(-80), VerticalAnchor.aboveBottom(80))));
    }

    private static ResourceKey<PlacedFeature> registerKey(String name)
    {
        return ResourceKey.create(Registries.PLACED_FEATURE, ResourceLocation.fromNamespaceAndPath(TokuHeroes.MOD_ID, name));
    }

    private static void register(BootstrapContext<PlacedFeature> context, ResourceKey<PlacedFeature> key, Holder<ConfiguredFeature<?, ?>> configuration,
                                 List<PlacementModifier> modifiers)
    {
        context.register(key, new PlacedFeature(configuration, List.copyOf(modifiers)));
    }
}
