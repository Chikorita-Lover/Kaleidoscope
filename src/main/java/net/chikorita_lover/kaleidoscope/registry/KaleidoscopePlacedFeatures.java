package net.chikorita_lover.kaleidoscope.registry;

import net.chikorita_lover.kaleidoscope.Kaleidoscope;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.world.gen.feature.PlacedFeature;

public class KaleidoscopePlacedFeatures {
    public static final RegistryKey<PlacedFeature> PILE_STICK_BLOCK = of("pile_stick_block");

    public static RegistryKey<PlacedFeature> of(String identifier) {
        return RegistryKey.of(RegistryKeys.PLACED_FEATURE, Kaleidoscope.of(identifier));
    }
}
