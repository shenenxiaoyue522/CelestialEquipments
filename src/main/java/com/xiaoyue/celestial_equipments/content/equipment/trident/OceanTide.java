package com.xiaoyue.celestial_equipments.content.equipment.trident;

import com.google.common.collect.Multimap;
import com.xiaoyue.celestial_equipments.content.entities.SimpleTridentEntity;
import com.xiaoyue.celestial_equipments.content.items.generic.UpgradeableTrident;
import com.xiaoyue.celestial_equipments.utils.EquipmentUtils;
import com.xiaoyue.celestial_invoker.content.ancillary.entry.AttributeAdder;
import com.xiaoyue.celestial_invoker.invoker.tooltip.SubscribeTooltip;
import com.xiaoyue.celestial_invoker.invoker.tooltip.TooltipEntry;
import com.xiaoyue.celestial_invoker.invoker.tooltip.TooltipHolder;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

import java.util.List;

public class OceanTide extends UpgradeableTrident {
    public OceanTide() {
        super(new Properties().durability(4000));
    }

    @SubscribeTooltip(id = "ocean_tide")
    public static TooltipHolder tooltips = TooltipHolder.define(
            TooltipEntry.define("It comes with riptide enchantment effect"),
            TooltipEntry.define("Ability to trigger riptide at any time"));

    @Override
    public void addEquipmentTooltips(ItemStack stack, List<Component> list, int lv) {
        for (TooltipEntry tooltip : tooltips) {
            list.add(tooltip.withGray());
        }
    }

    @Override
    protected AbstractArrow getThrownEntity(Level level, Player player, ItemStack trident) {
        return new SimpleTridentEntity(player, level, trident);
    }

    @Override
    public void onFullCharge(Level pLevel, Player player, ItemStack pStack, int riptide) {
        int lv = EquipmentUtils.getLevel(pStack);
        if (lv > 0) {
            useRiptide(player, 1 + riptide);
        }
        super.onFullCharge(pLevel, player, pStack, riptide);
    }

    @Override
    protected void addAttributes(EquipmentSlot slot, ItemStack stack, Multimap<Attribute, AttributeModifier> map) {
        if (slot == EquipmentSlot.MAINHAND) {
            AttributeAdder builder = AttributeAdder.builder().name("Tool Modifier");
            builder.uuid(BASE_ATTACK_DAMAGE_UUID).value(8 + EquipmentUtils.getLevel(stack)).toMap(map);
            builder.attr(Attributes.ATTACK_SPEED).uuid(BASE_ATTACK_SPEED_UUID).value(-2.8).toMap(map);
        }
    }
}
