package com.chikoritalover.kaleidoscope.mixin;

import com.chikoritalover.kaleidoscope.block.entity.KaleidoscopeBrewingStandBlockEntity;
import com.chikoritalover.kaleidoscope.entity.KaleidoscopePlayerEntity;
import com.chikoritalover.kaleidoscope.recipe.BrewingRecipe;
import com.chikoritalover.kaleidoscope.recipe.BrewingUtil;
import net.minecraft.block.BlockState;
import net.minecraft.block.BrewingStandBlock;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BrewingStandBlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.BrewingStandScreenHandler;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.ArrayList;
import java.util.Collection;

@Mixin(BrewingStandBlock.class)
public class BrewingStandBlockMixin {
    @Inject(method = "onStateReplaced", at = @At(value = "INVOKE", target = "Lnet/minecraft/block/BlockWithEntity;onStateReplaced(Lnet/minecraft/block/BlockState;Lnet/minecraft/world/World;Lnet/minecraft/util/math/BlockPos;Lnet/minecraft/block/BlockState;Z)V", shift = At.Shift.BEFORE))
    public void onStateReplaced(BlockState state, World world, BlockPos pos, BlockState newState, boolean moved, CallbackInfo ci) {
        BlockEntity blockEntity = world.getBlockEntity(pos);
        if (blockEntity instanceof BrewingStandBlockEntity && world instanceof ServerWorld) {
            ((KaleidoscopeBrewingStandBlockEntity) blockEntity).kaleidoscope$getRecipesUsedAndDropExperience((ServerWorld) world, pos.toCenterPos());
        }
    }

    @Inject(method = "onUse", at = @At("TAIL"))
    public void onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit, CallbackInfoReturnable<ActionResult> cir) {
        BlockEntity blockEntity = world.getBlockEntity(pos);
        if (blockEntity instanceof BrewingStandBlockEntity brewingStandBlockEntity && player instanceof ServerPlayerEntity serverPlayerEntity && player.currentScreenHandler instanceof BrewingStandScreenHandler brewingStandScreenHandler && brewingStandScreenHandler.inventory == blockEntity) {
            Collection<BrewingRecipe> collection = new ArrayList<>();
            for (int i = 0; i < 3; ++i) {
                ItemStack itemStack = brewingStandBlockEntity.getStack(i);
                collection.addAll(BrewingUtil.getUnlockableRecipes(itemStack));
            }
            ((KaleidoscopePlayerEntity) serverPlayerEntity).kaleidoscope$unlockRecipes(collection);
        }
    }
}
