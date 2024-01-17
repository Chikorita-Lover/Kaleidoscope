package com.chikoritalover.kaleidoscope.block.entity;

import com.chikoritalover.kaleidoscope.registry.KaleidoscopeBlockEntities;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtElement;
import net.minecraft.network.packet.s2c.play.BlockEntityUpdateS2CPacket;
import net.minecraft.potion.PotionUtil;
import net.minecraft.util.math.BlockPos;

import java.util.Arrays;
import java.util.List;

public class PotionCauldronBlockEntity extends BlockEntity {
    public static final String POTION_ITEM_KEY = "PotionItem";
    private static final List<String> IRRELEVANT_POTION_NBT_KEYS = Arrays.asList("AttributeModifiers", "BlockEntityTag", "BlockStateTag", "CanDestroy", "CanPlaceOn", "CustomModelData", "Damage", "display", "Enchantments", "HideFlags", "RepairCost", "StoredEnchantments", "Unbreakable");
    private ItemStack stack = ItemStack.EMPTY;

    public PotionCauldronBlockEntity(BlockPos pos, BlockState state) {
        super(KaleidoscopeBlockEntities.POTION_CAULDRON, pos, state);
    }

    @Override
    public void readNbt(NbtCompound nbt) {
        super.readNbt(nbt);
        if (nbt.contains(POTION_ITEM_KEY, NbtElement.COMPOUND_TYPE)) {
            this.setPotion(ItemStack.fromNbt(nbt.getCompound(POTION_ITEM_KEY)));
        }
    }

    @Override
    protected void writeNbt(NbtCompound nbt) {
        super.writeNbt(nbt);
        if (!this.getPotion().isEmpty()) {
            nbt.put(POTION_ITEM_KEY, this.getPotion().writeNbt(new NbtCompound()));
        }
    }

    @Override
    public BlockEntityUpdateS2CPacket toUpdatePacket() {
        return BlockEntityUpdateS2CPacket.create(this);
    }

    @Override
    public NbtCompound toInitialChunkDataNbt() {
        return this.createNbt();
    }

    public int getColor() {
        return PotionUtil.getColor(this.getPotion());
    }

    public static NbtCompound removeIrrelevantNbtKeys(NbtCompound compound) {
        for (String string : IRRELEVANT_POTION_NBT_KEYS) {
            compound.remove(string);
        }
        return compound;
    }

    public ItemStack getPotion() {
        return this.stack;
    }

    public void setPotion(ItemStack stack) {
        this.stack = stack.copy();
        this.stack.setCount(1);
        if (this.stack.hasNbt()) removeIrrelevantNbtKeys(this.stack.getNbt());
        this.markDirty();
    }

    public List<StatusEffectInstance> getStatusEffects() {
        ItemStack stack = ((PotionCauldronBlockEntity) world.getBlockEntity(pos)).getPotion();
        List<StatusEffectInstance> effectInstances = PotionUtil.getPotionEffects(stack);
        PotionUtil.getCustomPotionEffects(stack.getNbt(), effectInstances);
        return effectInstances;
    }
}
