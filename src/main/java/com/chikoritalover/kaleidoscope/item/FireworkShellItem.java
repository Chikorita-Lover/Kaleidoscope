package com.chikoritalover.kaleidoscope.item;

import com.chikoritalover.kaleidoscope.Kaleidoscope;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.item.FireworkRocketItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class FireworkShellItem extends Item {
    private final FireworkRocketItem.Type type;

    public FireworkShellItem(FireworkRocketItem.Type type, Settings settings) {
        super(settings);
        this.type = type;
    }

    public FireworkRocketItem.Type getType() {
        return this.type;
    }

    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        tooltip.add(Text.translatable("item.minecraft.firework_star.shape." + this.getType().getName()).formatted(Formatting.GRAY));
    }

    @Override
    public String getTranslationKey() {
        return "item." + Kaleidoscope.MODID + ".firework_shell";
    }
}
