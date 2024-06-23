package net.chikorita_lover.kaleidoscope.registry.tag;

import net.chikorita_lover.kaleidoscope.Kaleidoscope;
import net.minecraft.item.Item;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;

public class KaleidoscopeItemTags {
    public static final TagKey<Item> BURNS_INTO_CHARCOAL = of("burns_into_charcoal");
    public static final TagKey<Item> FIREWORK_SHELLS = of("firework_shells");

    private static TagKey<Item> of(String id) {
        return TagKey.of(RegistryKeys.ITEM, Kaleidoscope.of(id));
    }
}
