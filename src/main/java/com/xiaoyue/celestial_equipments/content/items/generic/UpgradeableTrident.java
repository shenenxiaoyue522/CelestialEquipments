package com.xiaoyue.celestial_equipments.content.items.generic;

import com.xiaoyue.celestial_equipments.content.entities.SimpleTridentEntity;
import com.xiaoyue.celestial_equipments.content.library.ICelestialEquip;
import com.xiaoyue.celestial_equipments.utils.EquipmentUtils;
import com.xiaoyue.celestial_invoker.content.generic.item.CelestialTridentItem;
import com.xiaoyue.celestial_invoker.invoker.tooltip.SubscribeTooltip;
import com.xiaoyue.celestial_invoker.invoker.tooltip.TooltipEntry;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class UpgradeableTrident extends CelestialTridentItem implements ICelestialEquip {
    public static final List<UpgradeableTrident> TRIDENTS = new ArrayList<>();

    public UpgradeableTrident(Properties pProperties) {
        super(pProperties);
        TRIDENTS.add(this);
    }

    @SubscribeTooltip(id = "throw_speed")
    public static TooltipEntry throwSpeedTooltip = TooltipEntry.define("Throw time: %s seconds");

    @SubscribeTooltip(id = "throw_damage")
    public static TooltipEntry throwDamageTooltip = TooltipEntry.define("Throw damage: %s");

    public int getChargeTime(int lv) {
        return 10;
    }

    public float getThrowDamageFactor(int lv) {
        return 1f;
    }

    @Override
    public Component getName(ItemStack pStack) {
        return getItemName(pStack);
    }

    @Override
    public void appendHoverText(ItemStack pStack, @Nullable Level pLevel, List<Component> list, TooltipFlag pIsAdvanced) {
        list.add(Component.empty());
        int lv = EquipmentUtils.getLevel(pStack);
        EquipmentUtils.addTridentStatsTooltips(list, getThrowDamageFactor(lv), getChargeTime(lv) / 20f);
        addBaseTooltips(pStack, list);
        if (!this.isEnabled()) {
            list.add(Component.empty());
            list.add(itemBanTooltip.withGray());
        }
        if (pStack.isEnchanted()) {
            list.add(Component.empty());
        }
    }

    @Override
    protected AbstractArrow getThrownEntity(Level level, Player player, ItemStack trident) {
        SimpleTridentEntity entity = new SimpleTridentEntity(player, level, trident);
        double baseDamage = player.getAttributeValue(Attributes.ATTACK_DAMAGE);
        entity.setBaseDamage(baseDamage * getThrowDamageFactor(EquipmentUtils.getLevel(trident)));
        return entity;
    }

    @Override
    public Rarity getRarity(ItemStack pStack) {
        return getEquipmentRarity(EquipmentUtils.getLevel(pStack));
    }

    @Override
    protected int getChargeTime(ItemStack trident, Player player) {
        return getChargeTime(EquipmentUtils.getLevel(trident));
    }
}
