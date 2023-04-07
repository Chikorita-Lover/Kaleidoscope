package com.chikoritalover.kaleidoscope.registry;

import com.chikoritalover.kaleidoscope.Kaleidoscope;
import com.chikoritalover.kaleidoscope.enchantment.IllagerBaneEnchantment;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class ModEnchantments {
    public static final Enchantment ILLAGER_BANE;

    public ModEnchantments() {
    }

    private static Enchantment register(String name, Enchantment enchantment) {
        return Registry.register(Registry.ENCHANTMENT, new Identifier(Kaleidoscope.MODID, name), enchantment);
    }

    static {
        ILLAGER_BANE = register("illager_bane", new IllagerBaneEnchantment(Enchantment.Rarity.VERY_RARE, EquipmentSlot.MAINHAND));
    }

    public static void register() {}
}
