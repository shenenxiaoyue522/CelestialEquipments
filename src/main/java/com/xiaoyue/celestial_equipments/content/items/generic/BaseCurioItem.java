package com.xiaoyue.celestial_equipments.content.items.generic;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraftforge.registries.ForgeRegistries;
import top.theillusivec4.curios.api.CuriosApi;
import top.theillusivec4.curios.api.SlotContext;
import top.theillusivec4.curios.api.type.capability.ICurioItem;

public class BaseCurioItem extends Item implements ICurioItem {
    public BaseCurioItem(Properties properties) {
        super(properties.stacksTo(1).rarity(Rarity.EPIC));
    }

    @Override
    public boolean canEquip(SlotContext slotContext, ItemStack stack) {
        if (slotContext.entity() instanceof Player player) {
            var repeat = CuriosApi.getCuriosInventory(player).resolve()
                    .flatMap(e -> e.findFirstCurio(this));
            if (repeat.isEmpty()) return true;
            var rep = repeat.get().slotContext();
            return rep.identifier().equals(slotContext.identifier()) && rep.index() == slotContext.index();
        }
        return false;
    }

    public String bonusModifierName(String attr) {
        return ForgeRegistries.ITEMS.getKey(this) + "_" + attr + "_bonus";
    }
}
