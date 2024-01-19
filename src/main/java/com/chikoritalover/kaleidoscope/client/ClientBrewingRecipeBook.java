package com.chikoritalover.kaleidoscope.client;

import com.chikoritalover.kaleidoscope.client.gui.screen.BrewingRecipeResultCollection;
import com.chikoritalover.kaleidoscope.recipe.BrewingRecipe;
import com.chikoritalover.kaleidoscope.recipe.BrewingRecipeBook;
import com.google.common.collect.*;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.potion.PotionUtil;
import net.minecraft.potion.Potions;
import net.minecraft.registry.Registries;

import java.util.*;
import java.util.stream.Stream;

@Environment(value = EnvType.CLIENT)
public class ClientBrewingRecipeBook extends BrewingRecipeBook {
    private Map<Group, List<BrewingRecipeResultCollection>> resultsByGroup = ImmutableMap.of();
    private List<BrewingRecipeResultCollection> orderedResults = ImmutableList.of();

    @Override
    public void copyFrom(BrewingRecipeBook book) {
        super.copyFrom(book);
        if (book instanceof ClientBrewingRecipeBook clientBrewingRecipeBook) {
            this.resultsByGroup = Map.copyOf(clientBrewingRecipeBook.resultsByGroup);
            this.orderedResults = List.copyOf(clientBrewingRecipeBook.getOrderedResults());
        }
    }

    private static Map<Group, List<List<BrewingRecipe>>> toGroupedMap(Iterable<BrewingRecipe> recipes) {
        HashMap<Group, List<List<BrewingRecipe>>> map = Maps.newHashMap();
        HashBasedTable<Group, String, ArrayList<BrewingRecipe>> table = HashBasedTable.create();
        for (BrewingRecipe recipe : recipes) {
            Group group = getGroupForRecipe(recipe);
            String string = Registries.POTION.getId(PotionUtil.getPotion(recipe.getOutput())).getPath() + "_" + recipe.getOutput().getItem();
            if (string.isEmpty()) {
                map.computeIfAbsent(group, group2 -> Lists.newArrayList()).add(ImmutableList.of(recipe));
                continue;
            }
            ArrayList<BrewingRecipe> list = table.get(group, string);
            if (list == null) {
                list = Lists.newArrayList();
                table.put(group, string, list);
                map.computeIfAbsent(group, group2 -> Lists.newArrayList()).add(list);
            }
            list.add(recipe);
        }
        return map;
    }

    private static Group getGroupForRecipe(BrewingRecipe recipe) {
        if (PotionUtil.getPotion(recipe.getOutput()) == recipe.getInputPotion()) {
            return Group.MISC;
        }
        if (recipe.getReagent().test(new ItemStack(Items.GLOWSTONE_DUST)) || recipe.getReagent().test(new ItemStack(Items.REDSTONE))) {
            return Group.STRONG_LONG;
        }
        return Group.EFFECTS;
    }

    public void reload(Iterable<BrewingRecipe> recipes) {
        Map<Group, List<List<BrewingRecipe>>> map = toGroupedMap(recipes);
        HashMap<Group, List<BrewingRecipeResultCollection>> map2 = Maps.newHashMap();
        ImmutableList.Builder<BrewingRecipeResultCollection> builder = ImmutableList.builder();
        map.forEach((recipeBookGroup, list) -> map2.put(recipeBookGroup, list.stream().map(BrewingRecipeResultCollection::new).peek(builder::add).collect(ImmutableList.toImmutableList())));
        map2.put(Group.SEARCH, Stream.of(Group.EFFECTS, Group.STRONG_LONG, Group.MISC).flatMap(searchGroup -> (map2.getOrDefault(searchGroup, ImmutableList.of())).stream()).collect(ImmutableList.toImmutableList()));
        this.resultsByGroup = ImmutableMap.copyOf(map2);
        this.orderedResults = builder.build();
    }

    public List<BrewingRecipeResultCollection> getOrderedResults() {
        return this.orderedResults;
    }

    public List<BrewingRecipeResultCollection> getResultsForGroup(Group group) {
        return this.resultsByGroup.getOrDefault(group, Collections.emptyList());
    }

    public enum Group {
        SEARCH(new ItemStack(Items.COMPASS)),
        EFFECTS(PotionUtil.setPotion(new ItemStack(Items.POTION), Potions.STRENGTH)),
        STRONG_LONG(new ItemStack(Items.GLOWSTONE_DUST), new ItemStack(Items.REDSTONE)),
        MISC(PotionUtil.setPotion(new ItemStack(Items.SPLASH_POTION), Potions.HEALING), PotionUtil.setPotion(new ItemStack(Items.LINGERING_POTION), Potions.POISON));

        private final List<ItemStack> icons;

        Group(ItemStack... entries) {
            this.icons = ImmutableList.copyOf(entries);
        }

        public List<ItemStack> getIcons() {
            return this.icons;
        }
    }
}
