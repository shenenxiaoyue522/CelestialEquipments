package com.xiaoyue.celestial_equipments.content.items.curios;

import com.xiaoyue.celestial_core.utils.EntityUtils;
import com.xiaoyue.celestial_equipments.content.items.generic.BaseCurioItem;
import com.xiaoyue.celestial_equipments.register.CEEffects;
import com.xiaoyue.celestial_equipments.register.CEItems;
import com.xiaoyue.celestial_equipments.utils.EquipmentUtils;
import com.xiaoyue.celestial_invoker.invoker.config.ConfigHolderEntry;
import com.xiaoyue.celestial_invoker.invoker.config.value.DoubleConfigEntry;
import com.xiaoyue.celestial_invoker.invoker.tooltip.SubscribeTooltip;
import com.xiaoyue.celestial_invoker.invoker.tooltip.TooltipEntry;
import com.xiaoyue.celestial_invoker.invoker.tooltip.TooltipHolder;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class GaleGrip extends BaseCurioItem {
    public GaleGrip(Properties properties) {
        super(properties);
    }

    @ConfigHolderEntry(category = "curios")
    public static DoubleConfigEntry reduceConfig = DoubleConfigEntry.defineChance("Gale Grip Cooldown Per Reduce",
            0.1, "Gale Grip: Cooldown reduction per attack");

    @ConfigHolderEntry(category = "curios")
    public static DoubleConfigEntry maxReduceConfig = DoubleConfigEntry.defineChance("Gale Grip Max Cooldown Reduce",
            1, "Gale Grip: Max cooldown reduction");

    @SubscribeTooltip(id = "gale_grip")
    public static TooltipHolder tooltips = TooltipHolder.define(
            TooltipEntry.define("When fully attacking a creature"),
            TooltipEntry.define("Reduces your attack interval by %s for a short duration"),
            TooltipEntry.define("Attack interval can be shortened by up to %s"));

    @Override
    public void appendHoverText(ItemStack pStack, @Nullable Level pLevel, List<Component> list, TooltipFlag pIsAdvanced) {
        list.add(tooltips.get(0).withGray());
        list.add(tooltips.get(1).withGray(TooltipEntry.per(reduceConfig.get())));
        list.add(tooltips.get(2).withGray(TooltipEntry.per(maxReduceConfig.get())));
    }

    public static void onAttack(LivingEntity attacker) {
        if (attacker instanceof Player player && EquipmentUtils.hasCurio(player, CEItems.GALE_GRIP.get()) && EntityUtils.isFullCharged(player)) {
            int level = EntityUtils.getEffectLevel(player, CEEffects.GALE_FORCE.get());
            int min = (int) Math.min(reduceConfig.get() * 100 + level, maxReduceConfig.get() * 100);
            player.sendSystemMessage(Component.literal(min + ""));
            EntityUtils.addEct(player, CEEffects.GALE_FORCE.get(), 100, min);
        }
    }
}
