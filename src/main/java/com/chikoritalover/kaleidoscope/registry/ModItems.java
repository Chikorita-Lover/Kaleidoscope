package com.chikoritalover.kaleidoscope.registry;

import com.chikoritalover.kaleidoscope.Kaleidoscope;
import com.chikoritalover.kaleidoscope.item.CakeSliceItem;
import net.fabricmc.fabric.api.registry.CompostingChanceRegistry;
import net.fabricmc.fabric.api.registry.FuelRegistry;
import net.minecraft.item.*;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class ModItems {
    public static final Item CRIMSON_BOAT = register("crimson_boat", new BoatItem(false, ModBoatType.CRIMSON, new Item.Settings().group(ItemGroup.TRANSPORTATION).maxCount(1)));
    public static final Item CRIMSON_CHEST_BOAT = register("crimson_chest_boat", new BoatItem(true, ModBoatType.CRIMSON, new Item.Settings().group(ItemGroup.TRANSPORTATION).maxCount(1)));
    public static final Item WARPED_BOAT = register("warped_boat", new BoatItem(false, ModBoatType.WARPED, new Item.Settings().group(ItemGroup.TRANSPORTATION).maxCount(1)));
    public static final Item WARPED_CHEST_BOAT = register("warped_chest_boat", new BoatItem(true, ModBoatType.WARPED, new Item.Settings().group(ItemGroup.TRANSPORTATION).maxCount(1)));

    public static final Item CHAINMAIL_HORSE_ARMOR = register("chainmail_horse_armor", new HorseArmorItem(4, "chainmail", new Item.Settings().maxCount(1).group(ItemGroup.MISC)));
    public static final Item DISC_FRAGMENT_PIGSTEP = register("disc_fragment_pigstep", new DiscFragmentItem(new Item.Settings().group(ItemGroup.MISC)));

    public static final Item CAKE_SLICE = register("cake_slice", new CakeSliceItem(new Item.Settings().food(ModFoodComponents.CAKE_SLICE).group(ItemGroup.FOOD)));

    public static Item register(String id, Item item) {
        return Registry.register(Registry.ITEM, new Identifier(Kaleidoscope.MODID, id), item);
    }

    public static void register() {
    }

    public static void registerCompostingChances() {
        CompostingChanceRegistry.INSTANCE.add(Items.BAMBOO, 0.3F);
    }

    public static void registerFuels() {
        FuelRegistry.INSTANCE.add(ModBlocks.STICK_BUNDLE, 1000);
    }

    public static void registerMaxItemCounts() {
        MaxItemCountRegistry.registerMaxItemCount(Items.CAKE, 16);
        MaxItemCountRegistry.registerMaxItemCount(Items.EGG, 64);
        MaxItemCountRegistry.registerMaxItemCount(Items.POTION, 16);
        MaxItemCountRegistry.registerMaxItemCount(Items.SNOWBALL, 64);
    }
}
