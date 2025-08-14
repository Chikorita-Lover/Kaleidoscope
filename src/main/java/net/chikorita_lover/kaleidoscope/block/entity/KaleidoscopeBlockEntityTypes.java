package net.chikorita_lover.kaleidoscope.block.entity;

import net.chikorita_lover.kaleidoscope.Kaleidoscope;
import net.chikorita_lover.kaleidoscope.block.KaleidoscopeBlocks;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.block.Block;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;

public class KaleidoscopeBlockEntityTypes {
    public static final BlockEntityType<KilnBlockEntity> KILN = register("kiln", KilnBlockEntity::new, KaleidoscopeBlocks.KILN);

    public static void register() {
    }

    private static <T extends BlockEntity> BlockEntityType<T> register(String path, FabricBlockEntityTypeBuilder.Factory<? extends T> factory, Block... blocks) {
        return Registry.register(Registries.BLOCK_ENTITY_TYPE, Kaleidoscope.of(path), FabricBlockEntityTypeBuilder.<T>create(factory, blocks).build());
    }
}
