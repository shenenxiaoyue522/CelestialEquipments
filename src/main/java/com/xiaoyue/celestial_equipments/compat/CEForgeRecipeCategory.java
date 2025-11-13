package com.xiaoyue.celestial_equipments.compat;

import com.xiaoyue.celestial_equipments.CelestialEquipments;
import com.xiaoyue.celestial_equipments.content.container.CEForgeTableScreen;
import com.xiaoyue.celestial_equipments.content.recipe.CEForgeRecipe;
import com.xiaoyue.celestial_equipments.register.CEBlocks;
import com.xiaoyue.celestial_invoker.invoker.tooltip.SubscribeTooltip;
import com.xiaoyue.celestial_invoker.invoker.tooltip.TooltipEntry;
import dev.xkmc.l2library.serial.recipe.BaseRecipeCategory;
import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.helpers.IJeiHelpers;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.RecipeIngredientRole;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;

public class CEForgeRecipeCategory extends BaseRecipeCategory<CEForgeRecipe, CEForgeRecipeCategory> {
    public CEForgeRecipeCategory() {
        super(CelestialEquipments.loc("special"), CEForgeRecipe.class);
    }

    @SubscribeTooltip(key = "jei.ce_forge_recipe.is_upgrade_recipe")
    public static TooltipEntry recipeDesc = TooltipEntry.define("This is an equipment upgrade recipe");

    @SubscribeTooltip(key = "jei.ce_forge_recipe.upgrade_recipe_condition")
    public static TooltipEntry levelCondition = TooltipEntry.define("Applies to equipment when level is below %s level");

    public CEForgeRecipeCategory init(IJeiHelpers jeiHelpers) {
        IGuiHelper guiHelper = jeiHelpers.getGuiHelper();
        icon = guiHelper.createDrawableIngredient(VanillaTypes.ITEM_STACK, CEBlocks.ASSEMBLY_TABLE.asStack());
        background = guiHelper.createDrawable(CEForgeTableScreen.TEXTURE, 5, 15, 166, 56);
        return this;
    }

    @Override
    public Component getTitle() {
        return CEForgeTableScreen.TITLE;
    }

    @Override
    public void setRecipe(IRecipeLayoutBuilder builder, CEForgeRecipe recipe, IFocusGroup group) {
        builder.addSlot(RecipeIngredientRole.INPUT, 11, 21)
                .setSlotName("input")
                .addIngredients(recipe.input);
        int x = 53;
        int y = 2;
        for (int i = 0; i < 9; i++) {
            if (recipe.materials.size() > i) {
                builder.addSlot(RecipeIngredientRole.INPUT, x + 1, y + 1)
                        .setSlotName("input" + i)
                        .addIngredients(recipe.materials.get(i));
            }
            y += 18;
            if (y >= 56) {
                y -= 54;
                x += 18;
            }
        }
        if (recipe.isUpgrade) {
            builder.addSlot(RecipeIngredientRole.OUTPUT, 138, 21)
                    .setSlotName("output")
                    .addTooltipCallback((view, list) -> {
                        list.clear();
                        list.add(recipeDesc.withColor(ChatFormatting.YELLOW));
                        list.add(levelCondition.withColor(ChatFormatting.YELLOW, TooltipEntry.num(recipe.levelCondition)));
                        list.add(Component.empty());
                    })
                    .addIngredients(recipe.input);
        } else {
            builder.addSlot(RecipeIngredientRole.OUTPUT, 138, 21)
                    .setSlotName("output")
                    .addItemStack(recipe.output);
        }
    }
}
