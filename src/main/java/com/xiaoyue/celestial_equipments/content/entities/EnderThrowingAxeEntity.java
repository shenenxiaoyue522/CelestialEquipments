package com.xiaoyue.celestial_equipments.content.entities;

import com.xiaoyue.celestial_equipments.register.CEEntities;
import com.xiaoyue.celestial_equipments.register.CEItems;
import com.xiaoyue.celestial_invoker.content.entities.SimpleThrowEntity;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;

public class EnderThrowingAxeEntity extends SimpleThrowEntity {
    public EnderThrowingAxeEntity(LivingEntity pShooter, Level pLevel) {
        super(CEEntities.ENDER_THROWING_AXE.get(), pShooter, pLevel);
        this.weapon = CEItems.ENDER_THROWING_AXE.asStack();
    }

    public EnderThrowingAxeEntity(EntityType<? extends AbstractArrow> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
        this.weapon = CEItems.ENDER_THROWING_AXE.asStack();
    }

    @Override
    protected void onHitEntity(EntityHitResult pResult) {
        if (getOwner() != null && getOwner() instanceof Player player) {
            Entity entity = pResult.getEntity();
            if (entity instanceof LivingEntity target) {
                target.hurt(player.damageSources().thrown(player, this), (float) getBaseDamage());
            }
            player.addItem(weapon);
        }
        super.onHitEntity(pResult);
    }

    @Override
    protected void onHitBlock(BlockHitResult pResult) {
        if (getOwner() != null && getOwner() instanceof Player player) {
            player.addItem(weapon);
        }
    }

    @Override
    public boolean shouldRender(double pX, double pY, double pZ) {
        return true;
    }
}
