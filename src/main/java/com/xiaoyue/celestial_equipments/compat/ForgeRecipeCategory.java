package com.xiaoyue.celestial_equipments.compat;

import com.xiaoyue.celestial_equipments.CelestialEquipments;
import com.xiaoyue.celestial_equipments.content.container.CEForgeTableScreen;
import com.xiaoyue.celestial_equipments.content.items.generic.ICelestialEquip;
import com.xiaoyue.celestial_equipments.content.recipes.CEForgeRecipe;
import com.xiaoyue.celestial_equipments.data.CETagGen;
import com.xiaoyue.celestial_equipments.register.CEBlocks;
import com.xiaoyue.celestial_equipments.register.CEItems;
import com.xiaoyue.celestial_equipments.utils.EquipmentUtils;
import com.xiaoyue.celestial_invoker.invoker.tooltip.SubscribeTooltip;
import com.xiaoyue.celestial_invoker.invoker.tooltip.TooltipEntry;
import dev.xkmc.l2library.serial.recipe.BaseRecipeCategory;
import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.helpers.IJeiHelpers;
import mezz.jei.api.recipe.IFocus;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.RecipeIngredientRole;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ForgeRecipeCategory extends BaseRecipeCategory<CEForgeRecipe, ForgeRecipeCategory> {
    public ForgeRecipeCategory() {
        super(CelestialEquipments.loc("forge_recipe"), CEForgeRecipe.class);
    }

    @SubscribeTooltip(key = "jei.forge_recipe.is_upgrade_recipe")
    public static TooltipEntry upgradeRecipeTooltip = TooltipEntry.define("This is an equipment upgrade recipe");

    @SubscribeTooltip(key = "jei.forge_recipe.upgrade_recipe_condition")
    public static TooltipEntry levelConditionTooltip = TooltipEntry.define("Applies to equipment when level is below %s level");

    public ForgeRecipeCategory init(IJeiHelpers jeiHelpers) {
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
        Ingredient input = recipe.input;
        Ingredient output = Ingredient.of(recipe.output);
        Optional<IFocus<ItemStack>> first = group.getItemStackFocuses(RecipeIngredientRole.INPUT).findFirst();
        if (recipe.isUpgrade) {
            if (first.isPresent()) {
                if (first.get().getTypedValue().getIngredient().is(CETagGen.NOT_UPGRADEABLE)) {
                    return;
                }
            }
            if (group.getItemStackFocuses(RecipeIngredientRole.OUTPUT).findFirst().isPresent()) {
                return;
            }
            List<ItemStack> stacks = new ArrayList<>();
            if (first.isPresent()) {
                ItemStack stack = first.get().getTypedValue().getIngredient().copy();
                input = Ingredient.of(first.get().getTypedValue().getIngredient());
                if (!stack.is(CEBlocks.ASSEMBLY_TABLE.asItem()) && recipe.levelCondition >= EquipmentUtils.getLevel(stack)) {
                    EquipmentUtils.upLevel(stack);
                    output = Ingredient.of(stack);
                }
            } else {
                for (ItemStack stack : recipe.input.getItems()) {
                    ItemStack copy = stack.copy();
                    if (recipe.levelCondition > EquipmentUtils.getLevel(copy)) {
                        EquipmentUtils.upLevel(copy);
                        stacks.add(copy);
                    }
                }
                output = Ingredient.of(stacks.stream());
            }
            if (output.isEmpty()) {
                return;
            }
            builder.addSlot(RecipeIngredientRole.OUTPUT, 138, 21)
                    .setSlotName("output")
                    .addIngredients(output);
            builder.addSlot(RecipeIngredientRole.RENDER_ONLY, 138, 3)
                    .setSlotName("upgradeable").addItemStack(Items.COMPASS.getDefaultInstance())
                    .addTooltipCallback((view, list) -> {
                        list.clear();
                        list.add(upgradeRecipeTooltip.withColor(ChatFormatting.YELLOW));
                        if (recipe.levelCondition > 0) {
                            list.add(levelConditionTooltip.withColor(ChatFormatting.YELLOW, TooltipEntry.num(recipe.levelCondition)));
                        }
                        list.add(Component.empty());
                    });
        } else {
            builder.addSlot(RecipeIngredientRole.OUTPUT, 138, 21)
                    .setSlotName("output")
                    .addItemStack(recipe.output);
        }
        builder.addSlot(RecipeIngredientRole.INPUT, 11, 21)
                .setSlotName("input")
                .addIngredients(input);
        int x = 53;
        int y = 2;
        for (int i = 0; i < 9; i++) {
            if (recipe.materials.size() > i) {
                builder.addSlot(RecipeIngredientRole.CATALYST, x + 1, y + 1)
                        .setSlotName("input" + i)
                        .addIngredients(recipe.materials.get(i));
            }
            y += 18;
            if (y >= 56) {
                y -= 54;
                x += 18;
            }
        }
    }
}
