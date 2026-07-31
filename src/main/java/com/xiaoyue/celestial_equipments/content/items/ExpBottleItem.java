package com.xiaoyue.celestial_equipments.content.items;

import com.xiaoyue.celestial_invoker.invoker.tooltip.SubscribeTooltip;
import com.xiaoyue.celestial_invoker.invoker.tooltip.TooltipEntry;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class ExpBottleItem extends Item {
    private final int experience;

    public ExpBottleItem(Item.Properties pProperties, int experience) {
        super(pProperties);
        this.experience = experience;
    }

    public int getExperience() {
        return this.experience;
    }

    @SubscribeTooltip(id = "exp_bottle_info")
    public static TooltipEntry tooltip = TooltipEntry.define("Forging a equipment on an anvil increases the weapon's %s experience");

    @Override
    public void appendHoverText(ItemStack pStack, @Nullable Level pLevel, List<Component> list, TooltipFlag pIsAdvanced) {
        list.add(tooltip.withGray(TooltipEntry.num(this.experience)));
    }
}
