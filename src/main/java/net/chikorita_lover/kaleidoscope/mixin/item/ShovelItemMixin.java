package net.chikorita_lover.kaleidoscope.mixin.item;

import net.chikorita_lover.kaleidoscope.registry.KaleidoscopeSoundEvents;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.PillarBlock;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.item.ShovelItem;
import net.minecraft.sound.SoundCategory;
import net.minecraft.util.ActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.event.GameEvent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ShovelItem.class)
public class ShovelItemMixin {
    @Inject(method = "useOnBlock", at = @At("HEAD"), cancellable = true)
    private void scoopMuddyMangroveRoots(ItemUsageContext context, CallbackInfoReturnable<ActionResult> cir) {
        World world = context.getWorld();
        BlockPos pos = context.getBlockPos();
        BlockState oldState = world.getBlockState(pos);
        if (oldState.isOf(Blocks.MUDDY_MANGROVE_ROOTS)) {
            world.addBlockBreakParticles(pos, Blocks.MUD.getDefaultState());
            world.playSound(null, pos, KaleidoscopeSoundEvents.ITEM_SHOVEL_SCOOP_MUD, SoundCategory.BLOCKS, 1.0F, MathHelper.nextBetween(world.getRandom(), 0.9F, 1.1F));
            if (!world.isClient()) {
                Direction direction = context.getSide();
                PlayerEntity player = context.getPlayer();
                BlockState newState = Blocks.MANGROVE_ROOTS.getDefaultState().with(PillarBlock.AXIS, oldState.get(PillarBlock.AXIS));
                world.setBlockState(pos, newState, Block.NOTIFY_ALL_AND_REDRAW);
                ItemEntity item = new ItemEntity(world, pos.getX() + 0.5 + context.getSide().getOffsetX() * 0.65, pos.getY() + 0.5 + context.getSide().getOffsetY() * 0.65, pos.getZ() + 0.5 + context.getSide().getOffsetZ() * 0.65, new ItemStack(Blocks.MUD));
                item.setVelocity(0.05 * direction.getOffsetX() + world.getRandom().nextDouble() * 0.02, 0.05 * direction.getOffsetY() + world.getRandom().nextDouble() * 0.02, 0.05 * direction.getOffsetZ() + world.getRandom().nextDouble() * 0.02);
                world.spawnEntity(item);
                world.emitGameEvent(GameEvent.BLOCK_CHANGE, pos, GameEvent.Emitter.of(player, newState));
                if (player != null) {
                    context.getStack().damage(1, player, LivingEntity.getSlotForHand(context.getHand()));
                }
            }
            cir.setReturnValue(ActionResult.success(world.isClient()));
        }
    }
}
