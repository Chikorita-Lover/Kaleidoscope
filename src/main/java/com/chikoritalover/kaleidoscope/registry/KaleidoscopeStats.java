package com.chikoritalover.kaleidoscope.registry;

import com.chikoritalover.kaleidoscope.Kaleidoscope;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.stat.StatFormatter;
import net.minecraft.stat.StatType;
import net.minecraft.util.Identifier;

import static net.minecraft.stat.Stats.CUSTOM;

public class KaleidoscopeStats {
    public static final Identifier INTERACT_WITH_KILN;

    public KaleidoscopeStats() {
    }

    private static Identifier register(String id, StatFormatter formatter) {
        Identifier identifier = new Identifier(Kaleidoscope.MODID, id);
        Registry.register(Registries.CUSTOM_STAT, id, identifier);
        CUSTOM.getOrCreateStat(identifier, formatter);
        return identifier;
    }

    static {
        INTERACT_WITH_KILN = register("interact_with_kiln", StatFormatter.DEFAULT);
    }

    public static void register() {}
}
