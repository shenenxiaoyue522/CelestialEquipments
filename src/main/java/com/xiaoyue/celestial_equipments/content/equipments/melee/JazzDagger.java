package com.xiaoyue.celestial_equipments.content.equipments.melee;

import com.google.common.collect.Multimap;
import com.xiaoyue.celestial_core.content.generic.EntityIntData;
import com.xiaoyue.celestial_equipments.CelestialEquipments;
import com.xiaoyue.celestial_equipments.content.items.generic.UpgradeableMelee;
import com.xiaoyue.celestial_equipments.content.library.IAttackConfig;
import com.xiaoyue.celestial_equipments.content.library.MeleeType;
import com.xiaoyue.celestial_invoker.content.common.entry.AttributeAdder;
import com.xiaoyue.celestial_invoker.invoker.config.ConfigHolderEntry;
import com.xiaoyue.celestial_invoker.invoker.config.value.DoubleConfigEntry;
import com.xiaoyue.celestial_invoker.invoker.tooltip.SubscribeTooltip;
import com.xiaoyue.celestial_invoker.invoker.tooltip.TooltipEntry;
import dev.xkmc.l2damagetracker.contents.attack.AttackCache;
import dev.xkmc.l2damagetracker.contents.attack.DamageModifier;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.ItemStack;

import java.util.List;

public class JazzDagger extends UpgradeableMelee implements IAttackConfig {
    public JazzDagger() {
        super(MeleeType.SMALL);
    }

    @ConfigHolderEntry(category = "melee")
    public static DoubleConfigEntry luckConfig = DoubleConfigEntry.define("Jazz Dagger Luck Bonus", 1, 1, 100,
            "Jazz Dagger: Luck bonus");

    @ConfigHolderEntry(category = "melee")
    public static DoubleConfigEntry dmgConfig = DoubleConfigEntry.defineSmallRange("Jazz Dagger Damage Bonus",
            0.02, "Jazz Dagger: Single bonus when hitting combos");

    @ConfigHolderEntry(category = "melee")
    public static DoubleConfigEntry maxDmgConfig = DoubleConfigEntry.defineSmallRange("Jazz Dagger Max Damage Bonus",
            0.2, "Jazz Dagger: Max damage bonus");

    @SubscribeTooltip(id = "jazz_dagger")
    public static TooltipEntry tooltip = TooltipEntry.define(
            "Increases the damage of the target by %s each time they attack, up to a maximum of %s damage");

    @Override
    public void addEquipmentTooltips(ItemStack stack, List<Component> list, int lv) {
        list.add(tooltip.withGray(TooltipEntry.per(dmgConfig.get()), TooltipEntry.per(maxDmgConfig.get())));
    }

    @Override
    protected void modify(EquipmentSlot slot, ItemStack stack, int lv, boolean selected, Multimap<Attribute, AttributeModifier> modify) {
        if (selected && lv > 2) {
            AttributeAdder.builder().attr(Attributes.LUCK).nameWithUUID(CelestialEquipments.loc("jazz_dagger"))
                            .value(luckConfig.get()).toMap(modify);
        }
    }

    @Override
    protected void attacked(ItemStack stack, LivingEntity target, LivingEntity attacker, int lv) {
        int origin = EntityIntData.getData(target, "jazz_dagger");
        EntityIntData.addData(target, "jazz_dagger", origin + 1);
    }

    @Override
    public void onMeleeHurt(ItemStack stack, LivingEntity attacker, AttackCache cache, int lv) {
        float toAdd = Math.min(dmgConfig.floatValue() * EntityIntData.getData(cache.getAttackTarget(), "jazz_dagger"), maxDmgConfig.floatValue());
        cache.addHurtModifier(DamageModifier.multBase(toAdd));
    }
}
