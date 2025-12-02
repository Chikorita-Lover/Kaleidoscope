package net.chikorita_lover.kaleidoscope.mixin.entity;

import net.chikorita_lover.kaleidoscope.KaleidoscopeConfig;
import net.chikorita_lover.kaleidoscope.entity.HealableAnimal;
import net.chikorita_lover.kaleidoscope.registry.KaleidoscopeDataComponentTypes;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.passive.PassiveEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(AnimalEntity.class)
public abstract class AnimalEntityMixin extends PassiveEntity implements HealableAnimal {
    protected AnimalEntityMixin(EntityType<? extends PassiveEntity> entityType, World world) {
        super(entityType, world);
    }

    @Shadow
    protected abstract void playEatSound();

    @Shadow
    public abstract boolean isBreedingItem(ItemStack var1);

    @Inject(method = "interactMob", at = @At("HEAD"), cancellable = true)
    public void receiveFood(PlayerEntity player, Hand hand, CallbackInfoReturnable<ActionResult> cir) {
        if (!KaleidoscopeConfig.HEAL_ANIMALS_ON_FEED.get()) {
            return;
        }
        ItemStack stack = player.getStackInHand(hand);
        if ((this.isBreedingItem(stack) || this.kaleidoscope$isFeedingItem(stack)) && this.getHealth() < this.getMaxHealth()) {
            this.eat(player, hand, stack);
            float amount = stack.getOrDefault(KaleidoscopeDataComponentTypes.ANIMAL_HEALABLE, 2);
            this.heal(amount);
            this.playEatSound();
            cir.setReturnValue(ActionResult.SUCCESS_SERVER);
        }
    }
}
