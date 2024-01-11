package com.chikoritalover.kaleidoscope.registry;

import com.chikoritalover.kaleidoscope.Kaleidoscope;
import com.google.common.collect.Lists;
import net.fabricmc.fabric.api.object.builder.v1.trade.TradeOfferHelper;
import net.minecraft.block.Blocks;
import net.minecraft.entity.Entity;
import net.minecraft.item.*;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.DyeColor;
import net.minecraft.util.math.random.Random;
import net.minecraft.village.TradeOffer;
import net.minecraft.village.TradeOffers;
import net.minecraft.village.VillagerProfession;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class KaleidoscopeTradeOffers {
    public static void register() {
        TradeOfferHelper.registerVillagerOffers(VillagerProfession.LEATHERWORKER, 3, factories -> {
            factories.add(new SellDyedBundleFactory(4, 10));
        });
        TradeOfferHelper.registerVillagerOffers(Kaleidoscope.FIREWORKER, 1, factories -> {
            factories.add(new TradeOffers.BuyForOneEmeraldFactory(Items.CHARCOAL, 15, 16, 2));
            factories.add(new TradeOffers.SellItemFactory(Items.GUNPOWDER, 1, 1, 12, 1));
        });
        TradeOfferHelper.registerVillagerOffers(Kaleidoscope.FIREWORKER, 2, factories -> {
            factories.add(new TradeOffers.BuyForOneEmeraldFactory(Items.BONE_MEAL, 12, 16, 10));
            factories.add(new TradeOffers.SellItemFactory(Items.FIRE_CHARGE, 2, 3, 12, 5));
            factories.add(new SellFireworkRocketFactory(1, 5));
        });
        TradeOfferHelper.registerVillagerOffers(Kaleidoscope.FIREWORKER, 3, factories -> {
            factories.add(new TradeOffers.BuyForOneEmeraldFactory(Items.PAPER, 16, 16, 20));
            factories.add(new SellFireworkStarFactory(1, false, 10));
        });
        TradeOfferHelper.registerVillagerOffers(Kaleidoscope.FIREWORKER, 4, factories -> {
            factories.add(new TradeOffers.BuyForOneEmeraldFactory(Items.GLOWSTONE_DUST, 10, 12, 30));
            factories.add(new TradeOffers.SellItemFactory(Blocks.TNT, 5, 1, 12, 15));
        });
        TradeOfferHelper.registerVillagerOffers(Kaleidoscope.FIREWORKER, 5, factories -> {
            factories.add(new SellFireworkRocketFactory(3, 30));
            factories.add(new SellFireworkStarFactory(2, true, 30));
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

    public static class SellFireworkStarFactory implements TradeOffers.Factory {
        final boolean hasShape;
        final int colors;
        final int experience;
        private final float multiplier;

        public SellFireworkStarFactory(int colors, boolean hasShape, int experience) {
            this.colors = colors;
            this.hasShape = hasShape;
            this.experience = experience;
            this.multiplier = 0.05F;
        }

        @Override
        @Nullable
        public TradeOffer create(Entity entity, Random random) {
            ItemStack itemStack = new ItemStack(Items.FIREWORK_STAR, 1);
            NbtCompound nbtCompound = itemStack.getOrCreateSubNbt("Explosion");
            List<Integer> list = new ArrayList<>();
            List<DyeColor> list2 = new ArrayList<>(Arrays.stream(DyeColor.values()).toList());
            for (int i = 0; i < this.colors; ++i) {
                DyeColor dyeColor = list2.remove(random.nextInt(list2.size()));
                list.add(dyeColor.getFireworkColor());
            }
            nbtCompound.putIntArray("Colors", list);
            FireworkRocketItem.Type type = this.hasShape ? FireworkRocketItem.Type.byId(random.nextBetween(1, 4)) : FireworkRocketItem.Type.SMALL_BALL;
            nbtCompound.putByte("Type", (byte) type.getId());
            return new TradeOffer(new ItemStack(Items.EMERALD, this.colors), itemStack, 12, this.experience, this.multiplier);
        }
    }

    public static class SellFireworkRocketFactory implements TradeOffers.Factory {
        final int duration;
        final int experience;
        private final float multiplier;

        public SellFireworkRocketFactory(int duration, int experience) {
            this.duration = duration;
            this.experience = experience;
            this.multiplier = 0.05F;
        }

        @Override
        @Nullable
        public TradeOffer create(Entity entity, Random random) {
            ItemStack itemStack = new ItemStack(Items.FIREWORK_ROCKET, 3);
            NbtCompound nbtCompound = itemStack.getOrCreateSubNbt("Fireworks");
            nbtCompound.putByte("Flight", (byte) this.duration);
            return new TradeOffer(new ItemStack(Items.EMERALD, this.duration + 1), itemStack, 12, this.experience, this.multiplier);
        }
    }
}
