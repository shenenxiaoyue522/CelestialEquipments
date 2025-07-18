package com.xiaoyue.celestial_equipments.content.generic;

import com.xiaoyue.celestial_equipments.content.generic.builder.MeleeWeaponBuilder;
import com.xiaoyue.celestial_invoker.content.generic.ISimpleItem;
import dev.xkmc.l2damagetracker.contents.attack.AttackCache;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;

public class SimpleMeleeWeapon extends ISimpleItem.Factory {
    private final MeleeWeaponBuilder builder;

    public SimpleMeleeWeapon(Properties prop, MeleeWeaponBuilder builder) {
        super(prop);
        this.builder = builder;
    }

    @Override
    public MeleeWeaponBuilder getBuilder() {
        return builder;
    }

    @Override
    public boolean hurtEnemy(ItemStack pStack, LivingEntity pTarget, LivingEntity pAttacker) {
        if (builder.effectOnMe != null) {
            builder.effectOnMe.forEach(pAttacker::addEffect);
        }
        if (builder.effectOnTarget != null) {
            builder.effectOnTarget.forEach(pAttacker::addEffect);
        }
        return false;
    }

    public void onHurtTarget(LivingEntity attacker, AttackCache cache) {
        if (builder.hurtTarget != null) {
            builder.hurtTarget.accept(attacker, cache);
        }
    }
}
