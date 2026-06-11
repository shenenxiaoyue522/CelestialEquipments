package com.xiaoyue.celestial_equipments.content.equipments.bow;

import com.xiaoyue.celestial_equipments.content.items.generic.UpgradeableBow;
import com.xiaoyue.celestial_equipments.content.library.BowType;
import com.xiaoyue.celestial_equipments.content.library.IAttackConfig;
import com.xiaoyue.celestial_invoker.invoker.config.ConfigHolderEntry;
import com.xiaoyue.celestial_invoker.invoker.config.value.IntConfigEntry;
import com.xiaoyue.celestial_invoker.invoker.tooltip.SubscribeTooltip;
import com.xiaoyue.celestial_invoker.invoker.tooltip.TooltipEntry;
import dev.xkmc.l2damagetracker.contents.attack.AttackCache;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;

import java.util.List;

public class FrozenInvasion extends UpgradeableBow implements IAttackConfig {
    public FrozenInvasion() {
        super(5500, BowType.LONG_BOW);
    }

    @ConfigHolderEntry(category = "bow")
    public static IntConfigEntry frozenTimeConfig = IntConfigEntry.define("Frozen Invasion Frozen Time", 60, 10, 1000,
            "Frozen Invasion: The value of time the target is frozen in");

    @SubscribeTooltip(id = "frozen_invasion")
    public static TooltipEntry tooltip = TooltipEntry.define("Targets hit are frozen in %s seconds");

    @Override
    public void addTooltips(ItemStack stack, List<Component> list, int lv) {
        list.add(tooltip.withGray(TooltipEntry.num(frozenTimeConfig.get() / 20)));
    }

    @Override
    public void onProjectileHurt(ItemStack stack, LivingEntity attacker, AttackCache cache, int lv) {
        if (lv > 0) {
            cache.getAttackTarget().setTicksFrozen(frozenTimeConfig.get());
        }
    }
}
