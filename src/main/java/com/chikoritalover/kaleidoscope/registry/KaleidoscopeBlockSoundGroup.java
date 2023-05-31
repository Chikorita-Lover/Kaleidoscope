package com.chikoritalover.kaleidoscope.registry;

import net.minecraft.sound.BlockSoundGroup;

public class KaleidoscopeBlockSoundGroup {
    public static final BlockSoundGroup ANDESITE = new BlockSoundGroup(1.0F, 1.0F,
            KaleidoscopeSoundEvents.BLOCK_ANDESITE_BREAK,
            KaleidoscopeSoundEvents.BLOCK_ANDESITE_STEP,
            KaleidoscopeSoundEvents.BLOCK_ANDESITE_PLACE,
            KaleidoscopeSoundEvents.BLOCK_ANDESITE_HIT,
            KaleidoscopeSoundEvents.BLOCK_ANDESITE_FALL
    );
    public static final BlockSoundGroup DIORITE = new BlockSoundGroup(1.0F, 1.0F,
            KaleidoscopeSoundEvents.BLOCK_DIORITE_BREAK,
            KaleidoscopeSoundEvents.BLOCK_DIORITE_STEP,
            KaleidoscopeSoundEvents.BLOCK_DIORITE_PLACE,
            KaleidoscopeSoundEvents.BLOCK_DIORITE_HIT,
            KaleidoscopeSoundEvents.BLOCK_DIORITE_FALL
    );
    public static final BlockSoundGroup SPONGE = new BlockSoundGroup(1.0F, 1.0F,
            KaleidoscopeSoundEvents.BLOCK_SPONGE_BREAK,
            KaleidoscopeSoundEvents.BLOCK_SPONGE_STEP,
            KaleidoscopeSoundEvents.BLOCK_SPONGE_PLACE,
            KaleidoscopeSoundEvents.BLOCK_SPONGE_HIT,
            KaleidoscopeSoundEvents.BLOCK_SPONGE_FALL
    );
}
