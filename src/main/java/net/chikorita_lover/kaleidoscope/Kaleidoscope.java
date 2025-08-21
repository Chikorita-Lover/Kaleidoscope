package net.chikorita_lover.kaleidoscope;

import net.chikorita_lover.chicory.api.loot.LootModificationUtils;
import net.chikorita_lover.chicory.api.recipe.RecipeBookTypeRegistry;
import net.chikorita_lover.chicory.api.registry.TagKeyEvents;
import net.chikorita_lover.kaleidoscope.block.KaleidoscopeBlocks;
import net.chikorita_lover.kaleidoscope.block.entity.KaleidoscopeBlockEntityTypes;
import net.chikorita_lover.kaleidoscope.registry.KaleidoscopeDataComponentTypes;
import net.chikorita_lover.kaleidoscope.entity.KaleidoscopeEntityTypes;
import net.chikorita_lover.kaleidoscope.item.KaleidoscopeItemGroups;
import net.chikorita_lover.kaleidoscope.item.KaleidoscopeItems;
import net.chikorita_lover.kaleidoscope.network.OpenStriderScreenS2CPacket;
import net.chikorita_lover.kaleidoscope.network.StopJukeboxMinecartPlayingS2CPacket;
import net.chikorita_lover.kaleidoscope.network.UpdateJukeboxMinecartS2CPacket;
import net.chikorita_lover.kaleidoscope.recipe.KaleidoscopeRecipeBookCategories;
import net.chikorita_lover.kaleidoscope.recipe.KaleidoscopeRecipeSerializers;
import net.chikorita_lover.kaleidoscope.recipe.KaleidoscopeRecipeTypes;
import net.chikorita_lover.kaleidoscope.registry.*;
import net.chikorita_lover.kaleidoscope.registry.tag.KaleidoscopeBlockTags;
import net.chikorita_lover.kaleidoscope.registry.tag.KaleidoscopeItemTags;
import net.chikorita_lover.kaleidoscope.screen.KaleidoscopeScreenHandlerTypes;
import net.chikorita_lover.kaleidoscope.structure.StructurePoolModifiers;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents;
import net.fabricmc.fabric.api.loot.v3.LootTableEvents;
import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.DispenserBlock;
import net.minecraft.block.SideShapeType;
import net.minecraft.block.dispenser.BoatDispenserBehavior;
import net.minecraft.block.dispenser.ShearsDispenserBehavior;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.EquippableComponent;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.*;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.LootTables;
import net.minecraft.loot.condition.AnyOfLootCondition;
import net.minecraft.loot.condition.DamageSourcePropertiesLootCondition;
import net.minecraft.loot.condition.EntityPropertiesLootCondition;
import net.minecraft.loot.condition.KilledByPlayerLootCondition;
import net.minecraft.loot.context.LootContext;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.function.EnchantedCountIncreaseLootFunction;
import net.minecraft.loot.function.FurnaceSmeltLootFunction;
import net.minecraft.loot.function.SetCountLootFunction;
import net.minecraft.loot.provider.number.UniformLootNumberProvider;
import net.minecraft.predicate.NumberRange;
import net.minecraft.predicate.TagPredicate;
import net.minecraft.predicate.component.ComponentPredicateTypes;
import net.minecraft.predicate.component.ComponentsPredicate;
import net.minecraft.predicate.entity.DamageSourcePredicate;
import net.minecraft.predicate.entity.EntityEquipmentPredicate;
import net.minecraft.predicate.entity.EntityFlagsPredicate;
import net.minecraft.predicate.entity.EntityPredicate;
import net.minecraft.predicate.item.EnchantmentPredicate;
import net.minecraft.predicate.item.EnchantmentsPredicate;
import net.minecraft.predicate.item.ItemPredicate;
import net.minecraft.recipe.book.RecipeBookType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.DamageTypeTags;
import net.minecraft.registry.tag.EnchantmentTags;
import net.minecraft.registry.tag.ItemTags;
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
    public static final RecipeBookType KILNING_CATEGORY = RecipeBookTypeRegistry.register("kaleidoscope_kiln", KaleidoscopeRecipeBookCategories.KILN_BLOCKS, KaleidoscopeRecipeBookCategories.KILN_MISC);

    public static Identifier of(String path) {
        return Identifier.of(MODID, path);
    }

    public static boolean isHoisted(BlockView world, BlockPos pos, BlockState state) {
        if (!KaleidoscopeConfig.CHAINS_HOIST_BLOCKS.get()) {
            return false;
        }
        BlockState aboveState = world.getBlockState(pos.up());
        return aboveState.isIn(KaleidoscopeBlockTags.HOISTS_FALLING_BLOCKS) && aboveState.isSideSolid(world, pos.up(), Direction.DOWN, SideShapeType.CENTER) && state.isSideSolid(world, pos, Direction.UP, SideShapeType.CENTER);
    }

    private static void addStructureProcessor(StructureProcessorList processorList, RuleStructureProcessor processor) {
        ArrayList<StructureProcessor> list = new ArrayList<>(processorList.getList());
        list.add(processor);
        processorList.list = list;
    }

    private static boolean isHorseArmor(Item item) {
        EquippableComponent component = item.getComponents().get(DataComponentTypes.EQUIPPABLE);
        return component != null && component.allows(EntityType.HORSE) && component.slot() == EquipmentSlot.BODY;
    }

    private static void registerLootTableEvents() {
        LootTableEvents.MODIFY.register((key, lootBuilder, source, registries) -> {
            if (KaleidoscopeConfig.ADDITIONAL_HORSE_ARMORS.get()) {
                if (key.equals(LootTables.SIMPLE_DUNGEON_CHEST)) {
                    LootModificationUtils.modifyPool(lootBuilder, 0, builder -> {
                        LootModificationUtils.removeItemIf(builder, Kaleidoscope::isHorseArmor);
                        builder.with(ItemEntry.builder(Items.IRON_HORSE_ARMOR).weight(20));
                        builder.with(ItemEntry.builder(KaleidoscopeItems.CHAINMAIL_HORSE_ARMOR).weight(10));
                    });
                }
                if (key.equals(LootTables.VILLAGE_WEAPONSMITH_CHEST)) {
                    LootModificationUtils.modifyPool(lootBuilder, 0, builder -> {
                        LootModificationUtils.removeItemIf(builder, Kaleidoscope::isHorseArmor);
                        builder.with(ItemEntry.builder(Items.IRON_HORSE_ARMOR).weight(2));
                        builder.with(ItemEntry.builder(KaleidoscopeItems.CHAINMAIL_HORSE_ARMOR));
                    });
                }
                if (key.equals(LootTables.BASTION_OTHER_CHEST)) {
                    LootModificationUtils.modifyPool(lootBuilder, 1, builder -> builder.with(ItemEntry.builder(KaleidoscopeItems.NETHERITE_HORSE_ARMOR)));
                }
                if (key.equals(LootTables.BASTION_HOGLIN_STABLE_CHEST)) {
                    LootModificationUtils.modifyPool(lootBuilder, 0, builder -> builder.with(ItemEntry.builder(KaleidoscopeItems.NETHERITE_HORSE_ARMOR).weight(12)));
                }
            }
            if (KaleidoscopeConfig.ADDITIONAL_DISC_FRAGMENTS.get() && key.equals(LootTables.PIGLIN_BARTERING_GAMEPLAY)) {
                lootBuilder.modifyPools(builder -> builder.with((ItemEntry.builder(KaleidoscopeItems.DISC_FRAGMENT_PIGSTEP).weight(10)).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0F, 3.0F)))).build());
            }
            if (KaleidoscopeConfig.DO_CAMEL_DROPS.get() && key.equals(EntityType.CAMEL.getLootTableKey().orElse(null))) {
                lootBuilder.pool(LootPool.builder().with(ItemEntry.builder(Items.LEATHER).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(0.0F, 2.0F))).apply(EnchantedCountIncreaseLootFunction.builder(registries, UniformLootNumberProvider.create(0.0F, 1.0F)))).build());
            }
            if (KaleidoscopeConfig.ADDITIONAL_CRACKED_BLOCKS.get() && key.equals(EntityType.GHAST.getLootTableKey().orElse(null))) {
                LootModificationUtils.modifyPool(lootBuilder, 2, builder -> {
                    LootModificationUtils.removeItemIf(builder, item -> item == Items.MUSIC_DISC_TEARS);
                });
                lootBuilder.pool(LootPool.builder().with(ItemEntry.builder(KaleidoscopeItems.DISC_FRAGMENT_TEARS).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0F, 3.0F)))).conditionally(DamageSourcePropertiesLootCondition.builder(DamageSourcePredicate.Builder.create().tag(TagPredicate.expected(DamageTypeTags.IS_PROJECTILE)).directEntity(EntityPredicate.Builder.create().type(registries.getOrThrow(RegistryKeys.ENTITY_TYPE), EntityType.FIREBALL)))).conditionally(KilledByPlayerLootCondition.builder()));
            }
            if (KaleidoscopeConfig.DO_GOAT_DROPS.get() && key.equals(EntityType.GOAT.getLootTableKey().orElse(null))) {
                lootBuilder.pool(LootPool.builder().with(ItemEntry.builder(Items.MUTTON).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0F, 2.0F))).apply(FurnaceSmeltLootFunction.builder().conditionally(createSmeltLootCondition(registries))).apply(EnchantedCountIncreaseLootFunction.builder(registries, UniformLootNumberProvider.create(0.0F, 1.0F)))).build());
            }
        });
    }

    private static AnyOfLootCondition.Builder createSmeltLootCondition(RegistryWrapper.WrapperLookup registries) {
        RegistryWrapper.Impl<Enchantment> impl = registries.getOrThrow(RegistryKeys.ENCHANTMENT);
        return AnyOfLootCondition.builder(EntityPropertiesLootCondition.builder(LootContext.EntityTarget.THIS, EntityPredicate.Builder.create().flags(EntityFlagsPredicate.Builder.create().onFire(true))), EntityPropertiesLootCondition.builder(LootContext.EntityTarget.DIRECT_ATTACKER, EntityPredicate.Builder.create().equipment(EntityEquipmentPredicate.Builder.create().mainhand(ItemPredicate.Builder.create().components(ComponentsPredicate.Builder.create().partial(ComponentPredicateTypes.ENCHANTMENTS, EnchantmentsPredicate.enchantments(List.of(new EnchantmentPredicate(impl.getOrThrow(EnchantmentTags.SMELTS_LOOT), NumberRange.IntRange.ANY)))).build())))));
    }

    @Override
    public void onInitialize() {
        KaleidoscopeBlocks.registerFlammableBlocks();
        KaleidoscopeBlocks.registerOxidizablePairs();
        KaleidoscopeBlockEntityTypes.register();
        KaleidoscopeDataComponentTypes.register();
        KaleidoscopeEntityTypes.register();
        KaleidoscopeItemGroups.register();
        KaleidoscopeItems.register();
        KaleidoscopeLootTables.register();
        KaleidoscopePointOfInterestTypes.register();
        KaleidoscopeRecipeBookCategories.register();
        KaleidoscopeRecipeSerializers.register();
        KaleidoscopeRecipeTypes.register();
        KaleidoscopeScreenHandlerTypes.register();
        KaleidoscopeSoundEvents.register();
        KaleidoscopeStats.register();
        KaleidoscopeTradeOffers.register();
        KaleidoscopeVillagerProfessions.register();
        StructurePoolModifiers.register();

        TagKeyEvents.modifyEntriesEvent(KaleidoscopeItemTags.HORSE_ARMOR).register(entries -> {
            Registries.ITEM.streamEntries().filter(item -> isHorseArmor(item.value())).forEach(entries::add);
        });
        TagKeyEvents.modifyEntriesEvent(ItemTags.TRIMMABLE_ARMOR).register(entries -> {
            if (KaleidoscopeConfig.HORSE_ARMOR_TRIMS.get()) {
                entries.addAll(Registries.ITEM.streamEntries().filter(item -> item.isIn(KaleidoscopeItemTags.HORSE_ARMOR)).toList());
            }
        });

        registerLootTableEvents();

        DispenserBlock.registerBehavior(KaleidoscopeItems.NETHERITE_SHEARS, new ShearsDispenserBehavior());
        DispenserBlock.registerBehavior(KaleidoscopeItems.CRIMSON_BOAT, new BoatDispenserBehavior(KaleidoscopeEntityTypes.CRIMSON_BOAT));
        DispenserBlock.registerBehavior(KaleidoscopeItems.CRIMSON_CHEST_BOAT, new BoatDispenserBehavior(KaleidoscopeEntityTypes.CRIMSON_CHEST_BOAT));
        DispenserBlock.registerBehavior(KaleidoscopeItems.WARPED_BOAT, new BoatDispenserBehavior(KaleidoscopeEntityTypes.WARPED_BOAT));
        DispenserBlock.registerBehavior(KaleidoscopeItems.WARPED_CHEST_BOAT, new BoatDispenserBehavior(KaleidoscopeEntityTypes.WARPED_CHEST_BOAT));

        PayloadTypeRegistry.playS2C().register(OpenStriderScreenS2CPacket.PACKET_ID, OpenStriderScreenS2CPacket.PACKET_CODEC);
        PayloadTypeRegistry.playS2C().register(StopJukeboxMinecartPlayingS2CPacket.PACKET_ID, StopJukeboxMinecartPlayingS2CPacket.PACKET_CODEC);
        PayloadTypeRegistry.playS2C().register(UpdateJukeboxMinecartS2CPacket.PACKET_ID, UpdateJukeboxMinecartS2CPacket.PACKET_CODEC);

        ServerLifecycleEvents.SERVER_STARTED.register(server -> {
            Registry<StructureProcessorList> processorLists = server.getRegistryManager().getOrThrow(RegistryKeys.PROCESSOR_LIST);
            if (KaleidoscopeConfig.ADDITIONAL_CRACKED_BLOCKS.get() && processorLists != null) {
                addStructureProcessor(processorLists.getOrThrow(StructureProcessorLists.TRAIL_RUINS_HOUSES_ARCHAEOLOGY).value(), new RuleStructureProcessor(List.of(new StructureProcessorRule(new RandomBlockMatchRuleTest(Blocks.MUD_BRICKS, 0.2F), AlwaysTrueRuleTest.INSTANCE, KaleidoscopeBlocks.CRACKED_MUD_BRICKS.getDefaultState()))));
                addStructureProcessor(processorLists.getOrThrow(StructureProcessorLists.TRAIL_RUINS_ROADS_ARCHAEOLOGY).value(), new RuleStructureProcessor(List.of(new StructureProcessorRule(new RandomBlockMatchRuleTest(Blocks.MUD_BRICKS, 0.2F), AlwaysTrueRuleTest.INSTANCE, KaleidoscopeBlocks.CRACKED_MUD_BRICKS.getDefaultState()))));
                addStructureProcessor(processorLists.getOrThrow(StructureProcessorLists.TRAIL_RUINS_TOWER_TOP_ARCHAEOLOGY).value(), new RuleStructureProcessor(List.of(new StructureProcessorRule(new RandomBlockMatchRuleTest(Blocks.MUD_BRICKS, 0.2F), AlwaysTrueRuleTest.INSTANCE, KaleidoscopeBlocks.CRACKED_MUD_BRICKS.getDefaultState()))));
                addStructureProcessor(processorLists.getOrThrow(StructureProcessorLists.TRIAL_CHAMBERS_COPPER_BULB_DEGRADATION).value(), new RuleStructureProcessor(List.of(new StructureProcessorRule(new RandomBlockMatchRuleTest(Blocks.TUFF_BRICKS, 0.3F), AlwaysTrueRuleTest.INSTANCE, KaleidoscopeBlocks.CRACKED_TUFF_BRICKS.getDefaultState()))));
            }
        });
    }
}
