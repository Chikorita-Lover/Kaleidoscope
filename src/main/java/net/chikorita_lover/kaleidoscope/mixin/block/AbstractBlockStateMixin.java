package net.chikorita_lover.kaleidoscope.mixin.block;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import com.mojang.serialization.MapCodec;
import it.unimi.dsi.fastutil.objects.Reference2ObjectArrayMap;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.enums.NoteBlockInstrument;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.state.State;
import net.minecraft.state.property.Property;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(AbstractBlock.AbstractBlockState.class)
public abstract class AbstractBlockStateMixin extends State<Block, BlockState> {
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

    @ModifyReturnValue(method = "getInstrument", at = @At("RETURN"))
    private NoteBlockInstrument modifyInstrument(NoteBlockInstrument instrument) {
        if (this.isOf(Blocks.SOUL_SOIL)) {
            return NoteBlockInstrument.COW_BELL;
        } else if (this.isOf(Blocks.CARVED_PUMPKIN) || this.isOf(Blocks.JACK_O_LANTERN)) {
            return NoteBlockInstrument.DIDGERIDOO;
        }
        return instrument;
    }
}
