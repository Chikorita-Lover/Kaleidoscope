package com.chikorita_lover.chikorita_lover_mod;

import com.chikorita_lover.chikorita_lover_mod.recipe.KilnCookingRecipe;
import com.chikorita_lover.chikorita_lover_mod.registry.*;
import com.chikorita_lover.chikorita_lover_mod.screen.KilnScreenHandler;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.loot.v2.LootTableEvents;
import net.fabricmc.fabric.api.screenhandler.v1.ScreenHandlerRegistry;
import net.minecraft.item.ItemConvertible;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.LootTables;
import net.minecraft.loot.condition.LootCondition;
import net.minecraft.loot.condition.RandomChanceLootCondition;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.provider.number.UniformLootNumberProvider;
import net.minecraft.recipe.CookingRecipeSerializer;
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
		ModBlocks.register();
		ModBlocks.registerOxidizablePairs();
		ModBlockEntities.register();
		ModEnchantments.register();
		ModItems.register();
		ModItems.registerMaxItemCounts();
		ModSoundEvents.register();
		ModStats.register();
		ModTradeOffers.register();

		registerLootTableEvents();
	}

	public void registerLootTableEvents() {
		addLootTablePool(1, 1, 0.31F, LootTables.SIMPLE_DUNGEON_CHEST, ModItems.CHAINMAIL_HORSE_ARMOR);
		addLootTablePool(1, 1, 0.375F, LootTables.VILLAGE_ARMORER_CHEST, ModItems.CHAINMAIL_HORSE_ARMOR);
	}

	private void addLootTablePool(int minRolls, int maxRolls, float chance, Identifier lootTable, ItemConvertible item) {
		UniformLootNumberProvider lootTableRange = UniformLootNumberProvider.create(minRolls, maxRolls);
		LootCondition condition = RandomChanceLootCondition.builder(chance).build();
		LootTableEvents.MODIFY.register((resourceManager, lootManager, id, supplier, setter) -> {
			if (lootTable.equals(id)) {
				LootPool lootPool = LootPool.builder()
						.rolls(lootTableRange)
						.conditionally(condition)
						.with(ItemEntry.builder(item).build()).build();

				supplier.pool(lootPool);
			}
		});
	}
}
