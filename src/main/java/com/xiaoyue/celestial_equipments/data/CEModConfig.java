package com.xiaoyue.celestial_equipments.data;

import com.xiaoyue.celestial_core.utils.ItemUtils;
import com.xiaoyue.celestial_equipments.register.CEItems;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraftforge.common.ForgeConfigSpec;

import java.util.LinkedHashMap;
import java.util.Map;

public class CEModConfig {
    public static final Map<String, ForgeConfigSpec.BooleanValue> map = new LinkedHashMap<>();

    public CEModConfig(ForgeConfigSpec.Builder builder) {
        builder.push("itemToggles");

        for(String item : CEItems.ALL_EQUIPMENTS) {
            map.put(item, builder.define(item, true));
        }

        builder.pop();
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
