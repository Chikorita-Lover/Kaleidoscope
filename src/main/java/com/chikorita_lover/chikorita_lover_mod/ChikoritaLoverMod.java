package com.chikorita_lover.chikorita_lover_mod;

import com.chikorita_lover.chikorita_lover_mod.recipe.KilnCookingRecipe;
import com.chikorita_lover.chikorita_lover_mod.registry.*;
import com.chikorita_lover.chikorita_lover_mod.screen.KilnScreenHandler;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.screenhandler.v1.ScreenHandlerRegistry;
import net.minecraft.recipe.CookingRecipeSerializer;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.recipe.RecipeType;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ChikoritaLoverMod implements ModInitializer {
	public static final String MODID = "chikorita_lover";
	public static final Logger LOGGER = LoggerFactory.getLogger(MODID);
	public static final CookingRecipeSerializer<KilnCookingRecipe> KILN_COOKING_RECIPE_SERIALIZER;
	public static final ScreenHandlerType<KilnScreenHandler> KILN_SCREEN_HANDLER;
	public static final RecipeType<KilnCookingRecipe> KILNING;

	static {
		KILN_COOKING_RECIPE_SERIALIZER = Registry.register(Registry.RECIPE_SERIALIZER, new Identifier(MODID, "kilning"), new CookingRecipeSerializer(KilnCookingRecipe::new, 100));
		KILN_SCREEN_HANDLER = ScreenHandlerRegistry.registerSimple(new Identifier(MODID, "kilning"), KilnScreenHandler::new);
		KILNING = Registry.register(Registry.RECIPE_TYPE, new Identifier(MODID, "kilning"), new RecipeType<KilnCookingRecipe>() {
			@Override
			public String toString() {
				return "kilning";
			}
		});
	}

	@Override
	public void onInitialize() {
		// This code runs as soon as Minecraft is in a mod-load-ready state.
		// However, some things (like resources) may still be uninitialized.
		// Proceed with mild caution.

		ModBlocks.register();
		ModBlocks.registerOxidizablePairs();
		ModBlockEntities.register();
		ModItems.register();
		ModSoundEvents.register();
		ModStats.register();
	}
}
