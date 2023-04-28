package com.chikoritalover.kaleidoscope.registry;

import com.chikoritalover.kaleidoscope.Kaleidoscope;
import com.chikoritalover.kaleidoscope.enchantment.IllagerBaneEnchantment;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class KaleidoscopeEnchantments {
    public static final Enchantment ILLAGER_BANE;

    public KaleidoscopeEnchantments() {
    }

    private static Enchantment register(String name, Enchantment enchantment) {
        return Registry.register(Registries.ENCHANTMENT, new Identifier(Kaleidoscope.MODID, name), enchantment);
    }

    static {
        ILLAGER_BANE = register("illager_bane", new IllagerBaneEnchantment(Enchantment.Rarity.VERY_RARE, EquipmentSlot.MAINHAND));
    }

    public static void register() {}
}
