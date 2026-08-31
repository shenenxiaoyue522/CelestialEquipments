package com.xiaoyue.celestial_equipments.content.equipments.armor;

import com.xiaoyue.celestial_core.content.generic.EntityIntData;
import com.xiaoyue.celestial_equipments.CelestialEquipments;
import com.xiaoyue.celestial_equipments.content.items.generic.GenericArmorItem;
import com.xiaoyue.celestial_equipments.register.CEItems;
import com.xiaoyue.celestial_invoker.content.common.entry.ArmorMate;
import com.xiaoyue.celestial_invoker.content.common.entry.ArmorSetEntry;
import com.xiaoyue.celestial_invoker.content.generic.item.api.ISetHandler;
import com.xiaoyue.celestial_invoker.invoker.config.ConfigHolderEntry;
import com.xiaoyue.celestial_invoker.invoker.config.value.DoubleConfigEntry;
import com.xiaoyue.celestial_invoker.invoker.config.value.IntConfigEntry;
import com.xiaoyue.celestial_invoker.invoker.tooltip.SubscribeTooltip;
import com.xiaoyue.celestial_invoker.invoker.tooltip.TooltipEntry;
import com.xiaoyue.celestial_invoker.invoker.tooltip.TooltipHolder;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class MortalShadow extends GenericArmorItem implements ISetHandler {
    public static final ArmorMate MATE = ArmorMate.builder().durability(29).defense(new int[]{3, 5, 6, 3}).toughness(1f).build();
    public static final String SET_ID = CelestialEquipments.loc("mortal_shadow_set").toString();

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

    @ConfigHolderEntry(category = "armor")
    public static DoubleConfigEntry eachDmgConfig = DoubleConfigEntry.defineChance("Mortal Shadow Each Damage Factor",
            0.2, "Mortal Shadow: Damage depends on accumulated amount");

    @ConfigHolderEntry(category = "armor")
    public static IntConfigEntry dmgIntervalConfig = IntConfigEntry.defineFromZero("Mortal Shadow Damage Interval",
            2, Integer.MAX_VALUE, "Mortal Shadow: Damage interval time");

    @SubscribeTooltip(id = "mortal_shadow_set_id")
    public static TooltipEntry setIdTooltip = TooltipEntry.define("Mortal Shadow");

    @SubscribeTooltip(id = "mortal_shadow_set")
    public static TooltipHolder tooltips = TooltipHolder.define(
            TooltipEntry.define("When the wearer takes damage"),
            TooltipEntry.define("Damage taken becomes true damage and is stored"),
            TooltipEntry.define("Deals %s of stored damage every %s seconds"));

    @Override
    public MutableComponent getSetName() {
        return setIdTooltip.withColor(ChatFormatting.DARK_PURPLE);
    }

    @Override
    public void addSetTooltips(ItemStack stack, List<Component> list) {
        list.add(tooltips.get(0).withGray());
        list.add(tooltips.get(1).withGray());
        list.add(tooltips.get(2).withGray(TooltipEntry.per(eachDmgConfig.get()), TooltipEntry.num(dmgIntervalConfig.get())));
    }

    @Override
    public @Nullable String getArmorTexture(ItemStack stack, Entity entity, EquipmentSlot slot, String type) {
        return EMPTY_MODEL_TEX;
    }

    @Override
    public ArmorSetEntry<? extends Item> getArmorSet() {
        return CEItems.MORTAL_SHADOW;
    }

    @Override
    public void onSetTick(Player player) {
        int data = EntityIntData.getData(player, SET_ID);
        if (player.tickCount % (dmgIntervalConfig.get() * 20) == 0 && data > 0) {
            float damage = data * eachDmgConfig.floatValue();
            player.setHealth(player.getHealth() - damage);
            EntityIntData.addData(player, SET_ID, (int) (data - damage));
        }
    }

    public static void onHurt(LivingHurtEvent event, LivingEntity entity) {
        if (!CEItems.MORTAL_SHADOW.isFullSet(entity)) return;
        int data = EntityIntData.getData(entity, SET_ID);
        EntityIntData.addData(entity, SET_ID, data + (int) event.getAmount());
        event.setCanceled(true);
    }
}
