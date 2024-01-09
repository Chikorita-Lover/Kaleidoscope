package com.chikoritalover.kaleidoscope.screen;

import com.chikoritalover.kaleidoscope.Kaleidoscope;
import com.chikoritalover.kaleidoscope.registry.KaleidoscopeBlocks;
import com.chikoritalover.kaleidoscope.registry.KaleidoscopeSoundEvents;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import net.fabricmc.fabric.api.tag.convention.v1.ConventionalItemTags;
import net.minecraft.block.SkullBlock;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.item.*;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtList;
import net.minecraft.recipe.Ingredient;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.ScreenHandlerContext;
import net.minecraft.screen.slot.Slot;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.stat.Stat;
import net.minecraft.stat.Stats;
import net.minecraft.util.Util;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class FireworksTableScreenHandler extends ScreenHandler {
    private static final int INPUT_START = 0;
    private static final int INPUT_END = 12;
    public static final int OUTPUT_ID = 12;
    private static final int INVENTORY_START = 13;
    private static final int INVENTORY_END = 40;
    private static final int HOTBAR_START = 40;
    private static final int HOTBAR_END = 49;
    private final ScreenHandlerContext context;
    final Slot baseSlot;
    final List<Slot> modifierSlots;
    final List<Slot> colorSlots;
    final Slot outputSlot;
    long lastTakeResultTime;
    private static final Ingredient TYPE_MODIFIER = Ingredient.ofItems(Items.FIRE_CHARGE, Items.FEATHER, Items.GOLD_NUGGET, Items.CREEPER_HEAD);
    private static final Map<Item, FireworkRocketItem.Type> TYPE_MODIFIER_MAP = Util.make(Maps.newHashMap(), typeModifiers -> {
        typeModifiers.put(Items.FIRE_CHARGE, FireworkRocketItem.Type.LARGE_BALL);
        typeModifiers.put(Items.FEATHER, FireworkRocketItem.Type.BURST);
        typeModifiers.put(Items.GOLD_NUGGET, FireworkRocketItem.Type.STAR);
        typeModifiers.put(Items.CREEPER_HEAD, FireworkRocketItem.Type.CREEPER);
    });
    private final Inventory input = new SimpleInventory(12) {

        @Override
        public void markDirty() {
            super.markDirty();
            FireworksTableScreenHandler.this.onContentChanged(this);
        }
    };
    private final Inventory output = new SimpleInventory(1);

    public FireworksTableScreenHandler(int i, PlayerInventory playerInventory) {
        this(i, playerInventory, ScreenHandlerContext.EMPTY);
    }

    public FireworksTableScreenHandler(int i, PlayerInventory playerInventory, ScreenHandlerContext context) {
        super(Kaleidoscope.FIREWORKS_TABLE, i);
        this.context = context;
        this.baseSlot = this.addSlot(new Slot(this.input, 0, 13, 48) {

            @Override
            public boolean canInsert(ItemStack stack) {
                return stack.isOf(Items.FIREWORK_STAR) || stack.isOf(Items.PAPER);
            }

            @Override
            public void onTakeItem(PlayerEntity player, ItemStack stack) {
                if (!this.hasStack()) {
                    for (int i = 4; i < inventory.size(); ++i) {
                        PlayerInventory playerInventory = player.getInventory();
                        if (!(playerInventory.player instanceof ServerPlayerEntity)) continue;
                        playerInventory.offerOrDrop(inventory.removeStack(i));
                    }
                }
                super.onTakeItem(player, stack);
            }
        });
        this.modifierSlots = new ArrayList<>();
        for (i = 0; i < 3; ++i) {
            this.modifierSlots.add(this.addSlot(new Slot(this.input, i + 1, 52 + i * 18, 63) {

                @Override
                public boolean canInsert(ItemStack stack) {
                    return canInsertAsModifier(this.inventory, stack);
                }
            }));
        }
        this.colorSlots = new ArrayList<>();
        for (i = 0; i < 2; ++i) {
            for (int j = 0; j < 4; ++j) {
                this.colorSlots.add(this.addSlot(new Slot(this.input, j + i * 4 + 4, 43 + j * 18, 19 + i * 18) {

                    @Override
                    public boolean canInsert(ItemStack stack) {
                        return canInsertAsColor(this.inventory, stack);
                    }

                    @Override
                    public boolean isEnabled() {
                        return !this.inventory.getStack(0).isEmpty();
                    }
                }));
            }
        }
        this.outputSlot = this.addSlot(new Slot(this.output, 0, 153, 44) {

            @Override
            public boolean canInsert(ItemStack stack) {
                return false;
            }

            @Override
            public void onTakeItem(PlayerEntity player, ItemStack stack) {
                for (Slot slot : FireworksTableScreenHandler.this.slots.subList(FireworksTableScreenHandler.INPUT_START, FireworksTableScreenHandler.INPUT_END)) {
                    if (slot.hasStack()) {
                        slot.takeStack(1);
                    }
                }
                context.run((world, pos) -> {
                    long l = world.getTime();
                    if (FireworksTableScreenHandler.this.lastTakeResultTime != l) {
                        world.playSound(null, pos, KaleidoscopeSoundEvents.BLOCK_FIREWORKS_TABLE_TAKE_RESULT, SoundCategory.BLOCKS, 1.0F, 1.0F);
                        FireworksTableScreenHandler.this.lastTakeResultTime = l;
                    }
                });
                if (player instanceof ServerPlayerEntity serverPlayer) {
                    serverPlayer.incrementStat(Stats.CRAFTED.getOrCreateStat(stack.getItem()));
                }
                super.onTakeItem(player, stack);
            }
        });
        for (i = 0; i < 3; ++i) {
            for (int j = 0; j < 9; ++j) {
                this.addSlot(new Slot(playerInventory, j + i * 9 + 9, 13 + j * 18, 94 + i * 18));
            }
        }
        for (i = 0; i < 9; ++i) {
            this.addSlot(new Slot(playerInventory, i, 13 + i * 18, 152));
        }
    }

    @Override
    public ItemStack quickMove(PlayerEntity player, int slot) {
        ItemStack itemStack = ItemStack.EMPTY;
        Slot slot2 = this.slots.get(slot);
        if (slot2.hasStack()) {
            ItemStack itemStack2 = slot2.getStack();
            itemStack = itemStack2.copy();
            if (slot == this.outputSlot.id) {
                if (!this.insertItem(itemStack2, INVENTORY_START, HOTBAR_END, true)) {
                    return ItemStack.EMPTY;
                }
                slot2.onQuickTransfer(itemStack2, itemStack);
            } else if (slot < INVENTORY_START ? !this.insertItem(itemStack2, INVENTORY_START, HOTBAR_END, false) : (this.getBaseSlot().canInsert(itemStack2) && (!this.getBaseSlot().hasStack() || itemStack2.isOf(this.getBaseSlot().getStack().getItem())) ? !this.insertItem(itemStack2, this.getBaseSlot().id, this.getBaseSlot().id + 1, false) : (canInsertAsModifier(this.getInputs(), itemStack2) ? !this.insertItem(itemStack2, 1, 4, false) : (canInsertAsColor(this.getInputs(), itemStack2) ? !this.insertItem(itemStack2, 4, INPUT_END, false) : (slot < INVENTORY_END ? !this.insertItem(itemStack2, HOTBAR_START, HOTBAR_END, false) : slot < HOTBAR_END && !this.insertItem(itemStack2, INVENTORY_START, INVENTORY_END, false)))))) {
                return ItemStack.EMPTY;
            }
            if (itemStack2.isEmpty()) {
                slot2.setStack(ItemStack.EMPTY);
            } else {
                slot2.markDirty();
            }
            if (itemStack2.getCount() == itemStack.getCount()) {
                return ItemStack.EMPTY;
            }
            slot2.onTakeItem(player, itemStack2);
        }
        return itemStack;
    }

    @Override
    public void onContentChanged(Inventory inventory) {
        super.onContentChanged(inventory);
        if (inventory == this.input) {
            this.updateOutputSlot();
        }
    }

    @Override
    public void onClosed(PlayerEntity player) {
        super.onClosed(player);
        this.context.run((world, pos) -> this.dropInventory(player, this.input));
    }

    @Override
    public boolean canUse(PlayerEntity player) {
        return FireworksTableScreenHandler.canUse(this.context, player, KaleidoscopeBlocks.FIREWORKS_TABLE);
    }

    private void updateOutputSlot() {
        ItemStack itemStack = this.getBaseSlot().getStack();
        ItemStack itemStack2 = ItemStack.EMPTY;
        if (!itemStack.isEmpty() && !this.hasInvalidInputs()) {
            if (itemStack.isOf(Items.FIREWORK_STAR) && (this.modifierSlots.stream().anyMatch(Slot::hasStack) || this.colorSlots.stream().anyMatch(Slot::hasStack))) {
                itemStack2 = itemStack.copyWithCount(1);
                NbtCompound nbtCompound = itemStack2.getOrCreateSubNbt("Explosion");
                FireworkRocketItem.Type type = nbtCompound.contains("Type") ? FireworkRocketItem.Type.byId(nbtCompound.getByte("Type")) : FireworkRocketItem.Type.SMALL_BALL;
                for (Slot modifierSlot : this.modifierSlots) {
                    ItemStack itemStack3 = modifierSlot.getStack();
                    if (itemStack3.isEmpty()) continue;
                    if (TYPE_MODIFIER.test(itemStack3)) {
                        type = TYPE_MODIFIER_MAP.get(itemStack3.getItem());
                        continue;
                    }
                    if (itemStack3.isOf(Items.GLOWSTONE_DUST)) {
                        nbtCompound.putBoolean("Flicker", true);
                        continue;
                    }
                    if (itemStack3.isOf(Items.DIAMOND)) {
                        nbtCompound.putBoolean("Trail", true);
                    }
                }
                if (this.colorSlots.stream().anyMatch(Slot::hasStack)) {
                    ArrayList<Integer> list = Lists.newArrayList();
                    for (Slot colorSlot : this.colorSlots) {
                        ItemStack itemStack3 = colorSlot.getStack();
                        if (itemStack3.getItem() instanceof DyeItem dyeItem) {
                            list.add(dyeItem.getColor().getFireworkColor());
                        }
                    }
                    nbtCompound.putIntArray(nbtCompound.contains("Colors") ? "FadeColors" : "Colors", list);
                }
                nbtCompound.putByte("Type", (byte) type.getId());
            } else if (itemStack.isOf(Items.PAPER) && this.modifierSlots.stream().anyMatch(slot -> slot.getStack().isOf(Items.GUNPOWDER))) {
                itemStack2 = new ItemStack(Items.FIREWORK_ROCKET, 3);
                NbtCompound nbtCompound = itemStack2.getOrCreateSubNbt("Fireworks");
                NbtList nbtList = new NbtList();
                int i = 0;
                for (Slot modifierSlot : this.modifierSlots) {
                    ItemStack itemStack3 = modifierSlot.getStack();
                    if (itemStack3.isOf(Items.GUNPOWDER)) {
                        ++i;
                    }
                }
                for (Slot colorSlot : this.colorSlots) {
                    NbtCompound nbtCompound2;
                    ItemStack itemStack3 = colorSlot.getStack();
                    if (itemStack3.isOf(Items.FIREWORK_STAR) && (nbtCompound2 = itemStack3.getSubNbt("Explosion")) != null) {
                        nbtList.add(nbtCompound2);
                    }
                }
                nbtCompound.putByte("Flight", (byte) i);
                if (!nbtList.isEmpty()) {
                    nbtCompound.put("Explosions", nbtList);
                }
            }
        }
        if (!ItemStack.areEqual(itemStack2, this.getOutputSlot().getStack())) {
            this.getOutputSlot().setStackNoCallbacks(itemStack2);
        }
    }

    public Slot getBaseSlot() {
        return this.baseSlot;
    }

    public Slot getOutputSlot() {
        return this.outputSlot;
    }

    public Inventory getInputs() {
        return this.input;
    }

    public static boolean canInsertAsModifier(Inventory inventory, ItemStack stack) {
        ItemStack itemStack;
        return (itemStack = inventory.getStack(0)).isOf(Items.PAPER) && stack.isOf(Items.GUNPOWDER) || itemStack.isOf(Items.FIREWORK_STAR) && ((TYPE_MODIFIER.test(stack) || stack.isOf(Items.GLOWSTONE_DUST) || stack.isOf(Items.DIAMOND)));
    }

    public static boolean canInsertAsColor(Inventory inventory, ItemStack stack) {
        ItemStack itemStack = inventory.getStack(0);
        return !itemStack.isEmpty() && (itemStack.isOf(Items.PAPER) ? stack.isOf(Items.FIREWORK_STAR) : stack.getItem() instanceof DyeItem);
    }

    public boolean hasInvalidInputs() {
        return this.getInputError() != ErrorType.NONE;
    }

    public ErrorType getInputError() {
        ItemStack itemStack;
        if (this.slots.subList(INPUT_START, INPUT_END).stream().anyMatch(slot -> slot.hasStack() && !slot.canInsert(slot.getStack()))) {
            return ErrorType.INVALID_INPUT;
        }
        if ((itemStack = this.getBaseSlot().getStack()).isOf(Items.PAPER)) {
            if (this.modifierSlots.stream().noneMatch(slot -> slot.getStack().isOf(Items.GUNPOWDER))) {
                return ErrorType.MISSING_GUNPOWDER;
            }
            if (this.colorSlots.stream().anyMatch(slot -> slot.hasStack() && (!slot.getStack().hasNbt() || !slot.getStack().getNbt().contains("Explosion")))) {
                return ErrorType.NO_EFFECT;
            }
        } else if (itemStack.isOf(Items.FIREWORK_STAR)) {
            if (this.modifierSlots.stream().filter(slot -> TYPE_MODIFIER.test(slot.getStack())).count() > 1 || this.modifierSlots.stream().filter(slot -> slot.getStack().isOf(Items.GLOWSTONE_DUST)).count() > 1 || this.modifierSlots.stream().filter(slot -> slot.getStack().isOf(Items.DIAMOND)).count() > 1) {
                return ErrorType.EXTRA_MODIFIER;
            }
            if (this.colorSlots.stream().noneMatch(slot -> slot.getStack().getItem() instanceof DyeItem) && (!itemStack.hasNbt() || !itemStack.getNbt().contains("Explosion") || !itemStack.getNbt().getCompound("Explosion").contains("Colors"))) {
                return ErrorType.MISSING_DYE;
            }
        }
        return ErrorType.NONE;
    }

    public enum ErrorType {
        NONE,
        EXTRA_MODIFIER,
        INVALID_INPUT,
        MISSING_DYE,
        MISSING_GUNPOWDER,
        NO_EFFECT
    }
}
