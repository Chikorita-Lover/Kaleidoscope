package net.chikorita_lover.kaleidoscope.mixin.entity;

import net.chikorita_lover.kaleidoscope.KaleidoscopeConfig;
import net.chikorita_lover.kaleidoscope.entity.Chestable;
import net.chikorita_lover.kaleidoscope.network.OpenStriderScreenS2CPacket;
import net.chikorita_lover.kaleidoscope.registry.KaleidoscopeSoundEvents;
import net.chikorita_lover.kaleidoscope.screen.StriderScreenHandler;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.block.Blocks;
import net.minecraft.component.EnchantmentEffectComponentTypes;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.RideableInventory;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.passive.StriderEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.inventory.StackWithSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.screen.NamedScreenHandlerFactory;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.storage.ReadView;
import net.minecraft.storage.WriteView;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(StriderEntity.class)
public abstract class StriderEntityMixin extends AnimalEntity implements Chestable, NamedScreenHandlerFactory, RideableInventory {
    @Unique
    private static final TrackedData<Boolean> CHEST = DataTracker.registerData(StriderEntity.class, TrackedDataHandlerRegistry.BOOLEAN);
    @Unique
    private SimpleInventory items;

    protected StriderEntityMixin(EntityType<? extends AnimalEntity> type, World world) {
        super(type, world);
    }

    @Inject(method = "initDataTracker", at = @At("TAIL"))
    private void addChestDataTracker(DataTracker.Builder builder, CallbackInfo ci) {
        builder.add(CHEST, false);
    }

    @Inject(method = "interactMob", at = @At("HEAD"), cancellable = true)
    private void tryAddChest(PlayerEntity player, Hand hand, CallbackInfoReturnable<ActionResult> cir) {
        if (this.hasPassengers() || this.isBaby()) {
            return;
        }
        ItemStack stack = player.getStackInHand(hand);
        if (KaleidoscopeConfig.ALLOW_STRIDER_EQUIPMENT.get() && !this.kaleidoscope$hasChest() && stack.isOf(Items.CHEST)) {
            this.kaleidoscope$addChest(player, stack);
            cir.setReturnValue(ActionResult.SUCCESS);
        } else if (KaleidoscopeConfig.ALLOW_STRIDER_EQUIPMENT.get() && this.kaleidoscope$hasChest() && (!this.hasSaddleEquipped() && !stack.isOf(Items.SADDLE) || player.shouldCancelInteraction())) {
            this.openInventory(player);
            cir.setReturnValue(ActionResult.SUCCESS);
        }
    }

    @Override
    public void kaleidoscope$onChestedStatusChanged() {
        SimpleInventory inventory = this.items;
        this.items = new SimpleInventory(this.kaleidoscope$getInventorySize());
        if (inventory != null) {
            int size = Math.min(inventory.size(), this.items.size());
            for (int slot = 0; slot < size; ++slot) {
                ItemStack stack = inventory.getStack(slot);
                if (stack.isEmpty()) {
                    continue;
                }
                this.items.setStack(slot, stack.copy());
            }
        }
    }

    @Override
    public void kaleidoscope$playAddChestSound() {
        this.playSound(KaleidoscopeSoundEvents.ENTITY_STRIDER_CHEST, 1.0F, MathHelper.nextBetween(this.getRandom(), 0.8F, 1.2F));
    }

    @Override
    public void kaleidoscope$setHasChest(boolean hasChest) {
        this.dataTracker.set(CHEST, hasChest);
    }

    @Override
    public boolean kaleidoscope$hasChest() {
        return this.dataTracker.get(CHEST);
    }

    @Override
    public int kaleidoscope$getInventorySize() {
        return 15;
    }

    @Override
    public void kaleidoscope$writeChestData(WriteView view) {
        view.putBoolean("Chested", this.kaleidoscope$hasChest());
        if (this.kaleidoscope$hasChest()) {
            WriteView.ListAppender<StackWithSlot> listAppender = view.getListAppender("Items", StackWithSlot.CODEC);
            for (int slot = 0; slot < this.items.size(); ++slot) {
                ItemStack stack = this.items.getStack(slot);
                if (!stack.isEmpty()) {
                    listAppender.add(new StackWithSlot(slot, stack));
                }
            }
        }
    }

    @Override
    public void kaleidoscope$readChestData(ReadView view) {
        this.kaleidoscope$setHasChest(view.getBoolean("Chested", false));
        this.kaleidoscope$onChestedStatusChanged();
        if (this.kaleidoscope$hasChest()) {
            for (StackWithSlot stack : view.getTypedListView("Items", StackWithSlot.CODEC)) {
                if (stack.isValidSlot(this.items.size())) {
                    this.items.setStack(stack.slot(), stack.stack());
                }
            }
        }
    }

    @Override
    public void kaleidoscope$dropChestContents(ServerWorld world) {
        if (this.items != null) {
            for (int slot = 0; slot < this.items.size(); ++slot) {
                ItemStack stack = this.items.getStack(slot);
                if (!stack.isEmpty() && !EnchantmentHelper.hasAnyEnchantmentsWith(stack, EnchantmentEffectComponentTypes.PREVENT_EQUIPMENT_DROP)) {
                    this.dropStack(world, stack);
                }
            }
        }
        if (this.kaleidoscope$hasChest()) {
            this.dropItem(world, Blocks.CHEST);
            this.kaleidoscope$setHasChest(false);
        }
    }

    @Nullable
    @Override
    public ScreenHandler createMenu(int syncId, PlayerInventory inventory, PlayerEntity player) {
        if (this.items == null) {
            return null;
        }
        return new StriderScreenHandler(syncId, inventory, this.items, StriderEntity.class.cast(this));
    }

    @Override
    public Text getDisplayName() {
        return super.getDisplayName();
    }

    @Override
    public void openInventory(PlayerEntity player) {
        if (!KaleidoscopeConfig.ALLOW_STRIDER_EQUIPMENT.get() || !(player instanceof ServerPlayerEntity serverPlayer) || (this.hasPassengers() && !this.hasPassenger(player))) {
            return;
        }
        if (serverPlayer.currentScreenHandler != serverPlayer.playerScreenHandler) {
            serverPlayer.closeHandledScreen();
        }
        serverPlayer.incrementScreenHandlerSyncId();
        ServerPlayNetworking.send(serverPlayer, new OpenStriderScreenS2CPacket(serverPlayer.screenHandlerSyncId, this.getId()));
        serverPlayer.currentScreenHandler = new StriderScreenHandler(serverPlayer.screenHandlerSyncId, serverPlayer.getInventory(), this.items, StriderEntity.class.cast(this));
        serverPlayer.onScreenHandlerOpened(serverPlayer.currentScreenHandler);
    }
}
