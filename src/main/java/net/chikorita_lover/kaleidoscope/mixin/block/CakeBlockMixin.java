package net.chikorita_lover.kaleidoscope.mixin.block;

import net.chikorita_lover.kaleidoscope.item.CakeSliceItem;
import net.chikorita_lover.kaleidoscope.registry.KaleidoscopeItems;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.CakeBlock;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.particle.ItemStackParticleEffect;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.ItemActionResult;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
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
        player.playSound(player.getEatSound(new ItemStack(KaleidoscopeItems.CAKE_SLICE)), 1.0F, 1.0F + (world.getRandom().nextFloat() - world.getRandom().nextFloat()) * 0.4F);
        for (int i = 0; i < 5; ++i) {
            Vec3d vec3d = new Vec3d((player.getRandom().nextFloat() - 0.5) * 0.1, Math.random() * 0.1 + 0.1, 0.0);
            vec3d = vec3d.rotateX(-player.getPitch() * 0.017453292F);
            vec3d = vec3d.rotateY(-player.getYaw() * 0.017453292F);
            double d = -player.getRandom().nextFloat() * 0.6 - 0.3;
            Vec3d vec3d2 = new Vec3d((player.getRandom().nextFloat() - 0.5) * 0.3, d, 0.6);
            vec3d2 = vec3d2.rotateX(-player.getPitch() * 0.017453292F);
            vec3d2 = vec3d2.rotateY(-player.getYaw() * 0.017453292F);
            vec3d2 = vec3d2.add(player.getX(), player.getEyeY(), player.getZ());
            player.getWorld().addParticle(new ItemStackParticleEffect(ParticleTypes.ITEM, new ItemStack(Items.CAKE)), vec3d2.x, vec3d2.y, vec3d2.z, vec3d.x, vec3d.y + 0.05, vec3d.z);
        }
    }

    @Inject(method = "onUseWithItem", at = @At("HEAD"), cancellable = true)
    private void shouldUseWithItem(ItemStack stack, BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit, CallbackInfoReturnable<ItemActionResult> cir) {
        if (stack.getItem() instanceof CakeSliceItem) {
            cir.setReturnValue(ItemActionResult.SKIP_DEFAULT_BLOCK_INTERACTION);
        }
    }
}
