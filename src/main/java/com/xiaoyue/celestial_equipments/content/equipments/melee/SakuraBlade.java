package com.xiaoyue.celestial_equipments.content.equipments.melee;

import com.xiaoyue.celestial_equipments.content.entities.SimpleTridentEntity;
import com.xiaoyue.celestial_equipments.content.items.generic.UpgradeableMelee;
import com.xiaoyue.celestial_equipments.content.library.MeleeType;
import com.xiaoyue.celestial_invoker.invoker.config.ConfigHolderEntry;
import com.xiaoyue.celestial_invoker.invoker.config.value.DoubleConfigEntry;
import com.xiaoyue.celestial_invoker.invoker.tooltip.SubscribeTooltip;
import com.xiaoyue.celestial_invoker.invoker.tooltip.TooltipEntry;
import com.xiaoyue.celestial_invoker.invoker.tooltip.TooltipHolder;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

import java.util.List;

public class SakuraBlade extends UpgradeableMelee {
    public SakuraBlade() {
        super(MeleeType.SMALL);
    }

    @ConfigHolderEntry(category = "melee")
    public static DoubleConfigEntry dmgConfig = DoubleConfigEntry.defineBigRange("Sakura Blade Throw Damage Factor", 2,
            "Throw damage factor");

    @SubscribeTooltip(id = "sakura_blade")
    public static TooltipHolder tooltips = TooltipHolder.define(
            TooltipEntry.define("When hitting a creature, it deals attack damage attribute %s attack damage"));

    @Override
    public void addTooltips(ItemStack stack, List<Component> list, int lv) {
        list.add(throwableTooltip.withGray());
        list.add(tooltips.get(0).withGray(TooltipEntry.per(dmgConfig.get())));
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand pUsedHand) {
        ItemStack stack = pPlayer.getItemInHand(pUsedHand);
        SimpleTridentEntity entity = new SimpleTridentEntity(pPlayer, pLevel, stack);
        entity.setBaseDamage(pPlayer.getAttributeValue(Attributes.ATTACK_DAMAGE) * 2);
        entity.shootFromRotation(pPlayer, pPlayer.getXRot(), pPlayer.getYRot(), 0f, 5f, 0.85f);
        pLevel.addFreshEntity(entity);
        stack.shrink(1);
        return InteractionResultHolder.success(stack);
    }

    @Override
    public boolean isUpgradeable() {
        return false;
    }
}
