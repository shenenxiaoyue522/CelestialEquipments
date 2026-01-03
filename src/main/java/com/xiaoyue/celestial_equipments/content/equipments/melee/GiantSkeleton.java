package com.xiaoyue.celestial_equipments.content.equipments.melee;

import com.google.common.collect.Multimap;
import com.xiaoyue.celestial_equipments.CelestialEquipments;
import com.xiaoyue.celestial_equipments.content.items.generic.UpgradeableMelee;
import com.xiaoyue.celestial_equipments.content.library.IAttackConfig;
import com.xiaoyue.celestial_equipments.content.library.MeleeType;
import com.xiaoyue.celestial_invoker.content.ancillary.entry.AttributeAdder;
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

public class GiantSkeleton extends UpgradeableMelee implements IAttackConfig {
    public GiantSkeleton() {
        super(MeleeType.BROAD);
    }

    @ConfigHolderEntry(category = "melee")
    public static IntConfigEntry reachConfig = IntConfigEntry.define("Giant Skeleton Entity Reach Bonus", 2, 1, 100,
            "Giant Skeleton: Entity reach bonus");

    @ConfigHolderEntry(category = "melee")
    public static DoubleConfigEntry dmgConfig = DoubleConfigEntry.defineFromZero("Giant Skeleton Damage Factor", 0.005,
            1, "Giant Skeleton: Increase the attack according to the maximum life of the target");

    @SubscribeTooltip(id = "giant_skeleton")
    public static TooltipEntry tooltip = TooltipEntry.define(
            "Increases the target's %s max health damage when attacking with full force");

    @Override
    public void addEquipmentTooltips(ItemStack stack, List<Component> list, int lv) {
        list.add(tooltip.withGray(TooltipEntry.per(dmgConfig.get() * (float)lv)));
    }

    @Override
    protected void modify(EquipmentSlot slot, ItemStack stack, int lv, boolean selected, Multimap<Attribute, AttributeModifier> modify) {
        if (selected && lv >= 3) {
            AttributeAdder.builder().attr(ForgeMod.ENTITY_REACH.get())
                    .nameWithUUID(CelestialEquipments.loc("giant_skeleton")).value(reachConfig.get()).toMap(modify);
        }
    }

    @Override
    public void onMeleeHurt(ItemStack stack, LivingEntity attacker, AttackCache cache, int lv) {
        float toAdd = dmgConfig.floatValue() * lv * cache.getAttackTarget().getMaxHealth();
        cache.addHurtModifier(DamageModifier.add(toAdd));
    }
}
