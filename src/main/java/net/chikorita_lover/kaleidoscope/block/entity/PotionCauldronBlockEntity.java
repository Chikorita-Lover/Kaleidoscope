package net.chikorita_lover.kaleidoscope.block.entity;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.PotionContentsComponent;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtElement;
import net.minecraft.network.packet.s2c.play.BlockEntityUpdateS2CPacket;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.util.math.BlockPos;

import java.util.List;

public class PotionCauldronBlockEntity extends BlockEntity {
    public static final String POTION_ITEM_KEY = "PotionItem";
    private ItemStack potionStack = ItemStack.EMPTY;

    public PotionCauldronBlockEntity(BlockPos pos, BlockState state) {
        super(KaleidoscopeBlockEntityTypes.POTION_CAULDRON, pos, state);
    }

    @Override
    protected void readNbt(NbtCompound nbt, RegistryWrapper.WrapperLookup registryLookup) {
        super.readNbt(nbt, registryLookup);
        if (nbt.contains(POTION_ITEM_KEY, NbtElement.COMPOUND_TYPE)) {
            this.potionStack = ItemStack.fromNbt(registryLookup, nbt.getCompound(POTION_ITEM_KEY)).orElse(ItemStack.EMPTY);
        } else {
            this.potionStack = ItemStack.EMPTY;
        }
        this.updateListeners();
    }

    @Override
    protected void writeNbt(NbtCompound nbt, RegistryWrapper.WrapperLookup registryLookup) {
        super.writeNbt(nbt, registryLookup);
        if (!this.getStack().isEmpty()) {
            nbt.put(POTION_ITEM_KEY, this.getStack().encode(registryLookup));
        }
    }

    @Override
    public BlockEntityUpdateS2CPacket toUpdatePacket() {
        return BlockEntityUpdateS2CPacket.create(this);
    }

    @Override
    public NbtCompound toInitialChunkDataNbt(RegistryWrapper.WrapperLookup registryLookup) {
        return this.createNbt(registryLookup);
    }

    public ItemStack getStack() {
        return this.potionStack;
    }

    public void setStack(ItemStack stack) {
        this.potionStack = new ItemStack(Items.POTION);
        this.potionStack.set(DataComponentTypes.POTION_CONTENTS, stack.get(DataComponentTypes.POTION_CONTENTS));
        this.markDirty();
        this.updateListeners();
    }

    public Iterable<StatusEffectInstance> getEffects() {
        return this.getStack().contains(DataComponentTypes.POTION_CONTENTS) ? this.getStack().get(DataComponentTypes.POTION_CONTENTS).getEffects() : List.of();
    }

    public int getColor() {
        return PotionContentsComponent.getColor(this.getEffects());
    }

    private void updateListeners() {
        if (this.getWorld() != null) {
            this.getWorld().updateListeners(this.getPos(), this.getCachedState(), this.getCachedState(), Block.NOTIFY_LISTENERS);
        }
    }
}
