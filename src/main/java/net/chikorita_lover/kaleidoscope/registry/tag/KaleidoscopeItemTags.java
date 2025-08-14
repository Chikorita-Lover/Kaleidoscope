package net.chikorita_lover.kaleidoscope.registry.tag;

import net.chikorita_lover.kaleidoscope.Kaleidoscope;
import net.minecraft.item.Item;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;

public class KaleidoscopeItemTags {
    public static final TagKey<Item> DISPENSER_TOOLS = of("dispenser_tools");
    public static final TagKey<Item> FIREWORK_SHELLS = of("firework_shells");
    public static final TagKey<Item> FIREWORK_STAR_BASES = of("firework_star_bases");
    public static final TagKey<Item> HORSE_ARMOR = of("horse_armor");
    public static final TagKey<Item> PREVENTS_HORSE_ANGER = of("prevents_horse_anger");

    private static TagKey<Item> of(String id) {
        return TagKey.of(RegistryKeys.ITEM, Kaleidoscope.of(id));
    }
}
