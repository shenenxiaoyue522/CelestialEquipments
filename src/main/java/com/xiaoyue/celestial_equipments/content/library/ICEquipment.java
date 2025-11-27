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

public interface ICEquipment {

    @SubscribeTooltip(id = "item_cooldown")
    TooltipEntry itemCooldownInfo = TooltipEntry.define("Cooldown time: %s seconds");

    @SubscribeTooltip(id = "shift_down")
    TooltipEntry shiftDownInfo = TooltipEntry.define("Press [%s] to display equipment info details");

    @SubscribeTooltip(id = "item_ban")
    TooltipEntry itemBanInfo = TooltipEntry.define("This item is disabled");

    Item self();

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

    default void addBaseTooltips(ItemStack stack, List<Component> list, boolean singleLevel) {
        list.add(Component.empty());
        if (!Screen.hasShiftDown()) {
            list.add(shiftDown.withGray(Component.literal("SHIFT").withStyle(ChatFormatting.YELLOW)));
        } else {
            EquipmentUtils.addExpTooltips(list, stack);
            if (EquipmentUtils.getLevel(stack) > 0 || singleLevel) {
                list.add(Component.empty());
                this.addEquipmentTooltips(stack, list);
            }
        }
    }

    default void addBaseTooltips(ItemStack stack, List<Component> list) {
        this.addBaseTooltips(stack, list, false);
    }

    default void addEquipmentTooltips(ItemStack stack, List<Component> list) {
        this.addEquipmentTooltips(stack, list, EquipmentUtils.getLevel(stack));
    }

    default void addEquipmentTooltips(ItemStack stack, List<Component> list, int lv) {
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

    default Rarity getGearRarity(int level) {
        return switch (level) {
            case 0, 1, 2 -> Rarity.COMMON;
            case 3 -> IRarityUtils.GREEN;
            case 4 -> Rarity.UNCOMMON;
            default -> Rarity.EPIC;
        };
    }
}
