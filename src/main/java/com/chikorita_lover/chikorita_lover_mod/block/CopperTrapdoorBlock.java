package com.chikorita_lover.chikorita_lover_mod.block;

import com.chikorita_lover.chikorita_lover_mod.registry.ModSoundEvents;
import net.minecraft.block.*;
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
        super(settings);
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
            case UNAFFECTED -> 1.0f;
            case EXPOSED -> 0.9f;
            case WEATHERED -> 0.85f;
            case OXIDIZED -> 0.8f;
        };
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        world.createAndScheduleBlockTick(pos, this, getDelay());
        this.playToggleSound(player, world, pos, !state.get(OPEN));
        return ActionResult.success(world.isClient);
    }

    @Override
    protected void playToggleSound(@Nullable PlayerEntity player, World world, BlockPos pos, boolean open) {
        world.playSound(null, pos, open ? ModSoundEvents.BLOCK_COPPER_TRAPDOOR_OPEN : ModSoundEvents.BLOCK_COPPER_TRAPDOOR_CLOSE, SoundCategory.BLOCKS, 1, getPitchFloat());
        world.emitGameEvent(player, open ? GameEvent.BLOCK_OPEN : GameEvent.BLOCK_CLOSE, pos);
    }

    @Override
    public void scheduledTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        state = state.cycle(OPEN);
        world.setBlockState(pos, state, 2);
        if (state.get(WATERLOGGED)) {
            world.createAndScheduleFluidTick(pos, Fluids.WATER, Fluids.WATER.getTickRate(world));
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
