package net.chikorita_lover.kaleidoscope.mixin.item;

import net.minecraft.item.FoodComponent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(FoodComponent.class)
public interface FoodComponentAccessor {
    @Accessor("snack")
    @Mutable
    void setSnack(boolean snack);
}
