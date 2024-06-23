package net.chikorita_lover.kaleidoscope.mixin.item;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import net.chikorita_lover.kaleidoscope.item.MaxItemCountRegistry;
import net.minecraft.component.ComponentMap;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(Item.class)
public abstract class ItemMixin {
    @Shadow
    public abstract Item asItem();

    @ModifyReturnValue(method = "getMaxCount", at = @At("RETURN"))
    private int getMaxCount(int maxCount) {
        Item item = this.asItem();
        ComponentMap components = item.getComponents();
        if (MaxItemCountRegistry.MAX_COUNTS.containsKey(item)) {
            return MaxItemCountRegistry.MAX_COUNTS.get(item);
        }
        if (components.contains(DataComponentTypes.JUKEBOX_PLAYABLE)) {
            return 64;
        }
        if (components.contains(DataComponentTypes.FOOD) && components.get(DataComponentTypes.FOOD).usingConvertsTo().orElse(ItemStack.EMPTY).isOf(Items.BOWL)) {
            return 16;
        }
        return maxCount;
    }

    @ModifyExpressionValue(method = "getMaxUseTime", at = @At(value = "INVOKE", target = "Lnet/minecraft/component/type/FoodComponent;getEatTicks()I"))
    private int getEatTicks(int eatTicks) {
        return this.asItem().equals(Items.COOKIE) ? 16 : eatTicks;
    }
}
