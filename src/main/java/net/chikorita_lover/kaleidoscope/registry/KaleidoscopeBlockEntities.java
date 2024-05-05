package net.chikorita_lover.kaleidoscope.registry;

import net.chikorita_lover.kaleidoscope.Kaleidoscope;
import net.chikorita_lover.kaleidoscope.block.entity.KilnBlockEntity;
import net.chikorita_lover.kaleidoscope.block.entity.PotionCauldronBlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class KaleidoscopeBlockEntities {
    public static BlockEntityType<KilnBlockEntity> KILN;
    public static BlockEntityType<PotionCauldronBlockEntity> POTION_CAULDRON;

    public static void register() {
        KILN = Registry.register(Registries.BLOCK_ENTITY_TYPE, new Identifier(Kaleidoscope.MODID, "kiln"), BlockEntityType.Builder.create(KilnBlockEntity::new, KaleidoscopeBlocks.KILN).build(null));
        POTION_CAULDRON = Registry.register(Registries.BLOCK_ENTITY_TYPE, new Identifier(Kaleidoscope.MODID, "potion_cauldron"), BlockEntityType.Builder.create(PotionCauldronBlockEntity::new, KaleidoscopeBlocks.POTION_CAULDRON).build(null));
    }
}
