package com.xiaoyue.celestial_equipments.content.items;

import com.xiaoyue.celestial_core.content.generic.CCTooltipItem;
import com.xiaoyue.celestial_invoker.invoker.config.ConfigHolderEntry;
import com.xiaoyue.celestial_invoker.invoker.config.value.DoubleConfigEntry;
import com.xiaoyue.celestial_invoker.invoker.config.value.IntConfigEntry;
import com.xiaoyue.celestial_invoker.invoker.tooltip.SubscribeTooltip;
import com.xiaoyue.celestial_invoker.invoker.tooltip.TooltipEntry;

public class ArtifactDesign extends CCTooltipItem {
    public ArtifactDesign(Properties pProperties) {
        super(pProperties, true, () -> tooltip.withGray(TooltipEntry.per(artifactDesignChanceConfig.get()),
                TooltipEntry.num(artifactDesignMinHealthConfig.get())));
    }

    @ConfigHolderEntry(category = "material")
    public static DoubleConfigEntry artifactDesignChanceConfig = DoubleConfigEntry.defineChance("Artifact Design Chance", 0.35);

    @ConfigHolderEntry(category = "material")
    public static IntConfigEntry artifactDesignMinHealthConfig = IntConfigEntry.defineFromZero("Artifact Design Min Health",
            100, 1000000);

    @SubscribeTooltip(id = "artifact_design")
    public static TooltipEntry tooltip = TooltipEntry.define("%s chance to drop when killing a creature with max health above %s");
}
