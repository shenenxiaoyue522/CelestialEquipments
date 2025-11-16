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
    default void addBaseTooltips(ItemStack stack, List<Component> list) {
        this.addBaseTooltips(stack, list, true);
    }

    @Override
    default boolean isUpgradeable() {
        return false;
    }

    default void getBreakSpeed(ItemStack stack, Player player, PlayerEvent.BreakSpeed event, int lv) {
    }

    class Axe extends AxeItem implements IGenericDigger {
        public Axe(Tier pTier, Item.Properties pProperties) {
            super(pTier, 6.0F, -3.2F, pProperties);
        }

        @Override
        public Component getName(ItemStack pStack) {
            return this.getItemName(pStack);
        }

        @Override
        public void appendHoverText(ItemStack pStack, @Nullable Level pLevel, List<Component> list, TooltipFlag pIsAdvanced) {
            this.addEffectTooltips(pStack, list);
            if (!this.isEnabled()) {
                list.add(Component.empty());
                list.add(itemBan.withGray());
            }
        }

        public Item self() {
            return this;
        }
    }

    class Pickaxe extends PickaxeItem implements IGenericDigger {
        public Pickaxe(Tier pTier, Item.Properties pProperties) {
            super(pTier, 1, -2.8F, pProperties);
        }

        @Override
        public Component getName(ItemStack pStack) {
            return this.getItemName(pStack);
        }

        @Override
        public void appendHoverText(ItemStack pStack, @Nullable Level pLevel, List<Component> list, TooltipFlag pIsAdvanced) {
            this.addEffectTooltips(pStack, list);
            if (!this.isEnabled()) {
                list.add(Component.empty());
                list.add(itemBan.withGray());
            }
        }

        public Item self() {
            return this;
        }
    }

    class Shovel extends ShovelItem implements IGenericDigger {
        public Shovel(Tier pTier, Item.Properties pProperties) {
            super(pTier, 1.5F, -3.0F, pProperties);
        }

        @Override
        public Component getName(ItemStack pStack) {
            return this.getItemName(pStack);
        }

        @Override
        public void appendHoverText(ItemStack pStack, @Nullable Level pLevel, List<Component> list, TooltipFlag pIsAdvanced) {
            this.addEffectTooltips(pStack, list);
            if (!this.isEnabled()) {
                list.add(Component.empty());
                list.add(itemBan.withGray());
            }
        }

        public Item self() {
            return this;
        }
    }

    class Hoe extends HoeItem implements IGenericDigger {
        public Hoe(Tier pTier, Item.Properties pProperties) {
            super(pTier, 0, -3.0F, pProperties);
        }

        @Override
        public Component getName(ItemStack pStack) {
            return this.getItemName(pStack);
        }

        @Override
        public void appendHoverText(ItemStack pStack, @Nullable Level pLevel, List<Component> list, TooltipFlag pIsAdvanced) {
            this.addEffectTooltips(pStack, list);
            if (!this.isEnabled()) {
                list.add(Component.empty());
                list.add(itemBan.withGray());
            }
        }

        public Item self() {
            return this;
        }
    }
}
