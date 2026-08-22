package com.xiaoyue.celestial_equipments.content.equipments.melee;

import com.xiaoyue.celestial_equipments.content.items.generic.UpgradeableMelee;
import com.xiaoyue.celestial_equipments.content.library.MeleeType;
import com.xiaoyue.celestial_invoker.invoker.config.ConfigHolderEntry;
import com.xiaoyue.celestial_invoker.invoker.config.value.IntConfigEntry;
import com.xiaoyue.celestial_invoker.invoker.tooltip.SubscribeTooltip;
import com.xiaoyue.celestial_invoker.invoker.tooltip.TooltipEntry;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;

import java.util.List;

public class GlintstoneScythe extends UpgradeableMelee {
    public GlintstoneScythe() {
        super(MeleeType.GENERIC);
    }

    @ConfigHolderEntry(category = "melee")
    public static IntConfigEntry cooldownAcceleratedConfig = IntConfigEntry.define("Glintstone Scythe Cooldown AcceleratedConfig",
            1, 1, 100, "Each attack reduces the cooldown");

    @SubscribeTooltip(id = "glintstone_scythe")
    public static TooltipEntry tooltip = TooltipEntry.define(
            "Glintstone Scythe: Each attack reduces the cooldown of all items by %s tick");

    @Override
    public void addTooltips(ItemStack stack, List<Component> list, int lv) {
        list.add(tooltip.withGray(TooltipEntry.num(cooldownAcceleratedConfig.get())));
    }

    @Override
    protected void attacked(ItemStack stack, LivingEntity target, LivingEntity attacker, int lv) {
        if (attacker instanceof Player player) {
            for (int i = 0; i < cooldownAcceleratedConfig.get() * lv; i++) {
                player.getCooldowns().tick();
            }
        }
    }

    @Override
    public boolean canAttackBlock(BlockState pState, Level pLevel, BlockPos pPos, Player pPlayer) {
        return true;
    }
}
