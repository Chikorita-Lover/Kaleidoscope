package com.chikoritalover.kaleidoscope.registry;

import com.chikoritalover.kaleidoscope.Kaleidoscope;
import net.fabricmc.fabric.api.particle.v1.FabricParticleTypes;
import net.minecraft.particle.DefaultParticleType;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class ModParticleTypes {
    public static final DefaultParticleType FIREFLY = registerSimple("firefly");

    public static DefaultParticleType registerSimple(String id) {
        return Registry.register(Registry.PARTICLE_TYPE, new Identifier(Kaleidoscope.MODID, id), FabricParticleTypes.simple());
    }

    public static void register() {
    }
}
