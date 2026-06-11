package com.xiaoyue.celestial_equipments.content.equipments.trident;

import com.google.common.collect.Multimap;
import com.xiaoyue.celestial_core.data.CCDamageTypes;
import com.xiaoyue.celestial_core.register.CCEffects;
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
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.ItemStack;

import java.util.List;

public class PoseidonWrath extends UpgradeableTrident implements SimpleThrowingFactory {
    public PoseidonWrath() {
        super(new Properties().durability(3400));
    }

    @ConfigHolderEntry(category = "trident")
    public static DoubleConfigEntry thunderDmgConfig = DoubleConfigEntry.defineBigRange("Poseidon Wrath Thunder Damage Factor",
            1.2, "Poseidon Wrath: Thunder damage factor");

    @ConfigHolderEntry(category = "trident")
    public static DoubleConfigEntry thunderDmgGrowthConfig = DoubleConfigEntry.defineBigRange("Poseidon Wrath Thunder Damage Growth",
            0.1, "Poseidon Wrath: Thunder damage growth per level");

    @SubscribeTooltip(id = "poseidon_wrath")
    public static TooltipHolder tooltips = TooltipHolder.define(
            TooltipEntry.define("Summons thunder and lightning when thrown on hit"),
            TooltipEntry.define("Deals magic damage equivalent to %s of attack damage to nearby creatures"),
            TooltipEntry.define("Apply the %s effect to the primary target"));

    @Override
    public int getChargeTime(int lv) {
        return 20;
    }

    @Override
    public void addTooltips(ItemStack stack, List<Component> list, int lv) {
        list.add(tooltips.get(0).withGray());
        list.add(tooltips.get(1).withGray(TooltipEntry.per(thunderDmgConfig.get() + thunderDmgGrowthConfig.get() * lv)));
        list.add(tooltips.get(2).withGray(TooltipEntry.eff(CCEffects.SOUL_SHATTER.get())));
    }

    @Override
    protected void addAttributes(EquipmentSlot slot, ItemStack stack, Multimap<Attribute, AttributeModifier> map) {
        if (slot == EquipmentSlot.MAINHAND) {
            AttributeAdder builder = AttributeAdder.builder().name("Tool Modifier");
            builder.uuid(BASE_ATTACK_DAMAGE_UUID).value(7 + EquipmentUtils.getLevel(stack)).toMap(map);
            builder.attr(Attributes.ATTACK_SPEED).uuid(BASE_ATTACK_SPEED_UUID).value(-2.7).toMap(map);
        }
    }

    @Override
    public void onHitEntity(SimpleTridentEntity trident, Entity target) {
        ItemStack weapon = trident.weapon;
        Entity owner = trident.getOwner();
        int lv = EquipmentUtils.getLevel(weapon);
        if (lv > 0 && owner instanceof LivingEntity attacker) {
            if (target instanceof LivingEntity livingTarget) {
                EntityUtils.addEct(livingTarget, CCEffects.SOUL_SHATTER.get(), 200, 1);
                for (int i = 0; i < 2; i++) {
                    EntityUtils.spawnThunder(target.level(), target.getOnPos(), true);
                }
                for (LivingEntity entity : EntityUtils.getExceptForCentralEntity(livingTarget, 5, 3)) {
                    double damage = trident.getBaseDamage() * (thunderDmgConfig.floatValue() + thunderDmgGrowthConfig.get() * lv);
                    entity.hurt(CCDamageTypes.magic(attacker), (float) damage);
                }
            }
        }
    }
}
