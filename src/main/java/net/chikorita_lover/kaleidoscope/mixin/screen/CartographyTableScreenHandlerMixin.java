package net.chikorita_lover.kaleidoscope.mixin.screen;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import com.llamalad7.mixinextras.sugar.Local;
import net.chikorita_lover.kaleidoscope.registry.KaleidoscopeSoundEvents;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.CraftingResultInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.screen.CartographyTableScreenHandler;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(CartographyTableScreenHandler.class)
public abstract class CartographyTableScreenHandlerMixin extends ScreenHandler {
    @Shadow
    @Final
    private CraftingResultInventory resultInventory;

    protected CartographyTableScreenHandlerMixin(@Nullable ScreenHandlerType<?> type, int syncId) {
        super(type, syncId);
    }

    @Inject(method = "method_17382", at = @At("HEAD"), cancellable = true)
    private void cloneLodestoneCompass(ItemStack compass, ItemStack item, ItemStack oldResult, World world, BlockPos pos, CallbackInfo ci) {
        if (!compass.contains(DataComponentTypes.LODESTONE_TRACKER) || !item.isOf(Items.COMPASS)) {
            return;
        }
        ItemStack result = compass.copyWithCount(2);
        this.resultInventory.setStack(2, result);
        this.sendContentUpdates();
        ci.cancel();
    }

    @ModifyExpressionValue(method = "quickMove", at = @At(value = "INVOKE", target = "Lnet/minecraft/item/ItemStack;contains(Lnet/minecraft/component/ComponentType;)Z"))
    private boolean canInsertMapSlot(boolean map, @Local(ordinal = 1) ItemStack stack) {
        return map || stack.contains(DataComponentTypes.LODESTONE_TRACKER);
    }

    @ModifyExpressionValue(method = "quickMove", at = @At(value = "INVOKE", target = "Lnet/minecraft/item/ItemStack;isOf(Lnet/minecraft/item/Item;)Z", ordinal = 0))
    private boolean canInsertSlot(boolean paper, @Local(ordinal = 1) ItemStack stack) {
        return paper || stack.isOf(Items.COMPASS) && !stack.contains(DataComponentTypes.LODESTONE_TRACKER);
    }

    @Mixin(targets = "net.minecraft.screen.CartographyTableScreenHandler$3")
    public static class CartographyTableScreenHandler3Mixin {
        @ModifyReturnValue(method = "canInsert", at = @At("RETURN"))
        private boolean canInsert(boolean insert, ItemStack stack) {
            return insert || stack.contains(DataComponentTypes.LODESTONE_TRACKER);
        }
    }

    @Mixin(targets = "net.minecraft.screen.CartographyTableScreenHandler$4")
    public static class CartographyTableScreenHandler4Mixin {
        @ModifyReturnValue(method = "canInsert", at = @At("RETURN"))
        private boolean canInsert(boolean insert, ItemStack stack) {
            return insert || stack.isOf(Items.COMPASS) && !stack.contains(DataComponentTypes.LODESTONE_TRACKER);
        }
    }

    @Mixin(targets = "net.minecraft.screen.CartographyTableScreenHandler$5")
    public static class CartographyTableScreenHandler5Mixin {
        @Shadow(aliases = "field_17303")
        @Final
        CartographyTableScreenHandler handler;
        @Unique
        private boolean hasCompass;

        @Inject(method = "onTakeItem", at = @At("HEAD"))
        private void setHasCompass(PlayerEntity player, ItemStack stack, CallbackInfo ci) {
            this.hasCompass = this.handler.getSlot(0).getStack().contains(DataComponentTypes.LODESTONE_TRACKER);
        }

        @ModifyExpressionValue(method = "method_17387", at = @At(value = "FIELD", target = "Lnet/minecraft/sound/SoundEvents;UI_CARTOGRAPHY_TABLE_TAKE_RESULT:Lnet/minecraft/sound/SoundEvent;"))
        private SoundEvent getUseSound(SoundEvent sound) {
            return this.hasCompass ? SoundEvents.ITEM_LODESTONE_COMPASS_LOCK : sound;
        }
    }
}
