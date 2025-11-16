package com.xiaoyue.celestial_equipments.content.items.equipment.digger;

import com.xiaoyue.celestial_equipments.content.items.generic.IGenericDigger;
import com.xiaoyue.celestial_invoker.content.ancillary.material.ToolStats;
import com.xiaoyue.celestial_invoker.invoker.tooltip.SubscribeTooltip;
import com.xiaoyue.celestial_invoker.invoker.tooltip.TooltipEntry;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.block.state.BlockState;

import java.util.List;

public class RadiantTreasure extends IGenericDigger.Pickaxe {
    public static final ToolStats STAT = new ToolStats(3200, 8.0f, 6.0f, 3, 22, Ingredient.of());

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
