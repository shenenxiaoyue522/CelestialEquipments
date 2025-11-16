package com.xiaoyue.celestial_equipments;

import com.mojang.logging.LogUtils;
import com.tterrag.registrate.providers.ProviderType;
import com.tterrag.registrate.util.entry.RegistryEntry;
import com.xiaoyue.celestial_equipments.data.CEModConfig;
import com.xiaoyue.celestial_equipments.data.CERecipeGen;
import com.xiaoyue.celestial_equipments.data.CETagGen;
import com.xiaoyue.celestial_equipments.events.CEAttackListener;
import com.xiaoyue.celestial_equipments.register.CEBlocks;
import com.xiaoyue.celestial_equipments.register.CEItems;
import com.xiaoyue.celestial_equipments.register.CEMenus;
import com.xiaoyue.celestial_equipments.register.CERecipes;
import com.xiaoyue.celestial_invoker.content.ancillary.CelestialRegistrate;
import com.xiaoyue.celestial_invoker.invoker.config.ConfigHolderMap;
import com.xiaoyue.celestial_invoker.invoker.config.ConfigLoader;
import com.xiaoyue.celestial_invoker.invoker.tooltip.TooltipLoader;
import dev.xkmc.l2damagetracker.contents.attack.AttackEventHandler;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.fml.config.ModConfig.Type;
import org.slf4j.Logger;

import static com.xiaoyue.celestial_equipments.CelestialEquipments.MODID;

@Mod(MODID)
@EventBusSubscriber(modid = MODID, bus = Bus.MOD)
public class CelestialEquipments {

    public static final String MODID = "celestial_equipments";
    public static final Logger LOGGER = LogUtils.getLogger();
    public static final ConfigHolderMap CONFIG = ConfigLoader.mapConfig(MODID);
    public static final CelestialRegistrate REGISTRATE = new CelestialRegistrate(MODID);

    public static final RegistryEntry<CreativeModeTab> TAB = REGISTRATE.buildCreativeTab("tab",
            e -> e.icon(CEItems.BLOOD_BINDING::asStack));

    public CelestialEquipments() {
        CEItems.register();
        CERecipes.register();
        CEMenus.register();
        CEBlocks.register();
        AttackEventHandler.register(3420, new CEAttackListener());
        CONFIG.addExtra(Type.COMMON, CEModConfig::new).initCelestialConfigs(Type.COMMON);
        TooltipLoader.generator(MODID, REGISTRATE);
        REGISTRATE.addDataGenerator(ProviderType.ITEM_TAGS, CETagGen::onItemTagGen);
        REGISTRATE.addDataGenerator(ProviderType.BLOCK_TAGS, CETagGen::onBlockTagGen);
        REGISTRATE.addDataGenerator(ProviderType.RECIPE, CERecipeGen::onRecipeGen);
    }

    @SubscribeEvent
    public static void gatherData(GatherDataEvent event) {
    }

    public static ResourceLocation loc(String id) {
        return new ResourceLocation(MODID, id);
    }
}

