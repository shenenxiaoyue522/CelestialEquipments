package com.xiaoyue.celestial_equipments.events;

import com.xiaoyue.celestial_core.utils.ItemUtils;
import com.xiaoyue.celestial_equipments.content.equipments.digger.LifeHoe;
import com.xiaoyue.celestial_equipments.content.equipments.melee.AvariceBlade;
import com.xiaoyue.celestial_equipments.register.CEItems;
import com.xiaoyue.celestial_equipments.utils.EquipmentUtils;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.entity.living.LivingExperienceDropEvent;
import net.minecraftforge.event.entity.living.LivingHealEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;

import static dev.xkmc.l2damagetracker.init.L2DamageTracker.MODID;

@EventBusSubscriber(modid = MODID, bus = Bus.FORGE)
public class EquipmentEventHandler {

    @SubscribeEvent
    public static void onExpDrop(LivingExperienceDropEvent event) {
        Player player = event.getAttackingPlayer();
        if (player != null) {
            ItemStack stack = player.getMainHandItem();
            int lv = EquipmentUtils.getLevel(stack);
            if (stack.is(CEItems.AVARICE_BLADE.get())) {
                event.setDroppedExperience((int) (event.getDroppedExperience() * (1f + AvariceBlade.expBonusConfig.get() * (float)lv)));
            }
        }
    }

    @SubscribeEvent
    public static void onHeal(LivingHealEvent event) {
        LivingEntity entity = event.getEntity();
        if (entity instanceof Player player) {
            for (ItemStack stack : player.getInventory().items) {
                if (stack.is(CEItems.LIFE_HOE.get())) {
                    ItemUtils.repairStack(stack, LifeHoe.durabilityRecovery.get());
                }
            }
        }
    }
}
