package com.xiaoyue.celestial_equipments.content.library;

import com.xiaoyue.celestial_core.utils.IRarityUtils;
import com.xiaoyue.celestial_equipments.data.CEModConfig;
import com.xiaoyue.celestial_equipments.data.CETagGen;
import com.xiaoyue.celestial_equipments.utils.EquipmentUtils;
import com.xiaoyue.celestial_invoker.invoker.tooltip.SubscribeTooltip;
import com.xiaoyue.celestial_invoker.invoker.tooltip.TooltipEntry;
import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;

import java.util.List;

public interface ICelestialEquip {

    @SubscribeTooltip(id = "item_cooldown")
    TooltipEntry cooldownTooltip = TooltipEntry.define("Cooldown time: %s seconds");

    @SubscribeTooltip(id = "shift_down")
    TooltipEntry shiftDownTooltip = TooltipEntry.define("Press [%s] to display equipment info details");

    @SubscribeTooltip(id = "item_ban")
    TooltipEntry itemBanTooltip = TooltipEntry.define("This item is disabled");

    default Item self() {
        return (Item) this;
    }

    default boolean isUpgradeable() {
        return !this.self().getDefaultInstance().is(CETagGen.NOT_UPGRADEABLE);
    }

    default boolean isEnabled() {
        return CEModConfig.enabled(self());
    }

    default Component getItemName(ItemStack stack) {
        MutableComponent defaultName = Component.translatable(this.self().getDescriptionId(stack));
        return !this.isEnabled() ? defaultName.withStyle(ChatFormatting.GRAY).withStyle(ChatFormatting.STRIKETHROUGH) : defaultName;
    }

    default void addAbstractText(boolean singleLevel, List<Component> list, ItemStack stack, int lv) {
        if (!singleLevel) {
            EquipmentUtils.addExpTooltips(list, stack);
            if (lv > 0 ) {
                this.addTooltips(stack, list, lv);
            }
        } else {
            this.addTooltips(stack, list, lv);
        }
    }

    default void addBaseTooltips(ItemStack stack, List<Component> list, boolean singleLevel) {
        int lv = EquipmentUtils.getLevel(stack);
        list.add(Component.empty());
        if (!requiredShiftDown()) {
            addAbstractText(singleLevel, list, stack, lv);
        } else if (Screen.hasShiftDown()) {
            addAbstractText(singleLevel, list, stack, lv);
        } else {
            list.add(shiftDownTooltip.withGray(Component.literal("SHIFT").withStyle(ChatFormatting.YELLOW)));
        }
    }

    default void addBaseTooltips(ItemStack stack, List<Component> list) {
        this.addBaseTooltips(stack, list, !isUpgradeable());
    }

    default void addTooltips(ItemStack stack, List<Component> list, int lv) {
    }

    default boolean requiredShiftDown() {
        return true;
    }

    default boolean noCooldown(LivingEntity entity) {
        if (entity instanceof Player player) {
            return !player.getCooldowns().isOnCooldown(this.self());
        } else {
            return false;
        }
    }

    default void addCooldown(LivingEntity entity, int time) {
        if (entity instanceof Player player) {
            player.getCooldowns().addCooldown(this.self(), time);
        }
    }

    default Rarity getEquipmentRarity(int level) {
        return switch (level) {
            case 0, 1, 2 -> Rarity.COMMON;
            case 3 -> IRarityUtils.GREEN;
            case 4 -> Rarity.UNCOMMON;
            default -> Rarity.EPIC;
        };
    }
}
