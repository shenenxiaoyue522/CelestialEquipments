package com.xiaoyue.celestial_equipments.data;

import com.tterrag.registrate.providers.RegistrateItemTagsProvider;
import com.tterrag.registrate.providers.RegistrateTagsProvider;
import com.xiaoyue.celestial_equipments.CelestialEquipments;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

public class CETagGen {

    public static final TagKey<Block> GRAVEDIGGERS_HELPER_MINABLE = BlockTags.create(CelestialEquipments.loc("gravediggers_helper_minable"));

    public static final TagKey<Item> NOT_UPGRADEABLE = ItemTags.create(CelestialEquipments.loc("not_upgradeable"));
    public static final TagKey<Item> CELESTIAL_MELEE = ItemTags.create(CelestialEquipments.loc("celestial_melee"));
    public static final TagKey<Item> CELESTIAL_BOW = ItemTags.create(CelestialEquipments.loc("celestial_bow"));
    public static final TagKey<Item> CELESTIAL_CROSSBOW = ItemTags.create(CelestialEquipments.loc("celestial_crossbow"));
    public static final TagKey<Item> CELESTIAL_ARMORS = ItemTags.create(CelestialEquipments.loc("celestial_armors"));

    public static void onItemTagGen(RegistrateItemTagsProvider pvd) {
        pvd.addTag(CELESTIAL_MELEE);
        pvd.addTag(CELESTIAL_BOW);
        pvd.addTag(CELESTIAL_CROSSBOW);
        pvd.addTag(CELESTIAL_ARMORS);
    }

    public static void onBlockTagGen(RegistrateTagsProvider.IntrinsicImpl<Block> pvd) {
        pvd.addTag(GRAVEDIGGERS_HELPER_MINABLE).addTag(BlockTags.BASE_STONE_OVERWORLD).addTag(BlockTags.BASE_STONE_NETHER);
    }
}
