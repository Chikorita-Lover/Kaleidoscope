package com.chikoritalover.kaleidoscope;

import com.chikoritalover.kaleidoscope.mixin.StructurePoolMixin;
import com.chikoritalover.kaleidoscope.recipe.KilnCookingRecipe;
import com.chikoritalover.kaleidoscope.registry.*;
import com.chikoritalover.kaleidoscope.screen.KilnScreenHandler;
import com.chocohead.mm.api.ClassTinkerers;
import com.google.common.collect.ImmutableSet;
import com.mojang.datafixers.util.Pair;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerEntityEvents;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents;
import net.fabricmc.fabric.api.loot.v2.LootTableEvents;
import net.fabricmc.fabric.api.screenhandler.v1.ScreenHandlerRegistry;
import net.fabricmc.fabric.mixin.content.registry.GiveGiftsToHeroTaskAccessor;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.mob.EndermanEntity;
import net.minecraft.entity.mob.Monster;
import net.minecraft.item.ItemConvertible;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.LootTables;
import net.minecraft.loot.condition.LootCondition;
import net.minecraft.loot.condition.RandomChanceLootCondition;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.entry.LootTableEntry;
import net.minecraft.loot.function.SetCountLootFunction;
import net.minecraft.loot.provider.number.UniformLootNumberProvider;
import net.minecraft.recipe.CookingRecipeSerializer;
import net.minecraft.recipe.RecipeType;
import net.minecraft.recipe.book.RecipeBookCategory;
import net.minecraft.registry.*;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.server.MinecraftServer;
import net.minecraft.structure.pool.FeaturePoolElement;
import net.minecraft.structure.pool.SinglePoolElement;
import net.minecraft.structure.pool.StructurePool;
import net.minecraft.structure.pool.StructurePoolElement;
import net.minecraft.structure.processor.StructureProcessorList;
import net.minecraft.structure.processor.StructureProcessorLists;
import net.minecraft.util.Identifier;
import net.minecraft.village.VillagerProfession;
import net.minecraft.world.gen.feature.PlacedFeature;
import net.minecraft.world.poi.PointOfInterestType;
import net.minecraft.world.poi.PointOfInterestTypes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Optional;

public class Kaleidoscope implements ModInitializer {
	public static final VillagerProfession GLASSBLOWER;
	public static final RegistryKey<PointOfInterestType> GLASSBLOWER_POINT_OF_INTEREST;
	public static final String MODID = "kaleidoscope";
	public static final Logger LOGGER = LoggerFactory.getLogger(MODID);
	public static final CookingRecipeSerializer<KilnCookingRecipe> KILN_COOKING_RECIPE_SERIALIZER;
	public static final ScreenHandlerType<KilnScreenHandler> KILN_SCREEN_HANDLER;
	public static final RecipeType<KilnCookingRecipe> KILNING;
	public static final RecipeBookCategory KILNING_CATEGORY;

	static {
		GLASSBLOWER_POINT_OF_INTEREST = RegistryKey.of(RegistryKeys.POINT_OF_INTEREST_TYPE, new Identifier(MODID, "glassblower"));
		GLASSBLOWER = new VillagerProfession("glassblower", entry -> entry.matchesKey(GLASSBLOWER_POINT_OF_INTEREST), entry -> entry.matchesKey(GLASSBLOWER_POINT_OF_INTEREST), ImmutableSet.of(), ImmutableSet.of(), KaleidoscopeSoundEvents.ENTITY_VILLAGER_WORK_GLASSBLOWER);
		KILN_COOKING_RECIPE_SERIALIZER = Registry.register(Registries.RECIPE_SERIALIZER, new Identifier(MODID, "kilning"), new CookingRecipeSerializer(KilnCookingRecipe::new, 100));
		KILN_SCREEN_HANDLER = ScreenHandlerRegistry.registerSimple(new Identifier(MODID, "kilning"), KilnScreenHandler::new);
		KILNING = Registry.register(Registries.RECIPE_TYPE, new Identifier(MODID, "kilning"), new RecipeType<KilnCookingRecipe>() {
			@Override
			public String toString() {
				return "kilning";
			}
		});
		KILNING_CATEGORY = ClassTinkerers.getEnum(RecipeBookCategory.class, "KALEIDOSCOPE_KILN");
	}

	@Override
	public void onInitialize() {
		KaleidoscopeBlocks.registerFlammableBlocks();
		KaleidoscopeBlocks.registerMossyPairs();
		KaleidoscopeBlocks.registerOxidizablePairs();
		KaleidoscopeBlockEntities.register();
		KaleidoscopeCauldronBehavior.register();
		KaleidoscopeEnchantments.register();
		KaleidoscopeItemGroups.register();
		KaleidoscopeItems.registerCompostingChances();
		KaleidoscopeItems.registerFuels();
		KaleidoscopeItems.registerMaxItemCounts();
		new KaleidoscopeModelPredicateProvider();
		KaleidoscopeSoundEvents.register();
		KaleidoscopeStats.register();
		KaleidoscopeTradeOffers.register();

		registerLootTableEvents();

		PointOfInterestTypes.register(Registries.POINT_OF_INTEREST_TYPE, GLASSBLOWER_POINT_OF_INTEREST, PointOfInterestTypes.getStatesOfBlock(KaleidoscopeBlocks.KILN), 1, 1);
		Registry.register(Registries.VILLAGER_PROFESSION, new Identifier(MODID, "glassblower"), GLASSBLOWER);

		GiveGiftsToHeroTaskAccessor.fabric_getGifts().put(GLASSBLOWER, LootTables.registerLootTable(new Identifier(MODID, "gameplay/hero_of_the_village/glassblower_gift")));

		ServerLifecycleEvents.SERVER_STARTING.register(server -> {
			RegistryEntryLookup<PlacedFeature> registryEntryLookup = server.getRegistryManager().createRegistryLookup().getOrThrow(RegistryKeys.PLACED_FEATURE);
			RegistryEntryLookup<StructureProcessorList> registryEntryLookup2 = server.getRegistryManager().createRegistryLookup().getOrThrow(RegistryKeys.PROCESSOR_LIST);
			addToStructurePool(server, new Identifier("minecraft", "village/desert/houses"), new Identifier(MODID, "village/desert/houses/desert_glassblower_1"), 4);
			addToStructurePool(server, new Identifier("minecraft", "village/plains/houses"), new Identifier(MODID, "village/plains/houses/plains_glassblower_1"), registryEntryLookup2.getOrThrow(StructureProcessorLists.MOSSIFY_10_PERCENT), 4);
			addToStructurePool(server, new Identifier("minecraft", "village/savanna/houses"), new Identifier(MODID, "village/savanna/houses/savanna_glassblower_1"), 4);
			addToStructurePool(server, new Identifier("minecraft", "village/snowy/decor"), registryEntryLookup.getOrThrow(KaleidoscopePlacedFeatures.PILE_STICK_BUNDLE), 1);
			addToStructurePool(server, new Identifier("minecraft", "village/snowy/houses"), new Identifier(MODID, "village/snowy/houses/snowy_glassblower_1"), 4);
			addToStructurePool(server, new Identifier("minecraft", "village/taiga/decor"), registryEntryLookup.getOrThrow(KaleidoscopePlacedFeatures.PILE_STICK_BUNDLE), 1);
			addToStructurePool(server, new Identifier("minecraft", "village/taiga/houses"), new Identifier(MODID, "village/taiga/houses/taiga_glassblower_1"), 4);
		});

		ServerEntityEvents.ENTITY_LOAD.register(((entity, world) -> {
			if (entity instanceof EndermanEntity endermanEntity) {
				endermanEntity.experiencePoints = Monster.STRONG_MONSTER_XP;
			}
		}));
	}

	public static void addToStructurePool(MinecraftServer server, Identifier village, Identifier structure, int weight) {
		RegistryEntry<StructureProcessorList> emptyProcessorList = server.getRegistryManager().get(RegistryKeys.PROCESSOR_LIST).entryOf(RegistryKey.of(RegistryKeys.PROCESSOR_LIST, new Identifier("minecraft", "empty")));
		addToStructurePool(server, village, structure, emptyProcessorList, weight);
	}

	public static void addToStructurePool(MinecraftServer server, Identifier village, Identifier structure, RegistryEntry<StructureProcessorList> processorList, int weight) {
		Optional<StructurePool> poolGetter = server.getRegistryManager().get(RegistryKeys.TEMPLATE_POOL).getOrEmpty(village);
		StructurePool pool = poolGetter.get();
		var pieceList = ((StructurePoolMixin) pool).getElements();
		SinglePoolElement piece = StructurePoolElement.ofProcessedSingle(structure.toString(), processorList).apply(StructurePool.Projection.RIGID);
		var list = new ArrayList<>(((StructurePoolMixin) pool).getElementCounts());
		list.add(Pair.of(piece, weight));
		((StructurePoolMixin) pool).setElementCounts(list);
		for (int i = 0; i < weight; ++i) {
			pieceList.add(piece);
		}
	}

	public static void addToStructurePool(MinecraftServer server, Identifier village, RegistryEntry<PlacedFeature> placedFeature, int weight) {
		Optional<StructurePool> poolGetter = server.getRegistryManager().get(RegistryKeys.TEMPLATE_POOL).getOrEmpty(village);
		StructurePool pool = poolGetter.get();
		var pieceList = ((StructurePoolMixin) pool).getElements();
		FeaturePoolElement piece = StructurePoolElement.ofFeature(placedFeature).apply(StructurePool.Projection.RIGID);
		var list = new ArrayList<>(((StructurePoolMixin) pool).getElementCounts());
		list.add(Pair.of(piece, weight));
		((StructurePoolMixin) pool).setElementCounts(list);
		for (int i = 0; i < weight; ++i) {
			pieceList.add(piece);
		}
	}

	public void registerLootTableEvents() {
		addLootTablePool(1, 1, 0.294F, LootTables.SIMPLE_DUNGEON_CHEST, KaleidoscopeItems.CHAINMAIL_HORSE_ARMOR);
		addLootTablePool(1, 1, 0.318F, LootTables.VILLAGE_ARMORER_CHEST, KaleidoscopeItems.CHAINMAIL_HORSE_ARMOR);

		LootTableEvents.MODIFY.register((resourceManager, lootManager, id, supplier, setter) -> {
			if (id.equals(EntityType.GOAT.getLootTableId())) {
				supplier.pool(LootPool.builder().with(LootTableEntry.builder(new Identifier(Kaleidoscope.MODID, "entities/goat")).build()).build());
			}
			if (id.equals(LootTables.PIGLIN_BARTERING_GAMEPLAY)) {
				supplier.modifyPools(builder -> builder.with((ItemEntry.builder(KaleidoscopeItems.DISC_FRAGMENT_PIGSTEP).weight(10)).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0F, 3.0F)))).build());
			}
		});
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
