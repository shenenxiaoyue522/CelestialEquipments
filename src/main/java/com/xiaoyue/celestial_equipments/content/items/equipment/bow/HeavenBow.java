package com.xiaoyue.celestial_equipments.content.items.equipment.bow;

import com.xiaoyue.celestial_equipments.content.items.generic.GenericBow;
import com.xiaoyue.celestial_equipments.content.library.AttackConfig;
import com.xiaoyue.celestial_equipments.content.library.BowType;
import com.xiaoyue.celestial_invoker.invoker.tooltip.SubscribeTooltip;
import com.xiaoyue.celestial_invoker.invoker.tooltip.TooltipEntry;
import dev.xkmc.l2damagetracker.contents.attack.CreateSourceEvent;
import dev.xkmc.l2damagetracker.contents.damage.DefaultDamageState;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.ItemStack;

import java.util.List;

public class HeavenBow extends GenericBow implements AttackConfig {
    public HeavenBow() {
        super(6000, BowType.SHORT_BOW);
    }

    @SubscribeTooltip(id = "heaven_bow")
    public static TooltipEntry tooltip = TooltipEntry.define("Damage pierces magic protection when attacking");

    @Override
    public float getAttack(int lv) {
        return 0.2f * lv;
    }

    @Override
    public void addEffectTooltips(ItemStack stack, List<Component> list, int lv) {
        list.add(tooltip.withGray());
    }

    @Override
    public void onCreateSource(ItemStack stack, LivingEntity attacker, CreateSourceEvent event, int lv) {
        if (lv > 0 && event.getDirect() != null && event.getDirect() instanceof AbstractArrow) {
            event.enable(DefaultDamageState.BYPASS_MAGIC);
        }
    }
}
