package net.chikorita_lover.kaleidoscope.mixin.entity;

import net.chikorita_lover.kaleidoscope.entity.BoatEntityAccessor;
import net.chikorita_lover.kaleidoscope.item.KaleidoscopeItems;
import net.chikorita_lover.kaleidoscope.registry.KaleidoscopeSoundEvents;
import net.fabricmc.fabric.api.registry.FlammableBlockRegistry;
import net.fabricmc.fabric.api.tag.convention.v2.ConventionalItemTags;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.vehicle.BoatEntity;
import net.minecraft.entity.vehicle.VehicleEntity;
import net.minecraft.item.BannerItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtElement;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.stat.Stats;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.ItemScatterer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(BoatEntity.class)
public abstract class BoatEntityMixin extends VehicleEntity implements BoatEntityAccessor {
    @Unique
    private static final TrackedData<ItemStack> EQUIPPED_BANNER = DataTracker.registerData(BoatEntity.class, TrackedDataHandlerRegistry.ITEM_STACK);

    public BoatEntityMixin(EntityType<?> entityType, World world) {
        super(entityType, world);
    }

    @Shadow
    public abstract BoatEntity.Type getVariant();

    @Override
    public ItemStack kaleidoscope$getBannerStack() {
        return this.dataTracker.get(EQUIPPED_BANNER);
    }

    @Inject(method = "initDataTracker", at = @At("TAIL"))
    private void initDataTracker(DataTracker.Builder builder, CallbackInfo ci) {
        builder.add(EQUIPPED_BANNER, ItemStack.EMPTY);
    }

    @Inject(method = "asItem", at = @At("HEAD"), cancellable = true)
    protected void asItem(CallbackInfoReturnable<Item> cir) {
        if (this.getVariant() == KaleidoscopeItems.CRIMSON_BOAT_TYPE) {
            cir.setReturnValue(KaleidoscopeItems.CRIMSON_BOAT);
        }
        if (this.getVariant() == KaleidoscopeItems.WARPED_BOAT_TYPE) {
            cir.setReturnValue(KaleidoscopeItems.WARPED_BOAT);
        }
    }

    @Inject(method = "fall", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/GameRules;getBoolean(Lnet/minecraft/world/GameRules$Key;)Z", shift = At.Shift.AFTER))
    private void dropBannerOnFall(double heightDifference, boolean onGround, BlockState state, BlockPos landedPosition, CallbackInfo ci) {
        ItemScatterer.spawn(this.getWorld(), this.getX(), this.getY(), this.getZ(), this.kaleidoscope$getBannerStack());
    }

    @Inject(method = "writeCustomDataToNbt", at = @At("TAIL"))
    private void writeBannerItemToNbt(NbtCompound nbt, CallbackInfo ci) {
        if (!this.kaleidoscope$getBannerStack().isEmpty()) {
            nbt.put("KaleidoscopeBannerItem", this.kaleidoscope$getBannerStack().encode(this.getRegistryManager()));
        }
    }

    @Inject(method = "readCustomDataFromNbt", at = @At("TAIL"))
    private void readBannerItemFromNbt(NbtCompound nbt, CallbackInfo ci) {
        if (nbt.contains("KaleidoscopeBannerItem", NbtElement.COMPOUND_TYPE)) {
            NbtCompound nbtCompound = nbt.getCompound("KaleidoscopeBannerItem");
            this.dataTracker.set(EQUIPPED_BANNER, ItemStack.fromNbt(this.getRegistryManager(), nbtCompound).orElse(ItemStack.EMPTY));
        } else {
            this.dataTracker.set(EQUIPPED_BANNER, ItemStack.EMPTY);
        }
    }

    @Inject(method = "interact", at = @At(value = "CONSTANT", args = "floatValue=60.0", shift = At.Shift.BEFORE), cancellable = true)
    private void tryInteract(PlayerEntity player, Hand hand, CallbackInfoReturnable<ActionResult> cir) {
        ItemStack stack = player.getStackInHand(hand);
        if (stack.getItem() instanceof BannerItem) {
            if (!this.kaleidoscope$getBannerStack().isEmpty()) {
                return;
            }
            this.dataTracker.set(EQUIPPED_BANNER, stack.copyWithCount(1));
            stack.decrementUnlessCreative(1, player);
            this.playSound(KaleidoscopeSoundEvents.ENTITY_BOAT_EQUIP_BANNER, 1.0F, MathHelper.nextBetween(this.random, 0.9F, 1.1F));
            if (player instanceof ServerPlayerEntity serverPlayer) {
                serverPlayer.incrementStat(Stats.USED.getOrCreateStat(stack.getItem()));
            }
            cir.setReturnValue(ActionResult.success(this.getWorld().isClient()));
        } else if (stack.isIn(ConventionalItemTags.SHEARS_TOOLS)) {
            if (this.kaleidoscope$getBannerStack().isEmpty()) {
                return;
            }
            ItemScatterer.spawn(this.getWorld(), this.getX(), this.getY() + this.getHeight(), this.getZ(), this.kaleidoscope$getBannerStack());
            this.dataTracker.set(EQUIPPED_BANNER, ItemStack.EMPTY);
            this.playSound(KaleidoscopeSoundEvents.ENTITY_BOAT_SHEAR, 1.0F, 1.0F);
            if (!this.getWorld().isClient()) {
                stack.damage(1, player, LivingEntity.getSlotForHand(hand));
            }
            cir.setReturnValue(ActionResult.success(this.getWorld().isClient()));
        }
    }

    @Override
    public boolean isFireImmune() {
        Block baseBlock = this.getVariant().getBaseBlock();
        return FlammableBlockRegistry.getDefaultInstance().get(baseBlock).getBurnChance() == 0;
    }

    @Override
    public void killAndDropSelf(DamageSource source) {
        super.killAndDropSelf(source);
        if (!this.getWorld().isClient() && !(source.getAttacker() instanceof PlayerEntity player && player.isInCreativeMode())) {
            ItemScatterer.spawn(this.getWorld(), this.getX(), this.getY(), this.getZ(), this.kaleidoscope$getBannerStack());
        }
    }

    @Override
    public Box getVisibilityBoundingBox() {
        Box box = super.getVisibilityBoundingBox();
        return this.kaleidoscope$getBannerStack().isEmpty() ? box : box.withMaxY(box.maxY + 1.875);
    }
}
