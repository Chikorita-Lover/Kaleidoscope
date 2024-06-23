package net.chikorita_lover.kaleidoscope.mixin.entity;

import net.chikorita_lover.kaleidoscope.entity.Chestable;
import net.chikorita_lover.kaleidoscope.network.OpenStriderScreenS2CPacket;
import net.chikorita_lover.kaleidoscope.registry.KaleidoscopeSoundEvents;
import net.chikorita_lover.kaleidoscope.screen.StriderScreenHandler;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.fabricmc.fabric.api.tag.convention.v2.ConventionalItemTags;
import net.minecraft.component.EnchantmentEffectComponentTypes;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.RideableInventory;
import net.minecraft.entity.SaddledComponent;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.passive.StriderEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtElement;
import net.minecraft.nbt.NbtList;
import net.minecraft.screen.NamedScreenHandlerFactory;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.event.GameEvent;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(StriderEntity.class)
public abstract class StriderEntityMixin extends AnimalEntity implements Chestable, NamedScreenHandlerFactory, RideableInventory {
    @Unique
    private static final TrackedData<Boolean> CHEST = DataTracker.registerData(StriderEntity.class, TrackedDataHandlerRegistry.BOOLEAN);
    @Shadow
    @Final
    private SaddledComponent saddledComponent;
    @Unique
    private SimpleInventory items;

    protected StriderEntityMixin(EntityType<? extends AnimalEntity> entityType, World world) {
        super(entityType, world);
    }

    @Shadow
    public abstract boolean isSaddled();

    @Inject(method = "initDataTracker", at = @At("TAIL"))
    private void addChestDataTracker(DataTracker.Builder builder, CallbackInfo ci) {
        builder.add(CHEST, false);
    }

    @Inject(method = "writeCustomDataToNbt", at = @At("TAIL"))
    private void writeChestDataToNbt(NbtCompound nbt, CallbackInfo ci) {
        nbt.putBoolean("Chested", this.kaleidoscope$hasChest());
        if (this.kaleidoscope$hasChest()) {
            NbtList nbtList = new NbtList();
            for (int slot = 1; slot < this.items.size(); ++slot) {
                ItemStack stack = this.items.getStack(slot);
                if (stack.isEmpty()) {
                    continue;
                }
                NbtCompound nbtCompound = new NbtCompound();
                nbtCompound.putByte("Slot", (byte) (slot - 1));
                nbtList.add(stack.encode(this.getRegistryManager(), nbtCompound));
            }
            nbt.put("Items", nbtList);
        }
    }

    @Inject(method = "readCustomDataFromNbt", at = @At("TAIL"))
    private void readChestDataFromNbt(NbtCompound nbt, CallbackInfo ci) {
        this.kaleidoscope$setHasChest(nbt.getBoolean("Chested"));
        this.kaleidoscope$onChestedStatusChanged();
        if (this.kaleidoscope$hasChest()) {
            NbtList nbtList = nbt.getList("Items", NbtElement.COMPOUND_TYPE);
            for (int i = 0; i < nbtList.size(); ++i) {
                NbtCompound nbtCompound = nbtList.getCompound(i);
                int j = nbtCompound.getByte("Slot") % 256;
                if (j >= this.items.size() - 1) {
                    continue;
                }
                this.items.setStack(j + 1, ItemStack.fromNbt(this.getRegistryManager(), nbtCompound).orElse(ItemStack.EMPTY));
            }
        }
    }

    @Inject(method = "dropInventory", at = @At("TAIL"))
    private void dropChestContents(CallbackInfo ci) {
        if (this.kaleidoscope$hasChest()) {
            this.dropStack(new ItemStack(Items.CHEST));
        }
        if (this.items == null) {
            return;
        }
        for (int i = 0; i < this.items.size(); ++i) {
            ItemStack stack = this.items.getStack(i);
            if (stack.isEmpty() || EnchantmentHelper.hasAnyEnchantmentsWith(stack, EnchantmentEffectComponentTypes.PREVENT_EQUIPMENT_DROP)) {
                continue;
            }
            this.dropStack(stack);
        }
    }

    @Inject(method = "interactMob", at = @At("HEAD"), cancellable = true)
    private void tryInteract(PlayerEntity player, Hand hand, CallbackInfoReturnable<ActionResult> cir) {
        if (this.hasPassengers() || this.isBaby()) {
            return;
        }
        ItemStack stack = player.getStackInHand(hand);
        if (!this.kaleidoscope$hasChest() && stack.isOf(Items.CHEST)) {
            this.kaleidoscope$addChest(player, stack);
            cir.setReturnValue(ActionResult.success(this.getWorld().isClient()));
        } else if (this.isSaddled() && stack.isIn(ConventionalItemTags.SHEAR_TOOLS)) {
            this.saddledComponent.setSaddled(false);
            this.playSound(KaleidoscopeSoundEvents.ENTITY_STRIDER_SHEAR);
            this.dropStack(new ItemStack(Items.SADDLE), this.getHeight());
            this.emitGameEvent(GameEvent.SHEAR, player);
            if (!this.getWorld().isClient()) {
                stack.damage(1, player, LivingEntity.getSlotForHand(hand));
            }
            cir.setReturnValue(ActionResult.success(this.getWorld().isClient()));
        } else if (this.kaleidoscope$hasChest() && (!this.isSaddled() && !stack.isOf(Items.SADDLE) || player.shouldCancelInteraction())) {
            this.openInventory(player);
            cir.setReturnValue(ActionResult.success(this.getWorld().isClient()));
        }
    }

    @Override
    public void kaleidoscope$onChestedStatusChanged() {
        SimpleInventory simpleInventory = this.items;
        this.items = new SimpleInventory(this.kaleidoscope$getInventorySize());
        if (simpleInventory != null) {
            int size = Math.min(simpleInventory.size(), this.items.size());
            for (int slot = 0; slot < size; ++slot) {
                ItemStack itemStack = simpleInventory.getStack(slot);
                if (itemStack.isEmpty()) {
                    continue;
                }
                this.items.setStack(slot, itemStack.copy());
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
        if (!(player instanceof ServerPlayerEntity serverPlayer) || (this.hasPassengers() && !this.hasPassenger(player))) {
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
