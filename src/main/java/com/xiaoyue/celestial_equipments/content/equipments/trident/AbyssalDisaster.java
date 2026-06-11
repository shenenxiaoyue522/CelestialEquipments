package com.xiaoyue.celestial_equipments.content.equipments.trident;

import com.google.common.collect.Multimap;
import com.xiaoyue.celestial_core.data.CCDamageTypes;
import com.xiaoyue.celestial_core.utils.EntityUtils;
import com.xiaoyue.celestial_equipments.content.entities.SimpleTridentEntity;
import com.xiaoyue.celestial_equipments.content.items.generic.UpgradeableTrident;
import com.xiaoyue.celestial_equipments.content.library.SimpleThrowingFactory;
import com.xiaoyue.celestial_equipments.utils.EquipmentUtils;
import com.xiaoyue.celestial_invoker.content.common.entry.AttributeAdder;
import com.xiaoyue.celestial_invoker.invoker.config.ConfigHolderEntry;
import com.xiaoyue.celestial_invoker.invoker.config.value.DoubleConfigEntry;
import com.xiaoyue.celestial_invoker.invoker.tooltip.SubscribeTooltip;
import com.xiaoyue.celestial_invoker.invoker.tooltip.TooltipEntry;
import com.xiaoyue.celestial_invoker.invoker.tooltip.TooltipHolder;
import dev.xkmc.l2library.init.events.GeneralEventHandler;
import net.minecraft.network.chat.Component;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.ItemStack;

import java.util.List;

public class AbyssalDisaster extends UpgradeableTrident implements SimpleThrowingFactory {
    public AbyssalDisaster() {
        super(new Properties().durability(2600));
    }

    @ConfigHolderEntry(category = "trident")
    public static DoubleConfigEntry abyssalDmgConfig = DoubleConfigEntry.defineBigRange("Abyssal Disaster Abyssal Damage Factor",
            1.2, "Abyssal Disaster: Abyssal damage factor");

    @ConfigHolderEntry(category = "trident")
    public static DoubleConfigEntry abyssalDmgGrowthConfig = DoubleConfigEntry.defineBigRange("Abyssal Disaster Abyssal Damage Growth",
            0.1, "Abyssal Disaster: Abyssal damage growth per level");

    @SubscribeTooltip(id = "abyssal_disaster")
    public static TooltipHolder tooltips = TooltipHolder.define(
            TooltipEntry.define("Throws %s attack damage to nearby creatures on hit, Abyss Damage"),
            TooltipEntry.define("Apply %s effect to the primary target"));

    @Override
    public void addTooltips(ItemStack stack, List<Component> list, int lv) {
        list.add(tooltips.get(0).withGray(TooltipEntry.per(getDmgFactorWithLv(lv))));
        list.add(tooltips.get(1).withGray(TooltipEntry.eff(MobEffects.WITHER)));
    }

    public static float getDmgFactorWithLv(int lv) {
        return abyssalDmgConfig.floatValue() + abyssalDmgGrowthConfig.floatValue() * lv;
    }

    @Override
    public void onHitEntity(SimpleTridentEntity trident, Entity target) {
        if (target instanceof LivingEntity livingTarget) {
            EntityUtils.addEct(livingTarget, MobEffects.WITHER, 200, 2);
            for (LivingEntity entity : EntityUtils.getExceptForCentralEntity(livingTarget, 8, 3)) {
                float baseDamage = (float) trident.getBaseDamage();
                if (trident.getOwner() instanceof LivingEntity attacker) {
                    float damage = baseDamage * getDmgFactorWithLv(EquipmentUtils.getLevel(trident.weapon));
                    GeneralEventHandler.schedule(() -> entity.hurt(CCDamageTypes.abyss(attacker), damage));
                } else {
                    GeneralEventHandler.schedule(() -> entity.hurt(CCDamageTypes.abyss(entity.level()), baseDamage));
                }
            }
        }
    }

    @Override
    protected void addAttributes(EquipmentSlot slot, ItemStack stack, Multimap<Attribute, AttributeModifier> map) {
        if (slot == EquipmentSlot.MAINHAND) {
            AttributeAdder builder = AttributeAdder.builder().name("Tool Modifier");
            builder.uuid(BASE_ATTACK_DAMAGE_UUID).value(6.5 + EquipmentUtils.getLevel(stack) * 0.75f).toMap(map);
            builder.attr(Attributes.ATTACK_SPEED).uuid(BASE_ATTACK_SPEED_UUID).value(-2.7).toMap(map);
        }
    }
}
