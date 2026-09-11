package com.xiaoyue.celestial_equipments.content.equipments.misc;

import com.xiaoyue.celestial_core.data.CCDamageTypes;
import com.xiaoyue.celestial_equipments.content.items.generic.ICelestialEquip;
import com.xiaoyue.celestial_invoker.invoker.config.ConfigHolderEntry;
import com.xiaoyue.celestial_invoker.invoker.config.value.DoubleConfigEntry;
import com.xiaoyue.celestial_invoker.invoker.tooltip.SubscribeTooltip;
import com.xiaoyue.celestial_invoker.invoker.tooltip.TooltipEntry;
import com.xiaoyue.celestial_invoker.invoker.tooltip.TooltipHolder;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

import java.util.List;

public class AbyssSacrificeDagger extends ICelestialEquip.Impl {
    public AbyssSacrificeDagger() {
        super(new Properties().stacksTo(1).durability(500));
    }

    @ConfigHolderEntry(category = "melee")
    public static DoubleConfigEntry costConfig = DoubleConfigEntry.defineChance("Abyss Sacrifice Dagger Health Cost",
            0.8, "Abyss Sacrifice Dagger: Health cost factor");

    @SubscribeTooltip(id = "abyss_sacrifice_dagger")
    public static TooltipHolder tooltips = TooltipHolder.define(
            TooltipEntry.define("Right-click: Deal %s of your maximum health as Abyssal damage to yourself"),
            TooltipEntry.define("Convert this lost health into damage absorption"),
            TooltipEntry.define("Cannot be used while you have damage absorption"));

    @Override
    public void addTooltips(ItemStack stack, List<Component> list, int lv) {
        list.add(tooltips.get(0).withGray(TooltipEntry.per(costConfig.get())));
        list.add(tooltips.get(1).withGray());
        list.add(tooltips.get(2).withGray());
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level pLevel, Player player, InteractionHand hand) {
        ItemStack stack = player.getItemInHand(hand);
        float cost = player.getMaxHealth() * costConfig.floatValue();
        if (player.getHealth() > cost) {
            player.hurt(CCDamageTypes.abyss(player), cost);
            player.setAbsorptionAmount(cost);
            if (!player.getAbilities().instabuild) {
                stack.hurtAndBreak(1, player, (p) -> p.broadcastBreakEvent(hand));
            }
            return InteractionResultHolder.consume(stack);
        }
        return InteractionResultHolder.fail(stack);
    }

    @Override
    public boolean isUpgradeable() {
        return false;
    }
}
