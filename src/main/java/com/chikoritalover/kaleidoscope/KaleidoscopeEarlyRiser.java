package com.chikoritalover.kaleidoscope;

import com.chikoritalover.kaleidoscope.registry.KaleidoscopeSoundEvents;
import com.chocohead.mm.api.ClassTinkerers;
import net.fabricmc.loader.api.FabricLoader;
import net.fabricmc.loader.api.MappingResolver;
import net.minecraft.block.Blocks;
import net.minecraft.block.enums.Instrument;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;

public class KaleidoscopeEarlyRiser implements Runnable {
    @Override
    public void run() {
        MappingResolver mappingResolver = FabricLoader.getInstance().getMappingResolver();

        String instrument = mappingResolver.mapClassName("intermediary", "net.minecraft.class_2766");
        String registryEntry = 'L' + mappingResolver.mapClassName("intermediary", "net.minecraft.class_6880") + ';';
        String instrumentType = 'L' + mappingResolver.mapClassName("intermediary", "net.minecraft.class_2766$class_7994") + ';';
        ClassTinkerers.enumBuilder(instrument, String.class, registryEntry, instrumentType).addEnum("KALEIDOSCOPE_SAXOPHONE", () -> new Object[]{"kaleidoscope_saxophone", KaleidoscopeSoundEvents.BLOCK_NOTE_BLOCK_SAXOPHONE, Instrument.Type.BASE_BLOCK}).build();

        String boatEntityType = mappingResolver.mapClassName("intermediary", "net.minecraft.class_1690$class_1692");
        String block = 'L' + mappingResolver.mapClassName("intermediary", "net.minecraft.class_2248") + ';';
        ClassTinkerers.enumBuilder(boatEntityType, block, String.class).addEnum("CRIMSON", () -> new Object[]{Blocks.CRIMSON_PLANKS, "crimson"}).build();
        ClassTinkerers.enumBuilder(boatEntityType, block, String.class).addEnum("WARPED", () -> new Object[]{Blocks.WARPED_PLANKS, "warped"}).build();

        String recipeBookCategory = mappingResolver.mapClassName("intermediary", "net.minecraft.class_5421");
        ClassTinkerers.enumBuilder(recipeBookCategory).addEnum("KALEIDOSCOPE_KILN").build();

        String recipeBookGroup = mappingResolver.mapClassName("intermediary", "net.minecraft.class_314");
        String itemStack = "[L" + mappingResolver.mapClassName("intermediary", "net.minecraft.class_1799") + ';';
        ClassTinkerers.enumBuilder(recipeBookGroup, itemStack).addEnum("KALEIDOSCOPE_KILN_SEARCH", () -> new Object[]{new ItemStack[]{new ItemStack(Items.COMPASS)}}).build();
        ClassTinkerers.enumBuilder(recipeBookGroup, itemStack).addEnum("KALEIDOSCOPE_KILN_BLOCKS", () -> new Object[]{new ItemStack[]{new ItemStack(Blocks.STONE)}}).build();
        ClassTinkerers.enumBuilder(recipeBookGroup, itemStack).addEnum("KALEIDOSCOPE_KILN_MISC", () -> new Object[]{new ItemStack[]{new ItemStack(Items.LAVA_BUCKET), new ItemStack(Items.CHARCOAL)}}).build();
    }
}