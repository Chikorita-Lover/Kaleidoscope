package com.chikoritalover.kaleidoscope.block;

import com.chikoritalover.kaleidoscope.block.entity.PotionCauldronBlockEntity;
import com.chikoritalover.kaleidoscope.registry.KaleidoscopeCauldronBehavior;
import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.ItemStack;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.event.GameEvent;

import java.util.List;

public class PotionCauldronBlock extends LeveledCauldronBlock implements BlockEntityProvider {
    public PotionCauldronBlock(AbstractBlock.Settings settings) {
        super(settings, null, KaleidoscopeCauldronBehavior.POTION_CAULDRON_BEHAVIOR);
    }

    public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new PotionCauldronBlockEntity(pos, state);
    }

    @Override
    public void randomDisplayTick(BlockState state, World world, BlockPos pos, Random random) {
        super.randomDisplayTick(state, world, pos, random);
        int i = ((PotionCauldronBlockEntity) world.getBlockEntity(pos)).getColor();
        double d = pos.getX() + random.nextDouble() * 0.5 + 0.25;
        double e = pos.getY() + this.getFluidHeight(state);
        double f = pos.getZ() + random.nextDouble() * 0.5 + 0.25;
        double g = (double) (i >> 16 & 0xFF) / 255.0;
        double h = (double) (i >> 8 & 0xFF) / 255.0;
        double j = (double) (i & 0xFF) / 255.0;
        world.addParticle(ParticleTypes.ENTITY_EFFECT, d, e, f, g, h, j);
    }

    @Override
    public ItemStack getPickStack(BlockView world, BlockPos pos, BlockState state) {
        return new ItemStack(Blocks.CAULDRON);
    }

    @Override
    public void onEntityCollision(BlockState state, World world, BlockPos pos, Entity entity) {
        super.onEntityCollision(state, world, pos, entity);
        if (entity instanceof LivingEntity livingEntity && this.isEntityTouchingFluid(state, pos, entity)) {
            List<StatusEffectInstance> effectInstances = ((PotionCauldronBlockEntity) world.getBlockEntity(pos)).getStatusEffects();
            boolean bl = false;
            for (StatusEffectInstance instance : effectInstances) {
                if (!livingEntity.canHaveStatusEffect(instance)) continue;
                StatusEffect effect = instance.getEffectType();
                if (effect == StatusEffects.INSTANT_HEALTH) {
                    bl = livingEntity.getHealth() < livingEntity.getMaxHealth();
                } else {
                    bl = !livingEntity.hasStatusEffect(effect) || livingEntity.getStatusEffect(effect).getAmplifier() < instance.getAmplifier();
                }
                if (bl) break;
            }
            if (!bl) return;
            for (StatusEffectInstance instance2 : effectInstances) {
                livingEntity.addStatusEffect(new StatusEffectInstance(instance2));
            }
            LeveledCauldronBlock.decrementFluidLevel(state, world, pos);
            world.playSound(null, pos, SoundEvents.ENTITY_GENERIC_SPLASH, entity.getSoundCategory(), 1.0F, 1.0F);
            world.emitGameEvent(entity, GameEvent.BLOCK_CHANGE, pos);
        }
    }
}
