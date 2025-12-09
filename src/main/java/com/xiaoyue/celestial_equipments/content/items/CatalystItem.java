package com.xiaoyue.celestial_equipments.content.items;

import com.xiaoyue.celestial_invoker.invoker.config.ConfigHolderEntry;
import com.xiaoyue.celestial_invoker.invoker.config.value.DoubleConfigEntry;
import com.xiaoyue.celestial_invoker.invoker.tooltip.SubscribeTooltip;
import com.xiaoyue.celestial_invoker.invoker.tooltip.TooltipEntry;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class CatalystItem extends Item {
    private final Type type;

    public CatalystItem(Item.Properties pProperties, Type type) {
        super(pProperties);
        this.type = type;
    }

    public Type getType() {
        return this.type;
    }

    @SubscribeTooltip(id = "upgrade_stone_info")
    public static TooltipEntry upgradeStoneTooltip = TooltipEntry.define(
            "When the equipment experience is maxed, forge with the item on the anvil to upgrade the equipment");

    @SubscribeTooltip(id = "upgrade_stone_condition")
    public static TooltipEntry upgradeStoneConditionTooltip = TooltipEntry.define("Scope of application: below %s level");

    @Override
    public void appendHoverText(ItemStack pStack, @Nullable Level pLevel, List<Component> list, TooltipFlag pIsAdvanced) {
        list.add(upgradeStoneTooltip.withGray());
        list.add(upgradeStoneConditionTooltip.withGray(TooltipEntry.num(this.type.getCondition())));
    }

    public enum Type {
        BASIC(5),
        INTERMEDIATE(10),
        ADVANCED(20);

        public final int baseCondition;

        Type(int baseCondition) {
            this.baseCondition = baseCondition;
        }

        @ConfigHolderEntry(category = "misc")
        public static final DoubleConfigEntry upgradeConditionScaleConfig = DoubleConfigEntry.define("Upgrade Condition Scale",
                1, 0, 10000, "Luck bonus");


        public int getCondition() {
            return (int) (baseCondition * upgradeConditionScaleConfig.get());
        }
    }
}
