package com.xiaoyue.celestial_equipments.content.container;

import com.xiaoyue.celestial_invoker.content.generic.shared.NetworkHandler;
import net.minecraft.core.NonNullList;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class CEFMenuSyncPayload {

    public void encode(FriendlyByteBuf buf) {
    }

    public static CEFMenuSyncPayload decode(FriendlyByteBuf buf) {
        return new CEFMenuSyncPayload();
    }

    public void handle(Supplier<NetworkEvent.Context> ctx) {
        ctx.get().enqueueWork(() -> {
            ServerPlayer player = ctx.get().getSender();
            if (player != null && player.containerMenu instanceof CEForgeTableMenu menu) {
                NonNullList<ItemStack> items = NonNullList.create();
                for (int i = 0; i < menu.slots.size(); i++) {
                    items.add(menu.getSlot(i).getItem());
                }
                new CEFMenuSyncResponsePayload(menu.containerId, items).sendTo(player);
            }
        });
        ctx.get().setPacketHandled(true);
    }

    public void toServer() {
        NetworkHandler.INSTANCE.sendToServer(this);
    }
}
