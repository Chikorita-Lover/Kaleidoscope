package net.chikorita_lover.kaleidoscope.mixin.block;

import com.chocohead.mm.api.ClassTinkerers;
import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import com.mojang.serialization.MapCodec;
import it.unimi.dsi.fastutil.objects.Reference2ObjectArrayMap;
import net.minecraft.block.*;
import net.minecraft.block.enums.NoteBlockInstrument;
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
    private static final NoteBlockInstrument SAXOPHONE = ClassTinkerers.getEnum(NoteBlockInstrument.class, "KALEIDOSCOPE_SAXOPHONE");

    protected AbstractBlockStateMixin(Block owner, Reference2ObjectArrayMap<Property<?>, Comparable<?>> propertyMap, MapCodec<BlockState> codec) {
        super(owner, propertyMap, codec);
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

    @Inject(method = "getInstrument", at = @At("RETURN"), cancellable = true)
    private void getInstrument(CallbackInfoReturnable<NoteBlockInstrument> ci) {
        if (this.isOf(Blocks.SOUL_SOIL)) {
            ci.setReturnValue(NoteBlockInstrument.COW_BELL);
        } else if (this.isOf(Blocks.CARVED_PUMPKIN) || this.isOf(Blocks.JACK_O_LANTERN)) {
            ci.setReturnValue(NoteBlockInstrument.DIDGERIDOO);
        } else if (this.getBlock() instanceof Oxidizable || this.isOf(Blocks.LIGHTNING_ROD)) {
            ci.setReturnValue(SAXOPHONE);
        }
    }
}
