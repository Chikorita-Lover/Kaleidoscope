package net.chikorita_lover.kaleidoscope.mixin.item;

import net.chikorita_lover.kaleidoscope.registry.KaleidoscopeSoundEvents;
import net.chikorita_lover.kaleidoscope.registry.MossyBlocksRegistry;
import net.minecraft.advancement.criterion.Criteria;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.*;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.sound.SoundCategory;
import net.minecraft.util.ActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.event.GameEvent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArgs;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.invoke.arg.Args;

import java.util.Optional;

@Mixin(HoeItem.class)
public class HoeItemMixin {
    @ModifyArgs(method = "<init>", at = @At(value = "INVOKE", target = "Lnet/minecraft/item/MiningToolItem;<init>(Lnet/minecraft/item/ToolMaterial;Lnet/minecraft/registry/tag/TagKey;Lnet/minecraft/item/Item$Settings;)V"))
    private static void init(Args args) {
        if (args.get(0).equals(ToolMaterials.GOLD)) {
            args.set(2, ((Item.Settings) args.get(2)).attributeModifiers(AxeItem.createAttributeModifiers(ToolMaterials.GOLD, -1.0F, -2.0F)));
        }
    }

    @Inject(method = "useOnBlock", at = @At("HEAD"), cancellable = true)
    private void useOnBlock(ItemUsageContext context, CallbackInfoReturnable<ActionResult> cir) {
        World world = context.getWorld();
        BlockPos pos = context.getBlockPos();
        PlayerEntity player = context.getPlayer();
        BlockState state = world.getBlockState(pos);
        ItemStack stack = context.getStack();
        Optional<Block> optional = Optional.ofNullable(MossyBlocksRegistry.MOSSY_TO_CLEAN_BLOCKS.get(state.getBlock()));

        if (optional.isPresent()) {
            BlockState blockState = optional.get().getStateWithProperties(state);
            world.playSound(player, pos, KaleidoscopeSoundEvents.ITEM_HOE_SCRAPE, SoundCategory.BLOCKS, 1.0F, 1.0F);
            world.addBlockBreakParticles(pos, Blocks.MOSS_BLOCK.getDefaultState());
            if (player instanceof ServerPlayerEntity) {
                Criteria.ITEM_USED_ON_BLOCK.trigger((ServerPlayerEntity) player, pos, stack);
            }
            world.setBlockState(pos, blockState, 11);
            world.emitGameEvent(GameEvent.BLOCK_CHANGE, pos, GameEvent.Emitter.of(player, blockState));
            if (player != null) {
                stack.damage(1, player, LivingEntity.getSlotForHand(context.getHand()));
            }
            cir.setReturnValue(ActionResult.success(world.isClient));
        }
    }
}
