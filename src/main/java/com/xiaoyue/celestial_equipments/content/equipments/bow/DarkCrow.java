package com.xiaoyue.celestial_equipments.content.equipments.bow;

import com.google.common.collect.Multimap;
import com.xiaoyue.celestial_core.utils.CCUtils;
import com.xiaoyue.celestial_core.utils.ItemUtils;
import com.xiaoyue.celestial_equipments.content.items.generic.UpgradeableBow;
import com.xiaoyue.celestial_equipments.content.library.BowType;
import com.xiaoyue.celestial_invoker.invoker.config.ConfigHolderEntry;
import com.xiaoyue.celestial_invoker.invoker.config.value.DoubleConfigEntry;
import com.xiaoyue.celestial_invoker.invoker.tooltip.SubscribeTooltip;
import com.xiaoyue.celestial_invoker.invoker.tooltip.TooltipEntry;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.EquipmentSlot.Type;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.ArrowItem;
import net.minecraft.world.item.ItemStack;

import java.util.List;

public class DarkCrow extends UpgradeableBow {
    public DarkCrow() {
        super(6000, BowType.LONG_BOW);
    }

    @ConfigHolderEntry(category = "bow")
    public static DoubleConfigEntry speedConfig = DoubleConfigEntry.defineSmallRange("Dark Crow Speed Bonus", 0.05,
            "Dark Crow: Movement speed bonus");

    @ConfigHolderEntry(category = "bow")
    public static DoubleConfigEntry dmgConfig = DoubleConfigEntry.defineSmallRange("Dark Crow Damage Bonus", 0.02,
            "Dark Crow: Increase the damage every 1 point below the max light level");

    @SubscribeTooltip(id = "dark_crow")
    public static TooltipEntry tooltip = TooltipEntry.define("Increases damage by %s per 1 level lower light level");

    @Override
    public float getArrowSpeed(int lv) {
        return 0.1f;
    }

    @Override
    public void addTooltips(ItemStack stack, List<Component> list, int lv) {
        list.add(tooltip.withGray(TooltipEntry.per(dmgConfig.get() * lv)));
    }

    @Override
    protected void modify(EquipmentSlot slot, ItemStack stack, int lv, boolean selected, Multimap<Attribute, AttributeModifier> modify) {
        if (lv >= 1 && slot.getType().equals(Type.HAND)) {
            modify.put(Attributes.MOVEMENT_SPEED, ItemUtils.addMod("dark_crow", speedConfig.get(), 1));
        }
    }

    @Override
    protected void onConfigShoot(ItemStack bow, Player shooter, ArrowItem arrowItem, AbstractArrow arrow, float pull, int lv) {
        int light = CCUtils.getLight(shooter.level(), shooter.getOnPos());
        float toAdd = (15 - light) * dmgConfig.floatValue() * lv;
        this.mulArrowBaseDamage(arrow, toAdd);
    }
}
