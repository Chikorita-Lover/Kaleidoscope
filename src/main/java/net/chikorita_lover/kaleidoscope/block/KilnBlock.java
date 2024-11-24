package net.chikorita_lover.kaleidoscope.block;

import net.chikorita_lover.kaleidoscope.block.entity.KaleidoscopeBlockEntityTypes;
import net.chikorita_lover.kaleidoscope.block.entity.KilnBlockEntity;
import net.chikorita_lover.kaleidoscope.registry.KaleidoscopeSoundEvents;
import net.chikorita_lover.kaleidoscope.registry.KaleidoscopeStats;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.AbstractFurnaceBlock;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityTicker;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.sound.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;

public class KilnBlock extends AbstractFurnaceBlock {
    public KilnBlock(AbstractBlock.Settings settings) {
        super(settings);
    }

    public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new KilnBlockEntity(pos, state);
    }

    @Nullable
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(World world, BlockState state, BlockEntityType<T> type) {
        return checkType(world, type, KaleidoscopeBlockEntityTypes.KILN);
    }

    protected void openScreen(World world, BlockPos pos, PlayerEntity player) {
        Optional<KilnBlockEntity> blockEntity = world.getBlockEntity(pos, KaleidoscopeBlockEntityTypes.KILN);
        if (blockEntity.isPresent()) {
            player.openHandledScreen(blockEntity.get());
            player.incrementStat(KaleidoscopeStats.INTERACT_WITH_KILN);
        }

    }

    public void randomDisplayTick(BlockState state, World world, BlockPos pos, Random random) {
        if (!state.get(LIT)) {
            return;
        }
        double x = pos.getX() + 0.5;
        double y = pos.getY();
        double z = pos.getZ() + 0.5;
        if (random.nextDouble() < 0.1) {
            world.playSound(x, y, z, KaleidoscopeSoundEvents.BLOCK_KILN_CRACKLE, SoundCategory.BLOCKS, 1.0F, 1.0F, false);
        }
        Direction direction = state.get(FACING);
        Direction.Axis axis = direction.getAxis();
        float offset = MathHelper.nextBetween(random, -0.2F, 0.2F);
        x += axis == Direction.Axis.X ? direction.getOffsetX() * 0.52 : offset;
        y += MathHelper.nextBetween(random, 0.4F, 0.5F);
        z += axis == Direction.Axis.Z ? direction.getOffsetZ() * 0.52 : offset;
        world.addParticle(ParticleTypes.SMOKE, x, y, z, 0.0, 0.0, 0.0);
        world.addParticle(ParticleTypes.FLAME, x, y, z, 0.0, 0.0, 0.0);
    }
}
