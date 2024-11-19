package net.chikorita_lover.kaleidoscope.mixin.entity;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.minecraft.block.BlockState;
import net.minecraft.block.ChainBlock;
import net.minecraft.block.SideShapeType;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.TntEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(TntEntity.class)
public abstract class TntEntityMixin extends Entity {
    public TntEntityMixin(EntityType<?> type, World world) {
        super(type, world);
    }

    @WrapOperation(method = "<init>(Lnet/minecraft/world/World;DDDLnet/minecraft/entity/LivingEntity;)V", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/TntEntity;setVelocity(DDD)V"))
    private void trySetVelocity(TntEntity entity, double x, double y, double z, Operation<Void> operation) {
        if (!this.isChained()) {
            operation.call(this, x, y, z);
        }
    }

    @ModifyReturnValue(method = "getGravity", at = @At("RETURN"))
    private double tryChain(double gravity) {
        return this.isChained() ? 0.0F : gravity;
    }

    @Unique
    private boolean isChained() {
        BlockView world = this.getWorld();
        BlockPos pos = this.getBlockPos();
        BlockState chain = world.getBlockState(pos.up());
        return chain.getBlock() instanceof ChainBlock && chain.get(ChainBlock.AXIS).isVertical() && chain.isSideSolid(world, pos, Direction.UP, SideShapeType.CENTER);
    }
}
