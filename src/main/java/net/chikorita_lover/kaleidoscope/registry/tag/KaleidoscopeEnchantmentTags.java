package net.chikorita_lover.kaleidoscope.registry.tag;

import net.chikorita_lover.kaleidoscope.Kaleidoscope;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;

public class KaleidoscopeEnchantmentTags {
    public static final TagKey<Enchantment> PREVENTS_FARMLAND_TRAMPLING = of("prevents_farmland_trampling");

    private static TagKey<Enchantment> of(String id) {
        return TagKey.of(RegistryKeys.ENCHANTMENT, Kaleidoscope.of(id));
    }
}
