package com.chikoritalover.kaleidoscope.registry;

import com.chikoritalover.kaleidoscope.Kaleidoscope;
import com.chikoritalover.kaleidoscope.item.CakeSliceItem;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.fabricmc.fabric.api.registry.CompostingChanceRegistry;
import net.fabricmc.fabric.api.registry.FuelRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.item.*;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class KaleidoscopeItems {
    public static final Item CRIMSON_BOAT = register("crimson_boat", new BoatItem(false, KaleidoscopeBoatType.CRIMSON, new Item.Settings().maxCount(1)));
    public static final Item CRIMSON_CHEST_BOAT = register("crimson_chest_boat", new BoatItem(true, KaleidoscopeBoatType.CRIMSON, new Item.Settings().maxCount(1)));
    public static final Item WARPED_BOAT = register("warped_boat", new BoatItem(false, KaleidoscopeBoatType.WARPED, new Item.Settings().maxCount(1)));
    public static final Item WARPED_CHEST_BOAT = register("warped_chest_boat", new BoatItem(true, KaleidoscopeBoatType.WARPED, new Item.Settings().maxCount(1)));
    public static final Item CHAINMAIL_HORSE_ARMOR = register("chainmail_horse_armor", new HorseArmorItem(4, "chainmail", new Item.Settings().maxCount(1)));
    public static final Item NETHERITE_HORSE_ARMOR = register("netherite_horse_armor", new HorseArmorItem(11, "netherite", new Item.Settings().fireproof().maxCount(1)));
    public static final Item CAKE_SLICE = register("cake_slice", new CakeSliceItem(new Item.Settings().food(KaleidoscopeFoodComponents.CAKE_SLICE)));
    public static final Item DISC_FRAGMENT_PIGSTEP = register("disc_fragment_pigstep", new DiscFragmentItem(new Item.Settings()));

    public static Item register(Block block) {
        return register(new BlockItem(block, new Item.Settings()));
    }

    private static Item register(BlockItem item) {
        return register(item.getBlock(), item);
    }

    private static Item register(Block block, Item item) {
        return register(Registries.BLOCK.getId(block), item);
    }

    private static Item register(String id, Item item) {
        return register(new Identifier(Kaleidoscope.MODID, id), item);
    }

    private static Item register(Identifier id, Item item) {
        if (item instanceof BlockItem) {
            ((BlockItem)item).appendBlocks(Item.BLOCK_ITEMS, item);
        }

        return Registry.register(Registries.ITEM, id, item);
    }

    public static void registerCompostingChances() {
        CompostingChanceRegistry.INSTANCE.add(Items.BAMBOO, 0.3F);
    }

    public static void registerFuels() {
        FuelRegistry.INSTANCE.add(KaleidoscopeBlocks.CHARCOAL_BLOCK, 7000);
        FuelRegistry.INSTANCE.add(KaleidoscopeBlocks.STICK_BUNDLE, 300);
    }

    public static void registerMaxItemCounts() {
        MaxItemCountRegistry.registerMaxItemCount(Items.CAKE, 16);
        MaxItemCountRegistry.registerMaxItemCount(Items.EGG, 64);
        MaxItemCountRegistry.registerMaxItemCount(Items.POTION, 16);
        MaxItemCountRegistry.registerMaxItemCount(Items.SNOWBALL, 64);
    }
}
