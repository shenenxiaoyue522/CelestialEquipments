package com.xiaoyue.celestial_equipments.content.library.network;

import com.xiaoyue.celestial_equipments.CelestialEquipments;
import com.xiaoyue.celestial_equipments.content.container.CEForgeTableMenu;
import dev.xkmc.l2serial.network.SerialPacketBase;
import dev.xkmc.l2serial.serialization.SerialClass;
import net.minecraft.core.NonNullList;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.network.NetworkEvent;

@SerialClass
public class CEFMenuSyncPayload extends SerialPacketBase {

    @Override
    public void handle(NetworkEvent.Context ctx) {
        ctx.enqueueWork(() -> {
            ServerPlayer player = ctx.getSender();
            if (player != null && player.containerMenu instanceof CEForgeTableMenu menu) {
                NonNullList<ItemStack> items = NonNullList.create();
                for (int i = 0; i < menu.slots.size(); i++) {
                    items.add(menu.getSlot(i).getItem());
                }
                new CEFMenuSyncResponsePayload(menu.containerId, items).sendTo(player);
            }
        });
        ctx.setPacketHandled(true);
    }

    public void toServer() {
        CelestialEquipments.HANDLER.toServer(this);
    }
}
