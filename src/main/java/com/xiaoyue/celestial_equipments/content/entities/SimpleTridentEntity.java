package com.xiaoyue.celestial_equipments.content.entities;

import com.xiaoyue.celestial_equipments.content.library.SimpleThrowingFactory;
import com.xiaoyue.celestial_equipments.register.CEEntities;
import com.xiaoyue.celestial_invoker.content.entities.SimpleThrowEntity;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;

public class SimpleTridentEntity extends SimpleThrowEntity {
    public SimpleTridentEntity(LivingEntity pShooter, Level pLevel, ItemStack stack) {
        super(CEEntities.SIMPLE_TRIDENT.get(), pShooter, pLevel, stack);
    }

    public SimpleTridentEntity(EntityType<? extends AbstractArrow> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }

    @Override
    protected void onHitEntity(EntityHitResult pResult) {
        if (weapon.getItem() instanceof SimpleThrowingFactory factory) {
            factory.onHitEntity(this, pResult.getEntity());
        }
        super.onHitEntity(pResult);
    }

    @Override
    protected void onHitBlock(BlockHitResult pResult) {
        if (weapon.getItem() instanceof SimpleThrowingFactory factory) {
            factory.onHitBlock(this, pResult.getBlockPos());
        }
        super.onHitBlock(pResult);
    }

    @Override
    public Component getName() {
        return weapon.isEmpty() ? super.getName() : weapon.getDisplayName();
    }
}
