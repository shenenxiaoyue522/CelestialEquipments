package com.xiaoyue.celestial_equipments.content.items.equipment.digger;

import com.xiaoyue.celestial_equipments.content.items.generic.IGenericDigger;
import com.xiaoyue.celestial_invoker.content.ancillary.material.ToolStats;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.Tags.Blocks;

public class GravediggersHelper extends IGenericDigger.Pickaxe {
    public GravediggersHelper() {
        super(new ToolStats(5000, 8.0F, 5.0F, 0, 15, Ingredient.of()), new Item.Properties().rarity(Rarity.RARE));
    }

    @Override
    public boolean mineBlock(ItemStack pStack, Level pLevel, BlockState pState, BlockPos pPos, LivingEntity pEntityLiving) {
        if (!this.isEnabled()) {
            return false;
        } else {
            if (!pLevel.isClientSide && pState.getDestroySpeed(pLevel, pPos) != 0.0F) {
                pStack.hurtAndBreak(1, pEntityLiving, (p) -> p.broadcastBreakEvent(EquipmentSlot.MAINHAND));
            }
            return pState.is(Blocks.STONE);
        }
    }

    @Override
    public float getDestroySpeed(ItemStack pStack, BlockState pState) {
        return this.isEnabled() && pState.is(Blocks.STONE) ? 2222.0F : super.getDestroySpeed(pStack, pState);
    }

    @Override
    public boolean isCorrectToolForDrops(ItemStack stack, BlockState state) {
        return false;
    }
}
