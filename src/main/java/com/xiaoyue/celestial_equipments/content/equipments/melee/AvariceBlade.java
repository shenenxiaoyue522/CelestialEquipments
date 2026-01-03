package com.xiaoyue.celestial_equipments.content.equipments.melee;

import com.xiaoyue.celestial_equipments.content.items.generic.UpgradeableMelee;
import com.xiaoyue.celestial_equipments.content.library.MeleeType;
import com.xiaoyue.celestial_invoker.invoker.config.ConfigHolderEntry;
import com.xiaoyue.celestial_invoker.invoker.config.value.DoubleConfigEntry;
import com.xiaoyue.celestial_invoker.invoker.tooltip.SubscribeTooltip;
import com.xiaoyue.celestial_invoker.invoker.tooltip.TooltipEntry;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;

import java.util.List;

public class AvariceBlade extends UpgradeableMelee {
    public AvariceBlade() {
        super(MeleeType.GENERIC);
    }

    @ConfigHolderEntry(category = "melee")
    public static DoubleConfigEntry expBonusConfig = DoubleConfigEntry.defineSmallRange("Avarice Blade Exp Drop Bonus",
            0.04, "Avarice Blade: Extra experience drop by this value");

    @SubscribeTooltip(id = "avarice_blade")
    public static TooltipEntry tooltip = TooltipEntry.define(
            "Experience of dropping an extra %s when killing the target");

    public float getSpeed(int lv) {
        return 0.1f;
    }

    public void addEquipmentTooltips(ItemStack stack, List<Component> list, int lv) {
        list.add(tooltip.withGray(TooltipEntry.per(expBonusConfig.get() * lv)));
    }
}
