package com.xiaoyue.celestial_equipments.content.equipment.crossbow;

import com.google.common.collect.LinkedHashMultimap;
import com.google.common.collect.Multimap;
import com.xiaoyue.celestial_core.register.CCAttributes;
import com.xiaoyue.celestial_equipments.CelestialEquipments;
import com.xiaoyue.celestial_equipments.content.items.generic.UpgradeableCrossbow;
import com.xiaoyue.celestial_equipments.utils.EquipmentUtils;
import com.xiaoyue.celestial_invoker.content.ancillary.entry.AttributeAdder;
import com.xiaoyue.celestial_invoker.invoker.config.ConfigHolderEntry;
import com.xiaoyue.celestial_invoker.invoker.config.value.DoubleConfigEntry;
import com.xiaoyue.celestial_invoker.invoker.tooltip.SubscribeTooltip;
import com.xiaoyue.celestial_invoker.invoker.tooltip.TooltipEntry;
import com.xiaoyue.celestial_invoker.invoker.tooltip.TooltipHolder;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.Level;

import java.util.List;

public class VirtualGoldCrossbow extends UpgradeableCrossbow {
    public VirtualGoldCrossbow() {
        super(new Properties().durability(3000));
    }

    @ConfigHolderEntry(category = "crossbow")
    public static DoubleConfigEntry armorPenetration = DoubleConfigEntry.defineSmallRange("Virtual Gold Crossbow Penetration Bonus",
            0.1, "Each level of the Piercing enchantment increases armor penetration");

    @SubscribeTooltip(id = "virtual_gold_crossbow")
    public static TooltipHolder tooltips = TooltipHolder.define(
            TooltipEntry.define("When you have the Multishot enchantment, it will fire immediately after reloading"),
            TooltipEntry.define("When you have a Quick Charge enchantment, the charge speed is greatly reduced"),
            TooltipEntry.define("When you have the Piercing enchantment, you can increase your armor penetration attribute")
    );

    @Override
    public float getAttack(int lv) {
        return 1 + 0.3f * lv;
    }

    @Override
    public void addEquipmentTooltips(ItemStack stack, List<Component> list) {
        for (TooltipEntry tooltip : tooltips) {
            list.add(tooltip.withGray());
        }
    }

    @Override
    public void inventoryTick(ItemStack pStack, Level pLevel, Entity pEntity, int pSlotId, boolean pIsSelected) {
        if (pEntity instanceof Player player && pIsSelected) {
            int multishot = pStack.getEnchantmentLevel(Enchantments.MULTISHOT);
            if (isCharged(pStack) && multishot > 0 && EquipmentUtils.getLevel(pStack) > 0) {
                use(pLevel, player, player.getUsedItemHand());
            }
        }
    }

    @Override
    public Multimap<Attribute, AttributeModifier> getAttributeModifiers(EquipmentSlot slot, ItemStack stack) {
        Multimap<Attribute, AttributeModifier> map = LinkedHashMultimap.create();
        if (slot.equals(EquipmentSlot.MAINHAND) && EquipmentUtils.getLevel(stack) > 0) {
            int piercing = stack.getEnchantmentLevel(Enchantments.PIERCING);
            AttributeAdder.builder().attr(CCAttributes.ARMOR_PENETRATION.get()).nameWithUUID(CelestialEquipments.loc("virtual_gold_crossbow"))
                    .value(piercing * armorPenetration.get()).toMap(map);
        }
        return map;
    }

    @Override
    protected int getChargeTime(ItemStack crossbow) {
        if (crossbow.getEnchantmentLevel(Enchantments.QUICK_CHARGE) > 0 && EquipmentUtils.getLevel(crossbow) > 0) {
            return 5;
        }
        return super.getChargeTime(crossbow);
    }
}
