package com.xiaoyue.celestial_equipments.content.items.curios;

import com.xiaoyue.celestial_core.data.CCDamageTypes;
import com.xiaoyue.celestial_core.utils.EntityUtils;
import com.xiaoyue.celestial_equipments.register.CEItems;
import com.xiaoyue.celestial_equipments.utils.EquipmentUtils;
import com.xiaoyue.celestial_invoker.content.common.helper.FindTargetHelper;
import com.xiaoyue.celestial_invoker.invoker.config.ConfigHolderEntry;
import com.xiaoyue.celestial_invoker.invoker.config.value.DoubleConfigEntry;
import com.xiaoyue.celestial_invoker.invoker.config.value.IntConfigEntry;
import com.xiaoyue.celestial_invoker.invoker.tooltip.SubscribeTooltip;
import com.xiaoyue.celestial_invoker.invoker.tooltip.TooltipEntry;
import com.xiaoyue.celestial_invoker.invoker.tooltip.TooltipHolder;
import net.minecraft.network.chat.Component;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;
import top.theillusivec4.curios.api.SlotContext;

import java.util.List;

public class CursedVisage extends CurioItem {
    public CursedVisage(Properties properties) {
        super(properties);
    }

    @ConfigHolderEntry(category = "curios")
    public static IntConfigEntry flameTimeConfig = IntConfigEntry.defineFromZero("Cursed Visage Abyssal Flame Time",
            2, 100, "Cursed Visage: Abyssal Flame duration applied");

    @ConfigHolderEntry(category = "curios")
    public static DoubleConfigEntry healConfig = DoubleConfigEntry.defineFromMinUsable("Cursed Visage Heal Factor",
            0.05, 10, "Cursed Visage: Heals for missing health percentage");

    @SubscribeTooltip(id = "cursed_visage")
    public static TooltipHolder tooltips = TooltipHolder.define(
            TooltipEntry.define("The creature you are targeting is set ablaze with Abyssal Flame for %s seconds"),
            TooltipEntry.define("When nearby creatures within the Abyssal Flame take Abyssal damage"),
            TooltipEntry.define("Restore %s of your missing health"));

    @Override
    public void appendHoverText(ItemStack pStack, @Nullable Level pLevel, List<Component> list, TooltipFlag pIsAdvanced) {
        list.add(tooltips.get(0).withGray(TooltipEntry.num(flameTimeConfig.get())));
        list.add(tooltips.get(1).withGray());
        list.add(tooltips.get(2).withGray(TooltipEntry.per(healConfig.get())));
    }

    @Override
    public void curioTick(SlotContext ctx, ItemStack stack) {
        if (ctx.entity() instanceof Player player) {
            Entity target = FindTargetHelper.getEntityInCrosshair(player, 6, e -> true);
            if (target instanceof LivingEntity entity && !EntityUtils.onBlackFlame(entity)) {
                EntityUtils.startAddBlackFlame(entity, flameTimeConfig.get() * 20);
            }
        }
    }

    public static void onOtherDamaged(LivingEntity entity, DamageSource source) {
        if (!source.is(CCDamageTypes.ABYSSAL_MAGIC) || !EntityUtils.onBlackFlame(entity)) return;
        for (LivingEntity other : EntityUtils.getExceptForCentralEntity(entity, 6, 2)) {
            if (EquipmentUtils.hasCurio(other, CEItems.CURSED_VISAGE.get())) {
                other.heal((other.getMaxHealth() - other.getHealth()) * healConfig.floatValue());
            }
        }
    }
}
