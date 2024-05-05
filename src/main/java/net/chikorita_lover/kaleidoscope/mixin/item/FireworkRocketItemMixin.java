package net.chikorita_lover.kaleidoscope.mixin.item;

import net.chikorita_lover.kaleidoscope.Kaleidoscope;
import net.minecraft.client.item.TooltipType;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.FireworkExplosionComponent;
import net.minecraft.component.type.FireworksComponent;
import net.minecraft.item.FireworkRocketItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.ScreenTexts;
import net.minecraft.text.MutableText;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;

@Mixin(FireworkRocketItem.class)
public class FireworkRocketItemMixin {
    @Inject(method = "appendTooltip", at = @At("HEAD"), cancellable = true)
    public void appendTooltip(ItemStack stack, Item.TooltipContext context, List<Text> tooltip, TooltipType type, CallbackInfo ci) {
        FireworksComponent component = stack.get(DataComponentTypes.FIREWORKS);
        if (component == null) {
            ci.cancel();
            return;
        }
        List<FireworkExplosionComponent> explosions = component.explosions();
        tooltip.add(Text.translatable("item.minecraft.firework_rocket.flight").append(ScreenTexts.SPACE).append(String.valueOf(component.flightDuration())));
        for (FireworkExplosionComponent explosion : explosions) {
            tooltip.add(Kaleidoscope.getFireworkStarText(explosion).formatted(Formatting.GRAY));
            boolean hasTrail = explosion.hasTrail();
            boolean hasTwinkle = explosion.hasTwinkle();
            if (hasTrail || hasTwinkle) {
                MutableText text = Text.empty();
                if (hasTrail) {
                    text.append(Text.translatable("item.minecraft.firework_star.trail"));
                }
                if (hasTwinkle) {
                    if (hasTrail) {
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
        ci.cancel();
    }
}
