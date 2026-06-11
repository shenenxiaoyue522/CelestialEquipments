package com.xiaoyue.celestial_equipments.content.container;

import com.xiaoyue.celestial_invoker.content.generic.shared.NetworkHandler;
import net.minecraft.client.Minecraft;
import net.minecraft.core.NonNullList;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.network.NetworkEvent;
import net.minecraftforge.network.PacketDistributor;

import java.util.function.Supplier;

public class CEFMenuSyncResponsePayload {

    private final int containerId;
    private final NonNullList<ItemStack> items;

    public CEFMenuSyncResponsePayload(int containerId, NonNullList<ItemStack> items) {
        this.containerId = containerId;
        this.items = items;
    }

    public static void encode(CEFMenuSyncResponsePayload msg, FriendlyByteBuf buf) {
        buf.writeInt(msg.containerId);
        buf.writeInt(msg.items.size());
        for (ItemStack stack : msg.items) {
            buf.writeItem(stack);
        }
    }

    public static CEFMenuSyncResponsePayload decode(FriendlyByteBuf buf) {
        int containerId = buf.readInt();
        int size = buf.readInt();
        NonNullList<ItemStack> items = NonNullList.withSize(size, ItemStack.EMPTY);
        for (int i = 0; i < size; i++) {
            items.set(i, buf.readItem());
        }
        return new CEFMenuSyncResponsePayload(containerId, items);
    }

    public void handle(Supplier<NetworkEvent.Context> ctx) {
        ctx.get().enqueueWork(() -> {
            Minecraft mc = Minecraft.getInstance();
            if (mc.player != null && mc.player.containerMenu instanceof CEForgeTableMenu menu && menu.containerId == containerId) {
                for (int i = 0; i < items.size() && i < menu.slots.size(); i++) {
                    menu.slots.get(i).set(items.get(i));
                }
            }
        });
        ctx.get().setPacketHandled(true);
    }

    public void sendTo(ServerPlayer player) {
        NetworkHandler.INSTANCE.send(PacketDistributor.PLAYER.with(() -> player), this);
    }
}
