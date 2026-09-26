package com.xiaoyue.celestial_equipments.content.items.curios;

import com.xiaoyue.celestial_core.content.generic.EntityIntData;
import com.xiaoyue.celestial_equipments.CelestialEquipments;
import com.xiaoyue.celestial_equipments.content.items.generic.BaseCurioItem;
import com.xiaoyue.celestial_equipments.register.CEItems;
import com.xiaoyue.celestial_equipments.utils.EquipmentUtils;
import com.xiaoyue.celestial_invoker.invoker.config.ConfigHolderEntry;
import com.xiaoyue.celestial_invoker.invoker.config.value.DoubleConfigEntry;
import com.xiaoyue.celestial_invoker.invoker.config.value.IntConfigEntry;
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

public class SoulBelt extends BaseCurioItem {
    public static final String STACK_ID = CelestialEquipments.loc("soul_belt_stack").toString();

    public SoulBelt(Properties properties) {
        super(properties);
    }

    @ConfigHolderEntry(category = "curios")
    public static IntConfigEntry maxBonusConfig = IntConfigEntry.defineFromZero("Soul Belt Max Bonus",
            100, Integer.MAX_VALUE, "Soul Belt: Max damage bonus");

    @ConfigHolderEntry(category = "curios")
    public static IntConfigEntry intervalConfig = IntConfigEntry.defineFromZero("Soul Belt Bonus Interval",
            1, Integer.MAX_VALUE, "Soul Belt: Seconds per damage bonus stack");

    @ConfigHolderEntry(category = "curios")
    public static DoubleConfigEntry dmgBonusConfig = DoubleConfigEntry.defineChance("Soul Belt Damage Per Stack",
            0.01, "Soul Belt: Damage bonus per stack");

    @ConfigHolderEntry(category = "curios")
    public static DoubleConfigEntry reduceConfig = DoubleConfigEntry.defineChance("Soul Belt Stack Reduce Factor",
            0.35, "Soul Belt: Damage bonus lost when taking damage");

    @SubscribeTooltip(id = "soul_belt")
    public static TooltipHolder tooltips = TooltipHolder.define(
            TooltipEntry.define("Every %s seconds, damage dealt is increased by %s"),
            TooltipEntry.define("Up to %s"),
            TooltipEntry.define("Taking damage reduces the damage bonus by %s"));

    @Override
    public void appendHoverText(ItemStack pStack, @Nullable Level pLevel, List<Component> list, TooltipFlag pIsAdvanced) {
        list.add(tooltips.get(0).withGray(TooltipEntry.num(intervalConfig.get()), TooltipEntry.per(dmgBonusConfig.get())));
        list.add(tooltips.get(1).withGray(TooltipEntry.per(maxBonusConfig.get() * dmgBonusConfig.get())));
        list.add(tooltips.get(2).withGray(TooltipEntry.per(reduceConfig.get())));
    }

    @Override
    public void curioTick(SlotContext ctx, ItemStack stack) {
        LivingEntity entity = ctx.entity();
        if (entity.level().isClientSide()) return;
        if (entity.tickCount % (intervalConfig.get() * 20) != 0) return;
        int current = EntityIntData.getData(entity, STACK_ID);
        if (current < maxBonusConfig.get()) {
            EntityIntData.addData(entity, STACK_ID, current + 1);
        }
    }

    @Override
    public void onUnequip(SlotContext slotContext, ItemStack newStack, ItemStack oldStack) {
        EntityIntData.removeData(slotContext.entity(), STACK_ID);
    }

    public static void onAttacked(LivingEntity attacker, AttackCache cache) {
        if (attacker == null || !EquipmentUtils.hasCurio(attacker, CEItems.SOUL_BELT.get())) return;
        int stack = EntityIntData.getData(attacker, STACK_ID);
        if (stack <= 0) return;
        cache.addHurtModifier(DamageModifier.multTotal(1 + stack * dmgBonusConfig.floatValue()));
    }

    public static void onDamaged(LivingEntity entity) {
        int stack = EntityIntData.getData(entity, STACK_ID);
        if (stack <= 0) return;
        if (!EquipmentUtils.hasCurio(entity, CEItems.SOUL_BELT.get())) return;
        EntityIntData.addData(entity, STACK_ID, (int) (stack * (1 - reduceConfig.get())));
    }
}
