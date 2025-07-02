package com.xiaoyue.celestial_equipments.event;

import com.xiaoyue.celestial_equipments.generic.SimpleMeleeWeapon;
import dev.xkmc.l2damagetracker.contents.attack.AttackCache;
import dev.xkmc.l2damagetracker.contents.attack.AttackListener;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;

public class CEAttackListener implements AttackListener {

    @Override
    public void onHurtMaximized(AttackCache cache, ItemStack weapon) {
        LivingEntity attacker = cache.getAttacker();
        if (attacker != null) {
            if (attacker.getMainHandItem().getItem() instanceof SimpleMeleeWeapon melee) {
                melee.onHurtTarget(attacker, cache);
            }
        }
    }
}
