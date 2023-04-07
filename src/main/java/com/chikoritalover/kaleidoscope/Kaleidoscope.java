package com.chikoritalover.kaleidoscope;

import com.chikoritalover.kaleidoscope.recipe.KilnCookingRecipe;
import com.chikoritalover.kaleidoscope.registry.*;
import com.chikoritalover.kaleidoscope.screen.KilnScreenHandler;
import com.google.common.collect.ImmutableSet;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerEntityEvents;
import net.fabricmc.fabric.api.loot.v2.LootTableEvents;
import net.fabricmc.fabric.api.screenhandler.v1.ScreenHandlerRegistry;
import net.fabricmc.fabric.mixin.content.registry.GiveGiftsToHeroTaskAccessor;
import net.minecraft.entity.mob.EndermanEntity;
import net.minecraft.entity.mob.Monster;
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
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.village.VillagerProfession;
import net.minecraft.world.poi.PointOfInterestType;
import net.minecraft.world.poi.PointOfInterestTypes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Kaleidoscope implements ModInitializer {
	public static final VillagerProfession GLASSBLOWER;
	public static final RegistryKey<PointOfInterestType> GLASSBLOWER_POINT_OF_INTEREST;
	public static final String MODID = "kaleidoscope";
	public static final Logger LOGGER = LoggerFactory.getLogger(MODID);
	public static final CookingRecipeSerializer<KilnCookingRecipe> KILN_COOKING_RECIPE_SERIALIZER;
	public static final ScreenHandlerType<KilnScreenHandler> KILN_SCREEN_HANDLER;
	public static final RecipeType<KilnCookingRecipe> KILNING;

	static {
		GLASSBLOWER_POINT_OF_INTEREST = RegistryKey.of(Registry.POINT_OF_INTEREST_TYPE_KEY, new Identifier(MODID, "glassblower"));
		GLASSBLOWER = new VillagerProfession("glassblower", entry -> entry.matchesKey(GLASSBLOWER_POINT_OF_INTEREST), entry -> entry.matchesKey(GLASSBLOWER_POINT_OF_INTEREST), ImmutableSet.of(), ImmutableSet.of(), ModSoundEvents.ENTITY_VILLAGER_WORK_GLASSBLOWER);
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
		ModBlocks.registerFlammableBlocks();
		ModBlocks.registerMossyPairs();
		ModBlocks.registerOxidizablePairs();
		ModBlockEntities.register();
		ModCauldronBehavior.register();
		ModEnchantments.register();
		ModItems.register();
		ModItems.registerCompostingChances();
		ModItems.registerFuels();
		ModItems.registerMaxItemCounts();
		new ModModelPredicateProvider();
		ModSoundEvents.register();
		ModStats.register();
		ModTradeOffers.register();

		registerLootTableEvents();

		PointOfInterestTypes.register(Registry.POINT_OF_INTEREST_TYPE, GLASSBLOWER_POINT_OF_INTEREST, PointOfInterestTypes.getStatesOfBlock(ModBlocks.KILN), 1, 1);
		Registry.register(Registry.VILLAGER_PROFESSION, new Identifier(MODID, "glassblower"), GLASSBLOWER);

		GiveGiftsToHeroTaskAccessor.fabric_getGifts().put(GLASSBLOWER, LootTables.registerLootTable(new Identifier(MODID, "gameplay/hero_of_the_village/glassblower_gift")));

		ServerEntityEvents.ENTITY_LOAD.register(((entity, world) -> {
			if (entity instanceof EndermanEntity endermanEntity) {
				endermanEntity.experiencePoints = Monster.STRONG_MONSTER_XP;
			}
		}));
	}

	public void registerLootTableEvents() {
		addLootTablePool(1, 1, 0.294F, LootTables.SIMPLE_DUNGEON_CHEST, ModItems.CHAINMAIL_HORSE_ARMOR);
		addLootTablePool(1, 1, 0.318F, LootTables.VILLAGE_ARMORER_CHEST, ModItems.CHAINMAIL_HORSE_ARMOR);
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
