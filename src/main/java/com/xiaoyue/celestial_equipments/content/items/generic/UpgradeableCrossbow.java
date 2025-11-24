package com.xiaoyue.celestial_equipments.content.items.generic;

import com.xiaoyue.celestial_equipments.content.library.ICEquipment;
import com.xiaoyue.celestial_equipments.utils.EquipmentUtils;
import com.xiaoyue.celestial_invoker.content.generic.item.CelestialCrossbowItem;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class UpgradeableCrossbow extends CelestialCrossbowItem implements ICEquipment {
    public static final List<UpgradeableCrossbow> CROSSBOWS = new ArrayList<>();

    public UpgradeableCrossbow(Properties pProperties) {
        super(pProperties);
        CROSSBOWS.add(this);
    }

    public float getAttack(int lv) {
        return 1f;
    }

    public float getDrawSpeed(int lv) {
        return 1.25f;
    }

    public float getArrowSpeed(int lv) {
        return 1f;
    }

    @Override
    public Component getName(ItemStack pStack) {
        return getItemName(pStack);
    }

    @Override
    public void appendHoverText(ItemStack pStack, @Nullable Level pLevel, List<Component> list, TooltipFlag pFlag) {
        int lv = EquipmentUtils.getLevel(pStack);
        list.add(Component.empty());
        EquipmentUtils.addBowStatsTooltips(list, getAttack(lv), getDrawSpeed(lv), getArrowSpeed(lv));
        addBaseTooltips(pStack, list);
        if (!this.isEnabled()) {
            list.add(Component.empty());
            list.add(itemBan.withGray());
        }
    }

    @Override
    protected int getChargeTime(ItemStack crossbow) {
        int quick_charge = crossbow.getEnchantmentLevel(Enchantments.QUICK_CHARGE);
        int based = (int) (getDrawSpeed(EquipmentUtils.getLevel(crossbow)) * 20);
        return based - quick_charge * 5;
    }

    @Override
    protected final void onConfigShoot(LivingEntity shooter, InteractionHand hand, ItemStack crossbow, ItemStack ammo, Projectile projectile, @Nullable AbstractArrow arrow) {
        int lv = EquipmentUtils.getLevel(crossbow);
        if (arrow != null) {
            arrow.setBaseDamage(arrow.getBaseDamage() + getAttack(lv));
        }
        projectile.setDeltaMovement(projectile.getDeltaMovement().scale(getArrowSpeed(lv)));
        onConfigShoot(shooter, hand, crossbow, ammo, projectile, arrow, lv);
    }

    protected final void onConfigShoot(LivingEntity shooter, InteractionHand hand, ItemStack crossbow, ItemStack ammo, Projectile projectile, @Nullable AbstractArrow arrow, int lv) {

    }

    @Override
    public Item self() {
        return this;
    }
}
