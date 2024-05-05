package net.chikorita_lover.kaleidoscope.mixin.block;

import com.chocohead.mm.api.ClassTinkerers;
import com.google.common.collect.ImmutableList;
import com.mojang.serialization.MapCodec;
import it.unimi.dsi.fastutil.objects.Reference2ObjectArrayMap;
import net.minecraft.block.*;
import net.minecraft.block.enums.Instrument;
import net.minecraft.sound.BlockSoundGroup;
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

    protected AbstractBlockStateMixin(Block owner, Reference2ObjectArrayMap<Property<?>, Comparable<?>> propertyMap, MapCodec<BlockState> codec) {
        super(owner, propertyMap, codec);
    }

    @Shadow
    public abstract Block getBlock();

    @Shadow
    public abstract boolean isOf(Block block);

    @Shadow
    public abstract BlockSoundGroup getSoundGroup();

    @Inject(method = "getInstrument", at = @At("RETURN"), cancellable = true)
    private void getInstrument(CallbackInfoReturnable<Instrument> ci) {
        Block block = this.getBlock();
        if (this.isOf(Blocks.SOUL_SOIL)) {
            ci.setReturnValue(Instrument.COW_BELL);
        }
        if (ImmutableList.of(Blocks.CARVED_PUMPKIN, Blocks.JACK_O_LANTERN).contains(block)) {
            ci.setReturnValue(Instrument.DIDGERIDOO);
        }
        if (this.getSoundGroup() == BlockSoundGroup.COPPER && this.getBlock() instanceof Oxidizable) {
            ci.setReturnValue(SAXOPHONE);
        }
    }
}
