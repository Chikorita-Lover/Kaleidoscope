package com.chikorita_lover.chikorita_lover_mod.registry;

import net.minecraft.block.cauldron.CauldronBehavior;
import net.minecraft.item.Items;

public class ModCauldronBehavior {
    public static void register() {
        CauldronBehavior.WATER_CAULDRON_BEHAVIOR.put(Items.BUNDLE, CauldronBehavior.CLEAN_DYEABLE_ITEM);
    }
}
