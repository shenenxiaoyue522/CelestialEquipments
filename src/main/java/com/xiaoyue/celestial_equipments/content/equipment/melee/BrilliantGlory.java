package com.xiaoyue.celestial_equipments.content.equipment.melee;

import com.google.common.collect.Multimap;
import com.xiaoyue.celestial_core.utils.EntityUtils;
import com.xiaoyue.celestial_core.utils.ItemUtils;
import com.xiaoyue.celestial_equipments.content.items.generic.UpgradeableMelee;
import com.xiaoyue.celestial_equipments.content.library.AttackConfig;
import com.xiaoyue.celestial_equipments.content.library.MeleeType;
import com.xiaoyue.celestial_invoker.invoker.config.ConfigHolderEntry;
import com.xiaoyue.celestial_invoker.invoker.config.value.DoubleConfigEntry;
import com.xiaoyue.celestial_invoker.invoker.tooltip.SubscribeTooltip;
import com.xiaoyue.celestial_invoker.invoker.tooltip.TooltipEntry;
import dev.xkmc.l2damagetracker.contents.attack.AttackCache;
import dev.xkmc.l2damagetracker.contents.attack.DamageModifier;
import dev.xkmc.l2damagetracker.init.L2DamageTracker;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.item.ItemStack;

import java.util.List;

public class BrilliantGlory extends UpgradeableMelee implements AttackConfig {
    public BrilliantGlory() {
        super(MeleeType.BROAD);
    }

    @ConfigHolderEntry(category = "melee")
    public static DoubleConfigEntry critDmgBonus = DoubleConfigEntry.defineSmallRange("Brilliant Glory Crit Damage Bonus",
            0.2, "Brilliant Glory: The health recovered when killing the target");

    @ConfigHolderEntry(category = "melee")
    public static DoubleConfigEntry effectDmgBonus = DoubleConfigEntry.defineFromZero("Brilliant Glory Effect Damage Bonus",
            0.05, 10, "Brilliant Glory: Improve the attack according to the positive effect");

    @SubscribeTooltip(id = "brilliant_glory")
    public static TooltipEntry tooltip = TooltipEntry.define(
            "Increases damage by %s for every 1 positive effect you have when attacking");

    @Override
    public void addEquipmentTooltips(ItemStack stack, List<Component> list, int lv) {
        list.add(tooltip.withGray(TooltipEntry.per(effectDmgBonus.get() * lv)));
    }

    @Override
    protected void modify(EquipmentSlot slot, ItemStack stack, int lv, boolean selected, Multimap<Attribute, AttributeModifier> modify) {
        if (selected && lv >= 3) {
            modify.put(L2DamageTracker.CRIT_DMG.get(), ItemUtils.addMod("brilliant_glory", critDmgBonus.get(), 0));
        }
    }

    public void onMeleeHurt(ItemStack stack, LivingEntity attacker, AttackCache cache, int lv) {
        float toAdd = effectDmgBonus.floatValue() * lv * EntityUtils.getBeneficialEffect(attacker);
        cache.addHurtModifier(DamageModifier.multBase(toAdd));
    }
}
