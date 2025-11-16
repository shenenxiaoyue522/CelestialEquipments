package com.xiaoyue.celestial_equipments.content.items.equipment.bow;

import com.xiaoyue.celestial_core.utils.EntityUtils;
import com.xiaoyue.celestial_equipments.content.items.generic.GenericBow;
import com.xiaoyue.celestial_equipments.content.library.AttackConfig;
import com.xiaoyue.celestial_equipments.content.library.BowType;
import com.xiaoyue.celestial_invoker.invoker.config.ConfigHolderEntry;
import com.xiaoyue.celestial_invoker.invoker.config.value.DoubleConfigEntry;
import com.xiaoyue.celestial_invoker.invoker.tooltip.SubscribeTooltip;
import com.xiaoyue.celestial_invoker.invoker.tooltip.TooltipEntry;
import dev.xkmc.l2damagetracker.contents.attack.AttackCache;
import dev.xkmc.l2damagetracker.contents.attack.DamageModifier;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;

import java.util.List;

public class ElvenBow extends GenericBow implements AttackConfig {
    public ElvenBow() {
        super(6000, BowType.LONG_BOW);
    }

    @ConfigHolderEntry(category = "ranged")
    public static DoubleConfigEntry damageBonus = DoubleConfigEntry.defineSmallRange("Elven Bow Damage Bonus", 0.07,
            "Elven Bow: Increased attack when the target is not wearing armor");

    @SubscribeTooltip(id = "elven_bow")
    public static TooltipEntry tooltip = TooltipEntry.define("Increases damage from arrows hitting unarmored targets by %s");

    @Override
    public float getArrowSpeed(int lv) {
        return 0.2F;
    }

    @Override
    public void addEffectTooltips(ItemStack stack, List<Component> list, int lv) {
        list.add(tooltip.withGray(TooltipEntry.per(damageBonus.get() * lv)));
    }

    @Override
    public void onProjectileHurt(ItemStack stack, LivingEntity attacker, AttackCache cache, int lv) {
        if (EntityUtils.nullArmor(cache.getAttackTarget())) {
            float toAdd = damageBonus.floatValue() * lv;
            cache.addHurtModifier(DamageModifier.multBase(toAdd));
        }
    }
}
