package com.xiaoyue.celestial_equipments.content.items;

import com.xiaoyue.celestial_core.content.generic.CCTooltipItem;
import com.xiaoyue.celestial_invoker.invoker.config.ConfigHolderEntry;
import com.xiaoyue.celestial_invoker.invoker.config.value.IntConfigEntry;
import com.xiaoyue.celestial_invoker.invoker.tooltip.SubscribeTooltip;
import com.xiaoyue.celestial_invoker.invoker.tooltip.TooltipEntry;

public class RepairKitItem extends CCTooltipItem {
    public RepairKitItem(Properties pProperties) {
        super(pProperties, true, true, () -> tooltip.withGray(TooltipEntry.num(repairConfig.get())));
    }

    @ConfigHolderEntry(category = "misc")
    public static IntConfigEntry repairConfig = IntConfigEntry.defineFromZero("Repair Kit Repair Durability", 1200,
            200000, "The durability value restored for equipment");

    @SubscribeTooltip(id = "repair_kit")
    public static TooltipEntry tooltip = TooltipEntry.define("Restore %s durability to equipment");

}
