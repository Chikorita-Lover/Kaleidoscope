package net.chikorita_lover.kaleidoscope.item;

import net.minecraft.item.FireworkRocketItem;
import net.minecraft.item.Item;

public class FireworkShellItem extends Item {
    private final FireworkRocketItem.Type shape;

    public FireworkShellItem(FireworkRocketItem.Type shape, Settings settings) {
        super(settings);
        this.shape = shape;
    }

    public FireworkRocketItem.Type getShape() {
        return this.shape;
    }
}
