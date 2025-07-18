package com.xiaoyue.celestial_equipments;

import com.mojang.logging.LogUtils;
import com.tterrag.registrate.util.entry.RegistryEntry;
import com.xiaoyue.celestial_equipments.data.CEModConfig;
import com.xiaoyue.celestial_equipments.event.CEAttackListener;
import com.xiaoyue.celestial_equipments.register.CEItems;
import com.xiaoyue.celestial_invoker.content.binding.CelestialRegistrate;
import com.xiaoyue.celestial_invoker.content.config.ConfigLoader;
import dev.xkmc.l2damagetracker.contents.attack.AttackEventHandler;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import org.slf4j.Logger;

@Mod(CelestialEquipments.MODID)
@Mod.EventBusSubscriber(modid = CelestialEquipments.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class CelestialEquipments {

    public static final String MODID = "celestial_equipments";
    public static final Logger LOGGER = LogUtils.getLogger();
    public static final CelestialRegistrate REGISTRATE = new CelestialRegistrate(MODID);

    public static final RegistryEntry<CreativeModeTab> TAB = REGISTRATE.buildCreativeTab("tab",
            e -> e.icon(CEItems.UNDEAD_SWORD::asStack));

    public CelestialEquipments() {
        CEItems.register();
        AttackEventHandler.register(6600, new CEAttackListener());
        ConfigLoader.mapConfig(MODID).initConfigsInPath(ModConfig.Type.COMMON, CEModConfig.COMMON_PATH);
        REGISTRATE.addModTooltipGen();
    }

    @SubscribeEvent
    public static void gatherData(GatherDataEvent event) {
    }

    public static ResourceLocation loc(String s) {
        return new ResourceLocation(MODID, s);
    }
}
