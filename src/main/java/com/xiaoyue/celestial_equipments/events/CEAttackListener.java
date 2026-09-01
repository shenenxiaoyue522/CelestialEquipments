package com.xiaoyue.celestial_equipments.events;

import com.xiaoyue.celestial_equipments.content.equipments.armor.ChasingSummer;
import com.xiaoyue.celestial_equipments.content.equipments.armor.MortalShadow;
import com.xiaoyue.celestial_equipments.content.items.curios.CursedVisage;
import com.xiaoyue.celestial_equipments.content.items.curios.GaleGrip;
import com.xiaoyue.celestial_equipments.content.items.generic.GenericArmorItem;
import com.xiaoyue.celestial_equipments.content.library.IAttackConfig;
import com.xiaoyue.celestial_equipments.utils.EquipmentUtils;
import com.xiaoyue.celestial_invoker.invoker.config.ConfigHolderEntry;
import com.xiaoyue.celestial_invoker.invoker.config.value.DoubleConfigEntry;
import com.xiaoyue.celestial_invoker.invoker.config.value.IntConfigEntry;
import dev.xkmc.l2damagetracker.contents.attack.AttackCache;
import dev.xkmc.l2damagetracker.contents.attack.AttackListener;
import dev.xkmc.l2damagetracker.contents.attack.CreateSourceEvent;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;

public class CEAttackListener implements AttackListener {

    @Override
    public void onCreateSource(CreateSourceEvent event) {
        ItemStack stack = event.getAttacker().getMainHandItem();
        if (stack.getItem() instanceof IAttackConfig attack) {
            attack.onCreateSource(stack, event.getAttacker(), event, EquipmentUtils.getLevel(stack));
        }
    }

    @Override
    public void postAttack(AttackCache cache, LivingAttackEvent event, ItemStack weapon) {
        LivingEntity attacker = cache.getAttacker();
        GaleGrip.onAttack(attacker);
    }

    @Override
    public void onHurt(AttackCache cache, ItemStack weapon) {
        LivingHurtEvent event = cache.getLivingHurtEvent();
        assert event != null;
        LivingEntity attacker = cache.getAttacker();
        if (attacker == null) return;
        ItemStack stack = attacker.getMainHandItem();
        if (IAttackConfig.isMelee(event.getSource()) && stack.getItem() instanceof IAttackConfig attack) {
            attack.onMeleeHurt(stack, attacker, cache, EquipmentUtils.getLevel(stack));
        }
        ItemStack useItem = attacker.getUseItem();
        if (IAttackConfig.isProjectile(event.getSource()) && useItem.getItem() instanceof IAttackConfig attack) {
            attack.onProjectileHurt(useItem, attacker, cache, EquipmentUtils.getLevel(useItem));
        }
    }

    @Override
    public void onHurtMaximized(AttackCache cache, ItemStack weapon) {
        LivingHurtEvent event = cache.getLivingHurtEvent();
        assert event != null;
        MortalShadow.onHurt(event, cache.getAttackTarget());
    }

    @ConfigHolderEntry(category = "misc")
    public static DoubleConfigEntry armorExpGetChance = DoubleConfigEntry.defineChance("Armor Exp Get Chance",
            0.5, "The chance of gaining experience when armor takes damage");

    @ConfigHolderEntry(category = "misc")
    public static IntConfigEntry armorExpGet = IntConfigEntry.define("Equipment Max Level", 1, 0, Integer.MAX_VALUE,
            "The value gained when armor gains experience");

    @Override
    public void onDamage(AttackCache cache, ItemStack weapon) {
        LivingEntity entity = cache.getAttackTarget();
        entity.getArmorSlots().forEach((stack) -> {
            if (stack.getItem() instanceof GenericArmorItem armor) {
                if (entity.getRandom().nextDouble() <= armorExpGetChance.get() && armor.isEnabled()) {
                    EquipmentUtils.addExp(stack, armorExpGet.get());
                }
            }
        });
    }

    @Override
    public void onDamageFinalized(AttackCache cache, ItemStack weapon) {
        LivingDamageEvent event = cache.getLivingDamageEvent();
        assert event != null;
        LivingEntity entity = cache.getAttackTarget();
        ChasingSummer.onDamaged(entity, event);
        CursedVisage.onOtherDamaged(entity, event.getSource());
    }
}
