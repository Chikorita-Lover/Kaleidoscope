package com.chikorita_lover.chikorita_lover_mod.registry;

import com.chikorita_lover.chikorita_lover_mod.ChikoritaLoverMod;
import com.chikorita_lover.chikorita_lover_mod.block.entity.KilnBlockEntity;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class ModBlockEntities {
    public static BlockEntityType<KilnBlockEntity> KILN;

    public static void register() {
        KILN = Registry.register(
                Registry.BLOCK_ENTITY_TYPE,
                new Identifier(ChikoritaLoverMod.MODID, "kiln"),
                FabricBlockEntityTypeBuilder.create(KilnBlockEntity::new, ModBlocks.KILN).build(null)
        );
    }
}