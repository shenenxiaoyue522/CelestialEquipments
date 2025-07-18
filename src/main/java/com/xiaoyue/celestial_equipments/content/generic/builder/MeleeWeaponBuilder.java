package com.xiaoyue.celestial_equipments.content.generic.builder;

import com.xiaoyue.celestial_equipments.content.generic.SimpleMeleeWeapon;
import com.xiaoyue.celestial_invoker.content.generic.builder.SimpleItemBuilder;
import dev.xkmc.l2damagetracker.contents.attack.AttackCache;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;

import java.util.List;
import java.util.function.BiConsumer;

public class MeleeWeaponBuilder extends SimpleItemBuilder<MeleeWeaponBuilder> {

    public BiConsumer<LivingEntity, AttackCache> hurtTarget;
    public List<MobEffectInstance> effectOnMe, effectOnTarget;

    public static MeleeWeaponBuilder builder() {
        return new MeleeWeaponBuilder();
    }

    public MeleeWeaponBuilder onHurtTarget(BiConsumer<LivingEntity, AttackCache> cons) {
        this.hurtTarget = cons;
        return this;
    }

    public MeleeWeaponBuilder effectOnMe(MobEffectInstance... effects) {
        this.effectOnMe = List.of(effects);
        return this;
    }

    public MeleeWeaponBuilder effectOnTarget(MobEffectInstance... effects) {
        this.effectOnTarget = List.of(effects);
        return this;
    }

    public SimpleMeleeWeapon build(Item.Properties prop) {
        return new SimpleMeleeWeapon(prop, this);
    }
}
