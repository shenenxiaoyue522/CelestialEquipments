package com.xiaoyue.celestial_equipments.content.items.curios;

import com.google.common.collect.LinkedHashMultimap;
import com.google.common.collect.Multimap;
import com.xiaoyue.celestial_equipments.content.items.generic.BaseCurioItem;
import com.xiaoyue.celestial_invoker.content.common.entry.AttributeAdder;
import com.xiaoyue.celestial_invoker.invoker.config.ConfigHolderEntry;
import com.xiaoyue.celestial_invoker.invoker.config.value.DoubleConfigEntry;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.common.ForgeMod;
import top.theillusivec4.curios.api.SlotContext;

import java.util.UUID;

public class AmethystGauntlets extends BaseCurioItem {
    public AmethystGauntlets(Properties properties) {
        super(properties);
    }

    @ConfigHolderEntry(category = "curios")
    public static DoubleConfigEntry attackBonusConfig = DoubleConfigEntry.defineBigRange("Amethyst Gauntlets Attack Bonus",
            0.08, "Amethyst Gauntlets: Attack damage bonus");

    @ConfigHolderEntry(category = "curios")
    public static DoubleConfigEntry reachBonusConfig = DoubleConfigEntry.defineSmallRange("Amethyst Gauntlets Reach Bonus",
            1, "Amethyst Gauntlets: Entity reach bonus");

    @Override
    public Multimap<Attribute, AttributeModifier> getAttributeModifiers(SlotContext slotContext, UUID uuid, ItemStack stack) {
        Multimap<Attribute, AttributeModifier> map = LinkedHashMultimap.create();
        AttributeAdder builder = AttributeAdder.builder().uuid(uuid);
        builder.name(bonusModifierName("attack")).value(attackBonusConfig.get()).operation(1).toMap(map);
        builder.attr(ForgeMod.ENTITY_REACH.get()).name(bonusModifierName("entity_reach")).value(reachBonusConfig.get()).operation(0).toMap(map);
        return map;
    }
}
