package com.xiaoyue.celestial_equipments.content.library;

import com.xiaoyue.celestial_equipments.content.entities.SimpleTridentEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;

public interface SimpleThrowingFactory {

    default void onHitEntity(SimpleTridentEntity trident, Entity target) {

    }

    default void onHitBlock(SimpleTridentEntity trident, BlockPos pos) {

    }
}
