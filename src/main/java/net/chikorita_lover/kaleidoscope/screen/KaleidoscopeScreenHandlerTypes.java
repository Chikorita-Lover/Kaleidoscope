package net.chikorita_lover.kaleidoscope.screen;

import net.chikorita_lover.kaleidoscope.Kaleidoscope;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.resource.featuretoggle.FeatureFlags;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.ScreenHandlerType;

public class KaleidoscopeScreenHandlerTypes {
    public static final ScreenHandlerType<FireworksTableScreenHandler> FIREWORKS_TABLE = register("fireworks_table", (syncId, playerInventory) -> new FireworksTableScreenHandler(syncId, playerInventory));
    public static final ScreenHandlerType<KilnScreenHandler> KILN = register("kiln", (syncId, playerInventory) -> new KilnScreenHandler(syncId, playerInventory));

    public static void register() {
    }

    private static <T extends ScreenHandler> ScreenHandlerType<T> register(String path, ScreenHandlerType.Factory<T> factory) {
        return Registry.register(Registries.SCREEN_HANDLER, Kaleidoscope.of(path), new ScreenHandlerType<>(factory, FeatureFlags.VANILLA_FEATURES));
    }
}
