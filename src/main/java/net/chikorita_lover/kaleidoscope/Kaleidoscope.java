package net.chikorita_lover.kaleidoscope;

import com.mojang.datafixers.util.Pair;
import net.chikorita_lover.kaleidoscope.block.CrackedBlockRegistry;
import net.chikorita_lover.kaleidoscope.block.KaleidoscopeBlocks;
import net.chikorita_lover.kaleidoscope.block.KaleidoscopeCauldronBehavior;
import net.chikorita_lover.kaleidoscope.block.entity.KaleidoscopeBlockEntityTypes;
import net.chikorita_lover.kaleidoscope.item.KaleidoscopeItemGroups;
import net.chikorita_lover.kaleidoscope.item.KaleidoscopeItems;
import net.chikorita_lover.kaleidoscope.mixin.structure.StructurePoolAccessor;
import net.chikorita_lover.kaleidoscope.network.OpenStriderScreenS2CPacket;
import net.chikorita_lover.kaleidoscope.recipe.KaleidoscopeRecipeSerializers;
import net.chikorita_lover.kaleidoscope.recipe.KilningRecipe;
import net.chikorita_lover.kaleidoscope.registry.*;
import net.chikorita_lover.kaleidoscope.screen.KaleidoscopeScreenHandlerTypes;
import net.chikorita_lover.kaleidoscope.structure.EndCityStructureProcessor;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents;
import net.fabricmc.fabric.api.loot.v2.LootTableEvents;
import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry;
import net.minecraft.block.Blocks;
import net.minecraft.block.DispenserBlock;
import net.minecraft.block.dispenser.ShearsDispenserBehavior;
import net.minecraft.loot.LootTables;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.function.SetCountLootFunction;
import net.minecraft.loot.provider.number.UniformLootNumberProvider;
import net.minecraft.recipe.Recipe;
import net.minecraft.recipe.RecipeType;
import net.minecraft.registry.*;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.server.MinecraftServer;
import net.minecraft.structure.pool.FeaturePoolElement;
import net.minecraft.structure.pool.SinglePoolElement;
import net.minecraft.structure.pool.StructurePool;
import net.minecraft.structure.pool.StructurePoolElement;
import net.minecraft.structure.processor.*;
import net.minecraft.structure.rule.AlwaysTrueRuleTest;
import net.minecraft.structure.rule.RandomBlockMatchRuleTest;
import net.minecraft.util.Identifier;
import net.minecraft.world.gen.feature.PlacedFeature;
import net.minecraft.world.poi.PointOfInterestType;
import net.minecraft.world.poi.PointOfInterestTypes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Kaleidoscope implements ModInitializer {
    public static final Logger LOGGER = LoggerFactory.getLogger("Kaleidoscope");
    public static final String MODID = "kaleidoscope";
    public static final RegistryKey<PointOfInterestType> FIREWORKER_POINT_OF_INTEREST = RegistryKey.of(RegistryKeys.POINT_OF_INTEREST_TYPE, of("fireworker"));
    public static final RegistryKey<PointOfInterestType> GLASSBLOWER_POINT_OF_INTEREST = RegistryKey.of(RegistryKeys.POINT_OF_INTEREST_TYPE, of("glassblower"));
    public static final RecipeType<KilningRecipe> KILNING = registerRecipeType("kilning");
    public static final StructureProcessorType<EndCityStructureProcessor> END_CITY_STRUCTURE_PROCESSOR = Registry.register(Registries.STRUCTURE_PROCESSOR, of("end_city"), () -> EndCityStructureProcessor.CODEC);

    public static Identifier of(String path) {
        return Identifier.of(MODID, path);
    }

    private static void addToStructurePool(MinecraftServer server, Identifier village, Identifier structure, int weight) {
        RegistryEntry<StructureProcessorList> emptyProcessorList = server.getRegistryManager().get(RegistryKeys.PROCESSOR_LIST).entryOf(RegistryKey.of(RegistryKeys.PROCESSOR_LIST, Identifier.of("empty")));
        addToStructurePool(server, village, structure, emptyProcessorList, weight);
    }

    private static void addToStructurePool(MinecraftServer server, Identifier village, Identifier structure, RegistryEntry<StructureProcessorList> processorList, int weight) {
        Optional<StructurePool> optionalPool = server.getRegistryManager().get(RegistryKeys.TEMPLATE_POOL).getOrEmpty(village);
        if (optionalPool.isPresent()) {
            StructurePool pool = optionalPool.get();
            var pieceList = ((StructurePoolAccessor) pool).getElements();
            SinglePoolElement piece = StructurePoolElement.ofProcessedSingle(structure.toString(), processorList).apply(StructurePool.Projection.RIGID);
            var list = new ArrayList<>(((StructurePoolAccessor) pool).getElementCounts());
            list.add(Pair.of(piece, weight));
            ((StructurePoolAccessor) pool).setElementCounts(list);
            for (int i = 0; i < weight; ++i) {
                pieceList.add(piece);
            }
        }
    }

    private static void addToStructurePool(MinecraftServer server, Identifier village, RegistryEntry<PlacedFeature> placedFeature, int weight) {
        Optional<StructurePool> poolGetter = server.getRegistryManager().get(RegistryKeys.TEMPLATE_POOL).getOrEmpty(village);
        StructurePool pool = poolGetter.get();
        var pieceList = ((StructurePoolAccessor) pool).getElements();
        FeaturePoolElement piece = StructurePoolElement.ofFeature(placedFeature).apply(StructurePool.Projection.RIGID);
        var list = new ArrayList<>(((StructurePoolAccessor) pool).getElementCounts());
        list.add(Pair.of(piece, weight));
        ((StructurePoolAccessor) pool).setElementCounts(list);
        for (int i = 0; i < weight; ++i) {
            pieceList.add(piece);
        }
    }

    private static void addRuleToProcessorList(StructureProcessorList processorList, RuleStructureProcessor processor) {
        if (processorList == null) {
            return;
        }
        ArrayList<StructureProcessor> list = new ArrayList<>(processorList.getList());
        list.add(processor);
        processorList.list = list;
    }

    private static void registerLootTableEvents() {
        LootTableEvents.MODIFY.register((key, tableBuilder, source) -> {
            if (key == LootTables.PIGLIN_BARTERING_GAMEPLAY) {
                tableBuilder.modifyPools(builder -> builder.with((ItemEntry.builder(KaleidoscopeItems.DISC_FRAGMENT_PIGSTEP).weight(10)).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0F, 3.0F)))).build());
            }
        });
    }

    private static <T extends Recipe<?>> RecipeType<T> registerRecipeType(String path) {
        return Registry.register(Registries.RECIPE_TYPE, of("kilning"), new RecipeType<T>() {
            @Override
            public String toString() {
                return path;
            }
        });
    }

    @Override
    public void onInitialize() {
        CrackedBlockRegistry.register();
        KaleidoscopeBlocks.registerFlammableBlocks();
        KaleidoscopeBlocks.registerMossyPairs();
        KaleidoscopeBlocks.registerOxidizablePairs();
        KaleidoscopeBlockEntityTypes.register();
        KaleidoscopeCauldronBehavior.register();
        KaleidoscopeItemGroups.register();
        KaleidoscopeItems.registerCompostingChances();
        KaleidoscopeItems.registerFuels();
        KaleidoscopeItems.registerMaxItemCounts();
        KaleidoscopeLootTables.register();
        KaleidoscopeRecipeSerializers.register();
        KaleidoscopeScreenHandlerTypes.register();
        KaleidoscopeSoundEvents.register();
        KaleidoscopeStats.register();
        KaleidoscopeTradeOffers.register();
        KaleidoscopeVillagerProfessions.register();

        registerLootTableEvents();

        DispenserBlock.registerBehavior(KaleidoscopeItems.NETHERITE_SHEARS, new ShearsDispenserBehavior());

        PayloadTypeRegistry.playS2C().register(OpenStriderScreenS2CPacket.PACKET_ID, OpenStriderScreenS2CPacket.PACKET_CODEC);

        PointOfInterestTypes.register(Registries.POINT_OF_INTEREST_TYPE, FIREWORKER_POINT_OF_INTEREST, PointOfInterestTypes.getStatesOfBlock(KaleidoscopeBlocks.FIREWORKS_TABLE), 1, 1);
        PointOfInterestTypes.register(Registries.POINT_OF_INTEREST_TYPE, GLASSBLOWER_POINT_OF_INTEREST, PointOfInterestTypes.getStatesOfBlock(KaleidoscopeBlocks.KILN), 1, 1);

        ServerLifecycleEvents.SERVER_STARTING.register(server -> {
            RegistryEntryLookup<PlacedFeature> featureRegistry = server.getRegistryManager().createRegistryLookup().getOrThrow(RegistryKeys.PLACED_FEATURE);
            RegistryEntryLookup<StructureProcessorList> processorRegistry = server.getRegistryManager().createRegistryLookup().getOrThrow(RegistryKeys.PROCESSOR_LIST);
            addToStructurePool(server, Identifier.of("village/desert/houses"), Identifier.of(MODID, "village/desert/houses/desert_glassblower_1"), 4);
            addToStructurePool(server, Identifier.of("village/plains/houses"), Identifier.of(MODID, "village/plains/houses/plains_glassblower_1"), processorRegistry.getOrThrow(StructureProcessorLists.MOSSIFY_10_PERCENT), 4);
            addToStructurePool(server, Identifier.of("village/savanna/houses"), Identifier.of(MODID, "village/savanna/houses/savanna_glassblower_1"), 4);
            addToStructurePool(server, Identifier.of("village/snowy/decor"), featureRegistry.getOrThrow(KaleidoscopePlacedFeatures.PILE_STICK_BLOCK), 1);
            addToStructurePool(server, Identifier.of("village/snowy/houses"), Identifier.of(MODID, "village/snowy/houses/snowy_glassblower_1"), 4);
            addToStructurePool(server, Identifier.of("village/taiga/decor"), featureRegistry.getOrThrow(KaleidoscopePlacedFeatures.PILE_STICK_BLOCK), 1);
            addToStructurePool(server, Identifier.of("village/taiga/houses"), Identifier.of(MODID, "village/taiga/houses/taiga_glassblower_1"), 4);

            Registry<StructureProcessorList> processorLists = server.getRegistryManager().get(RegistryKeys.PROCESSOR_LIST);
            if (processorLists != null) {
                addRuleToProcessorList(processorLists.get(StructureProcessorLists.TRAIL_RUINS_HOUSES_ARCHAEOLOGY), new RuleStructureProcessor(List.of(new StructureProcessorRule(new RandomBlockMatchRuleTest(Blocks.MUD_BRICKS, 0.2F), AlwaysTrueRuleTest.INSTANCE, KaleidoscopeBlocks.CRACKED_MUD_BRICKS.getDefaultState()))));
                addRuleToProcessorList(processorLists.get(StructureProcessorLists.TRAIL_RUINS_ROADS_ARCHAEOLOGY), new RuleStructureProcessor(List.of(new StructureProcessorRule(new RandomBlockMatchRuleTest(Blocks.MUD_BRICKS, 0.2F), AlwaysTrueRuleTest.INSTANCE, KaleidoscopeBlocks.CRACKED_MUD_BRICKS.getDefaultState()))));
                addRuleToProcessorList(processorLists.get(StructureProcessorLists.TRAIL_RUINS_TOWER_TOP_ARCHAEOLOGY), new RuleStructureProcessor(List.of(new StructureProcessorRule(new RandomBlockMatchRuleTest(Blocks.MUD_BRICKS, 0.2F), AlwaysTrueRuleTest.INSTANCE, KaleidoscopeBlocks.CRACKED_MUD_BRICKS.getDefaultState()))));
                addRuleToProcessorList(processorLists.get(StructureProcessorLists.TRIAL_CHAMBERS_COPPER_BULB_DEGRADATION), new RuleStructureProcessor(List.of(new StructureProcessorRule(new RandomBlockMatchRuleTest(Blocks.TUFF_BRICKS, 0.3F), AlwaysTrueRuleTest.INSTANCE, KaleidoscopeBlocks.CRACKED_TUFF_BRICKS.getDefaultState()))));
            }
        });
    }
}
