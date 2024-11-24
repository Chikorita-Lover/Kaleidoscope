package net.chikorita_lover.kaleidoscope.registry;

import net.chikorita_lover.kaleidoscope.Kaleidoscope;
import net.fabricmc.fabric.api.particle.v1.FabricParticleTypes;
import net.minecraft.particle.DefaultParticleType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;

public class KaleidoscopeParticleTypes {
    public static final DefaultParticleType FIREFLY = register("firefly");

    public static void register() {
    }

    private static DefaultParticleType register(String id) {
        return Registry.register(Registries.PARTICLE_TYPE, Kaleidoscope.of(id), FabricParticleTypes.simple());
    }
}
