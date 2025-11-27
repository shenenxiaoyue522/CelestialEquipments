package com.xiaoyue.celestial_equipments.utils;

import com.xiaoyue.celestial_equipments.content.items.generic.UpgradeableBow;
import com.xiaoyue.celestial_equipments.content.library.ICEquipment;
import com.xiaoyue.celestial_invoker.invoker.config.ConfigHolderEntry;
import com.xiaoyue.celestial_invoker.invoker.config.value.IntConfigEntry;
import com.xiaoyue.celestial_invoker.invoker.tooltip.SubscribeTooltip;
import com.xiaoyue.celestial_invoker.invoker.tooltip.TooltipEntry;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;

import java.util.List;

public class EquipmentUtils {

    public static final String EXP = "CelestialEquipments_Exp";
    public static final String LEVEL = "CelestialEquipments_Level";

    @SubscribeTooltip(id = "equipment_level")
    public static TooltipEntry equipmentLevelInfo = TooltipEntry.define("Equipment's rank: %s");

    @SubscribeTooltip(id = "equipment_exp")
    public static TooltipEntry equipmentExpInfo = TooltipEntry.define("Equipment's experience: %s / %s");

    @ConfigHolderEntry(category = "misc")
    public static IntConfigEntry maxLevel = IntConfigEntry.define("Equipment Max Level", 20, 1, 1000,
            "Equipment max level");

    @ConfigHolderEntry(category = "misc")
    public static IntConfigEntry maxExp = IntConfigEntry.define("Equipment Max Exp", 500, 1, 9999999,
            "Equipment max exp");

    public static void addExpTooltips(List<Component> list, ItemStack stack) {
        list.add(equipmentLevelInfo.withGray(TooltipEntry.num(getLevel(stack))));
        list.add(equipmentExpInfo.withGray(TooltipEntry.num(getExp(stack)), TooltipEntry.num(getMaxExp())));
    }

    public static void addBowStatsTooltips(List<Component> list, float damage, float drawSpeed, float arrowSpeed) {
        list.add(UpgradeableBow.bowDamageInfo.withColor(ChatFormatting.BLUE, TooltipEntry.num(damage)));
        list.add(UpgradeableBow.drawSpeedInfo.withColor(ChatFormatting.BLUE, TooltipEntry.num(drawSpeed)));
        list.add(UpgradeableBow.arrowSpeedInfo.withColor(ChatFormatting.BLUE, TooltipEntry.chance(arrowSpeed)));
    }

    public static int getMaxExp() {
        return maxExp.get();
    }

    public static int getMaxLevel() {
        return maxLevel.get();
    }

    public static void upGear(ItemStack stack) {
        addLevel(stack);
        removeExp(stack);
    }

    public static int getLevel(ItemStack stack) {
        return stack.getItem() instanceof ICEquipment ? stack.getOrCreateTag().getInt(LEVEL) : 0;
    }

    public static void addLevel(ItemStack stack) {
        int level = getLevel(stack);
        if (level < getMaxLevel()) {
            stack.getOrCreateTag().putInt(LEVEL, level + 1);
        }
    }

    public static int getExp(ItemStack stack) {
        return stack.getItem() instanceof ICEquipment ? stack.getOrCreateTag().getInt(EXP) : 0;
    }

    public static void addExp(ItemStack stack, int amount) {
        int exp = getExp(stack);
        if (stack.getItem() instanceof ICEquipment && exp < getMaxExp()) {
            int toAdd = Math.min(getMaxExp(), exp + amount);
            stack.getOrCreateTag().putInt(EXP, toAdd);
        }
    }

    public static void removeExp(ItemStack stack) {
        if (stack.getItem() instanceof ICEquipment) {
            stack.getOrCreateTag().putInt(EXP, 0);
        }
    }

    public static boolean isFullExp(ItemStack stack) {
        return getExp(stack) == getMaxExp();
    }
}

