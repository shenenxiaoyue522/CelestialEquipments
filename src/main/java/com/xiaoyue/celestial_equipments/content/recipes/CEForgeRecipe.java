package com.xiaoyue.celestial_equipments.content.recipes;

import com.xiaoyue.celestial_equipments.register.CERecipes;
import com.xiaoyue.celestial_equipments.utils.EquipmentUtils;
import com.xiaoyue.celestial_invoker.content.common.Bindings;
import dev.xkmc.l2library.serial.recipe.BaseRecipe;
import dev.xkmc.l2serial.serialization.SerialClass;
import net.minecraft.core.RegistryAccess;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.Level;

import java.util.ArrayList;
import java.util.List;

@SerialClass
public class CEForgeRecipe extends BaseRecipe<CEForgeRecipe, CEForgeRecipe, CEForgeRecipe.Inv> {

    @SerialClass.SerialField
    public Ingredient input;

    @SerialClass.SerialField
    public List<Ingredient> materials = new ArrayList<>();

    @SerialClass.SerialField
    public ItemStack output;

    @SerialClass.SerialField
    public boolean isUpgrade;

    @SerialClass.SerialField
    public int levelCondition;

    public CEForgeRecipe(ResourceLocation id) {
        super(id, CERecipes.RS_CE_FORGE.get());
    }

    @Override
    public boolean matches(Inv inv, Level world) {
        return Bindings.checkShapelessInputs(materials, inv);
    }

    public boolean matchesInput(ItemStack stack) {
        return this.input.test(stack);
    }

    @Override
    @Deprecated
    public ItemStack assemble(Inv inv, RegistryAccess registryAccess) {
        return output;
    }

    public ItemStack assemble(ItemStack input) {
        if (isUpgrade) {
            boolean allowedLv = EquipmentUtils.getLevel(input) <= levelCondition || levelCondition == -1;
            if (EquipmentUtils.isFullExp(input) && allowedLv)  {
                ItemStack newOutput = input.copy();
                EquipmentUtils.upGear(newOutput);
                return newOutput;
            }
        }
        return output;
    }

    @Override
    public boolean canCraftInDimensions(int i, int i1) {
        return true;
    }

    @Override
    public ItemStack getResultItem(RegistryAccess registryAccess) {
        return output.copy();
    }

    public static class Inv extends SimpleContainer implements RecInv<CEForgeRecipe> {
        public Inv() {
            super(9);
        }
    }
}
