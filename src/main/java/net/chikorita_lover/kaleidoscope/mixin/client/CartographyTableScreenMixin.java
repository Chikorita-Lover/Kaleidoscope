package net.chikorita_lover.kaleidoscope.mixin.client;

import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.client.gl.RenderPipelines;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.ingame.CartographyTableScreen;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.screen.CartographyTableScreenHandler;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(CartographyTableScreen.class)
public abstract class CartographyTableScreenMixin extends HandledScreen<CartographyTableScreenHandler> {
    @Shadow
    @Final
    private static Identifier ERROR_TEXTURE;

    public CartographyTableScreenMixin(CartographyTableScreenHandler handler, PlayerInventory inventory, Text title) {
        super(handler, inventory, title);
    }

    @Inject(method = "drawBackground", at = @At("TAIL"))
    private void drawCompassTextures(DrawContext context, float deltaTicks, int mouseX, int mouseY, CallbackInfo ci, @Local(ordinal = 0) ItemStack stack, @Local(ordinal = 1) ItemStack stack2) {
        int i = this.x;
        int j = this.y;
        if (stack.isEmpty() || stack2.isEmpty()) {
            return;
        }
        boolean lodestone = stack2.contains(DataComponentTypes.LODESTONE_TRACKER);
        boolean compass = stack.isOf(Items.COMPASS);
        if (lodestone != compass) {
            context.drawGuiTexture(RenderPipelines.GUI_TEXTURED, ERROR_TEXTURE, i + 35, j + 31, 28, 21);
        }
    }
}
