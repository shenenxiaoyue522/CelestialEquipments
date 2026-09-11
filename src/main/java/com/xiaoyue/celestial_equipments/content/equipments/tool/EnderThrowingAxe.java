package com.xiaoyue.celestial_equipments.content.equipments.tool;

import com.xiaoyue.celestial_equipments.content.entities.EnderThrowingAxeEntity;
import com.xiaoyue.celestial_equipments.content.items.generic.GenericDiggerItem;
import com.xiaoyue.celestial_equipments.content.library.DiggerType;
import com.xiaoyue.celestial_equipments.utils.EquipmentUtils;
import com.xiaoyue.celestial_invoker.content.common.entry.ToolStats;
import com.xiaoyue.celestial_invoker.invoker.config.ConfigHolderEntry;
import com.xiaoyue.celestial_invoker.invoker.config.value.DoubleConfigEntry;
import com.xiaoyue.celestial_invoker.invoker.config.value.IntConfigEntry;
import com.xiaoyue.celestial_invoker.invoker.tooltip.SubscribeTooltip;
import com.xiaoyue.celestial_invoker.invoker.tooltip.TooltipEntry;
import com.xiaoyue.celestial_invoker.invoker.tooltip.TooltipHolder;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

import java.util.List;

public class EnderThrowingAxe extends GenericDiggerItem {
    public static final ToolStats STAT = ToolStats.builder().durability(4000).speed(7f).attack(7).enchant(15).build();

    public EnderThrowingAxe() {
        super(STAT, DiggerType.AXE);
    }

    @ConfigHolderEntry(category = "tool")
    public static DoubleConfigEntry dmgConfig = DoubleConfigEntry.defineBigRange("Ender Throwing Axe Throw Damage Factor", 0.05,
            "Increased throw damage per level");

    @ConfigHolderEntry(category = "tool")
    public static IntConfigEntry cooldownConfig = IntConfigEntry.defineFromZero("Ender Throwing Axe Cooldown", 5,
            Integer.MAX_VALUE, "Throw cooldown");

    @SubscribeTooltip(id = "ender_throwing_axe")
    public static TooltipHolder tooltips = TooltipHolder.define(
            TooltipEntry.define("Thrown axes will immediately return to your hand when they hit a creature or block"),
            TooltipEntry.define("When hitting a creature, it deals attack damage attribute %s attack damage"));

    @Override
    public void addTooltips(ItemStack stack, List<Component> list, int lv) {
        list.add(throwableTooltip.withGray());
        list.add(tooltips.get(0).withGray());
        list.add(tooltips.get(1).withGray(TooltipEntry.per(1 + dmgConfig.get() * lv)));
        list.add(cooldownTooltip.withGray(TooltipEntry.num(cooldownConfig.get())));
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level pLevel, Player player, InteractionHand pUsedHand) {
        ItemStack stack = player.getItemInHand(pUsedHand);
        int lv = EquipmentUtils.getLevel(stack);
        if (lv > 0 && cooldownReady(player)) {
            stack.hurtAndBreak(1, player, p -> p.broadcastBreakEvent(pUsedHand));
            EnderThrowingAxeEntity thrownEntity = new EnderThrowingAxeEntity(player, pLevel, stack);
            thrownEntity.setBaseDamage(player.getAttributeValue(Attributes.ATTACK_DAMAGE) * (1 + dmgConfig.get() * lv));
            thrownEntity.shootFromRotation(player, player.getXRot(), player.getYRot(), 0f, 5f, 1f);
            pLevel.addFreshEntity(thrownEntity);
            stack.shrink(1);
            addCooldown(player, cooldownConfig.get() * 20);
        }
        return InteractionResultHolder.success(stack);
    }

    @Override
    public boolean isUpgradeable() {
        return true;
    }

    @Override
    public boolean requiredShiftDown() {
        return false;
    }
}
