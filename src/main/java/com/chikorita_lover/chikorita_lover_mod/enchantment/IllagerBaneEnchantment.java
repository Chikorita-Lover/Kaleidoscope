package com.chikorita_lover.chikorita_lover_mod.enchantment;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.entity.EntityGroup;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.AxeItem;
import net.minecraft.item.ItemStack;

public class IllagerBaneEnchantment extends Enchantment {
    private static final int BASE_POWER = 5;
    private static final int POWER_PER_LEVEL = 8;
    private static final int MIN_MAX_POWER_DIFFERENCE = 20;

    public IllagerBaneEnchantment(Enchantment.Rarity weight, EquipmentSlot... slots) {
        super(weight, EnchantmentTarget.WEAPON, slots);
    }

    public int getMinPower(int level) {
        return BASE_POWER + (level - 1) * POWER_PER_LEVEL;
    }

    public int getMaxPower(int level) {
        return this.getMinPower(level) + MIN_MAX_POWER_DIFFERENCE;
    }

    public boolean isTreasure() {
        return true;
    }

    public boolean isAvailableForEnchantedBookOffer() {
        return false;
    }

    public int getMaxLevel() {
        return 5;
    }

    public float getAttackDamage(int level, EntityGroup group) {
        return group == EntityGroup.ILLAGER ? (float)level * 2.5F : 0.0F;
    }

    public boolean canAccept(Enchantment other) {
        return !(other instanceof net.minecraft.enchantment.DamageEnchantment);
    }

    public boolean isAcceptableItem(ItemStack stack) {
        return stack.getItem() instanceof AxeItem || super.isAcceptableItem(stack);
    }
}
