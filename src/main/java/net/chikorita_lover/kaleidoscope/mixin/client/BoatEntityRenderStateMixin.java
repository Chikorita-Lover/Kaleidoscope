package net.chikorita_lover.kaleidoscope.mixin.client;

import net.chikorita_lover.kaleidoscope.client.render.BannerEquippableRenderState;
import net.minecraft.client.render.entity.state.BoatEntityRenderState;
import net.minecraft.client.render.item.ItemRenderState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;

@Mixin(BoatEntityRenderState.class)
public class BoatEntityRenderStateMixin implements BannerEquippableRenderState {
    @Unique
    private final ItemRenderState bannerRenderState = new ItemRenderState();

    @Override
    public ItemRenderState kaleidoscope$getState() {
        return this.bannerRenderState;
    }
}
