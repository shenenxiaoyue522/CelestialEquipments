package com.xiaoyue.celestial_equipments.content.entities;

import com.xiaoyue.celestial_equipments.register.CEEntities;
import com.xiaoyue.celestial_invoker.content.entities.SimpleThrowEntity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;

public class EnderThrowingAxeEntity extends SimpleThrowEntity {
    public EnderThrowingAxeEntity(LivingEntity pShooter, Level pLevel, ItemStack stack) {
        super(CEEntities.ENDER_THROWING_AXE.get(), pShooter, pLevel, stack);
    }

    public EnderThrowingAxeEntity(EntityType<? extends SimpleThrowEntity> type, Level level) {
        super(type, level);
    }

    @Override
    protected void onHitEntity(EntityHitResult pResult) {
        super.onHitEntity(pResult);
        if (getOwner() instanceof Player player) {
            player.addItem(weapon);
        }
    }

    @Override
    protected void onHitBlock(BlockHitResult pResult) {
        if (getOwner() instanceof Player player) {
            player.addItem(weapon);
        }
    }

    @Override
    protected boolean canDrop() {
        return false;
    }
}
