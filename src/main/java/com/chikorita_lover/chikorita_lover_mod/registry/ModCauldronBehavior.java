package com.chikorita_lover.chikorita_lover_mod.registry;

import com.chikorita_lover.chikorita_lover_mod.block.entity.PotionCauldronBlockEntity;
import net.minecraft.block.Blocks;
import net.minecraft.block.LeveledCauldronBlock;
import net.minecraft.block.cauldron.CauldronBehavior;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsage;
import net.minecraft.item.Items;
import net.minecraft.potion.PotionUtil;
import net.minecraft.potion.Potions;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.stat.Stats;
import net.minecraft.util.ActionResult;
import net.minecraft.world.event.GameEvent;

import java.util.Map;

public class ModCauldronBehavior {
    public static final Map<Item, CauldronBehavior> POTION_CAULDRON_BEHAVIOR = CauldronBehavior.createMap();

    public static void register() {
        CauldronBehavior.WATER_CAULDRON_BEHAVIOR.put(Items.BUNDLE, CauldronBehavior.CLEAN_DYEABLE_ITEM);
        CauldronBehavior.EMPTY_CAULDRON_BEHAVIOR.put(Items.POTION, (state, world, pos, player, hand, stack) -> {
            ItemStack itemStack = stack.copy();
            player.setStackInHand(hand, ItemUsage.exchangeStack(stack, player, new ItemStack(Items.GLASS_BOTTLE)));
            player.incrementStat(Stats.USE_CAULDRON);
            player.incrementStat(Stats.USED.getOrCreateStat(itemStack.getItem()));
            if (PotionUtil.getPotion(stack) == Potions.WATER) {
                world.setBlockState(pos, Blocks.WATER_CAULDRON.getDefaultState());
            } else {
                world.setBlockState(pos, ModBlocks.POTION_CAULDRON.getDefaultState());
                ((PotionCauldronBlockEntity) world.getBlockEntity(pos)).setPotion(itemStack);
            }
            world.playSound(null, pos, SoundEvents.ITEM_BOTTLE_EMPTY, SoundCategory.BLOCKS, 1.0F, 1.0F);
            world.emitGameEvent(null, GameEvent.FLUID_PLACE, pos);
            return ActionResult.success(world.isClient);
        });
        POTION_CAULDRON_BEHAVIOR.put(Items.GLASS_BOTTLE, (state, world, pos, player, hand, stack) -> {
            Item item = stack.getItem();
            ItemStack potionStack = ((PotionCauldronBlockEntity) world.getBlockEntity(pos)).getPotion().copy();
            player.setStackInHand(hand, ItemUsage.exchangeStack(stack, player, potionStack));
            player.incrementStat(Stats.USE_CAULDRON);
            player.incrementStat(Stats.USED.getOrCreateStat(item));
            LeveledCauldronBlock.decrementFluidLevel(state, world, pos);
            world.playSound(null, pos, SoundEvents.ITEM_BOTTLE_FILL, SoundCategory.BLOCKS, 1.0f, 1.0f);
            world.emitGameEvent(null, GameEvent.FLUID_PICKUP, pos);
            return ActionResult.success(world.isClient);
        });
        POTION_CAULDRON_BEHAVIOR.put(Items.POTION, (state, world, pos, player, hand, stack) -> {
            if (state.get(LeveledCauldronBlock.LEVEL) == 3) {
                return ActionResult.PASS;
            }
            ItemStack potionStack = ((PotionCauldronBlockEntity) world.getBlockEntity(pos)).getPotion().copy();
            if (PotionCauldronBlockEntity.removeIrrelevantNbtKeys(stack.copy().getNbt()).equals(potionStack.getNbt())) {
                Item item = stack.getItem();
                player.setStackInHand(hand, ItemUsage.exchangeStack(stack, player, new ItemStack(Items.GLASS_BOTTLE)));
                player.incrementStat(Stats.USE_CAULDRON);
                player.incrementStat(Stats.USED.getOrCreateStat(item));
                world.setBlockState(pos, state.cycle(LeveledCauldronBlock.LEVEL));
                world.playSound(null, pos, SoundEvents.ITEM_BOTTLE_EMPTY, SoundCategory.BLOCKS, 1.0F, 1.0F);
                world.emitGameEvent(null, GameEvent.FLUID_PLACE, pos);
            } else {
                return ActionResult.PASS;
            }
            return ActionResult.success(world.isClient);
        });
        POTION_CAULDRON_BEHAVIOR.put(Items.ARROW, (state, world, pos, player, hand, stack) -> {
            ItemStack potionStack = ((PotionCauldronBlockEntity) world.getBlockEntity(pos)).getPotion();
            int i = Math.min(stack.getCount(), 8);
            if (!player.getAbilities().creativeMode) stack.decrement(i);
            world.playSound(null, pos, SoundEvents.ENTITY_GENERIC_SPLASH, SoundCategory.BLOCKS, 1.0F, 1.0F);
            ItemStack itemStack = new ItemStack(Items.TIPPED_ARROW, i);
            PotionUtil.setPotion(itemStack, PotionUtil.getPotion(potionStack));
            PotionUtil.setCustomPotionEffects(itemStack, PotionUtil.getCustomPotionEffects(potionStack));
            if (!player.getInventory().insertStack(itemStack)) {
                player.dropItem(itemStack, false);
            }
            player.incrementStat(Stats.USE_CAULDRON);
            player.increaseStat(Stats.USED.getOrCreateStat(Items.ARROW), i);
            LeveledCauldronBlock.decrementFluidLevel(state, world, pos);
            world.emitGameEvent(null, GameEvent.BLOCK_CHANGE, pos);
            return ActionResult.success(world.isClient);
        });
    }
}
