package com.xiaoyue.celestial_equipments.register;

import com.tterrag.registrate.util.entry.ItemEntry;
import com.tterrag.registrate.util.nullness.NonNullFunction;
import com.tterrag.registrate.util.nullness.NonNullSupplier;
import com.xiaoyue.celestial_core.utils.IRarityUtils;
import com.xiaoyue.celestial_equipments.CelestialEquipments;
import com.xiaoyue.celestial_equipments.content.equipments.arrow.*;
import com.xiaoyue.celestial_equipments.content.equipments.bow.*;
import com.xiaoyue.celestial_equipments.content.equipments.crossbow.SakuraBloom;
import com.xiaoyue.celestial_equipments.content.equipments.crossbow.SonicCrossbow;
import com.xiaoyue.celestial_equipments.content.equipments.crossbow.VirtualGoldCrossbow;
import com.xiaoyue.celestial_equipments.content.equipments.digger.EnderThrowingAxe;
import com.xiaoyue.celestial_equipments.content.equipments.digger.FashionScissors;
import com.xiaoyue.celestial_equipments.content.equipments.digger.GravediggersHelper;
import com.xiaoyue.celestial_equipments.content.equipments.digger.RadiantTreasure;
import com.xiaoyue.celestial_equipments.content.equipments.melee.*;
import com.xiaoyue.celestial_equipments.content.equipments.trident.OceanTide;
import com.xiaoyue.celestial_equipments.content.items.ExpBottleItem;
import com.xiaoyue.celestial_equipments.content.items.RepairKitItem;
import com.xiaoyue.celestial_equipments.content.items.generic.GenericArrowItem;
import com.xiaoyue.celestial_equipments.data.CETagGen;
import com.xiaoyue.celestial_invoker.content.ancillary.helper.IRegistrateHelper;
import com.xiaoyue.celestial_invoker.content.ancillary.helper.ItemModelHelper;
import com.xiaoyue.celestial_invoker.content.generic.builder.ArrowDataBuilder;
import com.xiaoyue.celestial_invoker.content.generic.item.CelestialTridentItem;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@SuppressWarnings("unused")
public class CEItems {

    public static final List<String> ALL_EQUIPMENTS = new ArrayList<>();

    public static final ItemEntry<Item> CREATIVE_UP_STONE = register("creative_up_stone",
            p -> new Item(p.rarity(Rarity.EPIC)));
    public static final ItemEntry<Item> GEAR_ESSENCE_PLATE = register("gear_essence_plate",
            p -> new Item(p.rarity(Rarity.RARE)));
    public static final ItemEntry<Item> REPAIR_KIT = register("repair_kit",
            p -> new RepairKitItem(p.rarity(Rarity.RARE)));

    public static final ItemEntry<ExpBottleItem> EXP_BOTTLE_SMALL = register("misc", "exp_bottle_small",
            p -> new ExpBottleItem(p.rarity(Rarity.RARE), 100));
    public static final ItemEntry<ExpBottleItem> EXP_BOTTLE_BASE = register("misc", "exp_bottle_base",
            p -> new ExpBottleItem(p.rarity(Rarity.UNCOMMON), 150));
    public static final ItemEntry<ExpBottleItem> EXP_BOTTLE_BIG = register("misc", "exp_bottle_big",
            p -> new ExpBottleItem(p.rarity(Rarity.EPIC), 200));

    public static final ItemEntry<UndeadSword> UNDEAD_SWORD = melee("undead_sword", UndeadSword::new);
    public static final ItemEntry<BlankingDagger> BLANKING_DAGGER = melee("blanking_dagger", BlankingDagger::new);
    public static final ItemEntry<AbyssWhisper> ABYSS_WHISPER = melee("abyss_whisper", AbyssWhisper::new);
    public static final ItemEntry<BrilliantGlory> BRILLIANT_GLORY = melee("brilliant_glory", BrilliantGlory::new);
    public static final ItemEntry<ShadyDeap> SHADY_DEAP = melee("shady_deap", ShadyDeap::new);
    public static final ItemEntry<GiantSkeleton> GIANT_SKELETON = melee("giant_skeleton", GiantSkeleton::new);
    public static final ItemEntry<BloodBinding> BLOOD_BINDING = melee("blood_binding", BloodBinding::new);
    public static final ItemEntry<CrystalSword> CRYSTAL_SWORD = melee("crystal_sword", CrystalSword::new);
    public static final ItemEntry<HeavenGift> HEAVEN_GIFT = melee("heaven_gift", HeavenGift::new);
    public static final ItemEntry<AvariceBlade> AVARICE_BLADE = melee("avarice_blade", AvariceBlade::new);
    public static final ItemEntry<TerraBroadsword> TERRA_BROADSWORD = melee("terra_broadsword", TerraBroadsword::new);
    public static final ItemEntry<JazzDagger> JAZZ_DAGGER = melee("jazz_dagger", JazzDagger::new);

    public static final ItemEntry<ElvenBow> ELVEN_BOW = bow("elven_bow", ElvenBow::new);
    public static final ItemEntry<SunFlame> SUN_FLAME = bow("sun_flame", SunFlame::new);
    public static final ItemEntry<BrightProphecy> BRIGHT_PROPHECY = bow("bright_prophecy", BrightProphecy::new);
    public static final ItemEntry<DarkCrow> DARK_CROW = bow("dark_crow", DarkCrow::new);
    public static final ItemEntry<EmeraldWind> EMERALD_WIND = bow("emerald_wind", EmeraldWind::new);
    public static final ItemEntry<PolarShadow> POLAR_SHADOW = bow("polar_shadow", PolarShadow::new);
    public static final ItemEntry<HeavenBow> HEAVEN_BOW = bow("heaven_bow", HeavenBow::new);
    public static final ItemEntry<FrozenInvasion> FROZEN_INVASION = bow("frozen_invasion", FrozenInvasion::new);

    public static final ItemEntry<SakuraBloom> SAKURA_BLOOM = crossbow("sakura_bloom", SakuraBloom::new);
    public static final ItemEntry<VirtualGoldCrossbow> VIRTUAL_GOLD_CROSSBOW = crossbow("virtual_gold_crossbow", VirtualGoldCrossbow::new);
    public static final ItemEntry<SonicCrossbow> SONIC_CROSSBOW = crossbow("sonic_crossbow", SonicCrossbow::new);

    public static final ItemEntry<CelestialTridentItem> OCEAN_TIDE = trident("ocean_tide", OceanTide::new);

    public static final ItemEntry<GravediggersHelper> GRAVEDIGGERS_HELPER = digger("gravediggers_helper", GravediggersHelper::new,
            ItemTags.PICKAXES, ItemTags.TOOLS);
    public static final ItemEntry<RadiantTreasure> RADIANT_TREASURE = digger("radiant_treasure", RadiantTreasure::new,
            ItemTags.PICKAXES, ItemTags.TOOLS);
    public static final ItemEntry<EnderThrowingAxe> ENDER_THROWING_AXE = digger("ender_throwing_axe", EnderThrowingAxe::new,
            ItemTags.AXES, ItemTags.TOOLS, CETagGen.UPGRADEABLE_DIGGER);
    public static final ItemEntry<FashionScissors> FASHION_SCISSORS = digger("fashion_scissors", FashionScissors::new,
            ItemTags.TOOLS);

    public static final ItemEntry<GenericArrowItem> TRAINING_ARROW = arrow("training_arrow", p ->
            new GenericArrowItem(p.rarity(Rarity.RARE), 1f));
    public static final ItemEntry<GenericArrowItem> OCEAN_ARROW = arrow("ocean_arrow", p ->
            new GenericArrowItem(p.rarity(Rarity.RARE).fireResistant(), new ArrowDataBuilder().damage(4.0).ignoreWater()));
    public static final ItemEntry<GenericArrowItem> FRAGMENT_ARROW = arrow("fragment_arrow", p ->
            new GenericArrowItem(p.rarity(IRarityUtils.DARK_GREEN), new ArrowDataBuilder().damage(9.0).pierce((byte) 32)));
    public static final ItemEntry<AbyssArrow> ABYSS_ARROW = arrow("abyss_arrow", AbyssArrow::new);
    public static final ItemEntry<BlackFlameArrow> BLACK_FLAME_ARROW = arrow("black_flame_arrow", BlackFlameArrow::new);
    public static final ItemEntry<EnderArrow> ENDER_ARROW = arrow("ender_arrow", EnderArrow::new);
    public static final ItemEntry<VirtualGoldArrow> VIRTUAL_GOLD_ARROW = arrow("virtual_gold_arrow", VirtualGoldArrow::new);
    public static final ItemEntry<GuardianArrow> GUARDIAN_ARROW = arrow("guardian_arrow", GuardianArrow::new);

    public static <T extends Item> ItemEntry<T> register(String path, String id, NonNullFunction<Item.Properties, T> factory) {
        return CelestialEquipments.REGISTRATE.item(id, factory).model((ctx, pvd) ->
                pvd.generated(ctx, pvd.modLoc("item/" + path + "/" + ctx.getName()))).register();
    }

    public static <T extends Item> ItemEntry<T> register(String id, NonNullFunction<Item.Properties, T> factory) {
        return CelestialEquipments.REGISTRATE.item(id, factory).model((ctx, pvd) ->
                pvd.generated(ctx, pvd.modLoc("item/" + ctx.getName()))).register();
    }

    public static <T extends Item> ItemEntry<T> arrow(String id, NonNullFunction<Item.Properties, T> factory) {
        ALL_EQUIPMENTS.add(id);
        return CelestialEquipments.REGISTRATE.item(id, factory)
                .model((ctx, pvd) -> pvd.generated(ctx, pvd.modLoc("item/arrow/" + ctx.getName())))
                .tag(ItemTags.ARROWS).register();
    }

    public static <T extends Item> ItemEntry<T> arrow(String id, NonNullSupplier<T> factory) {
        return arrow(id, p -> factory.get());
    }

    public static <T extends Item> ItemEntry<T> melee(String id, NonNullSupplier<T> factory) {
        ALL_EQUIPMENTS.add(id);
        return CelestialEquipments.REGISTRATE.item(id, p -> factory.get()).model((ctx, pvd) -> pvd.handheld(ctx, pvd.modLoc("item/melee/" + ctx.getName())))
                .tag(ItemTags.SWORDS, CETagGen.UPGRADEABLE_MELEE).register();
    }

    @SafeVarargs
    public static <T extends Item> ItemEntry<T> digger(String id, NonNullSupplier<T> factory, TagKey<Item>... tag) {
        ALL_EQUIPMENTS.add(id);
        return CelestialEquipments.REGISTRATE.item(id, p -> factory.get()).model((ctx, pvd) -> pvd.handheld(ctx, pvd.modLoc("item/digger/" + ctx.getName())))
                .tag(tag).register();
    }

    public static <T extends Item> ItemEntry<T> bow(String id, NonNullSupplier<T> factory) {
        ALL_EQUIPMENTS.add(id);
        return CelestialEquipments.REGISTRATE.item(id, p -> factory.get()).model(ItemModelHelper::createBowModel)
                .tag(CETagGen.UPGRADEABLE_BOW).register();
    }

    public static <T extends Item> ItemEntry<T> crossbow(String id, NonNullSupplier<T> factory) {
        ALL_EQUIPMENTS.add(id);
        return CelestialEquipments.REGISTRATE.item(id, p -> factory.get()).model(ItemModelHelper::createCrossbowModel)
                .tag(CETagGen.UPGRADEABLE_CROSSBOW).register();
    }

    public static <T extends Item> ItemEntry<T> trident(String id, NonNullSupplier<T> factory) {
        ALL_EQUIPMENTS.add(id);
        return CelestialEquipments.REGISTRATE.item(id, p -> factory.get()).model(ItemModelHelper::createTridentModel)
                .tag(CETagGen.UPGRADEABLE_TRIDENTS).register();
    }

    public static <T extends Item> Map<ArmorItem.Type, ItemEntry<T>> armors(IRegistrateHelper.ArmorNameCallback name, String path, IRegistrateHelper.ArmorTypeCallback<T> item) {
        Map<ArmorItem.Type, ItemEntry<T>> map = CelestialEquipments.REGISTRATE.armors(name, "armor/" + path + "/", item);
        map.values().forEach(ent -> ALL_EQUIPMENTS.add(ent.getId().getPath()));
        return map;
    }

    public static void register() {
    }
}