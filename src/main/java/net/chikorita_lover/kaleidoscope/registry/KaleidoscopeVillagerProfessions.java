package net.chikorita_lover.kaleidoscope.registry;

import com.google.common.collect.ImmutableSet;
import net.chikorita_lover.kaleidoscope.Kaleidoscope;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.sound.SoundEvent;
import net.minecraft.text.Text;
import net.minecraft.village.VillagerProfession;
import net.minecraft.world.poi.PointOfInterestType;
import org.jetbrains.annotations.Nullable;

public class KaleidoscopeVillagerProfessions {
    public static final RegistryKey<VillagerProfession> FIREWORKER = register("fireworker", KaleidoscopePointOfInterestTypes.FIREWORKER, KaleidoscopeSoundEvents.ENTITY_VILLAGER_WORK_FIREWORKER);
    public static final RegistryKey<VillagerProfession> GLASSBLOWER = register("glassblower", KaleidoscopePointOfInterestTypes.GLASSBLOWER, KaleidoscopeSoundEvents.ENTITY_VILLAGER_WORK_GLASSBLOWER);

    private static RegistryKey<VillagerProfession> register(String id, RegistryKey<PointOfInterestType> heldWorkstation, @Nullable SoundEvent workSound) {
        return register(id, heldWorkstation, ImmutableSet.of(), ImmutableSet.of(), workSound);
    }

    private static RegistryKey<VillagerProfession> register(String id, final RegistryKey<PointOfInterestType> heldWorkstation, ImmutableSet<Item> gatherableItems, ImmutableSet<Block> secondaryJobSites, @Nullable SoundEvent workSound) {
        VillagerProfession profession = new VillagerProfession(Text.translatable("entity." + Kaleidoscope.MODID + ".villager." + id), entry -> entry.matchesKey(heldWorkstation), entry -> entry.matchesKey(heldWorkstation), gatherableItems, secondaryJobSites, workSound);
        Registry.register(Registries.VILLAGER_PROFESSION, Kaleidoscope.of(id), profession);
        return RegistryKey.of(RegistryKeys.VILLAGER_PROFESSION, Kaleidoscope.of(id));
    }

    public static void register() {
    }
}
