package com.chikoritalover.kaleidoscope.registry;

import com.chikoritalover.kaleidoscope.Kaleidoscope;
import com.chikoritalover.kaleidoscope.block.entity.KilnBlockEntity;
import com.chikoritalover.kaleidoscope.block.entity.PotionCauldronBlockEntity;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class ModBlockEntities {
    public static BlockEntityType<KilnBlockEntity> KILN;
    public static BlockEntityType<PotionCauldronBlockEntity> POTION_CAULDRON;

    public static void register() {
        KILN = Registry.register(
                Registry.BLOCK_ENTITY_TYPE,
                new Identifier(Kaleidoscope.MODID, "kiln"),
                FabricBlockEntityTypeBuilder.create(KilnBlockEntity::new, ModBlocks.KILN).build(null)
        );
        POTION_CAULDRON = Registry.register(
                Registry.BLOCK_ENTITY_TYPE,
                new Identifier(Kaleidoscope.MODID, "potion_cauldron"),
                FabricBlockEntityTypeBuilder.create(PotionCauldronBlockEntity::new, ModBlocks.POTION_CAULDRON).build(null)
        );
    }
}
