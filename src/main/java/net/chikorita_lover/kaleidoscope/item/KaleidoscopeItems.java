package net.chikorita_lover.kaleidoscope.item;

import net.chikorita_lover.kaleidoscope.Kaleidoscope;
import net.chikorita_lover.kaleidoscope.block.KaleidoscopeBlocks;
import net.chikorita_lover.kaleidoscope.entity.JukeboxMinecartEntity;
import net.chikorita_lover.kaleidoscope.registry.KaleidoscopeBoatTypes;
import net.fabricmc.fabric.api.registry.FuelRegistry;
import net.minecraft.block.Block;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.FireworkExplosionComponent;
import net.minecraft.component.type.ToolComponent;
import net.minecraft.item.*;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.minecraft.util.Rarity;

import java.util.ArrayList;

public class KaleidoscopeItems {
    public static final Item NETHERITE_SHEARS = register("netherite_shears", new ShearsItem(new Item.Settings().maxDamage(1934).component(DataComponentTypes.TOOL, createNetheriteShearsToolComponent()).fireproof()));

    public static final Item CRIMSON_BOAT = register("crimson_boat", new BoatItem(false, KaleidoscopeBoatTypes.CRIMSON, new Item.Settings().maxCount(1)));
    public static final Item CRIMSON_CHEST_BOAT = register("crimson_chest_boat", new BoatItem(true, KaleidoscopeBoatTypes.CRIMSON, new Item.Settings().maxCount(1)));
    public static final Item WARPED_BOAT = register("warped_boat", new BoatItem(false, KaleidoscopeBoatTypes.WARPED, new Item.Settings().maxCount(1)));
    public static final Item WARPED_CHEST_BOAT = register("warped_chest_boat", new BoatItem(true, KaleidoscopeBoatTypes.WARPED, new Item.Settings().maxCount(1)));
    public static final Item JUKEBOX_MINECART = register("jukebox_minecart", new MinecartItem(JukeboxMinecartEntity.JUKEBOX_TYPE, new Item.Settings().maxCount(1)));

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

    public static Item register(String id, Block block) {
        return register(id, new BlockItem(block, new Item.Settings()));
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

    public static void registerFuels() {
        FuelRegistry.INSTANCE.add(KaleidoscopeBlocks.CHARCOAL_BLOCK, 7000);
        FuelRegistry.INSTANCE.add(KaleidoscopeBlocks.STICK_BLOCK, 1000);
    }
}
