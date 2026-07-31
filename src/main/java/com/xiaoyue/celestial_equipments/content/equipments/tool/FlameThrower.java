package com.xiaoyue.celestial_equipments.content.equipments.tool;

import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tiers;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;

import java.util.List;

public class FlameThrower extends SwordItem {
    public FlameThrower() {
        super(Tiers.DIAMOND, 7, -2.4f, new Properties());
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        ItemStack stack = player.getItemInHand(hand);
        player.startUsingItem(hand);
        return InteractionResultHolder.consume(stack);
    }

    @Override
    public int getUseDuration(ItemStack stack) {
        return 72000;
    }

    @Override
    public UseAnim getUseAnimation(ItemStack stack) {
        return UseAnim.BOW;
    }

    @Override
    public void onUseTick(Level pLevel, LivingEntity pLivingEntity, ItemStack pStack, int pRemainingUseDuration) {
        if (pLivingEntity instanceof Player player) {
            Level level = player.level();
            if (level.isClientSide()) {
                spawnFlameParticles(level, player);
            } else {
                dealFireDamage(level, player, pStack);
            }
        }
    }

    private void spawnFlameParticles(Level level, Player player) {
        Vec3 lookVec = player.getLookAngle();
        Vec3 startPos = player.getEyePosition().add(lookVec.scale(1.0));
        for (int i = 0; i < 15; i++) {
            double offsetX = (level.random.nextDouble() - 0.5) * 0.5;
            double offsetY = (level.random.nextDouble() - 0.5) * 0.5;
            double offsetZ = (level.random.nextDouble() - 0.5) * 0.5;
            Vec3 particlePos = startPos.add(lookVec.scale(i * 0.2))
                    .add(offsetX, offsetY + 0.5, offsetZ);
            level.addParticle(ParticleTypes.FLAME, particlePos.x, particlePos.y, particlePos.z, lookVec.x * 0.5 + offsetX * 0.1,
                    lookVec.y * 0.5 + offsetY * 0.1, lookVec.z * 0.5 + offsetZ * 0.1);
        }
    }

    private void dealFireDamage(Level level, Player player, ItemStack stack) {
        Vec3 lookVec = player.getLookAngle();
        Vec3 start = player.getEyePosition();
        AABB aabb = player.getBoundingBox().inflate(5f);
        List<LivingEntity> entities = level.getEntitiesOfClass(LivingEntity.class, aabb);
        for (LivingEntity target : entities) {
            if (target == player) continue;
            Vec3 toTarget = target.getEyePosition().subtract(start).normalize();
            if (lookVec.dot(toTarget) > 0.8) {
                target.setRemainingFireTicks(100);
                target.hurt(target.damageSources().onFire(), 2.0f);
                stack.hurtAndBreak(1, player, (p) -> p.broadcastBreakEvent(player.getUsedItemHand()));
            }
        }
    }
}
