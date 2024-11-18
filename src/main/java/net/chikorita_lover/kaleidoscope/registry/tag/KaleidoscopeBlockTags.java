package net.chikorita_lover.kaleidoscope.registry.tag;

import net.chikorita_lover.kaleidoscope.Kaleidoscope;
import net.minecraft.block.Block;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;

public class KaleidoscopeBlockTags {
    public static final TagKey<Block> BURNS_INTO_CHARCOAL = of("burns_into_charcoal");
    public static final TagKey<Block> CONDUIT_ACTIVATING_BLOCKS = of("conduit_activating_blocks");
    public static final TagKey<Block> COPPER = of("copper");
    public static final TagKey<Block> FIREFLIES_SPAWNABLE_ON = of("fireflies_spawnable_on");
    public static final TagKey<Block> SHEARS_MINEABLE = of("mineable/shears");
    public static final TagKey<Block> SIGNAL_FIRE_BASE_BLOCKS = of("signal_fire_base_blocks");

    private static TagKey<Block> of(String id) {
        return TagKey.of(RegistryKeys.BLOCK, Kaleidoscope.of(id));
    }
}
