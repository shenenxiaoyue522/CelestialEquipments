package com.xiaoyue.celestial_equipments.content.items.generic;

import com.xiaoyue.celestial_equipments.content.library.ICEquipment;
import com.xiaoyue.celestial_equipments.utils.EquipmentUtils;
import com.xiaoyue.celestial_invoker.content.generic.item.ExtraDataArmorItem;
import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.entity.living.LivingDeathEvent;

import java.util.ArrayList;
import java.util.List;

public class GenericArmor extends ExtraDataArmorItem implements ICEquipment {
    public static final List<GenericArmor> ARMORS = new ArrayList<>();

    public GenericArmor(ArmorMaterial material, Type pType, Properties pProperties) {
        super(material, pType, pProperties);
        ARMORS.add(this);
    }

    @Override
    public Component getName(ItemStack pStack) {
        return this.getItemName(pStack);
    }

    @Override
    public void addBaseTooltips(ItemStack stack, List<Component> list, boolean singleLevel) {
        list.add(Component.empty());
        if (!Screen.hasShiftDown()) {
            list.add(shiftDown.withGray(Component.literal("SHIFT").withStyle(ChatFormatting.YELLOW)));
        } else {
            EquipmentUtils.addExpTooltips(list, stack);
            if (!getSetArmors().isEmpty()) {
                list.add(Component.empty());
            }
            if (EquipmentUtils.getLevel(stack) > 0 || singleLevel) {
                list.add(Component.empty());
                this.addEffectTooltips(stack, list);
            }
        }
    }

    @Override
    public void addTooltips(ItemStack stack, List<Component> list, EquipmentSlot slot) {
        this.addBaseTooltips(stack, list);
        if (!this.isEnabled()) {
            list.add(Component.empty());
            list.add(itemBan.withGray());
        }
    }

    @Override
    public Item self() {
        return this;
    }

    public void onDeath(ItemStack stack, LivingEntity entity, LivingDeathEvent event, int lv) {

    }
}
