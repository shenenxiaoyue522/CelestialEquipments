package com.xiaoyue.celestial_equipments.content.builder;

import com.xiaoyue.celestial_equipments.generic.SimpleMeleeWeapon;
import com.xiaoyue.celestial_invoker.content.generic.SimpleItemBuilder;
import dev.xkmc.l2damagetracker.contents.attack.AttackCache;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;

import java.util.List;
import java.util.function.BiConsumer;

public class MeleeWeaponBuilder extends SimpleItemBuilder<MeleeWeaponBuilder> {

    public BiConsumer<LivingEntity, AttackCache> hurtTarget;
    public List<MobEffectInstance> effectOnMe, effectOnTarget;

    public MeleeWeaponBuilder() {
        super(new MeleeWeaponBuilder());
    }

    public static MeleeWeaponBuilder builder() {
        return new MeleeWeaponBuilder();
    }

    public MeleeWeaponBuilder onHurtTarget(BiConsumer<LivingEntity, AttackCache> cons) {
        this.hurtTarget = cons;
        return this;
    }

    public MeleeWeaponBuilder effectOnMe(List<MobEffectInstance> effects) {
        this.effectOnMe = effects;
        return this;
    }

    public MeleeWeaponBuilder effectOnTarget(List<MobEffectInstance> effects) {
        this.effectOnTarget = effects;
        return this;
    }

    public SimpleMeleeWeapon build(Item.Properties prop) {
        return new SimpleMeleeWeapon(prop, this);
    }
}
