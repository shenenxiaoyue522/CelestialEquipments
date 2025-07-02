package com.xiaoyue.celestial_equipments.generic;

import com.xiaoyue.celestial_equipments.content.builder.MeleeWeaponBuilder;
import com.xiaoyue.celestial_invoker.generic.SimpleItem;
import dev.xkmc.l2damagetracker.contents.attack.AttackCache;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;

public class SimpleMeleeWeapon extends SimpleItem {
    private final MeleeWeaponBuilder builder;

    public SimpleMeleeWeapon(Properties prop, MeleeWeaponBuilder builder) {
        super(prop.stacksTo(1), builder);
        this.builder = builder;
    }

    @Override
    public boolean hurtEnemy(ItemStack pStack, LivingEntity pTarget, LivingEntity pAttacker) {
        builder.effectOnMe.forEach(pAttacker::addEffect);
        builder.effectOnTarget.forEach(pTarget::addEffect);
        return false;
    }

    public void onHurtTarget(LivingEntity attacker, AttackCache cache) {
        builder.hurtTarget.accept(attacker, cache);
    }
}
