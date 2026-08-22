package com.xiaoyue.celestial_equipments.content.effects;

import com.xiaoyue.celestial_core.content.generic.CelestialEffect;
import dev.xkmc.l2library.util.math.MathHelper;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraftforge.common.ForgeMod;

public class AquaFun extends CelestialEffect {
    public AquaFun() {
        super(MobEffectCategory.BENEFICIAL, 0xff00a2ff);
        String uuid = MathHelper.getUUIDFromString("celestial_equipments:aqua_fun").toString();
        addAttributeModifier(ForgeMod.SWIM_SPEED.get(), uuid, 0.1, AttributeModifier.Operation.MULTIPLY_BASE);
    }

    @Override
    public boolean beRemove(MobEffectInstance instance, LivingEntity entity) {
        return false;
    }
}
