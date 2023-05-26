package com.chikoritalover.kaleidoscope.registry;

import com.chikoritalover.kaleidoscope.Kaleidoscope;
import com.google.common.collect.Lists;
import net.fabricmc.fabric.api.object.builder.v1.trade.TradeOfferHelper;
import net.minecraft.block.Blocks;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentLevelEntry;
import net.minecraft.entity.Entity;
import net.minecraft.item.*;
import net.minecraft.util.DyeColor;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.random.Random;
import net.minecraft.village.TradeOffer;
import net.minecraft.village.TradeOffers;
import net.minecraft.village.VillagerProfession;

import java.util.ArrayList;

public class KaleidoscopeTradeOffers {
    public static void register() {
        TradeOfferHelper.registerVillagerOffers(VillagerProfession.ARMORER, 3, factories -> {
            factories.add(new TradeOffers.SellItemFactory(KaleidoscopeItems.CHAINMAIL_HORSE_ARMOR, 3, 1, 12, 10));
        });
        TradeOfferHelper.registerVillagerOffers(VillagerProfession.LEATHERWORKER, 3, factories -> {
            factories.add(new SellDyedBundleFactory(4, 10));
        });
        TradeOfferHelper.registerVillagerOffers(VillagerProfession.LIBRARIAN, 5, factories -> {
            factories.add(new IllagerBaneFactory(30));
        });
        TradeOfferHelper.registerVillagerOffers(VillagerProfession.MASON, 3, factories -> {
            factories.add(new TradeOffers.BuyForOneEmeraldFactory(Blocks.TUFF, 16, 16, 20));
            factories.add(new TradeOffers.SellItemFactory(KaleidoscopeBlocks.POLISHED_TUFF, 1, 4, 16, 10));
        });
        TradeOfferHelper.registerVillagerOffers(Kaleidoscope.GLASSBLOWER, 1, factories -> {
            factories.add(new TradeOffers.BuyForOneEmeraldFactory(Blocks.SAND, 16, 16, 2));
            factories.add(new TradeOffers.SellItemFactory(Blocks.GLASS, 1, 4, 16, 1));
        });
        TradeOfferHelper.registerVillagerOffers(Kaleidoscope.GLASSBLOWER, 2, factories -> {
            factories.add(new TradeOffers.BuyForOneEmeraldFactory(Items.COAL, 15, 16, 10));
            factories.add(new TradeOffers.BuyForOneEmeraldFactory(Items.COPPER_INGOT, 14, 16, 10));
            factories.add(new TradeOffers.SellItemFactory(Items.SPYGLASS, 2, 1, 12, 5));
        });
        TradeOfferHelper.registerVillagerOffers(Kaleidoscope.GLASSBLOWER, 3, factories -> {
            factories.add(new TradeOffers.BuyForOneEmeraldFactory(Items.AMETHYST_SHARD, 10, 12, 20));
            factories.add(new TradeOffers.SellItemFactory(Blocks.REDSTONE_LAMP, 1, 1, 12, 10));
            factories.add(new TradeOffers.SellItemFactory(Blocks.DAYLIGHT_DETECTOR, 1, 1, 12, 10));
        });
        TradeOfferHelper.registerVillagerOffers(Kaleidoscope.GLASSBLOWER, 4, factories -> {
            factories.add(new TradeOffers.SellItemFactory(Blocks.BLACK_STAINED_GLASS, 1, 1, 12, 15));
            factories.add(new TradeOffers.SellItemFactory(Blocks.BLUE_STAINED_GLASS, 1, 1, 12, 15));
            factories.add(new TradeOffers.SellItemFactory(Blocks.BROWN_STAINED_GLASS, 1, 1, 12, 15));
            factories.add(new TradeOffers.SellItemFactory(Blocks.CYAN_STAINED_GLASS, 1, 1, 12, 15));
            factories.add(new TradeOffers.SellItemFactory(Blocks.GRAY_STAINED_GLASS, 1, 1, 12, 15));
            factories.add(new TradeOffers.SellItemFactory(Blocks.GREEN_STAINED_GLASS, 1, 1, 12, 15));
            factories.add(new TradeOffers.SellItemFactory(Blocks.LIGHT_BLUE_STAINED_GLASS, 1, 1, 12, 15));
            factories.add(new TradeOffers.SellItemFactory(Blocks.LIGHT_GRAY_STAINED_GLASS, 1, 1, 12, 15));
            factories.add(new TradeOffers.SellItemFactory(Blocks.LIME_STAINED_GLASS, 1, 1, 12, 15));
            factories.add(new TradeOffers.SellItemFactory(Blocks.MAGENTA_STAINED_GLASS, 1, 1, 12, 15));
            factories.add(new TradeOffers.SellItemFactory(Blocks.ORANGE_STAINED_GLASS, 1, 1, 12, 15));
            factories.add(new TradeOffers.SellItemFactory(Blocks.PINK_STAINED_GLASS, 1, 1, 12, 15));
            factories.add(new TradeOffers.SellItemFactory(Blocks.PURPLE_STAINED_GLASS, 1, 1, 12, 15));
            factories.add(new TradeOffers.SellItemFactory(Blocks.RED_STAINED_GLASS, 1, 1, 12, 15));
            factories.add(new TradeOffers.SellItemFactory(Blocks.WHITE_STAINED_GLASS, 1, 1, 12, 15));
            factories.add(new TradeOffers.SellItemFactory(Blocks.YELLOW_STAINED_GLASS, 1, 1, 12, 15));
        });
        TradeOfferHelper.registerVillagerOffers(Kaleidoscope.GLASSBLOWER, 5, factories -> {
            factories.add(new TradeOffers.SellItemFactory(Blocks.TINTED_GLASS, 1, 1, 12, 15));
        });
        TradeOfferHelper.registerWanderingTraderOffers(1, factories -> {
            factories.add(new TradeOffers.SellItemFactory(Items.SWEET_BERRIES, 1, 1, 12, 1));
            factories.add(new TradeOffers.SellItemFactory(Items.PINK_PETALS, 1, 2, 7, 1));
            factories.add(new TradeOffers.SellItemFactory(Items.BAMBOO, 3, 1, 8, 1));
            factories.add(new TradeOffers.SellItemFactory(Items.COCOA_BEANS, 4, 1, 12, 1));
        });
        TradeOfferHelper.registerWanderingTraderOffers(2, factories -> {
            factories.add(new TradeOffers.SellItemFactory(Items.TADPOLE_BUCKET, 5, 1, 4, 1));
        });
    }

    public static class IllagerBaneFactory implements TradeOffers.Factory {
        private final int experience;

        public IllagerBaneFactory(int experience) {
            this.experience = experience;
        }

        public TradeOffer create(Entity entity, Random random) {
            Enchantment enchantment = KaleidoscopeEnchantments.ILLAGER_BANE;
            int i = MathHelper.nextInt(random, enchantment.getMinLevel(), enchantment.getMaxLevel());
            ItemStack itemStack = EnchantedBookItem.forEnchantment(new EnchantmentLevelEntry(enchantment, i));
            int j = 2 + random.nextInt(5 + i * 10) + 3 * i;

            if (j > 64) {
                j = 64;
            }

            return new TradeOffer(new ItemStack(Items.EMERALD, j), new ItemStack(Items.BOOK), itemStack, 12, this.experience, 0.2F);
        }
    }

    public static class SellDyedBundleFactory implements TradeOffers.Factory {
        private final int price;
        private final int experience;

        public SellDyedBundleFactory(int price, int experience) {
            this.price = price;
            this.experience = experience;
        }

        private static DyeItem getDye(Random random) {
            return DyeItem.byColor(DyeColor.byId(random.nextInt(16)));
        }

        @Override
        public TradeOffer create(Entity entity, Random random) {
            ItemStack itemStack = new ItemStack(Items.EMERALD, this.price);
            ItemStack itemStack2 = new ItemStack(Items.BUNDLE);
            ArrayList<DyeItem> list = Lists.newArrayList();
            list.add(SellDyedBundleFactory.getDye(random));
            if (random.nextFloat() > 0.7f) {
                list.add(SellDyedBundleFactory.getDye(random));
            }
            if (random.nextFloat() > 0.8f) {
                list.add(SellDyedBundleFactory.getDye(random));
            }
            itemStack2 = DyeableItem.blendAndSetColor(itemStack2, list);
            return new TradeOffer(itemStack, itemStack2, 12, this.experience, 0.05F);
        }
    }
}
