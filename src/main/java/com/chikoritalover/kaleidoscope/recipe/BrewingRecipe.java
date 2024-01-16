package com.chikoritalover.kaleidoscope.recipe;

import com.chikoritalover.kaleidoscope.Kaleidoscope;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionUtil;
import net.minecraft.recipe.Ingredient;
import net.minecraft.registry.Registries;
import net.minecraft.util.Identifier;
import org.apache.commons.compress.utils.Lists;

import java.util.List;

public class BrewingRecipe {
    public static final List<BrewingRecipe> BREWING_RECIPES = Lists.newArrayList();
    private final Ingredient input;
    private final Potion potion;
    private final Ingredient reagent;
    private final ItemStack output;
    private final Identifier id;

    public BrewingRecipe(Ingredient input, Potion potion, Ingredient reagent, ItemStack output) {
        this(input, potion, reagent, output, new Identifier(Kaleidoscope.MODID, Registries.POTION.getId(PotionUtil.getPotion(output)).getPath() + "_" + output.getItem() + "_from_" + Registries.POTION.getId(potion).getPath() + "_" + input.getMatchingStacks()[0].getItem() + "_" + reagent.getMatchingStacks()[0].getItem() + "_brewing"));
    }

    public BrewingRecipe(Ingredient input, Potion potion, Ingredient reagent, ItemStack output, Identifier id) {
        this.input = input;
        this.potion = potion;
        this.reagent = reagent;
        this.output = output;
        this.id = id;
    }

    public Ingredient getInput() {
        return this.input;
    }

    public Potion getInputPotion() { return this.potion; }

    public Ingredient getReagent() {
        return this.reagent;
    }

    public ItemStack getOutput() {
        return this.output;
    }

    public Identifier getId() {
        return this.id;
    }
}
