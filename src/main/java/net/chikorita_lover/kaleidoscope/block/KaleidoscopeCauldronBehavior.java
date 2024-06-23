package net.chikorita_lover.kaleidoscope.block;

import net.chikorita_lover.kaleidoscope.Kaleidoscope;
import net.chikorita_lover.kaleidoscope.block.entity.KaleidoscopeBlockEntityTypes;
import net.chikorita_lover.kaleidoscope.block.entity.PotionCauldronBlockEntity;
import net.minecraft.block.Blocks;
import net.minecraft.block.LeveledCauldronBlock;
import net.minecraft.block.cauldron.CauldronBehavior;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsage;
import net.minecraft.item.Items;
import net.minecraft.potion.Potions;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.stat.Stats;
import net.minecraft.util.ItemActionResult;
import net.minecraft.world.event.GameEvent;

import java.util.Objects;
import java.util.Optional;

public class KaleidoscopeCauldronBehavior {
    public static final CauldronBehavior.CauldronBehaviorMap POTION_CAULDRON_BEHAVIOR = CauldronBehavior.createMap("kaleidoscope_potion");
    public static final String NO_BLOCK_ENTITY_ERROR = "Could not retrieve potion cauldron block entity, state: {}, pos: {}";

    public static void register() {
        CauldronBehavior.WATER_CAULDRON_BEHAVIOR.map().put(Items.BUNDLE, CauldronBehavior.CLEAN_DYEABLE_ITEM);
        CauldronBehavior.EMPTY_CAULDRON_BEHAVIOR.map().put(Items.POTION, (state, world, pos, player, hand, stack) -> {
            if (!world.isClient()) {
                if (stack.contains(DataComponentTypes.POTION_CONTENTS) && stack.get(DataComponentTypes.POTION_CONTENTS).matches(Potions.WATER)) {
                    world.setBlockState(pos, Blocks.WATER_CAULDRON.getDefaultState());
                } else {
                    world.setBlockState(pos, KaleidoscopeBlocks.POTION_CAULDRON.getDefaultState());
                    Optional<PotionCauldronBlockEntity> optional = world.getBlockEntity(pos, KaleidoscopeBlockEntityTypes.POTION_CAULDRON);
                    if (optional.isPresent()) {
                        optional.get().setStack(stack);
                    } else {
                        Kaleidoscope.LOGGER.error(NO_BLOCK_ENTITY_ERROR, world.getBlockState(pos), pos);
                    }
                }
                world.playSound(null, pos, SoundEvents.ITEM_BOTTLE_EMPTY, SoundCategory.BLOCKS);
                world.emitGameEvent(null, GameEvent.FLUID_PLACE, pos);
                player.setStackInHand(hand, ItemUsage.exchangeStack(stack, player, new ItemStack(Items.GLASS_BOTTLE)));
                player.incrementStat(Stats.USE_CAULDRON);
                player.incrementStat(Stats.USED.getOrCreateStat(stack.getItem()));
            }
            return ItemActionResult.success(world.isClient());
        });
        POTION_CAULDRON_BEHAVIOR.map().put(Items.GLASS_BOTTLE, (state, world, pos, player, hand, stack) -> {
            Optional<PotionCauldronBlockEntity> optional = world.getBlockEntity(pos, KaleidoscopeBlockEntityTypes.POTION_CAULDRON);
            if (optional.isEmpty()) {
                Kaleidoscope.LOGGER.error(NO_BLOCK_ENTITY_ERROR, world.getBlockState(pos), pos);
                return ItemActionResult.PASS_TO_DEFAULT_BLOCK_INTERACTION;
            }
            if (!world.isClient()) {
                Item item = stack.getItem();
                ItemStack potionStack = optional.get().getStack().copy();
                player.setStackInHand(hand, ItemUsage.exchangeStack(stack, player, potionStack));
                player.incrementStat(Stats.USE_CAULDRON);
                player.incrementStat(Stats.USED.getOrCreateStat(item));
                LeveledCauldronBlock.decrementFluidLevel(state, world, pos);
                world.playSound(null, pos, SoundEvents.ITEM_BOTTLE_FILL, SoundCategory.BLOCKS);
                world.emitGameEvent(null, GameEvent.FLUID_PICKUP, pos);
            }
            return ItemActionResult.success(world.isClient());
        });
        POTION_CAULDRON_BEHAVIOR.map().put(Items.POTION, (state, world, pos, player, hand, stack) -> {
            if (state.get(LeveledCauldronBlock.LEVEL) == 3) {
                return ItemActionResult.PASS_TO_DEFAULT_BLOCK_INTERACTION;
            }
            Optional<PotionCauldronBlockEntity> optional = world.getBlockEntity(pos, KaleidoscopeBlockEntityTypes.POTION_CAULDRON);
            if (optional.isEmpty()) {
                Kaleidoscope.LOGGER.error(NO_BLOCK_ENTITY_ERROR, world.getBlockState(pos), pos);
                return ItemActionResult.PASS_TO_DEFAULT_BLOCK_INTERACTION;
            }
            if (!Objects.equals(stack.get(DataComponentTypes.POTION_CONTENTS), optional.get().getStack().get(DataComponentTypes.POTION_CONTENTS))) {
                return ItemActionResult.PASS_TO_DEFAULT_BLOCK_INTERACTION;
            }
            if (!world.isClient()) {
                Item item = stack.getItem();
                player.setStackInHand(hand, ItemUsage.exchangeStack(stack, player, new ItemStack(Items.GLASS_BOTTLE)));
                player.incrementStat(Stats.USE_CAULDRON);
                player.incrementStat(Stats.USED.getOrCreateStat(item));
                world.setBlockState(pos, state.cycle(LeveledCauldronBlock.LEVEL));
                world.playSound(null, pos, SoundEvents.ITEM_BOTTLE_EMPTY, SoundCategory.BLOCKS);
                world.emitGameEvent(null, GameEvent.FLUID_PLACE, pos);
            }
            return ItemActionResult.success(world.isClient);
        });
        POTION_CAULDRON_BEHAVIOR.map().put(Items.ARROW, (state, world, pos, player, hand, stack) -> {
            Optional<PotionCauldronBlockEntity> optional = world.getBlockEntity(pos, KaleidoscopeBlockEntityTypes.POTION_CAULDRON);
            if (optional.isEmpty()) {
                Kaleidoscope.LOGGER.error(NO_BLOCK_ENTITY_ERROR, world.getBlockState(pos), pos);
                return ItemActionResult.PASS_TO_DEFAULT_BLOCK_INTERACTION;
            }
            if (!world.isClient()) {
                ItemStack potionStack = optional.get().getStack();
                int count = Math.min(stack.getCount(), 8);
                if (!player.getAbilities().creativeMode) {
                    stack.decrement(count);
                }
                world.playSound(null, pos, SoundEvents.ENTITY_GENERIC_SPLASH, SoundCategory.BLOCKS);
                ItemStack arrowStack = potionStack.copyComponentsToNewStack(Items.TIPPED_ARROW, count);
                if (!player.getInventory().insertStack(arrowStack)) {
                    player.dropItem(arrowStack, false);
                }
                player.incrementStat(Stats.USE_CAULDRON);
                player.increaseStat(Stats.USED.getOrCreateStat(Items.ARROW), count);
                LeveledCauldronBlock.decrementFluidLevel(state, world, pos);
                world.emitGameEvent(player, GameEvent.BLOCK_CHANGE, pos);
            }
            return ItemActionResult.success(world.isClient());
        });
    }
}
