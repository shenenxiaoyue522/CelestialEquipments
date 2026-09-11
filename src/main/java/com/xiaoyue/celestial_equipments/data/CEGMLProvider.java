package com.xiaoyue.celestial_equipments.data;

import com.xiaoyue.celestial_core.content.loot.AddItemModifier;
import com.xiaoyue.celestial_core.content.loot.DoubleConfigValue;
import com.xiaoyue.celestial_core.content.loot.EntityHealthCondition;
import com.xiaoyue.celestial_core.content.loot.IntConfigValue;
import com.xiaoyue.celestial_equipments.CelestialEquipments;
import com.xiaoyue.celestial_equipments.content.items.ArtifactDesign;
import com.xiaoyue.celestial_equipments.register.CEItems;
import com.xiaoyue.celestial_invoker.invoker.config.ConfigHolderEntry;
import com.xiaoyue.celestial_invoker.invoker.config.value.DoubleConfigEntry;
import dev.xkmc.l2library.util.data.LootTableTemplate;
import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.GlobalLootModifierProvider;

import static com.xiaoyue.celestial_equipments.CelestialEquipments.CONFIG;
import static com.xiaoyue.celestial_equipments.CelestialEquipments.MODID;

public class CEGMLProvider extends GlobalLootModifierProvider {
    public CEGMLProvider(PackOutput output) {
        super(output, MODID);
    }

    @Override
    protected void start() {
        add("drops/artifact_design", new AddItemModifier(CEItems.ABYSSAL_DISASTER.get(),
                DoubleConfigValue.of(CONFIG.configPath.path(), ArtifactDesign.artifactDesignChanceConfig.entry),
                new EntityHealthCondition(IntConfigValue.of(CONFIG.configPath.path(), ArtifactDesign.artifactDesignMinHealthConfig.entry)),
                LootTableTemplate.byPlayer().build()));
    }
}
