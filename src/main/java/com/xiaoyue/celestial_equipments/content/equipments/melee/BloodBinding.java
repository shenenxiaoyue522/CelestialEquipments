package com.xiaoyue.celestial_equipments.content.equipments.melee;

import com.xiaoyue.celestial_equipments.content.items.generic.UpgradeableMelee;
import com.xiaoyue.celestial_equipments.content.library.IAttackConfig;
import com.xiaoyue.celestial_equipments.content.library.MeleeType;
import com.xiaoyue.celestial_equipments.utils.EquipmentUtils;
import com.xiaoyue.celestial_invoker.invoker.config.ConfigHolderEntry;
import com.xiaoyue.celestial_invoker.invoker.config.value.DoubleConfigEntry;
import com.xiaoyue.celestial_invoker.invoker.tooltip.SubscribeTooltip;
import com.xiaoyue.celestial_invoker.invoker.tooltip.TooltipEntry;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;

import java.util.List;

public class BloodBinding extends UpgradeableMelee implements IAttackConfig {
    public BloodBinding() {
        super(MeleeType.BROAD);
    }

    @ConfigHolderEntry(category = "melee")
    public static DoubleConfigEntry healConfig = DoubleConfigEntry.defineFromZero("Blood Binding Heal Factor",
            0.15, 5, "Blood Binding: The health recovered when killing the target");

    @SubscribeTooltip(id = "blood_binding")
    public static TooltipEntry tooltip = TooltipEntry.define(
            "Upon killing the target, self regenerates %s and loses health");

    public float getAttack(int lv) {
        return 2.5f;
    }

    @Override
    public void addTooltips(ItemStack stack, List<Component> list, int lv) {
        list.add(tooltip.withGray(TooltipEntry.per(healConfig.get() * lv)));
    }

    public static void onMeleeKill(ItemStack stack, LivingEntity attacker) {
        int lv = EquipmentUtils.getLevel(stack);
        float heal = healConfig.floatValue() * lv * (attacker.getMaxHealth() - attacker.getHealth());
        attacker.heal(heal);
    }
}
