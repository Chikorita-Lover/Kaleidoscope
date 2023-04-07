package com.chikoritalover.kaleidoscope.registry;

import net.minecraft.sound.BlockSoundGroup;

import static net.minecraft.sound.SoundEvents.*;

public class ModBlockSoundGroup {
    public static final BlockSoundGroup STICK_BUNDLE = new BlockSoundGroup(1.0F, 1.0F,
            ModSoundEvents.BLOCK_STICK_BUNDLE_BREAK,
            BLOCK_MANGROVE_ROOTS_STEP,
            ModSoundEvents.BLOCK_STICK_BUNDLE_PLACE,
            BLOCK_MANGROVE_ROOTS_HIT,
            BLOCK_MANGROVE_ROOTS_FALL
    );
}
