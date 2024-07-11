package net.chikorita_lover.kaleidoscope.registry;

import net.chikorita_lover.kaleidoscope.Kaleidoscope;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;

public class KaleidoscopeSoundEvents {
    public static final RegistryEntry.Reference<SoundEvent> BLOCK_NOTE_BLOCK_SAXOPHONE = registerReference("block.note_block.saxophone");
    public static final SoundEvent BLOCK_FIREWORKS_TABLE_TAKE_RESULT = register("block.fireworks_table.take_result");
    public static final SoundEvent BLOCK_KILN_CRACKLE = register("block.kiln.crackle");

    public static final SoundEvent ENTITY_BOAT_EQUIP_BANNER = register("entity.boat.equip_banner");
    public static final SoundEvent ENTITY_BOAT_SHEAR = register("entity.boat.shear");
    public static final SoundEvent ENTITY_CAMEL_MILK = register("entity.camel.milk");
    public static final SoundEvent ENTITY_PIG_SHEAR = register("entity.pig.shear");
    public static final SoundEvent ENTITY_STRIDER_CHEST = register("entity.strider.chest");
    public static final SoundEvent ENTITY_STRIDER_SHEAR = register("entity.strider.shear");
    public static final SoundEvent ENTITY_VILLAGER_WORK_FIREWORKER = register("entity.villager.work_fireworker");
    public static final SoundEvent ENTITY_VILLAGER_WORK_GLASSBLOWER = register("entity.villager.work_glassblower");

    public static final SoundEvent ITEM_SHOVEL_SCOOP_MUD = register("item.shovel.scoop_mud");
    public static final SoundEvent ITEM_HOE_SCRAPE = register("item.hoe.scrape");

    public static final SoundEvent RANDOM_BLOCK_CRACK = register("random.block_crack");

    private static SoundEvent register(String id) {
        return Registry.register(Registries.SOUND_EVENT, Kaleidoscope.of(id), SoundEvent.of(Kaleidoscope.of(id)));
    }

    private static RegistryEntry.Reference<SoundEvent> registerReference(String id) {
        return registerReference(Kaleidoscope.of(id));
    }

    private static RegistryEntry.Reference<SoundEvent> registerReference(Identifier id) {
        return registerReference(id, id);
    }

    private static RegistryEntry.Reference<SoundEvent> registerReference(Identifier id, Identifier soundId) {
        return Registry.registerReference(Registries.SOUND_EVENT, id, SoundEvent.of(soundId));
    }

    public static void register() {}
}
