package com.xiaoyue.celestial_equipments.content.recipes;

import com.xiaoyue.celestial_equipments.register.CERecipes;
import dev.xkmc.l2library.serial.recipe.BaseRecipeBuilder;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;

import java.util.ArrayList;
import java.util.List;

public class CEForgeRecipeBuilder extends BaseRecipeBuilder<CEForgeRecipeBuilder, CEForgeRecipe, CEForgeRecipe, CEForgeRecipe.Inv> {
    private final List<Ingredient> materials = new ArrayList<>();

    public CEForgeRecipeBuilder(Item input, ItemStack output) {
        super(CERecipes.RS_CE_FORGE.get());
        recipe.input = Ingredient.of(input);
        recipe.output = output;
        recipe.levelCondition = -1;
    }

    public CEForgeRecipeBuilder(TagKey<Item> input, ItemStack output) {
        super(CERecipes.RS_CE_FORGE.get());
        recipe.input = Ingredient.of(input);
        recipe.output = output;
        recipe.levelCondition = -1;
    }

    public CEForgeRecipeBuilder(TagKey<Item> input) {
        super(CERecipes.RS_CE_FORGE.get());
        recipe.input = Ingredient.of(input);
        recipe.output = ItemStack.EMPTY;
        recipe.levelCondition = -1;
    }

    public CEForgeRecipeBuilder material(Ingredient material) {
        materials.add(material);
        recipe.materials = materials;
        return this;
    }

    public CEForgeRecipeBuilder material(ItemLike item) {
        return material(Ingredient.of(item));
    }

    public CEForgeRecipeBuilder material(TagKey<Item> item) {
        return material(Ingredient.of(item));
    }

    public CEForgeRecipeBuilder isUpgrade(int levelCondition) {
        recipe.isUpgrade = true;
        recipe.levelCondition = levelCondition;
        return this;
    }

    public CEForgeRecipeBuilder isUpgrade() {
        return isUpgrade(-1);
    }
}
