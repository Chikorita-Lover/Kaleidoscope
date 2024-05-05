package net.chikorita_lover.kaleidoscope.mixin.item;

import net.minecraft.client.item.TooltipType;
import net.minecraft.item.FireworkStarItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;

@Mixin(FireworkStarItem.class)
public class FireworkStarItemMixin extends Item {
    public FireworkStarItemMixin(Settings settings) {
        super(settings);
    }

    @Inject(method = "appendTooltip", at = @At("HEAD"))
    private void appendFireworkTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type, CallbackInfo ci) {
        /*FireworkExplosionComponent component = stack.getComponents().get(DataComponentTypes.FIREWORK_EXPLOSION);
        if (component != null){
            tooltip.add(Kaleidoscope.getFireworkStarText(component).formatted(Formatting.GRAY));
            tooltip.add(ScreenTexts.EMPTY);
            IntList is = component.colors();
            if (is.size() > 2) {
                tooltip.add(Text.translatable("item.minecraft.firework_star.colors").formatted(Formatting.GRAY));
                tooltip.add(appendColors(ScreenTexts.space().formatted(Formatting.BLUE), is.toIntArray()));
            }
            int[] js = nbt.getIntArray("FadeColors");
            if (js.length > 0) {
                tooltip.add(Text.translatable("item.minecraft.firework_star.fade_to").append(":").formatted(Formatting.GRAY));
                tooltip.add(appendColors(ScreenTexts.space().formatted(Formatting.BLUE), js));
            }
            boolean bl;
            boolean bl2 = nbt.getBoolean("Flicker");
            if ((bl = nbt.getBoolean("Trail")) || bl2) {
                tooltip.add(Text.translatable("item.minecraft.firework_star.effects").formatted(Formatting.GRAY));
                if (bl) {
                    tooltip.add(ScreenTexts.space().append(Text.translatable("item.minecraft.firework_star.trail")).formatted(Formatting.BLUE));
                }
                if (bl2) {
                    tooltip.add(ScreenTexts.space().append(Text.translatable("item.minecraft.firework_star.flicker")).formatted(Formatting.BLUE));
                }
            }
            if (tooltip.get(tooltip.size() - 1).equals(ScreenTexts.EMPTY)) {
                tooltip.remove(tooltip.size() - 1);
            }
        }
        ci.cancel();*/
    }
}
