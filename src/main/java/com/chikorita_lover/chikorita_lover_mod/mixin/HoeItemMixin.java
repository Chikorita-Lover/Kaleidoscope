package com.chikorita_lover.chikorita_lover_mod.mixin;

import com.chikorita_lover.chikorita_lover_mod.registry.ModSoundEvents;
import com.chikorita_lover.chikorita_lover_mod.registry.MossyBlocksRegistry;
import net.minecraft.advancement.criterion.Criteria;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.HoeItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.sound.SoundCategory;
import net.minecraft.util.ActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.event.GameEvent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Optional;

@Mixin(HoeItem.class)
public class HoeItemMixin {
    @Inject(at = @At("HEAD"), method = "useOnBlock", cancellable = true)
    public void useOnBlock(ItemUsageContext context, CallbackInfoReturnable<ActionResult> info) {
        World world = context.getWorld();
        BlockPos pos = context.getBlockPos();
        PlayerEntity playerEntity = context.getPlayer();
        BlockState state = world.getBlockState(pos);
        ItemStack itemStack = context.getStack();
        Optional<Block> optional = Optional.ofNullable(MossyBlocksRegistry.MOSSY_TO_CLEAN_BLOCKS.get(state.getBlock()));

        if (optional.isPresent()) {
            BlockState blockState = optional.get().getStateWithProperties(state);

            world.playSound(playerEntity, pos, ModSoundEvents.ITEM_HOE_SCRAPE, SoundCategory.BLOCKS, 1.0F, 1.0F);
            world.addBlockBreakParticles(pos, Blocks.MOSS_BLOCK.getDefaultState());

            if (playerEntity instanceof ServerPlayerEntity) {
                Criteria.ITEM_USED_ON_BLOCK.trigger((ServerPlayerEntity)playerEntity, pos, itemStack);
            }

            world.setBlockState(pos, blockState, 11);
            world.emitGameEvent(GameEvent.BLOCK_CHANGE, pos, GameEvent.Emitter.of(playerEntity, blockState));
            if (playerEntity != null) {
                itemStack.damage(1, playerEntity, (p) -> p.sendToolBreakStatus(context.getHand()));
            }

            info.setReturnValue(ActionResult.success(world.isClient));
        }
    }
}
