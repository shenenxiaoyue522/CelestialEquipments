package com.xiaoyue.celestial_equipments.content.entities;

import com.xiaoyue.celestial_equipments.content.equipments.misc.Senbonzakura;
import com.xiaoyue.celestial_equipments.register.CEEntities;
import com.xiaoyue.celestial_equipments.register.CEItems;
import com.xiaoyue.celestial_invoker.content.entities.SimpleThrowEntity;
import com.xiaoyue.celestial_invoker.content.entities.render.ThrownEntityRender;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.Vec3;

public class SakuraBladeEntity extends SimpleThrowEntity {
    public SakuraBladeEntity(LivingEntity pShooter, Level pLevel, ItemStack stack) {
        super(CEEntities.SAKURA_BLADE.get(), pShooter, pLevel, stack);
    }

    public SakuraBladeEntity(EntityType<? extends AbstractArrow> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }

    @Override
    public Component getName() {
        return CEItems.SAKURA_BLADE.asStack().getDisplayName();
    }

    @Override
    protected void onHitEntity(EntityHitResult pResult) {
        if (getOwner() instanceof LivingEntity entity) {
            double damage = entity.getAttributeValue(Attributes.ATTACK_DAMAGE) * Senbonzakura.dmgConfig.floatValue();
            entity.heal((entity.getMaxHealth() - entity.getHealth()) * Senbonzakura.healConfig.floatValue());
            pResult.getEntity().hurt(entity.damageSources().mobProjectile(this, entity), (float) damage);
        }
        discard();
    }

    @Override
    protected void onHitBlock(BlockHitResult pResult) {
        super.onHitBlock(pResult);
        discard();
    }

    public static class Render extends ThrownEntityRender<SakuraBladeEntity> {
        public Render(EntityRendererProvider.Context context) {
            super(context, new Vec3(0.85f, 0.85f, 0.85f), 0.1f, (p, e)
                    -> p.scale(2.5f, 1.25f, 2.5f));
        }
    }
}
