package com.xiaoyue.celestial_equipments.content.equipments.armor;

import com.xiaoyue.celestial_equipments.content.items.generic.GenericArmorItem;
import com.xiaoyue.celestial_equipments.register.CEItems;
import com.xiaoyue.celestial_invoker.content.common.entry.ArmorMate;
import com.xiaoyue.celestial_invoker.content.common.entry.ArmorSetEntry;
import com.xiaoyue.celestial_invoker.invoker.tooltip.SubscribeTooltip;
import com.xiaoyue.celestial_invoker.invoker.tooltip.TooltipEntry;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

import javax.annotation.Nullable;

public class CelestialWitch extends GenericArmorItem {
    public static final ArmorMate MATE = ArmorMate.builder().durability(22).defense(new int[]{3, 5, 7, 3}).toughness(2f).enchant(22).build();

    public CelestialWitch(Type pType) {
        super(MATE, pType, new Properties());
    }

    public static String createName(ArmorItem.Type type) {
        String name = switch (type) {
            case HELMET -> "celestial_witch_hat";
            case CHESTPLATE -> "celestial_witch_robe";
            case LEGGINGS -> "celestial_witch_dress";
            case BOOTS -> "celestial_witch_boots";
        };
        CEItems.ALL_EQUIPMENTS.add(name);
        return name;
    }

    @SubscribeTooltip(id = "celestial_witch_set_id")
    public static TooltipEntry setIdTooltip = TooltipEntry.define("Celestial Witch's Blessing");

    @Override
    public MutableComponent getSetName() {
        return setIdTooltip.withColor(0x7c69ff);
    }

      @Override
      public @Nullable String getArmorTexture(ItemStack stack, Entity entity, EquipmentSlot slot, String type) {
          // return "celestial_equipments:textures/models/armor/celestial_witch_armor.png";
          return EMPTY_MODEL_TEX;
      }

    @Override
    public ArmorSetEntry<? extends Item> getArmorSet() {
        return CEItems.CELESTIAL_WITCH;
    }

    /*
    @Override
    public void initializeClient(Consumer<IClientItemExtensions> consumer) {
        consumer.accept(new IClientItemExtensions() {
            @Override
            public @NotNull HumanoidModel<?> getHumanoidArmorModel(LivingEntity entity, ItemStack stack, EquipmentSlot slot, HumanoidModel<?> original) {
                EntityModelSet set = Minecraft.getInstance().getEntityModels();
                ItemStack chest = entity.getItemBySlot(EquipmentSlot.CHEST);
                boolean check = chest.is(CEItems.CELESTIAL_WITCH.getChestplate().get());
                return new CelestialWitchModel(check, slot, set.bakeLayer(CEModelLayers.CELESTIAL_WITCH_ARMOR_LAYER));
            }
        });
    }
     */
}
