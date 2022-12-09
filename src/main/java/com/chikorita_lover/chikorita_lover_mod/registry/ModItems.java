package com.chikorita_lover.chikorita_lover_mod.registry;

import com.chikorita_lover.chikorita_lover_mod.ChikoritaLoverMod;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class ModItems {
    public static final BlockItem POLISHED_CALCITE = new BlockItem(ModBlocks.POLISHED_CALCITE, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS));
    public static final BlockItem POLISHED_CALCITE_SLAB = new BlockItem(ModBlocks.POLISHED_CALCITE_SLAB, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS));
    public static final BlockItem POLISHED_CALCITE_STAIRS = new BlockItem(ModBlocks.POLISHED_CALCITE_STAIRS, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS));
    public static void register(){
        Registry.register(Registry.ITEM, new Identifier(ChikoritaLoverMod.MODID, "polished_calcite"), POLISHED_CALCITE);
        Registry.register(Registry.ITEM, new Identifier(ChikoritaLoverMod.MODID, "polished_calcite_slab"), POLISHED_CALCITE_SLAB);
        Registry.register(Registry.ITEM, new Identifier(ChikoritaLoverMod.MODID, "polished_calcite_stairs"), POLISHED_CALCITE_STAIRS);
    }
}
