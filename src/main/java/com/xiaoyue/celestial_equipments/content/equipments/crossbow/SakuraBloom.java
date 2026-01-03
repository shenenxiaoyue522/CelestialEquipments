package com.xiaoyue.celestial_equipments.content.equipments.crossbow;

import com.xiaoyue.celestial_equipments.content.items.generic.UpgradeableCrossbow;
import com.xiaoyue.celestial_equipments.utils.EquipmentUtils;
import com.xiaoyue.celestial_invoker.content.entities.GenericArrowEntity;
import com.xiaoyue.celestial_invoker.content.generic.builder.ArrowDataBuilder;
import com.xiaoyue.celestial_invoker.invoker.config.ConfigHolderEntry;
import com.xiaoyue.celestial_invoker.invoker.config.value.DoubleConfigEntry;
import com.xiaoyue.celestial_invoker.invoker.tooltip.SubscribeTooltip;
import com.xiaoyue.celestial_invoker.invoker.tooltip.TooltipEntry;
import com.xiaoyue.celestial_invoker.invoker.tooltip.TooltipHolder;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.entity.projectile.Arrow;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

import java.util.List;

public class SakuraBloom extends UpgradeableCrossbow {
    public SakuraBloom() {
        super(new Properties().durability(2500));
    }

    @Override
    public float getAttack(int lv) {
        return 1 + 0.5f * lv;
    }

    @ConfigHolderEntry(category = "crossbow")
    public static DoubleConfigEntry dmgGrowthConfig = DoubleConfigEntry.defineSmallRange("Sakura Bloom Damage Growth",
            0.05, "Sakura Bloom: Split Arrow's damage multiplier");

    @SubscribeTooltip(id = "sakura_bloom")
    public static TooltipHolder tooltips = TooltipHolder.define(
            TooltipEntry.define("The arrows fired will be converted into splitting arrows"),
            TooltipEntry.define("When the splitting arrow hits a creature, it splits into 5 arrows"),
            TooltipEntry.define("The split arrow has %s of original arrow damage and can penetrate creatures indefinitely"));

    @Override
    public void addEquipmentTooltips(ItemStack stack, List<Component> list, int lv) {
        for (int i = 0; i < tooltips.size(); i++) {
            TooltipEntry tooltip = tooltips.get(i);
            if (i == 2) {
                list.add(tooltip.withGray(TooltipEntry.per(1 + dmgGrowthConfig.get())));
            } else {
                list.add(tooltip.withGray());
            }
        }
    }

    @Override
    protected Projectile getCustomProjectile(LivingEntity shooter, InteractionHand hand, ItemStack crossbow, ItemStack ammo, Projectile origin) {
        if (EquipmentUtils.getLevel(crossbow) >= 0) {
            GenericArrowEntity arrow = createArrowFactory(shooter, crossbow);
            arrow.setBow(crossbow);
            arrow.setArrow(ammo);
            return arrow;
        }
        return origin;
    }

    public static GenericArrowEntity createArrowFactory(LivingEntity shooter, ItemStack crossbow) {
        Level level = shooter.level();
        ArrowDataBuilder builder = new ArrowDataBuilder().hitEntity((arrow, entity) -> {
            if (!(entity instanceof LivingEntity target)) return;
            Vec3 originalDirection = arrow.getDeltaMovement().normalize();
            Vec3 targetLookDirection = target.getViewVector(1f);
            Vec3 behindDirection = targetLookDirection.reverse();
            for (int i = 0; i < 5; i++) {
                Arrow spreadArrow = new Arrow(level, shooter);
                Vec3 spawnPos = calculateSpawnPositionBehind(target, behindDirection, i);
                spreadArrow.setPos(spawnPos.x, spawnPos.y, spawnPos.z);
                Vec3 spreadDirection = calculateForwardSpread(shooter, originalDirection, i);
                float speed = (float) arrow.getDeltaMovement().length() * 0.8F;
                spreadArrow.shoot(spreadDirection.x, spreadDirection.y, spreadDirection.z, speed, 3.0F);
                spreadArrow.setBaseDamage(arrow.getBaseDamage() * (1 + 0.05 * EquipmentUtils.getLevel(crossbow)));
                spreadArrow.pickup = AbstractArrow.Pickup.DISALLOWED;
                spreadArrow.setPierceLevel((byte) 222);
                level.addFreshEntity(spreadArrow);
            }
        });
        return new GenericArrowEntity(level, shooter, builder);
    }

    private static Vec3 calculateSpawnPositionBehind(LivingEntity target, Vec3 behindDirection, int index) {
        Vec3 baseBehindPos = target.position()
                .add(behindDirection.x * 1.5, 0, behindDirection.z * 1.5)
                .add(0, target.getBbHeight() * 0.6, 0);
        Vec3 up = new Vec3(0, 1, 0);
        Vec3 right = behindDirection.cross(up).normalize();
        double spreadRadius = 0.8;
        double angle = (2 * Math.PI * index) / 5;
        double offsetX = Math.cos(angle) * spreadRadius;
        double offsetZ = Math.sin(angle) * spreadRadius;
        return baseBehindPos
                .add(right.scale(offsetX))
                .add(up.scale(offsetZ * 0.5));
    }

    private static Vec3 calculateForwardSpread(LivingEntity shooter, Vec3 originalDirection, int index) {
        double spreadAngle = Math.toRadians(10);
        double angleStep = spreadAngle / 4;
        double currentAngle = -spreadAngle / 2 + angleStep * index;
        Vec3 spreadDir = rotateVectorAroundY(originalDirection, currentAngle);
        double verticalSpread = 0.03;
        spreadDir = spreadDir.add(0, (shooter.getRandom().nextDouble() - 0.5) * verticalSpread, 0);
        return spreadDir.normalize();
    }

    private static Vec3 rotateVectorAroundY(Vec3 vector, double angle) {
        double cos = Math.cos(angle);
        double sin = Math.sin(angle);
        double x = vector.x * cos + vector.z * sin;
        double z = vector.z * cos - vector.x * sin;
        return new Vec3(x, vector.y, z);
    }
}