package com.xiaoyue.celestial_equipments.content.equipments.tool;

import com.xiaoyue.celestial_equipments.content.items.generic.GenericDiggerItem;
import com.xiaoyue.celestial_equipments.content.library.DiggerType;
import com.xiaoyue.celestial_invoker.content.common.entry.ToolStats;
import com.xiaoyue.celestial_invoker.invoker.config.ConfigHolderEntry;
import com.xiaoyue.celestial_invoker.invoker.config.value.DoubleConfigEntry;
import com.xiaoyue.celestial_invoker.invoker.tooltip.SubscribeTooltip;
import com.xiaoyue.celestial_invoker.invoker.tooltip.TooltipEntry;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.entity.player.PlayerEvent;

import java.util.List;

public class FinalShovel extends GenericDiggerItem {
    public static ToolStats STAT = ToolStats.builder().durability(4500).speed(7.5f).attack(5f).enchant(12).build();

    public FinalShovel() {
        super(STAT, DiggerType.SHOVEL);
    }

    @ConfigHolderEntry(category = "tool")
    public static DoubleConfigEntry bonusConfig = DoubleConfigEntry.defineBigRange("Final Shovel Speed Bonus", 0.55,
            "Increased digging speed when health drops below a certain threshold");

    @ConfigHolderEntry(category = "tool")
    public static DoubleConfigEntry hpConditionConfig = DoubleConfigEntry.defineChance("Final Shovel Health Condition", 0.5,
            "Increases digging speed when health drops below a certain level");

    @SubscribeTooltip(id = "final_shovel")
    public static TooltipEntry tooltip = TooltipEntry.define("When health drops below %s, digging speed increases %s");

    @Override
    public void getBreakSpeed(ItemStack stack, Player player, PlayerEvent.BreakSpeed event, int lv) {
        if (player.getHealth() <= player.getMaxHealth() * hpConditionConfig.floatValue()) {
            event.setNewSpeed(event.getOriginalSpeed() * (1 + bonusConfig.floatValue()));
        }
    }

    @Override
    public void addTooltips(ItemStack stack, List<Component> list, int lv) {
        list.add(tooltip.withGray(TooltipEntry.per(bonusConfig.get()), TooltipEntry.per(hpConditionConfig.get())));
    }
}
