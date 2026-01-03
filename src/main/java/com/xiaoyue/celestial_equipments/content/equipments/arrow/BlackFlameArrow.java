package com.xiaoyue.celestial_equipments.content.equipments.arrow;

import com.xiaoyue.celestial_core.utils.EntityUtils;
import com.xiaoyue.celestial_equipments.content.items.generic.GenericArrowItem;
import com.xiaoyue.celestial_invoker.content.entities.GenericArrowEntity;
import com.xiaoyue.celestial_invoker.content.generic.builder.ArrowDataBuilder;
import com.xiaoyue.celestial_invoker.invoker.config.ConfigHolderEntry;
import com.xiaoyue.celestial_invoker.invoker.config.value.IntConfigEntry;
import com.xiaoyue.celestial_invoker.invoker.tooltip.SubscribeTooltip;
import com.xiaoyue.celestial_invoker.invoker.tooltip.TooltipEntry;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;

import java.util.List;

public class BlackFlameArrow extends GenericArrowItem {
    public BlackFlameArrow() {
        super(new Item.Properties().rarity(Rarity.EPIC), new ArrowDataBuilder()
                .damage(4.0).hitEntity(BlackFlameArrow::onHitEntity));
    }

    @ConfigHolderEntry(category = "arrow")
    public static IntConfigEntry burnTimeConfig = IntConfigEntry.define("Black Flame Arrow Burn Time",
            60, 20, 1000, "Black Flame Arrow: Burn time");

    @SubscribeTooltip(id = "black_flame_arrow")
    public static TooltipEntry tooltip = TooltipEntry.define(
            "Upon impact, the target is plunged into a %s seconds black flame burn", TooltipEntry.num(burnTimeConfig.get() / 20));

    public void addEquipmentTooltips(ItemStack stack, List<Component> list) {
        list.add(tooltip.withGray());
    }

    private static void onHitEntity(GenericArrowEntity arrow, Entity target) {
        if (target instanceof LivingEntity entity) {
            EntityUtils.setBlackFlameTime(entity, burnTimeConfig.get());
        }
    }
}
