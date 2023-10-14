package com.chikoritalover.kaleidoscope;

import com.chocohead.mm.api.ClassTinkerers;
import net.fabricmc.loader.api.FabricLoader;
import net.fabricmc.loader.api.MappingResolver;
import net.minecraft.block.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;

public class KaleidoscopeEarlyRiser implements Runnable {
    @Override
    public void run() {
        MappingResolver mappingResolver = FabricLoader.getInstance().getMappingResolver();

        String recipeBookCategory = mappingResolver.mapClassName("intermediary", "net.minecraft.class_5421");
        ClassTinkerers.enumBuilder(recipeBookCategory).addEnum("KALEIDOSCOPE_KILN").build();

        String recipeBookGroup = mappingResolver.mapClassName("intermediary", "net.minecraft.class_314");
        String itemStack = "[L" + mappingResolver.mapClassName("intermediary", "net.minecraft.class_1799") + ';';
        ClassTinkerers.enumBuilder(recipeBookGroup, itemStack).addEnum("KALEIDOSCOPE_KILN_SEARCH", () -> new Object[]{new ItemStack[]{new ItemStack(Items.COMPASS)}}).build();
        ClassTinkerers.enumBuilder(recipeBookGroup, itemStack).addEnum("KALEIDOSCOPE_KILN_BLOCKS", () -> new Object[]{new ItemStack[]{new ItemStack(Blocks.STONE)}}).build();
        ClassTinkerers.enumBuilder(recipeBookGroup, itemStack).addEnum("KALEIDOSCOPE_KILN_MISC", () -> new Object[]{new ItemStack[]{new ItemStack(Items.LAVA_BUCKET), new ItemStack(Items.CHARCOAL)}}).build();
    }
}
