package net.chikorita_lover.kaleidoscope.registry;

import net.chikorita_lover.kaleidoscope.Kaleidoscope;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;
import net.minecraft.world.gen.feature.PlacedFeature;

public class KaleidoscopePlacedFeatures {
    public static final RegistryKey<PlacedFeature> PILE_STICK_BUNDLE = of("pile_stick_bundle");

    public static RegistryKey<PlacedFeature> of(String identifier) {
        return RegistryKey.of(RegistryKeys.PLACED_FEATURE, Kaleidoscope.of(identifier));
    }
}
