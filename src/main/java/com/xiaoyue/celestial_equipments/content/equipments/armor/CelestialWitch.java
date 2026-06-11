package com.xiaoyue.celestial_equipments.content.equipments.armor;

import com.tterrag.registrate.util.entry.RegistryEntry;
import com.xiaoyue.celestial_equipments.content.items.generic.UpgradeableArmor;
import com.xiaoyue.celestial_equipments.register.CEItems;
import com.xiaoyue.celestial_invoker.content.common.entry.ArmorMate;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class CelestialWitch extends UpgradeableArmor {
    public CelestialWitch(Type pType) {
        super(ArmorMate.builder().build(), pType, new Properties());
    }

    public static String createName(ArmorItem.Type type) {
        return switch (type) {
            case HELMET -> "test_helmet";
            case CHESTPLATE -> "test_chestplate";
            case LEGGINGS -> "test_leggings";
            case BOOTS -> "test_boots";
        };
    }

    @Override
    public @Nullable String getArmorTexture(ItemStack stack, Entity entity, EquipmentSlot slot, String type) {
        return EMPTY_MODEL_TEX;
    }

    @Override
    public List<Item> getSetArmors() {
        return CEItems.CELESTIAL_WITCH.values().stream().map(RegistryEntry::get).toList();
    }
}
