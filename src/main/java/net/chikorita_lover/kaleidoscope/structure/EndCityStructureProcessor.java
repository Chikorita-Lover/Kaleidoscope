package net.chikorita_lover.kaleidoscope.structure;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.chikorita_lover.kaleidoscope.Kaleidoscope;
import net.chikorita_lover.kaleidoscope.block.KaleidoscopeBlocks;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.structure.StructurePlacementData;
import net.minecraft.structure.StructureTemplate;
import net.minecraft.structure.processor.StructureProcessor;
import net.minecraft.structure.processor.StructureProcessorType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.WorldView;
import org.jetbrains.annotations.Nullable;

public class EndCityStructureProcessor extends StructureProcessor {
    public static final Codec<EndCityStructureProcessor> CODEC = RecordCodecBuilder.create((instance) -> instance.group(Codec.FLOAT.fieldOf("crackedness").forGetter(processor -> processor.crackedness)).apply(instance, EndCityStructureProcessor::new));
    private final float crackedness;

    public EndCityStructureProcessor(float crackedness) {
        this.crackedness = crackedness;
    }

    @Override
    @Nullable
    public StructureTemplate.StructureBlockInfo process(WorldView world, BlockPos pos, BlockPos pivot, StructureTemplate.StructureBlockInfo originalBlockInfo, StructureTemplate.StructureBlockInfo currentBlockInfo, StructurePlacementData data) {
        BlockPos blockPos = currentBlockInfo.pos();
        Random random = data.getRandom(blockPos);
        BlockState state = currentBlockInfo.state();
        BlockState state2 = null;
        if (random.nextFloat() < this.crackedness && state.isOf(Blocks.END_STONE_BRICKS)) {
            state2 = KaleidoscopeBlocks.CRACKED_END_STONE_BRICKS.getDefaultState();
        }
        if (state2 != null) {
            return new StructureTemplate.StructureBlockInfo(blockPos, state2, currentBlockInfo.nbt());
        }
        return currentBlockInfo;
    }

    @Override
    protected StructureProcessorType<?> getType() {
        return Kaleidoscope.END_CITY_STRUCTURE_PROCESSOR;
    }
}
