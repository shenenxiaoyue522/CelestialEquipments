package com.xiaoyue.celestial_equipments.content.items.equipment.armor;

import com.xiaoyue.celestial_equipments.content.items.generic.GenericArmor;
import com.xiaoyue.celestial_invoker.content.ancillary.material.ArmorMate;
import com.xiaoyue.celestial_invoker.invoker.tooltip.SubscribeTooltip;
import com.xiaoyue.celestial_invoker.invoker.tooltip.TooltipEntry;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.world.item.ItemStack;

import java.util.List;

public class SakuraEchoesArmor extends GenericArmor {
    public SakuraEchoesArmor(Type pType) {
        super(mate(), pType, new Properties());
    }

    @SubscribeTooltip(id = "sakura_echoes_set")
    public static TooltipEntry setName = TooltipEntry.define("Sakura blossoms echoed");

    public static String getArmorName(Type type) {
        return switch (type) {
            case HELMET -> "sakura_falling";
            case CHESTPLATE -> "sakura_love";
            case LEGGINGS -> "sakura_sky";
            case BOOTS -> "sakura_dream";
        };
    }

    private static ArmorMate mate() {
        return ArmorMate.builder().build();
    }

    @Override
    public void addEffectTooltips(ItemStack stack, List<Component> list, int lv) {
        super.addEffectTooltips(stack, list, lv);
    }

    @Override
    public boolean hasArmorSetTooltip(ItemStack stack) {
        return true;
    }

    @Override
    public void addArmorSetTooltips(ItemStack stack, List<Component> list) {
        list.add(Component.empty());
        super.addArmorSetTooltips(stack, list);
    }

    @Override
    public MutableComponent getArmorSetName() {
        return setName.withColor(ChatFormatting.LIGHT_PURPLE);
    }

    @Override
    public void addArmorSetEffectTooltips(ItemStack stack, List<Component> list) {
        list.add(Component.literal("xxx"));
        list.add(Component.literal("xxxxxxx"));
        list.add(Component.empty());
    }

}
