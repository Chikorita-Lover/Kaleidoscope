package com.chikoritalover.kaleidoscope.registry;

import com.chikoritalover.kaleidoscope.Kaleidoscope;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;

public class KaleidoscopeSoundEvents {
    public static final RegistryEntry.Reference<SoundEvent> BLOCK_NOTE_BLOCK_SAXOPHONE = registerReference("block.note_block.saxophone");
    public static final SoundEvent BLOCK_FIREWORKS_TABLE_TAKE_RESULT = register("block.fireworks_table.take_result");
    public static final SoundEvent BLOCK_KILN_CRACKLE = register("block.kiln.crackle");

    public static final SoundEvent ENTITY_VILLAGER_WORK_FIREWORKER = register("entity.villager.work_fireworker");
    public static final SoundEvent ENTITY_VILLAGER_WORK_GLASSBLOWER = register("entity.villager.work_glassblower");

    public static final SoundEvent ITEM_HOE_SCRAPE = register("item.hoe.scrape");

    private static SoundEvent register(String id) {
        return Registry.register(Registries.SOUND_EVENT, new Identifier(Kaleidoscope.MODID, id), SoundEvent.of(new Identifier(Kaleidoscope.MODID, id)));
    }

    private static RegistryEntry.Reference<SoundEvent> registerReference(String id) {
        return registerReference(new Identifier(Kaleidoscope.MODID, id));
    }

    private static RegistryEntry.Reference<SoundEvent> registerReference(Identifier id) {
        return registerReference(id, id);
    }

    private static RegistryEntry.Reference<SoundEvent> registerReference(Identifier id, Identifier soundId) {
        return Registry.registerReference(Registries.SOUND_EVENT, id, SoundEvent.of(soundId));
    }

    public static void register() {}
}
