package com.xiaoyue.celestial_equipments.events;

import com.xiaoyue.celestial_core.utils.ItemUtils;
import com.xiaoyue.celestial_equipments.content.items.ExpBottleItem;
import com.xiaoyue.celestial_equipments.content.items.RepairKitItem;
import com.xiaoyue.celestial_equipments.content.items.generic.GenericDiggerItem;
import com.xiaoyue.celestial_equipments.content.library.IAttackConfig;
import com.xiaoyue.celestial_equipments.content.library.ICelestialEquip;
import com.xiaoyue.celestial_equipments.register.CEEffects;
import com.xiaoyue.celestial_equipments.register.CEItems;
import com.xiaoyue.celestial_equipments.utils.EquipmentUtils;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.AnvilUpdateEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.living.LivingHealEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;

import static com.xiaoyue.celestial_equipments.CelestialEquipments.MODID;

@EventBusSubscriber(modid = MODID, bus = Bus.FORGE)
public class CGeneralEventHandler {

    @SubscribeEvent
    public static void onRecipe(AnvilUpdateEvent event) {
        ItemStack stack = event.getLeft().copy();
        ItemStack meta = event.getRight();
        if (meta.is(CEItems.REPAIR_KIT.get()) && stack.isDamaged()) {
            ItemUtils.repairStack(stack, RepairKitItem.repairConfig.get());
            ItemUtils.defaultAnvilOutput(event, stack, 15);
        }
        if (stack.getItem() instanceof ICelestialEquip gear) {
            if (gear.isUpgradeable()) {
                if (meta.is(CEItems.CREATIVE_UP_STONE.get())) {
                    EquipmentUtils.upLevel(stack);
                    ItemUtils.defaultAnvilOutput(event, stack, 1);
                }
                if (meta.getItem() instanceof ExpBottleItem bottle) {
                    int experience = bottle.getExperience();
                    EquipmentUtils.addExp(stack, experience);
                    ItemUtils.defaultAnvilOutput(event, stack, 5);
                }
            }
        }
    }

    @SubscribeEvent
    public static void onKill(LivingDeathEvent event) {
        LivingEntity entity = event.getEntity();
        DamageSource source = event.getSource();
        Entity sourceEntity = source.getEntity();
        if (sourceEntity instanceof LivingEntity attacker) {
            ItemStack weapon = attacker.getMainHandItem();
            if (weapon.getItem() instanceof ICelestialEquip item) {
                if (item.isEnabled()) {
                    EquipmentUtils.addExp(weapon, (int) (entity.getMaxHealth() / 20.0F));
                }
            }
            if (IAttackConfig.isMelee(event.getSource())) {
                if (weapon.getItem() instanceof IAttackConfig attack) {
                    attack.onMeleeKill(weapon, attacker, event, EquipmentUtils.getLevel(weapon));
                }
            }
        }
    }

    @SubscribeEvent(priority = EventPriority.HIGH)
    public static void getBreakSpeed(PlayerEvent.BreakSpeed event) {
        Player player = event.getEntity();
        ItemStack stack = player.getMainHandItem();
        if (stack.getItem() instanceof GenericDiggerItem digger) {
            digger.getBreakSpeed(stack, player, event, EquipmentUtils.getLevel(stack));
        }
    }

    @SubscribeEvent(priority = EventPriority.LOWEST)
    public static void onHeal(LivingHealEvent event) {
        LivingEntity entity = event.getEntity();
        if (entity.hasEffect(CEEffects.MORTAL_WOUND.get())) {
            event.setCanceled(true);
        }
    }
}
