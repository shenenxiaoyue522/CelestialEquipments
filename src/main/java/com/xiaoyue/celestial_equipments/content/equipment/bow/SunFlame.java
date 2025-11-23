package com.xiaoyue.celestial_equipments.content.equipment.bow;

import com.xiaoyue.celestial_equipments.content.items.generic.UpgradeableBow;
import com.xiaoyue.celestial_equipments.content.library.AttackConfig;
import com.xiaoyue.celestial_equipments.content.library.BowType;
import com.xiaoyue.celestial_invoker.invoker.config.ConfigHolderEntry;
import com.xiaoyue.celestial_invoker.invoker.config.value.DoubleConfigEntry;
import com.xiaoyue.celestial_invoker.invoker.config.value.IntConfigEntry;
import com.xiaoyue.celestial_invoker.invoker.tooltip.SubscribeTooltip;
import com.xiaoyue.celestial_invoker.invoker.tooltip.TooltipEntry;
import dev.xkmc.l2damagetracker.contents.attack.AttackCache;
import dev.xkmc.l2damagetracker.contents.attack.DamageModifier;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.ArrowItem;
import net.minecraft.world.item.ItemStack;

import java.util.List;

public class SunFlame extends UpgradeableBow implements AttackConfig {
    public SunFlame() {
        super(6000, BowType.LONG_BOW);
    }

    @ConfigHolderEntry(category = "ranged")
    public static IntConfigEntry burnTime = IntConfigEntry.define("Sun Flame Burn Time", 60, 1, 1000,
            "Sun Flame: The burn time the target is stuck into");

    @ConfigHolderEntry(category = "ranged")
    public static DoubleConfigEntry damageBonus = DoubleConfigEntry.defineSmallRange("Sun Flame Damage Bonus", 0.05,
            "Sun Flame: Attack the target in the fire and increase the attack");

    @SubscribeTooltip(id = "sun_flame")
    public static TooltipEntry tooltip = TooltipEntry.define("Increases damage by %s when attacking burning targets");

    public void addEquipmentTooltips(ItemStack stack, List<Component> list, int lv) {
        list.add(tooltip.withGray(TooltipEntry.per(damageBonus.get() * lv)));
        list.add(burnTimeText.withGray(TooltipEntry.num((burnTime.get() / 20) * lv)));
    }

    @Override
    protected void onConfigShoot(ItemStack bow, Player shooter, ArrowItem arrowItem, AbstractArrow arrow, float pull, int lv) {
        arrow.setSecondsOnFire(arrow.getRemainingFireTicks() + burnTime.get() * lv);
    }

    @Override
    public void onProjectileHurt(ItemStack stack, LivingEntity attacker, AttackCache cache, int lv) {
        if (cache.getAttackTarget().isOnFire()) {
            cache.addHurtModifier(DamageModifier.multBase(damageBonus.floatValue() * lv));
        }
    }
}
