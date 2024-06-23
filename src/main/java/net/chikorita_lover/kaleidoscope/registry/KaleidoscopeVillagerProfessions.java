package net.chikorita_lover.kaleidoscope.registry;

import com.google.common.collect.ImmutableSet;
import net.chikorita_lover.kaleidoscope.Kaleidoscope;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.sound.SoundEvent;
import net.minecraft.village.VillagerProfession;
import net.minecraft.world.poi.PointOfInterestType;
import org.jetbrains.annotations.Nullable;

public class KaleidoscopeVillagerProfessions {
    public static final VillagerProfession FIREWORKER = register("fireworker", Kaleidoscope.FIREWORKER_POINT_OF_INTEREST, KaleidoscopeSoundEvents.ENTITY_VILLAGER_WORK_FIREWORKER);
    public static final VillagerProfession GLASSBLOWER = register("glassblower", Kaleidoscope.GLASSBLOWER_POINT_OF_INTEREST, KaleidoscopeSoundEvents.ENTITY_VILLAGER_WORK_GLASSBLOWER);

    public static void register() {
    }

    private static VillagerProfession register(String id, RegistryKey<PointOfInterestType> heldWorkstation, @Nullable SoundEvent workSound) {
        return register(id, heldWorkstation, ImmutableSet.of(), ImmutableSet.of(), workSound);
    }

    private static VillagerProfession register(String id, RegistryKey<PointOfInterestType> heldWorkstation, ImmutableSet<Item> gatherableItems, ImmutableSet<Block> secondaryJobSites, @Nullable SoundEvent workSound) {
        return Registry.register(Registries.VILLAGER_PROFESSION, Kaleidoscope.of(id), new VillagerProfession(id, entry -> entry.matchesKey(heldWorkstation), entry -> entry.matchesKey(heldWorkstation), gatherableItems, secondaryJobSites, workSound));
    }
}
