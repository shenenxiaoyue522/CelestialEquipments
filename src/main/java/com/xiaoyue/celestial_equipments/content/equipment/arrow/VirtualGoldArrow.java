package com.xiaoyue.celestial_equipments.content.equipment.arrow;

import com.xiaoyue.celestial_core.data.CCDamageTypes;
import com.xiaoyue.celestial_core.utils.DelayUtils;
import com.xiaoyue.celestial_equipments.CelestialEquipments;
import com.xiaoyue.celestial_equipments.content.items.generic.GenericArrowItem;
import com.xiaoyue.celestial_invoker.content.entities.GenericArrowEntity;
import com.xiaoyue.celestial_invoker.content.generic.builder.ArrowDataBuilder;
import com.xiaoyue.celestial_invoker.invoker.config.ConfigHolderEntry;
import com.xiaoyue.celestial_invoker.invoker.config.value.DoubleConfigEntry;
import com.xiaoyue.celestial_invoker.invoker.tooltip.SubscribeTooltip;
import com.xiaoyue.celestial_invoker.invoker.tooltip.TooltipEntry;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;

import java.util.List;

public class VirtualGoldArrow extends GenericArrowItem {
    public VirtualGoldArrow() {
        super(new Item.Properties().rarity(Rarity.EPIC).fireResistant(), new ArrowDataBuilder()
                .damage(6.0).hitEntity(VirtualGoldArrow::onHitEntity));
    }

    @ConfigHolderEntry(category = "arrow")
    public static DoubleConfigEntry baseDmgConfig = DoubleConfigEntry.defineSmallRange(
            "Virtual Gold Arrow Base Damage Multiplier", 0.05, "Virtual Gold Arrow: Base damage multiplier");

    @ConfigHolderEntry(category = "arrow")
    public static DoubleConfigEntry inFireDmgConfig = DoubleConfigEntry.defineFromMinUsable(
            "Virtual Gold Arrow Damage Multiplier In Fire", 0.1, 100, "Virtual Gold Arrow: Damage multiplier when burning");

    @SubscribeTooltip(id = "virtual_gold_arrow")
    public static TooltipEntry tooltip = TooltipEntry.define(
            "When the target is hit, it deals 1 magic damage equal to %s of arrow damage every 1 second, for a total of 10 times, and if the target is burning, the damage becomes %s",
            TooltipEntry.per(baseDmgConfig.get()), TooltipEntry.per(inFireDmgConfig.get()));

    public void addEquipmentTooltips(ItemStack stack, List<Component> list) {
        list.add(tooltip.withGray());
    }

    private static void onHitEntity(GenericArrowEntity arrow, Entity target) {
        Entity entity = arrow.getOwner();
        if (entity instanceof LivingEntity owner) {
            float damage = (float)(target.isOnFire() ? arrow.getBaseDamage() * inFireDmgConfig.get() : arrow.getBaseDamage() * baseDmgConfig.get());
            DelayUtils.schedule(CelestialEquipments.loc("virtual_gold_arrow"), 20, 10, () -> target.hurt(CCDamageTypes.magic(owner), damage));
        }

    }
}
