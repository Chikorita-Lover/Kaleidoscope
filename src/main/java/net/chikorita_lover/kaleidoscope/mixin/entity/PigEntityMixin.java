package net.chikorita_lover.kaleidoscope.mixin.entity;

import net.chikorita_lover.kaleidoscope.registry.KaleidoscopeSoundEvents;
import net.fabricmc.fabric.api.tag.convention.v2.ConventionalItemTags;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.Saddleable;
import net.minecraft.entity.SaddledComponent;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.passive.PigEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;
import net.minecraft.world.event.GameEvent;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(PigEntity.class)
public abstract class PigEntityMixin extends AnimalEntity implements Saddleable {
    @Shadow
    @Final
    private SaddledComponent saddledComponent;

    protected PigEntityMixin(EntityType<? extends AnimalEntity> entityType, World world) {
        super(entityType, world);
    }

    @Shadow
    public abstract boolean isSaddled();

    @Inject(method = "interactMob", at = @At("HEAD"), cancellable = true)
    private void tryRemoveSaddle(PlayerEntity player, Hand hand, CallbackInfoReturnable<ActionResult> cir) {
        ItemStack stack = player.getStackInHand(hand);
        if (!stack.isIn(ConventionalItemTags.SHEAR_TOOLS) || !this.isSaddled() || this.hasPassengers()) {
            return;
        }
        this.saddledComponent.setSaddled(false);
        this.playSound(KaleidoscopeSoundEvents.ENTITY_PIG_SHEAR);
        this.dropStack(new ItemStack(Items.SADDLE), this.getHeight());
        this.emitGameEvent(GameEvent.SHEAR, player);
        if (!this.getWorld().isClient()) {
            stack.damage(1, player, LivingEntity.getSlotForHand(hand));
        }
        cir.setReturnValue(ActionResult.success(this.getWorld().isClient()));
    }
}
