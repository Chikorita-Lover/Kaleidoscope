package com.chikorita_lover.chikorita_lover_mod.registry;

import com.chikorita_lover.chikorita_lover_mod.ChikoritaLoverMod;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class ModSoundEvents {
    public static final SoundEvent BLOCK_COPPER_DOOR_CLOSE = register("block.copper_door.close");
    public static final SoundEvent BLOCK_COPPER_DOOR_OPEN = register("block.copper_door.open");

    public static final SoundEvent BLOCK_COPPER_TRAPDOOR_CLOSE = register("block.copper_trapdoor.close");
    public static final SoundEvent BLOCK_COPPER_TRAPDOOR_OPEN = register("block.copper_trapdoor.open");

    public static final SoundEvent BLOCK_DIRT_BRICKS_BREAK = register("block.dirt_bricks.break");
    public static final SoundEvent BLOCK_DIRT_BRICKS_FALL = register("block.dirt_bricks.fall");
    public static final SoundEvent BLOCK_DIRT_BRICKS_HIT = register("block.dirt_bricks.hit");
    public static final SoundEvent BLOCK_DIRT_BRICKS_PLACE = register("block.dirt_bricks.place");
    public static final SoundEvent BLOCK_DIRT_BRICKS_STEP = register("block.dirt_bricks.step");

    public static final SoundEvent BLOCK_FLOWER_POT_PLANT = register("block.flower_pot.plant");

    public static final SoundEvent BLOCK_KILN_CRACKLE = register("block.kiln.crackle");
    
    public static final SoundEvent BLOCK_POLISHED_CALCITE_BREAK = register("block.polished_calcite.break");
    public static final SoundEvent BLOCK_POLISHED_CALCITE_FALL = register("block.polished_calcite.fall");
    public static final SoundEvent BLOCK_POLISHED_CALCITE_HIT = register("block.polished_calcite.hit");
    public static final SoundEvent BLOCK_POLISHED_CALCITE_PLACE = register("block.polished_calcite.place");
    public static final SoundEvent BLOCK_POLISHED_CALCITE_STEP = register("block.polished_calcite.step");

    public static final SoundEvent BLOCK_STICK_BUNDLE_BREAK = register("block.stick_bundle.break");
    public static final SoundEvent BLOCK_STICK_BUNDLE_PLACE = register("block.stick_bundle.place");

    public static final SoundEvent ENTITY_SNOWBALL_HIT = register("entity.snowball.hit");

    public static final SoundEvent ITEM_HOE_SCRAPE = register("item.hoe.scrape");

    private static SoundEvent register(String id) {
        return Registry.register(Registry.SOUND_EVENT, new Identifier(ChikoritaLoverMod.MODID, id), new SoundEvent(new Identifier(ChikoritaLoverMod.MODID, id)));
    }

    public static void register() {}
}
