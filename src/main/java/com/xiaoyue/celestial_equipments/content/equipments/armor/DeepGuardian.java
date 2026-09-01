package com.xiaoyue.celestial_equipments.content.equipments.armor;

import com.xiaoyue.celestial_core.utils.EntityUtils;
import com.xiaoyue.celestial_equipments.content.items.generic.GenericArmorItem;
import com.xiaoyue.celestial_equipments.register.CEEffects;
import com.xiaoyue.celestial_equipments.register.CEItems;
import com.xiaoyue.celestial_invoker.content.common.entry.ArmorMate;
import com.xiaoyue.celestial_invoker.content.common.entry.ArmorSetEntry;
import com.xiaoyue.celestial_invoker.content.generic.item.api.ISetHandler;
import com.xiaoyue.celestial_invoker.invoker.tooltip.SubscribeTooltip;
import com.xiaoyue.celestial_invoker.invoker.tooltip.TooltipEntry;
import com.xiaoyue.celestial_invoker.invoker.tooltip.TooltipHolder;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class DeepGuardian extends GenericArmorItem implements ISetHandler {
    public static final ArmorMate MATE = ArmorMate.builder().durability(39).defense(new int[]{3, 5, 7, 3}).toughness(1f).build();

    public DeepGuardian(Type pType) {
        super(MATE, pType, new Properties());
    }

    public static String createName(ArmorItem.Type type) {
        String name = "deep_guardian_" + type.getName();
        CEItems.ALL_EQUIPMENTS.add(name);
        return name;
    }

    @SubscribeTooltip(id = "deep_guardian_set_id")
    public static TooltipEntry setIdTooltip = TooltipEntry.define("Deep Guardian");

    @SubscribeTooltip(id = "deep_guardian_set")
    public static TooltipHolder tooltips = TooltipHolder.define(
            TooltipEntry.define("Enhances underwater visibility"),
            TooltipEntry.define("Continuously gain %s effects"),
            TooltipEntry.define("Gradually pick up your swimming speed underwater"));

    @Override
    public MutableComponent getSetName() {
        return setIdTooltip.withColor(ChatFormatting.AQUA);
    }

    @Override
    public void addSetTooltips(ItemStack stack, List<Component> list) {
        list.add(tooltips.get(0).withGray());
        list.add(tooltips.get(1).withGray(TooltipEntry.eff(MobEffects.CONDUIT_POWER)));
        list.add(tooltips.get(2).withGray());
    }

    @Override
    public @Nullable String getArmorTexture(ItemStack stack, Entity entity, EquipmentSlot slot, String type) {
        return EMPTY_MODEL_TEX;
    }

    @Override
    public ArmorSetEntry<? extends Item> getArmorSet() {
        return CEItems.DEEP_GUARDIAN;
    }

    @Override
    public void onSetTick(Player player) {
        EntityUtils.addEct(player, MobEffects.CONDUIT_POWER, 20);
        if (player.isUnderWater() && player.tickCount % 100 == 0) {
            int level = EntityUtils.getEffectLevel(player, CEEffects.AQUA_FUN.get());
            EntityUtils.addEct(player, CEEffects.AQUA_FUN.get(), 100, Math.min(3, level + 1));
        }
    }
}
