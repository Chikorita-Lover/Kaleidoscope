package com.chikoritalover.kaleidoscope.registry;

import com.chikoritalover.kaleidoscope.Kaleidoscope;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;

public class KaleidoscopeSoundEvents {
    public static final SoundEvent BLOCK_COPPER_DOOR_CLOSE = register("block.copper_door.close");
    public static final SoundEvent BLOCK_COPPER_DOOR_OPEN = register("block.copper_door.open");
    public static final SoundEvent BLOCK_COPPER_TRAPDOOR_CLOSE = register("block.copper_trapdoor.close");
    public static final SoundEvent BLOCK_COPPER_TRAPDOOR_OPEN = register("block.copper_trapdoor.open");
    public static final SoundEvent BLOCK_KILN_CRACKLE = register("block.kiln.crackle");

    public static final SoundEvent ENTITY_VILLAGER_WORK_GLASSBLOWER = register("entity.villager.work_glassblower");

    public static final SoundEvent ITEM_HOE_SCRAPE = register("item.hoe.scrape");

    private static SoundEvent register(String id) {
        return Registry.register(Registries.SOUND_EVENT, new Identifier(Kaleidoscope.MODID, id), SoundEvent.of(new Identifier(Kaleidoscope.MODID, id)));
    }

    public static void register() {}
}
