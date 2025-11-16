package com.xiaoyue.celestial_equipments.content.items.equipment.bow;

import com.xiaoyue.celestial_equipments.content.items.generic.GenericBow;
import com.xiaoyue.celestial_equipments.content.library.BowType;
import com.xiaoyue.celestial_equipments.utils.EquipmentUtils;
import com.xiaoyue.celestial_invoker.invoker.tooltip.SubscribeTooltip;
import com.xiaoyue.celestial_invoker.invoker.tooltip.TooltipEntry;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.ArrowItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

import java.util.List;

public class PolarShadow extends GenericBow {
    public PolarShadow() {
        super(4000, BowType.SHORT_BOW);
    }

    @SubscribeTooltip(id = "polar_shadow")
    public static TooltipEntry tooltip = TooltipEntry.define(
            "When the bowstring is full, arrows are automatically fired and reloaded");

    @Override
    public float getAttack(int lv) {
        return 0.3f * lv;
    }

    @Override
    public float getDrawSpeed(int lv) {
        return 0.1f;
    }

    @Override
    public void addEquipmentTooltips(ItemStack stack, List<Component> list, int lv) {
        list.add(tooltip.withGray());
    }

    @Override
    public void onUseTick(Level level, LivingEntity shooter, ItemStack bow, int missTime) {
        int lv = EquipmentUtils.getLevel(bow);
        float pull = this.getBowPowerForTime(lv, this.getUseDuration(bow) - missTime);
        if (lv > 0 && pull == 1.0F && shooter instanceof Player player) {
            InteractionHand hand = player.getUsedItemHand();
            player.stopUsingItem();
            bow.releaseUsing(player.level(), player, 0);
            player.startUsingItem(hand);
        }

    }

    @Override
    protected void onConfigShoot(ItemStack bow, Player shooter, ArrowItem arrowItem, AbstractArrow arrow, float pull, int lv) {
        arrow.setCritArrow(true);
    }
}
