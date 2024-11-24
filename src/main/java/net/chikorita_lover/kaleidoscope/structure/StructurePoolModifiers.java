package net.chikorita_lover.kaleidoscope.structure;

import com.mojang.datafixers.util.Pair;
import it.unimi.dsi.fastutil.objects.Object2IntArrayMap;
import net.chikorita_lover.kaleidoscope.Kaleidoscope;
import net.chikorita_lover.kaleidoscope.mixin.structure.StructurePoolAccessor;
import net.chikorita_lover.kaleidoscope.registry.KaleidoscopePlacedFeatures;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.structure.pool.StructurePool;
import net.minecraft.structure.pool.StructurePoolElement;
import net.minecraft.structure.processor.StructureProcessorList;
import net.minecraft.structure.processor.StructureProcessorLists;
import net.minecraft.util.Identifier;
import net.minecraft.world.gen.feature.PlacedFeature;

public class StructurePoolModifiers {
    public static void register() {
        ServerLifecycleEvents.SERVER_STARTED.register(server -> {
            RegistryWrapper.WrapperLookup registries = server.getRegistryManager();
            final RegistryWrapper.Impl<StructureProcessorList> processorLists = registries.getWrapperOrThrow(RegistryKeys.PROCESSOR_LIST);
            final RegistryWrapper.Impl<PlacedFeature> placedFeatures = registries.getWrapperOrThrow(RegistryKeys.PLACED_FEATURE);
            final RegistryEntry<StructureProcessorList> mossify10Percent = processorLists.getOrThrow(StructureProcessorLists.MOSSIFY_10_PERCENT);
            modifyStructurePool(Identifier.of(Identifier.DEFAULT_NAMESPACE, "village/desert/houses"), registries, elements -> {
                addVillageHouse(elements, "village/desert/houses/desert_fireworker_1", 2);
                addVillageHouse(elements, "village/desert/houses/desert_glassblower_1", 3);
            });
            modifyStructurePool(Identifier.of(Identifier.DEFAULT_NAMESPACE, "village/desert/zombie/houses"), registries, elements -> {
                RegistryEntry<StructureProcessorList> zombieDesert = processorLists.getOrThrow(StructureProcessorLists.ZOMBIE_DESERT);
                addVillageHouse(elements, "village/desert/houses/desert_fireworker_1", zombieDesert, 2);
                addVillageHouse(elements, "village/desert/houses/desert_glassblower_1", zombieDesert, 3);
            });
            modifyStructurePool(Identifier.of(Identifier.DEFAULT_NAMESPACE, "village/plains/houses"), registries, elements -> {
                addVillageHouse(elements, "village/plains/houses/plains_fireworker_1", mossify10Percent, 3);
                addVillageHouse(elements, "village/plains/houses/plains_glassblower_1", mossify10Percent, 5);
            });
            modifyStructurePool(Identifier.of(Identifier.DEFAULT_NAMESPACE, "village/plains/zombie/houses"), registries, elements -> {
                RegistryEntry<StructureProcessorList> zombiePlains = processorLists.getOrThrow(StructureProcessorLists.ZOMBIE_PLAINS);
                addVillageHouse(elements, "village/plains/houses/plains_fireworker_1", zombiePlains, 3);
                addVillageHouse(elements, "village/plains/houses/plains_glassblower_1", zombiePlains, 5);
            });
            modifyStructurePool(Identifier.of(Identifier.DEFAULT_NAMESPACE, "village/savanna/houses"), registries, elements -> {
                addVillageHouse(elements, "village/savanna/houses/savanna_fireworker_1", 5);
                addVillageHouse(elements, "village/savanna/houses/savanna_fireworker_2", 3);
                addVillageHouse(elements, "village/savanna/houses/savanna_glassblower_1", 5);
            });
            modifyStructurePool(Identifier.of(Identifier.DEFAULT_NAMESPACE, "village/savanna/zombie/houses"), registries, elements -> {
                RegistryEntry<StructureProcessorList> zombieSavanna = processorLists.getOrThrow(StructureProcessorLists.ZOMBIE_SAVANNA);
                addVillageHouse(elements, "village/savanna/houses/savanna_fireworker_1", zombieSavanna, 5);
                addVillageHouse(elements, "village/savanna/houses/savanna_fireworker_2", zombieSavanna, 3);
                addVillageHouse(elements, "village/savanna/houses/savanna_glassblower_1", zombieSavanna, 5);
            });
            modifyStructurePool(Identifier.of(Identifier.DEFAULT_NAMESPACE, "village/snowy/decor"), registries, elements -> {
                elements.put(StructurePoolElement.ofFeature(placedFeatures.getOrThrow(KaleidoscopePlacedFeatures.PILE_STICK_BLOCK)).apply(StructurePool.Projection.RIGID), 2);
            });
            modifyStructurePool(Identifier.of(Identifier.DEFAULT_NAMESPACE, "village/snowy/houses"), registries, elements -> {
                addVillageHouse(elements, "village/snowy/houses/snowy_fireworker_1", 2);
                addVillageHouse(elements, "village/snowy/houses/snowy_glassblower_1", 3);
            });
            modifyStructurePool(Identifier.of(Identifier.DEFAULT_NAMESPACE, "village/snowy/zombie/decor"), registries, elements -> {
                elements.put(StructurePoolElement.ofFeature(placedFeatures.getOrThrow(KaleidoscopePlacedFeatures.PILE_STICK_BLOCK)).apply(StructurePool.Projection.RIGID), 2);
            });
            modifyStructurePool(Identifier.of(Identifier.DEFAULT_NAMESPACE, "village/snowy/zombie/houses"), registries, elements -> {
                RegistryEntry<StructureProcessorList> zombieSnowy = processorLists.getOrThrow(StructureProcessorLists.ZOMBIE_SNOWY);
                addVillageHouse(elements, "village/snowy/houses/snowy_fireworker_1", zombieSnowy, 2);
                addVillageHouse(elements, "village/snowy/houses/snowy_glassblower_1", zombieSnowy, 3);
            });
            modifyStructurePool(Identifier.of(Identifier.DEFAULT_NAMESPACE, "village/taiga/decor"), registries, elements -> {
                elements.put(StructurePoolElement.ofFeature(placedFeatures.getOrThrow(KaleidoscopePlacedFeatures.PILE_STICK_BLOCK)).apply(StructurePool.Projection.RIGID), 2);
            });
            modifyStructurePool(Identifier.of(Identifier.DEFAULT_NAMESPACE, "village/taiga/houses"), registries, elements -> {
                addVillageHouse(elements, "village/taiga/houses/taiga_fireworker_1", mossify10Percent, 2);
                addVillageHouse(elements, "village/taiga/houses/taiga_glassblower_1", mossify10Percent, 2);
            });
            modifyStructurePool(Identifier.of(Identifier.DEFAULT_NAMESPACE, "village/taiga/zombie/decor"), registries, elements -> {
                elements.put(StructurePoolElement.ofFeature(placedFeatures.getOrThrow(KaleidoscopePlacedFeatures.PILE_STICK_BLOCK)).apply(StructurePool.Projection.RIGID), 2);
            });
            modifyStructurePool(Identifier.of(Identifier.DEFAULT_NAMESPACE, "village/taiga/zombie/houses"), registries, elements -> {
                RegistryEntry<StructureProcessorList> zombieTaiga = processorLists.getOrThrow(StructureProcessorLists.ZOMBIE_TAIGA);
                addVillageHouse(elements, "village/taiga/houses/taiga_fireworker_1", zombieTaiga, 2);
                addVillageHouse(elements, "village/taiga/houses/taiga_glassblower_1", zombieTaiga, 2);
            });
        });
    }

    public static void modifyStructurePool(Identifier id, RegistryWrapper.WrapperLookup registries, Modifier modifier) {
        RegistryKey<StructurePool> pool = RegistryKey.of(RegistryKeys.TEMPLATE_POOL, id);
        StructurePoolAccessor accessor = (StructurePoolAccessor) registries.getWrapperOrThrow(RegistryKeys.TEMPLATE_POOL).getOrThrow(pool).value();
        Object2IntArrayMap<StructurePoolElement> builder = new Object2IntArrayMap<>();
        accessor.getElementCounts().forEach(pair -> builder.put(pair.getFirst(), pair.getSecond().intValue()));
        modifier.apply(builder);
        accessor.setElementCounts(builder.object2IntEntrySet().stream().map(entry -> Pair.of(entry.getKey(), entry.getIntValue())).toList());
        accessor.getElements().clear();
        builder.forEach((element, weight) -> {
            for (int i = 0; i < weight; ++i) {
                accessor.getElements().add(element);
            }
        });
    }

    private static void addVillageHouse(Object2IntArrayMap<StructurePoolElement> elements, String id, int weight) {
        elements.put(StructurePoolElement.ofLegacySingle(Kaleidoscope.of(id).toString()).apply(StructurePool.Projection.RIGID), weight);
    }

    private static void addVillageHouse(Object2IntArrayMap<StructurePoolElement> elements, String id, RegistryEntry<StructureProcessorList> processorList, int weight) {
        elements.put(StructurePoolElement.ofProcessedLegacySingle(Kaleidoscope.of(id).toString(), processorList).apply(StructurePool.Projection.RIGID), weight);
    }

    @FunctionalInterface
    public interface Modifier {
        void apply(Object2IntArrayMap<StructurePoolElement> elements);
    }
}
