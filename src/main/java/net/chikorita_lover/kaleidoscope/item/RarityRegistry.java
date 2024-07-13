package net.chikorita_lover.kaleidoscope.item;

import net.minecraft.item.DiscFragmentItem;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.item.SmithingTemplateItem;
import net.minecraft.registry.Registries;
import net.minecraft.util.Rarity;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.function.Predicate;

public class RarityRegistry {
    private static final HashMap<Item, Rarity> ITEM_TO_RARITY_MAP = new HashMap<>();
    private static boolean registered;

    public static Optional<Rarity> getRarity(Item item) {
        if (!registered) {
            registerPredicates();
        }
        return Optional.ofNullable(ITEM_TO_RARITY_MAP.getOrDefault(item, null));
    }

    private static void registerPredicates() {
        ITEM_TO_RARITY_MAP.put(Items.OMINOUS_BOTTLE, Rarity.UNCOMMON);
        ITEM_TO_RARITY_MAP.put(Items.ENDER_EYE, Rarity.UNCOMMON);
        ITEM_TO_RARITY_MAP.put(Items.ECHO_SHARD, Rarity.UNCOMMON);
        ITEM_TO_RARITY_MAP.put(Items.HEAVY_CORE, Rarity.UNCOMMON);
        ITEM_TO_RARITY_MAP.put(Items.ENDER_CHEST, Rarity.RARE);
        ITEM_TO_RARITY_MAP.put(Items.RECOVERY_COMPASS, Rarity.RARE);
        ITEM_TO_RARITY_MAP.put(Items.TRIDENT, Rarity.RARE);
        ITEM_TO_RARITY_MAP.put(Items.MACE, Rarity.RARE);
        HashMap<Predicate<Item>, Rarity> map = new HashMap<>();
        map.put(item -> item instanceof DiscFragmentItem, Rarity.UNCOMMON);
        map.put(item -> item instanceof SmithingTemplateItem, Rarity.UNCOMMON);
        for (Item item : Registries.ITEM) {
            if (ITEM_TO_RARITY_MAP.containsKey(item)) {
                continue;
            }
            for (Map.Entry<Predicate<Item>, Rarity> entry : map.entrySet()) {
                if (entry.getKey().test(item)) {
                    ITEM_TO_RARITY_MAP.put(item, entry.getValue());
                    break;
                }
            }
        }
        registered = true;
    }
}
