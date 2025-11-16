package com.xiaoyue.celestial_equipments.content.items.equipment.melee;

import com.xiaoyue.celestial_equipments.content.items.generic.GenericMelee;
import com.xiaoyue.celestial_equipments.content.library.AttackConfig;
import com.xiaoyue.celestial_equipments.content.library.MeleeType;
import com.xiaoyue.celestial_invoker.invoker.config.ConfigHolderEntry;
import com.xiaoyue.celestial_invoker.invoker.config.value.DoubleConfigEntry;
import com.xiaoyue.celestial_invoker.invoker.tooltip.SubscribeTooltip;
import com.xiaoyue.celestial_invoker.invoker.tooltip.TooltipEntry;
import dev.xkmc.l2damagetracker.contents.attack.CreateSourceEvent;
import dev.xkmc.l2damagetracker.contents.damage.DefaultDamageState;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.phys.AABB;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class HeavenGift extends GenericMelee implements AttackConfig {
    public HeavenGift() {
        super(MeleeType.BROAD);
    }

    @ConfigHolderEntry(category = "melee")
    public static DoubleConfigEntry sweepReachBonus = DoubleConfigEntry.defineSmallRange("Heaven Gift Sweep Reach Bonus",
            0.5, "Heaven Gift: Sweep reach bonus");

    @SubscribeTooltip(id = "heaven_gift")
    public static TooltipEntry tooltip = TooltipEntry.define("Damage pierces through armor when attacking with full force");

    public float getAttack(int lv) {
        return 0.2f * lv;
    }

    public void addEquipmentTooltips(ItemStack stack, List<Component> list, int lv) {
        list.add(tooltip.withGray());
        list.add(sweepBonusText.withGray(TooltipEntry.per(sweepReachBonus.get())));
    }

    public @NotNull AABB getSweepHitBox(@NotNull ItemStack stack, @NotNull Player player, @NotNull Entity target) {
        double toAdd = (double) 1f + sweepReachBonus.get();
        return target.getBoundingBox().inflate(1f * toAdd, 0.25f, 1f * toAdd);
    }

    public void onCreateSource(ItemStack stack, LivingEntity attacker, CreateSourceEvent event, int lv) {
        if (lv > 0 && event.getDirect() != null && event.getDirect().is(event.getAttacker())) {
            event.enable(DefaultDamageState.BYPASS_ARMOR);
        }
    }
}
