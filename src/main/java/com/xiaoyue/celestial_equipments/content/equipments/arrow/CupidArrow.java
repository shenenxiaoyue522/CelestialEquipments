package com.xiaoyue.celestial_equipments.content.equipments.arrow;

import com.xiaoyue.celestial_core.utils.IRarityUtils;
import com.xiaoyue.celestial_equipments.content.items.generic.GenericArrowItem;
import com.xiaoyue.celestial_invoker.content.client.helper.SimpleParticleHelper;
import com.xiaoyue.celestial_invoker.content.entities.GenericArrowEntity;
import com.xiaoyue.celestial_invoker.content.generic.builder.ArrowDataBuilder;
import com.xiaoyue.celestial_invoker.invoker.tooltip.SubscribeTooltip;
import com.xiaoyue.celestial_invoker.invoker.tooltip.TooltipEntry;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

import java.util.List;

public class CupidArrow extends GenericArrowItem {
    public CupidArrow(Properties pProperties) {
        super(pProperties.rarity(IRarityUtils.PINK), new ArrowDataBuilder()
                .damage(0).hitEntity(CupidArrow::onHitEntity));
    }

    @SubscribeTooltip(id = "cupid_arrow")
    public static TooltipEntry tooltip = TooltipEntry.define("Hitting an animal causes it to enter breeding mode");

    @Override
    public void addTooltips(ItemStack stack, List<Component> list, int lv) {
        list.add(tooltip.withGray());
    }

    private static void onHitEntity(GenericArrowEntity arrow, Entity target) {
        if (target instanceof Animal animal) {
            animal.setAge(0);
            Player player = null;
            if (arrow.getOwner() instanceof Player p) {
                player = p;
            }
            animal.setInLove(player);
            spawnParticle(animal.level(), animal);
        }
        arrow.discard();
    }

    private static void spawnParticle(Level level, LivingEntity entity) {
        new SimpleParticleHelper(level).particle(ParticleTypes.HEART)
                .position(entity.getEyePosition()).count(1)
                .spread(0, 0).speed(0).batchMode().spawn();
    }
}
