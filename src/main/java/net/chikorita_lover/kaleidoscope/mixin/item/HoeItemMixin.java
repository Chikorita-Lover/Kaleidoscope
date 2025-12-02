package net.chikorita_lover.kaleidoscope.mixin.item;

import net.chikorita_lover.kaleidoscope.KaleidoscopeConfig;
import net.chikorita_lover.kaleidoscope.recipe.KaleidoscopeRecipeTypes;
import net.chikorita_lover.kaleidoscope.recipe.MossScrapingRecipe;
import net.chikorita_lover.kaleidoscope.recipe.SingleBlockRecipeInput;
import net.chikorita_lover.kaleidoscope.registry.KaleidoscopeSoundEvents;
import net.minecraft.advancement.criterion.Criteria;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.HoeItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.recipe.RecipeEntry;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.util.ActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.event.GameEvent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Optional;

@Mixin(HoeItem.class)
public class HoeItemMixin {
    @Inject(method = "useOnBlock", at = @At("HEAD"), cancellable = true)
    private void scrapeMoss(final ItemUsageContext context, final CallbackInfoReturnable<ActionResult> cir) {
        if (!KaleidoscopeConfig.SCRAPE_MOSS.get() || !(context.getWorld() instanceof ServerWorld world)) {
            return;
        }
        final BlockPos pos = context.getBlockPos();
        final BlockState state = world.getBlockState(pos);
        Optional<RecipeEntry<MossScrapingRecipe>> recipe = world.getRecipeManager().getFirstMatch(KaleidoscopeRecipeTypes.MOSS_SCRAPING, new SingleBlockRecipeInput(state.getBlock()), world);
        if (recipe.isPresent()) {
            PlayerEntity player = context.getPlayer();
            ItemStack stack = context.getStack();
            BlockState newState = recipe.get().value().createStateFrom(world, state);
            world.playSound(player, pos, KaleidoscopeSoundEvents.ITEM_HOE_SCRAPE, SoundCategory.BLOCKS, 1.0F, 1.0F);
            world.setBlockState(pos, newState, Block.NOTIFY_ALL_AND_REDRAW);
            world.emitGameEvent(GameEvent.BLOCK_CHANGE, pos, GameEvent.Emitter.of(player, newState));
            if (player != null) {
                Criteria.ITEM_USED_ON_BLOCK.trigger((ServerPlayerEntity) player, pos, stack);
                stack.damage(1, player, context.getHand().getEquipmentSlot());
            }
            cir.setReturnValue(ActionResult.SUCCESS_SERVER);
        }
    }
}
