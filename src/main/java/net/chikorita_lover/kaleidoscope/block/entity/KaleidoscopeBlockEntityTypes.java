package net.chikorita_lover.kaleidoscope.block.entity;

import net.chikorita_lover.kaleidoscope.Kaleidoscope;
import net.chikorita_lover.kaleidoscope.block.KaleidoscopeBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;

public class KaleidoscopeBlockEntityTypes {
    public static final BlockEntityType<KilnBlockEntity> KILN = register("kiln", (pos, state) -> new KilnBlockEntity(pos, state), KaleidoscopeBlocks.KILN);
    public static final BlockEntityType<PotionCauldronBlockEntity> POTION_CAULDRON = register("potion_cauldron", (pos, state) -> new PotionCauldronBlockEntity(pos, state), KaleidoscopeBlocks.POTION_CAULDRON);

    public static void register() {
    }

    private static <T extends BlockEntity> BlockEntityType<T> register(String path, BlockEntityType.BlockEntityFactory<T> factory, Block... blocks) {
        return Registry.register(Registries.BLOCK_ENTITY_TYPE, Kaleidoscope.of(path), BlockEntityType.Builder.create(factory, blocks).build(null));
    }
}
