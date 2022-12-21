package com.chikorita_lover.chikorita_lover_mod.registry;

import com.chikorita_lover.chikorita_lover_mod.ChikoritaLoverMod;
import net.minecraft.stat.StatFormatter;
import net.minecraft.stat.StatType;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

import static net.minecraft.stat.Stats.CUSTOM;

public class ModStats {
    public static final Identifier INTERACT_WITH_KILN;

    public ModStats() {
    }

    private static Identifier register(String id, StatFormatter formatter) {
        Identifier identifier = new Identifier(ChikoritaLoverMod.MODID, id);
        Registry.register(Registry.CUSTOM_STAT, id, identifier);
        CUSTOM.getOrCreateStat(identifier, formatter);
        return identifier;
    }

    private static <T> StatType registerType(String id, Registry<T> registry) {
        return Registry.register(Registry.STAT_TYPE, id, new StatType<>(registry));
    }

    static {
        INTERACT_WITH_KILN = register("interact_with_kiln", StatFormatter.DEFAULT);
    }

    public static void register() {}
}
