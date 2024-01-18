package com.chikoritalover.kaleidoscope.mixin;

import com.chikoritalover.kaleidoscope.block.entity.KaleidoscopeBrewingStandBlockEntity;
import net.minecraft.advancement.criterion.BrewedPotionCriterion;
import net.minecraft.block.entity.BrewingStandBlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.screen.BrewingStandScreenHandler;
import net.minecraft.screen.slot.Slot;
import net.minecraft.server.network.ServerPlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(BrewingStandScreenHandler.class)
public class BrewingStandScreenHandlerMixin {
    @Mixin(targets = "net.minecraft.screen.BrewingStandScreenHandler$PotionSlot")
    static class PotionSlotMixin extends Slot {
        public PotionSlotMixin(Inventory inventory, int index, int x, int y) {
            super(inventory, index, x, y);
        }

        @Redirect(method = "onTakeItem", at = @At(value = "INVOKE", target = "Lnet/minecraft/advancement/criterion/BrewedPotionCriterion;trigger(Lnet/minecraft/server/network/ServerPlayerEntity;Lnet/minecraft/potion/Potion;)V"))
        public void onTakeItem(BrewedPotionCriterion criterion, ServerPlayerEntity player, Potion potion) {
            if (this.inventory instanceof BrewingStandBlockEntity) {
                if (!((KaleidoscopeBrewingStandBlockEntity) this.inventory).kaleidoscope$getRecipesUsed().isEmpty()) {
                    criterion.trigger(player, potion);
                }
                ((KaleidoscopeBrewingStandBlockEntity) this.inventory).kaleidoscope$dropExperienceForRecipesUsed(player);
            }
        }
    }
}
