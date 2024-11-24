package net.chikorita_lover.kaleidoscope.registry;

import it.unimi.dsi.fastutil.ints.IntArrayList;
import net.fabricmc.fabric.api.object.builder.v1.trade.TradeOfferHelper;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.entity.Entity;
import net.minecraft.item.FireworkRocketItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.DyeColor;
import net.minecraft.util.math.random.Random;
import net.minecraft.village.TradeOffer;
import net.minecraft.village.TradeOffers;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class KaleidoscopeTradeOffers {
    public static void register() {
        TradeOfferHelper.registerVillagerOffers(KaleidoscopeVillagerProfessions.FIREWORKER, 1, factories -> {
            factories.add(new TradeOffers.BuyForOneEmeraldFactory(Items.CHARCOAL, 15, 16, 2));
            factories.add(new TradeOffers.SellItemFactory(Items.GUNPOWDER, 1, 1, 12, 1));
        });
        TradeOfferHelper.registerVillagerOffers(KaleidoscopeVillagerProfessions.FIREWORKER, 2, factories -> {
            factories.add(new TradeOffers.BuyForOneEmeraldFactory(Items.BONE_MEAL, 12, 16, 10));
            factories.add(new TradeOffers.SellItemFactory(Items.FIRE_CHARGE, 2, 3, 12, 5));
            factories.add(new SellFireworkRocketFactory(1, 5));
        });
        TradeOfferHelper.registerVillagerOffers(KaleidoscopeVillagerProfessions.FIREWORKER, 3, factories -> {
            factories.add(new TradeOffers.BuyForOneEmeraldFactory(Items.PAPER, 16, 16, 20));
            factories.add(new SellFireworkStarFactory(1, false, 10));
        });
        TradeOfferHelper.registerVillagerOffers(KaleidoscopeVillagerProfessions.FIREWORKER, 4, factories -> {
            factories.add(new TradeOffers.BuyForOneEmeraldFactory(Items.GLOWSTONE_DUST, 10, 12, 30));
            factories.add(new TradeOffers.SellItemFactory(Blocks.TNT, 5, 1, 12, 15));
        });
        TradeOfferHelper.registerVillagerOffers(KaleidoscopeVillagerProfessions.FIREWORKER, 5, factories -> {
            factories.add(new TradeOffers.BuyForOneEmeraldFactory(Items.BLAZE_ROD, 3, 12, 30));
            factories.add(new SellFireworkRocketFactory(3, 30));
            factories.add(new SellFireworkStarFactory(2, true, 30));
        });
        TradeOfferHelper.registerVillagerOffers(KaleidoscopeVillagerProfessions.GLASSBLOWER, 1, factories -> {
            factories.add(new TradeOffers.BuyForOneEmeraldFactory(Blocks.SAND, 16, 16, 2));
            factories.add(new TradeOffers.SellItemFactory(Blocks.GLASS, 1, 4, 16, 1));
        });
        TradeOfferHelper.registerVillagerOffers(KaleidoscopeVillagerProfessions.GLASSBLOWER, 2, factories -> {
            factories.add(new TradeOffers.BuyForOneEmeraldFactory(Items.COAL, 15, 16, 10));
            factories.add(new TradeOffers.BuyForOneEmeraldFactory(Items.COPPER_INGOT, 18, 16, 10));
            factories.add(new TradeOffers.SellItemFactory(Items.SPYGLASS, 2, 1, 12, 5));
        });
        TradeOfferHelper.registerVillagerOffers(KaleidoscopeVillagerProfessions.GLASSBLOWER, 3, factories -> {
            factories.add(new TradeOffers.BuyForOneEmeraldFactory(Items.AMETHYST_SHARD, 14, 12, 20));
            factories.add(new TradeOffers.SellItemFactory(Blocks.REDSTONE_LAMP, 1, 1, 12, 10));
            factories.add(new TradeOffers.SellItemFactory(Blocks.DAYLIGHT_DETECTOR, 1, 1, 12, 10));
        });
        TradeOfferHelper.registerVillagerOffers(KaleidoscopeVillagerProfessions.GLASSBLOWER, 4, factories -> {
            Block[] stainedGlassBlocks = new Block[]{Blocks.WHITE_STAINED_GLASS, Blocks.LIGHT_GRAY_STAINED_GLASS, Blocks.GRAY_STAINED_GLASS, Blocks.BLACK_STAINED_GLASS, Blocks.BROWN_STAINED_GLASS, Blocks.RED_STAINED_GLASS, Blocks.ORANGE_STAINED_GLASS, Blocks.YELLOW_STAINED_GLASS, Blocks.LIME_STAINED_GLASS, Blocks.GREEN_STAINED_GLASS, Blocks.CYAN_STAINED_GLASS, Blocks.LIGHT_BLUE_STAINED_GLASS, Blocks.BLUE_STAINED_GLASS, Blocks.PURPLE_STAINED_GLASS, Blocks.MAGENTA_STAINED_GLASS, Blocks.PINK_STAINED_GLASS};
            for (Block stainedGlassBlock : stainedGlassBlocks) {
                factories.add(new TradeOffers.SellItemFactory(stainedGlassBlock, 1, 1, 12, 15));
            }
        });
        TradeOfferHelper.registerVillagerOffers(KaleidoscopeVillagerProfessions.GLASSBLOWER, 5, factories -> {
            factories.add(new TradeOffers.BuyForOneEmeraldFactory(Items.QUARTZ, 10, 12, 30));
            factories.add(new TradeOffers.SellItemFactory(Blocks.TINTED_GLASS, 1, 1, 12, 30));
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

    private static class SellFireworkStarFactory implements TradeOffers.Factory {
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
            ItemStack stack = new ItemStack(Items.FIREWORK_STAR, 1);
            NbtCompound explosion = stack.getOrCreateSubNbt("Explosion");
            List<Integer> list = new IntArrayList();
            List<DyeColor> list2 = new ArrayList<>(Arrays.stream(DyeColor.values()).toList());
            for (int i = 0; i < this.colors; ++i) {
                DyeColor dyeColor = list2.remove(random.nextInt(list2.size()));
                list.add(dyeColor.getFireworkColor());
            }
            explosion.putIntArray("Colors", list);
            FireworkRocketItem.Type shape = this.hasShape ? FireworkRocketItem.Type.byId(random.nextBetween(1, 4)) : FireworkRocketItem.Type.SMALL_BALL;
            explosion.putByte("Type", (byte) shape.getId());
            return new TradeOffer(new ItemStack(Items.EMERALD, this.colors), stack, 12, this.experience, this.multiplier);
        }
    }

    private static class SellFireworkRocketFactory implements TradeOffers.Factory {
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
            ItemStack stack = new ItemStack(Items.FIREWORK_ROCKET, 3);
            NbtCompound fireworks = stack.getOrCreateSubNbt("Fireworks");
            fireworks.putByte("Flight", (byte) this.duration);
            return new TradeOffer(new ItemStack(Items.EMERALD, this.duration + 1), stack, 12, this.experience, this.multiplier);
        }
    }
}
