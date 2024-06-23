package net.chikorita_lover.kaleidoscope.registry;

import net.chikorita_lover.kaleidoscope.Kaleidoscope;
import net.minecraft.loot.LootTable;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;

public class KaleidoscopeLootTables {
    public static final RegistryKey<LootTable> HERO_OF_THE_VILLAGE_FIREWORKER_GIFT_GAMEPLAY = register("gameplay/hero_of_the_village/fireworker_gift");
    public static final RegistryKey<LootTable> HERO_OF_THE_VILLAGE_GLASSBLOWER_GIFT_GAMEPLAY = register("gameplay/hero_of_the_village/glassblower_gift");
    public static final RegistryKey<LootTable> VILLAGE_FIREWORKER_CHEST = register("chests/village/village_fireworker");

    public static void register() {
    }

    private static RegistryKey<LootTable> register(String id) {
        return RegistryKey.of(RegistryKeys.LOOT_TABLE, Kaleidoscope.of(id));
    }
}
