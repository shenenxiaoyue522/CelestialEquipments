package com.xiaoyue.celestial_equipments.content.items.curios;

import com.google.common.collect.LinkedHashMultimap;
import com.google.common.collect.Multimap;
import com.xiaoyue.celestial_equipments.content.items.generic.BaseCurioItem;
import com.xiaoyue.celestial_invoker.content.common.entry.AttributeAdder;
import com.xiaoyue.celestial_invoker.invoker.config.ConfigHolderEntry;
import com.xiaoyue.celestial_invoker.invoker.config.value.DoubleConfigEntry;
import com.xiaoyue.celestial_invoker.invoker.config.value.IntConfigEntry;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.ItemStack;
import top.theillusivec4.curios.api.SlotContext;

import java.util.UUID;

public class SovereignCrown extends BaseCurioItem {
    public SovereignCrown(Properties properties) {
        super(properties);
    }

    @ConfigHolderEntry(category = "curios")
    public static DoubleConfigEntry attackBonusConfig = DoubleConfigEntry.defineBigRange("Sovereign Crown Attack Bonus",
            0.1, "Sovereign Crown: Attack damage bonus");

    @ConfigHolderEntry(category = "curios")
    public static DoubleConfigEntry attackSpeedBonusConfig = DoubleConfigEntry.defineBigRange("Sovereign Crown Attack Speed Bonus",
            0.1, "Sovereign Crown: Attack speed bonus");

    @ConfigHolderEntry(category = "curios")
    public static IntConfigEntry healthBonusConfig = IntConfigEntry.defineFromZero("Sovereign Crown Health Bonus",
            10, 1000, "Sovereign Crown: Max health bonus");

    @ConfigHolderEntry(category = "curios")
    public static IntConfigEntry toughnessBonusConfig = IntConfigEntry.defineFromZero("Sovereign Crown Toughness Bonus",
            4, 1000, "Sovereign Crown: Armor toughness bonus");

    @Override
    public Multimap<Attribute, AttributeModifier> getAttributeModifiers(SlotContext slotContext, UUID uuid, ItemStack stack) {
        Multimap<Attribute, AttributeModifier> map = LinkedHashMultimap.create();
        AttributeAdder builder = AttributeAdder.builder().uuid(uuid);
        builder.name(bonusModifierName("attack")).operation(1).value(attackBonusConfig.get()).toMap(map);
        builder.attr(Attributes.ATTACK_SPEED).name(bonusModifierName("attack_speed")).value(attackSpeedBonusConfig.get()).toMap(map);
        builder.attr(Attributes.MAX_HEALTH).name(bonusModifierName("health")).operation(0).value(healthBonusConfig.get()).toMap(map);
        builder.attr(Attributes.ARMOR_TOUGHNESS).name(bonusModifierName("toughness")).value(toughnessBonusConfig.get()).toMap(map);
        return map;
    }
}
