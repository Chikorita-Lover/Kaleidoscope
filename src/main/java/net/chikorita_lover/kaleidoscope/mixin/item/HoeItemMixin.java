package net.chikorita_lover.kaleidoscope.mixin.item;

import net.chikorita_lover.kaleidoscope.recipe.KaleidoscopeRecipeTypes;
import net.chikorita_lover.kaleidoscope.registry.KaleidoscopeSoundEvents;
import net.minecraft.advancement.criterion.Criteria;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.item.HoeItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
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

@Mixin(HoeItem.class)
public class HoeItemMixin {
    @Inject(method = "useOnBlock", at = @At("HEAD"), cancellable = true)
    private void tryScrapeMoss(final ItemUsageContext context, final CallbackInfoReturnable<ActionResult> cir) {
        final World world = context.getWorld();
        final BlockPos pos = context.getBlockPos();
        final PlayerEntity player = context.getPlayer();
        final BlockState state = world.getBlockState(pos);
        final ItemStack stack = context.getStack();
        world.getRecipeManager().getFirstMatch(KaleidoscopeRecipeTypes.MOSS_SCRAPING, new SimpleInventory(new ItemStack(state.getBlock().asItem())), world).ifPresent(entry -> {
            BlockState newState = entry.createStateFrom(state);
            world.playSound(player, pos, KaleidoscopeSoundEvents.ITEM_HOE_SCRAPE, SoundCategory.BLOCKS, 1.0F, 1.0F);
            if (player instanceof ServerPlayerEntity serverPlayer) {
                Criteria.ITEM_USED_ON_BLOCK.trigger(serverPlayer, pos, stack);
            }
            world.setBlockState(pos, newState, Block.NOTIFY_ALL);
            world.emitGameEvent(GameEvent.BLOCK_CHANGE, pos, GameEvent.Emitter.of(player, newState));
            if (player != null) {
                stack.damage(1, player, playerx -> playerx.sendToolBreakStatus(context.getHand()));
            }
            cir.setReturnValue(ActionResult.success(world.isClient()));
        });
    }
}
