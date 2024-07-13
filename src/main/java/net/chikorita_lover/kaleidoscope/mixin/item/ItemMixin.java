package net.chikorita_lover.kaleidoscope.mixin.item;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import net.chikorita_lover.kaleidoscope.item.MaxItemCountRegistry;
import net.minecraft.component.ComponentMap;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.item.BannerPatternItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;

import java.util.Optional;

@Mixin(Item.class)
public abstract class ItemMixin {
    @Shadow
    public abstract Item asItem();

    @ModifyReturnValue(method = "getMaxCount", at = @At("RETURN"))
    private int getMaxCount(int maxCount) {
        Item item = this.asItem();
        ComponentMap components = item.getComponents();
        Optional<Integer> optionalCount = MaxItemCountRegistry.getMaxItemCount(item);
        if (optionalCount.isPresent()) {
            return optionalCount.get();
        }
        if (item instanceof BannerPatternItem) {
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
