package com.xiaoyue.celestial_equipments.content.items.equipment.ranged;

import com.google.common.collect.Multimap;
import com.xiaoyue.celestial_core.utils.ItemUtils;
import com.xiaoyue.celestial_equipments.content.items.generic.GenericBow;
import com.xiaoyue.celestial_equipments.content.library.BowType;
import com.xiaoyue.celestial_invoker.invoker.config.ConfigHolderEntry;
import com.xiaoyue.celestial_invoker.invoker.config.value.DoubleConfigEntry;
import com.xiaoyue.celestial_invoker.invoker.tooltip.SubscribeTooltip;
import com.xiaoyue.celestial_invoker.invoker.tooltip.TooltipEntry;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.entity.projectile.AbstractArrow.Pickup;
import net.minecraft.world.item.ArrowItem;
import net.minecraft.world.item.ItemStack;

import java.util.List;

public class EmeraldWind extends GenericBow {
    public EmeraldWind() {
        super(6000, BowType.LONG_BOW);
    }

    @ConfigHolderEntry(category = "ranged")
    public static DoubleConfigEntry luckBonus = DoubleConfigEntry.define("Emerald Wind Luck Bonus", 2, 1, 100,
            "Emerald Wind: Luck bonus");

    @SubscribeTooltip(id = "emerald_wind")
    public static TooltipEntry tooltip = TooltipEntry.define("Fires two additional arrows when firing");

    @Override
    public float getAttack(int lv) {
        return 0.2f * lv;
    }

    @Override
    public void addEffectTooltips(ItemStack stack, List<Component> list, int lv) {
        list.add(tooltip.withGray());
    }

    @Override
    protected void modify(EquipmentSlot slot, ItemStack stack, int lv, boolean selected, Multimap<Attribute, AttributeModifier> modify) {
        if (lv >= 2 && selected) {
            modify.put(Attributes.LUCK, ItemUtils.addMod("emerald_wind", luckBonus.get(), 0));
        }
    }

    @Override
    protected void onConfigShoot(ItemStack bow, Player shooter, ArrowItem arrowItem, AbstractArrow arrow, float pull, int lv) {
        ItemStack ammo = shooter.getProjectile(bow);
        if (lv > 0) {
            for(int i = -1; i < 2; ++i) {
                AbstractArrow otherArrow = arrowItem.createArrow(shooter.level(), ammo, shooter);
                otherArrow.shootFromRotation(shooter, shooter.getXRot(), shooter.getYRot(), 0.0f, pull * (3.0f - Math.abs(i)), 1f);
                otherArrow.setDeltaMovement(otherArrow.getDeltaMovement().add(0f, 0.15 * i, 0f));
                if (i != 0) {
                    otherArrow.setPos(otherArrow.getX(), otherArrow.getY() + 0.025f, otherArrow.getZ());
                    otherArrow.pickup = Pickup.CREATIVE_ONLY;
                }
                shooter.level().addFreshEntity(otherArrow);
            }
        }
    }
}
