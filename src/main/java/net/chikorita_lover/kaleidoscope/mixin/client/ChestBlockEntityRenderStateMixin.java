package net.chikorita_lover.kaleidoscope.mixin.client;

import net.chikorita_lover.kaleidoscope.client.render.DyeableRenderState;
import net.minecraft.client.render.block.entity.state.ChestBlockEntityRenderState;
import net.minecraft.util.DyeColor;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;

@Mixin(ChestBlockEntityRenderState.class)
public class ChestBlockEntityRenderStateMixin implements DyeableRenderState {
    @Unique
    private DyeColor color;

    @Override
    public DyeColor kaleidoscope$getColor() {
        return this.color;
    }

    @Override
    public void kaleidoscope$setColor(DyeColor color) {
        this.color = color;
    }
}
