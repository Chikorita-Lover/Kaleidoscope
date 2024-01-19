package com.chikoritalover.kaleidoscope.mixin;

import com.chikoritalover.kaleidoscope.block.entity.KaleidoscopeBrewingStandBlockEntity;
import net.minecraft.advancement.criterion.BrewedPotionCriterion;
import net.minecraft.block.entity.BrewingStandBlockEntity;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.screen.BrewingStandScreenHandler;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.screen.slot.Slot;
import net.minecraft.server.network.ServerPlayerEntity;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(BrewingStandScreenHandler.class)
public abstract class BrewingStandScreenHandlerMixin extends ScreenHandler {
    protected BrewingStandScreenHandlerMixin(@Nullable ScreenHandlerType<?> type, int syncId) {
        super(type, syncId);
    }

    @Redirect(method = "quickMove", at = @At(value = "INVOKE", target = "Lnet/minecraft/screen/BrewingStandScreenHandler;insertItem(Lnet/minecraft/item/ItemStack;IIZ)Z", ordinal = 4))
    public boolean quickMove(BrewingStandScreenHandler screenHandler, ItemStack stack, int startIndex, int endIndex, boolean fromLast) {
        // Ensure we are not quick moving a potion to an already-occupied potion slot. Otherwise, we can stack multiple potions in one slot due to increased potion stack size.
        for (int i = startIndex; i < endIndex; i++) {
            Slot slot = screenHandler.getSlot(i);
            if (slot.hasStack()) continue;
            this.insertItem(stack, i, i + 1, false);
            return true;
        }
        return false;
    }

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
