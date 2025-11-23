package com.xiaoyue.celestial_equipments;

import com.xiaoyue.celestial_equipments.content.items.generic.BowEquipment;
import com.xiaoyue.celestial_equipments.register.CEItems;
import com.xiaoyue.celestial_equipments.utils.EquipmentUtils;
import com.xiaoyue.celestial_invoker.content.generic.item.CelestialCrossbowItem;
import com.xiaoyue.celestial_invoker.content.generic.item.CelestialTridentItem;
import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CrossbowItem;
import net.minecraft.world.item.Items;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

import java.util.List;

import static com.xiaoyue.celestial_equipments.CelestialEquipments.MODID;

@Mod.EventBusSubscriber(modid = MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class CEquipmentsClient {

    @SubscribeEvent
    public static void registerLayer(EntityRenderersEvent.RegisterLayerDefinitions event) {
    }

    @SubscribeEvent
    public static void clientSetup(FMLClientSetupEvent event) {
        event.enqueueWork(() -> {
            CEquipmentsClient.registerBowProperties();
            CEquipmentsClient.registerCrossbowProperties();
            CEquipmentsClient.registerTridentProperties();
        });
    }

    public static void registerTridentProperties() {
        List<CelestialTridentItem> list = List.of(CEItems.OCEAN_TIDE.get());
        for (CelestialTridentItem trident : list) {
            ItemProperties.register(trident, new ResourceLocation("using"), (stack, level, entity, i) ->
                    entity != null && entity.isUsingItem() && entity.getUseItem() == stack ? 1.0f : 0f);
        }
    }

    public static void registerBowProperties() {
        for(BowEquipment bow : BowEquipment.BOWS) {
            ItemProperties.register(bow, new ResourceLocation("pull"), (stack, level, entity, i) ->
                    entity != null && entity.getUseItem() == stack ? bow.getBowPowerForTime(EquipmentUtils.getLevel(stack), (float) (stack.getUseDuration() - entity.getUseItemRemainingTicks())) : 0f);
            ItemProperties.register(bow, new ResourceLocation("pulling"), (stack, level, entity, i) ->
                    entity != null && entity.isUsingItem() && entity.getUseItem() == stack ? 1.0f : 0f);
        }
    }

    public static void registerCrossbowProperties() {
        List<CelestialCrossbowItem> list = List.of(CEItems.SAKURA_BLOOM.get());
        for (CelestialCrossbowItem crossbow : list) {
            ItemProperties.register(crossbow, new ResourceLocation("pull"), (stack, level, entity, i) -> {
                if (entity == null) {
                    return 0.0F;
                } else {
                    return CrossbowItem.isCharged(stack) ? 0.0F : (float) (stack.getUseDuration() - entity.getUseItemRemainingTicks()) / (float) CelestialCrossbowItem.getChargeDuration(stack);
                }
            });
            ItemProperties.register(crossbow, new ResourceLocation("pulling"), (stack, level, entity, i)
                    -> entity != null && entity.isUsingItem() && entity.getUseItem() == stack && !CrossbowItem.isCharged(stack) ? 1.0F : 0.0F);
            ItemProperties.register(crossbow, new ResourceLocation("charged"), (stack, level, entity, i) ->
                    CrossbowItem.isCharged(stack) ? 1.0F : 0.0F);
            ItemProperties.register(crossbow, new ResourceLocation("firework"), (stack, level, entity, i) ->
                    CrossbowItem.isCharged(stack) && CrossbowItem.containsChargedProjectile(stack, Items.FIREWORK_ROCKET) ? 1.0F : 0.0F);
        }
    }
}
