package net.chikorita_lover.kaleidoscope.mixin.client;

import net.chikorita_lover.kaleidoscope.registry.tag.KaleidoscopeBiomeTags;
import net.chikorita_lover.kaleidoscope.registry.tag.KaleidoscopeBlockTags;
import net.chikorita_lover.kaleidoscope.registry.KaleidoscopeParticleTypes;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.client.render.WorldRenderer;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.registry.DynamicRegistryManager;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.util.profiler.Profiler;
import net.minecraft.world.MutableWorldProperties;
import net.minecraft.world.World;
import net.minecraft.world.dimension.DimensionType;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.function.Supplier;

@Mixin(ClientWorld.class)
public abstract class ClientWorldMixin extends World {
    @Shadow
    @Final
    private WorldRenderer worldRenderer;

    protected ClientWorldMixin(MutableWorldProperties properties, RegistryKey<World> registryRef, DynamicRegistryManager registryManager, RegistryEntry<DimensionType> dimensionEntry, Supplier<Profiler> profiler, boolean isClient, boolean debugWorld, long biomeAccess, int maxChainedNeighborUpdates) {
        super(properties, registryRef, registryManager, dimensionEntry, profiler, isClient, debugWorld, biomeAccess, maxChainedNeighborUpdates);
    }

    @Unique
    private boolean isFireflyNight() {
        return this.getTimeOfDay() % 24000 >= 12000 && this.getTimeOfDay() % 24000 < 22000 && (this.getMoonPhase() == 4 || this.getMoonPhase() == 5);
    }

    @Inject(method = "randomBlockDisplayTick", at = @At("TAIL"))
    private void createFireflyParticles(int centerX, int centerY, int centerZ, int radius, Random random, Block block, BlockPos.Mutable pos, CallbackInfo ci) {
        BlockState blockState = this.getBlockState(pos);
        if (pos.getY() < 64 || !this.isFireflyNight()) {
            return;
        }
        if (this.getBiome(pos).isIn(KaleidoscopeBiomeTags.SPAWNS_FIREFLIES) && this.getBlockState(pos.down()).isIn(KaleidoscopeBlockTags.FIREFLIES_SPAWNABLE_ON) && !blockState.isSolid() && random.nextFloat() < 0.015F) {
            this.addParticle(KaleidoscopeParticleTypes.FIREFLY, pos.getX() + random.nextDouble(), pos.getY() + random.nextDouble() * 3.0, pos.getZ() + random.nextDouble(), 0.0, 0.0, 0.0);
        }
    }
}
