package net.chikorita_lover.kaleidoscope.mixin.entity;

import net.chikorita_lover.kaleidoscope.registry.KaleidoscopeSoundEvents;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.passive.AbstractHorseEntity;
import net.minecraft.entity.passive.CamelEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsage;
import net.minecraft.item.Items;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(CamelEntity.class)
public class CamelEntityMixin extends AbstractHorseEntity {
    protected CamelEntityMixin(EntityType<? extends AbstractHorseEntity> entityType, World world) {
        super(entityType, world);
    }

    @Inject(method = "interactMob", at = @At("HEAD"), cancellable = true)
    private void tryMilkMob(PlayerEntity player, Hand hand, CallbackInfoReturnable<ActionResult> cir) {
        ItemStack stack = player.getStackInHand(hand);
        if (!stack.isOf(Items.BUCKET) || this.isBaby()) {
            return;
        }
        player.playSound(KaleidoscopeSoundEvents.ENTITY_CAMEL_MILK, 1.0F, 1.0F);
        ItemStack milkStack = ItemUsage.exchangeStack(stack, player, Items.MILK_BUCKET.getDefaultStack());
        player.setStackInHand(hand, milkStack);
        cir.setReturnValue(ActionResult.success(this.getWorld().isClient()));
    }
}
