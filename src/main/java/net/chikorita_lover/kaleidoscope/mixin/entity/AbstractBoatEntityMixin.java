package net.chikorita_lover.kaleidoscope.mixin.entity;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.chikorita_lover.kaleidoscope.KaleidoscopeConfig;
import net.chikorita_lover.kaleidoscope.entity.BannerEquippable;
import net.chikorita_lover.kaleidoscope.registry.KaleidoscopeSoundEvents;
import net.fabricmc.fabric.api.tag.convention.v2.ConventionalItemTags;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.vehicle.AbstractBoatEntity;
import net.minecraft.entity.vehicle.VehicleEntity;
import net.minecraft.item.BannerItem;
import net.minecraft.item.ItemStack;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.stat.Stats;
import net.minecraft.storage.ReadView;
import net.minecraft.storage.WriteView;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(AbstractBoatEntity.class)
public abstract class AbstractBoatEntityMixin extends VehicleEntity implements BannerEquippable {
    @Unique
    private static final TrackedData<ItemStack> EQUIPPED_BANNER = DataTracker.registerData(AbstractBoatEntity.class, TrackedDataHandlerRegistry.ITEM_STACK);

    public AbstractBoatEntityMixin(EntityType<?> type, World world) {
        super(type, world);
    }

    @Override
    public ItemStack kaleidoscope$getBannerStack() {
        return this.dataTracker.get(EQUIPPED_BANNER);
    }

    @Override
    public void kaleidoscope$setBannerStack(ItemStack stack) {
        this.dataTracker.set(EQUIPPED_BANNER, stack);
    }

    @Inject(method = "initDataTracker", at = @At("TAIL"))
    private void initDataTracker(DataTracker.Builder builder, CallbackInfo ci) {
        builder.add(EQUIPPED_BANNER, ItemStack.EMPTY);
    }

    @Inject(method = "writeCustomData", at = @At("TAIL"))
    private void writeBannerData(WriteView view, CallbackInfo ci) {
        this.kaleidoscope$writeBannerData(view);
    }

    @Inject(method = "readCustomData", at = @At("TAIL"))
    private void readBannerData(ReadView view, CallbackInfo ci) {
        this.kaleidoscope$readBannerData(view);
    }

    @WrapOperation(method = "interact", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/vehicle/VehicleEntity;interact(Lnet/minecraft/entity/player/PlayerEntity;Lnet/minecraft/util/Hand;)Lnet/minecraft/util/ActionResult;"))
    private ActionResult tryInteract(AbstractBoatEntity boat, PlayerEntity player, Hand hand, Operation<ActionResult> original) {
        ActionResult result = original.call(boat, player, hand);
        if (result != ActionResult.PASS) {
            return result;
        }
        boolean server = !this.getEntityWorld().isClient();
        ItemStack stack = player.getStackInHand(hand);
        if (KaleidoscopeConfig.ALLOW_BANNERS_ON_BOATS.get() && stack.getItem() instanceof BannerItem && !this.kaleidoscope$hasBanner()) {
            this.kaleidoscope$setBannerStack(stack.copyWithCount(1));
            stack.decrementUnlessCreative(1, player);
            this.playSound(KaleidoscopeSoundEvents.ENTITY_BOAT_EQUIP_BANNER, 1.0F, MathHelper.nextBetween(this.random, 0.9F, 1.1F));
            if (server) {
                player.incrementStat(Stats.USED.getOrCreateStat(stack.getItem()));
            }
            result = ActionResult.SUCCESS;
        } else if (stack.isIn(ConventionalItemTags.SHEAR_TOOLS) && this.kaleidoscope$hasBanner()) {
            if (server) {
                this.dropStack((ServerWorld) this.getEntityWorld(), this.kaleidoscope$getBannerStack(), this.getHeight());
                stack.damage(1, player, hand.getEquipmentSlot());
            }
            this.kaleidoscope$setBannerStack(ItemStack.EMPTY);
            this.playSound(KaleidoscopeSoundEvents.ENTITY_BOAT_SHEAR, 1.0F, 1.0F);
            result = ActionResult.SUCCESS;
        }
        return result;
    }
}
