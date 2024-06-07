package net.chikorita_lover.kaleidoscope.mixin.structure;

import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.structure.StructurePiece;
import net.minecraft.world.ChunkRegion;
import net.minecraft.world.StructureWorldAccess;
import net.minecraft.world.gen.structure.StructureKeys;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Mixin(StructurePiece.class)
public class StructurePieceMixin {
    @Unique
    private static BlockState tryModifyBlockState(StructureWorldAccess world, BlockState state) {
        if (!(world instanceof ChunkRegion chunkRegion) || chunkRegion.currentlyGeneratingStructureName == null || !chunkRegion.currentlyGeneratingStructureName.get().equals(StructureKeys.FORTRESS.toString())) {
            return state;
        }
        if (state.isOf(Blocks.NETHER_BRICKS) && world.getRandom().nextFloat() < 0.2F) {
            return Blocks.CRACKED_NETHER_BRICKS.getStateWithProperties(state);
        }
        return state;
    }

    @ModifyVariable(method = "addBlock", at = @At("HEAD"), argsOnly = true)
    private BlockState getBlockToPlace(BlockState block, @Local(argsOnly = true) StructureWorldAccess world) {
        return tryModifyBlockState(world, block);
    }

    @ModifyArg(method = "fillDownwards", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/StructureWorldAccess;setBlockState(Lnet/minecraft/util/math/BlockPos;Lnet/minecraft/block/BlockState;I)Z"))
    private BlockState getStateToFill(BlockState state, @Local(argsOnly = true) StructureWorldAccess world) {
        return tryModifyBlockState(world, state);
    }
}
