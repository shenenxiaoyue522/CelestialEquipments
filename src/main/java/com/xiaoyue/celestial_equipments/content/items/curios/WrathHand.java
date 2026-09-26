package com.xiaoyue.celestial_equipments.content.items.curios;

import com.xiaoyue.celestial_core.content.generic.EntityIntData;
import com.xiaoyue.celestial_equipments.CelestialEquipments;
import com.xiaoyue.celestial_equipments.content.items.generic.BaseCurioItem;
import com.xiaoyue.celestial_equipments.register.CEItems;
import com.xiaoyue.celestial_equipments.utils.EquipmentUtils;
import com.xiaoyue.celestial_invoker.invoker.config.ConfigHolderEntry;
import com.xiaoyue.celestial_invoker.invoker.config.value.DoubleConfigEntry;
import com.xiaoyue.celestial_invoker.invoker.tooltip.SubscribeTooltip;
import com.xiaoyue.celestial_invoker.invoker.tooltip.TooltipEntry;
import com.xiaoyue.celestial_invoker.invoker.tooltip.TooltipHolder;
import dev.xkmc.l2damagetracker.contents.attack.AttackCache;
import dev.xkmc.l2damagetracker.contents.attack.DamageModifier;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;
import top.theillusivec4.curios.api.SlotContext;

import java.util.List;

public class WrathHand extends BaseCurioItem {
    public static final String READY_ID = CelestialEquipments.loc("wrath_hand_ready").toString();

    public WrathHand(Properties properties) {
        super(properties);
    }

    @ConfigHolderEntry(category = "curios")
    public static DoubleConfigEntry dmgConfig = DoubleConfigEntry.defineBigRange("Wrath Hand Damage Bonus",
            0.55, "Wrath Hand: Damage bonus of the next attack after taking damage");

    @SubscribeTooltip(id = "wrath_hand")
    public static TooltipHolder tooltips = TooltipHolder.define(
            TooltipEntry.define("After taking damage"),
            TooltipEntry.define("The damage dealt by the next attack is increased by %s"));

    @Override
    public void appendHoverText(ItemStack pStack, @Nullable Level pLevel, List<Component> list, TooltipFlag pIsAdvanced) {
        list.add(tooltips.get(0).withGray());
        list.add(tooltips.get(1).withGray(TooltipEntry.per(dmgConfig.get())));
    }

    @Override
    public void onUnequip(SlotContext slotContext, ItemStack newStack, ItemStack oldStack) {
        EntityIntData.addData(slotContext.entity(), READY_ID, 0);
    }

    public static void onDamaged(LivingEntity entity) {
        if (!EquipmentUtils.hasCurio(entity, CEItems.WRATH_HAND.get())) return;
        EntityIntData.addData(entity, READY_ID, 1);
    }

    public static void onHurtTarget(LivingEntity attacker, AttackCache cache) {
        if (attacker == null) return;
        if (EntityIntData.getData(attacker, READY_ID) <= 0) return;
        if (!EquipmentUtils.hasCurio(attacker, CEItems.WRATH_HAND.get())) return;
        cache.addHurtModifier(DamageModifier.multTotal(1 + dmgConfig.floatValue()));
        EntityIntData.addData(attacker, READY_ID, 0);
    }
}
