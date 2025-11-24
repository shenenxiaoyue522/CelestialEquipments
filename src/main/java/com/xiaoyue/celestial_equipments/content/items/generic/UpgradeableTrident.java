package com.xiaoyue.celestial_equipments.content.items.generic;

import com.xiaoyue.celestial_equipments.content.library.ICEquipment;
import com.xiaoyue.celestial_invoker.content.generic.item.CelestialTridentItem;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class UpgradeableTrident extends CelestialTridentItem implements ICEquipment {
    public static final List<UpgradeableTrident> TRIDENTS = new ArrayList<>();

    public UpgradeableTrident(Properties pProperties) {
        super(pProperties);
        TRIDENTS.add(this);
    }

    @Override
    public void appendHoverText(ItemStack pStack, @Nullable Level pLevel, List<Component> list, TooltipFlag pIsAdvanced) {
        addBaseTooltips(pStack, list);
        if (!this.isEnabled()) {
            list.add(Component.empty());
            list.add(itemBan.withGray());
        }
    }

    @Override
    public Item self() {
        return this;
    }
}
