package net.chikorita_lover.kaleidoscope.registry;

import net.chikorita_lover.kaleidoscope.Kaleidoscope;
import net.minecraft.block.Block;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;

public class KaleidoscopeBlockTags {
    public static final TagKey<Block> BURNS_INTO_CHARCOAL = of("burns_into_charcoal");
    public static final TagKey<Block> FIREFLIES_SPAWNABLE_ON = of("fireflies_spawnable_on");
    public static final TagKey<Block> SHEARS_MINEABLE = of("mineable/shears");

    private static TagKey<Block> of(String id) {
        return TagKey.of(RegistryKeys.BLOCK, new Identifier(Kaleidoscope.MODID, id));
    }
}
