package com.xiaoyue.celestial_equipments.content.equipments.misc;

import com.xiaoyue.celestial_core.utils.EntityUtils;
import com.xiaoyue.celestial_equipments.content.library.ICelestialEquip;
import com.xiaoyue.celestial_invoker.content.client.helper.SimpleParticleHelper;
import com.xiaoyue.celestial_invoker.invoker.config.ConfigHolderEntry;
import com.xiaoyue.celestial_invoker.invoker.config.value.DoubleConfigEntry;
import com.xiaoyue.celestial_invoker.invoker.config.value.IntConfigEntry;
import com.xiaoyue.celestial_invoker.invoker.tooltip.SubscribeTooltip;
import com.xiaoyue.celestial_invoker.invoker.tooltip.TooltipEntry;
import com.xiaoyue.celestial_invoker.invoker.tooltip.TooltipHolder;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class BubblingScepter extends Item implements ICelestialEquip {
    public BubblingScepter(Properties pProperties) {
        super(pProperties.stacksTo(1).durability(222).rarity(Rarity.RARE));
    }

    @ConfigHolderEntry(category = "misc")
    public static IntConfigEntry cooldownConfig = IntConfigEntry.defineFromZero("Bubbling Scepter Cooldown Time",
            5, Integer.MAX_VALUE, "Bubbling Scepter: Cooldown time");

    @ConfigHolderEntry(category = "misc")
    public static DoubleConfigEntry healConfig = DoubleConfigEntry.defineFromMinUsable("Bubbling Scepter Heal Factor",
            0.43, 10000, "Bubbling Scepter: Restores health based on the amount of attack damage dealt");

    @SubscribeTooltip(id = "bubbling_scepter")
    public static TooltipHolder tooltips = TooltipHolder.define(
            TooltipEntry.define("Right-click: Heal yourself and nearby minions and players"),
            TooltipEntry.define("Restores health equal to %s of your attack damage"),
            TooltipEntry.define("And grants the target a %s effect"));

    @Override
    public Component getName(ItemStack pStack) {
        return getItemName(pStack);
    }

    @Override
    public void appendHoverText(ItemStack pStack, @Nullable Level pLevel, List<Component> list, TooltipFlag pIsAdvanced) {
        list.add(tooltips.get(0).withGray());
        list.add(tooltips.get(1).withGray(TooltipEntry.per(healConfig.get())));
        list.add(tooltips.get(2).withGray(TooltipEntry.eff(MobEffects.HEALTH_BOOST)));
        list.add(cooldownTooltip.withGray(TooltipEntry.num(cooldownConfig.get())));
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand pUsedHand) {
        ItemStack stack = pPlayer.getItemInHand(pUsedHand);
        if (noCooldown(pPlayer)) {
            AABB aabb = EntityUtils.getAABB(pPlayer, 6, 2);
            for (LivingEntity entity : pLevel.getEntitiesOfClass(LivingEntity.class, aabb)) {
                if (entity instanceof Player) {
                    double attack = pPlayer.getAttributeValue(Attributes.ATTACK_DAMAGE);
                    entity.heal((float) (attack * healConfig.floatValue()));
                    EntityUtils.addEct(entity, MobEffects.HEALTH_BOOST, 6000, 4);
                    spawnParticle(pLevel, entity);
                }
            }
            addCooldown(pPlayer, cooldownConfig.get() * 20);
            stack.hurtAndBreak(1, pPlayer, e -> e.broadcastBreakEvent(pUsedHand));
            return InteractionResultHolder.success(stack);
        }
        return InteractionResultHolder.fail(stack);
    }

    private static void spawnParticle(Level level, LivingEntity entity) {
        new SimpleParticleHelper(level).particle(ParticleTypes.HEART)
                .position(entity.getEyePosition()).count(1)
                .spread(0, 0).speed(0).batchMode().spawn();
    }
}
