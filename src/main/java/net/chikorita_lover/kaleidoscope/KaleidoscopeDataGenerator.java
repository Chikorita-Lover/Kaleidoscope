package net.chikorita_lover.kaleidoscope;

import net.chikorita_lover.kaleidoscope.data.KaleidoscopeLootTableProvider;
import net.chikorita_lover.kaleidoscope.data.KaleidoscopeModelProvider;
import net.chikorita_lover.kaleidoscope.data.KaleidoscopeRecipeProvider;
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;

public class KaleidoscopeDataGenerator implements DataGeneratorEntrypoint {
    public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
        fabricDataGenerator.createPack().addProvider(KaleidoscopeLootTableProvider::new);
        fabricDataGenerator.createPack().addProvider(KaleidoscopeModelProvider::new);
        fabricDataGenerator.createPack().addProvider(KaleidoscopeRecipeProvider::new);
    }
}
