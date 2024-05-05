package net.chikorita_lover.kaleidoscope.item;

import net.chikorita_lover.kaleidoscope.Kaleidoscope;
import net.minecraft.client.item.TooltipType;
import net.minecraft.component.type.FireworkExplosionComponent;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;

import java.util.List;

public class FireworkShellItem extends Item {
    private final FireworkExplosionComponent.Type shape;

    public FireworkShellItem(FireworkExplosionComponent.Type shape, Settings settings) {
        super(settings);
        this.shape = shape;
    }

    public FireworkExplosionComponent.Type getShape() {
        return this.shape;
    }

    @Override
    public void appendTooltip(ItemStack stack, Item.TooltipContext context, List<Text> tooltip, TooltipType type) {
        tooltip.add(this.getShape().getName().formatted(Formatting.GRAY));
    }

    @Override
    public String getTranslationKey() {
        return "item." + Kaleidoscope.MODID + ".firework_shell";
    }
}
