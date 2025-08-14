package net.chikorita_lover.kaleidoscope;

import net.chikorita_lover.kaleidoscope.data.KaleidoscopeLootTableProvider;
import net.chikorita_lover.kaleidoscope.data.KaleidoscopeModelProvider;
import net.chikorita_lover.kaleidoscope.data.KaleidoscopeRecipeGenerator;
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;

public class KaleidoscopeDataGenerator implements DataGeneratorEntrypoint {
    public void onInitializeDataGenerator(FabricDataGenerator generator) {
        generator.createPack().addProvider(KaleidoscopeLootTableProvider::new);
        generator.createPack().addProvider(KaleidoscopeModelProvider::new);
        generator.createPack().addProvider(KaleidoscopeRecipeGenerator.Provider::new);
    }
}
