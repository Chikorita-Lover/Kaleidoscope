package net.chikorita_lover.kaleidoscope.integration;

import com.terraformersmc.modmenu.api.ConfigScreenFactory;
import com.terraformersmc.modmenu.api.ModMenuApi;
import net.chikorita_lover.chicory.client.gui.ConfigScreen;
import net.chikorita_lover.kaleidoscope.KaleidoscopeConfig;

public class ModMenuIntegration implements ModMenuApi {
    @Override
    public ConfigScreenFactory<?> getModConfigScreenFactory() {
        return parent -> new ConfigScreen(parent, KaleidoscopeConfig.INSTANCE);
    }
}
