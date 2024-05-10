package net.chikorita_lover.kaleidoscope.block;

import net.chikorita_lover.kaleidoscope.block.entity.PotionCauldronBlockEntity;
import net.chikorita_lover.kaleidoscope.registry.KaleidoscopeBlockEntities;
import net.chikorita_lover.kaleidoscope.registry.KaleidoscopeCauldronBehavior;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.BlockEntityProvider;
import net.minecraft.block.BlockState;
import net.minecraft.block.LeveledCauldronBlock;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.particle.EntityEffectParticleEffect;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;
import net.minecraft.world.WorldView;
import net.minecraft.world.event.GameEvent;

import java.util.Optional;

public class PotionCauldronBlock extends LeveledCauldronBlock implements BlockEntityProvider {
    public PotionCauldronBlock(AbstractBlock.Settings settings) {
        super(null, KaleidoscopeCauldronBehavior.POTION_CAULDRON_BEHAVIOR, settings);
    }

    private static boolean canApplyPotion(LivingEntity livingEntity, StatusEffectInstance instance) {
        if (instance.equals(StatusEffects.INSTANT_HEALTH)) {
            return livingEntity.getHealth() < livingEntity.getMaxHealth();
        }
        return !livingEntity.hasStatusEffect(instance.getEffectType()) || livingEntity.getStatusEffect(instance.getEffectType()).getAmplifier() < instance.getAmplifier();
    }

    @Override
    public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new PotionCauldronBlockEntity(pos, state);
    }

    @Override
    public void randomDisplayTick(BlockState state, World world, BlockPos pos, Random random) {
        super.randomDisplayTick(state, world, pos, random);
        Optional<PotionCauldronBlockEntity> optional = world.getBlockEntity(pos, KaleidoscopeBlockEntities.POTION_CAULDRON);
        if (optional.isPresent()) {
            int color = optional.get().getColor();
            double d = pos.getX() + random.nextDouble() * 0.5 + 0.25;
            double e = pos.getY() + this.getFluidHeight(state);
            double f = pos.getZ() + random.nextDouble() * 0.5 + 0.25;
            float red = (color >> 16 & 0xFF) / 255.0F;
            float green = (color >> 8 & 0xFF) / 255.0F;
            float blue = (color & 0xFF) / 255.0F;
            world.addParticle(EntityEffectParticleEffect.create(ParticleTypes.ENTITY_EFFECT, red, green, blue), d, e, f, 0.0, 0.0, 0.0);
        }
    }

    @Override
    public void onEntityCollision(BlockState state, World world, BlockPos pos, Entity entity) {
        super.onEntityCollision(state, world, pos, entity);
        Optional<PotionCauldronBlockEntity> optional = world.getBlockEntity(pos, KaleidoscopeBlockEntities.POTION_CAULDRON);
        if (entity instanceof LivingEntity livingEntity && this.isEntityTouchingFluid(state, pos, entity) && optional.isPresent()) {
            Iterable<StatusEffectInstance> effectInstances = optional.get().getEffects();
            boolean applyEffects = false;
            for (StatusEffectInstance instance : effectInstances) {
                if (!livingEntity.canHaveStatusEffect(instance)) continue;
                applyEffects = canApplyPotion(livingEntity, instance);
                if (applyEffects) {
                    break;
                }
            }
            if (!applyEffects) {
                return;
            }
            for (StatusEffectInstance instance : effectInstances) {
                livingEntity.addStatusEffect(new StatusEffectInstance(instance));
            }
            LeveledCauldronBlock.decrementFluidLevel(state, world, pos);
            world.playSound(null, pos, SoundEvents.ENTITY_GENERIC_SPLASH, entity.getSoundCategory(), 1.0F, 1.0F);
            world.emitGameEvent(entity, GameEvent.BLOCK_CHANGE, pos);
        }
    }

    @Override
    public ItemStack getPickStack(WorldView world, BlockPos pos, BlockState state) {
        return new ItemStack(Items.CAULDRON);
    }
}
