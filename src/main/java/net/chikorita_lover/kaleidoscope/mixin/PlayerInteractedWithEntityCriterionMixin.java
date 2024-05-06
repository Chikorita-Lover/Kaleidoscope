package net.chikorita_lover.kaleidoscope.mixin;

import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.advancement.criterion.AbstractCriterion;
import net.minecraft.advancement.criterion.PlayerInteractedWithEntityCriterion;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.ShearsItem;
import net.minecraft.loot.context.LootContext;
import net.minecraft.server.network.ServerPlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PlayerInteractedWithEntityCriterion.class)
public abstract class PlayerInteractedWithEntityCriterionMixin extends AbstractCriterion<PlayerInteractedWithEntityCriterion.Conditions> {
    @Inject(method = "trigger", at = @At("TAIL"))
    private void foo(ServerPlayerEntity player, ItemStack stack, Entity entity, CallbackInfo ci, @Local LootContext lootContext) {
        if (stack.getItem() instanceof ShearsItem) {
            this.trigger(player, conditions -> conditions.test(stack.copyComponentsToNewStack(Items.SHEARS, stack.getCount()), lootContext));
        }
    }
}
