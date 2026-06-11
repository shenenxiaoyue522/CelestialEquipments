package com.xiaoyue.celestial_equipments.register;

import com.tterrag.registrate.util.entry.EntityEntry;
import com.xiaoyue.celestial_equipments.CelestialEquipments;
import com.xiaoyue.celestial_equipments.content.entities.EnderThrowingAxeEntity;
import com.xiaoyue.celestial_equipments.content.entities.SimpleTridentEntity;
import net.minecraft.world.entity.MobCategory;

public class CEEntities {

    public static final EntityEntry<SimpleTridentEntity> SIMPLE_TRIDENT;
    public static final EntityEntry<EnderThrowingAxeEntity> ENDER_THROWING_AXE;

    static {
        SIMPLE_TRIDENT = CelestialEquipments.REGISTRATE.<SimpleTridentEntity>entity("simple_trident",
                SimpleTridentEntity::new, MobCategory.MISC).properties((b) -> b
                .sized(0.5f, 0.5f).clientTrackingRange(4)
                .updateInterval(20).setShouldReceiveVelocityUpdates(true))
                .renderer(() -> SimpleTridentEntity.Render::new)
                .defaultLang().register();
        ENDER_THROWING_AXE = CelestialEquipments.REGISTRATE.<EnderThrowingAxeEntity>entity("ender_throwing_axe",
                EnderThrowingAxeEntity::new, MobCategory.MISC).properties((b) -> b
                .sized(0.5f, 0.5f).clientTrackingRange(4)
                .updateInterval(20).setShouldReceiveVelocityUpdates(true))
                .renderer(() -> EnderThrowingAxeEntity.Render::new).defaultLang().register();
    }

    public static void register() {

    }
}
