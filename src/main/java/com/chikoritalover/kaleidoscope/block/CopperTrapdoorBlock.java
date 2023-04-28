package com.chikoritalover.kaleidoscope.block;

import com.chikoritalover.kaleidoscope.registry.KaleidoscopeBlockSetType;
import com.chikoritalover.kaleidoscope.registry.KaleidoscopeSoundEvents;
import net.minecraft.block.BlockState;
import net.minecraft.block.Oxidizable;
import net.minecraft.block.TrapdoorBlock;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.Fluids;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;
import net.minecraft.world.event.GameEvent;
import org.jetbrains.annotations.Nullable;

public class CopperTrapdoorBlock extends TrapdoorBlock implements Oxidizable {
    private final OxidationLevel oxidationLevel;

    public CopperTrapdoorBlock(OxidationLevel oxidationLevel, Settings settings) {
        super(settings, KaleidoscopeBlockSetType.COPPER);
        this.oxidationLevel = oxidationLevel;
    }

    public int getDelay() {
        return switch (this.oxidationLevel) {
            case UNAFFECTED -> 0;
            case EXPOSED -> 2;
            case WEATHERED -> 3;
            case OXIDIZED -> 4;
        };
    }

    public float getPitchFloat() {
        return switch (this.oxidationLevel) {
            case UNAFFECTED -> 0.9F;
            case EXPOSED -> 0.8F;
            case WEATHERED -> 0.75F;
            case OXIDIZED -> 0.7F;
        };
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        world.scheduleBlockTick(pos, this, getDelay());
        this.playToggleSound(player, world, pos, !state.get(OPEN));
        return ActionResult.success(world.isClient);
    }

    @Override
    protected void playToggleSound(@Nullable PlayerEntity player, World world, BlockPos pos, boolean open) {
        world.playSound(player, pos, open ? KaleidoscopeSoundEvents.BLOCK_COPPER_TRAPDOOR_OPEN : KaleidoscopeSoundEvents.BLOCK_COPPER_TRAPDOOR_CLOSE, SoundCategory.BLOCKS, 1.0F, world.getRandom().nextFloat() * 0.1F + this.getPitchFloat());
        world.emitGameEvent(player, open ? GameEvent.BLOCK_OPEN : GameEvent.BLOCK_CLOSE, pos);
    }

    @Override
    public void scheduledTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        state = state.cycle(OPEN);
        world.setBlockState(pos, state, 2);
        if (state.get(WATERLOGGED)) {
            world.scheduleFluidTick(pos, Fluids.WATER, Fluids.WATER.getTickRate(world));
        }
    }

    public void randomTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        this.tickDegradation(state, world, pos, random);
    }

    public boolean hasRandomTicks(BlockState state) {
        return Oxidizable.getIncreasedOxidationBlock(state.getBlock()).isPresent();
    }

    public OxidationLevel getDegradationLevel() {
        return this.oxidationLevel;
    }
}
