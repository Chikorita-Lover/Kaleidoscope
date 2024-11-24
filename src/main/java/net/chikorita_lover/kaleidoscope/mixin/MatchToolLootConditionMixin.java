package net.chikorita_lover.kaleidoscope.mixin;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.llamalad7.mixinextras.sugar.Local;
import net.fabricmc.fabric.api.tag.convention.v1.ConventionalItemTags;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.loot.condition.MatchToolLootCondition;
import net.minecraft.predicate.item.ItemPredicate;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(MatchToolLootCondition.class)
public abstract class MatchToolLootConditionMixin {
    @Shadow
    @Final
    ItemPredicate predicate;

    @ModifyExpressionValue(method = "test(Lnet/minecraft/loot/context/LootContext;)Z", at = @At(value = "INVOKE", target = "Lnet/minecraft/predicate/item/ItemPredicate;test(Lnet/minecraft/item/ItemStack;)Z"))
    private boolean testShears(boolean bl, @Local ItemStack stack) {
        ItemStack shears = new ItemStack(Items.SHEARS);
        shears.setNbt(stack.getNbt());
        return bl || (stack.isIn(ConventionalItemTags.SHEARS) && this.predicate.test(shears));
    }
}
