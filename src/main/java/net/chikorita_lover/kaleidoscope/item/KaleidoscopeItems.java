package net.chikorita_lover.kaleidoscope.item;

import net.chikorita_lover.chicory.api.resource.ToggleableFeatureRegistry;
import net.chikorita_lover.kaleidoscope.Kaleidoscope;
import net.chikorita_lover.kaleidoscope.KaleidoscopeConfig;
import net.chikorita_lover.kaleidoscope.block.KaleidoscopeBlocks;
import net.chikorita_lover.kaleidoscope.entity.KaleidoscopeEntityTypes;
import net.chikorita_lover.kaleidoscope.registry.tag.KaleidoscopeItemTags;
import net.fabricmc.fabric.api.registry.FuelRegistryEvents;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.FireworkExplosionComponent;
import net.minecraft.component.type.ToolComponent;
import net.minecraft.item.*;
import net.minecraft.item.equipment.ArmorMaterials;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Rarity;

import java.util.ArrayList;
import java.util.function.Function;
import java.util.function.Supplier;

public class KaleidoscopeItems {
    public static final Item NETHERITE_SHEARS = register("netherite_shears", ShearsItem::new, new Item.Settings().maxDamage(1934).component(DataComponentTypes.TOOL, createNetheriteShearsToolComponent()).fireproof(), KaleidoscopeConfig.NETHERITE_SHEARS);

    public static final Item CRIMSON_BOAT = register("crimson_boat", settings -> new BoatItem(KaleidoscopeEntityTypes.CRIMSON_BOAT, settings), new Item.Settings().maxCount(1), KaleidoscopeConfig.NETHER_BOATS);
    public static final Item CRIMSON_CHEST_BOAT = register("crimson_chest_boat", settings -> new BoatItem(KaleidoscopeEntityTypes.CRIMSON_CHEST_BOAT, settings), new Item.Settings().maxCount(1), KaleidoscopeConfig.NETHER_BOATS);
    public static final Item WARPED_BOAT = register("warped_boat", settings -> new BoatItem(KaleidoscopeEntityTypes.WARPED_BOAT, settings), new Item.Settings().maxCount(1), KaleidoscopeConfig.NETHER_BOATS);
    public static final Item WARPED_CHEST_BOAT = register("warped_chest_boat", settings -> new BoatItem(KaleidoscopeEntityTypes.WARPED_CHEST_BOAT, settings), new Item.Settings().maxCount(1), KaleidoscopeConfig.NETHER_BOATS);
    public static final Item JUKEBOX_MINECART = register("jukebox_minecart", settings -> new MinecartItem(KaleidoscopeEntityTypes.JUKEBOX_MINECART, settings), new Item.Settings().maxCount(1), KaleidoscopeConfig.JUKEBOX_MINECARTS);

    public static final Item CHAINMAIL_HORSE_ARMOR = register("chainmail_horse_armor", new Item.Settings().horseArmor(ArmorMaterials.CHAIN), KaleidoscopeConfig.ADDITIONAL_HORSE_ARMORS);
    public static final Item NETHERITE_HORSE_ARMOR = register("netherite_horse_armor", new Item.Settings().horseArmor(ArmorMaterials.NETHERITE).fireproof(), KaleidoscopeConfig.ADDITIONAL_HORSE_ARMORS);

    public static final Item DISC_FRAGMENT_PIGSTEP = register("disc_fragment_pigstep", DiscFragmentItem::new, new Item.Settings().rarity(Rarity.RARE), KaleidoscopeConfig.ADDITIONAL_DISC_FRAGMENTS);
    public static final Item DISC_FRAGMENT_TEARS = register("disc_fragment_tears", DiscFragmentItem::new, new Item.Settings().rarity(Rarity.UNCOMMON), KaleidoscopeConfig.ADDITIONAL_DISC_FRAGMENTS);

    public static final Item LARGE_BALL_FIREWORK_SHELL = registerFireworkShellItem("large_ball_firework_shell", FireworkExplosionComponent.Type.LARGE_BALL, Rarity.COMMON);
    public static final Item STAR_FIREWORK_SHELL = registerFireworkShellItem("star_firework_shell", FireworkExplosionComponent.Type.STAR, Rarity.COMMON);
    public static final Item CREEPER_FIREWORK_SHELL = registerFireworkShellItem("creeper_firework_shell", FireworkExplosionComponent.Type.CREEPER, Rarity.UNCOMMON);
    public static final Item BURST_FIREWORK_SHELL = registerFireworkShellItem("burst_firework_shell", FireworkExplosionComponent.Type.BURST, Rarity.COMMON);

    private static Item registerFireworkShellItem(String id, final FireworkExplosionComponent.Type type, Rarity rarity) {
        return register(id, settings -> new FireworkShellItem(type, settings), new Item.Settings().rarity(rarity), KaleidoscopeConfig.FIREWORK_IMPROVEMENTS);
    }

    private static ToolComponent createNetheriteShearsToolComponent() {
        ToolComponent component = ShearsItem.createToolComponent();
        ArrayList<ToolComponent.Rule> rules = new ArrayList<>();
        for (ToolComponent.Rule rule : component.rules()) {
            rules.add(new ToolComponent.Rule(rule.blocks(), rule.speed().map(f -> f * 1.5F), rule.correctForDrops()));
        }
        return new ToolComponent(rules, component.defaultMiningSpeed(), component.damagePerBlock(), true);
    }

    private static RegistryKey<Item> keyOf(String id) {
        return RegistryKey.of(RegistryKeys.ITEM, Kaleidoscope.of(id));
    }

    private static Item register(String id, Item.Settings settings) {
        return register(id, Item::new, settings);
    }

    private static Item register(String id, Item.Settings settings, Supplier<Boolean> condition) {
        return register(id, Item::new, settings, condition);
    }

    private static Item register(String id, Function<Item.Settings, Item> factory, Item.Settings settings) {
        return Items.register(keyOf(id), factory, settings);
    }

    private static Item register(String id, Function<Item.Settings, Item> factory, Item.Settings settings, Supplier<Boolean> condition) {
        Item item = register(id, factory, settings);
        ToggleableFeatureRegistry.add(item, condition);
        return item;
    }

    public static void register() {
        FuelRegistryEvents.BUILD.register((builder, context) -> builder.add(KaleidoscopeBlocks.CHARCOAL_BLOCK, 8000).add(KaleidoscopeBlocks.STICK_BLOCK, 1000).add(KaleidoscopeItemTags.DYED_CHESTS, 30));
    }
}
