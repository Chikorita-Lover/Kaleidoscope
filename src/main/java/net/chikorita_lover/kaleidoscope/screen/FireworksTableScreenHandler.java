package net.chikorita_lover.kaleidoscope.screen;

import it.unimi.dsi.fastutil.ints.IntArrayList;
import it.unimi.dsi.fastutil.ints.IntList;
import net.chikorita_lover.kaleidoscope.Kaleidoscope;
import net.chikorita_lover.kaleidoscope.item.FireworkShellItem;
import net.chikorita_lover.kaleidoscope.registry.KaleidoscopeBlocks;
import net.chikorita_lover.kaleidoscope.registry.KaleidoscopeItemTags;
import net.chikorita_lover.kaleidoscope.registry.KaleidoscopeSoundEvents;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.FireworkExplosionComponent;
import net.minecraft.component.type.FireworksComponent;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.item.DyeItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.ScreenHandlerContext;
import net.minecraft.screen.slot.Slot;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.sound.SoundCategory;
import net.minecraft.stat.Stats;

import java.util.ArrayList;
import java.util.List;

public class FireworksTableScreenHandler extends ScreenHandler {
    public static final int OUTPUT_ID = 12;
    private static final int INPUT_START = 0;
    private static final int INPUT_END = 12;
    private static final int INVENTORY_START = 13;
    private static final int INVENTORY_END = 40;
    private static final int HOTBAR_START = 40;
    private static final int HOTBAR_END = 49;
    private final Slot baseSlot;
    private final List<Slot> modifierSlots;
    private final List<Slot> colorSlots;
    private final Slot outputSlot;
    private final ScreenHandlerContext context;
    private final Inventory output = new SimpleInventory(1);
    private final Inventory input;
    private long lastTakeResultTime;

    public FireworksTableScreenHandler(int syncId, PlayerInventory playerInventory) {
        this(syncId, playerInventory, ScreenHandlerContext.EMPTY);
    }

    public FireworksTableScreenHandler(int syncId, PlayerInventory playerInventory, ScreenHandlerContext context) {
        super(Kaleidoscope.FIREWORKS_TABLE, syncId);
        this.context = context;
        this.input = new SimpleInventory(12) {
            @Override
            public void markDirty() {
                super.markDirty();
                FireworksTableScreenHandler.this.onContentChanged(this);
            }
        };
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
        for (syncId = 0; syncId < 3; ++syncId) {
            this.modifierSlots.add(this.addSlot(new Slot(this.input, syncId + 1, 52 + syncId * 18, 63) {

                @Override
                public boolean canInsert(ItemStack stack) {
                    return canInsertAsModifier(this.inventory, this.id, stack);
                }
            }));
        }
        this.colorSlots = new ArrayList<>();
        for (syncId = 0; syncId < 2; ++syncId) {
            for (int j = 0; j < 4; ++j) {
                this.colorSlots.add(this.addSlot(new Slot(this.input, j + syncId * 4 + 4, 43 + j * 18, 19 + syncId * 18) {

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
                // TODO return color slot inputs to player when base slot becomes empty due to constructing
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
        for (syncId = 0; syncId < 3; ++syncId) {
            for (int j = 0; j < 9; ++j) {
                this.addSlot(new Slot(playerInventory, j + syncId * 9 + 9, 13 + j * 18, 94 + syncId * 18));
            }
        }
        for (syncId = 0; syncId < 9; ++syncId) {
            this.addSlot(new Slot(playerInventory, syncId, 13 + syncId * 18, 152));
        }
    }

    public static boolean canInsertAsModifier(Inventory inventory, int slot, ItemStack stack) {
        if (slot < 1 || slot > 3) return false;
        ItemStack itemStack;
        return (itemStack = inventory.getStack(0)).isOf(Items.PAPER) && stack.isOf(Items.GUNPOWDER) || itemStack.isOf(Items.FIREWORK_STAR) && ((slot == 1 && stack.isIn(KaleidoscopeItemTags.FIREWORK_SHELLS)) || (slot == 2 && stack.isOf(Items.GLOWSTONE_DUST)) || (slot == 3 && stack.isOf(Items.DIAMOND)));
    }

    public static boolean isModifier(Inventory inventory, ItemStack stack) {
        ItemStack itemStack;
        return (itemStack = inventory.getStack(0)).isOf(Items.PAPER) && stack.isOf(Items.GUNPOWDER) || itemStack.isOf(Items.FIREWORK_STAR) && (stack.isIn(KaleidoscopeItemTags.FIREWORK_SHELLS) || stack.isOf(Items.GLOWSTONE_DUST) || stack.isOf(Items.DIAMOND));
    }

    public static boolean canInsertAsColor(Inventory inventory, ItemStack stack) {
        ItemStack itemStack = inventory.getStack(0);
        return !itemStack.isEmpty() && (itemStack.isOf(Items.PAPER) ? stack.isOf(Items.FIREWORK_STAR) : stack.getItem() instanceof DyeItem);
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
            } else if (slot < INVENTORY_START ? !this.insertItem(itemStack2, INVENTORY_START, HOTBAR_END, false) : (this.getBaseSlot().canInsert(itemStack2) && (!this.getBaseSlot().hasStack() || itemStack2.isOf(this.getBaseSlot().getStack().getItem())) ? !this.insertItem(itemStack2, this.getBaseSlot().id, this.getBaseSlot().id + 1, false) : (isModifier(this.getInputs(), itemStack2) ? !this.insertItem(itemStack2, 1, 4, false) : (canInsertAsColor(this.getInputs(), itemStack2) ? !this.insertItem(itemStack2, 4, INPUT_END, false) : (slot < INVENTORY_END ? !this.insertItem(itemStack2, HOTBAR_START, HOTBAR_END, false) : slot < HOTBAR_END && !this.insertItem(itemStack2, INVENTORY_START, INVENTORY_END, false)))))) {
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
                itemStack2 = new ItemStack(itemStack.getItem());
                FireworkExplosionComponent component = itemStack.get(DataComponentTypes.FIREWORK_EXPLOSION);
                FireworkExplosionComponent.Type shape = component != null ? component.shape() : FireworkExplosionComponent.Type.SMALL_BALL;
                boolean hasTrail = false;
                boolean hasTwinkle = false;
                for (Slot modifierSlot : this.modifierSlots) {
                    ItemStack itemStack3 = modifierSlot.getStack();
                    if (itemStack3.isEmpty()) continue;
                    if (itemStack3.isIn(KaleidoscopeItemTags.FIREWORK_SHELLS) && itemStack3.getItem() instanceof FireworkShellItem fireworkShellItem) {
                        shape = fireworkShellItem.getShape();
                        continue;
                    }
                    if (itemStack3.isOf(Items.DIAMOND)) {
                        hasTrail = true;
                        continue;
                    }
                    if (itemStack3.isOf(Items.GLOWSTONE_DUST)) {
                        hasTwinkle = true;
                    }
                }
                IntList colors = IntList.of();
                IntList fadeColors = IntList.of();
                if (this.colorSlots.stream().anyMatch(Slot::hasStack)) {
                    IntArrayList integers = new IntArrayList();
                    for (Slot colorSlot : this.colorSlots) {
                        if (colorSlot.getStack().getItem() instanceof DyeItem dyeItem) {
                            integers.add(dyeItem.getColor().getFireworkColor());
                        }
                    }
                    if (component != null && !component.colors().isEmpty()) {
                        colors = component.colors();
                        fadeColors = integers;
                    } else {
                        colors = integers;
                    }
                }
                itemStack2.set(DataComponentTypes.FIREWORK_EXPLOSION, new FireworkExplosionComponent(shape, colors, fadeColors, hasTrail, hasTwinkle));
            } else if (itemStack.isOf(Items.PAPER) && this.modifierSlots.stream().anyMatch(slot -> slot.getStack().isOf(Items.GUNPOWDER))) {
                itemStack2 = new ItemStack(Items.FIREWORK_ROCKET, 3);
                ArrayList<FireworkExplosionComponent> explosions = new ArrayList<>();
                int flightDuration = 0;
                for (Slot modifierSlot : this.modifierSlots) {
                    if (modifierSlot.getStack().isOf(Items.GUNPOWDER)) {
                        ++flightDuration;
                    }
                }
                for (Slot colorSlot : this.colorSlots) {
                    ItemStack colorStack = colorSlot.getStack();
                    if (colorStack.isOf(Items.FIREWORK_STAR) && colorStack.contains(DataComponentTypes.FIREWORK_EXPLOSION)) {
                        explosions.add(colorStack.get(DataComponentTypes.FIREWORK_EXPLOSION));
                    }
                }
                FireworksComponent component = new FireworksComponent(flightDuration, explosions);
                itemStack2.set(DataComponentTypes.FIREWORKS, component);
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
            if (this.colorSlots.stream().anyMatch(slot -> slot.hasStack() && !slot.getStack().contains(DataComponentTypes.FIREWORK_EXPLOSION))) {
                return ErrorType.NO_EFFECT;
            }
        } else if (itemStack.isOf(Items.FIREWORK_STAR)) {
            if (this.modifierSlots.stream().filter(slot -> slot.getStack().isIn(KaleidoscopeItemTags.FIREWORK_SHELLS)).count() > 1 || this.modifierSlots.stream().filter(slot -> slot.getStack().isOf(Items.GLOWSTONE_DUST)).count() > 1 || this.modifierSlots.stream().filter(slot -> slot.getStack().isOf(Items.DIAMOND)).count() > 1) {
                return ErrorType.EXTRA_MODIFIER;
            }
            if (this.colorSlots.stream().noneMatch(slot -> slot.getStack().getItem() instanceof DyeItem) && (!itemStack.contains(DataComponentTypes.FIREWORK_EXPLOSION) || itemStack.get(DataComponentTypes.FIREWORK_EXPLOSION).colors().isEmpty())) {
                return ErrorType.MISSING_DYE;
            }
        }
        return ErrorType.NONE;
    }

    public enum ErrorType {
        NONE, EXTRA_MODIFIER, INVALID_INPUT, MISSING_DYE, MISSING_GUNPOWDER, NO_EFFECT
    }


}