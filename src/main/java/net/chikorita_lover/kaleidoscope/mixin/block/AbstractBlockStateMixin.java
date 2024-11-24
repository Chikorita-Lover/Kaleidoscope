package net.chikorita_lover.kaleidoscope.mixin.block;

import com.chocohead.mm.api.ClassTinkerers;
import com.google.common.collect.ImmutableMap;
import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import com.mojang.serialization.MapCodec;
import net.chikorita_lover.kaleidoscope.registry.tag.KaleidoscopeBlockTags;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.enums.Instrument;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.state.State;
import net.minecraft.state.property.Property;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(AbstractBlock.AbstractBlockState.class)
public abstract class AbstractBlockStateMixin extends State<Block, BlockState> {
    @Unique
    private static final Instrument SAXOPHONE = ClassTinkerers.getEnum(Instrument.class, "KALEIDOSCOPE_SAXOPHONE");

    protected AbstractBlockStateMixin(Block owner, ImmutableMap<Property<?>, Comparable<?>> entries, MapCodec<BlockState> codec) {
        super(owner, entries, codec);
    }

    @Shadow
    public abstract Block getBlock();

    @Shadow
    public abstract boolean isOf(Block block);

    @Shadow
    public abstract boolean isIn(TagKey<Block> tag);

    @ModifyReturnValue(method = "getHardness", at = @At("RETURN"))
    private float getHardness(float hardness) {
        return this.isOf(Blocks.COBWEB) ? 1.6F : hardness;
    }

    @Inject(method = "getInstrument", at = @At("HEAD"), cancellable = true)
    private void getInstrument(CallbackInfoReturnable<Instrument> cir) {
        if (this.isOf(Blocks.SOUL_SOIL)) {
            cir.setReturnValue(Instrument.COW_BELL);
        } else if (this.isOf(Blocks.CARVED_PUMPKIN) || this.isOf(Blocks.JACK_O_LANTERN)) {
            cir.setReturnValue(Instrument.DIDGERIDOO);
        } else if (this.isIn(KaleidoscopeBlockTags.COPPER)) {
            cir.setReturnValue(SAXOPHONE);
        }
    }
}
