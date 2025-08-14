package net.chikorita_lover.kaleidoscope.mixin.block;

import net.chikorita_lover.kaleidoscope.KaleidoscopeConfig;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.CakeBlock;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.particle.ItemStackParticleEffect;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.WorldAccess;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(CakeBlock.class)
public class CakeBlockMixin extends Block {
    public CakeBlockMixin(Settings settings) {
        super(settings);
    }

    @Inject(method = "tryEat", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/player/HungerManager;add(IF)V", shift = At.Shift.BEFORE))
    private static void playEatEffects(WorldAccess world, BlockPos pos, BlockState state, PlayerEntity player, CallbackInfoReturnable<ActionResult> cir) {
        if (!KaleidoscopeConfig.PRODUCE_CAKE_EFFECTS.get()) {
            return;
        }
        player.playSound(SoundEvents.ENTITY_GENERIC_EAT.value(), 1.0F, 1.0F + (world.getRandom().nextFloat() - world.getRandom().nextFloat()) * 0.4F);
        for (int i = 0; i < 5; ++i) {
            Vec3d velocity = new Vec3d((player.getRandom().nextFloat() - 0.5) * 0.1, Math.random() * 0.1 + 0.1, 0.0);
            velocity = velocity.rotateX((float) Math.toRadians(-player.getPitch()));
            velocity = velocity.rotateY((float) Math.toRadians(-player.getYaw()));
            double d = -player.getRandom().nextFloat() * 0.6 - 0.3;
            Vec3d position = new Vec3d((player.getRandom().nextFloat() - 0.5) * 0.3, d, 0.6);
            position = position.rotateX((float) Math.toRadians(-player.getPitch()));
            position = position.rotateY((float) Math.toRadians(-player.getYaw()));
            position = position.add(player.getX(), player.getEyeY(), player.getZ());
            player.getWorld().addParticleClient(new ItemStackParticleEffect(ParticleTypes.ITEM, new ItemStack(Items.CAKE)), position.x, position.y, position.z, velocity.x, velocity.y + 0.05, velocity.z);
        }
    }
}
