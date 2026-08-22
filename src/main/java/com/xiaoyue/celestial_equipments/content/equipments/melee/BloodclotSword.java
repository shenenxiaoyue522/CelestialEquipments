package com.xiaoyue.celestial_equipments.content.equipments.melee;

import com.xiaoyue.celestial_equipments.content.items.generic.UpgradeableMelee;
import com.xiaoyue.celestial_equipments.content.library.MeleeType;
import com.xiaoyue.celestial_equipments.register.CEEffects;
import com.xiaoyue.celestial_invoker.invoker.config.ConfigHolderEntry;
import com.xiaoyue.celestial_invoker.invoker.config.value.DoubleConfigEntry;
import com.xiaoyue.celestial_invoker.invoker.tooltip.SubscribeTooltip;
import com.xiaoyue.celestial_invoker.invoker.tooltip.TooltipEntry;
import com.xiaoyue.celestial_invoker.invoker.tooltip.TooltipHolder;
import dev.xkmc.l2library.base.effects.EffectUtil;
import net.minecraft.network.chat.Component;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;

import java.util.List;

public class BloodclotSword extends UpgradeableMelee {
    public BloodclotSword() {
        super(MeleeType.SMALL);
    }

    @ConfigHolderEntry(category = "melee")
    public static DoubleConfigEntry healthCostConfig = DoubleConfigEntry.defineChance("Bloodclot Sword Health Cost",
            0.08, "Bloodclot Sword: On attack, consumes health based on maximum health");

    @ConfigHolderEntry(category = "melee")
    public static DoubleConfigEntry targetHealthCostConfig = DoubleConfigEntry.defineChance("Bloodclot Target Health Cost",
            0.11, "Bloodclot Sword: On attack, consumes health based on the target's current health");

    @SubscribeTooltip(id = "bloodclot_sword")
    public static TooltipHolder tooltips = TooltipHolder.define(
            TooltipEntry.define("On attack, consume %s of your maximum health"),
            TooltipEntry.define("Also consumes %s of the target's current health"),
            TooltipEntry.define("And apply %s effect to the target"));

    @Override
    public void addTooltips(ItemStack stack, List<Component> list, int lv) {
        list.add(tooltips.get(0).withGray(TooltipEntry.per(healthCostConfig.get())));
        list.add(tooltips.get(1).withGray(TooltipEntry.per(targetHealthCostConfig.get())));
        list.add(tooltips.get(2).withGray(TooltipEntry.eff(CEEffects.MORTAL_WOUND.get())));
    }

    @Override
    protected void attacked(ItemStack stack, LivingEntity target, LivingEntity attacker, int lv) {
        float cost = attacker.getMaxHealth() * healthCostConfig.floatValue();
        if (attacker.getHealth() > cost) {
            attacker.setHealth(attacker.getHealth() - cost);
            float targetCost = target.getHealth() * targetHealthCostConfig.floatValue();
            MobEffectInstance effect = new MobEffectInstance(CEEffects.MORTAL_WOUND.get(), 600, 0);
            EffectUtil.addEffect(target, effect, EffectUtil.AddReason.FORCE, attacker);
            if (targetCost < 1f) return;
            target.setHealth(target.getHealth() - targetCost);
        }
    }
}
