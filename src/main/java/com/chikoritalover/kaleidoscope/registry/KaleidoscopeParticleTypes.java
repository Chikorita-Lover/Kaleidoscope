package com.chikoritalover.kaleidoscope.registry;

import com.chikoritalover.kaleidoscope.Kaleidoscope;
import net.fabricmc.fabric.api.particle.v1.FabricParticleTypes;
import net.minecraft.particle.DefaultParticleType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class KaleidoscopeParticleTypes {
    public static final DefaultParticleType FIREFLY = registerSimple("firefly");

    public static DefaultParticleType registerSimple(String id) {
        return Registry.register(Registries.PARTICLE_TYPE, new Identifier(Kaleidoscope.MODID, id), FabricParticleTypes.simple());
    }

    public static void register() {
    }
}
