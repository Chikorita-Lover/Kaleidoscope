package net.chikorita_lover.kaleidoscope.mixin.client;

import net.chikorita_lover.kaleidoscope.client.render.ChestableRenderState;
import net.minecraft.client.render.entity.state.StriderEntityRenderState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;

@Mixin(StriderEntityRenderState.class)
public class StriderEntityRenderStateMixin implements ChestableRenderState {
    @Unique
    private boolean hasChest;

    @Override
    public boolean kaleidoscope$hasChest() {
        return this.hasChest;
    }

    @Override
    public void kaleidoscope$setHasChest(boolean chest) {
        this.hasChest = chest;
    }
}
