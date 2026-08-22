package com.xiaoyue.celestial_equipments.content.equipments.misc;

import com.xiaoyue.celestial_equipments.content.entities.SakuraBladeEntity;
import com.xiaoyue.celestial_equipments.content.library.ICelestialEquip;
import com.xiaoyue.celestial_equipments.register.CEItems;
import com.xiaoyue.celestial_invoker.invoker.config.ConfigHolderEntry;
import com.xiaoyue.celestial_invoker.invoker.config.value.DoubleConfigEntry;
import com.xiaoyue.celestial_invoker.invoker.tooltip.SubscribeTooltip;
import com.xiaoyue.celestial_invoker.invoker.tooltip.TooltipEntry;
import com.xiaoyue.celestial_invoker.invoker.tooltip.TooltipHolder;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class Senbonzakura extends Item implements ICelestialEquip {
    public Senbonzakura(Properties properties) {
        super(properties.stacksTo(1).durability(621));
    }

    @ConfigHolderEntry(category = "tool")
    public static DoubleConfigEntry dmgConfig = DoubleConfigEntry.defineFromMinUsable("Senbonzakura Damage Factor",
            0.3f, 10000, "Senbonzakura: Damage factor per knife");

    @ConfigHolderEntry(category = "tool")
    public static DoubleConfigEntry healConfig = DoubleConfigEntry.defineFromMinUsable("Senbonzakura Heal Factor",
            0.03f, 100, "Senbonzakura: Heal factor per knife");

    @SubscribeTooltip(id = "senbonzakura")
    public static TooltipHolder tooltips = TooltipHolder.define(
            TooltipEntry.define("Right-click: Fire 5 knives straight ahead"),
            TooltipEntry.define("Each knife hit on a creature recovers %s of missing health"),
            TooltipEntry.define("And deals damage equal to %s of your attack damage"));

    @Override
    public void appendHoverText(ItemStack pStack, @Nullable Level pLevel, List<Component> list, TooltipFlag pIsAdvanced) {
        list.add(tooltips.get(0).withGray());
        list.add(tooltips.get(1).withGray(TooltipEntry.per(healConfig.get())));
        list.add(tooltips.get(2).withGray(TooltipEntry.per(dmgConfig.get())));
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level pLevel, Player player, InteractionHand pUsedHand) {
        ItemStack stack = player.getItemInHand(pUsedHand);
        if (!pLevel.isClientSide()) {
            for (int i = 0; i < 5; i++) {
                SakuraBladeEntity entity = new SakuraBladeEntity(player, pLevel, CEItems.SAKURA_BLADE.asStack());
                entity.setPos(player.getX(), player.getEyeY() - 0.1, player.getZ());
                float spread = (i - (5 - 1) / 2f) * (25f / 5);
                entity.shootFromRotation(player, player.getXRot(), player.getYRot() + spread, 0f, 2f, 0f);
                pLevel.addFreshEntity(entity);
            }
            if (!player.getAbilities().instabuild) {
                stack.hurtAndBreak(1, player, (p) -> p.broadcastBreakEvent(pUsedHand));
            }
            return InteractionResultHolder.success(stack);
        }
        return InteractionResultHolder.fail(stack);
    }
}
