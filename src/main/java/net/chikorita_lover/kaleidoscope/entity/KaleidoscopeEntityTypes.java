package net.chikorita_lover.kaleidoscope.entity;

import net.chikorita_lover.kaleidoscope.Kaleidoscope;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;

public class KaleidoscopeEntityTypes {
    public static final EntityType<JukeboxMinecartEntity> JUKEBOX_MINECART = register("jukebox_minecart", EntityType.Builder.<JukeboxMinecartEntity>create(JukeboxMinecartEntity::new, SpawnGroup.MISC).setDimensions(0.98F, 0.7F).maxTrackingRange(8));

    public static void register() {
    }

    private static <T extends Entity> EntityType<T> register(String id, EntityType.Builder<T> entityType) {
        return Registry.register(Registries.ENTITY_TYPE, Kaleidoscope.of(id), entityType.build(id));
    }
}
