package com.chikoritalover.kaleidoscope.registry;

import net.minecraft.block.BlockSetType;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.sound.SoundEvents;

public class KaleidoscopeBlockSetType {
    public static final BlockSetType COPPER = BlockSetType.register(new BlockSetType("copper", true, BlockSoundGroup.COPPER, KaleidoscopeSoundEvents.BLOCK_COPPER_DOOR_CLOSE, KaleidoscopeSoundEvents.BLOCK_COPPER_DOOR_OPEN, KaleidoscopeSoundEvents.BLOCK_COPPER_TRAPDOOR_CLOSE, KaleidoscopeSoundEvents.BLOCK_COPPER_TRAPDOOR_OPEN, SoundEvents.BLOCK_METAL_PRESSURE_PLATE_CLICK_OFF, SoundEvents.BLOCK_METAL_PRESSURE_PLATE_CLICK_ON, SoundEvents.BLOCK_STONE_BUTTON_CLICK_OFF, SoundEvents.BLOCK_STONE_BUTTON_CLICK_ON));
    public static final BlockSetType GLASS = BlockSetType.register(new BlockSetType("glass", true, BlockSoundGroup.GLASS, SoundEvents.BLOCK_WOODEN_DOOR_CLOSE, SoundEvents.BLOCK_WOODEN_DOOR_OPEN, SoundEvents.BLOCK_WOODEN_TRAPDOOR_CLOSE, SoundEvents.BLOCK_WOODEN_TRAPDOOR_OPEN, SoundEvents.BLOCK_METAL_PRESSURE_PLATE_CLICK_OFF, SoundEvents.BLOCK_METAL_PRESSURE_PLATE_CLICK_ON, SoundEvents.BLOCK_STONE_BUTTON_CLICK_OFF, SoundEvents.BLOCK_STONE_BUTTON_CLICK_ON));
}
