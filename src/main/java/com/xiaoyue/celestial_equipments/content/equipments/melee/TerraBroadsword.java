package com.xiaoyue.celestial_equipments.content.equipments.melee;

import com.xiaoyue.celestial_core.utils.EntityUtils;
import com.xiaoyue.celestial_equipments.CelestialEquipments;
import com.xiaoyue.celestial_equipments.content.items.generic.UpgradeableMelee;
import com.xiaoyue.celestial_equipments.content.library.MeleeType;
import com.xiaoyue.celestial_equipments.utils.EquipmentUtils;
import com.xiaoyue.celestial_invoker.content.entities.AirBladeEntity;
import com.xiaoyue.celestial_invoker.content.generic.item.api.IAirBladeUser;
import com.xiaoyue.celestial_invoker.content.generic.item.api.IClickInteraction;
import com.xiaoyue.celestial_invoker.invoker.config.ConfigHolderEntry;
import com.xiaoyue.celestial_invoker.invoker.config.value.IntConfigEntry;
import com.xiaoyue.celestial_invoker.invoker.tooltip.SubscribeTooltip;
import com.xiaoyue.celestial_invoker.invoker.tooltip.TooltipEntry;
import com.xiaoyue.celestial_invoker.invoker.tooltip.TooltipHolder;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;

import java.util.List;

public class TerraBroadsword extends UpgradeableMelee implements IAirBladeUser, IClickInteraction {
    public TerraBroadsword() {
        super(MeleeType.BROAD);
    }

    @ConfigHolderEntry(category = "melee")
    public static IntConfigEntry cooldownConfig = IntConfigEntry.define("Terra Broadsword Cooldown Time",
            100, 1, 1000000, "Terra Broadsword: Cooldown time");

    @SubscribeTooltip(id = "terra_broadsword")
    public static TooltipHolder tooltips = TooltipHolder.define(
            TooltipEntry.define("Fires an Earth Blade forward while swinging"),
            TooltipEntry.define("Sprint forward while crouching and right-clicking"));

    @Override
    public float getAttack(int lv) {
        return 0.3f * lv;
    }

    @Override
    public void addEquipmentTooltips(ItemStack stack, List<Component> list, int lv) {
        for (TooltipEntry tooltip : tooltips) {
            list.add(tooltip.withGray());
        }
        list.add(cooldownTooltip.withGray(TooltipEntry.num(cooldownConfig.get() / 20)));
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand pUsedHand) {
        ItemStack stack = pPlayer.getItemInHand(pUsedHand);
        if (this.noCooldown(pPlayer) && pPlayer.isCrouching() && EquipmentUtils.getLevel(stack) > 0) {
            Vec3 end = pPlayer.getLookAngle().scale(5f);
            pPlayer.setDeltaMovement(end.x, pPlayer.getDeltaMovement().y(), end.z);
            this.addCooldown(pPlayer, cooldownConfig.get());
            return InteractionResultHolder.success(stack);
        }
        return InteractionResultHolder.fail(stack);
    }

    @Override
    public ResourceLocation getTexture(AirBladeEntity airBladeEntity) {
        return CelestialEquipments.loc("textures/entity/terra_air_blade.png");
    }

    private void shootAirBlade(Level level, Player player, ItemStack stack, int lv) {
        if (lv > 0 && EntityUtils.isFullCharged(player)) {
            player.resetAttackStrengthTicker();
            if (!level.isClientSide()) {
                AirBladeEntity blade = new AirBladeEntity(level);
                float damage = (float) player.getAttributeValue(Attributes.ATTACK_DAMAGE);
                float zr = (float) (player.getRandom().nextDouble() * 360f);
                blade.setData(player, damage, 100, zr, stack);
                blade.shootFromRotation(player, player.getXRot(), player.getYRot(), 0, 1f, 0);
                level.addFreshEntity(blade);
            }
        }
    }

    @Override
    public void onLeftClickEmpty(ItemStack stack, PlayerInteractEvent.LeftClickEmpty event, Player player) {
        shootAirBlade(event.getLevel(), player, stack, EquipmentUtils.getLevel(stack));
    }

    @Override
    public void onLeftClickBlock(ItemStack stack, PlayerInteractEvent.LeftClickBlock event, Player player) {
        shootAirBlade(event.getLevel(), player, stack, EquipmentUtils.getLevel(stack));
    }
}
