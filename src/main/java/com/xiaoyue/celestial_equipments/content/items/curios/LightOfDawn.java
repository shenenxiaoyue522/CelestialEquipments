package com.xiaoyue.celestial_equipments.content.items.curios;

import com.xiaoyue.celestial_core.utils.EntityUtils;
import com.xiaoyue.celestial_equipments.CelestialEquipments;
import com.xiaoyue.celestial_equipments.content.items.generic.BaseCurioItem;
import com.xiaoyue.celestial_equipments.data.CETagGen;
import com.xiaoyue.celestial_equipments.register.CEItems;
import com.xiaoyue.celestial_equipments.utils.EquipmentUtils;
import com.xiaoyue.celestial_invoker.content.entities.AirBladeEntity;
import com.xiaoyue.celestial_invoker.content.generic.item.api.IAirBladeUser;
import com.xiaoyue.celestial_invoker.invoker.config.ConfigHolderEntry;
import com.xiaoyue.celestial_invoker.invoker.config.value.DoubleConfigEntry;
import com.xiaoyue.celestial_invoker.invoker.config.value.IntConfigEntry;
import com.xiaoyue.celestial_invoker.invoker.tooltip.SubscribeTooltip;
import com.xiaoyue.celestial_invoker.invoker.tooltip.TooltipEntry;
import com.xiaoyue.celestial_invoker.invoker.tooltip.TooltipHolder;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class LightOfDawn extends BaseCurioItem implements IAirBladeUser {
    public LightOfDawn(Properties properties) {
        super(properties);
    }

    @ConfigHolderEntry(category = "curios")
    public static IntConfigEntry levelConditionConfig = IntConfigEntry.defineFromZero("Light Of Dawn Level Condition",
            5, 20, "Light Of Dawn: Minimum level required to launch the Blade");

    @ConfigHolderEntry(category = "curios")
    public static DoubleConfigEntry dmgConfig = DoubleConfigEntry.defineFromMinUsable("Light Of Dawn Damage Factor",
            0.45, 1000, "Light Of Dawn: Damage dealt by the Blade is based on your attack damage");

    @SubscribeTooltip(id = "light_of_dawn")
    public static TooltipHolder tooltips = TooltipHolder.define(
            TooltipEntry.define("When wielding a melee weapon with an equipment level greater than %s"),
            TooltipEntry.define("Additionally launches a Radiant Blade forward"),
            TooltipEntry.define("The Radiant Blade deals %s of your attack damage"));

    @Override
    public void appendHoverText(ItemStack pStack, @Nullable Level pLevel, List<Component> list, TooltipFlag pIsAdvanced) {
        list.add(tooltips.get(0).withGray(TooltipEntry.num(levelConditionConfig.get())));
        list.add(tooltips.get(1).withGray());
        list.add(tooltips.get(2).withGray(TooltipEntry.per(dmgConfig.get())));
    }

    public static void onLeftClick(Player player) {
        ItemStack stack = player.getMainHandItem();
        int level = EquipmentUtils.getLevel(stack);
        if (level > levelConditionConfig.get() && EntityUtils.isFullCharged(player) && stack.is(CETagGen.UPGRADEABLE_MELEE)) {
            AirBladeEntity blade = new AirBladeEntity(player.level());
            blade.setUser(CEItems.LIGHT_OF_DAWN.get());
            float damage = (float) player.getAttributeValue(Attributes.ATTACK_DAMAGE) * dmgConfig.floatValue();
            float zr = (float) (player.getRandom().nextDouble() * 360f);
            blade.setData(player, damage, 100, zr, stack);
            blade.shootFromRotation(player, player.getXRot(), player.getYRot(), 0, 1.5f, 0);
            player.level().addFreshEntity(blade);
            player.resetAttackStrengthTicker();
        }
    }

    @Override
    public boolean isGlow() {
        return true;
    }

    @Override
    public ResourceLocation getTexture(AirBladeEntity blade) {
        return CelestialEquipments.loc("textures/entity/radiant_blade.png");
    }
}
