package com.xiaoyue.celestial_equipments.register;

import com.mojang.math.Axis;
import com.tterrag.registrate.util.entry.EntityEntry;
import com.xiaoyue.celestial_equipments.CelestialEquipments;
import com.xiaoyue.celestial_equipments.content.entities.EnderThrowingAxeEntity;
import com.xiaoyue.celestial_equipments.content.entities.SimpleTridentEntity;
import com.xiaoyue.celestial_invoker.content.client.entity.ThrownEntityRender;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.phys.Vec3;

public class CEEntities {

    public static final EntityEntry<SimpleTridentEntity> SIMPLE_TRIDENT;
    public static final EntityEntry<EnderThrowingAxeEntity> ENDER_THROWING_AXE;

    static {
        SIMPLE_TRIDENT = CelestialEquipments.REGISTRATE.<SimpleTridentEntity>entity("simple_trident",
                SimpleTridentEntity::new, MobCategory.MISC).properties((b) -> b
                .sized(0.5f, 0.5f).clientTrackingRange(4)
                .updateInterval(20).setShouldReceiveVelocityUpdates(true))
                .renderer(() -> ctx -> ThrownEntityRender.simple(ctx, new Vec3(0.85f, 0.85f, 0.85f), 0.1f,
                        (p, e) -> p.scale(4f, 2f, 4f)))
                .defaultLang().register();
        ENDER_THROWING_AXE = CelestialEquipments.REGISTRATE.<EnderThrowingAxeEntity>entity("ender_throwing_axe",
                EnderThrowingAxeEntity::new, MobCategory.MISC).properties((b) -> b
                .sized(0.5f, 0.5f).clientTrackingRange(4)
                .updateInterval(20).setShouldReceiveVelocityUpdates(true))
                .renderer(() -> ctx -> ThrownEntityRender.simple(ctx, new Vec3(0.85f, 0.85f, 0.85f), 0.1f,
                        (p, e) -> {
                    p.mulPose(Axis.XP.rotationDegrees(180f));
                    p.mulPose(Axis.ZP.rotationDegrees(270f));
                })).defaultLang().register();
    }

    public static void register() {

    }
}
