package com.chikoritalover.kaleidoscope.mixin;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.CropBlock;
import net.minecraft.block.PlantBlock;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(CropBlock.class)
public abstract class CropBlockMixin extends PlantBlock {
    protected CropBlockMixin(Settings settings) {
        super(settings);
    }

    @Shadow protected abstract int getAge(BlockState state);

    @Shadow public abstract int getMaxAge();

    @Shadow public abstract BlockState withAge(int age);

    @Inject(method = "randomTick", at = @At("HEAD"), cancellable = true)
    public void randomTick(BlockState state, ServerWorld world, BlockPos pos, Random random, CallbackInfo callbackInfo) {
        if (world.hasRain(pos.up())) {
            int i;
            if (world.getBaseLightLevel(pos, 0) >= 9 && (i = this.getAge(state)) < this.getMaxAge() && random.nextInt((int) (10.0F / CropBlock.getAvailableMoisture(this, world, pos)) + 1) == 0) {
                world.setBlockState(pos, this.withAge(i + 1), Block.NOTIFY_LISTENERS);
                callbackInfo.cancel();
            }
        }
    }
}
