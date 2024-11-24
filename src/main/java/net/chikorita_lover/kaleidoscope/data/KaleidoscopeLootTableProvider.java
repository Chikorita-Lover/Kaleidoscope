package net.chikorita_lover.kaleidoscope.data;

import net.chikorita_lover.kaleidoscope.block.KaleidoscopeBlockFamilies;
import net.chikorita_lover.kaleidoscope.block.KaleidoscopeBlocks;
import net.chikorita_lover.kaleidoscope.registry.KaleidoscopeLootTables;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;
import net.minecraft.data.family.BlockFamily;
import net.minecraft.item.Items;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.LootTable;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.function.SetCountLootFunction;
import net.minecraft.loot.provider.number.ConstantLootNumberProvider;
import net.minecraft.loot.provider.number.UniformLootNumberProvider;

public class KaleidoscopeLootTableProvider extends FabricBlockLootTableProvider {
    public KaleidoscopeLootTableProvider(FabricDataOutput dataOutput) {
        super(dataOutput);
    }

    @Override
    public void generate() {
        this.addDrop(KaleidoscopeBlocks.POLISHED_GRANITE_WALL);
        this.addDrop(KaleidoscopeBlocks.POLISHED_DIORITE_WALL);
        this.addDrop(KaleidoscopeBlocks.POLISHED_ANDESITE_WALL);

        this.addFamilyVariantDrops(KaleidoscopeBlockFamilies.CALCITE);
        this.addFamilyDrops(KaleidoscopeBlockFamilies.POLISHED_CALCITE);
        this.addFamilyDrops(KaleidoscopeBlockFamilies.SMOOTH_CALCITE);
        this.addFamilyVariantDrops(KaleidoscopeBlockFamilies.SMOOTH_BASALT);

        this.addFamilyDrops(KaleidoscopeBlockFamilies.BRICK_MOSAIC);

        this.addFamilyVariantDrops(KaleidoscopeBlockFamilies.PACKED_MUD);
        this.addDrop(KaleidoscopeBlocks.CRACKED_MUD_BRICKS);

        this.addFamilyVariantDrops(KaleidoscopeBlockFamilies.RED_NETHER_BRICKS);

        this.addFamilyVariantDrops(KaleidoscopeBlockFamilies.END_STONE);
        this.addFamilyDrops(KaleidoscopeBlockFamilies.POLISHED_END_STONE);
        this.addDrop(KaleidoscopeBlocks.CRACKED_END_STONE_BRICKS);

        this.addDrop(KaleidoscopeBlocks.CHARCOAL_BLOCK, block -> this.drops(block, Items.CHARCOAL, ConstantLootNumberProvider.create(4.0F)));

        this.addFamilyVariantDrops(KaleidoscopeBlockFamilies.QUARTZ_BRICKS);

        this.addFamilyDrops(KaleidoscopeBlockFamilies.SMOOTH_COPPER);
        this.addFamilyDrops(KaleidoscopeBlockFamilies.EXPOSED_SMOOTH_COPPER);
        this.addFamilyDrops(KaleidoscopeBlockFamilies.WEATHERED_SMOOTH_COPPER);
        this.addFamilyDrops(KaleidoscopeBlockFamilies.OXIDIZED_SMOOTH_COPPER);
        this.addFamilyDrops(KaleidoscopeBlockFamilies.WAXED_SMOOTH_COPPER);
        this.addFamilyDrops(KaleidoscopeBlockFamilies.WAXED_EXPOSED_SMOOTH_COPPER);
        this.addFamilyDrops(KaleidoscopeBlockFamilies.WAXED_WEATHERED_SMOOTH_COPPER);
        this.addFamilyDrops(KaleidoscopeBlockFamilies.WAXED_OXIDIZED_SMOOTH_COPPER);

        this.addFamilyVariantDrops(KaleidoscopeBlockFamilies.TERRACOTTA);
        this.addFamilyVariantDrops(KaleidoscopeBlockFamilies.WHITE_TERRACOTTA);
        this.addFamilyVariantDrops(KaleidoscopeBlockFamilies.LIGHT_GRAY_TERRACOTTA);
        this.addFamilyVariantDrops(KaleidoscopeBlockFamilies.GRAY_TERRACOTTA);
        this.addFamilyVariantDrops(KaleidoscopeBlockFamilies.BLACK_TERRACOTTA);
        this.addFamilyVariantDrops(KaleidoscopeBlockFamilies.BROWN_TERRACOTTA);
        this.addFamilyVariantDrops(KaleidoscopeBlockFamilies.RED_TERRACOTTA);
        this.addFamilyVariantDrops(KaleidoscopeBlockFamilies.ORANGE_TERRACOTTA);
        this.addFamilyVariantDrops(KaleidoscopeBlockFamilies.YELLOW_TERRACOTTA);
        this.addFamilyVariantDrops(KaleidoscopeBlockFamilies.LIME_TERRACOTTA);
        this.addFamilyVariantDrops(KaleidoscopeBlockFamilies.GREEN_TERRACOTTA);
        this.addFamilyVariantDrops(KaleidoscopeBlockFamilies.CYAN_TERRACOTTA);
        this.addFamilyVariantDrops(KaleidoscopeBlockFamilies.LIGHT_BLUE_TERRACOTTA);
        this.addFamilyVariantDrops(KaleidoscopeBlockFamilies.BLUE_TERRACOTTA);
        this.addFamilyVariantDrops(KaleidoscopeBlockFamilies.PURPLE_TERRACOTTA);
        this.addFamilyVariantDrops(KaleidoscopeBlockFamilies.MAGENTA_TERRACOTTA);
        this.addFamilyVariantDrops(KaleidoscopeBlockFamilies.PINK_TERRACOTTA);

        this.addDrop(KaleidoscopeBlocks.GLASS_DOOR, this::doorDrops);
        this.addDrop(KaleidoscopeBlocks.WHITE_STAINED_GLASS_DOOR, this::doorDrops);
        this.addDrop(KaleidoscopeBlocks.LIGHT_GRAY_STAINED_GLASS_DOOR, this::doorDrops);
        this.addDrop(KaleidoscopeBlocks.GRAY_STAINED_GLASS_DOOR, this::doorDrops);
        this.addDrop(KaleidoscopeBlocks.BLACK_STAINED_GLASS_DOOR, this::doorDrops);
        this.addDrop(KaleidoscopeBlocks.BROWN_STAINED_GLASS_DOOR, this::doorDrops);
        this.addDrop(KaleidoscopeBlocks.RED_STAINED_GLASS_DOOR, this::doorDrops);
        this.addDrop(KaleidoscopeBlocks.ORANGE_STAINED_GLASS_DOOR, this::doorDrops);
        this.addDrop(KaleidoscopeBlocks.YELLOW_STAINED_GLASS_DOOR, this::doorDrops);
        this.addDrop(KaleidoscopeBlocks.LIME_STAINED_GLASS_DOOR, this::doorDrops);
        this.addDrop(KaleidoscopeBlocks.GREEN_STAINED_GLASS_DOOR, this::doorDrops);
        this.addDrop(KaleidoscopeBlocks.CYAN_STAINED_GLASS_DOOR, this::doorDrops);
        this.addDrop(KaleidoscopeBlocks.LIGHT_BLUE_STAINED_GLASS_DOOR, this::doorDrops);
        this.addDrop(KaleidoscopeBlocks.BLUE_STAINED_GLASS_DOOR, this::doorDrops);
        this.addDrop(KaleidoscopeBlocks.PURPLE_STAINED_GLASS_DOOR, this::doorDrops);
        this.addDrop(KaleidoscopeBlocks.MAGENTA_STAINED_GLASS_DOOR, this::doorDrops);
        this.addDrop(KaleidoscopeBlocks.PINK_STAINED_GLASS_DOOR, this::doorDrops);

        this.addDrop(KaleidoscopeBlocks.GLASS_TRAPDOOR);
        this.addDrop(KaleidoscopeBlocks.BLACK_STAINED_GLASS_TRAPDOOR);
        this.addDrop(KaleidoscopeBlocks.BLUE_STAINED_GLASS_TRAPDOOR);
        this.addDrop(KaleidoscopeBlocks.BROWN_STAINED_GLASS_TRAPDOOR);
        this.addDrop(KaleidoscopeBlocks.CYAN_STAINED_GLASS_TRAPDOOR);
        this.addDrop(KaleidoscopeBlocks.GRAY_STAINED_GLASS_TRAPDOOR);
        this.addDrop(KaleidoscopeBlocks.GREEN_STAINED_GLASS_TRAPDOOR);
        this.addDrop(KaleidoscopeBlocks.LIGHT_BLUE_STAINED_GLASS_TRAPDOOR);
        this.addDrop(KaleidoscopeBlocks.LIGHT_GRAY_STAINED_GLASS_TRAPDOOR);
        this.addDrop(KaleidoscopeBlocks.LIME_STAINED_GLASS_TRAPDOOR);
        this.addDrop(KaleidoscopeBlocks.MAGENTA_STAINED_GLASS_TRAPDOOR);
        this.addDrop(KaleidoscopeBlocks.ORANGE_STAINED_GLASS_TRAPDOOR);
        this.addDrop(KaleidoscopeBlocks.PINK_STAINED_GLASS_TRAPDOOR);
        this.addDrop(KaleidoscopeBlocks.PURPLE_STAINED_GLASS_TRAPDOOR);
        this.addDrop(KaleidoscopeBlocks.RED_STAINED_GLASS_TRAPDOOR);
        this.addDrop(KaleidoscopeBlocks.WHITE_STAINED_GLASS_TRAPDOOR);
        this.addDrop(KaleidoscopeBlocks.YELLOW_STAINED_GLASS_TRAPDOOR);

        this.addDrop(KaleidoscopeBlocks.STICK_BLOCK);
        this.addDrop(KaleidoscopeBlocks.SOUL_JACK_O_LANTERN);

        this.addDrop(KaleidoscopeBlocks.FIREWORKS_TABLE);
        this.addDrop(KaleidoscopeBlocks.KILN, this::nameableContainerDrops);

        this.lootTables.put(KaleidoscopeLootTables.HERO_OF_THE_VILLAGE_GLASSBLOWER_GIFT_GAMEPLAY, LootTable.builder().pool(LootPool.builder().with(ItemEntry.builder(Items.GLASS_BOTTLE))));
        this.lootTables.put(KaleidoscopeLootTables.HERO_OF_THE_VILLAGE_FIREWORKER_GIFT_GAMEPLAY, LootTable.builder().pool(LootPool.builder().with(ItemEntry.builder(Items.GUNPOWDER)).with(ItemEntry.builder(Items.FIRE_CHARGE))));
        this.lootTables.put(KaleidoscopeLootTables.VILLAGE_FIREWORKER_CHEST, LootTable.builder().pool(LootPool.builder().rolls(UniformLootNumberProvider.create(1.0F, 5.0F)).with(ItemEntry.builder(Items.EMERALD)).with(ItemEntry.builder(Items.PAPER).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0F, 3.0F)))).with(ItemEntry.builder(Items.GUNPOWDER)).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0F, 3.0F))).with(ItemEntry.builder(Items.FIRE_CHARGE)).with(ItemEntry.builder(Items.BONE_MEAL).weight(2).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0F, 3.0F)))).with(ItemEntry.builder(Items.CHARCOAL).weight(2).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0F, 3.0F))))));
        this.lootTables.put(KaleidoscopeLootTables.VILLAGE_GLASSBLOWER_CHEST, LootTable.builder().pool(LootPool.builder().rolls(UniformLootNumberProvider.create(1.0F, 5.0F)).with(ItemEntry.builder(Items.EMERALD)).with(ItemEntry.builder(Items.COPPER_INGOT).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0F, 3.0F)))).with(ItemEntry.builder(Items.GLASS)).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0F, 3.0F))).with(ItemEntry.builder(Items.GLASS_BOTTLE).weight(2)).with(ItemEntry.builder(Items.COAL).weight(2).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0F, 3.0F)))).with(ItemEntry.builder(Items.BREAD).weight(4).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0F, 4.0F))))));
    }

    private void addFamilyDrops(BlockFamily family) {
        this.addDrop(family.getBaseBlock());
        this.addFamilyVariantDrops(family);
    }

    private void addFamilyVariantDrops(BlockFamily family) {
        family.getVariants().forEach((variant, block) -> {
            switch (variant) {
                case DOOR -> this.addDrop(block, this::doorDrops);
                case SLAB -> this.addDrop(block, this::slabDrops);
                case WALL_SIGN -> this.addDrop(block, family.getVariant(BlockFamily.Variant.SIGN));
                default -> this.addDrop(block);
            }
        });
    }
}
