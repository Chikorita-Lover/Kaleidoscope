package com.chikorita_lover.chikorita_lover_mod.registry;

import net.fabricmc.fabric.api.object.builder.v1.trade.TradeOfferHelper;
import net.minecraft.block.Blocks;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentLevelEntry;
import net.minecraft.entity.Entity;
import net.minecraft.item.EnchantedBookItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.random.Random;
import net.minecraft.village.TradeOffer;
import net.minecraft.village.TradeOffers;
import net.minecraft.village.VillagerProfession;

public class ModTradeOffers {
    public static void register() {
        TradeOfferHelper.registerVillagerOffers(VillagerProfession.ARMORER, 3, factories -> {
            factories.add(new TradeOffers.SellItemFactory(ModItems.CHAINMAIL_HORSE_ARMOR, 3, 1, 12, 10));
        });
        TradeOfferHelper.registerVillagerOffers(VillagerProfession.MASON, 3, factories -> {
            factories.add(new TradeOffers.BuyForOneEmeraldFactory(Blocks.CALCITE, 16, 16, 20));
            factories.add(new TradeOffers.SellItemFactory(ModBlocks.POLISHED_CALCITE, 1, 4, 16, 10));
        });
        TradeOfferHelper.registerVillagerOffers(VillagerProfession.MASON, 5, factories -> {
            factories.add(new TradeOffers.SellItemFactory(ModBlocks.LAPIS_LAZULI_TILES, 1, 1, 12, 30));
        });
        TradeOfferHelper.registerVillagerOffers(VillagerProfession.LIBRARIAN, 5, factories -> {
            factories.add(new IllagerBaneFactory(30));
        });
    }

    public static class IllagerBaneFactory implements TradeOffers.Factory {
        private final int experience;

        public IllagerBaneFactory(int experience) {
            this.experience = experience;
        }

        public TradeOffer create(Entity entity, Random random) {
            Enchantment enchantment = ModEnchantments.ILLAGER_BANE;
            int i = MathHelper.nextInt(random, enchantment.getMinLevel(), enchantment.getMaxLevel());
            ItemStack itemStack = EnchantedBookItem.forEnchantment(new EnchantmentLevelEntry(enchantment, i));
            int j = 2 + random.nextInt(5 + i * 10) + 3 * i;

            if (j > 64) {
                j = 64;
            }

            return new TradeOffer(new ItemStack(Items.EMERALD, j), new ItemStack(Items.BOOK), itemStack, 12, this.experience, 0.2F);
        }
    }
}
