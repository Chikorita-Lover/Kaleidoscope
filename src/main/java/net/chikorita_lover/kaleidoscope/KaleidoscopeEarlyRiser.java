package net.chikorita_lover.kaleidoscope;

import com.chocohead.mm.api.ClassTinkerers;
import net.fabricmc.loader.api.FabricLoader;
import net.fabricmc.loader.api.MappingResolver;

public class KaleidoscopeEarlyRiser implements Runnable {
    @Override
    public void run() {
        MappingResolver mappingResolver = FabricLoader.getInstance().getMappingResolver();
        String minecartType = mappingResolver.mapClassName("intermediary", "net.minecraft.class_1688$class_1689");
        ClassTinkerers.enumBuilder(minecartType).addEnum("JUKEBOX").build();
    }
}
