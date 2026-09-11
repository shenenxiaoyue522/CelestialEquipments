package com.xiaoyue.celestial_equipments.content.equipments.misc;

import com.google.common.collect.LinkedHashMultimap;
import com.google.common.collect.Multimap;
import com.xiaoyue.celestial_equipments.content.entities.SakuraBladeEntity;
import com.xiaoyue.celestial_equipments.content.items.generic.ICelestialEquip;
import com.xiaoyue.celestial_equipments.register.CEItems;
import com.xiaoyue.celestial_invoker.content.common.entry.AttributeAdder;
import com.xiaoyue.celestial_invoker.invoker.config.ConfigHolderEntry;
import com.xiaoyue.celestial_invoker.invoker.config.value.DoubleConfigEntry;
import com.xiaoyue.celestial_invoker.invoker.config.value.IntConfigEntry;
import com.xiaoyue.celestial_invoker.invoker.tooltip.SubscribeTooltip;
import com.xiaoyue.celestial_invoker.invoker.tooltip.TooltipEntry;
import com.xiaoyue.celestial_invoker.invoker.tooltip.TooltipHolder;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class Senbonzakura extends ICelestialEquip.Impl {
    public Senbonzakura(Properties properties) {
        super(properties.stacksTo(1).durability(621));
    }

    @ConfigHolderEntry(category = "misc")
    public static DoubleConfigEntry dmgConfig = DoubleConfigEntry.defineFromMinUsable("Senbonzakura Damage Factor",
            0.3f, 10000, "Senbonzakura: Damage factor per knife");

    @ConfigHolderEntry(category = "misc")
    public static DoubleConfigEntry healConfig = DoubleConfigEntry.defineFromMinUsable("Senbonzakura Heal Factor",
            0.03f, 100, "Senbonzakura: Heal factor per knife");

    @ConfigHolderEntry(category = "misc")
    public static IntConfigEntry cooldownConfig = IntConfigEntry.defineFromZero("Senbonzakura Cooldown Time",
            1, Integer.MAX_VALUE, "Senbonzakura: Cooldown time");

    @SubscribeTooltip(id = "senbonzakura")
    public static TooltipHolder tooltips = TooltipHolder.define(
            TooltipEntry.define("Right-click: Fire 5 knives straight ahead"),
            TooltipEntry.define("Each knife hit on a creature recovers %s of missing health"),
            TooltipEntry.define("And deals damage equal to %s of your attack damage"));

    @Override
    public Component getName(ItemStack pStack) {
        return getItemName(pStack);
    }

    @Override
    public void appendHoverText(ItemStack pStack, @Nullable Level pLevel, List<Component> list, TooltipFlag pIsAdvanced) {
        list.add(tooltips.get(0).withGray());
        list.add(tooltips.get(1).withGray(TooltipEntry.per(healConfig.get())));
        list.add(tooltips.get(2).withGray(TooltipEntry.per(dmgConfig.get())));
        list.add(cooldownTooltip.withGray(TooltipEntry.num(cooldownConfig.get())));
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level pLevel, Player player, InteractionHand pUsedHand) {
        ItemStack stack = player.getItemInHand(pUsedHand);
        if (!pLevel.isClientSide() && cooldownReady(player)) {
            for (int i = 0; i < 5; i++) {
                SakuraBladeEntity entity = new SakuraBladeEntity(player, pLevel, CEItems.SAKURA_BLADE.asStack());
                entity.setPos(player.getX(), player.getEyeY() - 0.1, player.getZ());
                float spread = (i - (5 - 1) / 2f) * (35f / 5);
                entity.shootFromRotation(player, player.getXRot(), player.getYRot() + spread, 0f, 5f, 0f);
                pLevel.addFreshEntity(entity);
            }
            if (!player.getAbilities().instabuild) {
                stack.hurtAndBreak(1, player, (p) -> p.broadcastBreakEvent(pUsedHand));
            }
            addCooldown(player, cooldownConfig.get() * 20);
            return InteractionResultHolder.success(stack);
        }
        return InteractionResultHolder.fail(stack);
    }

    @Override
    public Multimap<Attribute, AttributeModifier> getAttributeModifiers(EquipmentSlot slot, ItemStack stack) {
        Multimap<Attribute, AttributeModifier> modify = LinkedHashMultimap.create();
        if (slot.equals(EquipmentSlot.MAINHAND)) {
            AttributeAdder builder = AttributeAdder.builder().name("Weapon modifier");
            builder.uuid(BASE_ATTACK_DAMAGE_UUID).value(6).toMap(modify);
            builder.attr(Attributes.ATTACK_SPEED).uuid(BASE_ATTACK_SPEED_UUID).value(-2.4).toMap(modify);
        }
        return modify;
    }
}
