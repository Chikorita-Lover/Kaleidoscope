package net.chikorita_lover.kaleidoscope.mixin;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.llamalad7.mixinextras.sugar.Local;
import net.fabricmc.fabric.api.tag.convention.v2.ConventionalItemTags;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.loot.condition.MatchToolLootCondition;
import net.minecraft.predicate.item.ItemPredicate;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;

import java.util.Optional;

@Mixin(MatchToolLootCondition.class)
public abstract class MatchToolLootConditionMixin {
    @Shadow
    public abstract Optional<ItemPredicate> predicate();

    @ModifyExpressionValue(method = "test(Lnet/minecraft/loot/context/LootContext;)Z", at = @At(value = "INVOKE", target = "Lnet/minecraft/predicate/item/ItemPredicate;test(Lnet/minecraft/item/ItemStack;)Z"))
    private boolean testShears(boolean bl, @Local ItemStack stack) {
        return bl || (stack.isIn(ConventionalItemTags.SHEAR_TOOLS) && this.predicate().get().test(stack.copyComponentsToNewStack(Items.SHEARS, stack.getCount())));
    }
}
