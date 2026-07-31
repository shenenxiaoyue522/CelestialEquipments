package com.xiaoyue.celestial_equipments.content.equipments.trident;

import com.google.common.collect.Multimap;
import com.xiaoyue.celestial_equipments.content.items.generic.UpgradeableTrident;
import com.xiaoyue.celestial_equipments.content.library.SimpleThrowingFactory;
import com.xiaoyue.celestial_equipments.utils.EquipmentUtils;
import com.xiaoyue.celestial_invoker.content.common.entry.AttributeAdder;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.ItemStack;

public class BloodyPiercer extends UpgradeableTrident implements SimpleThrowingFactory {
    public BloodyPiercer(Properties pProperties) {
        super(pProperties);
    }

    @Override
    protected void addAttributes(EquipmentSlot slot, ItemStack stack, Multimap<Attribute, AttributeModifier> map) {
        if (slot == EquipmentSlot.MAINHAND) {
            AttributeAdder builder = AttributeAdder.builder().name("Tool Modifier");
            builder.uuid(BASE_ATTACK_DAMAGE_UUID).value(5 + EquipmentUtils.getLevel(stack) * 1f).toMap(map);
            builder.attr(Attributes.ATTACK_SPEED).uuid(BASE_ATTACK_SPEED_UUID).value(-2.4).toMap(map);
        }
    }
}
