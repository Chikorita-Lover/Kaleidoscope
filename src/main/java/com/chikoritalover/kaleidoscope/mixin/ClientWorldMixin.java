package com.chikoritalover.kaleidoscope.mixin;

import com.chikoritalover.kaleidoscope.registry.ModBiomeTags;
import com.chikoritalover.kaleidoscope.registry.ModBlockTags;
import com.chikoritalover.kaleidoscope.registry.ModParticleTypes;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ClientWorld.class)
public class ClientWorldMixin {
    @Inject(method = "randomBlockDisplayTick", at = @At("HEAD"))
    public void randomBlockDisplayTick(int centerX, int centerY, int centerZ, int radius, Random random, Block block, BlockPos.Mutable pos, CallbackInfo callbackInfo) {
        ClientWorld clientWorld = ClientWorld.class.cast(this);
        BlockState blockState = clientWorld.getBlockState(pos);
        if (pos.getY() < 64 || !isFireflyNight(clientWorld)) return;
        if (clientWorld.getBiome(pos).isIn(ModBiomeTags.SPAWNS_FIREFLIES)) {
            if (clientWorld.getBlockState(pos.down()).isIn(ModBlockTags.FIREFLIES_SPAWNABLE_ON) && !blockState.getMaterial().isSolid() && random.nextFloat() < 0.015F) {
                clientWorld.addParticle(ModParticleTypes.FIREFLY, (double) pos.getX() + random.nextDouble(), (double) pos.getY() + random.nextDouble() * 3.0, (double) pos.getZ() + random.nextDouble(), 0.0, 0.0, 0.0);
            }
        }
    }

    private static boolean isFireflyNight(ClientWorld world) {
        return world.getTimeOfDay() % 24000 >= 12000 && world.getTimeOfDay() % 24000 < 22000 && (world.getMoonPhase() == 4 || world.getMoonPhase() == 5);
    }
}
