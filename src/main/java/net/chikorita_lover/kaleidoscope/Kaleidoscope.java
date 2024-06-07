package net.chikorita_lover.kaleidoscope;

import com.chocohead.mm.api.ClassTinkerers;
import com.google.common.collect.ImmutableSet;
import com.mojang.datafixers.util.Pair;
import it.unimi.dsi.fastutil.ints.IntList;
import net.chikorita_lover.kaleidoscope.mixin.structure.StructurePoolAccessor;
import net.chikorita_lover.kaleidoscope.network.OpenStriderScreenS2CPacket;
import net.chikorita_lover.kaleidoscope.recipe.KilningRecipe;
import net.chikorita_lover.kaleidoscope.registry.*;
import net.chikorita_lover.kaleidoscope.screen.FireworksTableScreenHandler;
import net.chikorita_lover.kaleidoscope.screen.KilnScreenHandler;
import net.chikorita_lover.kaleidoscope.structure.EndCityStructureProcessor;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents;
import net.fabricmc.fabric.api.loot.v2.LootTableEvents;
import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry;
import net.minecraft.block.Blocks;
import net.minecraft.block.DispenserBlock;
import net.minecraft.block.dispenser.ShearsDispenserBehavior;
import net.minecraft.component.type.FireworkExplosionComponent;
import net.minecraft.loot.LootTables;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.function.SetCountLootFunction;
import net.minecraft.loot.provider.number.UniformLootNumberProvider;
import net.minecraft.recipe.CookingRecipeSerializer;
import net.minecraft.recipe.RecipeType;
import net.minecraft.recipe.book.RecipeBookCategory;
import net.minecraft.registry.*;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.resource.featuretoggle.FeatureFlags;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.screen.ScreenTexts;
import net.minecraft.server.MinecraftServer;
import net.minecraft.structure.pool.FeaturePoolElement;
import net.minecraft.structure.pool.SinglePoolElement;
import net.minecraft.structure.pool.StructurePool;
import net.minecraft.structure.pool.StructurePoolElement;
import net.minecraft.structure.processor.*;
import net.minecraft.structure.rule.AlwaysTrueRuleTest;
import net.minecraft.structure.rule.RandomBlockMatchRuleTest;
import net.minecraft.text.MutableText;
import net.minecraft.text.Text;
import net.minecraft.util.DyeColor;
import net.minecraft.util.Identifier;
import net.minecraft.village.VillagerProfession;
import net.minecraft.world.gen.feature.PlacedFeature;
import net.minecraft.world.poi.PointOfInterestType;
import net.minecraft.world.poi.PointOfInterestTypes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Kaleidoscope implements ModInitializer {
    public static final VillagerProfession FIREWORKER;
    public static final VillagerProfession GLASSBLOWER;
    public static final RegistryKey<PointOfInterestType> FIREWORKER_POINT_OF_INTEREST;
    public static final RegistryKey<PointOfInterestType> GLASSBLOWER_POINT_OF_INTEREST;
    public static final Logger LOGGER = LoggerFactory.getLogger("Kaleidoscope");
    public static final String MODID = "kaleidoscope";
    public static final CookingRecipeSerializer<KilningRecipe> KILN_COOKING_RECIPE_SERIALIZER;
    public static final ScreenHandlerType<FireworksTableScreenHandler> FIREWORKS_TABLE;
    public static final ScreenHandlerType<KilnScreenHandler> KILN_SCREEN_HANDLER;
    public static final RecipeType<KilningRecipe> KILNING;
    public static final RecipeBookCategory KILNING_CATEGORY;
    public static final StructureProcessorType<EndCityStructureProcessor> END_CITY_STRUCTURE_PROCESSOR = Registry.register(Registries.STRUCTURE_PROCESSOR, new Identifier(Kaleidoscope.MODID, "end_city"), () -> EndCityStructureProcessor.CODEC);

    static {
        FIREWORKER_POINT_OF_INTEREST = RegistryKey.of(RegistryKeys.POINT_OF_INTEREST_TYPE, new Identifier(MODID, "fireworker"));
        GLASSBLOWER_POINT_OF_INTEREST = RegistryKey.of(RegistryKeys.POINT_OF_INTEREST_TYPE, new Identifier(MODID, "glassblower"));
        GLASSBLOWER = new VillagerProfession("glassblower", entry -> entry.matchesKey(GLASSBLOWER_POINT_OF_INTEREST), entry -> entry.matchesKey(GLASSBLOWER_POINT_OF_INTEREST), ImmutableSet.of(), ImmutableSet.of(), KaleidoscopeSoundEvents.ENTITY_VILLAGER_WORK_GLASSBLOWER);
        FIREWORKER = new VillagerProfession("fireworker", entry -> entry.matchesKey(FIREWORKER_POINT_OF_INTEREST), entry -> entry.matchesKey(FIREWORKER_POINT_OF_INTEREST), ImmutableSet.of(), ImmutableSet.of(), KaleidoscopeSoundEvents.ENTITY_VILLAGER_WORK_FIREWORKER);
        KILN_COOKING_RECIPE_SERIALIZER = Registry.register(Registries.RECIPE_SERIALIZER, new Identifier(MODID, "kilning"), new CookingRecipeSerializer<>(KilningRecipe::new, 100));
        FIREWORKS_TABLE = Registry.register(Registries.SCREEN_HANDLER, new Identifier(MODID, "fireworks_table"), new ScreenHandlerType<>(FireworksTableScreenHandler::new, FeatureFlags.VANILLA_FEATURES));
        KILN_SCREEN_HANDLER = Registry.register(Registries.SCREEN_HANDLER, new Identifier(MODID, "kiln"), new ScreenHandlerType<>(KilnScreenHandler::new, FeatureFlags.VANILLA_FEATURES));
        KILNING = Registry.register(Registries.RECIPE_TYPE, new Identifier(MODID, "kilning"), new RecipeType<KilningRecipe>() {
            @Override
            public String toString() {
                return "kilning";
            }
        });
        KILNING_CATEGORY = ClassTinkerers.getEnum(RecipeBookCategory.class, "KALEIDOSCOPE_KILN");
    }

    public static void addToStructurePool(MinecraftServer server, Identifier village, Identifier structure, int weight) {
        RegistryEntry<StructureProcessorList> emptyProcessorList = server.getRegistryManager().get(RegistryKeys.PROCESSOR_LIST).entryOf(RegistryKey.of(RegistryKeys.PROCESSOR_LIST, new Identifier("empty")));
        addToStructurePool(server, village, structure, emptyProcessorList, weight);
    }

    public static void addToStructurePool(MinecraftServer server, Identifier village, Identifier structure, RegistryEntry<StructureProcessorList> processorList, int weight) {
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

    public static void addToStructurePool(MinecraftServer server, Identifier village, RegistryEntry<PlacedFeature> placedFeature, int weight) {
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

    public static MutableText getFireworkStarText(FireworkExplosionComponent component) {
        MutableText text = Text.empty();
        FireworkExplosionComponent.Type type = component.shape();
        IntList colors = component.colors();
        if (!colors.isEmpty()) {
            appendFireworkColorText(text, colors);
        }
        if (!text.equals(Text.empty())) {
            text.append(ScreenTexts.space());
        }
        text.append(type.getName());
        return text;
    }

    private static void appendFireworkColorText(MutableText text, IntList integers) {
        if (integers.size() == 1) {
            DyeColor dyeColor = DyeColor.byFireworkColor(integers.getInt(0));
            if (dyeColor == null) {
                text.append(Text.translatable("item.minecraft.firework_star.custom_color"));
            } else {
                text.append(Text.translatable("item.minecraft.firework_star." + dyeColor.getName()));
            }
        } else if (integers.size() == 2) {
            DyeColor dyeColor = DyeColor.byFireworkColor(integers.getInt(0));
            DyeColor dyeColor2 = DyeColor.byFireworkColor(integers.getInt(1));
            if (dyeColor == null || dyeColor2 == null) {
                text.append(Text.translatable("item.minecraft.firework_star.custom_color"));
            } else {
                text.append(Text.translatable("item.minecraft.firework_star." + dyeColor.getName()).append("-").append(Text.translatable("item.minecraft.firework_star." + dyeColor2.getName())));
            }
        } else {
            text.append(Text.translatable("item.minecraft.firework_star.multicolor"));
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

    @Override
    public void onInitialize() {
        CrackedBlockRegistry.register();
        KaleidoscopeBlocks.registerFlammableBlocks();
        KaleidoscopeBlocks.registerMossyPairs();
        KaleidoscopeBlocks.registerOxidizablePairs();
        KaleidoscopeBlockEntities.register();
        KaleidoscopeCauldronBehavior.register();
        KaleidoscopeItemGroups.register();
        KaleidoscopeItems.registerCompostingChances();
        KaleidoscopeItems.registerFuels();
        KaleidoscopeItems.registerMaxItemCounts();
        KaleidoscopeLootTables.register();
        KaleidoscopeSoundEvents.register();
        KaleidoscopeStats.register();
        KaleidoscopeTradeOffers.register();

        registerLootTableEvents();

        DispenserBlock.registerBehavior(KaleidoscopeItems.NETHERITE_SHEARS, new ShearsDispenserBehavior());

        PayloadTypeRegistry.playS2C().register(OpenStriderScreenS2CPacket.PACKET_ID, OpenStriderScreenS2CPacket.PACKET_CODEC);

        PointOfInterestTypes.register(Registries.POINT_OF_INTEREST_TYPE, FIREWORKER_POINT_OF_INTEREST, PointOfInterestTypes.getStatesOfBlock(KaleidoscopeBlocks.FIREWORKS_TABLE), 1, 1);
        PointOfInterestTypes.register(Registries.POINT_OF_INTEREST_TYPE, GLASSBLOWER_POINT_OF_INTEREST, PointOfInterestTypes.getStatesOfBlock(KaleidoscopeBlocks.KILN), 1, 1);
        Registry.register(Registries.VILLAGER_PROFESSION, new Identifier(MODID, "fireworker"), FIREWORKER);
        Registry.register(Registries.VILLAGER_PROFESSION, new Identifier(MODID, "glassblower"), GLASSBLOWER);

        ServerLifecycleEvents.SERVER_STARTING.register(server -> {
            RegistryEntryLookup<PlacedFeature> featureRegistry = server.getRegistryManager().createRegistryLookup().getOrThrow(RegistryKeys.PLACED_FEATURE);
            RegistryEntryLookup<StructureProcessorList> processorRegistry = server.getRegistryManager().createRegistryLookup().getOrThrow(RegistryKeys.PROCESSOR_LIST);
            addToStructurePool(server, new Identifier("village/desert/houses"), new Identifier(MODID, "village/desert/houses/desert_glassblower_1"), 4);
            addToStructurePool(server, new Identifier("village/plains/houses"), new Identifier(MODID, "village/plains/houses/plains_glassblower_1"), processorRegistry.getOrThrow(StructureProcessorLists.MOSSIFY_10_PERCENT), 4);
            addToStructurePool(server, new Identifier("village/savanna/houses"), new Identifier(MODID, "village/savanna/houses/savanna_glassblower_1"), 4);
            addToStructurePool(server, new Identifier("village/snowy/decor"), featureRegistry.getOrThrow(KaleidoscopePlacedFeatures.PILE_STICK_BUNDLE), 1);
            addToStructurePool(server, new Identifier("village/snowy/houses"), new Identifier(MODID, "village/snowy/houses/snowy_glassblower_1"), 4);
            addToStructurePool(server, new Identifier("village/taiga/decor"), featureRegistry.getOrThrow(KaleidoscopePlacedFeatures.PILE_STICK_BUNDLE), 1);
            addToStructurePool(server, new Identifier("village/taiga/houses"), new Identifier(MODID, "village/taiga/houses/taiga_glassblower_1"), 4);

            Registry<StructureProcessorList> processorLists = server.getRegistryManager().get(RegistryKeys.PROCESSOR_LIST);
            if (processorLists != null) {
                addRuleToProcessorList(processorLists.get(StructureProcessorLists.TRAIL_RUINS_HOUSES_ARCHAEOLOGY), new RuleStructureProcessor(List.of(new StructureProcessorRule(new RandomBlockMatchRuleTest(Blocks.MUD_BRICKS, 0.2F), AlwaysTrueRuleTest.INSTANCE, KaleidoscopeBlocks.CRACKED_MUD_BRICKS.getDefaultState()))));
                addRuleToProcessorList(processorLists.get(StructureProcessorLists.TRAIL_RUINS_ROADS_ARCHAEOLOGY), new RuleStructureProcessor(List.of(new StructureProcessorRule(new RandomBlockMatchRuleTest(Blocks.MUD_BRICKS, 0.2F), AlwaysTrueRuleTest.INSTANCE, KaleidoscopeBlocks.CRACKED_MUD_BRICKS.getDefaultState()))));
                addRuleToProcessorList(processorLists.get(StructureProcessorLists.TRAIL_RUINS_TOWER_TOP_ARCHAEOLOGY), new RuleStructureProcessor(List.of(new StructureProcessorRule(new RandomBlockMatchRuleTest(Blocks.MUD_BRICKS, 0.2F), AlwaysTrueRuleTest.INSTANCE, KaleidoscopeBlocks.CRACKED_MUD_BRICKS.getDefaultState()))));
                addRuleToProcessorList(processorLists.get(OneTwentyOneStructureProcessorLists.TRIAL_CHAMBERS_COPPER_BULB_DEGRADATION), new RuleStructureProcessor(List.of(new StructureProcessorRule(new RandomBlockMatchRuleTest(Blocks.TUFF_BRICKS, 0.3F), AlwaysTrueRuleTest.INSTANCE, KaleidoscopeBlocks.CRACKED_TUFF_BRICKS.getDefaultState()))));
            }
        });
    }

    public void registerLootTableEvents() {
        LootTableEvents.MODIFY.register((key, tableBuilder, source) -> {
            if (key == LootTables.PIGLIN_BARTERING_GAMEPLAY) {
                tableBuilder.modifyPools(builder -> builder.with((ItemEntry.builder(KaleidoscopeItems.DISC_FRAGMENT_PIGSTEP).weight(10)).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0F, 3.0F)))).build());
            }
        });
    }
}
