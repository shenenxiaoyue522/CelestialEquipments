package com.xiaoyue.celestial_equipments.content.items.curios;

import com.xiaoyue.celestial_equipments.content.items.generic.BaseCurioItem;
import com.xiaoyue.celestial_equipments.content.library.IAttackConfig;
import com.xiaoyue.celestial_equipments.data.CETagGen;
import com.xiaoyue.celestial_equipments.register.CEItems;
import com.xiaoyue.celestial_equipments.utils.EquipmentUtils;
import com.xiaoyue.celestial_invoker.invoker.config.ConfigHolderEntry;
import com.xiaoyue.celestial_invoker.invoker.config.value.DoubleConfigEntry;
import com.xiaoyue.celestial_invoker.invoker.tooltip.SubscribeTooltip;
import com.xiaoyue.celestial_invoker.invoker.tooltip.TooltipEntry;
import com.xiaoyue.celestial_invoker.invoker.tooltip.TooltipHolder;
import dev.xkmc.l2damagetracker.contents.attack.AttackCache;
import dev.xkmc.l2damagetracker.contents.attack.DamageModifier;
import net.minecraft.network.chat.Component;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class BladebiterGauntlets extends BaseCurioItem {
    public BladebiterGauntlets(Properties properties) {
        super(properties);
    }

    @ConfigHolderEntry(category = "curios")
    public static DoubleConfigEntry dmgBonusConfig = DoubleConfigEntry.defineBigRange("Bladebiter Gauntlets Damage Bonus",
            0.4, "Bladebiter Gauntlets: Increased damage dealt by the Air Blade when attacking");

    @ConfigHolderEntry(category = "curios")
    public static DoubleConfigEntry dmgReduceConfig = DoubleConfigEntry.defineChance("Bladebiter Gauntlets Damage Reduce",
            0.5, "Bladebiter Gauntlets: Reduces damage dealt when performing melee attacks");

    @SubscribeTooltip(id = "bladebiter_gauntlets")
    public static TooltipHolder tooltips = TooltipHolder.define(
            TooltipEntry.define("Increases damage dealt by the Air Blade by %s"),
            TooltipEntry.define("Reduces melee damage dealt by %s"));

    @Override
    public void appendHoverText(ItemStack pStack, @Nullable Level pLevel, List<Component> list, TooltipFlag pIsAdvanced) {
        list.add(tooltips.get(0).withGray(TooltipEntry.per(dmgBonusConfig.get())));
        list.add(tooltips.get(1).withGray(TooltipEntry.per(dmgReduceConfig.get())));
    }

    public static void onHurtTarget(LivingEntity attacker, DamageSource source, AttackCache cache) {
        if (attacker != null && EquipmentUtils.hasCurio(attacker, CEItems.BLADEBITER_GAUNTLETS.get())) {
            Entity direct = source.getDirectEntity();
            if (direct != null && direct.getType().is(CETagGen.IS_AIR_BLADE)) {
                cache.addHurtModifier(DamageModifier.multTotal(1 + dmgBonusConfig.floatValue()));
            } else if (IAttackConfig.isMelee(source)) {
                cache.addHurtModifier(DamageModifier.multTotal(1 - dmgReduceConfig.floatValue()));
            }
        }
    }
}
