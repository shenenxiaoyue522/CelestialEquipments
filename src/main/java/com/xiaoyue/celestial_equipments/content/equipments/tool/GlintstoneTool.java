package com.xiaoyue.celestial_equipments.content.equipments.tool;

import com.xiaoyue.celestial_equipments.content.items.generic.GenericDiggerItem;
import com.xiaoyue.celestial_equipments.content.library.DiggerType;
import com.xiaoyue.celestial_invoker.content.common.entry.ToolStats;
import com.xiaoyue.celestial_invoker.content.common.helper.breaker.MinerConfig;
import com.xiaoyue.celestial_invoker.content.common.helper.breaker.MinerConfigBuilder;
import com.xiaoyue.celestial_invoker.content.common.helper.breaker.MultiBlockMiner;
import com.xiaoyue.celestial_invoker.invoker.tooltip.SubscribeTooltip;
import com.xiaoyue.celestial_invoker.invoker.tooltip.TooltipEntry;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;

import java.util.List;

public class GlintstoneTool extends GenericDiggerItem {
    public static ToolStats.Builder STAT = ToolStats.builder().durability(2232).speed(8f).enchant(15).level(4);

    public GlintstoneTool(DiggerType type, float attack) {
        super(STAT.attack(attack).build(), type);
    }

    @SubscribeTooltip(id = "glintstone_tool")
    public static TooltipEntry tooltip = TooltipEntry.define("Can mine the same type of blocks in an area");

    @Override
    public void addTooltips(ItemStack stack, List<Component> list, int lv) {
        list.add(tooltip.withGray());
    }

    @Override
    public boolean mineBlock(ItemStack pStack, Level pLevel, BlockState pState, BlockPos pPos, LivingEntity entity) {
        if (entity instanceof Player player) {
            MinerConfig config = new MinerConfigBuilder()
                    .setBlockFilter(state -> state.requiresCorrectToolForDrops() && pState == state)
                    .setMaxDistance(16).setMaxBlocks(64).build();
            MultiBlockMiner.executeChainMining(pLevel, player, pPos, config);
        }
        return super.mineBlock(pStack, pLevel, pState, pPos, entity);
    }
}
