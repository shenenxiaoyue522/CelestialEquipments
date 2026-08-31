package com.xiaoyue.celestial_equipments.events;

import com.xiaoyue.celestial_core.utils.EntityUtils;
import com.xiaoyue.celestial_equipments.CelestialEquipments;
import com.xiaoyue.celestial_equipments.content.library.ICelestialEquip;
import com.xiaoyue.celestial_equipments.content.library.network.AutoAttackPayload;
import com.xiaoyue.celestial_equipments.register.CEItems;
import com.xiaoyue.celestial_equipments.utils.EquipmentUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.material.FogType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RenderTooltipEvent;
import net.minecraftforge.client.event.ViewportEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;

import static com.xiaoyue.celestial_equipments.CelestialEquipments.MODID;

@EventBusSubscriber(modid = MODID, value = Dist.CLIENT)
public class ClientEvents {

    @SubscribeEvent
    public static void renderTooltip(RenderTooltipEvent.Color event) {
        ItemStack itemStack = event.getItemStack();
        if (itemStack.getItem() instanceof ICelestialEquip) {
            event.setBorderStart(0xfff3f9ff);
            event.setBorderEnd(0xffa6c6f3);
        }
    }

    @SubscribeEvent
    public static void renderFog(ViewportEvent.RenderFog event) {
        LocalPlayer player = Minecraft.getInstance().player;
        if (player != null && event.getType().equals(FogType.WATER)) {
            if (CEItems.DEEP_GUARDIAN.isFullSet(player)) {
                event.setCanceled(true);
                event.setNearPlaneDistance(-8f);
                event.setFarPlaneDistance(200f);
            }
        }
    }

    @SubscribeEvent
    public static void onClientTick(TickEvent.ClientTickEvent event) {
        Minecraft mc = Minecraft.getInstance();
        if (mc.player == null) return;
        if (mc.options.keyAttack.isDown() && EntityUtils.isFullCharged(mc.player) && EquipmentUtils.hasCurio(mc.player, CEItems.TALOS_BRACER.get())) {
            CelestialEquipments.HANDLER.toServer(new AutoAttackPayload());
            mc.player.swing(InteractionHand.MAIN_HAND);
            mc.player.resetAttackStrengthTicker();
        }
    }
}
