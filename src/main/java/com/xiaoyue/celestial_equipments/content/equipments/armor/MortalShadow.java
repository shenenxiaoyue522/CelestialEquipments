package com.xiaoyue.celestial_equipments.content.equipments.armor;

import com.xiaoyue.celestial_equipments.content.items.generic.UpgradeableArmor;
import com.xiaoyue.celestial_equipments.register.CEItems;
import com.xiaoyue.celestial_invoker.content.common.entry.ArmorMate;
import com.xiaoyue.celestial_invoker.content.common.entry.ArmorSetEntry;
import com.xiaoyue.celestial_invoker.invoker.tooltip.SubscribeTooltip;
import com.xiaoyue.celestial_invoker.invoker.tooltip.TooltipEntry;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.Nullable;

public class MortalShadow extends UpgradeableArmor {
    public static final ArmorMate MATE = ArmorMate.builder().durability(29).defense(new int[]{3, 7, 5, 3}).toughness(1f).build();

    public MortalShadow(Type pType) {
        super(MATE, pType, new Properties());
    }

    public static String createName(Type type) {
        String name = switch (type) {
            case HELMET -> "mortal_shadow_hood";
            case CHESTPLATE -> "mortal_shadow_chestplate";
            case LEGGINGS -> "mortal_shadow_leggings";
            case BOOTS -> "mortal_shadow_boots";
        };
        CEItems.ALL_EQUIPMENTS.add(name);
        return name;
    }

    @SubscribeTooltip(id = "mortal_shadow_set_id")
    public static TooltipEntry setIdTooltip = TooltipEntry.define("Mortal Shadow");

    @Override
    public MutableComponent getSetName() {
        return setIdTooltip.withColor(ChatFormatting.DARK_PURPLE);
    }

    @Override
    public @Nullable String getArmorTexture(ItemStack stack, Entity entity, EquipmentSlot slot, String type) {
        return EMPTY_MODEL_TEX;
    }

    @Override
    public ArmorSetEntry<? extends Item> getArmorSet() {
        return CEItems.MORTAL_SHADOW;
    }
}
