package net.chikorita_lover.kaleidoscope.mixin.structure;

import net.chikorita_lover.kaleidoscope.block.PotionCauldronBlock;
import net.chikorita_lover.kaleidoscope.block.entity.PotionCauldronBlockEntity;
import net.chikorita_lover.kaleidoscope.registry.KaleidoscopeBlocks;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.PotionContentsComponent;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.potion.Potion;
import net.minecraft.potion.Potions;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.structure.ShiftableStructurePiece;
import net.minecraft.structure.StructurePieceType;
import net.minecraft.structure.SwampHutGenerator;
import net.minecraft.util.math.BlockBox;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.StructureWorldAccess;
import net.minecraft.world.gen.StructureAccessor;
import net.minecraft.world.gen.chunk.ChunkGenerator;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;

@Mixin(SwampHutGenerator.class)
public abstract class SwampHutGeneratorMixin extends ShiftableStructurePiece {
    @Unique
    private static final List<RegistryEntry<Potion>> CAULDRON_POTIONS = List.of(Potions.FIRE_RESISTANCE, Potions.SWIFTNESS, Potions.SLOWNESS, Potions.WATER_BREATHING, Potions.HEALING, Potions.POISON, Potions.STRENGTH, Potions.WEAKNESS);

    protected SwampHutGeneratorMixin(StructurePieceType type, int x, int y, int z, int width, int height, int depth, Direction orientation) {
        super(type, x, y, z, width, height, depth, orientation);
    }

    @Inject(method = "generate", at = @At("TAIL"))
    private void generatePotionCauldron(StructureWorldAccess world, StructureAccessor structureAccessor, ChunkGenerator chunkGenerator, Random random, BlockBox chunkBox, ChunkPos chunkPos, BlockPos pivot, CallbackInfo ci) {
        BlockPos blockPos = this.offsetPos(4, 2, 6);
        this.addBlock(world, KaleidoscopeBlocks.POTION_CAULDRON.getDefaultState().with(PotionCauldronBlock.LEVEL, random.nextBetween(1, 2)), 4, 2, 6, chunkBox);
        BlockEntity blockEntity = world.getBlockEntity(blockPos);
        if (blockEntity instanceof PotionCauldronBlockEntity potionCauldronBlockEntity) {
            RegistryEntry<Potion> potion = CAULDRON_POTIONS.get(random.nextInt(CAULDRON_POTIONS.size()));
            ItemStack potionStack = new ItemStack(Items.POTION);
            potionStack.set(DataComponentTypes.POTION_CONTENTS, new PotionContentsComponent(potion));
            potionCauldronBlockEntity.setStack(potionStack);
        }
    }
}
