package net.chikorita_lover.kaleidoscope.screen;

import it.unimi.dsi.fastutil.ints.IntArrayList;
import it.unimi.dsi.fastutil.ints.IntList;
import net.chikorita_lover.kaleidoscope.block.KaleidoscopeBlocks;
import net.chikorita_lover.kaleidoscope.item.FireworkShellItem;
import net.chikorita_lover.kaleidoscope.registry.KaleidoscopeSoundEvents;
import net.chikorita_lover.kaleidoscope.registry.tag.KaleidoscopeItemTags;
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
import net.minecraft.text.Text;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class FireworksTableScreenHandler extends ScreenHandler {
    protected static final int INPUT_START = 0;
    protected static final int INPUT_END = 12;
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

    public FireworksTableScreenHandler(int syncId, PlayerInventory inventory, ScreenHandlerContext context) {
        super(KaleidoscopeScreenHandlerTypes.FIREWORKS_TABLE, syncId);
        this.context = context;
        this.input = new SimpleInventory(12) {
            @Override
            public void markDirty() {
                super.markDirty();
                FireworksTableScreenHandler.this.onContentChanged(this);
            }
        };
        this.baseSlot = this.addSlot(new Slot(this.input, 0, 8, 35) {

            @Override
            public boolean canInsert(ItemStack stack) {
                return stack.isIn(KaleidoscopeItemTags.FIREWORK_STAR_BASES) || stack.isOf(Items.PAPER);
            }

            @Override
            public void onTakeItem(PlayerEntity player, ItemStack stack) {
                if (!this.hasStack()) {
                    for (int i = 4; i < inventory.size(); ++i) {
                        PlayerInventory playerInventory = player.getInventory();
                        if (!(playerInventory.player instanceof ServerPlayerEntity)) {
                            continue;
                        }
                        playerInventory.offerOrDrop(inventory.removeStack(i));
                    }
                }
                super.onTakeItem(player, stack);
            }
        });
        this.modifierSlots = new ArrayList<>();
        for (int i = 0; i < 3; ++i) {
            this.modifierSlots.add(this.addSlot(new Slot(this.input, i + 1, 31, 17 + i * 18) {

                @Override
                public boolean canInsert(ItemStack stack) {
                    return canInsertAsModifier(this.inventory, this.id, stack);
                }
            }));
        }
        this.colorSlots = new ArrayList<>();
        for (int i = 0; i < 8; ++i) {
            this.colorSlots.add(this.addSlot(new Slot(this.input, i + 4, 75 + (i % 3) * 18, 17 + (Math.floorDiv(i, 3) * 18)) {

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
        this.outputSlot = this.addSlot(new FireworksTableOutputSlot(this, inventory.player, this.output, 0, 143, 44));
        for (int i = 0; i < 3; ++i) {
            for (int j = 0; j < 9; ++j) {
                this.addSlot(new Slot(inventory, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));
            }
        }
        for (int i = 0; i < 9; ++i) {
            this.addSlot(new Slot(inventory, i, 8 + i * 18, 142));
        }
    }

    public static boolean canInsertAsModifier(Inventory inventory, int slot, ItemStack stack) {
        if (slot < 1 || slot > 3) {
            return false;
        }
        ItemStack baseStack = inventory.getStack(0);
        return baseStack.isOf(Items.PAPER) && stack.isOf(Items.GUNPOWDER) || baseStack.isIn(KaleidoscopeItemTags.FIREWORK_STAR_BASES) && ((slot == 1 && stack.isIn(KaleidoscopeItemTags.FIREWORK_SHELLS)) || (slot == 2 && stack.isOf(Items.GLOWSTONE_DUST)) || (slot == 3 && stack.isOf(Items.DIAMOND)));
    }

    public static boolean isModifier(Inventory inventory, ItemStack stack) {
        ItemStack baseStack = inventory.getStack(0);
        return baseStack.isOf(Items.PAPER) && stack.isOf(Items.GUNPOWDER) || baseStack.isIn(KaleidoscopeItemTags.FIREWORK_STAR_BASES) && (stack.isIn(KaleidoscopeItemTags.FIREWORK_SHELLS) || stack.isOf(Items.GLOWSTONE_DUST) || stack.isOf(Items.DIAMOND));
    }

    public static boolean canInsertAsColor(Inventory inventory, ItemStack stack) {
        ItemStack baseStack = inventory.getStack(0);
        return !baseStack.isEmpty() && (baseStack.isOf(Items.PAPER) ? stack.isOf(Items.FIREWORK_STAR) : stack.getItem() instanceof DyeItem);
    }

    @Override
    public ItemStack quickMove(PlayerEntity player, int slot) {
        ItemStack itemStack = ItemStack.EMPTY;
        Slot slot2 = this.slots.get(slot);
        if (slot2.hasStack()) {
            ItemStack stack = slot2.getStack();
            itemStack = stack.copy();
            if (slot == this.outputSlot.id) {
                // this.context.run((world, pos) -> stack.onCraftByPlayer(world, player, stack.getCount()));
                if (!this.insertItem(stack, INVENTORY_START, HOTBAR_END, true)) {
                    return ItemStack.EMPTY;
                }
                slot2.onQuickTransfer(stack, itemStack);
            } else if (slot < INVENTORY_START ? !this.insertItem(stack, INVENTORY_START, HOTBAR_END, false) : (this.getBaseSlot().canInsert(stack) && (!this.getBaseSlot().hasStack() || stack.isOf(this.getBaseSlot().getStack().getItem())) ? !this.insertItem(stack, this.getBaseSlot().id, this.getBaseSlot().id + 1, false) : (isModifier(this.getInputs(), stack) ? !this.insertItem(stack, 1, 4, false) : (canInsertAsColor(this.getInputs(), stack) ? !this.insertItem(stack, 4, INPUT_END, false) : (slot < INVENTORY_END ? !this.insertItem(stack, HOTBAR_START, HOTBAR_END, false) : slot < HOTBAR_END && !this.insertItem(stack, INVENTORY_START, INVENTORY_END, false)))))) {
                return ItemStack.EMPTY;
            }
            if (stack.isEmpty()) {
                slot2.setStack(ItemStack.EMPTY);
            } else {
                slot2.markDirty();
            }
            if (stack.getCount() == itemStack.getCount()) {
                return ItemStack.EMPTY;
            }
            slot2.onTakeItem(player, stack);
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
        return canUse(this.context, player, KaleidoscopeBlocks.FIREWORKS_TABLE);
    }

    private void updateOutputSlot() {
        ItemStack baseStack = this.getBaseSlot().getStack();
        ItemStack resultStack = ItemStack.EMPTY;
        if (!baseStack.isEmpty() && !this.hasInvalidInputs()) {
            if (baseStack.isIn(KaleidoscopeItemTags.FIREWORK_STAR_BASES) && (this.modifierSlots.stream().anyMatch(Slot::hasStack) || this.colorSlots.stream().anyMatch(Slot::hasStack))) {
                resultStack = new ItemStack(Items.FIREWORK_STAR);
                FireworkExplosionComponent component = baseStack.get(DataComponentTypes.FIREWORK_EXPLOSION);
                FireworkExplosionComponent.Type shape = component != null ? component.shape() : FireworkExplosionComponent.Type.SMALL_BALL;
                boolean hasTrail = component != null && component.hasTrail();
                boolean hasTwinkle = component != null && component.hasTwinkle();
                for (Slot modifierSlot : this.modifierSlots) {
                    ItemStack modifierStack = modifierSlot.getStack();
                    if (modifierStack.isEmpty()) {
                        continue;
                    }
                    if (modifierStack.isIn(KaleidoscopeItemTags.FIREWORK_SHELLS) && modifierStack.getItem() instanceof FireworkShellItem fireworkShell) {
                        shape = fireworkShell.getShape();
                        continue;
                    }
                    if (modifierStack.isOf(Items.DIAMOND)) {
                        hasTrail = true;
                        continue;
                    }
                    if (modifierStack.isOf(Items.GLOWSTONE_DUST)) {
                        hasTwinkle = true;
                    }
                }
                IntList colors = component != null ? component.colors() : IntList.of();
                IntList fadeColors = component != null ? component.fadeColors() : IntList.of();
                if (this.colorSlots.stream().anyMatch(Slot::hasStack)) {
                    IntArrayList integers = new IntArrayList();
                    for (Slot colorSlot : this.colorSlots) {
                        if (colorSlot.getStack().getItem() instanceof DyeItem dyeItem) {
                            integers.add(dyeItem.getColor().getFireworkColor());
                        }
                    }
                    if (!colors.isEmpty()) {
                        fadeColors = integers;
                    } else {
                        colors = integers;
                    }
                }
                resultStack.set(DataComponentTypes.FIREWORK_EXPLOSION, new FireworkExplosionComponent(shape, colors, fadeColors, hasTrail, hasTwinkle));
            } else if (baseStack.isOf(Items.PAPER) && this.modifierSlots.stream().anyMatch(slot -> slot.getStack().isOf(Items.GUNPOWDER))) {
                resultStack = new ItemStack(Items.FIREWORK_ROCKET, 3);
                ArrayList<FireworkExplosionComponent> explosions = new ArrayList<>();
                int flightDuration = (int) this.modifierSlots.stream().filter(slot -> slot.getStack().isOf(Items.GUNPOWDER)).count();
                for (Slot colorSlot : this.colorSlots) {
                    ItemStack colorStack = colorSlot.getStack();
                    if (colorStack.isOf(Items.FIREWORK_STAR) && colorStack.contains(DataComponentTypes.FIREWORK_EXPLOSION)) {
                        explosions.add(colorStack.get(DataComponentTypes.FIREWORK_EXPLOSION));
                    }
                }
                FireworksComponent component = new FireworksComponent(flightDuration, explosions);
                resultStack.set(DataComponentTypes.FIREWORKS, component);
            }
        }
        if (!ItemStack.areEqual(resultStack, this.getOutputSlot().getStack())) {
            this.getOutputSlot().setStackNoCallbacks(resultStack);
        }
    }

    protected void playResultSound() {
        this.context.run((world, pos) -> {
            long l = world.getTime();
            if (this.lastTakeResultTime != l) {
                world.playSound(null, pos, KaleidoscopeSoundEvents.BLOCK_FIREWORKS_TABLE_TAKE_RESULT, SoundCategory.BLOCKS, 1.0F, 1.0F);
                this.lastTakeResultTime = l;
            }
        });
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
        ItemStack baseStack = this.getBaseSlot().getStack();
        if (this.slots.subList(INPUT_START, INPUT_END).stream().anyMatch(slot -> slot.hasStack() && !slot.canInsert(slot.getStack()))) {
            return ErrorType.INVALID_INPUT;
        }
        if (baseStack.isOf(Items.PAPER)) {
            if (this.modifierSlots.stream().noneMatch(slot -> slot.getStack().isOf(Items.GUNPOWDER))) {
                return ErrorType.MISSING_GUNPOWDER;
            }
            if (this.colorSlots.stream().anyMatch(slot -> slot.hasStack() && !slot.getStack().contains(DataComponentTypes.FIREWORK_EXPLOSION))) {
                return ErrorType.NO_EFFECT;
            }
        } else if (baseStack.isIn(KaleidoscopeItemTags.FIREWORK_STAR_BASES)) {
            if (this.colorSlots.stream().noneMatch(slot -> slot.getStack().getItem() instanceof DyeItem) && (!baseStack.contains(DataComponentTypes.FIREWORK_EXPLOSION) || baseStack.get(DataComponentTypes.FIREWORK_EXPLOSION).colors().isEmpty())) {
                return ErrorType.MISSING_DYE;
            }
        }
        return ErrorType.NONE;
    }

    public enum ErrorType {
        NONE(null), INVALID_INPUT("invalid_input_tooltip"), MISSING_DYE("missing_dye_tooltip"), MISSING_GUNPOWDER("missing_gunpowder_tooltip"), NO_EFFECT("no_effect_tooltip");

        final Text tooltip;

        ErrorType(@Nullable String tooltipPath) {
            this.tooltip = tooltipPath != null ? Text.translatable("container.fireworks_table." + tooltipPath) : null;
        }

        public Text getTooltip() {
            return this.tooltip;
        }
    }
}
