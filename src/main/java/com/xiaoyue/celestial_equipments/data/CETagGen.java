package com.xiaoyue.celestial_equipments.data;

import com.tterrag.registrate.providers.RegistrateItemTagsProvider;
import com.tterrag.registrate.providers.RegistrateTagsProvider;
import com.xiaoyue.celestial_equipments.CelestialEquipments;
import com.xiaoyue.celestial_equipments.register.CEItems;
import com.xiaoyue.celestial_invoker.register.CIEntities;
import net.minecraft.core.registries.Registries;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

public class CETagGen {

    public static final TagKey<Block> GRAVEDIGGERS_HELPER_MINABLE = BlockTags.create(CelestialEquipments.loc("gravediggers_helper_minable"));

    public static final TagKey<Item> SMALL_THROWING = ItemTags.create(CelestialEquipments.loc("small_throwing"));

    public static final TagKey<Item> NOT_UPGRADEABLE = ItemTags.create(CelestialEquipments.loc("not_upgradeable"));
    public static final TagKey<Item> UPGRADEABLE_MELEE = ItemTags.create(CelestialEquipments.loc("upgradeable_melee"));
    public static final TagKey<Item> UPGRADEABLE_RANGED = ItemTags.create(CelestialEquipments.loc("upgradeable_ranged"));
    public static final TagKey<Item> UPGRADEABLE_ARMORS = ItemTags.create(CelestialEquipments.loc("upgradeable_armors"));
    public static final TagKey<Item> UPGRADEABLE_TRIDENTS = ItemTags.create(CelestialEquipments.loc("upgradeable_tridents"));
    public static final TagKey<Item> UPGRADEABLE_DIGGER = ItemTags.create(CelestialEquipments.loc("upgradeable_upgradeable_digger"));

    public static final TagKey<EntityType<?>> IS_AIR_BLADE = TagKey.create(Registries.ENTITY_TYPE, CelestialEquipments.loc("is_air_blade"));

    public static void onItemTagGen(RegistrateItemTagsProvider pvd) {
        pvd.addTag(SMALL_THROWING).add(CEItems.SAKURA_BLADE.asItem());
        pvd.addTag(NOT_UPGRADEABLE).add(CEItems.SAKURA_BLADE.asItem(), CEItems.RESONANT_RUIN_DAGGER.asItem(), CEItems.ABYSS_SACRIFICE_DAGGER.asItem());
        pvd.addTag(UPGRADEABLE_MELEE);
        pvd.addTag(UPGRADEABLE_RANGED);
        pvd.addTag(UPGRADEABLE_ARMORS);
        pvd.addTag(UPGRADEABLE_TRIDENTS);
        pvd.addTag(UPGRADEABLE_DIGGER);
    }

    public static void onEntityTagGen(RegistrateTagsProvider.IntrinsicImpl<EntityType<?>> pvd) {
        pvd.addTag(IS_AIR_BLADE).add(CIEntities.AIR_BLADE.get());
    }

    public static void onBlockTagGen(RegistrateTagsProvider.IntrinsicImpl<Block> pvd) {
        pvd.addTag(GRAVEDIGGERS_HELPER_MINABLE).addTag(BlockTags.BASE_STONE_OVERWORLD).addTag(BlockTags.BASE_STONE_NETHER);
    }
}
