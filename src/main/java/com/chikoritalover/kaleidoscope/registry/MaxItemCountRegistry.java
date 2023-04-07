package com.chikoritalover.kaleidoscope.registry;

import net.minecraft.item.ItemConvertible;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class MaxItemCountRegistry {
    public static Map<ItemConvertible, Integer> MAX_COUNTS = new HashMap<>();

    /**
     * Modifies the max stack count of a provided item.
     *
     * @param item the item to be affected
     * @param count the new max stack count
     */
    public static void registerMaxItemCount(ItemConvertible item, int count) {
        Objects.requireNonNull(item, "Affected item cannot be null!");
        MAX_COUNTS.put(item, count);
    }
}
