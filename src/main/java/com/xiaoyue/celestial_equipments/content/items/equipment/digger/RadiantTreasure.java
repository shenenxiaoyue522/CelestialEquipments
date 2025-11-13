package com.xiaoyue.celestial_equipments.content.items.equipment.digger;

import com.xiaoyue.celestial_equipments.content.items.generic.IGenericDigger;
import com.xiaoyue.celestial_invoker.content.ancillary.material.ToolStats;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.block.state.BlockState;

public class RadiantTreasure extends IGenericDigger.Pickaxe {
    public RadiantTreasure() {
        super(new ToolStats(3200, 8.0F, 6.0F, 3, 22, Ingredient.of()), new Item.Properties().rarity(Rarity.UNCOMMON));
    }

    @Override
    public boolean isCorrectToolForDrops(ItemStack stack, BlockState state) {
        return this.isEnabled() || super.isCorrectToolForDrops(stack, state);
    }
}
