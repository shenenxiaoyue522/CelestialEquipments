package com.xiaoyue.celestial_equipments.content.items.generic;

import com.xiaoyue.celestial_equipments.content.library.ICEquipment;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;
import net.minecraftforge.event.entity.player.PlayerEvent;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public interface IGenericDigger extends ICEquipment {

    @Override
    default boolean isUpgradeable() {
        return false;
    }

    default void getBreakSpeed(ItemStack stack, Player player, PlayerEvent.BreakSpeed event, int lv) {
    }

    class Axe extends AxeItem implements IGenericDigger {
        public Axe(Tier pTier) {
            super(pTier, 6.0F, -3.2F, new Properties());
        }

        @Override
        public Component getName(ItemStack pStack) {
            return this.getItemName(pStack);
        }

        @Override
        public void appendHoverText(ItemStack pStack, @Nullable Level pLevel, List<Component> list, TooltipFlag pIsAdvanced) {
            if (isUpgradeable()) {
                addBaseTooltips(pStack, list);
            } else {
                addTooltips(pStack, list);
            }
            if (!this.isEnabled()) {
                list.add(Component.empty());
                list.add(itemBanTooltip.withGray());
            }
        }
    }

    class Pickaxe extends PickaxeItem implements IGenericDigger {
        public Pickaxe(Tier pTier) {
            super(pTier, 1, -2.8F, new Properties());
        }

        @Override
        public Component getName(ItemStack pStack) {
            return this.getItemName(pStack);
        }

        @Override
        public void appendHoverText(ItemStack pStack, @Nullable Level pLevel, List<Component> list, TooltipFlag pIsAdvanced) {
            if (isUpgradeable()) {
                addBaseTooltips(pStack, list);
            } else {
                addTooltips(pStack, list);
            }
            if (!this.isEnabled()) {
                list.add(Component.empty());
                list.add(itemBanTooltip.withGray());
            }
        }
    }

    class Shovel extends ShovelItem implements IGenericDigger {
        public Shovel(Tier pTier) {
            super(pTier, 1.5F, -3.0F, new Properties());
        }

        @Override
        public Component getName(ItemStack pStack) {
            return this.getItemName(pStack);
        }

        @Override
        public void appendHoverText(ItemStack pStack, @Nullable Level pLevel, List<Component> list, TooltipFlag pIsAdvanced) {
            if (isUpgradeable()) {
                addBaseTooltips(pStack, list);
            } else {
                addTooltips(pStack, list);
            }
            if (!this.isEnabled()) {
                list.add(Component.empty());
                list.add(itemBanTooltip.withGray());
            }
        }
    }

    class Hoe extends HoeItem implements IGenericDigger {
        public Hoe(Tier pTier) {
            super(pTier, 0, -3.0F, new Properties());
        }

        @Override
        public Component getName(ItemStack pStack) {
            return this.getItemName(pStack);
        }

        @Override
        public void appendHoverText(ItemStack pStack, @Nullable Level pLevel, List<Component> list, TooltipFlag pIsAdvanced) {
            if (isUpgradeable()) {
                addBaseTooltips(pStack, list);
            } else {
                addTooltips(pStack, list);
            }
            if (!this.isEnabled()) {
                list.add(Component.empty());
                list.add(itemBanTooltip.withGray());
            }
        }
    }
}
