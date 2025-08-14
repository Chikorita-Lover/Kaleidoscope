package net.chikorita_lover.kaleidoscope.mixin.block;

import com.llamalad7.mixinextras.injector.v2.WrapWithCondition;
import net.chikorita_lover.kaleidoscope.KaleidoscopeConfig;
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

@Mixin(FarmlandBlock.class)
public class FarmlandBlockMixin {
    @WrapWithCondition(method = "onLandedUpon", at = @At(value = "INVOKE", target = "Lnet/minecraft/block/FarmlandBlock;setToDirt(Lnet/minecraft/entity/Entity;Lnet/minecraft/block/BlockState;Lnet/minecraft/world/World;Lnet/minecraft/util/math/BlockPos;)V"))
    private boolean canTrample(Entity entity, BlockState state, World world, BlockPos pos) {
        if (!KaleidoscopeConfig.FEATHER_FALLING_PRESERVES_FARMLAND.get() || !(entity instanceof LivingEntity livingEntity)) {
            return true;
        }
        for (EquipmentSlot slot : EquipmentSlot.values()) {
            if (EnchantmentHelper.hasAnyEnchantmentsIn(livingEntity.getEquippedStack(slot), KaleidoscopeEnchantmentTags.PREVENTS_FARMLAND_TRAMPLING)) {
                return false;
            }
        }
        return true;
    }
}
