package com.xiaoyue.celestial_equipments;

import com.mojang.logging.LogUtils;
import com.tterrag.registrate.providers.ProviderType;
import com.tterrag.registrate.util.entry.RegistryEntry;
import com.xiaoyue.celestial_equipments.content.container.CEFMenuSyncPayload;
import com.xiaoyue.celestial_equipments.content.container.CEFMenuSyncResponsePayload;
import com.xiaoyue.celestial_equipments.data.CEModConfig;
import com.xiaoyue.celestial_equipments.data.CERecipeGen;
import com.xiaoyue.celestial_equipments.data.CETagGen;
import com.xiaoyue.celestial_equipments.events.CEAttackListener;
import com.xiaoyue.celestial_equipments.register.*;
import com.xiaoyue.celestial_invoker.content.common.IRegistrateExtra;
import com.xiaoyue.celestial_invoker.content.network.NetworkHandler;
import com.xiaoyue.celestial_invoker.invoker.config.ConfigHolderMap;
import com.xiaoyue.celestial_invoker.invoker.config.ConfigLoader;
import dev.xkmc.l2damagetracker.contents.attack.AttackEventHandler;
import dev.xkmc.l2library.base.L2Registrate;
import dev.xkmc.l2library.serial.config.PacketHandler;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.fml.config.ModConfig.Type;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.network.NetworkDirection;
import org.slf4j.Logger;

import static com.xiaoyue.celestial_equipments.CelestialEquipments.MODID;

@Mod(MODID)
@EventBusSubscriber(modid = MODID, bus = Bus.MOD)
public class CelestialEquipments {

    public static final String MODID = "celestial_equipments";
    public static final Logger LOGGER = LogUtils.getLogger();
    public static final ConfigHolderMap CONFIG = ConfigLoader.mapConfig(MODID);
    public static final L2Registrate REGISTRATE = new L2Registrate(MODID);
    public static final IRegistrateExtra<L2Registrate> EXTRA = new IRegistrateExtra<>(REGISTRATE);
    public static final PacketHandler HANDLER = new PacketHandler(loc("main"), 1,
            e -> e.create(CEFMenuSyncPayload.class, NetworkDirection.PLAY_TO_SERVER));

    public static final RegistryEntry<CreativeModeTab> TAB = EXTRA.buildCreativeTab("tab",
            e -> e.icon(CEItems.BLOOD_BINDING::asStack));

    public CelestialEquipments() {
        CEItems.register();
        CERecipes.register();
        CEMenus.register();
        CEBlocks.register();
        CEEntities.register();
        CEEffects.register();
        AttackEventHandler.register(3420, new CEAttackListener());
        CONFIG.addExtra(CEModConfig::onConfig).initCelestialConfigs(Type.COMMON);
        EXTRA.genSubscribeTooltips();
        REGISTRATE.addDataGenerator(ProviderType.ITEM_TAGS, CETagGen::onItemTagGen);
        REGISTRATE.addDataGenerator(ProviderType.BLOCK_TAGS, CETagGen::onBlockTagGen);
        REGISTRATE.addDataGenerator(ProviderType.RECIPE, CERecipeGen::onRecipeGen);
    }

    @SubscribeEvent
    public static void onCommonStep(FMLCommonSetupEvent event) {
        event.enqueueWork(() -> {
            NetworkHandler.registerMSG(CEFMenuSyncResponsePayload.class, CEFMenuSyncResponsePayload::encode, CEFMenuSyncResponsePayload::decode, CEFMenuSyncResponsePayload::handle);
        });
    }

    @SubscribeEvent
    public static void gatherData(GatherDataEvent event) {
    }

    public static ResourceLocation loc(String id) {
        return new ResourceLocation(MODID, id);
    }
}

