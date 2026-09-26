package com.xiaoyue.celestial_equipments.content.items.curios;

import com.google.common.collect.LinkedHashMultimap;
import com.google.common.collect.Multimap;
import com.xiaoyue.celestial_equipments.content.items.generic.BaseCurioItem;
import com.xiaoyue.celestial_invoker.content.common.entry.AttributeAdder;
import com.xiaoyue.celestial_invoker.invoker.config.ConfigHolderEntry;
import com.xiaoyue.celestial_invoker.invoker.config.value.DoubleConfigEntry;
import com.xiaoyue.celestial_invoker.invoker.config.value.IntConfigEntry;
import com.xiaoyue.celestial_invoker.invoker.tooltip.SubscribeTooltip;
import com.xiaoyue.celestial_invoker.invoker.tooltip.TooltipEntry;
import com.xiaoyue.celestial_invoker.invoker.tooltip.TooltipHolder;
import net.minecraft.network.chat.Component;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.storage.loot.LootContext;
import org.jetbrains.annotations.Nullable;
import top.theillusivec4.curios.api.SlotContext;

import java.util.List;
import java.util.UUID;

public class AvariceGrip extends BaseCurioItem {
    public AvariceGrip(Properties properties) {
        super(properties);
    }

    @ConfigHolderEntry(category = "curios")
    public static DoubleConfigEntry dmgReduceConfig = DoubleConfigEntry.defineChance("Avarice Grip Damage Reduce",
            0.35, "Avarice Grip: Reduces attack damage dealt");

    @ConfigHolderEntry(category = "curios")
    public static IntConfigEntry fortuneConfig = IntConfigEntry.defineFromZero("Avarice Grip Fortune Bonus",
            2, Integer.MAX_VALUE, "Avarice Grip: Fortune level bonus");

    @ConfigHolderEntry(category = "curios")
    public static IntConfigEntry lootingConfig = IntConfigEntry.defineFromZero("Avarice Grip Looting Bonus",
            2, Integer.MAX_VALUE, "Avarice Grip: Looting level bonus");

    @SubscribeTooltip(id = "avarice_grip")
    public static TooltipHolder tooltips = TooltipHolder.define(
            TooltipEntry.define("Reduces attack damage dealt by %s"),
            TooltipEntry.define("Increases %s level by %s"),
            TooltipEntry.define("Increases %s level by %s"));

    @Override
    public void appendHoverText(ItemStack pStack, @Nullable Level pLevel, List<Component> list, TooltipFlag pIsAdvanced) {
        list.add(tooltips.get(0).withGray(TooltipEntry.per(dmgReduceConfig.get())));
        list.add(tooltips.get(1).withGray(TooltipEntry.enchantment(Enchantments.BLOCK_FORTUNE), TooltipEntry.num(fortuneConfig.get())));
        list.add(tooltips.get(2).withGray(TooltipEntry.enchantment(Enchantments.MOB_LOOTING), TooltipEntry.num(lootingConfig.get())));
    }

    @Override
    public Multimap<Attribute, AttributeModifier> getAttributeModifiers(SlotContext slotContext, UUID uuid, ItemStack stack) {
        Multimap<Attribute, AttributeModifier> map = LinkedHashMultimap.create();
        AttributeAdder.builder().uuid(uuid).attr(Attributes.ATTACK_DAMAGE)
                .name(bonusModifierName("attack")).operation(2).value(-dmgReduceConfig.get()).toMap(map);
        return map;
    }

    @Override
    public int getFortuneLevel(SlotContext slotContext, LootContext lootContext, ItemStack stack) {
        return fortuneConfig.get();
    }

    @Override
    public int getLootingLevel(SlotContext slotContext, DamageSource source, LivingEntity target, int baseLooting, ItemStack stack) {
        return baseLooting + lootingConfig.get();
    }
}
