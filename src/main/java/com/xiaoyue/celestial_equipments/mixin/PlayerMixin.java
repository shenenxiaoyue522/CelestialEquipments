package com.xiaoyue.celestial_equipments.mixin;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import com.xiaoyue.celestial_equipments.register.CEEffects;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.player.Player;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(Player.class)
public class PlayerMixin {

    @ModifyReturnValue(at = @At("TAIL"), method = "getCurrentItemAttackStrengthDelay")
    public float celestial_equipments$setAttackCooldown(float original) {
        Player player = (Player) (Object) this;
        MobEffectInstance effect = player.getEffect(CEEffects.GALE_FORCE.get());
        if (effect != null) {
            return original * (1 - effect.getAmplifier() * 0.01f);
        }
        return original;
    }
}
