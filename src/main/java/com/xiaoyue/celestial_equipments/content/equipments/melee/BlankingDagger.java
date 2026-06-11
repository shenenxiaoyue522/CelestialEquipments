package com.xiaoyue.celestial_equipments.content.equipments.melee;

import com.xiaoyue.celestial_core.utils.EntityUtils;
import com.xiaoyue.celestial_equipments.content.items.generic.UpgradeableMelee;
import com.xiaoyue.celestial_equipments.content.library.IAttackConfig;
import com.xiaoyue.celestial_equipments.content.library.MeleeType;
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

public class BlankingDagger extends UpgradeableMelee implements IAttackConfig {
    public BlankingDagger() {
        super(MeleeType.SMALL);
    }

    @ConfigHolderEntry(category = "melee")
    public static DoubleConfigEntry dmgConfig = DoubleConfigEntry.defineSmallRange("Blanking Dagger Damage Bonus",
            0.1, "Blanking Dagger: Increase attack damage when attacking from behind");

    @SubscribeTooltip(id = "blanking_dagger")
    public static TooltipEntry tooltip = TooltipEntry.define(
            "Increases damage by %s when attacking mobs from behind");

    public void addTooltips(ItemStack stack, List<Component> list, int lv) {
        list.add(tooltip.withGray(TooltipEntry.per(dmgConfig.get() * lv)));
    }

    public void onMeleeHurt(ItemStack stack, LivingEntity attacker, AttackCache cache, int lv) {
        if (EntityUtils.isLookingBehindTarget(cache.getAttackTarget(), attacker.getEyePosition())) {
            cache.addHurtModifier(DamageModifier.multBase(dmgConfig.floatMax() * lv));
        }
    }
}
