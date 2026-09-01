package com.xiaoyue.celestial_equipments.content.library;

import dev.xkmc.l2damagetracker.contents.attack.AttackCache;
import dev.xkmc.l2damagetracker.contents.attack.CreateSourceEvent;
import net.minecraft.tags.DamageTypeTags;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.entity.living.LivingDeathEvent;

public interface IAttackConfig {

    static boolean isProjectile(DamageSource source) {
        return source.is(DamageTypeTags.IS_PROJECTILE) || source.getDirectEntity() != source.getEntity();
    }

    static boolean isMelee(DamageSource source) {
        return source.getEntity() != null && source.getDirectEntity() != null && source.getEntity().equals(source.getDirectEntity());
    }

    default void onCreateSource(ItemStack stack, LivingEntity attacker, CreateSourceEvent event, int lv) {
    }

    default void onMeleeHurt(ItemStack stack, LivingEntity attacker, AttackCache cache, int lv) {
    }

    default void onProjectileHurt(ItemStack stack, LivingEntity attacker, AttackCache cache, int lv) {
    }

    default void onMeleeKill(ItemStack stack, LivingEntity attacker, LivingDeathEvent event, int lv) {
    }
}
