package net.chikorita_lover.kaleidoscope.registry;

import net.chikorita_lover.kaleidoscope.Kaleidoscope;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.stat.StatFormatter;
import net.minecraft.util.Identifier;

import static net.minecraft.stat.Stats.CUSTOM;

public class KaleidoscopeStats {
    public static final Identifier INTERACT_WITH_FIREWORKS_TABLE = register("interact_with_fireworks_table", StatFormatter.DEFAULT);
    public static final Identifier INTERACT_WITH_KILN = register("interact_with_kiln", StatFormatter.DEFAULT);

    public static void register() {
    }

    private static Identifier register(String id, StatFormatter formatter) {
        Identifier identifier = Kaleidoscope.of(id);
        Registry.register(Registries.CUSTOM_STAT, identifier, identifier);
        CUSTOM.getOrCreateStat(identifier, formatter);
        return identifier;
    }
}
