package com.chikoritalover.kaleidoscope.mixin;

import net.minecraft.block.BlockState;
import net.minecraft.block.ChestBlock;
import net.minecraft.block.enums.ChestType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.state.property.EnumProperty;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ChestBlock.class)
public class ChestBlockMixin {
    @Shadow
    @Final
    public static EnumProperty<ChestType> CHEST_TYPE;

    @Inject(method = "onUse", at = @At("RETURN"))
    public void onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit, CallbackInfoReturnable<ActionResult> callbackInfo) {
        BlockPos pos2 = pos.offset(ChestBlock.getFacing(state));
        if (ChestBlock.isChestBlocked(world, pos) || (state.get(CHEST_TYPE) != ChestType.SINGLE && ChestBlock.isChestBlocked(world, pos2))) {
            player.sendMessage(Text.translatable("block.minecraft.chest.obstructed"), true);
        }
    }
}
