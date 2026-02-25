package com.xiaoyue.celestial_equipments.content.equipments.digger;

import com.xiaoyue.celestial_equipments.content.items.generic.IGenericDigger;
import com.xiaoyue.celestial_invoker.content.common.entry.ToolStats;
import com.xiaoyue.celestial_invoker.invoker.tooltip.SubscribeTooltip;
import com.xiaoyue.celestial_invoker.invoker.tooltip.TooltipEntry;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.level.block.state.BlockState;

import java.util.List;

public class RadiantTreasure extends IGenericDigger.Pickaxe {
    public static final ToolStats STAT = ToolStats.builder().durability(3200).speed(8f).attack(6f).level(3).enchant(22).build();

    public RadiantTreasure() {
        super(STAT, new Item.Properties().rarity(Rarity.UNCOMMON));
    }

    @SubscribeTooltip(id = "radiant_treasure")
    public static TooltipEntry tooltip = TooltipEntry.define("When digging, you can get block drops regardless of the digging level");

    @Override
    public void addEquipmentTooltips(ItemStack stack, List<Component> list) {
        list.add(tooltip.withGray());
    }

    @Override
    public boolean isCorrectToolForDrops(ItemStack stack, BlockState state) {
        return this.isEnabled();
    }
}
