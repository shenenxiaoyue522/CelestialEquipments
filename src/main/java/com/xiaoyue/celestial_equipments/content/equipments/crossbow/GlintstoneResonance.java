package com.xiaoyue.celestial_equipments.content.equipments.crossbow;

import com.xiaoyue.celestial_core.utils.EntityUtils;
import com.xiaoyue.celestial_equipments.content.items.generic.UpgradeableCrossbow;
import com.xiaoyue.celestial_invoker.invoker.config.ConfigHolderEntry;
import com.xiaoyue.celestial_invoker.invoker.config.value.IntConfigEntry;
import com.xiaoyue.celestial_invoker.invoker.tooltip.SubscribeTooltip;
import com.xiaoyue.celestial_invoker.invoker.tooltip.TooltipEntry;
import com.xiaoyue.celestial_invoker.invoker.tooltip.TooltipHolder;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class GlintstoneResonance extends UpgradeableCrossbow {
    public GlintstoneResonance() {
        super(new Properties().durability(2800));
    }

    @Override
    public float getAttack(int lv) {
        return 1 + 0.8f * lv;
    }

    @ConfigHolderEntry(category = "crossbow")
    public static IntConfigEntry extraPenetrationConfig = IntConfigEntry.defineFromZero("Glintstone Resonance Extra Penetration",
            4, 99, "Glintstone Resonance: Extra penetration level");

    @SubscribeTooltip(id = "glintstone_resonance")
    public static TooltipHolder tooltips = TooltipHolder.define(
            TooltipEntry.define("Nearby creatures are highlighted when charged"),
            TooltipEntry.define("Arrows fired have an additional %s Penetration level"));

    @Override
    public void addEquipmentTooltips(ItemStack stack, List<Component> list, int lv) {
        list.add(tooltips.get(0).withGray());
        list.add(tooltips.get(1).withGray(TooltipEntry.num(extraPenetrationConfig.get())));
    }

    @Override
    public void onUseTick(Level pLevel, LivingEntity pLivingEntity, ItemStack crossbow, int pCount) {
        float f = (float) (crossbow.getUseDuration() - pCount) / (float) getChargeDuration(crossbow);
        if (f >= 0.5f) {
            List<LivingEntity> entities = EntityUtils.getExceptForCentralEntity(pLivingEntity, 12, 6);
            entities.forEach(e -> EntityUtils.addEct(e, MobEffects.GLOWING, 600));
        }
    }

    @Override
    protected void onConfigShoot(LivingEntity shooter, InteractionHand hand, ItemStack crossbow, ItemStack ammo, Projectile projectile, @Nullable AbstractArrow arrow, int lv) {
        if (arrow != null) {
            arrow.setPierceLevel((byte) (arrow.getPierceLevel() + extraPenetrationConfig.get()));
        }
    }
}
