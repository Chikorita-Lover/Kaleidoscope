package com.chikoritalover.kaleidoscope.mixin;

import com.chikoritalover.kaleidoscope.Kaleidoscope;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.item.FireworkRocketItem;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtElement;
import net.minecraft.nbt.NbtList;
import net.minecraft.screen.ScreenTexts;
import net.minecraft.text.MutableText;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;

@Mixin(FireworkRocketItem.class)
public class FireworkRocketItemMixin {
    @Inject(method = "appendTooltip", at = @At("HEAD"), cancellable = true)
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context, CallbackInfo ci) {
        NbtList nbtList;
        NbtCompound nbtCompound = stack.getSubNbt("Fireworks");
        if (nbtCompound == null) {
            ci.cancel();
            return;
        }
        if (nbtCompound.contains("Flight", NbtElement.NUMBER_TYPE)) {
            tooltip.add(Text.translatable("item.minecraft.firework_rocket.flight").append(ScreenTexts.SPACE).append(String.valueOf(nbtCompound.getByte("Flight"))));
        }
        if (!(nbtList = nbtCompound.getList("Explosions", NbtElement.COMPOUND_TYPE)).isEmpty()) {
            for (int i = 0; i < nbtList.size(); ++i) {
                NbtCompound nbtCompound2 = nbtList.getCompound(i);
                tooltip.add(Kaleidoscope.getFireworkStarText(nbtCompound2).formatted(Formatting.GRAY));
                boolean bl;
                boolean bl2 = nbtCompound2.getBoolean("Flicker");
                if ((bl = nbtCompound2.getBoolean("Trail")) || bl2) {
                    MutableText text = Text.empty();
                    if (bl) {
                        text.append(Text.translatable("item.minecraft.firework_star.trail"));
                    }
                    if (bl2) {
                        if (bl) {
                            text.append(", ");
                        }
                        text.append(Text.translatable("item.minecraft.firework_star.flicker"));
                    }
                    tooltip.add(ScreenTexts.space().append(text).formatted(Formatting.GRAY));
                }
                /*
                if (text.equals(Text.empty())) {
                    text = mutableText2;
                    i = 1;
                } else if (!Objects.equals(text, mutableText2)) {
                    if (i > 1) {
                        text.append(" x" + i);
                    }
                    tooltip.add(text.formatted(Formatting.GRAY));
                    text = mutableText2;
                    i = 1;
                } else {
                    ++i;
                }
                if (j == nbtList.size() - 1) {
                    if (i > 1) {
                        text.append(" x" + i);
                    }
                    tooltip.add(text.formatted(Formatting.GRAY));
                }*/
            }
        }
        ci.cancel();
    }
}
