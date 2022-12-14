package com.chikorita_lover.chikorita_lover_mod.registry;

import com.chikorita_lover.chikorita_lover_mod.ChikoritaLoverMod;
import net.minecraft.item.Item;
import net.minecraft.tag.TagKey;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class ModItemTags {
    public static final TagKey<Item> DIRT_BRICK_MATERIALS = TagKey.of(Registry.ITEM_KEY, new Identifier(ChikoritaLoverMod.MODID, "dirt_brick_materials"));
}
