package com.xiaoyue.celestial_equipments.content.items.generic;

import com.xiaoyue.celestial_equipments.content.library.ICEquipment;
import com.xiaoyue.celestial_equipments.utils.EquipmentUtils;
import com.xiaoyue.celestial_invoker.content.generic.item.CelestialArmorItem;
import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class UpgradeableArmor extends CelestialArmorItem implements ICEquipment {

    public static final List<UpgradeableArmor> ARMORS = new ArrayList<>();
    public static final String EMPTY_MODEL_TEX = "celestial_equipments:textures/models/armor/empty_armor_model.png";

    public UpgradeableArmor(ArmorMaterial material, Type pType, Properties pProperties) {
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
        if (!singleLevel) {
            if (!Screen.hasShiftDown()) {
                list.add(shiftDownTooltip.withGray(Component.literal("SHIFT").withStyle(ChatFormatting.YELLOW)));
            } else {
                EquipmentUtils.addExpTooltips(list, stack);
                if (EquipmentUtils.getLevel(stack) > 0) {
                    this.addTooltips(stack, list);
                }
            }
        } else {
            this.addTooltips(stack, list);
        }
        if (!this.isEnabled()) {
            list.add(Component.empty());
            list.add(itemBanTooltip.withGray());
        }
    }

    @Override
    public void addTooltips(ItemStack stack, List<Component> list, EquipmentSlot slot) {
        this.addBaseTooltips(stack, list);
        if (!this.isEnabled()) {
            list.add(Component.empty());
            list.add(itemBanTooltip.withGray());
        }
    }

    @Override
    public boolean requiredAltDown() {
        return false;
    }

    @Override
    public boolean isUpgradeable() {
        return false;
    }
}
