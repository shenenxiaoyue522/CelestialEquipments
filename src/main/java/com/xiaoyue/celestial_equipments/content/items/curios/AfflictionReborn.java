package com.xiaoyue.celestial_equipments.content.items.curios;

import com.google.common.collect.LinkedHashMultimap;
import com.google.common.collect.Multimap;
import com.xiaoyue.celestial_core.register.CCAttributes;
import com.xiaoyue.celestial_core.utils.EntityUtils;
import com.xiaoyue.celestial_equipments.content.items.generic.BaseCurioItem;
import com.xiaoyue.celestial_invoker.content.common.entry.AttributeAdder;
import com.xiaoyue.celestial_invoker.invoker.config.ConfigHolderEntry;
import com.xiaoyue.celestial_invoker.invoker.config.value.DoubleConfigEntry;
import com.xiaoyue.celestial_invoker.invoker.tooltip.SubscribeTooltip;
import com.xiaoyue.celestial_invoker.invoker.tooltip.TooltipEntry;
import com.xiaoyue.celestial_invoker.invoker.tooltip.TooltipHolder;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraftforge.event.entity.living.LivingHealEvent;
import org.jetbrains.annotations.Nullable;
import top.theillusivec4.curios.api.SlotContext;

import java.util.List;
import java.util.UUID;

public class AfflictionReborn extends BaseCurioItem {
    public AfflictionReborn(Properties properties) {
        super(properties);
    }

    @ConfigHolderEntry(category = "curios")
    public static DoubleConfigEntry dmgConfig = DoubleConfigEntry.defineBigRange("Affliction Reborn Damage Factor",
            1, "Affliction Reborn: Attack damage dealt based on the amount of health recovered");

    @ConfigHolderEntry(category = "curios")
    public static DoubleConfigEntry regenConfig = DoubleConfigEntry.defineBigRange("Affliction Reborn Regen Bonus",
            0.1, "Affliction Reborn: Regen rate bonus");

    @SubscribeTooltip(id = "affliction_reborn")
    public static TooltipHolder tooltips = TooltipHolder.define(
            TooltipEntry.define("Locks onto the creature you most recently attacked"),
            TooltipEntry.define("When the wearer recovers health"),
            TooltipEntry.define("Deals damage to the locked target equal to %s of the amount healed"));

    @Override
    public void appendHoverText(ItemStack pStack, @Nullable Level pLevel, List<Component> list, TooltipFlag pIsAdvanced) {
        list.add(tooltips.get(0).withGray());
        list.add(tooltips.get(1).withGray());
        list.add(tooltips.get(2).withGray(TooltipEntry.per(dmgConfig.get())));
    }

    @Override
    public Multimap<Attribute, AttributeModifier> getAttributeModifiers(SlotContext slotContext, UUID uuid, ItemStack stack) {
        Multimap<Attribute, AttributeModifier> map = LinkedHashMultimap.create();
        AttributeAdder.builder().uuid(uuid).attr(CCAttributes.REPLY_POWER.get()).operation(1).value(regenConfig.get()).toMap(map);
        return map;
    }

    public static void onHeal(LivingEntity entity, LivingHealEvent event) {
        LivingEntity target = entity.getLastHurtMob();
        if (target != null) {
            EntityUtils.hurtByPlayerOrMob(target, entity, event.getAmount() * dmgConfig.floatValue());
            event.setCanceled(true);
        }
    }
}
