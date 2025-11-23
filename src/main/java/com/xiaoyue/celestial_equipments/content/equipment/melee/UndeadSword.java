package com.xiaoyue.celestial_equipments.content.equipment.melee;

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

public class UndeadSword extends UpgradeableMelee implements AttackConfig {
    public UndeadSword() {
        super(MeleeType.GENERIC);
    }

    @ConfigHolderEntry(category = "melee")
    public static DoubleConfigEntry damageBonus = DoubleConfigEntry.defineSmallRange("Undead Sword Damage Bonus", 0.05,
            "Undead Sword: Increase the attack according to the loss of life");

    @SubscribeTooltip(id = "undead_sword")
    public static TooltipEntry tooltip = TooltipEntry.define("Damage is increased by %s for every 1 less life on attack");

    @Override
    public void addEquipmentTooltips(ItemStack stack, List<Component> list, int lv) {
        list.add(tooltip.withGray(TooltipEntry.per(damageBonus.get() * lv)));
    }

    @Override
    public void onMeleeHurt(ItemStack stack, LivingEntity attacker, AttackCache cache, int lv) {
        float missHp = attacker.getMaxHealth() - attacker.getHealth();
        float toAdd = missHp * damageBonus.floatValue() * lv;
        cache.addHurtModifier(DamageModifier.multBase(toAdd));
    }
}
