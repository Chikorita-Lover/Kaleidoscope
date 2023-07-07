package com.chikoritalover.kaleidoscope.mixin;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.mojang.serialization.MapCodec;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.enums.Instrument;
import net.minecraft.state.State;
import net.minecraft.state.property.Property;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(AbstractBlock.AbstractBlockState.class)
public abstract class AbstractBlockStateMixin extends State<Block, BlockState> {
    protected AbstractBlockStateMixin(Block owner, ImmutableMap<Property<?>, Comparable<?>> entries, MapCodec<BlockState> codec) {
        super(owner, entries, codec);
    }

    @Shadow
    public abstract Block getBlock();

    @Shadow
    public abstract boolean isOf(Block block);

    @Inject(method = "getInstrument", at = @At("RETURN"), cancellable = true)
    public void getInstrument(CallbackInfoReturnable<Instrument> callbackInfo) {
        Block block = this.getBlock();
        if (this.isOf(Blocks.SOUL_SOIL)) {
            callbackInfo.setReturnValue(Instrument.COW_BELL);
        }
        if (ImmutableList.of(Blocks.CARVED_PUMPKIN, Blocks.JACK_O_LANTERN).contains(block)) {
            callbackInfo.setReturnValue(Instrument.DIDGERIDOO);
        }
    }
}
