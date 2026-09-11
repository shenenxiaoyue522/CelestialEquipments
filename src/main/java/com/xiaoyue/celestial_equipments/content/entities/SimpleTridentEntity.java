package com.xiaoyue.celestial_equipments.content.entities;

import com.xiaoyue.celestial_equipments.content.items.generic.SimpleThrowingFactory;
import com.xiaoyue.celestial_equipments.data.CETagGen;
import com.xiaoyue.celestial_equipments.register.CEEntities;
import com.xiaoyue.celestial_invoker.content.entities.SimpleThrowEntity;
import com.xiaoyue.celestial_invoker.content.entities.render.ThrownEntityRender;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.Vec3;

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
    protected double getDamage(Entity target) {
        return super.getDamage(target);
    }

    @Override
    public Component getName() {
        return weapon.isEmpty() ? super.getName() : weapon.getDisplayName();
    }

    public static class Render extends ThrownEntityRender<SimpleTridentEntity> {
        public Render(EntityRendererProvider.Context context) {
            super(context, new Vec3(0.85f, 0.85f, 0.85f), 0.1f, (p, e) -> {
                if (e.weapon.is(CETagGen.SMALL_THROWING)) {
                    p.scale(2.5f, 1.25f, 2.5f);
                } else {
                    p.scale(4f, 2f, 4f);
                }
            });
        }
    }
}
