package com.xiaoyue.celestial_equipments.content.equipments.tool;

import com.xiaoyue.celestial_core.utils.EntityUtils;
import com.xiaoyue.celestial_equipments.content.library.ICelestialEquip;
import com.xiaoyue.celestial_invoker.invoker.config.ConfigHolderEntry;
import com.xiaoyue.celestial_invoker.invoker.config.value.IntConfigEntry;
import com.xiaoyue.celestial_invoker.invoker.tooltip.SubscribeTooltip;
import com.xiaoyue.celestial_invoker.invoker.tooltip.TooltipEntry;
import com.xiaoyue.celestial_invoker.invoker.tooltip.TooltipHolder;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.animal.Chicken;
import net.minecraft.world.entity.animal.Cow;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class FashionScissors extends ShearsItem implements ICelestialEquip {
    public FashionScissors() {
        super(new Properties().rarity(Rarity.UNCOMMON).durability(622));
    }

    @ConfigHolderEntry(category = "tool")
    public static IntConfigEntry cooldownConfig = IntConfigEntry.defineFromZero("Fashion Scissors Cooldown", 100,
            Integer.MAX_VALUE, "Use cooldown");

    @SubscribeTooltip(id = "fashion_scissors")
    public static TooltipHolder tooltips = TooltipHolder.define(
            TooltipEntry.define("Leather is obtained when trimming bovine creatures"),
            TooltipEntry.define("Feathers are obtained when trimming chicken creatures"));

    @Override
    public void appendHoverText(ItemStack pStack, @Nullable Level pLevel, List<Component> list, TooltipFlag pIsAdvanced) {
        for (TooltipEntry tooltip : tooltips) {
            list.add(tooltip.withGray());
        }
        list.add(cooldownTooltip.withGray(TooltipEntry.num(cooldownConfig.get() / 20)));
    }

    @Override
    public InteractionResult interactLivingEntity(ItemStack stack, Player playerIn, LivingEntity entity, InteractionHand hand) {
        InteractionResult result = super.interactLivingEntity(stack, playerIn, entity, hand);
        if (!noCooldown(playerIn) || !isEnabled()) {
            return InteractionResult.PASS;
        }
        ItemStack dropStack = ItemStack.EMPTY;
        int amount = 1 + playerIn.getRandom().nextInt(3);
        if (entity instanceof Cow) {
            dropStack = Items.LEATHER.getDefaultInstance();
        } else if (entity instanceof Chicken) {
            dropStack = Items.FEATHER.getDefaultInstance();
        }
        if (!dropStack.isEmpty()) {
            entity.hurt(playerIn.damageSources().playerAttack(playerIn), 2);
            stack.hurtAndBreak(1, playerIn, p -> p.broadcastBreakEvent(hand));
            for (int i = 0; i < amount; i++) {
                EntityUtils.spawnItem(playerIn.level(), entity.getOnPos(), dropStack);
            }
            addCooldown(playerIn, cooldownConfig.get());
            result = InteractionResult.SUCCESS;
        }
        return result;
    }

    @Override
    public boolean isUpgradeable() {
        return false;
    }

    @Override
    public Component getName(ItemStack pStack) {
        return getItemName(pStack);
    }
}
