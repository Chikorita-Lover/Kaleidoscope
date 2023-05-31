package com.chikoritalover.kaleidoscope.mixin;

import com.chikoritalover.kaleidoscope.registry.KaleidoscopeSoundEvents;
import net.minecraft.block.SpongeBlock;
import net.minecraft.sound.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(SpongeBlock.class)
public class SpongeBlockMixin {
    @Inject(method = "update", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/World;syncWorldEvent(ILnet/minecraft/util/math/BlockPos;I)V", shift = At.Shift.BEFORE), cancellable = true)
    public void update(World world, BlockPos pos, CallbackInfo callbackInfo) {
        world.playSound(null, pos, KaleidoscopeSoundEvents.BLOCK_SPONGE_ABSORB, SoundCategory.BLOCKS, 0.3F, MathHelper.lerp(world.getRandom().nextFloat(), 0.9F, 1.1F));
        callbackInfo.cancel();
    }
}
