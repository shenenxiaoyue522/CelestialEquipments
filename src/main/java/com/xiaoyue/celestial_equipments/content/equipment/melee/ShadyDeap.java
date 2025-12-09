package com.xiaoyue.celestial_equipments.content.equipment.melee;

import com.xiaoyue.celestial_core.utils.EntityUtils;
import com.xiaoyue.celestial_equipments.content.items.generic.UpgradeableMelee;
import com.xiaoyue.celestial_equipments.content.library.AttackConfig;
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

public class ShadyDeap extends UpgradeableMelee implements AttackConfig {
    public ShadyDeap() {
        super(MeleeType.SMALL);
    }

    @ConfigHolderEntry(category = "melee")
    public static DoubleConfigEntry dmgConfig = DoubleConfigEntry.defineSmallRange("Shady Deap Damage Bonus", 0.07,
            "Shady Deap: Increase the attack on targets without armor");

    @SubscribeTooltip(id = "shady_deap")
    public static TooltipEntry tooltip = TooltipEntry.define("Increases damage from unarmored creatures by %s");

    @Override
    public float getSpeed(int lv) {
        return -0.2f;
    }

    @Override
    public void addEquipmentTooltips(ItemStack stack, List<Component> list, int lv) {
        list.add(tooltip.withGray(TooltipEntry.per(dmgConfig.get() * lv)));
    }

    @Override
    public void onMeleeHurt(ItemStack stack, LivingEntity attacker, AttackCache cache, int lv) {
        if (EntityUtils.nullArmor(cache.getAttackTarget())) {
            cache.addHurtModifier(DamageModifier.multBase(dmgConfig.floatValue() * lv));
        }
    }
}
