package com.xiaoyue.celestial_equipments.content.items.equipment.arrow;

import com.xiaoyue.celestial_core.utils.IRarityUtils;
import com.xiaoyue.celestial_equipments.content.items.generic.GenericArrow;
import com.xiaoyue.celestial_invoker.content.entities.GenericArrowEntity;
import com.xiaoyue.celestial_invoker.content.generic.builder.ArrowDataBuilder;
import com.xiaoyue.celestial_invoker.invoker.tooltip.SubscribeTooltip;
import com.xiaoyue.celestial_invoker.invoker.tooltip.TooltipEntry;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraftforge.event.ForgeEventFactory;
import net.minecraftforge.event.entity.EntityTeleportEvent;

import java.util.List;

public class EnderArrow extends GenericArrow {
    public EnderArrow() {
        super(new Item.Properties().rarity(IRarityUtils.DARK_GREEN), new ArrowDataBuilder()
                .hitEntity(EnderArrow::onHitEntity).hitBlock(EnderArrow::onHitBlock));
    }

    @SubscribeTooltip(id = "ender_arrow")
    public static TooltipEntry tooltip = TooltipEntry.define(
            "Teleports a random target when it hits, and teleports to a block when it hits a block");

    public void addEquipmentTooltips(ItemStack stack, List<Component> list) {
        list.add(tooltip.withGray());
    }

    private static void onHitEntity(GenericArrowEntity arrow, Entity target) {
        randomTeleport(target.level(), target);
    }

    private static void onHitBlock(GenericArrowEntity arrow, BlockHitResult result) {
        Entity entity = arrow.getOwner();
        if (entity instanceof LivingEntity owner) {
            BlockPos pos = result.getBlockPos();
            owner.teleportTo(pos.getX(), pos.getY() + 1, pos.getZ());
        }
    }

    public static void randomTeleport(Level level, Entity entity) {
        if (entity instanceof Player player) {
            if (!level.isClientSide()) {
                double x = player.getX();
                double y = player.getY();
                double z = player.getZ();

                for(int i = 0; i < 16; ++i) {
                    double newX = x + (player.getRandom().nextDouble() - (double)0.5F) * (double)16.0F;
                    double newY = Mth.clamp(y + (double)(player.getRandom().nextInt(16) - 8),
                            level.getMinBuildHeight(), level.getMinBuildHeight() + ((ServerLevel)level).getLogicalHeight() - 1);
                    double newZ = z + (player.getRandom().nextDouble() - (double)0.5F) * (double)16.0F;
                    if (player.isPassenger()) {
                        player.stopRiding();
                    }
                    EntityTeleportEvent.ChorusFruit event2 = ForgeEventFactory.onChorusFruitTeleport(player, newX, newY, newZ);
                    if (player.randomTeleport(event2.getTargetX(), event2.getTargetY(), event2.getTargetZ(), true)) {
                        SoundEvent soundevent = SoundEvents.CHORUS_FRUIT_TELEPORT;
                        player.playSound(soundevent, 1.0F, 1.0F);
                        break;
                    }
                }
            }

        }
    }
}
