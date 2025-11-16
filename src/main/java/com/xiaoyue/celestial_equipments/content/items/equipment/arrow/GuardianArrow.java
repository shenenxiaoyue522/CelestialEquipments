package com.xiaoyue.celestial_equipments.content.items.equipment.arrow;

import com.xiaoyue.celestial_core.utils.EntityUtils;
import com.xiaoyue.celestial_equipments.content.items.generic.GenericArrow;
import com.xiaoyue.celestial_invoker.content.entities.GenericArrowEntity;
import com.xiaoyue.celestial_invoker.content.generic.builder.ArrowDataBuilder;
import com.xiaoyue.celestial_invoker.invoker.tooltip.SubscribeTooltip;
import com.xiaoyue.celestial_invoker.invoker.tooltip.TooltipEntry;
import net.minecraft.network.chat.Component;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;

import java.util.List;

public class GuardianArrow extends GenericArrow {
    public GuardianArrow() {
        super(new Item.Properties().rarity(Rarity.RARE).fireResistant(), new ArrowDataBuilder()
                .damage(5.5).ignoreWater().hitEntity(GuardianArrow::onHitEntity));
    }

    @SubscribeTooltip(id = "guardian_arrow")
    public static TooltipEntry tooltip = TooltipEntry.define(
            "When it hits the target, it causes all mobs around it except the attacker to fall into %s and %s",
            TooltipEntry.eff(MobEffects.WEAKNESS), TooltipEntry.eff(MobEffects.MOVEMENT_SLOWDOWN));

    public void addEquipmentTooltips(ItemStack stack, List<Component> list) {
        list.add(tooltip.withGray());
    }

    private static void onHitEntity(GenericArrowEntity arrow, Entity target) {
        if (target instanceof LivingEntity t) {
            List<LivingEntity> list = EntityUtils.getExceptForCentralEntity(t, 4.0f, 2.0f);
            if (arrow.getOwner() != null) {
                Entity owner = arrow.getOwner();
                if (owner instanceof LivingEntity entity) {
                    list.remove(entity);
                }
            }
            for(LivingEntity entity : list) {
                EntityUtils.addEct(entity, MobEffects.WEAKNESS, 100);
                EntityUtils.addEct(entity, MobEffects.MOVEMENT_SLOWDOWN, 100);
            }
        }

    }
}
