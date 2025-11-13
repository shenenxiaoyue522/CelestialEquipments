package com.xiaoyue.celestial_equipments.content.items.equipment.ranged;

import com.xiaoyue.celestial_core.data.CCLangData;
import com.xiaoyue.celestial_core.utils.CCUtils;
import com.xiaoyue.celestial_core.utils.EntityUtils;
import com.xiaoyue.celestial_equipments.content.items.generic.GenericBow;
import com.xiaoyue.celestial_equipments.content.library.BowType;
import com.xiaoyue.celestial_invoker.invoker.config.ConfigHolderEntry;
import com.xiaoyue.celestial_invoker.invoker.config.value.DoubleConfigEntry;
import com.xiaoyue.celestial_invoker.invoker.tooltip.SubscribeTooltip;
import com.xiaoyue.celestial_invoker.invoker.tooltip.TooltipEntry;
import net.minecraft.network.chat.Component;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.ArrowItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

import java.util.List;

public class BrightProphecy extends GenericBow {
    public BrightProphecy() {
        super(6000, BowType.LONG_BOW);
    }

    @ConfigHolderEntry(category = "ranged")
    public static DoubleConfigEntry damageBonus = DoubleConfigEntry.defineSmallRange("Bright Prophecy Damage Bonus", 0.02,
            "Bright Prophecy: Damage that can be increased by each light level");

    @SubscribeTooltip(id = "bright_prophecy")
    public static TooltipEntry tooltip = TooltipEntry.define(
            "Each 1 increase in Light Level increases the base damage of arrows by %s");

    @Override
    public float getAttack(int lv) {
        return 1f;
    }

    @Override
    public void addEffectTooltips(ItemStack stack, List<Component> list, int lv) {
        list.add(tooltip.withGray(TooltipEntry.per(damageBonus.get() * lv)));
        list.add(drawingEffect.withGray(CCLangData.eff(MobEffects.NIGHT_VISION)));
    }

    @Override
    public void onUseTick(Level pLevel, LivingEntity pLivingEntity, ItemStack pStack, int pRemainingUseDuration) {
        EntityUtils.addEct(pLivingEntity, MobEffects.NIGHT_VISION, 100);
    }

    @Override
    protected void onConfigShoot(ItemStack bow, Player shooter, ArrowItem arrowItem, AbstractArrow arrow, float pull, int lv) {
        int light = CCUtils.getLight(shooter.level(), shooter.getOnPos());
        float toAdd = light * damageBonus.floatValue() * lv;
        this.mulBaseDamage(arrow, toAdd);
    }
}
