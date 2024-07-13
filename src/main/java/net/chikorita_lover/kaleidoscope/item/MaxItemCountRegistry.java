package net.chikorita_lover.kaleidoscope.item;

import it.unimi.dsi.fastutil.objects.Object2IntArrayMap;
import net.fabricmc.fabric.api.tag.convention.v2.ConventionalItemTags;
import net.minecraft.item.Item;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.registry.tag.ItemTags;
import net.minecraft.registry.tag.TagKey;

import java.util.Objects;
import java.util.Optional;

public class MaxItemCountRegistry {
    private static final Object2IntArrayMap<TagKey<Item>> MAX_COUNTS_FROM_TAG = new Object2IntArrayMap<>();
    private static final Object2IntArrayMap<Item> MAX_COUNTS = new Object2IntArrayMap<>();

    public static void register() {
        registerFromTag(ItemTags.BANNERS, 64);
        registerFromTag(ItemTags.HANGING_SIGNS, 64);
        registerFromTag(ConventionalItemTags.MUSIC_DISCS, 64);
        registerFromTag(ItemTags.SIGNS, 64);
        registerItem(Items.CAKE, 16);
        registerItem(Items.EGG, 64);
        registerItem(Items.POTION, 16);
        registerItem(Items.SNOWBALL, 64);
    }

    /**
     * Modifies the max stack count of a provided item.
     *
     * @param item  the item to be affected
     * @param count the new max stack count
     */
    public static void registerItem(ItemConvertible item, int count) {
        Objects.requireNonNull(item, "Affected item cannot be null!");
        MAX_COUNTS.put(item.asItem(), count);
    }

    /**
     * Modifies the max stack counts of items of a provided item tag.
     *
     * @param tag   the item tag to be affected
     * @param count the new max stack count
     */
    public static void registerFromTag(TagKey<Item> tag, int count) {
        Objects.requireNonNull(tag, "Affected item tag cannot be null!");
        MAX_COUNTS_FROM_TAG.put(tag, count);
    }

    /**
     * Tries to get the modified max count of a provided item.
     *
     * @param item the item to be affected
     * @return an optional that may contain the max count
     */
    public static Optional<Integer> getMaxItemCount(ItemConvertible item) {
        ItemStack stack = item.asItem().getDefaultStack();
        if (MAX_COUNTS.containsKey(item.asItem())) {
            return Optional.of(MAX_COUNTS.getInt(item.asItem()));
        }
        for (TagKey<Item> tag : MAX_COUNTS_FROM_TAG.keySet()) {
            if (stack.isIn(tag)) {
                return Optional.of(MAX_COUNTS_FROM_TAG.getInt(tag));
            }
        }
        return Optional.empty();
    }
}
