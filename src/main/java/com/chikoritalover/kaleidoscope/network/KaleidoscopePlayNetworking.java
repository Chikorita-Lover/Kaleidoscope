package com.chikoritalover.kaleidoscope.network;

import com.chikoritalover.kaleidoscope.Kaleidoscope;
import com.chikoritalover.kaleidoscope.block.entity.KaleidoscopeBrewingStandBlockEntity;
import com.chikoritalover.kaleidoscope.client.ClientBrewingRecipeBook;
import com.chikoritalover.kaleidoscope.client.gui.screen.BrewingRecipeBookWidget;
import com.chikoritalover.kaleidoscope.client.toast.BrewingRecipeToast;
import com.chikoritalover.kaleidoscope.entity.KaleidoscopePlayerEntity;
import com.chikoritalover.kaleidoscope.recipe.BrewingRecipe;
import com.chikoritalover.kaleidoscope.recipe.BrewingRecipeMatcher;
import com.chikoritalover.kaleidoscope.recipe.BrewingUtil;
import com.chikoritalover.kaleidoscope.screen.KaleidoscopeBrewingStandScreen;
import com.google.common.collect.Lists;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.block.entity.BrewingStandBlockEntity;
import net.minecraft.client.gui.screen.ingame.BrewingStandScreen;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.network.NetworkThreadUtils;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.potion.PotionUtil;
import net.minecraft.screen.BrewingStandScreenHandler;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.slot.Slot;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.Identifier;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class KaleidoscopePlayNetworking {
    public static final Identifier BREW_FAILED_RESPONSE = new Identifier(Kaleidoscope.MODID, "brew_failed_response");
    public static final Identifier BREW_REQUEST = new Identifier(Kaleidoscope.MODID, "brew_request");
    public static final Identifier BREWING_RECIPE_BOOK_DATA = new Identifier(Kaleidoscope.MODID, "brewing_recipe_book_data");
    public static final Identifier UNLOCK_BREWING_RECIPES = new Identifier(Kaleidoscope.MODID, "unlock_brewing_recipes");
    public static final Identifier BREWING_RECIPE_BOOK_OPTIONS = new Identifier(Kaleidoscope.MODID, "brewing_recipe_book_options");

    public static void register() {
        ServerPlayNetworking.registerGlobalReceiver(BREW_REQUEST, (server, player, handler, buf, responseSender) -> {
            BrewRequestC2SPacket packet = new BrewRequestC2SPacket(buf);
            server.execute(() -> {
                NetworkThreadUtils.forceMainThread(packet, handler, player.getServerWorld());
                player.updateLastActionTime();
                if (player.isSpectator() || player.currentScreenHandler.syncId != packet.getSyncId() || !(player.currentScreenHandler instanceof BrewingStandScreenHandler screenHandler)) {
                    return;
                }
                if (!player.currentScreenHandler.canUse(player)) {
                    System.out.println("Player " + player + " interacted with invalid menu " + player.currentScreenHandler);
                    return;
                }

                BrewingRecipeMatcher matcher = new BrewingRecipeMatcher();
                Identifier identifier = packet.getRecipeId();
                Optional<BrewingRecipe> optional = BrewingUtil.getRecipeFromId(identifier);
                if (optional.isEmpty()) return;
                BrewingRecipe recipe = optional.get();
                if (!((KaleidoscopePlayerEntity) player).kaleidoscope$getServerBrewingRecipeBook().contains(recipe) || !canReturnInputs(player) && !player.isCreative()) {
                    return;
                }
                matcher.clear();
                for (ItemStack itemStack : player.getInventory().main) {
                    matcher.addInput(itemStack);
                }
                for (int i = 0; i < 5; ++i) {
                    matcher.addInput(screenHandler.getSlot(i).getStack());
                }
                if (matcher.match(recipe) > 0) {
                    fillInputSlots(recipe, packet.shouldBrewAll(), player);
                } else {
                    for (int i = 0; i < 4; ++i) {
                        Slot slot = screenHandler.getSlot(i);
                        if (!screenHandler.canInsertIntoSlot(slot)) continue;
                        ItemStack itemStack = slot.getStack().copy();
                        player.getInventory().offer(itemStack, false);
                        slot.setStackNoCallbacks(ItemStack.EMPTY);
                    }
                    ((KaleidoscopeBrewingStandBlockEntity) screenHandler.inventory).kaleidoscope$dropExperienceForRecipesUsed(player);
                    PacketByteBuf packetByteBuf = PacketByteBufs.create();
                    BrewFailedResponseS2CPacket packet2 = new BrewFailedResponseS2CPacket(player.currentScreenHandler.syncId, recipe);
                    packet2.write(packetByteBuf);
                    ServerPlayNetworking.send(player, BREW_FAILED_RESPONSE, packetByteBuf);
                }
                player.getInventory().markDirty();
            });
        });
        ServerPlayNetworking.registerGlobalReceiver(BREWING_RECIPE_BOOK_DATA, (server, player, handler, buf, responseSender) -> {
            BrewingRecipeBookDataC2SPacket packet = new BrewingRecipeBookDataC2SPacket(buf);
            server.execute(() -> {
                NetworkThreadUtils.forceMainThread(packet, handler, player.getServerWorld());
                Optional<BrewingRecipe> optional = BrewingUtil.getRecipeFromId(packet.getRecipeId());
                optional.ifPresent(recipe2 -> ((KaleidoscopePlayerEntity) player).kaleidoscope$getServerBrewingRecipeBook().onRecipeDisplayed(recipe2));
            });
        });
        ServerPlayNetworking.registerGlobalReceiver(BREWING_RECIPE_BOOK_OPTIONS, (server, player, handler, buf, responseSender) -> {
            BrewingRecipeBookOptionsC2SPacket packet = new BrewingRecipeBookOptionsC2SPacket(buf);
            server.execute(() -> {
                NetworkThreadUtils.forceMainThread(packet, handler, player.getServerWorld());
                ((KaleidoscopePlayerEntity) player).kaleidoscope$getServerBrewingRecipeBook().setCategoryOptions(packet.isGuiOpen(), packet.isFilteringBrewable());
            });
        });
        ClientPlayNetworking.registerGlobalReceiver(BREW_FAILED_RESPONSE, (client, handler, buf, responseSender) -> {
            BrewFailedResponseS2CPacket packet = new BrewFailedResponseS2CPacket(buf);
            client.execute(() -> {
                NetworkThreadUtils.forceMainThread(packet, handler, client);
                ScreenHandler screenHandler = client.player.currentScreenHandler;
                if (screenHandler.syncId != packet.getSyncId()) {
                    return;
                }
                BrewingUtil.getRecipeFromId(packet.getRecipeId()).ifPresent(recipe -> {
                    if (client.currentScreen instanceof BrewingStandScreen brewingStandScreen) {
                        BrewingRecipeBookWidget brewingRecipeBookWidget = ((KaleidoscopeBrewingStandScreen) brewingStandScreen).kaleidoscope$getRecipeBookWidget();
                        brewingRecipeBookWidget.showGhostRecipe(recipe, screenHandler.slots);
                    }
                });
            });
        });
        ClientPlayNetworking.registerGlobalReceiver(UNLOCK_BREWING_RECIPES, (client, handler, buf, responseSender) -> {
            UnlockBrewingRecipesS2CPacket packet = new UnlockBrewingRecipesS2CPacket(buf);
            client.execute(() -> {
                NetworkThreadUtils.forceMainThread(packet, handler, client);
                ClientBrewingRecipeBook clientBrewingRecipeBook = ((KaleidoscopePlayerEntity) client.player).kaleidoscope$getClientBrewingRecipeBook();
                clientBrewingRecipeBook.setFilteringBrewable(packet.isFilteringBrewable());
                clientBrewingRecipeBook.setGuiOpen(packet.isGuiOpen());
                UnlockBrewingRecipesS2CPacket.Action action = packet.getAction();
                switch (action) {
                    case REMOVE: {
                        for (Identifier identifier : packet.getRecipeIdsToChange()) {
                            clientBrewingRecipeBook.remove(identifier);
                        }
                        break;
                    }
                    case INIT: {
                        for (Identifier identifier : packet.getRecipeIdsToChange()) {
                            clientBrewingRecipeBook.add(identifier);
                        }
                        for (Identifier identifier : packet.getRecipeIdsToInit()) {
                            clientBrewingRecipeBook.display(identifier);
                        }
                        break;
                    }
                    case ADD: {
                        for (Identifier identifier : packet.getRecipeIdsToChange()) {
                            clientBrewingRecipeBook.add(identifier);
                            clientBrewingRecipeBook.display(identifier);
                            Optional<BrewingRecipe> optional = BrewingUtil.getRecipeFromId(identifier);
                            optional.ifPresent(recipe -> BrewingRecipeToast.show(client.getToastManager(), recipe));
                        }
                        break;
                    }
                }
                clientBrewingRecipeBook.getOrderedResults().forEach(recipeResultCollection -> recipeResultCollection.initialize(clientBrewingRecipeBook));
                if (client.currentScreen instanceof BrewingStandScreen) {
                    ((KaleidoscopeBrewingStandScreen) client.currentScreen).kaleidoscope$refreshRecipeBook();
                }
            });
        });
    }

    private static void fillInputSlots(BrewingRecipe recipe, boolean brewAll, ServerPlayerEntity player) {
        Slot slot = player.currentScreenHandler.getSlot(3);
        Inventory inventory = player.getInventory();
        if (!slot.hasStack() || !recipe.getReagent().test(slot.getStack())) {
            if (!inventory.containsAny(recipe.getReagent())) {
                return;
            }
            for (int i = 0; i < inventory.size(); ++i) {
                ItemStack itemStack = inventory.getStack(i);
                if (recipe.getReagent().test(itemStack)) {
                    slot.setStack(itemStack.split(1));
                    break;
                }
            }
        }
        List<Slot> slots = player.currentScreenHandler.slots.subList(0, 3);
        if (slots.stream().allMatch(Slot::hasStack) && slots.stream().noneMatch(slot2 -> recipe.getInput().test(slot2.getStack()) && PotionUtil.getPotion(slot2.getStack()) == recipe.getInputPotion())) {
            for (Slot slot2 : slots) {
                player.getInventory().offer(slot2.getStack().copy(), false);
                slot2.setStackNoCallbacks(ItemStack.EMPTY);
            }
        }
        if (slots.stream().allMatch(Slot::hasStack) || !inventory.containsAny(recipe.getInput())) {
            return;
        }
        for (Slot slot2 : slots) {
            if (slot2.hasStack()) {
                continue;
            }
            for (int i = 0; i < inventory.size(); ++i) {
                ItemStack itemStack = inventory.getStack(i);
                if (recipe.getInput().test(itemStack) && PotionUtil.getPotion(itemStack) == recipe.getInputPotion()) {
                    slot2.setStack(itemStack.split(1));
                    break;
                }
            }
            if (!brewAll) {
                break;
            }
        }
    }

    private static boolean canReturnInputs(ServerPlayerEntity player) {
        ArrayList<ItemStack> list = Lists.newArrayList();
        int i = 0;
        for (ItemStack itemStack : player.getInventory().main) {
            if (!itemStack.isEmpty()) continue;
            ++i;
        }
        for (int j = 0; j < 4; ++j) {
            ItemStack itemStack = player.currentScreenHandler.getSlot(j).getStack().copy();
            if (itemStack.isEmpty()) continue;
            int k = player.getInventory().getOccupiedSlotWithRoomForStack(itemStack);
            if (k == -1 && list.size() <= i) {
                for (ItemStack itemStack2 : list) {
                    if (!ItemStack.areItemsEqual(itemStack2, itemStack) || itemStack2.getCount() == itemStack2.getMaxCount() || itemStack2.getCount() + itemStack.getCount() > itemStack2.getMaxCount()) continue;
                    itemStack2.increment(itemStack.getCount());
                    itemStack.setCount(0);
                    break;
                }
                if (itemStack.isEmpty()) continue;
                if (list.size() < i) {
                    list.add(itemStack);
                    continue;
                }
                return false;
            }
            if (k != -1) continue;
            return false;
        }
        return true;
    }
}
