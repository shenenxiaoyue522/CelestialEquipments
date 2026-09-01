package com.xiaoyue.celestial_equipments.content.effects;

import com.xiaoyue.celestial_core.content.generic.CelestialEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;

public class FixedEffect extends CelestialEffect {
    public FixedEffect(MobEffectCategory pCategory, int pColor) {
        super(pCategory, pColor);
    }

    @Override
    public boolean isFixed() {
        return true;
    }

    @Override
    public boolean beRemove(MobEffectInstance instance, LivingEntity entity) {
        return false;
    }
}
