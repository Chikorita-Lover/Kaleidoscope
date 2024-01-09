package com.chikoritalover.kaleidoscope.mixin;

import com.chikoritalover.kaleidoscope.Kaleidoscope;
import net.minecraft.item.FireworkRocketItem;
import net.minecraft.item.FireworkStarItem;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.screen.ScreenTexts;
import net.minecraft.text.MutableText;
import net.minecraft.text.Text;
import net.minecraft.util.DyeColor;
import net.minecraft.util.Formatting;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.List;

@Mixin(FireworkStarItem.class)
public class FireworkStarItemMixin {
    @Shadow
    private static Text appendColors(MutableText line, int[] colors) {
        return null;
    }

    @Inject(method = "appendFireworkTooltip", at = @At("HEAD"), cancellable = true)
    private static void appendFireworkTooltip(NbtCompound nbt, List<Text> tooltip, CallbackInfo ci) {
        tooltip.add(Kaleidoscope.getFireworkStarText(nbt).formatted(Formatting.GRAY));
        tooltip.add(ScreenTexts.EMPTY);
        int[] is = nbt.getIntArray("Colors");
        if (is.length > 2) {
            tooltip.add(Text.translatable("item.minecraft.firework_star.colors").formatted(Formatting.GRAY));
            tooltip.add(appendColors(ScreenTexts.space().formatted(Formatting.BLUE), is));
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
        ci.cancel();
    }
}
