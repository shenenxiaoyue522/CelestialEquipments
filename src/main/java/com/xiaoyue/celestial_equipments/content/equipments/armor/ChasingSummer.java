package com.xiaoyue.celestial_equipments.content.equipments.armor;

import com.xiaoyue.celestial_equipments.content.items.generic.UpgradeableArmor;
import com.xiaoyue.celestial_equipments.register.CEItems;
import com.xiaoyue.celestial_invoker.content.common.entry.ArmorMate;
import com.xiaoyue.celestial_invoker.content.common.entry.ArmorSetEntry;
import com.xiaoyue.celestial_invoker.invoker.config.ConfigHolderEntry;
import com.xiaoyue.celestial_invoker.invoker.config.value.DoubleConfigEntry;
import com.xiaoyue.celestial_invoker.invoker.tooltip.SubscribeTooltip;
import com.xiaoyue.celestial_invoker.invoker.tooltip.TooltipEntry;
import com.xiaoyue.celestial_invoker.invoker.tooltip.TooltipHolder;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class ChasingSummer extends UpgradeableArmor {
    public static final ArmorMate MATE = ArmorMate.builder().durability(22).defense(new int[]{3, 6, 4, 3}).enchant(24).build();

    public ChasingSummer(Type pType) {
        super(MATE, pType, new Properties());
    }

    public static String createName(Type type) {
        String name = switch (type) {
            case HELMET -> "chasing_summer_hat";
            case CHESTPLATE -> "chasing_summer_clothes";
            case LEGGINGS -> "chasing_summer_dress";
            case BOOTS -> "chasing_summer_socks";
        };
        CEItems.ALL_EQUIPMENTS.add(name);
        return name;
    }

    @ConfigHolderEntry(category = "armor")
    public static DoubleConfigEntry healConfig = DoubleConfigEntry.defineSmallRange("Chasing Summer Heal Factor",
            0.03, "Chasing Summer: Change amount heal multiplier");

    @ConfigHolderEntry(category = "armor")
    public static DoubleConfigEntry hpTransferConfig = DoubleConfigEntry.defineSmallRange("Chasing Summer Health Transfer",
            0.5, "Chasing Summer: Transferring health to the victim multiplier");

    @SubscribeTooltip(id = "chasing_summer_set_id")
    public static TooltipEntry setIdTooltip = TooltipEntry.define("Maiden's Past Remembrance");

    @SubscribeTooltip(id = "chasing_summer_set")
    public static TooltipHolder tooltips = TooltipHolder.define(
            TooltipEntry.define("When the wearer's health changes"),
            TooltipEntry.define("The %s of the change amount is used to heal itself"),
            TooltipEntry.define("When a nearby player is about to die, help them block the damage"),
            TooltipEntry.define("And %s of the wearer's remaining health is given to the player"));

    @Override
    public MutableComponent getSetName() {
        return setIdTooltip.withColor(ChatFormatting.LIGHT_PURPLE);
    }

    @Override
    public void addSetTooltips(ItemStack stack, List<Component> list) {
        list.add(tooltips.get(0).withGray());
        list.add(tooltips.get(1).withGray(TooltipEntry.per(healConfig.get())));
        list.add(tooltips.get(2).withGray());
        list.add(tooltips.get(3).withGray(TooltipEntry.per(hpTransferConfig.get())));
    }

    @Override
    public @Nullable String getArmorTexture(ItemStack stack, Entity entity, EquipmentSlot slot, String type) {
        return EMPTY_MODEL_TEX;
    }

    @Override
    public ArmorSetEntry<? extends Item> getArmorSet() {
        return CEItems.CHASING_SUMMER;
    }
}
