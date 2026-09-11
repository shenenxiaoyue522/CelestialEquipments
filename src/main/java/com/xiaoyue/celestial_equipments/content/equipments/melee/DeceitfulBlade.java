package com.xiaoyue.celestial_equipments.content.equipments.melee;

import com.xiaoyue.celestial_core.register.CCEffects;
import com.xiaoyue.celestial_equipments.content.entities.SimpleTridentEntity;
import com.xiaoyue.celestial_equipments.content.items.generic.SimpleThrowingFactory;
import com.xiaoyue.celestial_equipments.content.items.generic.UpgradeableMelee;
import com.xiaoyue.celestial_equipments.content.library.MeleeType;
import com.xiaoyue.celestial_invoker.invoker.tooltip.SubscribeTooltip;
import com.xiaoyue.celestial_invoker.invoker.tooltip.TooltipEntry;
import com.xiaoyue.celestial_invoker.invoker.tooltip.TooltipHolder;
import dev.xkmc.l2library.base.effects.EffectUtil;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

import java.util.ArrayList;
import java.util.List;

public class DeceitfulBlade extends UpgradeableMelee implements SimpleThrowingFactory {
    public DeceitfulBlade() {
        super(MeleeType.SMALL);
    }

    @SubscribeTooltip(id = "deceitful_blade")
    public static TooltipHolder tooltips = TooltipHolder.define(
            TooltipEntry.define("On hit, steal all potion effects from the target"),
            TooltipEntry.define("And applies %s effect to the target"));

    @Override
    public void addTooltips(ItemStack stack, List<Component> list, int lv) {
        list.add(tooltips.get(0).withGray());
        list.add(tooltips.get(1).withGray(TooltipEntry.eff(CCEffects.FEAR_CURSE.get())));
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand hand) {
        ItemStack stack = pPlayer.getItemInHand(hand);
        SimpleTridentEntity entity = new SimpleTridentEntity(pPlayer, pLevel, stack);
        entity.setBaseDamage(pPlayer.getAttributeValue(Attributes.ATTACK_DAMAGE));
        entity.shootFromRotation(pPlayer, pPlayer.getXRot(), pPlayer.getYRot(), 0f, 5f, 0.85f);
        pLevel.addFreshEntity(entity);
        stack.shrink(1);
        return InteractionResultHolder.success(stack);
    }

    @Override
    public void onHitEntity(SimpleTridentEntity trident, Entity target) {
        if (trident.getOwner() instanceof LivingEntity owner && target instanceof LivingEntity entity) {
            List<MobEffectInstance> list = new ArrayList<>(entity.getActiveEffects());
            list.forEach(owner::addEffect);
            EffectUtil.addEffect(entity, new MobEffectInstance(CCEffects.FEAR_CURSE.get(), 1200), EffectUtil.AddReason.FORCE, owner);
        }
    }

    @Override
    public boolean isUpgradeable() {
        return false;
    }
}
