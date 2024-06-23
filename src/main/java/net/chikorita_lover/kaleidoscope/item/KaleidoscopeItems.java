package net.chikorita_lover.kaleidoscope.item;

import com.chocohead.mm.api.ClassTinkerers;
import net.chikorita_lover.kaleidoscope.Kaleidoscope;
import net.chikorita_lover.kaleidoscope.block.KaleidoscopeBlocks;
import net.fabricmc.fabric.api.registry.CompostingChanceRegistry;
import net.fabricmc.fabric.api.registry.FuelRegistry;
import net.minecraft.block.Block;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.FireworkExplosionComponent;
import net.minecraft.component.type.ToolComponent;
import net.minecraft.entity.vehicle.BoatEntity;
import net.minecraft.item.*;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.minecraft.util.Rarity;

import java.util.ArrayList;

public class KaleidoscopeItems {
    public static final BoatEntity.Type CRIMSON_BOAT_TYPE = ClassTinkerers.getEnum(BoatEntity.Type.class, "CRIMSON");
    public static final BoatEntity.Type WARPED_BOAT_TYPE = ClassTinkerers.getEnum(BoatEntity.Type.class, "WARPED");

    public static final Item NETHERITE_SHEARS = register("netherite_shears", new ShearsItem(new Item.Settings().maxDamage(1934).component(DataComponentTypes.TOOL, createNetheriteShearsToolComponent()).fireproof()));

    public static final Item CRIMSON_BOAT = register("crimson_boat", new BoatItem(false, CRIMSON_BOAT_TYPE, new Item.Settings().maxCount(1)));
    public static final Item CRIMSON_CHEST_BOAT = register("crimson_chest_boat", new BoatItem(true, CRIMSON_BOAT_TYPE, new Item.Settings().maxCount(1)));
    public static final Item WARPED_BOAT = register("warped_boat", new BoatItem(false, WARPED_BOAT_TYPE, new Item.Settings().maxCount(1)));
    public static final Item WARPED_CHEST_BOAT = register("warped_chest_boat", new BoatItem(true, WARPED_BOAT_TYPE, new Item.Settings().maxCount(1)));
    public static final Item CAKE_SLICE = register("cake_slice", new CakeSliceItem(new Item.Settings().food(KaleidoscopeFoodComponents.CAKE_SLICE)));
    public static final Item DISC_FRAGMENT_PIGSTEP = register("disc_fragment_pigstep", new DiscFragmentItem(new Item.Settings()));

    public static final Item LARGE_BALL_FIREWORK_SHELL = register("large_ball_firework_shell", createFireworkShell(FireworkExplosionComponent.Type.LARGE_BALL, Rarity.COMMON));
    public static final Item STAR_FIREWORK_SHELL = register("star_firework_shell", createFireworkShell(FireworkExplosionComponent.Type.STAR, Rarity.COMMON));
    public static final Item CREEPER_FIREWORK_SHELL = register("creeper_firework_shell", createFireworkShell(FireworkExplosionComponent.Type.CREEPER, Rarity.UNCOMMON));
    public static final Item BURST_FIREWORK_SHELL = register("burst_firework_shell", createFireworkShell(FireworkExplosionComponent.Type.BURST, Rarity.COMMON));

    private static FireworkShellItem createFireworkShell(FireworkExplosionComponent.Type type, Rarity rarity) {
        return new FireworkShellItem(type, new Item.Settings().rarity(rarity));
    }

    private static ToolComponent createNetheriteShearsToolComponent() {
        ToolComponent component = ShearsItem.createToolComponent();
        ArrayList<ToolComponent.Rule> rules = new ArrayList<>();
        for (ToolComponent.Rule rule : component.rules()) {
            rules.add(new ToolComponent.Rule(rule.blocks(), rule.speed().map(f -> f * 1.5F), rule.correctForDrops()));
        }
        return new ToolComponent(rules, component.defaultMiningSpeed(), component.damagePerBlock());
    }

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
        return register(Kaleidoscope.of(id), item);
    }

    private static Item register(Identifier id, Item item) {
        if (item instanceof BlockItem) {
            ((BlockItem) item).appendBlocks(Item.BLOCK_ITEMS, item);
        }

        return Registry.register(Registries.ITEM, id, item);
    }

    public static void registerCompostingChances() {
        CompostingChanceRegistry.INSTANCE.add(CAKE_SLICE, 0.85F);
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
