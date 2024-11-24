package net.chikorita_lover.kaleidoscope.mixin.block;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.MangroveRootsBlock;
import net.minecraft.block.PillarBlock;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.EnumProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.BlockRotation;
import net.minecraft.util.math.Direction;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(MangroveRootsBlock.class)
public class MangroveRootsBlockMixin extends Block {
    @Unique
    private static final EnumProperty<Direction.Axis> AXIS = Properties.AXIS;

    public MangroveRootsBlockMixin(Settings settings) {
        super(settings);
    }

    @Inject(method = "<init>", at = @At("TAIL"))
    private void modifyDefaultState(Settings settings, CallbackInfo ci) {
        this.setDefaultState(this.getDefaultState().with(AXIS, Direction.Axis.Y));
    }

    @Override
    public BlockState rotate(BlockState state, BlockRotation rotation) {
        return PillarBlock.changeRotation(state, rotation);
    }

    @Inject(method = "appendProperties", at = @At("TAIL"))
    protected void appendPillarProperties(StateManager.Builder<Block, BlockState> builder, CallbackInfo ci) {
        builder.add(AXIS);
    }

    @ModifyReturnValue(method = "getPlacementState", at = @At("RETURN"))
    public BlockState modifyPlacementState(BlockState state, @Local(argsOnly = true) ItemPlacementContext ctx) {
        return state.with(AXIS, ctx.getSide().getAxis());
    }
}
