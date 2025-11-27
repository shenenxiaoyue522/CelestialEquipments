package com.xiaoyue.celestial_equipments.events;

import com.xiaoyue.celestial_equipments.content.library.ICEquipment;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RenderTooltipEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;

import static com.xiaoyue.celestial_equipments.CelestialEquipments.MODID;

@EventBusSubscriber(modid = MODID, value = Dist.CLIENT)
public class ClientEvents {

    @SubscribeEvent
    public static void renderTooltip(RenderTooltipEvent.Color event) {
        ItemStack itemStack = event.getItemStack();
        if (itemStack.getItem() instanceof ICEquipment) {
            event.setBorderStart(-7876870);
            event.setBorderEnd(-7876870);
        }

    }
}
