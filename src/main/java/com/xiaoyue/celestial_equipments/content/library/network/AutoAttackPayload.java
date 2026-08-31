package com.xiaoyue.celestial_equipments.content.library.network;

import com.xiaoyue.celestial_invoker.content.common.helper.FindTargetHelper;
import dev.xkmc.l2serial.network.SerialPacketBase;
import dev.xkmc.l2serial.serialization.SerialClass;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraftforge.common.ForgeMod;
import net.minecraftforge.network.NetworkEvent;

@SerialClass
public class AutoAttackPayload extends SerialPacketBase {

    @Override
    public void handle(NetworkEvent.Context ctx) {
        ctx.enqueueWork(() -> {
            ServerPlayer player = ctx.getSender();
            if (player != null) {
                double range = player.getAttributeValue(ForgeMod.ENTITY_REACH.get());
                Entity target = FindTargetHelper.getEntityInCrosshair(player, range, e -> true);
                if (target != null) {
                    player.attack(target);
                }
            }
        });
        ctx.setPacketHandled(true);
    }
}
