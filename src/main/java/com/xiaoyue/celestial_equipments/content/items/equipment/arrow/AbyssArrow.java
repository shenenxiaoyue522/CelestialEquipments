package com.xiaoyue.celestial_equipments.content.items.equipment.arrow;

import com.xiaoyue.celestial_core.data.CCDamageTypes;
import com.xiaoyue.celestial_core.utils.IRarityUtils;
import com.xiaoyue.celestial_equipments.content.items.generic.GenericArrow;
import com.xiaoyue.celestial_invoker.content.entities.GenericArrowEntity;
import com.xiaoyue.celestial_invoker.content.generic.builder.ArrowDataBuilder;
import com.xiaoyue.celestial_invoker.invoker.config.ConfigHolderEntry;
import com.xiaoyue.celestial_invoker.invoker.config.value.DoubleConfigEntry;
import com.xiaoyue.celestial_invoker.invoker.tooltip.SubscribeTooltip;
import com.xiaoyue.celestial_invoker.invoker.tooltip.TooltipEntry;
import dev.xkmc.l2library.init.events.GeneralEventHandler;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

import java.util.List;

public class AbyssArrow extends GenericArrow {
    public AbyssArrow() {
        super(new Item.Properties().rarity(IRarityUtils.DARK_AQUA), new ArrowDataBuilder()
                .damage(4.0).hitEntity(AbyssArrow::onHitEntity));
    }

    @ConfigHolderEntry(category = "arrow")
    public static DoubleConfigEntry damageMultiplier = DoubleConfigEntry.defineFromMinUsable("Abyss Arrow Damage Multiplier",
            0.5, 100, "Abyss Arrow: Damage multiplier");

    @SubscribeTooltip(id = "abyss_arrow")
    public static TooltipEntry tooltip = TooltipEntry.define(
            "Inflicts additional %s Abyss damage to the target on impact", TooltipEntry.per(damageMultiplier.get()));

    public void addEffectTooltips(ItemStack stack, List<Component> list) {
        list.add(tooltip.withGray());
    }

    private static void onHitEntity(GenericArrowEntity arrow, Entity target) {
        Entity entity = arrow.getOwner();
        if (entity instanceof LivingEntity owner) {
            GeneralEventHandler.schedule(() -> target.hurt(CCDamageTypes.abyss(owner), (float) (arrow.getBaseDamage() * damageMultiplier.get())));
        }
    }
}
