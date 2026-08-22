package com.xiaoyue.celestial_equipments.register;

import com.tterrag.registrate.util.entry.ItemEntry;
import com.tterrag.registrate.util.nullness.NonNullFunction;
import com.tterrag.registrate.util.nullness.NonNullSupplier;
import com.xiaoyue.celestial_core.utils.IRarityUtils;
import com.xiaoyue.celestial_equipments.CelestialEquipments;
import com.xiaoyue.celestial_equipments.content.equipments.armor.*;
import com.xiaoyue.celestial_equipments.content.equipments.arrow.*;
import com.xiaoyue.celestial_equipments.content.equipments.bow.*;
import com.xiaoyue.celestial_equipments.content.equipments.crossbow.GlintstoneResonance;
import com.xiaoyue.celestial_equipments.content.equipments.crossbow.SakuraBloom;
import com.xiaoyue.celestial_equipments.content.equipments.crossbow.SonicCrossbow;
import com.xiaoyue.celestial_equipments.content.equipments.crossbow.VirtualGoldCrossbow;
import com.xiaoyue.celestial_equipments.content.equipments.melee.*;
import com.xiaoyue.celestial_equipments.content.equipments.misc.Senbonzakura;
import com.xiaoyue.celestial_equipments.content.equipments.tool.*;
import com.xiaoyue.celestial_equipments.content.equipments.trident.AbyssalDisaster;
import com.xiaoyue.celestial_equipments.content.equipments.trident.OceanTide;
import com.xiaoyue.celestial_equipments.content.equipments.trident.PoseidonWrath;
import com.xiaoyue.celestial_equipments.content.items.ExpBottleItem;
import com.xiaoyue.celestial_equipments.content.items.RepairKitItem;
import com.xiaoyue.celestial_equipments.content.items.generic.GenericArrowItem;
import com.xiaoyue.celestial_equipments.content.library.DiggerType;
import com.xiaoyue.celestial_equipments.data.CETagGen;
import com.xiaoyue.celestial_invoker.content.common.entry.ArmorSetEntry;
import com.xiaoyue.celestial_invoker.content.common.helper.ItemModelHelper;
import com.xiaoyue.celestial_invoker.content.generic.builder.ArrowDataBuilder;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;

import java.util.ArrayList;
import java.util.List;

import static com.xiaoyue.celestial_equipments.CelestialEquipments.REGISTRATE;

@SuppressWarnings("unused")
public class CEItems {

    public static final List<String> ALL_EQUIPMENTS = new ArrayList<>();

    public static final ItemEntry<Item> CREATIVE_UP_STONE = register("creative_up_stone",
            p -> new Item(p.rarity(Rarity.EPIC)));
    public static final ItemEntry<Item> GEAR_ESSENCE_PLATE = register("gear_essence_plate",
            p -> new Item(p.rarity(Rarity.RARE)));
    public static final ItemEntry<Item> REPAIR_KIT = register("repair_kit",
            p -> new RepairKitItem(p.rarity(Rarity.RARE)));

    public static final ItemEntry<ExpBottleItem> EXP_BOTTLE_SMALL = register("exp_bottle_small",
            p -> new ExpBottleItem(p.rarity(Rarity.RARE), 100));
    public static final ItemEntry<ExpBottleItem> EXP_BOTTLE_BASE = register("exp_bottle_base",
            p -> new ExpBottleItem(p.rarity(Rarity.UNCOMMON), 150));
    public static final ItemEntry<ExpBottleItem> EXP_BOTTLE_BIG = register("exp_bottle_big",
            p -> new ExpBottleItem(p.rarity(Rarity.EPIC), 200));

    public static final ItemEntry<Senbonzakura> SENBONZAKURA = register("misc", "senbonzakura", Senbonzakura::new);

    public static final ItemEntry<SakuraBlade> SAKURA_BLADE = noUpgradeMelee("sakura_blade", SakuraBlade::new);
    public static final ItemEntry<ResonantRuinDagger> RESONANT_RUIN_DAGGER = noUpgradeMelee("resonant_ruin_dagger", ResonantRuinDagger::new);
    public static final ItemEntry<AbyssSacrificeDagger> ABYSS_SACRIFICE_DAGGER = noUpgradeMelee("abyss_sacrifice_dagger", AbyssSacrificeDagger::new);
    public static final ItemEntry<BlankingDagger> BLANKING_DAGGER = melee("blanking_dagger", BlankingDagger::new);
    public static final ItemEntry<JazzDagger> JAZZ_DAGGER = melee("jazz_dagger", JazzDagger::new);
    public static final ItemEntry<UndeadSword> UNDEAD_SWORD = melee("undead_sword", UndeadSword::new);
    public static final ItemEntry<BloodclotSword> BLOODCLOT_SWORD = melee("bloodclot_sword", BloodclotSword::new);
    public static final ItemEntry<AbyssWhisper> ABYSS_WHISPER = melee("abyss_whisper", AbyssWhisper::new);
    public static final ItemEntry<BrilliantGlory> BRILLIANT_GLORY = melee("brilliant_glory", BrilliantGlory::new);
    public static final ItemEntry<ShadyDeap> SHADY_DEAP = melee("shady_deap", ShadyDeap::new);
    public static final ItemEntry<GiantSkeleton> GIANT_SKELETON = melee("giant_skeleton", GiantSkeleton::new);
    public static final ItemEntry<BloodBinding> BLOOD_BINDING = melee("blood_binding", BloodBinding::new);
    public static final ItemEntry<CrystalSword> CRYSTAL_SWORD = melee("crystal_sword", CrystalSword::new);
    public static final ItemEntry<HeavenGift> HEAVEN_GIFT = melee("heaven_gift", HeavenGift::new);
    public static final ItemEntry<AvariceBlade> AVARICE_BLADE = melee("avarice_blade", AvariceBlade::new);
    public static final ItemEntry<TerraBroadsword> TERRA_BROADSWORD = melee("terra_broadsword", TerraBroadsword::new);
    public static final ItemEntry<GlintstoneScythe> GLINTSTONE_SCYTHE = melee("glintstone_scythe", GlintstoneScythe::new);

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
    public static final ItemEntry<GlintstoneResonance> GLINTSTONE_RESONANCE = crossbow("glintstone_resonance", GlintstoneResonance::new);

    public static final ItemEntry<OceanTide> OCEAN_TIDE = trident("ocean_tide", OceanTide::new);
    public static final ItemEntry<PoseidonWrath> POSEIDON_WRATH = trident("poseidon_wrath", PoseidonWrath::new);
    public static final ItemEntry<AbyssalDisaster> ABYSSAL_DISASTER = trident("abyssal_disaster", AbyssalDisaster::new);

    public static final ItemEntry<GravediggersHelper> GRAVEDIGGERS_HELPER = tool("gravediggers_helper", GravediggersHelper::new,
            ItemTags.PICKAXES, ItemTags.TOOLS);
    public static final ItemEntry<RadiantTreasure> RADIANT_TREASURE = tool("radiant_treasure", RadiantTreasure::new,
            ItemTags.PICKAXES, ItemTags.TOOLS);
    public static final ItemEntry<GlintstoneTool> GLINTSTONE_PICKAXE = tool("glintstone_pickaxe",
            () -> new GlintstoneTool(DiggerType.PICKAXE, 3f), ItemTags.PICKAXES, ItemTags.TOOLS);
    public static final ItemEntry<GlintstoneTool> GLINTSTONE_AXE = tool("glintstone_axe",
            () -> new GlintstoneTool(DiggerType.AXE, 5f), ItemTags.AXES, ItemTags.TOOLS);
    public static final ItemEntry<GlintstoneTool> GLINTSTONE_SHOVEL = tool("glintstone_shovel",
            () -> new GlintstoneTool(DiggerType.SHOVEL, 2f), ItemTags.SHOVELS, ItemTags.TOOLS);
    public static final ItemEntry<GlintstoneTool> GLINTSTONE_HOE = tool("glintstone_hoe",
            () -> new GlintstoneTool(DiggerType.HOE, 1f), ItemTags.HOES, ItemTags.TOOLS);
    public static final ItemEntry<EnderThrowingAxe> ENDER_THROWING_AXE = tool("ender_throwing_axe", EnderThrowingAxe::new,
            ItemTags.AXES, ItemTags.TOOLS, CETagGen.UPGRADEABLE_DIGGER);
    public static final ItemEntry<LifeHoe> LIFE_HOE = tool("life_hoe", LifeHoe::new,
            ItemTags.HOES, ItemTags.TOOLS);
    public static final ItemEntry<FinalShovel> FINAL_SHOVEL = tool("final_shovel", FinalShovel::new,
            ItemTags.SHOVELS, ItemTags.TOOLS);
    public static final ItemEntry<FashionScissors> FASHION_SCISSORS = tool("fashion_scissors", FashionScissors::new,
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

    public static final ArmorSetEntry<CelestialWitch> CELESTIAL_WITCH = CelestialEquipments.EXTRA.armors(CelestialWitch::createName,
            "armor/celestial_witch/", type -> p -> new CelestialWitch(type));
    public static final ArmorSetEntry<EarthKnight> EARTH_KNIGHT = CelestialEquipments.EXTRA.armors(EarthKnight::createName,
            "armor/earth_knight/", type -> p -> new EarthKnight(type));
    public static final ArmorSetEntry<ChasingSummer> CHASING_SUMMER = CelestialEquipments.EXTRA.armors(ChasingSummer::createName,
            "armor/chasing_summer/", type -> p -> new ChasingSummer(type));
    public static final ArmorSetEntry<MortalShadow> MORTAL_SHADOW = CelestialEquipments.EXTRA.armors(MortalShadow::createName,
            "armor/mortal_shadow/", type -> p -> new MortalShadow(type));
    public static final ArmorSetEntry<DeepGuardian> DEEP_GUARDIAN = CelestialEquipments.EXTRA.armors(DeepGuardian::createName,
            "armor/deep_guardian/", type -> p -> new DeepGuardian(type));

    public static <T extends Item> ItemEntry<T> register(String path, String id, NonNullFunction<Item.Properties, T> factory) {
        return REGISTRATE.item(id, factory).model((ctx, pvd) ->
                pvd.generated(ctx, pvd.modLoc("item/" + path + "/" + ctx.getName()))).register();
    }

    public static <T extends Item> ItemEntry<T> register(String id, NonNullFunction<Item.Properties, T> factory) {
        return REGISTRATE.item(id, factory).model((ctx, pvd) ->
                pvd.generated(ctx, pvd.modLoc("item/" + ctx.getName()))).register();
    }

    public static <T extends Item> ItemEntry<T> arrow(String id, NonNullFunction<Item.Properties, T> factory) {
        ALL_EQUIPMENTS.add(id);
        return REGISTRATE.item(id, factory)
                .model((ctx, pvd) -> pvd.generated(ctx, pvd.modLoc("item/arrow/" + ctx.getName())))
                .tag(ItemTags.ARROWS).register();
    }

    public static <T extends Item> ItemEntry<T> melee(String id, NonNullSupplier<T> factory) {
        ALL_EQUIPMENTS.add(id);
        return REGISTRATE.item(id, p -> factory.get()).model((ctx, pvd)
                -> pvd.handheld(ctx, pvd.modLoc("item/melee/" + ctx.getName()))).tag(ItemTags.SWORDS, CETagGen.UPGRADEABLE_MELEE).register();
    }

    public static <T extends Item> ItemEntry<T> noUpgradeMelee(String id, NonNullSupplier<T> factory) {
        ALL_EQUIPMENTS.add(id);
        return REGISTRATE.item(id, p -> factory.get()).model((ctx, pvd)
                -> pvd.handheld(ctx, pvd.modLoc("item/melee/" + ctx.getName()))).tag(ItemTags.SWORDS).register();
    }

    @SafeVarargs
    public static <T extends Item> ItemEntry<T> tool(String id, NonNullSupplier<T> factory, TagKey<Item>... tag) {
        ALL_EQUIPMENTS.add(id);
        return REGISTRATE.item(id, p -> factory.get()).model((ctx, pvd)
                -> pvd.handheld(ctx, pvd.modLoc("item/tool/" + ctx.getName()))).tag(tag).register();
    }

    public static <T extends Item> ItemEntry<T> bow(String id, NonNullSupplier<T> factory) {
        ALL_EQUIPMENTS.add(id);
        return REGISTRATE.item(id, p -> factory.get()).model(ItemModelHelper::createBowModel)
                .tag(CETagGen.UPGRADEABLE_BOW).register();
    }

    public static <T extends Item> ItemEntry<T> crossbow(String id, NonNullSupplier<T> factory) {
        ALL_EQUIPMENTS.add(id);
        return REGISTRATE.item(id, p -> factory.get()).model(ItemModelHelper::createCrossbowModel)
                .tag(CETagGen.UPGRADEABLE_CROSSBOW).register();
    }

    public static <T extends Item> ItemEntry<T> trident(String id, NonNullSupplier<T> factory) {
        ALL_EQUIPMENTS.add(id);
        return REGISTRATE.item(id, p -> factory.get()).model(ItemModelHelper::createTridentModel)
                .tag(CETagGen.UPGRADEABLE_TRIDENTS).register();
    }

    public static void register() {
    }
}