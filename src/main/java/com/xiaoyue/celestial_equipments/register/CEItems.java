package com.xiaoyue.celestial_equipments.register;

import com.tterrag.registrate.providers.DataGenContext;
import com.tterrag.registrate.providers.RegistrateItemModelProvider;
import com.tterrag.registrate.util.entry.ItemEntry;
import com.tterrag.registrate.util.nullness.NonNullFunction;
import com.xiaoyue.celestial_core.data.CCDamageTypes;
import com.xiaoyue.celestial_core.register.CCEffects;
import com.xiaoyue.celestial_core.utils.EntityUtils;
import com.xiaoyue.celestial_core.utils.IRarityUtils;
import com.xiaoyue.celestial_equipments.CelestialEquipments;
import com.xiaoyue.celestial_equipments.content.generic.SimpleMeleeWeapon;
import com.xiaoyue.celestial_equipments.content.generic.builder.MeleeWeaponBuilder;
import com.xiaoyue.celestial_invoker.content.config.ConfigHolderEntry;
import com.xiaoyue.celestial_invoker.content.config.value.DoubleConfigEntry;
import com.xiaoyue.celestial_invoker.content.tooltip.SubscribeTooltip;
import com.xiaoyue.celestial_invoker.content.tooltip.TooltipEntry;
import dev.xkmc.l2damagetracker.contents.attack.DamageModifier;
import dev.xkmc.l2library.init.events.GeneralEventHandler;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.MobType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.BowItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import net.minecraftforge.client.model.generators.ItemModelBuilder;
import net.minecraftforge.client.model.generators.ModelFile;

import static com.xiaoyue.celestial_equipments.CelestialEquipments.MODID;

public class CEItems {

    @ConfigHolderEntry(category = "undead_sword")
    public static final DoubleConfigEntry UNDEAD_SWORD_DAMAGE_BONUS = DoubleConfigEntry
            .define("Undead Sword Damage Bonus", 0.3, 0.1, 10, "Bonus damage multiplier");

    @SubscribeTooltip(id = "undead_sword")
    public static final TooltipEntry UNDEAD_SWORD_TOOLTIP = TooltipEntry.define(
            "Increases damage by %s when attacking undead mobs",
            TooltipEntry.per(UNDEAD_SWORD_DAMAGE_BONUS.get()));

    public static final ItemEntry<SimpleMeleeWeapon> UNDEAD_SWORD = melee("undead_sword", p -> MeleeWeaponBuilder
            .builder().defaultDamage(7f).defaultSpeed(1.6f)
            .info((i, l) -> l.add(UNDEAD_SWORD_TOOLTIP.withGray()))
            .onHurtTarget((a, c) -> {
                if (c.getAttackTarget().getMobType().equals(MobType.UNDEAD)) {
                    c.addHurtModifier(DamageModifier.multBase(UNDEAD_SWORD_DAMAGE_BONUS.floatValue()));
                }
            }).build(p.rarity(Rarity.UNCOMMON).durability(1750)));

    @ConfigHolderEntry(category = "abyss_whisper")
    public static final DoubleConfigEntry ABYSS_WHISPER_DAMAGE_MULTIPLIER = DoubleConfigEntry
            .define("Abyss Whisper Damage Multiplier", 0.5, 0.01, 10, "Extra damage multiplier");

    @SubscribeTooltip(id = "abyss_whisper")
    public static final TooltipEntry ABYSS_WHISPER_TOOLTIP = TooltipEntry.define(
            "Deals an additional %s Abyss Damage after attacking",
            TooltipEntry.per(ABYSS_WHISPER_DAMAGE_MULTIPLIER.get()));

    public static final ItemEntry<SimpleMeleeWeapon> ABYSS_WHISPER = melee("abyss_whisper", p -> MeleeWeaponBuilder
            .builder().defaultDamage(6f).defaultSpeed(1.6f)
            .info((i, l) -> l.add(ABYSS_WHISPER_TOOLTIP.withGray()))
            .onHurtTarget((a, c) -> GeneralEventHandler.schedule(() -> {
                if (a instanceof Player r && EntityUtils.isFullCharged(r)) {
                    c.getAttackTarget().hurt(CCDamageTypes.abyss(a), c.getPreDamage() * ABYSS_WHISPER_DAMAGE_MULTIPLIER.floatValue());
                }
            })).build(p.rarity(IRarityUtils.DARK_AQUA).durability(1500)));

    @ConfigHolderEntry(category = "giant_skeleton")
    public static final DoubleConfigEntry GIANT_SKELETON_DAMAGE_MULTIPLIER = DoubleConfigEntry
            .define("Giant Skeleton Damage Multiplier", 0.02, 0.01, 1, "Adds damage multiplier");

    @SubscribeTooltip(id = "giant_skeleton")
    public static final TooltipEntry GIANT_SKELETON_TOOLTIP = TooltipEntry.define(
            "Attacks deal an additional %s damage to the target's maximum health",
            TooltipEntry.per(GIANT_SKELETON_DAMAGE_MULTIPLIER.get()));

    public static final ItemEntry<SimpleMeleeWeapon> GIANT_SKELETON = melee("giant_skeleton", p -> MeleeWeaponBuilder
            .builder().defaultDamage(9f).defaultSpeed(1.2f)
            .info((i, l) -> l.add(GIANT_SKELETON_TOOLTIP.withGray()))
            .onHurtTarget((a, c) -> {
                if (a instanceof Player r && EntityUtils.isFullCharged(r)) {
                    float bonus = c.getAttackTarget().getMaxHealth() * GIANT_SKELETON_DAMAGE_MULTIPLIER.floatValue();
                    c.addHurtModifier(DamageModifier.add(bonus));
                }
            }).build(p.rarity(Rarity.UNCOMMON).durability(2200)));

    @SubscribeTooltip(id = "crystal_longsword")
    public static final TooltipEntry CRYSTAL_LONGSWORD_TOOLTIP = TooltipEntry.define(
            "Briefly increases Critical Rate and Critical Damage after attacking");

    public static final ItemEntry<SimpleMeleeWeapon> CRYSTAL_LONGSWORD = melee("crystal_longsword", p -> MeleeWeaponBuilder
            .builder().defaultDamage(6.5f).defaultSpeed(1.8f)
            .info((i, l) -> l.add(CRYSTAL_LONGSWORD_TOOLTIP.withGray()))
            .effectOnTarget(new MobEffectInstance(CCEffects.CRIT_RATE.get(), 200, 0),
                    new MobEffectInstance(CCEffects.CRIT_DAMAGE.get(), 200, 0))
            .build(p.rarity(Rarity.EPIC).durability(1450)));

    public static <T extends Item> ItemEntry<T> melee(String id, NonNullFunction<Item.Properties, T> factory) {
        return CelestialEquipments.REGISTRATE.item(id, factory).model((ctx, pvd) ->
                        pvd.handheld(UNDEAD_SWORD, CelestialEquipments.loc("item/melee/" + ctx.getName())))
                .tag(ItemTags.SWORDS)
                .register();
    }

    private static final float[] BOW_PULL_VALS = {0f, 0.65f, 0.9f};

    public static <T extends BowItem> void createBowModel(DataGenContext<Item, T> ctx, RegistrateItemModelProvider pvd) {
        ItemModelBuilder builder = pvd.withExistingParent(ctx.getName(), "minecraft:bow");
        builder.texture("layer0", "item/bow/" + ctx.getName() + "/bow");
        for (int i = 0; i < 3; i++) {
            String name = ctx.getName() + "/bow_pulling_" + i;
            ItemModelBuilder ret = pvd.getBuilder("item/bow/" + name).parent(new ModelFile.UncheckedModelFile("minecraft:item/bow_pulling_" + i));
            ret.texture("layer0", "item/bow/" + name);
            ItemModelBuilder.OverrideBuilder override = builder.override();
            override.predicate(new ResourceLocation("pulling"), 1);
            if (BOW_PULL_VALS[i] > 0)
                override.predicate(new ResourceLocation("pull"), BOW_PULL_VALS[i]);
            override.model(new ModelFile.UncheckedModelFile(MODID + ":item/bow/" + name));
        }
    }

    public static void register() {
    }
}
