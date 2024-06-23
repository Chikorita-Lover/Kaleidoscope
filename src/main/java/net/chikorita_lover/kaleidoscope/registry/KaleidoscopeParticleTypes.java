package net.chikorita_lover.kaleidoscope.registry;

import net.chikorita_lover.kaleidoscope.Kaleidoscope;
import net.fabricmc.fabric.api.particle.v1.FabricParticleTypes;
import net.minecraft.particle.ParticleType;
import net.minecraft.particle.SimpleParticleType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class KaleidoscopeParticleTypes {
    public static final SimpleParticleType FIREFLY = registerSimple("firefly");

    public static void register() {
    }

    private static SimpleParticleType registerSimple(String id) {
        return Registry.register(Registries.PARTICLE_TYPE, Kaleidoscope.of(id), FabricParticleTypes.simple());
    }
}
