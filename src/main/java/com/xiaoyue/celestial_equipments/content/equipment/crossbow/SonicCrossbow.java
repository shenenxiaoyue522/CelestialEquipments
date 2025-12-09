package com.xiaoyue.celestial_equipments.content.equipment.crossbow;

import com.xiaoyue.celestial_equipments.content.items.generic.UpgradeableCrossbow;
import com.xiaoyue.celestial_equipments.utils.EquipmentUtils;
import com.xiaoyue.celestial_invoker.invoker.config.ConfigHolderEntry;
import com.xiaoyue.celestial_invoker.invoker.config.value.DoubleConfigEntry;
import com.xiaoyue.celestial_invoker.invoker.tooltip.SubscribeTooltip;
import com.xiaoyue.celestial_invoker.invoker.tooltip.TooltipEntry;
import com.xiaoyue.celestial_invoker.invoker.tooltip.TooltipHolder;
import dev.xkmc.l2library.util.raytrace.RayTraceUtil;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class SonicCrossbow extends UpgradeableCrossbow {
    public SonicCrossbow() {
        super(new Properties().durability(4000));
    }

    @ConfigHolderEntry(category = "crossbow")
    public static DoubleConfigEntry dmgConfig = DoubleConfigEntry.defineBigRange("Sonic Crossbow Sonic Damage", 1.6,
            "The extent to which base damage affects sonic boom damage");

    @SubscribeTooltip(id = "sonic_crossbow")
    public static TooltipHolder tooltips = TooltipHolder.define(
            TooltipEntry.define("Fires an additional sonic boom when firing to attack the creature in front of you"),
            TooltipEntry.define("The sonic boom deals base damage %s attack damage"));

    @Override
    public float getAttack(int lv) {
        return 1 + lv * 1.5f;
    }

    @Override
    public void addEquipmentTooltips(ItemStack stack, List<Component> list, int lv) {
        list.add(tooltips.get(0).withGray());
        list.add(tooltips.get(1).withGray(TooltipEntry.per(dmgConfig.get())));
    }

    @Override
    public void inventoryTick(ItemStack pStack, Level pLevel, Entity pEntity, int pSlotId, boolean pIsSelected) {
        if (EquipmentUtils.getLevel(pStack) > 0 && pEntity instanceof Player player) {
            RayTraceUtil.clientUpdateTarget(player, 17);
        }
    }

    @Override
    protected void onConfigShoot(LivingEntity shooter, InteractionHand hand, ItemStack crossbow, ItemStack ammo, Projectile projectile, @Nullable AbstractArrow arrow, int lv) {
        if (EquipmentUtils.getLevel(crossbow) > 0 && isCharged(crossbow)) {
            if (shooter.level() instanceof ServerLevel sl && shooter instanceof Player) {
                Vec3 src = shooter.getEyePosition();
                Vec3 dst = RayTraceUtil.getRayTerm(src, shooter.getXRot(), shooter.getYRot(), 17);
                Vec3 dir = dst.subtract(src).normalize();
                shoot(sl, shooter, crossbow, src, dir);
            }
        }
    }

    public void shoot(ServerLevel level, LivingEntity shooter, ItemStack stack, Vec3 src, Vec3 dir) {
        for (int i = 1; i < 17; ++i) {
            Vec3 vec33 = src.add(dir.scale(i));
            level.sendParticles(ParticleTypes.SONIC_BOOM, vec33.x, vec33.y, vec33.z, 1, 0, 0.0D, 0.0D, 0.0D);
        }
        List<LivingEntity> target = new ArrayList<>();
        AABB aabb = new AABB(src, src.add(dir.scale(17)));
        for (var e : level.getEntities(shooter, aabb)) {
            if (e instanceof LivingEntity x) {
                AABB box = x.getBoundingBox().inflate(1);
                for (int i = 0; i <= 17; i++) {
                    if (box.contains(src.add(dir.scale(i)))) {
                        target.add(x);
                        break;
                    }
                }
            }
        }
        for (var e : target) {
            e.hurt(level.damageSources().sonicBoom(shooter), getAttack(EquipmentUtils.getLevel(stack)) * dmgConfig.floatValue());
            double d1 = 0.5D * (1.0D - e.getAttributeValue(Attributes.KNOCKBACK_RESISTANCE));
            double d0 = 2.5D * (1.0D - e.getAttributeValue(Attributes.KNOCKBACK_RESISTANCE));
            e.push(dir.x() * d0, dir.y() * d1, dir.z() * d0);
        }
        shooter.playSound(SoundEvents.WARDEN_SONIC_BOOM, 3.0F, 1.0F);
        stack.hurtAndBreak(1, shooter, e -> e.broadcastBreakEvent(e.getUsedItemHand()));
    }
}
