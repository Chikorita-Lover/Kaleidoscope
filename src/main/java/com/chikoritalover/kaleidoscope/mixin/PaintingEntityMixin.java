package com.chikoritalover.kaleidoscope.mixin;

import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.decoration.AbstractDecorationEntity;
import net.minecraft.entity.decoration.painting.PaintingEntity;
import net.minecraft.entity.decoration.painting.PaintingVariant;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.ShearsItem;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PaintingEntity.class)
public abstract class PaintingEntityMixin extends AbstractDecorationEntity {
    protected PaintingEntityMixin(EntityType<? extends AbstractDecorationEntity> entityType, World world) {
        super(entityType, world);
    }

    @Shadow
    public abstract RegistryEntry<PaintingVariant> getVariant();

    @Inject(method = "onBreak", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/decoration/painting/PaintingEntity;dropItem(Lnet/minecraft/item/ItemConvertible;)Lnet/minecraft/entity/ItemEntity;", shift = At.Shift.BEFORE), cancellable = true)
    public void onBreak(Entity entity, CallbackInfo callbackInfo) {
        if (!(entity instanceof PlayerEntity player)) {
            return;
        }
        ItemStack itemStack = player.getMainHandStack();
        if (itemStack.getItem() instanceof ShearsItem || EnchantmentHelper.hasSilkTouch(itemStack)) {
            ItemStack stack = new ItemStack(Items.PAINTING);
            NbtCompound nbtCompound = stack.getOrCreateSubNbt("EntityTag");
            PaintingEntity.writeVariantToNbt(nbtCompound, this.getVariant());
            this.dropStack(stack);
            callbackInfo.cancel();
        }
    }
}
