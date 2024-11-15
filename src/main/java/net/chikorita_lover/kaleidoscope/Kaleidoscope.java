package net.chikorita_lover.kaleidoscope;

import net.chikorita_lover.kaleidoscope.block.KaleidoscopeBlocks;
import net.chikorita_lover.kaleidoscope.block.entity.KaleidoscopeBlockEntityTypes;
import net.chikorita_lover.kaleidoscope.entity.KaleidoscopeEntityTypes;
import net.chikorita_lover.kaleidoscope.item.KaleidoscopeItemGroups;
import net.chikorita_lover.kaleidoscope.item.KaleidoscopeItems;
import net.chikorita_lover.kaleidoscope.network.OpenStriderScreenS2CPacket;
import net.chikorita_lover.kaleidoscope.network.StopJukeboxMinecartPlayingS2CPacket;
import net.chikorita_lover.kaleidoscope.network.UpdateJukeboxMinecartS2CPacket;
import net.chikorita_lover.kaleidoscope.recipe.KaleidoscopeRecipeSerializers;
import net.chikorita_lover.kaleidoscope.recipe.KaleidoscopeRecipeTypes;
import net.chikorita_lover.kaleidoscope.registry.*;
import net.chikorita_lover.kaleidoscope.screen.KaleidoscopeScreenHandlerTypes;
import net.chikorita_lover.kaleidoscope.structure.EndCityStructureProcessor;
import net.chikorita_lover.kaleidoscope.structure.StructurePoolModifiers;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents;
import net.fabricmc.fabric.api.item.v1.DefaultItemComponentEvents;
import net.fabricmc.fabric.api.loot.v3.LootTableEvents;
import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry;
import net.minecraft.block.Blocks;
import net.minecraft.block.DispenserBlock;
import net.minecraft.block.dispenser.BoatDispenserBehavior;
import net.minecraft.block.dispenser.ShearsDispenserBehavior;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.EntityType;
import net.minecraft.item.BannerItem;
import net.minecraft.item.BannerPatternItem;
import net.minecraft.item.Items;
import net.minecraft.item.SignItem;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.LootTables;
import net.minecraft.loot.condition.AnyOfLootCondition;
import net.minecraft.loot.condition.EntityPropertiesLootCondition;
import net.minecraft.loot.context.LootContext;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.function.EnchantedCountIncreaseLootFunction;
import net.minecraft.loot.function.FurnaceSmeltLootFunction;
import net.minecraft.loot.function.SetCountLootFunction;
import net.minecraft.loot.provider.number.UniformLootNumberProvider;
import net.minecraft.predicate.NumberRange;
import net.minecraft.predicate.entity.EntityPredicate;
import net.minecraft.predicate.item.EnchantmentPredicate;
import net.minecraft.predicate.item.EnchantmentsPredicate;
import net.minecraft.predicate.item.ItemSubPredicateTypes;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.EnchantmentTags;
import net.minecraft.structure.processor.*;
import net.minecraft.structure.rule.AlwaysTrueRuleTest;
import net.minecraft.structure.rule.RandomBlockMatchRuleTest;
import net.minecraft.util.Identifier;
import net.minecraft.util.Unit;
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

    private static void addStructureProcessor(StructureProcessorList processorList, RuleStructureProcessor processor) {
        ArrayList<StructureProcessor> list = new ArrayList<>(processorList.getList());
        list.add(processor);
        processorList.list = list;
    }

    private static void registerLootTableEvents() {
        LootTableEvents.MODIFY.register((key, tableBuilder, source, registries) -> {
            if (key.equals(LootTables.PIGLIN_BARTERING_GAMEPLAY)) {
                tableBuilder.modifyPools(builder -> builder.with((ItemEntry.builder(KaleidoscopeItems.DISC_FRAGMENT_PIGSTEP).weight(10)).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0F, 3.0F)))).build());
            }
            if (key.equals(EntityType.CAMEL.getLootTableId())) {
                tableBuilder.pool(LootPool.builder().with(ItemEntry.builder(Items.LEATHER).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(0.0F, 2.0F))).apply(EnchantedCountIncreaseLootFunction.builder(registries, UniformLootNumberProvider.create(0.0F, 1.0F)))).build());
            }
            if (key.equals(EntityType.GOAT.getLootTableId())) {
                tableBuilder.pool(LootPool.builder().with(ItemEntry.builder(Items.MUTTON).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0F, 2.0F))).apply(FurnaceSmeltLootFunction.builder().conditionally(createSmeltLootCondition(registries))).apply(EnchantedCountIncreaseLootFunction.builder(registries, UniformLootNumberProvider.create(0.0F, 1.0F)))).build());
            }
        });
    }

    private static AnyOfLootCondition.Builder createSmeltLootCondition(RegistryWrapper.WrapperLookup registries) {
        RegistryWrapper.Impl<Enchantment> impl = registries.getWrapperOrThrow(RegistryKeys.ENCHANTMENT);
        return AnyOfLootCondition.builder(EntityPropertiesLootCondition.builder(LootContext.EntityTarget.THIS, EntityPredicate.Builder.create().flags(net.minecraft.predicate.entity.EntityFlagsPredicate.Builder.create().onFire(true))), EntityPropertiesLootCondition.builder(LootContext.EntityTarget.DIRECT_ATTACKER, EntityPredicate.Builder.create().equipment(net.minecraft.predicate.entity.EntityEquipmentPredicate.Builder.create().mainhand(net.minecraft.predicate.item.ItemPredicate.Builder.create().subPredicate(ItemSubPredicateTypes.ENCHANTMENTS, EnchantmentsPredicate.enchantments(List.of(new EnchantmentPredicate(impl.getOrThrow(EnchantmentTags.SMELTS_LOOT), NumberRange.IntRange.ANY))))))));
    }

    @Override
    public void onInitialize() {
        KaleidoscopeBlocks.registerFlammableBlocks();
        KaleidoscopeBlocks.registerOxidizablePairs();
        KaleidoscopeBlockEntityTypes.register();
        KaleidoscopeEntityTypes.register();
        KaleidoscopeItemGroups.register();
        KaleidoscopeItems.registerFuels();
        KaleidoscopeLootTables.register();
        KaleidoscopePointOfInterestTypes.register();
        KaleidoscopeRecipeSerializers.register();
        KaleidoscopeRecipeTypes.register();
        KaleidoscopeScreenHandlerTypes.register();
        KaleidoscopeSoundEvents.register();
        KaleidoscopeStats.register();
        KaleidoscopeTradeOffers.register();
        KaleidoscopeVillagerProfessions.register();
        StructurePoolModifiers.register();

        DefaultItemComponentEvents.MODIFY.register(context -> {
            context.modify(item -> item instanceof BannerItem, (builder, item) -> builder.add(DataComponentTypes.MAX_STACK_SIZE, 64));
            context.modify(item -> item instanceof BannerPatternItem, (builder, item) -> builder.add(DataComponentTypes.MAX_STACK_SIZE, 64));
            context.modify(item -> item instanceof SignItem, (builder, item) -> builder.add(DataComponentTypes.MAX_STACK_SIZE, 64));
            context.modify(item -> item.getComponents().contains(DataComponentTypes.JUKEBOX_PLAYABLE) && Registries.ITEM.getId(item).getPath().matches("music_disc_\\w+"), (builder, item) -> builder.add(DataComponentTypes.MAX_STACK_SIZE, 64));
            context.modify(List.of(Items.EGG, Items.SNOWBALL), (builder, item) -> builder.add(DataComponentTypes.MAX_STACK_SIZE, 64));
            context.modify(List.of(Items.BLAZE_POWDER, Items.BLAZE_ROD, Items.MAGMA_CREAM), (builder, item) -> builder.add(DataComponentTypes.FIRE_RESISTANT, Unit.INSTANCE));
        });

        registerLootTableEvents();

        DispenserBlock.registerBehavior(KaleidoscopeItems.NETHERITE_SHEARS, new ShearsDispenserBehavior());
        DispenserBlock.registerBehavior(KaleidoscopeItems.CRIMSON_BOAT, new BoatDispenserBehavior(KaleidoscopeBoatTypes.CRIMSON));
        DispenserBlock.registerBehavior(KaleidoscopeItems.WARPED_BOAT, new BoatDispenserBehavior(KaleidoscopeBoatTypes.WARPED));
        DispenserBlock.registerBehavior(KaleidoscopeItems.CRIMSON_CHEST_BOAT, new BoatDispenserBehavior(KaleidoscopeBoatTypes.CRIMSON));
        DispenserBlock.registerBehavior(KaleidoscopeItems.WARPED_CHEST_BOAT, new BoatDispenserBehavior(KaleidoscopeBoatTypes.WARPED));

        PayloadTypeRegistry.playS2C().register(OpenStriderScreenS2CPacket.PACKET_ID, OpenStriderScreenS2CPacket.PACKET_CODEC);
        PayloadTypeRegistry.playS2C().register(StopJukeboxMinecartPlayingS2CPacket.PACKET_ID, StopJukeboxMinecartPlayingS2CPacket.PACKET_CODEC);
        PayloadTypeRegistry.playS2C().register(UpdateJukeboxMinecartS2CPacket.PACKET_ID, UpdateJukeboxMinecartS2CPacket.PACKET_CODEC);

        ServerLifecycleEvents.SERVER_STARTED.register(server -> {
            Registry<StructureProcessorList> processorLists = server.getRegistryManager().get(RegistryKeys.PROCESSOR_LIST);
            if (processorLists != null) {
                addStructureProcessor(processorLists.getOrThrow(StructureProcessorLists.TRAIL_RUINS_HOUSES_ARCHAEOLOGY), new RuleStructureProcessor(List.of(new StructureProcessorRule(new RandomBlockMatchRuleTest(Blocks.MUD_BRICKS, 0.2F), AlwaysTrueRuleTest.INSTANCE, KaleidoscopeBlocks.CRACKED_MUD_BRICKS.getDefaultState()))));
                addStructureProcessor(processorLists.getOrThrow(StructureProcessorLists.TRAIL_RUINS_ROADS_ARCHAEOLOGY), new RuleStructureProcessor(List.of(new StructureProcessorRule(new RandomBlockMatchRuleTest(Blocks.MUD_BRICKS, 0.2F), AlwaysTrueRuleTest.INSTANCE, KaleidoscopeBlocks.CRACKED_MUD_BRICKS.getDefaultState()))));
                addStructureProcessor(processorLists.getOrThrow(StructureProcessorLists.TRAIL_RUINS_TOWER_TOP_ARCHAEOLOGY), new RuleStructureProcessor(List.of(new StructureProcessorRule(new RandomBlockMatchRuleTest(Blocks.MUD_BRICKS, 0.2F), AlwaysTrueRuleTest.INSTANCE, KaleidoscopeBlocks.CRACKED_MUD_BRICKS.getDefaultState()))));
                addStructureProcessor(processorLists.getOrThrow(StructureProcessorLists.TRIAL_CHAMBERS_COPPER_BULB_DEGRADATION), new RuleStructureProcessor(List.of(new StructureProcessorRule(new RandomBlockMatchRuleTest(Blocks.TUFF_BRICKS, 0.3F), AlwaysTrueRuleTest.INSTANCE, KaleidoscopeBlocks.CRACKED_TUFF_BRICKS.getDefaultState()))));
            }
        });
    }
}
