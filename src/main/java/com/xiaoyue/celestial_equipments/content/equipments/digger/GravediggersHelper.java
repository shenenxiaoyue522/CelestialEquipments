package com.xiaoyue.celestial_equipments.content.equipments.digger;

import com.xiaoyue.celestial_equipments.content.items.generic.IGenericDigger;
import com.xiaoyue.celestial_equipments.data.CETagGen;
import com.xiaoyue.celestial_invoker.content.ancillary.entry.ToolStats;
import com.xiaoyue.celestial_invoker.invoker.tooltip.SubscribeTooltip;
import com.xiaoyue.celestial_invoker.invoker.tooltip.TooltipEntry;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;

import java.util.List;

public class GravediggersHelper extends IGenericDigger.Pickaxe {
    public static final ToolStats STAT = ToolStats.builder().durability(5000).speed(8f).attack(5f).enchant(15).build();

    public GravediggersHelper() {
        super(STAT, new Item.Properties().rarity(Rarity.RARE));
    }

    @SubscribeTooltip(id = "gravediggers_helper")
    public static TooltipEntry tooltip = TooltipEntry.define("Dig extremely fast, but only mine stones and cannot obtain drops");

    @Override
    public void addEquipmentTooltips(ItemStack stack, List<Component> list) {
        list.add(tooltip.withGray());
    }

    @Override
    public boolean mineBlock(ItemStack pStack, Level pLevel, BlockState pState, BlockPos pPos, LivingEntity pEntityLiving) {
        if (!this.isEnabled()) {
            return false;
        } else {
            if (!pLevel.isClientSide() && pState.getDestroySpeed(pLevel, pPos) != 0.0F) {
                pStack.hurtAndBreak(1, pEntityLiving, (p) -> p.broadcastBreakEvent(EquipmentSlot.MAINHAND));
            }
            return pState.is(CETagGen.GRAVEDIGGERS_HELPER_MINABLE);
        }
    }

    @Override
    public float getDestroySpeed(ItemStack pStack, BlockState pState) {
        return this.isEnabled() && pState.is(CETagGen.GRAVEDIGGERS_HELPER_MINABLE) ? 2222f : 0f;
    }

    @Override
    public boolean isCorrectToolForDrops(ItemStack stack, BlockState state) {
        return false;
    }
}
