package com.xiaoyue.celestial_equipments.content.library.network;

import com.xiaoyue.celestial_equipments.content.items.curios.LightOfDawn;
import dev.xkmc.l2serial.network.SerialPacketBase;
import dev.xkmc.l2serial.serialization.SerialClass;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkEvent;

@SerialClass
public class ClickEmptyPayload extends SerialPacketBase {

    @SerialClass.SerialField
    public boolean isRight;

    @Deprecated
    public ClickEmptyPayload() {
    }

    public ClickEmptyPayload(boolean isRight) {
        this.isRight = isRight;
    }

    @Override
    public void handle(NetworkEvent.Context ctx) {
        ServerPlayer player = ctx.getSender();
        ctx.enqueueWork(() -> {
            if (player != null) {
                LightOfDawn.onLeftClick(player);
            }
        });
        ctx.setPacketHandled(true);
    }
}
