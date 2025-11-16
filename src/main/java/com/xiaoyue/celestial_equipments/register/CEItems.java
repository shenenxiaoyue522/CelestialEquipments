package com.xiaoyue.celestial_equipments.register;

import com.tterrag.registrate.util.entry.ItemEntry;
import com.tterrag.registrate.util.nullness.NonNullFunction;
import com.xiaoyue.celestial_core.utils.IRarityUtils;
import com.xiaoyue.celestial_equipments.CelestialEquipments;
import com.xiaoyue.celestial_equipments.content.items.ExpBottleItem;
import com.xiaoyue.celestial_equipments.content.items.equipment.arrow.*;
import com.xiaoyue.celestial_equipments.content.items.equipment.bow.*;
import com.xiaoyue.celestial_equipments.content.items.equipment.digger.GravediggersHelper;
import com.xiaoyue.celestial_equipments.content.items.equipment.digger.RadiantTreasure;
import com.xiaoyue.celestial_equipments.content.items.equipment.melee.*;
import com.xiaoyue.celestial_equipments.content.items.generic.GenericArrow;
import com.xiaoyue.celestial_equipments.data.CETagGen;
import com.xiaoyue.celestial_invoker.content.ancillary.helper.IRegistrateHelper;
import com.xiaoyue.celestial_invoker.content.ancillary.helper.ItemModelHelper;
import com.xiaoyue.celestial_invoker.content.generic.builder.ArrowDataBuilder;
import com.xiaoyue.celestial_invoker.content.generic.item.GenericCrossbowItem;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class CEItems {

    public static final List<String> ALL_EQUIPMENTS = new ArrayList<>();

    public static final ItemEntry<Item> CREATIVE_UP_STONE, GEAR_ESSENCE_PLATE;
    public static final ItemEntry<ExpBottleItem> EXP_BOTTLE_SMALL, EXP_BOTTLE_BASE, EXP_BOTTLE_BIG;
    public static final ItemEntry<UndeadSword> UNDEAD_SWORD;
    public static final ItemEntry<BlankingDagger> BLANKING_DAGGER;
    public static final ItemEntry<AbyssWhisper> ABYSS_WHISPER;
    public static final ItemEntry<BrilliantGlory> BRILLIANT_GLORY;
    public static final ItemEntry<ShadyDeap> SHADY_DEAP;
    public static final ItemEntry<GiantSkeleton> GIANT_SKELETON;
    public static final ItemEntry<BloodBinding> BLOOD_BINDING;
    public static final ItemEntry<CrystalSword> CRYSTAL_SWORD;
    public static final ItemEntry<HeavenGift> HEAVEN_GIFT;
    public static final ItemEntry<AvariceBlade> AVARICE_BLADE;
    public static final ItemEntry<TerraBroadsword> TERRA_BROADSWORD;
    public static final ItemEntry<JazzDagger> JAZZ_DAGGER;

    public static final ItemEntry<ElvenBow> ELVEN_BOW;
    public static final ItemEntry<SunFlame> SUN_FLAME;
    public static final ItemEntry<BrightProphecy> BRIGHT_PROPHECY;
    public static final ItemEntry<DarkCrow> DARK_CROW;
    public static final ItemEntry<EmeraldWind> EMERALD_WIND;
    public static final ItemEntry<PolarShadow> POLAR_SHADOW;
    public static final ItemEntry<HeavenBow> HEAVEN_BOW;
    public static final ItemEntry<FrozenInvasion> FROZEN_INVASION;

    public static final ItemEntry<GenericCrossbowItem> SAKURA_BLOOM;

    public static final ItemEntry<GravediggersHelper> GRAVEDIGGERS_HELPER;
    public static final ItemEntry<RadiantTreasure> RADIANT_TREASURE;

    public static final ItemEntry<GenericArrow> TRAINING_ARROW, OCEAN_ARROW, FRAGMENT_ARROW;
    public static final ItemEntry<AbyssArrow> ABYSS_ARROW;
    public static final ItemEntry<BlackFlameArrow> BLACK_FLAME_ARROW;
    public static final ItemEntry<EnderArrow> ENDER_ARROW;
    public static final ItemEntry<VirtualGoldArrow> VIRTUAL_GOLD_ARROW;
    public static final ItemEntry<GuardianArrow> GUARDIAN_ARROW;

    static {
        CREATIVE_UP_STONE = register("creative_up_stone", p -> new Item(p.rarity(Rarity.EPIC)));
        GEAR_ESSENCE_PLATE = register("gear_essence_plate", p -> new Item(p.rarity(Rarity.RARE)));
        EXP_BOTTLE_SMALL = register("misc", "exp_bottle_small", p -> new ExpBottleItem(p.rarity(Rarity.RARE), 100));
        EXP_BOTTLE_BASE = register("misc", "exp_bottle_base", p -> new ExpBottleItem(p.rarity(Rarity.UNCOMMON), 150));
        EXP_BOTTLE_BIG = register("misc", "exp_bottle_big", p -> new ExpBottleItem(p.rarity(Rarity.EPIC), 200));

        UNDEAD_SWORD = melee("undead_sword", p -> new UndeadSword());
        BLANKING_DAGGER = melee("blanking_dagger", p -> new BlankingDagger());
        ABYSS_WHISPER = melee("abyss_whisper", p -> new AbyssWhisper());
        BRILLIANT_GLORY = melee("brilliant_glory", p -> new BrilliantGlory());
        SHADY_DEAP = melee("shady_deap", p -> new ShadyDeap());
        GIANT_SKELETON = melee("giant_skeleton", p -> new GiantSkeleton());
        BLOOD_BINDING = melee("blood_binding", p -> new BloodBinding());
        CRYSTAL_SWORD = melee("crystal_sword", p -> new CrystalSword());
        HEAVEN_GIFT = melee("heaven_gift", p -> new HeavenGift());
        AVARICE_BLADE = melee("avarice_blade", p -> new AvariceBlade());
        TERRA_BROADSWORD = melee("terra_broadsword", p -> new TerraBroadsword());
        JAZZ_DAGGER = melee("jazz_dagger", p -> new JazzDagger());

        ELVEN_BOW = bow("elven_bow", p -> new ElvenBow());
        SUN_FLAME = bow("sun_flame", p -> new SunFlame());
        BRIGHT_PROPHECY = bow("bright_prophecy", p -> new BrightProphecy());
        DARK_CROW = bow("dark_crow", p -> new DarkCrow());
        EMERALD_WIND = bow("emerald_wind", p -> new EmeraldWind());
        POLAR_SHADOW = bow("polar_shadow", p -> new PolarShadow());
        HEAVEN_BOW = bow("heaven_bow", p -> new HeavenBow());
        FROZEN_INVASION = bow("frozen_invasion", p -> new FrozenInvasion());

        SAKURA_BLOOM = crossbow("sakura_bloom", GenericCrossbowItem::new);

        GRAVEDIGGERS_HELPER = digger("gravediggers_helper", p -> new GravediggersHelper(), ItemTags.PICKAXES);
        RADIANT_TREASURE = digger("radiant_treasure", p -> new RadiantTreasure(), ItemTags.PICKAXES);

        TRAINING_ARROW = arrow("training_arrow", p -> new GenericArrow(p.rarity(Rarity.RARE), 1.0F));
        OCEAN_ARROW = arrow("ocean_arrow", p -> new GenericArrow(p.rarity(Rarity.RARE).fireResistant(),
                new ArrowDataBuilder().damage(4.0).ignoreWater()));
        FRAGMENT_ARROW = arrow("fragment_arrow", p -> new GenericArrow(p.rarity(IRarityUtils.DARK_GREEN),
                new ArrowDataBuilder().damage(9.0).pierce((byte) 32)));
        ABYSS_ARROW = arrow("abyss_arrow", p -> new AbyssArrow());
        BLACK_FLAME_ARROW = arrow("black_flame_arrow", p -> new BlackFlameArrow());
        ENDER_ARROW = arrow("ender_arrow", p -> new EnderArrow());
        VIRTUAL_GOLD_ARROW = arrow("virtual_gold_arrow", p -> new VirtualGoldArrow());
        GUARDIAN_ARROW = arrow("guardian_arrow", p -> new GuardianArrow());
    }

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

    public static <T extends Item> ItemEntry<T> melee(String id, NonNullFunction<Item.Properties, T> factory) {
        ALL_EQUIPMENTS.add(id);
        return CelestialEquipments.REGISTRATE.item(id, factory).model((ctx, pvd) -> pvd.handheld(ctx, pvd.modLoc("item/melee/" + ctx.getName())))
                .tag(ItemTags.SWORDS, CETagGen.CELESTIAL_MELEE).register();
    }

    public static <T extends Item> ItemEntry<T> digger(String id, NonNullFunction<Item.Properties, T> factory, TagKey<Item> tag) {
        ALL_EQUIPMENTS.add(id);
        return CelestialEquipments.REGISTRATE.item(id, factory).model((ctx, pvd) -> pvd.handheld(ctx, pvd.modLoc("item/digger/" + ctx.getName())))
                .tag(tag).register();
    }

    public static <T extends Item> ItemEntry<T> bow(String id, NonNullFunction<Item.Properties, T> factory) {
        ALL_EQUIPMENTS.add(id);
        return CelestialEquipments.REGISTRATE.item(id, factory).model(ItemModelHelper::createBowModel)
                .tag(CETagGen.CELESTIAL_BOW).register();
    }

    public static <T extends Item> ItemEntry<T> crossbow(String id, NonNullFunction<Item.Properties, T> factory) {
        ALL_EQUIPMENTS.add(id);
        return CelestialEquipments.REGISTRATE.item(id, factory).model(ItemModelHelper::createCrossbowModel)
                .tag(CETagGen.CELESTIAL_CROSSBOW).register();
    }

    public static <T extends Item> Map<ArmorItem.Type, ItemEntry<T>> armors(IRegistrateHelper.ArmorNameCallback name, String path, IRegistrateHelper.ArmorTypeCallback<T> item) {
        Map<ArmorItem.Type, ItemEntry<T>> map = CelestialEquipments.REGISTRATE.armors(name, "armor/" + path + "/", item);
        map.values().forEach(ent -> ALL_EQUIPMENTS.add(ent.getId().getPath()));
        return map;
    }

    public static void register() {
    }
}