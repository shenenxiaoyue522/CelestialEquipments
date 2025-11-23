package com.xiaoyue.celestial_equipments.content.items.generic;

import com.xiaoyue.celestial_equipments.content.library.ICEquipment;
import com.xiaoyue.celestial_invoker.content.entities.GenericArrowEntity;
import com.xiaoyue.celestial_invoker.content.generic.builder.ArrowDataBuilder;
import com.xiaoyue.celestial_invoker.invoker.tooltip.SubscribeTooltip;
import com.xiaoyue.celestial_invoker.invoker.tooltip.TooltipEntry;
import dev.xkmc.l2damagetracker.contents.attack.CreateSourceEvent;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.ArrowItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class GenericArrowItem extends ArrowItem implements ICEquipment {
    public final ArrowDataBuilder builder;

    public GenericArrowItem(Properties pProperties, ArrowDataBuilder builder) {
        super(pProperties);
        this.builder = builder;
    }

    public GenericArrowItem(Item.Properties pProperties, double defaultDamage) {
        this(pProperties, new ArrowDataBuilder().damage(defaultDamage));
    }

    @Override
    public AbstractArrow createArrow(Level pLevel, ItemStack pStack, LivingEntity pShooter) {
        GenericArrowEntity arrow = new GenericArrowEntity(pLevel, pShooter, builder);
        arrow.setArrow(pStack);
        return arrow;
    }

    @Override
    public Component getName(ItemStack pStack) {
        return this.getItemName(pStack);
    }

    @Override
    public boolean isUpgradeable() {
        return false;
    }

    @SubscribeTooltip(id = "pierce_level")
    public static TooltipEntry pierceLevel = TooltipEntry.define("Penetration level: %s");

    @SubscribeTooltip(id = "knockback_ability")
    public static TooltipEntry knockbackAbility = TooltipEntry.define("Knockback ability: %s");

    @SubscribeTooltip(id = "ocean_arrow")
    public static TooltipEntry oceanArrow = TooltipEntry.define("Arrows are capable of flying in water");

    @Override
    public void appendHoverText(ItemStack pStack, @Nullable Level pLevel, List<Component> list, TooltipFlag pIsAdvanced) {
        list.add(UpgradeableBow.bowDamage.withColor(ChatFormatting.BLUE, TooltipEntry.num((int) this.builder.damage)));
        if (this.builder.pierce != 0) {
            list.add(pierceLevel.withColor(ChatFormatting.BLUE, TooltipEntry.num(this.builder.pierce)));
        }
        if (this.builder.knock != 0) {
            list.add(knockbackAbility.withColor(ChatFormatting.BLUE, TooltipEntry.num(this.builder.knock)));
        }
        if (this.builder.ignoreWater) {
            list.add(oceanArrow.withColor(ChatFormatting.BLUE));
        }
        this.addEquipmentTooltips(pStack, list);
        if (!this.isEnabled()) {
            list.add(Component.empty());
            list.add(itemBan.withGray());
        }
    }

    public void onCreateSource(GenericArrowItem arrow, CreateSourceEvent event) {
    }

    @Override
    public Item self() {
        return this;
    }
}
