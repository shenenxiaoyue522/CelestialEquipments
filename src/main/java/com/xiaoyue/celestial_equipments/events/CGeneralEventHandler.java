package com.xiaoyue.celestial_equipments.events;

import com.xiaoyue.celestial_core.utils.ItemUtils;
import com.xiaoyue.celestial_equipments.content.items.ExpBottleItem;
import com.xiaoyue.celestial_equipments.content.items.generic.GenericArmor;
import com.xiaoyue.celestial_equipments.content.items.generic.IGenericDigger;
import com.xiaoyue.celestial_equipments.content.library.AttackConfig;
import com.xiaoyue.celestial_equipments.content.library.ICEquipment;
import com.xiaoyue.celestial_equipments.register.CEItems;
import com.xiaoyue.celestial_equipments.utils.EquipmentUtils;
import com.xiaoyue.celestial_invoker.event.CIGeneralEventHandler;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.AnvilUpdateEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
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
        if (stack.getItem() instanceof ICEquipment gear) {
            if (gear.isUpgradeable()) {
                if (meta.is(CEItems.CREATIVE_UP_STONE.get())) {
                    EquipmentUtils.upGear(stack);
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
            if (weapon.getItem() instanceof ICEquipment item) {
                if (item.isEnabled()) {
                    EquipmentUtils.addExp(weapon, (int) (entity.getMaxHealth() / 20.0F));
                }
            }
            if (AttackConfig.isMelee(event.getSource())) {
                if (weapon.getItem() instanceof AttackConfig attack) {
                    attack.onMeleeKill(weapon, attacker, event, EquipmentUtils.getLevel(weapon));
                }
            }
        }
        CIGeneralEventHandler.postArmorMethod(entity, (stack, armor) -> {
            if (armor instanceof GenericArmor genericArmor) {
                genericArmor.onDeath(stack, entity, event, EquipmentUtils.getLevel(stack));
            }
        });
    }

    @SubscribeEvent
    public static void getBreakSpeed(PlayerEvent.BreakSpeed event) {
        Player player = event.getEntity();
        ItemStack stack = player.getMainHandItem();
        if (stack.getItem() instanceof IGenericDigger digger) {
            digger.getBreakSpeed(stack, player, event, EquipmentUtils.getLevel(stack));
        }
    }
}
