package com.chikorita_lover.chikorita_lover_mod;

import com.chikorita_lover.chikorita_lover_mod.registry.ModBlockSoundGroup;
import com.chikorita_lover.chikorita_lover_mod.registry.ModBlocks;
import com.chikorita_lover.chikorita_lover_mod.registry.ModItems;
import com.chikorita_lover.chikorita_lover_mod.registry.ModSoundEvents;
import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ChikoritaLoverMod implements ModInitializer {
	public static final String MODID = "chikorita_lover";
	public static final Logger LOGGER = LoggerFactory.getLogger(MODID);

	@Override
	public void onInitialize() {
		// This code runs as soon as Minecraft is in a mod-load-ready state.
		// However, some things (like resources) may still be uninitialized.
		// Proceed with mild caution.

		ModBlocks.register();
		ModItems.register();
		ModSoundEvents.register();
	}
}
