package com.xiaoyue.celestial_equipments.content.equipments.misc;

import com.xiaoyue.celestial_core.utils.EntityUtils;
import com.xiaoyue.celestial_equipments.content.items.generic.ICelestialEquip;
import com.xiaoyue.celestial_invoker.invoker.config.ConfigHolderEntry;
import com.xiaoyue.celestial_invoker.invoker.config.value.DoubleConfigEntry;
import com.xiaoyue.celestial_invoker.invoker.tooltip.SubscribeTooltip;
import com.xiaoyue.celestial_invoker.invoker.tooltip.TooltipEntry;
import com.xiaoyue.celestial_invoker.invoker.tooltip.TooltipHolder;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

import java.util.List;

public class ResonantRuinDagger extends ICelestialEquip.Impl {
    public ResonantRuinDagger() {
        super(new Properties().stacksTo(1).durability(500));
    }

    @ConfigHolderEntry(category = "melee")
    public static DoubleConfigEntry costConfig = DoubleConfigEntry.defineChance("Resonant Ruin Dagger Health Cost",
            0.11, "Resonant Ruin Dagger: Health cost factor");

    @ConfigHolderEntry(category = "melee")
    public static DoubleConfigEntry dmgConfig = DoubleConfigEntry.defineSmallRange("Resonant Ruin Dagger Damage Factor",
            2.0, "Resonant Ruin Dagger: Damage factor");

    @SubscribeTooltip(id = "resonant_ruin_dagger")
    public static TooltipHolder tooltips = TooltipHolder.define(
            TooltipEntry.define("Right-click: Deal %s of your maximum health as damage to yourself"),
            TooltipEntry.define("Deal %s of your taken damage to nearby creatures"));

    @Override
    public void addTooltips(ItemStack stack, List<Component> list, int lv) {
        list.add(tooltips.get(0).withGray(TooltipEntry.per(costConfig.get())));
        list.add(tooltips.get(1).withGray(TooltipEntry.per(dmgConfig.get())));
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level pLevel, Player player, InteractionHand hand) {
        ItemStack stack = player.getItemInHand(hand);
        float cost = player.getMaxHealth() * costConfig.floatValue();
        if (player.getHealth() > cost) {
            DamageSource source = player.damageSources().playerAttack(player);
            player.hurt(source, cost);
            for (LivingEntity e : EntityUtils.getExceptForCentralEntity(player, 8, 2)) {
                e.hurt(source, cost * dmgConfig.floatValue());
            }
            if (!player.getAbilities().instabuild) {
                stack.hurtAndBreak(1, player, (p) -> p.broadcastBreakEvent(hand));
            }
            return InteractionResultHolder.consume(stack);
        }
        return InteractionResultHolder.fail(stack);
    }

    @Override
    public boolean isUpgradeable() {
        return false;
    }
}
