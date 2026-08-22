package com.xiaoyue.celestial_equipments.data;

import com.xiaoyue.celestial_core.utils.ItemUtils;
import com.xiaoyue.celestial_equipments.register.CEItems;
import com.xiaoyue.celestial_invoker.invoker.config.ConfigHolderMap;
import com.xiaoyue.celestial_invoker.invoker.config.value.BooleanConfigEntry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.fml.config.ModConfig;

import java.util.LinkedHashMap;
import java.util.Map;

public class CEModConfig {
    public static final Map<String, ForgeConfigSpec.BooleanValue> map = new LinkedHashMap<>();

    public static void onConfig(ConfigHolderMap map) {
        for(String item : CEItems.ALL_EQUIPMENTS) {
            map.addConfig("itemToggles", BooleanConfigEntry.define(item, true), ModConfig.Type.COMMON);
        }
    }

    public static boolean enabled(Item item) {
        ResourceLocation res = ItemUtils.getKey(item);
        if (res == null) {
            return true;
        } else {
            ForgeConfigSpec.BooleanValue config = map.get(res.getPath());
            return config == null || config.get();
        }
    }
}
