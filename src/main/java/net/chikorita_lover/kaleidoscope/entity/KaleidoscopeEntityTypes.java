package net.chikorita_lover.kaleidoscope.entity;

import net.chikorita_lover.chicory.api.resource.ToggleableFeatureRegistry;
import net.chikorita_lover.kaleidoscope.Kaleidoscope;
import net.chikorita_lover.kaleidoscope.KaleidoscopeConfig;
import net.chikorita_lover.kaleidoscope.item.KaleidoscopeItems;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.entity.vehicle.BoatEntity;
import net.minecraft.entity.vehicle.ChestBoatEntity;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;

import java.util.function.Supplier;

public class KaleidoscopeEntityTypes {
    public static final EntityType<BoatEntity> CRIMSON_BOAT = register("crimson_boat", EntityType.Builder.create(getBoatFactory(() -> KaleidoscopeItems.CRIMSON_BOAT), SpawnGroup.MISC).dropsNothing().makeFireImmune().dimensions(1.375F, 0.5625F).eyeHeight(0.5625F).maxTrackingRange(10));
    public static final EntityType<ChestBoatEntity> CRIMSON_CHEST_BOAT = register("crimson_chest_boat", EntityType.Builder.create(getChestBoatFactory(() -> KaleidoscopeItems.CRIMSON_CHEST_BOAT), SpawnGroup.MISC).dropsNothing().makeFireImmune().dimensions(1.375F, 0.5625F).eyeHeight(0.5625F).maxTrackingRange(10));
    public static final EntityType<JukeboxMinecartEntity> JUKEBOX_MINECART = register("jukebox_minecart", EntityType.Builder.<JukeboxMinecartEntity>create(JukeboxMinecartEntity::new, SpawnGroup.MISC).dimensions(0.98F, 0.7F).passengerAttachments(0.1875F).maxTrackingRange(8));
    public static final EntityType<BoatEntity> WARPED_BOAT = register("warped_boat", EntityType.Builder.create(getBoatFactory(() -> KaleidoscopeItems.WARPED_BOAT), SpawnGroup.MISC).dropsNothing().makeFireImmune().dimensions(1.375F, 0.5625F).eyeHeight(0.5625F).maxTrackingRange(10));
    public static final EntityType<ChestBoatEntity> WARPED_CHEST_BOAT = register("warped_chest_boat", EntityType.Builder.create(getChestBoatFactory(() -> KaleidoscopeItems.WARPED_CHEST_BOAT), SpawnGroup.MISC).dropsNothing().makeFireImmune().dimensions(1.375F, 0.5625F).eyeHeight(0.5625F).maxTrackingRange(10));

    private static EntityType.EntityFactory<BoatEntity> getBoatFactory(final Supplier<Item> itemSupplier) {
        return (type, world) -> new BoatEntity(type, world, itemSupplier);
    }

    private static EntityType.EntityFactory<ChestBoatEntity> getChestBoatFactory(final Supplier<Item> itemSupplier) {
        return (type, world) -> new ChestBoatEntity(type, world, itemSupplier);
    }

    private static <T extends Entity> EntityType<T> register(String id, EntityType.Builder<T> entityType) {
        return Registry.register(Registries.ENTITY_TYPE, Kaleidoscope.of(id), entityType.build(RegistryKey.of(RegistryKeys.ENTITY_TYPE, Kaleidoscope.of(id))));
    }

    public static void register() {
        ToggleableFeatureRegistry.add(CRIMSON_BOAT, KaleidoscopeConfig.NETHER_BOATS);
        ToggleableFeatureRegistry.add(CRIMSON_CHEST_BOAT, KaleidoscopeConfig.NETHER_BOATS);
        ToggleableFeatureRegistry.add(JUKEBOX_MINECART, KaleidoscopeConfig.JUKEBOX_MINECARTS);
        ToggleableFeatureRegistry.add(WARPED_BOAT, KaleidoscopeConfig.NETHER_BOATS);
        ToggleableFeatureRegistry.add(WARPED_CHEST_BOAT, KaleidoscopeConfig.NETHER_BOATS);
    }
}
