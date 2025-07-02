package com.xiaoyue.celestial_equipments;

import com.mojang.logging.LogUtils;
import com.xiaoyue.celestial_equipments.event.CEAttackListener;
import com.xiaoyue.celestial_equipments.register.CEItems;
import com.xiaoyue.celestial_invoker.content.CelestialRegistrate;
import dev.xkmc.l2damagetracker.contents.attack.AttackEventHandler;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.fml.common.Mod;
import org.slf4j.Logger;

@Mod(CelestialEquipments.MODID)
public class CelestialEquipments {
    public static final String MODID = "celestial_equipments";
    public static final Logger LOGGER = LogUtils.getLogger();
    public static final CelestialRegistrate REGISTRATE = new CelestialRegistrate(MODID);

    public CelestialEquipments() {
        CEItems.register();
        AttackEventHandler.register(6600, new CEAttackListener());
    }

    public static ResourceLocation loc(String s) {
        return new ResourceLocation(MODID, s);
    }
}
