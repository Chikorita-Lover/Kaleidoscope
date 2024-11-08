package net.chikorita_lover.kaleidoscope.registry;

import com.google.common.collect.ImmutableSet;
import net.chikorita_lover.kaleidoscope.Kaleidoscope;
import net.chikorita_lover.kaleidoscope.block.KaleidoscopeBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.registry.Registries;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.world.poi.PointOfInterestType;
import net.minecraft.world.poi.PointOfInterestTypes;

import java.util.Set;

public class KaleidoscopePointOfInterestTypes {
    public static final RegistryKey<PointOfInterestType> FIREWORKER = register("fireworker", getStatesOfBlock(KaleidoscopeBlocks.FIREWORKS_TABLE), 1, 1);
    public static final RegistryKey<PointOfInterestType> GLASSBLOWER = register("glassblower", getStatesOfBlock(KaleidoscopeBlocks.KILN), 1, 1);

    public static void register() {
    }

    private static RegistryKey<PointOfInterestType> register(String id, Set<BlockState> states, int ticketCount, int searchDistance) {
        RegistryKey<PointOfInterestType> key = RegistryKey.of(RegistryKeys.POINT_OF_INTEREST_TYPE, Kaleidoscope.of(id));
        PointOfInterestTypes.register(Registries.POINT_OF_INTEREST_TYPE, key, states, ticketCount, searchDistance);
        return key;
    }

    private static Set<BlockState> getStatesOfBlock(Block block) {
        return ImmutableSet.copyOf(block.getStateManager().getStates());
    }
}
