package com.chikorita_lover.chikorita_lover_mod.registry;

import com.chikorita_lover.chikorita_lover_mod.ChikoritaLoverMod;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class ModSoundEvents {
    public static final SoundEvent BLOCK_POLISHED_CALCITE_BREAK = register("block.polished_calcite.break");
    public static final SoundEvent BLOCK_POLISHED_CALCITE_FALL = register("block.polished_calcite.fall");
    public static final SoundEvent BLOCK_POLISHED_CALCITE_HIT = register("block.polished_calcite.hit");
    public static final SoundEvent BLOCK_POLISHED_CALCITE_PLACE = register("block.polished_calcite.place");
    public static final SoundEvent BLOCK_POLISHED_CALCITE_STEP = register("block.polished_calcite.step");

    private static SoundEvent register(String id) {
        return (SoundEvent)Registry.register(Registry.SOUND_EVENT, new Identifier(ChikoritaLoverMod.MODID, id), new SoundEvent(new Identifier(ChikoritaLoverMod.MODID, id)));
    }

    public static void register() {}
}
