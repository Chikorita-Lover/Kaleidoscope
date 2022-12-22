package com.chikorita_lover.chikorita_lover_mod.block;

import com.chikorita_lover.chikorita_lover_mod.ChikoritaLoverMod;
import com.chikorita_lover.chikorita_lover_mod.registry.ModBlockTags;
import com.chikorita_lover.chikorita_lover_mod.registry.ModSoundEvents;
import net.minecraft.block.*;
import net.minecraft.block.enums.DoorHinge;
import net.minecraft.block.enums.DoubleBlockHalf;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.tag.TagKey;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.Identifier;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;
import net.minecraft.world.event.GameEvent;

import java.util.Iterator;

public class CopperDoorBlock extends DoorBlock implements Oxidizable {
    private final OxidationLevel oxidationLevel;

    public CopperDoorBlock(OxidationLevel oxidationLevel, Settings settings) {
        super(settings);
        this.oxidationLevel = oxidationLevel;
    }

    public int getDelay() {
        return switch (this.oxidationLevel) {
            case UNAFFECTED -> 0;
            case EXPOSED -> 2;
            case WEATHERED -> 4;
            case OXIDIZED -> 6; //Unused
        };
    }

    public float getPitchFloat() {
        return switch (this.oxidationLevel) {
            case UNAFFECTED -> 1.0f;
            case EXPOSED -> 0.9f;
            case WEATHERED -> 0.85f;
            case OXIDIZED -> 0.8f;
        };
    }

    @Override
    public BlockState getStateForNeighborUpdate(BlockState state, Direction direction, BlockState neighborState, WorldAccess world, BlockPos pos, BlockPos neighborPos) {
        DoubleBlockHalf doubleBlockHalf = state.get(HALF);
        if (direction.getAxis() == Direction.Axis.Y && doubleBlockHalf == DoubleBlockHalf.LOWER == (direction == Direction.UP)) {
            if (neighborState.isOf(Blocks.AIR)) return Blocks.AIR.getDefaultState();
            if (state != neighborState.cycle(HALF)) world.setBlockState(pos, neighborState.cycle(HALF), 2);
            return neighborState.isIn(ModBlockTags.COPPER_DOORS) && neighborState.get(HALF) != doubleBlockHalf ? state.with(FACING, neighborState.get(FACING)).with(OPEN, neighborState.get(OPEN)).with(HINGE, neighborState.get(HINGE)).with(POWERED, neighborState.get(POWERED)) : Blocks.AIR.getDefaultState();
        } else {
            return doubleBlockHalf == DoubleBlockHalf.LOWER && direction == Direction.DOWN && !state.canPlaceAt(world, pos) ? Blocks.AIR.getDefaultState() : super.getStateForNeighborUpdate(state, direction, neighborState, world, pos, neighborPos);
        }
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        if (this.oxidationLevel == Oxidizable.OxidationLevel.OXIDIZED) {
            return ActionResult.PASS;
        } else {
            world.createAndScheduleBlockTick(pos, this, getDelay());
            this.playOpenCloseSound(world, pos, !this.isOpen(state));
            world.emitGameEvent(player, this.isOpen(state) ? GameEvent.BLOCK_OPEN : GameEvent.BLOCK_CLOSE, pos);
            return ActionResult.success(world.isClient);
        }
    }

    @Override
    public void neighborUpdate(BlockState state, World world, BlockPos pos, Block sourceBlock, BlockPos sourcePos, boolean notify) {
        boolean bl = world.isReceivingRedstonePower(pos) || world.isReceivingRedstonePower(pos.offset(state.get(HALF) == DoubleBlockHalf.LOWER ? Direction.UP : Direction.DOWN));
        if (!this.getDefaultState().isOf(sourceBlock) && bl != state.get(POWERED)) {
            if (bl != state.get(OPEN)) {
                this.playOpenCloseSound(world, pos, bl);
                world.emitGameEvent(null, bl ? GameEvent.BLOCK_OPEN : GameEvent.BLOCK_CLOSE, pos);
            }

            world.setBlockState(pos, state.with(POWERED, bl).with(OPEN, bl), 2);
        }
    }

    @Override
    public void scheduledTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        state = state.cycle(OPEN);
        world.setBlockState(pos, state, 10);
    }

    private void playOpenCloseSound(World world, BlockPos pos, boolean open) {
        world.playSound(null, pos, open ? ModSoundEvents.BLOCK_COPPER_DOOR_OPEN : ModSoundEvents.BLOCK_COPPER_DOOR_CLOSE, SoundCategory.BLOCKS, 1.0f, getPitchFloat());
    }

    @Override
    public void randomTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        this.tickDegradation(state, world, pos, random);
    }

    @Override
    public boolean hasRandomTicks(BlockState state) {
        return Oxidizable.getIncreasedOxidationBlock(state.getBlock()).isPresent();
    }

    @Override
    public OxidationLevel getDegradationLevel() {
        return this.oxidationLevel;
    }
}
