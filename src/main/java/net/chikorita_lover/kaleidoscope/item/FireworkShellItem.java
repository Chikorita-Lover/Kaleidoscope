package net.chikorita_lover.kaleidoscope.item;

import net.minecraft.component.type.FireworkExplosionComponent;
import net.minecraft.item.Item;

public class FireworkShellItem extends Item {
    private final FireworkExplosionComponent.Type shape;

    public FireworkShellItem(FireworkExplosionComponent.Type shape, Settings settings) {
        super(settings);
        this.shape = shape;
    }

    public FireworkExplosionComponent.Type getShape() {
        return this.shape;
    }
}
