package net.chikorita_lover.kaleidoscope.mixin;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.GrindstoneScreenHandler;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.ScreenHandlerType;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(GrindstoneScreenHandler.class)
public abstract class GrindstoneScreenHandlerMixin extends ScreenHandler {
    @Shadow
    @Final
    Inventory input;

    @Shadow
    @Final
    private Inventory result;

    protected GrindstoneScreenHandlerMixin(@Nullable ScreenHandlerType<?> type, int syncId) {
        super(type, syncId);
    }

    @Shadow
    protected abstract ItemStack grind(ItemStack item);

    @Inject(method = "updateResult", at = @At("TAIL"), cancellable = true)
    private void updateResult(CallbackInfo ci) {
        ItemStack itemStack = this.input.getStack(0);
        ItemStack itemStack2 = this.input.getStack(1);
        if (itemStack.contains(DataComponentTypes.LODESTONE_TRACKER) || itemStack2.contains(DataComponentTypes.LODESTONE_TRACKER)) {
            boolean hasFirstStack = !itemStack.isEmpty() && itemStack2.isEmpty();
            if (!itemStack.isEmpty() && !itemStack2.isEmpty()) {
                this.result.setStack(0, ItemStack.EMPTY);
            } else {
                ItemStack newStack = hasFirstStack ? itemStack.copy() : itemStack2.copy();
                this.result.setStack(0, this.grind(newStack));
            }
            this.sendContentUpdates();
            ci.cancel();
        }
    }

    @ModifyReturnValue(method = "grind", at = @At("RETURN"))
    private ItemStack removeLodestoneTracker(ItemStack stack) {
        stack.remove(DataComponentTypes.LODESTONE_TRACKER);
        return stack;
    }

    @Mixin(targets = "net.minecraft.screen.GrindstoneScreenHandler$2")
    public static class GrindstoneScreenHandler2Mixin {
        @ModifyReturnValue(method = "canInsert", at = @At("RETURN"))
        private boolean canInsert(boolean canInsert, @Local(argsOnly = true) ItemStack stack) {
            return canInsert || stack.contains(DataComponentTypes.LODESTONE_TRACKER);
        }
    }

    @Mixin(targets = "net.minecraft.screen.GrindstoneScreenHandler$3")
    public static class GrindstoneScreenHandler3Mixin {
        @ModifyReturnValue(method = "canInsert", at = @At("RETURN"))
        private boolean canInsert(boolean canInsert, @Local(argsOnly = true) ItemStack stack) {
            return canInsert || stack.contains(DataComponentTypes.LODESTONE_TRACKER);
        }
    }
}
