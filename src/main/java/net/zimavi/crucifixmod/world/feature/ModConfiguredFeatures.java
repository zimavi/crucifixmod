package net.zimavi.crucifixmod.world.feature;

import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.RandomPatchConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.SimpleBlockConfiguration;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.zimavi.crucifixmod.CrucifixMod;
import net.zimavi.crucifixmod.block.ModBlocks;

public class ModConfiguredFeatures {


    //public static final RegistryObject<ConfiguredFeature<?, ?>> HERB_OF_VIRIDIS = CONFIGURED_FEATURES.register("herb_of_viridis",
    //        () -> new ConfiguredFeature<>(Feature.FLOWER,
    //                new RandomPatchConfiguration(3, 6, 2, PlacementUtils.onlyWhenEmpty(Feature.SIMPLE_BLOCK,
    //                        new SimpleBlockConfiguration(BlockStateProvider.simple(ModBlocks.HERB_OF_VIRIDIS.get()))))));

    public static final ResourceKey<ConfiguredFeature<?, ?>> HERB_OF_VIRIDIS = registerKey("herb_of_viridis");

    public static void bootstrap(BootstapContext<ConfiguredFeature<?, ?>> context) {
        HolderGetter<PlacedFeature> placedFeatures = context.lookup(Registries.PLACED_FEATURE);


        register(context, HERB_OF_VIRIDIS, Feature.FLOWER,
                new RandomPatchConfiguration(3, 6, 2, PlacementUtils.onlyWhenEmpty(Feature.SIMPLE_BLOCK,
                        new SimpleBlockConfiguration(BlockStateProvider.simple(ModBlocks.HERB_OF_VIRIDIS.get())))));
    }

    public static ResourceKey<ConfiguredFeature<?, ?>> registerKey(String name) {
        return ResourceKey.create(Registries.CONFIGURED_FEATURE, new ResourceLocation(CrucifixMod.MOD_ID, name));
    }

    private static <FC extends FeatureConfiguration, F extends Feature<FC>> void register(BootstapContext<ConfiguredFeature<?, ?>> context,
                                                                                          ResourceKey<ConfiguredFeature<?, ?>> key, F feature, FC configuration) {
        context.register(key, new ConfiguredFeature<>(feature, configuration));
    }
}
