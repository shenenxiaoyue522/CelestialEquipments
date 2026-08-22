package com.xiaoyue.celestial_equipments.content.equipments.tool;

import com.xiaoyue.celestial_equipments.content.items.generic.GenericDiggerItem;
import com.xiaoyue.celestial_equipments.content.library.DiggerType;
import com.xiaoyue.celestial_invoker.content.common.entry.ToolStats;
import com.xiaoyue.celestial_invoker.invoker.config.ConfigHolderEntry;
import com.xiaoyue.celestial_invoker.invoker.config.value.IntConfigEntry;
import com.xiaoyue.celestial_invoker.invoker.tooltip.SubscribeTooltip;
import com.xiaoyue.celestial_invoker.invoker.tooltip.TooltipEntry;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;

import java.util.List;

public class LifeHoe extends GenericDiggerItem {
    public static ToolStats STAT = ToolStats.builder().durability(795).speed(5f).attack(4f).enchant(15).build();

    public LifeHoe() {
        super(STAT, DiggerType.HOE);
    }

    @ConfigHolderEntry(category = "tool")
    public static IntConfigEntry durabilityRecoveryConfig = IntConfigEntry.defineFromZero("Life Hoe Durability Recovery", 1,
            Integer.MAX_VALUE, "Durability restored during treatment");

    @SubscribeTooltip(id = "life_hoe")
    public static TooltipEntry tooltip = TooltipEntry.define("When healed, this tool restores %s durability");

    @Override
    public void addTooltips(ItemStack stack, List<Component> list, int lv) {
        list.add(tooltip.withGray(TooltipEntry.num(durabilityRecoveryConfig.get())));
    }
}
