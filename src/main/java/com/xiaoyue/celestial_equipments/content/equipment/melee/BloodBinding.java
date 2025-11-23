package com.xiaoyue.celestial_equipments.content.equipment.melee;

import com.xiaoyue.celestial_equipments.content.items.generic.UpgradeableMelee;
import com.xiaoyue.celestial_equipments.content.library.AttackConfig;
import com.xiaoyue.celestial_equipments.content.library.MeleeType;
import com.xiaoyue.celestial_invoker.invoker.config.ConfigHolderEntry;
import com.xiaoyue.celestial_invoker.invoker.config.value.DoubleConfigEntry;
import com.xiaoyue.celestial_invoker.invoker.tooltip.SubscribeTooltip;
import com.xiaoyue.celestial_invoker.invoker.tooltip.TooltipEntry;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.entity.living.LivingDeathEvent;

import java.util.List;

public class BloodBinding extends UpgradeableMelee implements AttackConfig {
    public BloodBinding() {
        super(MeleeType.BROAD);
    }

    @ConfigHolderEntry(category = "melee")
    public static DoubleConfigEntry healMultiplier = DoubleConfigEntry.defineFromZero("Blood Binding Heal Multiplier",
            0.15, 5, "Blood Binding: The health recovered when killing the target");

    @SubscribeTooltip(id = "blood_binding")
    public static TooltipEntry tooltip = TooltipEntry.define(
            "Upon killing the target, self regenerates %s and loses health");

    public float getAttack(int lv) {
        return 2.5f;
    }

    public void addEquipmentTooltips(ItemStack stack, List<Component> list, int lv) {
        list.add(tooltip.withGray(TooltipEntry.per(healMultiplier.get() * lv)));
    }

    public void onMeleeKill(ItemStack stack, LivingEntity attacker, LivingDeathEvent event, int lv) {
        float heal = healMultiplier.floatValue() * lv * (attacker.getMaxHealth() - attacker.getHealth());
        attacker.heal(heal);
    }
}
