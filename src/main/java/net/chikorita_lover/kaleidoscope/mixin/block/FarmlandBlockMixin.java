package net.chikorita_lover.kaleidoscope.mixin.block;

import net.chikorita_lover.kaleidoscope.registry.tag.KaleidoscopeEnchantmentTags;
import net.minecraft.block.BlockState;
import net.minecraft.block.FarmlandBlock;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(FarmlandBlock.class)
public class FarmlandBlockMixin {
    @Inject(at = @At("HEAD"), method = "onLandedUpon", cancellable = true)
    private void tryCancelTrample(World world, BlockState state, BlockPos pos, Entity entity, float fallDistance, CallbackInfo ci) {
        if (entity instanceof LivingEntity livingEntity && (EnchantmentHelper.hasAnyEnchantmentsIn(livingEntity.getEquippedStack(EquipmentSlot.BODY), KaleidoscopeEnchantmentTags.PREVENTS_FARMLAND_TRAMPLING) || EnchantmentHelper.hasAnyEnchantmentsIn(livingEntity.getEquippedStack(EquipmentSlot.FEET), KaleidoscopeEnchantmentTags.PREVENTS_FARMLAND_TRAMPLING))) {
            ci.cancel();
        }
    }
}
