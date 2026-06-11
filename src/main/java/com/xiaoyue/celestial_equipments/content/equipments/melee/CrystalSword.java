package com.xiaoyue.celestial_equipments.content.equipments.melee;

import com.xiaoyue.celestial_core.register.CCEffects;
import com.xiaoyue.celestial_core.utils.EntityUtils;
import com.xiaoyue.celestial_equipments.content.items.generic.UpgradeableMelee;
import com.xiaoyue.celestial_equipments.content.library.MeleeType;
import com.xiaoyue.celestial_invoker.invoker.config.ConfigHolderEntry;
import com.xiaoyue.celestial_invoker.invoker.config.value.DoubleConfigEntry;
import com.xiaoyue.celestial_invoker.invoker.tooltip.SubscribeTooltip;
import com.xiaoyue.celestial_invoker.invoker.tooltip.TooltipEntry;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;

import java.util.List;

public class CrystalSword extends UpgradeableMelee {
    public CrystalSword() {
        super(MeleeType.GENERIC);
    }

    @ConfigHolderEntry(category = "melee")
    public static DoubleConfigEntry effectChanceConfig = DoubleConfigEntry.defineChance("Crystal Sword Effect Chance", 0.2,
            "Crystal Sword: How much chance does it have to increase critical hits and critical damage when attacking");

    @SubscribeTooltip(id = "crystal_sword")
    public static TooltipEntry tooltip = TooltipEntry.define(
            "When attacking, there is a %s chance to increase the caster's Critical Chance and Critical Damage");

    @Override
    public float getSpeed(int lv) {
        return 0.1f;
    }

    public static double getChance(int lv) {
        return Math.min(1.0, effectChanceConfig.get() * lv);
    }

    @Override
    public void addTooltips(ItemStack stack, List<Component> list, int lv) {
        list.add(tooltip.withGray(TooltipEntry.per(getChance(lv))));
    }

    @Override
    protected void attacked(ItemStack stack, LivingEntity target, LivingEntity attacker, int lv) {
        if (attacker.getRandom().nextDouble() <= getChance(lv)) {
            int toAdd = lv / 2;
            EntityUtils.addEct(attacker, CCEffects.CRIT_RATE.get(), 100, toAdd - 1);
            EntityUtils.addEct(attacker, CCEffects.CRIT_DAMAGE.get(), 100, toAdd - 1);
        }

    }
}
