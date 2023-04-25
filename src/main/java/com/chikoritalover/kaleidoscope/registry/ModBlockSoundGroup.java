package com.chikoritalover.kaleidoscope.registry;

import net.minecraft.sound.BlockSoundGroup;

public class ModBlockSoundGroup {
    public static final BlockSoundGroup ANDESITE = new BlockSoundGroup(1.0F, 1.0F,
            ModSoundEvents.BLOCK_ANDESITE_BREAK,
            ModSoundEvents.BLOCK_ANDESITE_STEP,
            ModSoundEvents.BLOCK_ANDESITE_PLACE,
            ModSoundEvents.BLOCK_ANDESITE_HIT,
            ModSoundEvents.BLOCK_ANDESITE_FALL
    );
    public static final BlockSoundGroup DIORITE = new BlockSoundGroup(1.0F, 1.0F,
            ModSoundEvents.BLOCK_DIORITE_BREAK,
            ModSoundEvents.BLOCK_DIORITE_STEP,
            ModSoundEvents.BLOCK_DIORITE_PLACE,
            ModSoundEvents.BLOCK_DIORITE_HIT,
            ModSoundEvents.BLOCK_DIORITE_FALL
    );
}
