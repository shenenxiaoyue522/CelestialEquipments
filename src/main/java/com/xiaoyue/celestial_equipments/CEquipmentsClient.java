package com.xiaoyue.celestial_equipments;

import com.xiaoyue.celestial_equipments.content.items.generic.GenericBow;
import com.xiaoyue.celestial_equipments.utils.EquipmentUtils;
import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CrossbowItem;
import net.minecraft.world.item.Item;
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
        event.enqueueWork(CEquipmentsClient::registerBowProperties);
    }

    public static void registerBowProperties() {
        for(GenericBow bow : GenericBow.BOWS) {
            ItemProperties.register(bow, new ResourceLocation("pull"), (stack, level, entity, i) ->
                    entity != null && entity.getUseItem() == stack ? bow.getBowPowerForTime(EquipmentUtils.getLevel(stack), (float) (stack.getUseDuration() - entity.getUseItemRemainingTicks())) : 0f);
            ItemProperties.register(bow, new ResourceLocation("pulling"), (stack, level, entity, i) ->
                    entity != null && entity.isUsingItem() && entity.getUseItem() == stack ? 1.0f : 0f);
        }
    }

    public static void registerCrossbowProperties() {
        List<Item> list = List.of();
        for (Item bow : list) {
            ItemProperties.register(bow, new ResourceLocation("pull"), (stack, level, entity, i) -> {
                CompoundTag tag = stack.getTag();
                if (tag != null && tag.contains("Charged")) {
                    return tag.getFloat("Charged");
                }
                return 0.0F;
            });
            ItemProperties.register(bow, new ResourceLocation("pulling"), (stack, level, entity, i)
                    -> CrossbowItem.isCharged(stack) ? 1.0F : 0.0F);
            ItemProperties.register(bow, new ResourceLocation("charged"), (stack, level, entity, i) ->
                    CrossbowItem.isCharged(stack) ? 1.0F : 0.0F);
            ItemProperties.register(bow, new ResourceLocation("firework"), (stack, level, entity, i) ->
                    CrossbowItem.containsChargedProjectile(stack, Items.FIREWORK_ROCKET) ? 1.0F : 0.0F);
        }
    }
}
