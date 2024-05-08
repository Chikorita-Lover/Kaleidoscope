package net.chikorita_lover.kaleidoscope.mixin.item;

import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.NbtComponent;
import net.minecraft.entity.decoration.painting.PaintingEntity;
import net.minecraft.entity.decoration.painting.PaintingVariant;
import net.minecraft.item.DecorationItem;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.nbt.NbtOps;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.util.ActionResult;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Optional;

@Mixin(DecorationItem.class)
public class DecorationItemMixin {
    @Inject(method = "useOnBlock", at = @At(value = "INVOKE", target = "Ljava/util/Optional;isEmpty()Z", shift = At.Shift.BEFORE, ordinal = 0), cancellable = true)
    public void canPlacePainting(ItemUsageContext context, CallbackInfoReturnable<ActionResult> cir, @Local(ordinal = 0) Optional<PaintingEntity> optionalEntity) {
        NbtComponent nbtComponent = context.getStack().getOrDefault(DataComponentTypes.ENTITY_DATA, NbtComponent.DEFAULT);
        if (optionalEntity.isEmpty()) {
            cir.setReturnValue(ActionResult.PASS);
            return;
        }
        Optional<RegistryEntry<PaintingVariant>> variant = PaintingEntity.VARIANT_ENTRY_CODEC.parse(NbtOps.INSTANCE, nbtComponent.copyNbt()).result();
        if (variant.isPresent()) {
            PaintingEntity painting = optionalEntity.get();
            painting.setVariant(variant.get());
            if (painting.canStayAttached()) {
                return;
            }
            cir.setReturnValue(ActionResult.PASS);
        }
    }

    @Inject(method = "useOnBlock", at = @At(value = "INVOKE", target = "Lnet/minecraft/item/ItemStack;decrement(I)V", shift = At.Shift.BEFORE), cancellable = true)
    public void succeedOnClient(ItemUsageContext context, CallbackInfoReturnable<ActionResult> callbackInfo) {
        if (context.getWorld().isClient()) {
            callbackInfo.setReturnValue(ActionResult.success(true));
        }
    }
}
