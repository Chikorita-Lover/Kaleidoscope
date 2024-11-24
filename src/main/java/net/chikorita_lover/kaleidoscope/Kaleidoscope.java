package net.chikorita_lover.kaleidoscope;

import net.chikorita_lover.kaleidoscope.block.KaleidoscopeBlocks;
import net.chikorita_lover.kaleidoscope.block.entity.KaleidoscopeBlockEntityTypes;
import net.chikorita_lover.kaleidoscope.entity.KaleidoscopeEntityTypes;
import net.chikorita_lover.kaleidoscope.item.KaleidoscopeItemGroups;
import net.chikorita_lover.kaleidoscope.item.KaleidoscopeItems;
import net.chikorita_lover.kaleidoscope.recipe.KaleidoscopeRecipeSerializers;
import net.chikorita_lover.kaleidoscope.recipe.KaleidoscopeRecipeTypes;
import net.chikorita_lover.kaleidoscope.registry.*;
import net.chikorita_lover.kaleidoscope.registry.tag.KaleidoscopeBlockTags;
import net.chikorita_lover.kaleidoscope.screen.KaleidoscopeScreenHandlerTypes;
import net.chikorita_lover.kaleidoscope.structure.EndCityStructureProcessor;
import net.chikorita_lover.kaleidoscope.structure.StructurePoolModifiers;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents;
import net.fabricmc.fabric.api.loot.v2.LootTableEvents;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.DispenserBlock;
import net.minecraft.block.SideShapeType;
import net.minecraft.block.dispenser.BoatDispenserBehavior;
import net.minecraft.block.dispenser.ShearsDispenserBehavior;
import net.minecraft.entity.EntityType;
import net.minecraft.item.Items;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.LootTables;
import net.minecraft.loot.condition.EntityPropertiesLootCondition;
import net.minecraft.loot.context.LootContext;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.function.FurnaceSmeltLootFunction;
import net.minecraft.loot.function.LootingEnchantLootFunction;
import net.minecraft.loot.function.SetCountLootFunction;
import net.minecraft.loot.provider.number.UniformLootNumberProvider;
import net.minecraft.predicate.entity.EntityFlagsPredicate;
import net.minecraft.predicate.entity.EntityPredicate;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.structure.processor.*;
import net.minecraft.structure.rule.AlwaysTrueRuleTest;
import net.minecraft.structure.rule.RandomBlockMatchRuleTest;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.BlockView;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class Kaleidoscope implements ModInitializer {
    public static final Logger LOGGER = LoggerFactory.getLogger("Kaleidoscope");
    public static final String MODID = "kaleidoscope";
    public static final StructureProcessorType<EndCityStructureProcessor> END_CITY_STRUCTURE_PROCESSOR = Registry.register(Registries.STRUCTURE_PROCESSOR, of("end_city"), () -> EndCityStructureProcessor.CODEC);

    public static Identifier of(String path) {
        return Identifier.of(MODID, path);
    }

    public static boolean isHoisted(BlockView world, BlockPos pos, BlockState state) {
        BlockState aboveState = world.getBlockState(pos.up());
        return aboveState.isIn(KaleidoscopeBlockTags.HOISTS_FALLING_BLOCKS) && aboveState.isSideSolid(world, pos.up(), Direction.DOWN, SideShapeType.CENTER) && state.isSideSolid(world, pos, Direction.UP, SideShapeType.CENTER);
    }

    private static void addStructureProcessor(StructureProcessorList processorList, RuleStructureProcessor processor) {
        ArrayList<StructureProcessor> list = new ArrayList<>(processorList.getList());
        list.add(processor);
        processorList.list = list;
    }

    private static void registerLootTableEvents() {
        LootTableEvents.MODIFY.register((resourceManager, lootManager, id, tableBuilder, source) -> {
            if (id.equals(LootTables.PIGLIN_BARTERING_GAMEPLAY)) {
                tableBuilder.modifyPools(builder -> builder.with((ItemEntry.builder(KaleidoscopeItems.DISC_FRAGMENT_PIGSTEP).weight(10)).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0F, 3.0F)))).build());
            }
            if (id.equals(EntityType.CAMEL.getLootTableId())) {
                tableBuilder.pool(LootPool.builder().with(ItemEntry.builder(Items.LEATHER).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(0.0F, 2.0F))).apply(LootingEnchantLootFunction.builder(UniformLootNumberProvider.create(0.0F, 1.0F)))).build());
            }
            if (id.equals(EntityType.GOAT.getLootTableId())) {
                tableBuilder.pool(LootPool.builder().with(ItemEntry.builder(Items.MUTTON).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0F, 2.0F))).apply(FurnaceSmeltLootFunction.builder().conditionally(EntityPropertiesLootCondition.builder(LootContext.EntityTarget.THIS, EntityPredicate.Builder.create().flags(EntityFlagsPredicate.Builder.create().onFire(true).build())))).apply(LootingEnchantLootFunction.builder(UniformLootNumberProvider.create(0.0F, 1.0F)))).build());
            }
        });
    }

    @Override
    public void onInitialize() {
        KaleidoscopeBlocks.registerFlammableBlocks();
        KaleidoscopeBlocks.registerOxidizablePairs();
        KaleidoscopeBlockEntityTypes.register();
        KaleidoscopeEntityTypes.register();
        KaleidoscopeItemGroups.register();
        KaleidoscopeItems.registerFuels();
        KaleidoscopePointOfInterestTypes.register();
        KaleidoscopeRecipeSerializers.register();
        KaleidoscopeRecipeTypes.register();
        KaleidoscopeScreenHandlerTypes.register();
        KaleidoscopeSoundEvents.register();
        KaleidoscopeStats.register();
        KaleidoscopeTradeOffers.register();
        KaleidoscopeVillagerProfessions.register();
        StructurePoolModifiers.register();

        registerLootTableEvents();

        DispenserBlock.registerBehavior(KaleidoscopeItems.NETHERITE_SHEARS, new ShearsDispenserBehavior());
        DispenserBlock.registerBehavior(KaleidoscopeItems.CRIMSON_BOAT, new BoatDispenserBehavior(KaleidoscopeBoatTypes.CRIMSON));
        DispenserBlock.registerBehavior(KaleidoscopeItems.WARPED_BOAT, new BoatDispenserBehavior(KaleidoscopeBoatTypes.WARPED));
        DispenserBlock.registerBehavior(KaleidoscopeItems.CRIMSON_CHEST_BOAT, new BoatDispenserBehavior(KaleidoscopeBoatTypes.CRIMSON));
        DispenserBlock.registerBehavior(KaleidoscopeItems.WARPED_CHEST_BOAT, new BoatDispenserBehavior(KaleidoscopeBoatTypes.WARPED));

        ServerLifecycleEvents.SERVER_STARTED.register(server -> {
            Registry<StructureProcessorList> processorLists = server.getRegistryManager().get(RegistryKeys.PROCESSOR_LIST);
            if (processorLists != null) {
                addStructureProcessor(processorLists.getOrThrow(StructureProcessorLists.TRAIL_RUINS_HOUSES_ARCHAEOLOGY), new RuleStructureProcessor(List.of(new StructureProcessorRule(new RandomBlockMatchRuleTest(Blocks.MUD_BRICKS, 0.2F), AlwaysTrueRuleTest.INSTANCE, KaleidoscopeBlocks.CRACKED_MUD_BRICKS.getDefaultState()))));
                addStructureProcessor(processorLists.getOrThrow(StructureProcessorLists.TRAIL_RUINS_ROADS_ARCHAEOLOGY), new RuleStructureProcessor(List.of(new StructureProcessorRule(new RandomBlockMatchRuleTest(Blocks.MUD_BRICKS, 0.2F), AlwaysTrueRuleTest.INSTANCE, KaleidoscopeBlocks.CRACKED_MUD_BRICKS.getDefaultState()))));
                addStructureProcessor(processorLists.getOrThrow(StructureProcessorLists.TRAIL_RUINS_TOWER_TOP_ARCHAEOLOGY), new RuleStructureProcessor(List.of(new StructureProcessorRule(new RandomBlockMatchRuleTest(Blocks.MUD_BRICKS, 0.2F), AlwaysTrueRuleTest.INSTANCE, KaleidoscopeBlocks.CRACKED_MUD_BRICKS.getDefaultState()))));
            }
        });
    }
}
