package com.xiaoyue.celestial_equipments.content.items.curios;

import com.google.common.collect.LinkedHashMultimap;
import com.google.common.collect.Multimap;
import com.xiaoyue.celestial_equipments.register.CEItems;
import com.xiaoyue.celestial_invoker.content.common.entry.AttributeAdder;
import com.xiaoyue.celestial_invoker.invoker.config.ConfigHolderEntry;
import com.xiaoyue.celestial_invoker.invoker.config.value.DoubleConfigEntry;
import com.xiaoyue.celestial_invoker.invoker.tooltip.SubscribeTooltip;
import com.xiaoyue.celestial_invoker.invoker.tooltip.TooltipEntry;
import com.xiaoyue.celestial_invoker.invoker.tooltip.TooltipHolder;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;
import top.theillusivec4.curios.api.SlotContext;

import java.util.List;
import java.util.UUID;

public class TalosBracer extends CurioItem {
    public TalosBracer(Properties properties) {
        super(properties);
    }

    @ConfigHolderEntry(category = "curios")
    public static DoubleConfigEntry hpBonusConfig = DoubleConfigEntry.defineFromZero("Talos Bracer Max Health Bonus",
            10, 10000, "Talos Bracer: Max Health Bonus");

    @SubscribeTooltip(id = "talos_bracer")
    public static TooltipHolder tooltips = TooltipHolder.define(
            TooltipEntry.define("While holding left-click"),
            TooltipEntry.define("If the attack cooldown is complete and the crosshair is aimed at a creature"),
            TooltipEntry.define("Automatically attacks the targeted creature within reach range"));

    @Override
    public void appendHoverText(ItemStack pStack, @Nullable Level pLevel, List<Component> list, TooltipFlag pIsAdvanced) {
        tooltips.forEach(tooltip -> list.add(tooltip.withGray()));
    }

    @Override
    public Multimap<Attribute, AttributeModifier> getAttributeModifiers(SlotContext slotContext, UUID uuid, ItemStack stack) {
        Multimap<Attribute, AttributeModifier> map = LinkedHashMultimap.create();
        AttributeAdder.builder().attr(Attributes.MAX_HEALTH).uuid(uuid)
                .name(CEItems.TALOS_BRACER.getId().toString()).value(hpBonusConfig.get()).toMap(map);
        return map;
    }
}
