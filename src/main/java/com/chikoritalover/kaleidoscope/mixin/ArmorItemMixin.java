package com.chikoritalover.kaleidoscope.mixin;

import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.stat.Stats;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ArmorItem.class)
public class ArmorItemMixin {
    @Inject(at = @At("HEAD"), method = "use", cancellable = true)
    public void use(World world, PlayerEntity user, Hand hand, CallbackInfoReturnable<TypedActionResult<ItemStack>> info) {
        ItemStack itemStack = user.getStackInHand(hand);
        EquipmentSlot equipmentSlot = MobEntity.getPreferredEquipmentSlot(itemStack);
        Item item = Item.class.cast(this);
        ItemStack itemStack2 = user.getEquippedStack(equipmentSlot);
        if (!itemStack.isItemEqual(itemStack2)) {
            user.equipStack(equipmentSlot, itemStack.copy());
            user.setStackInHand(hand, itemStack2);
            if (!world.isClient()) {
                user.incrementStat(Stats.USED.getOrCreateStat(item));
            }

            info.setReturnValue(TypedActionResult.success(itemStack, world.isClient()));
        }
    }
}
