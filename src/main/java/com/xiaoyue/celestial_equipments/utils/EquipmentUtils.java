package com.xiaoyue.celestial_equipments.utils;

import com.xiaoyue.celestial_equipments.content.items.generic.UpgradeableBow;
import com.xiaoyue.celestial_equipments.content.items.generic.UpgradeableTrident;
import com.xiaoyue.celestial_equipments.content.library.ICelestialEquip;
import com.xiaoyue.celestial_invoker.invoker.config.ConfigHolderEntry;
import com.xiaoyue.celestial_invoker.invoker.config.value.IntConfigEntry;
import com.xiaoyue.celestial_invoker.invoker.tooltip.SubscribeTooltip;
import com.xiaoyue.celestial_invoker.invoker.tooltip.TooltipEntry;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import top.theillusivec4.curios.api.CuriosApi;
import top.theillusivec4.curios.api.SlotResult;

import javax.annotation.Nullable;
import java.util.List;

public class EquipmentUtils {

    public static final String EXP = "CelestialEquipments_Exp";
    public static final String LEVEL = "CelestialEquipments_Level";

    @SubscribeTooltip(id = "equipment_level")
    public static TooltipEntry levelTooltip = TooltipEntry.define("Equipment's rank: %s");

    @SubscribeTooltip(id = "equipment_exp")
    public static TooltipEntry expTooltip = TooltipEntry.define("Equipment's experience: %s / %s");

    @ConfigHolderEntry(category = "misc")
    public static IntConfigEntry maxLevelConfig = IntConfigEntry.define("Equipment Max Level", 20, 1, 1000,
            "Equipment max level");

    @ConfigHolderEntry(category = "misc")
    public static IntConfigEntry maxExpConfig = IntConfigEntry.defineFromZero("Equipment Max Exp", 500, Integer.MAX_VALUE,
            "Equipment max exp");

    public static void addExpTooltips(List<Component> list, ItemStack stack) {
        list.add(levelTooltip.withGray(TooltipEntry.num(getLevel(stack))));
        list.add(expTooltip.withGray(TooltipEntry.num(getExp(stack)), TooltipEntry.num(getMaxExp())));
    }

    public static void addTridentStatsTooltips(List<Component> list, float damage, float throwSpeed) {
        list.add(UpgradeableTrident.throwDamageTooltip.withColor(ChatFormatting.BLUE, TooltipEntry.per(damage)));
        list.add(UpgradeableTrident.throwSpeedTooltip.withColor(ChatFormatting.BLUE, TooltipEntry.num(throwSpeed)));
    }

    public static void addBowStatsTooltips(List<Component> list, float damage, float drawSpeed, float arrowSpeed) {
        list.add(UpgradeableBow.bowDamageTooltip.withColor(ChatFormatting.BLUE, TooltipEntry.num(damage)));
        list.add(UpgradeableBow.drawSpeedTooltip.withColor(ChatFormatting.BLUE, TooltipEntry.num(drawSpeed)));
        list.add(UpgradeableBow.arrowSpeedTooltip.withColor(ChatFormatting.BLUE, TooltipEntry.per(arrowSpeed)));
    }

    public static int getMaxExp() {
        return maxExpConfig.get();
    }

    public static int getMaxLevel() {
        return maxLevelConfig.get();
    }

    public static void upLevel(ItemStack stack) {
        addLevel(stack);
        removeExp(stack);
    }

    public static int getLevel(ItemStack stack) {
        return stack.getItem() instanceof ICelestialEquip ? stack.getOrCreateTag().getInt(LEVEL) : 0;
    }

    public static void addLevel(ItemStack stack) {
        int level = getLevel(stack);
        if (level < getMaxLevel()) {
            stack.getOrCreateTag().putInt(LEVEL, level + 1);
        }
    }

    public static int getExp(ItemStack stack) {
        return stack.getItem() instanceof ICelestialEquip ? stack.getOrCreateTag().getInt(EXP) : 0;
    }

    public static void addExp(ItemStack stack, int amount) {
        int exp = getExp(stack);
        if (stack.getItem() instanceof ICelestialEquip && exp < getMaxExp()) {
            int toAdd = Math.min(getMaxExp(), exp + amount);
            stack.getOrCreateTag().putInt(EXP, toAdd);
        }
    }

    public static void removeExp(ItemStack stack) {
        if (stack.getItem() instanceof ICelestialEquip) {
            stack.getOrCreateTag().putInt(EXP, 0);
        }
    }

    public static boolean isFullExp(ItemStack stack) {
        return getExp(stack) == getMaxExp();
    }

    @Nullable
    public static SlotResult getCurio(LivingEntity entity, Item item) {
        var opt = CuriosApi.getCuriosInventory(entity).resolve();
        return opt.map(handler -> handler.findFirstCurio(item).get()).orElse(null);
    }

    public static boolean hasCurio(LivingEntity entity, Item item) {
        return getCurio(entity, item) != null;
    }
}

