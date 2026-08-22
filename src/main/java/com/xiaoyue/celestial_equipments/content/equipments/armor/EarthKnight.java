package com.xiaoyue.celestial_equipments.content.equipments.armor;

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
import dev.xkmc.l2library.init.events.GeneralEventHandler;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemCooldowns;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class EarthKnight extends GenericArmorItem implements ISetHandler {
    public static final ArmorMate MATE = ArmorMate.builder().durability(43).defense(new int[]{4, 6, 8, 4}).toughness(1.5f).knockResist(0.1f).build();

    public EarthKnight(Type pType) {
        super(MATE, pType, new Properties());
    }

    public static String createName(ArmorItem.Type type) {
        String name = "earth_knight_" + type.getName();
        CEItems.ALL_EQUIPMENTS.add(name);
        return name;
    }

    @ConfigHolderEntry(category = "armor")
    public static IntConfigEntry cooldownConfig = IntConfigEntry.define("Earth Knight Cooldown Time",
            20, 1, 1000000, "Earth Knight: Cooldown time");

    @ConfigHolderEntry(category = "armor")
    public static IntConfigEntry deathCooldownConfig = IntConfigEntry.define("Earth Knight Death Cooldown Time",
            6000, 1, 1000000, "Earth Knight: Death Cooldown time");

    @ConfigHolderEntry(category = "armor")
    public static DoubleConfigEntry dmgConfig = DoubleConfigEntry.defineSmallRange("Earth Knight Counterattack Damage Factor",
            0.35, "Earth Knight: Counterattack damage multiplier");

    @SubscribeTooltip(id = "earth_knight_set_id")
    public static TooltipEntry setIdTooltip = TooltipEntry.define("Earth Knight's Aegis");

    @SubscribeTooltip(id = "earth_knight_set")
    public static TooltipHolder tooltips = TooltipHolder.define(
      TooltipEntry.define("When attacked, it reflects the attacker's %s damage"),
            TooltipEntry.define("After the counterattack triggers, the %s second cooldown begins"),
            TooltipEntry.define("When the wearer dies, the cooldown starts with %s seconds"));

    @Override
    public MutableComponent getSetName() {
        return setIdTooltip.withColor(ChatFormatting.DARK_GREEN);
    }

    @Override
    public void addSetTooltips(ItemStack stack, List<Component> list) {
        list.add(tooltips.get(0).withGray(TooltipEntry.per(dmgConfig.get())));
        list.add(tooltips.get(1).withGray(TooltipEntry.num(cooldownConfig.get() / 20)));
        list.add(tooltips.get(2).withGray(TooltipEntry.num(deathCooldownConfig.get() / 20)));
    }

    @Override
    public @Nullable String getArmorTexture(ItemStack stack, Entity entity, EquipmentSlot slot, String type) {
        return EMPTY_MODEL_TEX;
    }

    @Override
    public ArmorSetEntry<? extends Item> getArmorSet() {
        return CEItems.EARTH_KNIGHT;
    }

    @Override
    public void onSetActivate(Player player) {
        player.sendSystemMessage(Component.literal("a"));
    }

    @Override
    public void onSetDeactivate(Player player) {
        player.sendSystemMessage(Component.literal("b"));
    }

    @Override
    public void onPlayerDamaged(Player player, LivingDamageEvent event, DamageSource source) {
        if (source.getEntity() instanceof LivingEntity attacker) {
            ItemCooldowns cooldowns = player.getCooldowns();
            Item chest = getArmorSet().getChestplate().get();
            if (!cooldowns.isOnCooldown(chest)) {
                float damage = event.getAmount() * dmgConfig.floatValue();
                GeneralEventHandler.schedule(() -> attacker.hurt(player.damageSources().playerAttack(player), damage));
                cooldowns.addCooldown(chest, cooldownConfig.get());
            }
        }
    }

    @Override
    public void onPlayerDeath(Player player, LivingDeathEvent event, DamageSource source) {
        Item chest = getArmorSet().getChestplate().get();
        player.getCooldowns().addCooldown(chest, deathCooldownConfig.get());
    }
}
