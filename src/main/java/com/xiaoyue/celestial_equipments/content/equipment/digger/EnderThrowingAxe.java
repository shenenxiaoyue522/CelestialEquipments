package com.xiaoyue.celestial_equipments.content.equipment.digger;

import com.xiaoyue.celestial_equipments.content.entities.EnderThrowingAxeEntity;
import com.xiaoyue.celestial_equipments.content.items.generic.IGenericDigger;
import com.xiaoyue.celestial_equipments.utils.EquipmentUtils;
import com.xiaoyue.celestial_invoker.content.ancillary.entry.ToolStats;
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

public class EnderThrowingAxe extends IGenericDigger.Axe {
    public static final ToolStats STAT = ToolStats.builder().durability(4000).speed(7f).attack(7).enchant(15).build();

    public EnderThrowingAxe() {
        super(STAT, new Properties());
    }

    @ConfigHolderEntry(category = "digger")
    public static DoubleConfigEntry dmgConfig = DoubleConfigEntry.defineBigRange("Ender Throwing Axe Throw Damage Factor", 0.05,
            "Increased throw damage per level");

    @ConfigHolderEntry(category = "digger")
    public static IntConfigEntry cooldownConfig = IntConfigEntry.defineFromZero("Ender Throwing Axe Cooldown", 100,
            Integer.MAX_VALUE, "Throw cooldown");

    @SubscribeTooltip(id = "ender_throwing_axe")
    public static TooltipHolder tooltips = TooltipHolder.define(
            TooltipEntry.define("Right-click to throw the axe out"),
            TooltipEntry.define("Thrown axes will immediately return to your hand when they hit a creature or block"),
            TooltipEntry.define("When hitting a creature, it deals attack damage attribute %s attack damage"));

    @Override
    public void addEquipmentTooltips(ItemStack stack, List<Component> list, int lv) {
        for (int i = 0; i < tooltips.size(); i++) {
            TooltipEntry tooltip = tooltips.get(i);
            if (i == 2) {
                list.add(tooltip.withGray(TooltipEntry.per(1 + dmgConfig.get() * lv)));
            } else {
                list.add(tooltip.withGray());
            }
        }
        list.add(itemCooldownTooltip.withGray(TooltipEntry.num(cooldownConfig.get() / 20)));
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level pLevel, Player player, InteractionHand pUsedHand) {
        ItemStack stack = player.getItemInHand(pUsedHand);
        int lv = EquipmentUtils.getLevel(stack);
        if (lv > 0 && noCooldown(player)) {
            stack.hurtAndBreak(1, player, p -> p.broadcastBreakEvent(pUsedHand));
            EnderThrowingAxeEntity thrownEntity = new EnderThrowingAxeEntity(player, pLevel, stack);
            thrownEntity.setBaseDamage(player.getAttributeValue(Attributes.ATTACK_DAMAGE) * (1 + dmgConfig.get() * lv));
            thrownEntity.shootFromRotation(player, player.getXRot(), player.getYRot(), 0f, 5f, 1f);
            pLevel.addFreshEntity(thrownEntity);
            player.setItemInHand(pUsedHand, ItemStack.EMPTY);
            addCooldown(player, cooldownConfig.get());
        }
        return InteractionResultHolder.success(stack);
    }

    @Override
    public boolean isUpgradeable() {
        return true;
    }
}
