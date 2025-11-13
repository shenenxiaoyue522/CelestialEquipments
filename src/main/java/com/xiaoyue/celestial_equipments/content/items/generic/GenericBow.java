package com.xiaoyue.celestial_equipments.content.items.generic;

import com.google.common.collect.LinkedHashMultimap;
import com.google.common.collect.Multimap;
import com.xiaoyue.celestial_equipments.content.library.BowType;
import com.xiaoyue.celestial_equipments.content.library.ICEquipment;
import com.xiaoyue.celestial_equipments.utils.EquipmentUtils;
import com.xiaoyue.celestial_invoker.content.generic.item.GenericBowItem;
import com.xiaoyue.celestial_invoker.invoker.tooltip.SubscribeTooltip;
import com.xiaoyue.celestial_invoker.invoker.tooltip.TooltipEntry;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class GenericBow extends GenericBowItem implements ICEquipment {

    public static final List<GenericBow> BOWS = new ArrayList<>();
    public final BowType type;

    public GenericBow(int durability, BowType type) {
        super(new Item.Properties().stacksTo(1).durability(durability));
        this.type = type;
        BOWS.add(this);
    }

    @SubscribeTooltip(id = "bow_damage")
    public static TooltipEntry bowDamage = TooltipEntry.define("Base damage %s");

    @SubscribeTooltip(id = "draw_speed")
    public static TooltipEntry drawSpeed = TooltipEntry.define("Draw time %s seconds");

    @SubscribeTooltip(id = "arrow_speed")
    public static TooltipEntry arrowSpeed = TooltipEntry.define("Arrow speed %s");

    @SubscribeTooltip(id = "drawing_effect")
    public static TooltipEntry drawingEffect = TooltipEntry.define("Gain %s when the bow is drawn");

    @SubscribeTooltip(id = "burn_time")
    public static TooltipEntry burnTimeText = TooltipEntry.define("Causes the target to burn in %s seconds when it hits");

    @Override
    public float getDrawSpeed(LivingEntity user, ItemStack bow) {
        return this.getDrawSpeed(EquipmentUtils.getLevel(bow));
    }

    public float getAttack(int lv) {
        return 0f;
    }

    public float getDrawSpeed(int lv) {
        return 0f;
    }

    public float getArrowSpeed(int lv) {
        return 0f;
    }

    @Override
    public Component getName(ItemStack pStack) {
        return this.getItemName(pStack);
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level pLevel, List<Component> list, TooltipFlag pIsAdvanced) {
        int lv = EquipmentUtils.getLevel(stack);
        list.add(Component.empty());
        EquipmentUtils.addBowStatsTooltips(list, this.type.getAttack(lv, this.getAttack(lv)), this.type.getDrawSpeed(this.getDrawSpeed(lv)), this.type.getArrowSpeed(this.getArrowSpeed(lv)));
        this.addBaseTooltips(stack, list);
        list.add(Component.empty());
        list.add(this.type.getLang().withStyle(ChatFormatting.BLUE));
        if (!this.isEnabled()) {
            list.add(Component.empty());
            list.add(itemBan.withGray());
        }
    }

    @Override
    protected void onConfigShoot(ItemStack bow, Player shooter, ItemStack ammo, ArrowItem arrowItem, AbstractArrow arrow, float pull) {
        int lv = EquipmentUtils.getLevel(bow);
        arrow.setBaseDamage(arrow.getBaseDamage() + type.getAttack(lv, getAttack(lv)));
        arrow.setDeltaMovement(arrow.getDeltaMovement().scale(type.getArrowSpeed(getArrowSpeed(lv))));
        this.onConfigShoot(bow, shooter, arrowItem, arrow, pull, lv);
    }

    protected void onConfigShoot(ItemStack bow, Player shooter, ArrowItem arrowItem, AbstractArrow arrow, float pull, int lv) {
    }

    public void mulBaseDamage(AbstractArrow arrow, float bonus) {
        arrow.setBaseDamage(arrow.getBaseDamage() * (double)(1f + bonus));
    }

    public float getPullForTime(int lv, float time) {
        float actual = this.type.getDrawSpeed(this.getDrawSpeed(lv)) * 20.0F;
        float f = time / actual * 1.5F;
        return Math.min(1.0F, f);
    }

    public float getBowPowerForTime(int lv, float time) {
        float f = this.getPullForTime(lv, time);
        f = (f * f + f * 2.0F) / 3.0F;
        if (f > 1.0F) {
            f = 1.0F;
        }
        return Math.min(1.0F, f);
    }

    @Override
    public Multimap<Attribute, AttributeModifier> getAttributeModifiers(EquipmentSlot slot, ItemStack stack) {
        Multimap<Attribute, AttributeModifier> modify = LinkedHashMultimap.create();
        this.modify(slot, stack, EquipmentUtils.getLevel(stack), slot.equals(EquipmentSlot.MAINHAND), modify);
        return modify;
    }

    protected void modify(EquipmentSlot slot, ItemStack stack, int lv, boolean selected, Multimap<Attribute, AttributeModifier> modify) {
    }

    @Override
    public Rarity getRarity(ItemStack pStack) {
        return this.getGearRarity(EquipmentUtils.getLevel(pStack));
    }

    @Override
    public int getEnchantmentValue(ItemStack stack) {
        return 15;
    }

    @Override
    public Item self() {
        return this;
    }
}
