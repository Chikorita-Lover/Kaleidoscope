package com.chikoritalover.kaleidoscope.mixin;

import com.chikoritalover.kaleidoscope.registry.KaleidoscopeItems;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.passive.AbstractHorseEntity;
import net.minecraft.entity.passive.HorseEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.UUID;

@Mixin(HorseEntity.class)
public abstract class HorseEntityMixin extends AbstractHorseEntity {
    @Shadow
    @Final
    private static UUID HORSE_ARMOR_BONUS_ID;

    protected HorseEntityMixin(EntityType<? extends AbstractHorseEntity> entityType, World world) {
        super(entityType, world);
    }

    @Inject(method = "setArmorTypeFromStack", at = @At("TAIL"))
    public void setArmorTypeFromStack(ItemStack stack, CallbackInfo callbackInfo) {
        if (this.getWorld().isClient()) {
            return;
        }
        this.getAttributeInstance(EntityAttributes.GENERIC_ARMOR_TOUGHNESS).removeModifier(HORSE_ARMOR_BONUS_ID);
        this.getAttributeInstance(EntityAttributes.GENERIC_KNOCKBACK_RESISTANCE).removeModifier(HORSE_ARMOR_BONUS_ID);
        if (stack.isOf(KaleidoscopeItems.NETHERITE_HORSE_ARMOR)) {
            this.getAttributeInstance(EntityAttributes.GENERIC_ARMOR_TOUGHNESS).addTemporaryModifier(new EntityAttributeModifier(HORSE_ARMOR_BONUS_ID, "Armor toughness", 3, EntityAttributeModifier.Operation.ADDITION));
            this.getAttributeInstance(EntityAttributes.GENERIC_KNOCKBACK_RESISTANCE).addTemporaryModifier(new EntityAttributeModifier(HORSE_ARMOR_BONUS_ID, "Armor knockback resistance", 0.2, EntityAttributeModifier.Operation.ADDITION));
        }
    }
}
