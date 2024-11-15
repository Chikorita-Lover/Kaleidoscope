package net.chikorita_lover.kaleidoscope;

import com.chocohead.mm.api.ClassTinkerers;
import net.chikorita_lover.kaleidoscope.registry.KaleidoscopeSoundEvents;
import net.fabricmc.loader.api.FabricLoader;
import net.fabricmc.loader.api.MappingResolver;
import net.minecraft.block.Blocks;
import net.minecraft.block.enums.NoteBlockInstrument;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;

public class KaleidoscopeEarlyRiser implements Runnable {
    @Override
    public void run() {
        MappingResolver mappingResolver = FabricLoader.getInstance().getMappingResolver();

        String noteBlockInstrument = mappingResolver.mapClassName("intermediary", "net.minecraft.class_2766");
        String registryEntry = 'L' + mappingResolver.mapClassName("intermediary", "net.minecraft.class_6880") + ';';
        String instrumentType = 'L' + mappingResolver.mapClassName("intermediary", "net.minecraft.class_2766$class_7994") + ';';
        ClassTinkerers.enumBuilder(noteBlockInstrument, String.class, registryEntry, instrumentType).addEnum("KALEIDOSCOPE_SAXOPHONE", () -> new Object[]{"kaleidoscope_saxophone", KaleidoscopeSoundEvents.BLOCK_NOTE_BLOCK_SAXOPHONE, NoteBlockInstrument.Type.BASE_BLOCK}).build();

        String recipeBookCategory = mappingResolver.mapClassName("intermediary", "net.minecraft.class_5421");
        ClassTinkerers.enumBuilder(recipeBookCategory).addEnum("KALEIDOSCOPE_KILN").build();

        String recipeBookGroup = mappingResolver.mapClassName("intermediary", "net.minecraft.class_314");
        String itemStack = "[L" + mappingResolver.mapClassName("intermediary", "net.minecraft.class_1799") + ';';
        ClassTinkerers.enumBuilder(recipeBookGroup, itemStack).addEnum("KALEIDOSCOPE_KILN_SEARCH", () -> new Object[]{new ItemStack[]{new ItemStack(Items.COMPASS)}}).build();
        ClassTinkerers.enumBuilder(recipeBookGroup, itemStack).addEnum("KALEIDOSCOPE_KILN_BLOCKS", () -> new Object[]{new ItemStack[]{new ItemStack(Blocks.STONE)}}).build();
        ClassTinkerers.enumBuilder(recipeBookGroup, itemStack).addEnum("KALEIDOSCOPE_KILN_MISC", () -> new Object[]{new ItemStack[]{new ItemStack(Items.LAVA_BUCKET), new ItemStack(Items.CHARCOAL)}}).build();

        String minecartType = mappingResolver.mapClassName("intermediary", "net.minecraft.class_1688$class_1689");
        ClassTinkerers.enumBuilder(minecartType).addEnum("JUKEBOX").build();
    }
}
