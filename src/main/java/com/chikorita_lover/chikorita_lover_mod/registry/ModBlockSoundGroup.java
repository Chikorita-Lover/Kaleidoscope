package com.chikorita_lover.chikorita_lover_mod.registry;

import static com.chikorita_lover.chikorita_lover_mod.registry.ModSoundEvents.*;
import net.minecraft.sound.BlockSoundGroup;
import static net.minecraft.sound.SoundEvents.*;

public class ModBlockSoundGroup {
    public static final BlockSoundGroup DIRT_BRICKS = new BlockSoundGroup(1.0F, 1.0F,
            BLOCK_DIRT_BRICKS_BREAK,
            BLOCK_DIRT_BRICKS_STEP,
            BLOCK_DIRT_BRICKS_PLACE,
            BLOCK_DIRT_BRICKS_HIT,
            BLOCK_DIRT_BRICKS_FALL
    );
    public static final BlockSoundGroup POLISHED_CALCITE = new BlockSoundGroup(1.0F, 1.0F,
            BLOCK_POLISHED_CALCITE_BREAK,
            BLOCK_POLISHED_CALCITE_STEP,
            BLOCK_POLISHED_CALCITE_PLACE,
            BLOCK_POLISHED_CALCITE_HIT,
            BLOCK_POLISHED_CALCITE_FALL
    );

    public static final BlockSoundGroup STICK_BUNDLE = new BlockSoundGroup(1.0F, 1.0F,
            BLOCK_STICK_BUNDLE_BREAK,
            BLOCK_MANGROVE_ROOTS_STEP,
            BLOCK_STICK_BUNDLE_PLACE,
            BLOCK_MANGROVE_ROOTS_HIT,
            BLOCK_MANGROVE_ROOTS_FALL
    );
}
