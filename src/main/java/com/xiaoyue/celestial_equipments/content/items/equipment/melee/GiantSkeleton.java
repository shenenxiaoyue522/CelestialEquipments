package com.xiaoyue.celestial_equipments.content.items.equipment.melee;

import com.google.common.collect.Multimap;
import com.xiaoyue.celestial_equipments.CelestialEquipments;
import com.xiaoyue.celestial_equipments.content.items.generic.GenericMelee;
import com.xiaoyue.celestial_equipments.content.library.AttackConfig;
import com.xiaoyue.celestial_equipments.content.library.MeleeType;
import com.xiaoyue.celestial_invoker.content.ancillary.entry.AttrModifierEntry;
import com.xiaoyue.celestial_invoker.invoker.config.ConfigHolderEntry;
import com.xiaoyue.celestial_invoker.invoker.config.value.DoubleConfigEntry;
import com.xiaoyue.celestial_invoker.invoker.config.value.IntConfigEntry;
import com.xiaoyue.celestial_invoker.invoker.tooltip.SubscribeTooltip;
import com.xiaoyue.celestial_invoker.invoker.tooltip.TooltipEntry;
import dev.xkmc.l2damagetracker.contents.attack.AttackCache;
import dev.xkmc.l2damagetracker.contents.attack.DamageModifier;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.common.ForgeMod;

import java.util.List;

public class GiantSkeleton extends GenericMelee implements AttackConfig {
    public GiantSkeleton() {
        super(MeleeType.BROAD);
    }

    @ConfigHolderEntry(category = "melee")
    public static IntConfigEntry reachBonus = IntConfigEntry.define("Giant Skeleton Entity Reach Bonus", 2, 1, 100,
            "Giant Skeleton: Entity reach bonus");

    @ConfigHolderEntry(category = "melee")
    public static DoubleConfigEntry damageBonus = DoubleConfigEntry.defineChance("Giant Skeleton Damage Bonus", 0.01,
            "Giant Skeleton: Increase the attack according to the maximum life of the target");

    @SubscribeTooltip(id = "giant_skeleton")
    public static TooltipEntry tooltip = TooltipEntry.define(
            "Increases the target's %s max health damage when attacking with full force");

    @Override
    public void addEquipmentTooltips(ItemStack stack, List<Component> list, int lv) {
        list.add(tooltip.withGray(TooltipEntry.per(damageBonus.get() * (float)lv)));
    }

    @Override
    protected void modify(EquipmentSlot slot, ItemStack stack, int lv, boolean selected, Multimap<Attribute, AttributeModifier> modify) {
        if (selected && lv >= 3) {
            AttrModifierEntry.builder().attr(ForgeMod.ENTITY_REACH.get())
                    .nameWithUUID(CelestialEquipments.loc("giant_skeleton")).value(reachBonus.get()).toMap(modify);
        }
    }

    @Override
    public void onMeleeHurt(ItemStack stack, LivingEntity attacker, AttackCache cache, int lv) {
        float toAdd = damageBonus.floatValue() * lv * cache.getAttackTarget().getMaxHealth();
        cache.addHurtModifier(DamageModifier.add(toAdd));
    }
}
