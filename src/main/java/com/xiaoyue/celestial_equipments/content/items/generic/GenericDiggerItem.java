package com.xiaoyue.celestial_equipments.content.items.generic;

import com.mojang.datafixers.util.Pair;
import com.xiaoyue.celestial_equipments.content.library.DiggerType;
import com.xiaoyue.celestial_equipments.content.library.ICelestialEquip;
import net.minecraft.network.chat.Component;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.ToolAction;
import net.minecraftforge.common.ToolActions;
import net.minecraftforge.event.entity.player.PlayerEvent;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class GenericDiggerItem extends DiggerItem implements ICelestialEquip {
    private final DiggerType type;

    public GenericDiggerItem(Tier pTier, DiggerType type) {
        super(getAttackAndSpeed(type).getFirst(), getAttackAndSpeed(type).getSecond(), pTier, getTag(type), new Properties());
        this.type = type;
    }

    public static TagKey<Block> getTag(DiggerType type) {
        return switch (type) {
            case PICKAXE -> BlockTags.MINEABLE_WITH_PICKAXE;
            case AXE -> BlockTags.MINEABLE_WITH_AXE;
            case SHOVEL -> BlockTags.MINEABLE_WITH_SHOVEL;
            case HOE -> BlockTags.MINEABLE_WITH_HOE;
        };
    }

    public static Pair<Float, Float> getAttackAndSpeed(DiggerType type) {
        return switch (type) {
            case PICKAXE -> Pair.of(1f, -2.8f);
            case AXE -> Pair.of(6f, -3.2f);
            case SHOVEL -> Pair.of(1.5f, -3f);
            case HOE -> Pair.of(0f, -3f);
        };
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
            addTooltips(pStack, list, 0);
        }
        if (!this.isEnabled()) {
            list.add(Component.empty());
            list.add(itemBanTooltip.withGray());
        }
    }

    @Override
    public InteractionResult useOn(UseOnContext pContext) {
        return switch (type) {
            case PICKAXE -> super.useOn(pContext);
            case AXE -> Items.NETHERITE_AXE.useOn(pContext);
            case SHOVEL -> Items.NETHERITE_SHOVEL.useOn(pContext);
            case HOE -> Items.NETHERITE_HOE.useOn(pContext);
        };
    }

    @Override
    public boolean canPerformAction(ItemStack stack, ToolAction toolAction) {
        return switch (type) {
            case PICKAXE -> ToolActions.DEFAULT_PICKAXE_ACTIONS.contains(toolAction);
            case AXE -> ToolActions.DEFAULT_AXE_ACTIONS.contains(toolAction);
            case SHOVEL -> ToolActions.DEFAULT_SHOVEL_ACTIONS.contains(toolAction);
            case HOE -> ToolActions.DEFAULT_HOE_ACTIONS.contains(toolAction);
        };
    }

    @Override
    public boolean isUpgradeable() {
        return false;
    }

    public void getBreakSpeed(ItemStack stack, Player player, PlayerEvent.BreakSpeed event, int lv) {
    }
}
