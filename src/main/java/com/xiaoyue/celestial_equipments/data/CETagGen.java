package com.xiaoyue.celestial_equipments.data;

import com.tterrag.registrate.providers.RegistrateItemTagsProvider;
import com.xiaoyue.celestial_equipments.CelestialEquipments;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;

public class CETagGen {

    public static final TagKey<Item> NOT_UPGRADEABLE = ItemTags.create(CelestialEquipments.loc("not_upgradeable"));
    public static final TagKey<Item> CELESTIAL_MELEE = ItemTags.create(CelestialEquipments.loc("celestial_melee"));
    public static final TagKey<Item> CELESTIAL_BOW = ItemTags.create(CelestialEquipments.loc("celestial_bow"));
    public static final TagKey<Item> CELESTIAL_CROSSBOW = ItemTags.create(CelestialEquipments.loc("celestial_crossbow"));
    public static final TagKey<Item> CELESTIAL_ARMORS = ItemTags.create(CelestialEquipments.loc("celestial_armors"));

    public static void onItemTagGen(RegistrateItemTagsProvider pvd) {
        pvd.addTag(CELESTIAL_MELEE);
        pvd.addTag(CELESTIAL_BOW);
        pvd.addTag(CELESTIAL_ARMORS);
    }
}
