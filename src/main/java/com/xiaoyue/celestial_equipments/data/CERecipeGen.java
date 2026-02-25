package com.xiaoyue.celestial_equipments.data;

import com.tterrag.registrate.providers.RegistrateRecipeProvider;
import com.xiaoyue.celestial_core.register.CCItems;
import com.xiaoyue.celestial_equipments.CelestialEquipments;
import com.xiaoyue.celestial_equipments.content.recipes.CEForgeRecipeBuilder;
import com.xiaoyue.celestial_equipments.register.CEBlocks;
import com.xiaoyue.celestial_equipments.register.CEItems;
import net.minecraft.data.recipes.RecipeBuilder;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.data.recipes.ShapelessRecipeBuilder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.ItemLike;

import static com.xiaoyue.celestial_invoker.content.common.Bindings.unlock;

public class CERecipeGen {

    public static void onRecipeGen(RegistrateRecipeProvider pvd) {
        // based
        unlock(pvd, ShapedRecipeBuilder.shaped(RecipeCategory.MISC, CEItems.REPAIR_KIT)::unlockedBy, CCItems.TREASURE_FRAGMENT.get())
                .pattern("CX").pattern("XB")
                .define('X', Items.LAPIS_LAZULI)
                .define('B', Items.QUARTZ)
                .define('C', CCItems.TREASURE_FRAGMENT)
                .save(pvd);
        unlock(pvd, ShapedRecipeBuilder.shaped(RecipeCategory.MISC, CEItems.EXP_BOTTLE_SMALL)::unlockedBy, Items.GLASS_BOTTLE)
                .pattern(" X ").pattern("BCB").pattern(" X ")
                .define('X', Items.LAPIS_LAZULI)
                .define('B', Items.DIAMOND)
                .define('C', Items.GLASS_BOTTLE)
                .save(pvd);
        unlock(pvd, ShapedRecipeBuilder.shaped(RecipeCategory.MISC, CEItems.EXP_BOTTLE_BASE)::unlockedBy, Items.GLASS_BOTTLE)
                .pattern(" X ").pattern("BCB").pattern(" X ")
                .define('X', Items.EXPERIENCE_BOTTLE)
                .define('B', CCItems.TREASURE_FRAGMENT)
                .define('C', Items.GLASS_BOTTLE)
                .save(pvd);
        unlock(pvd, ShapedRecipeBuilder.shaped(RecipeCategory.MISC, CEItems.EXP_BOTTLE_BIG)::unlockedBy, Items.GLASS_BOTTLE)
                .pattern(" X ").pattern("BCB").pattern(" X ")
                .define('X', CCItems.VIRTUAL_GOLD_NUGGET)
                .define('B', Items.DIAMOND)
                .define('C', Items.GLASS_BOTTLE)
                .save(pvd);
        unlock(pvd, ShapedRecipeBuilder.shaped(RecipeCategory.MISC, CEBlocks.ASSEMBLY_TABLE)::unlockedBy, Items.SMITHING_TABLE)
                .pattern(" X ").pattern("BCB").pattern(" X ")
                .define('X', Items.GOLD_INGOT)
                .define('B', Items.IRON_NUGGET)
                .define('C', Items.SMITHING_TABLE)
                .save(pvd);
        // arrow
        unlock(pvd, ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, CEItems.TRAINING_ARROW, 2)::unlockedBy, Items.STICK)
                .requires(Items.STICK).requires(Items.PAPER).requires(Items.PAPER).requires(Items.IRON_NUGGET).requires(Items.IRON_NUGGET)
                        .save(pvd, getID(CEItems.TRAINING_ARROW));
        unlock(pvd, ShapedRecipeBuilder.shaped(RecipeCategory.MISC, CEItems.OCEAN_ARROW, 4)::unlockedBy, Items.ARROW)
                .pattern(" X ").pattern("XAX").pattern(" X ")
                .define('X', CCItems.OCEAN_INGOT)
                .define('A', ItemTags.ARROWS)
                .save(pvd);
        unlock(pvd, ShapedRecipeBuilder.shaped(RecipeCategory.MISC, CEItems.FRAGMENT_ARROW, 4)::unlockedBy, Items.ARROW)
                .pattern(" X ").pattern("XAX").pattern(" X ")
                .define('X', CCItems.WARDEN_SCLERITE)
                .define('A', ItemTags.ARROWS)
                .save(pvd);
        unlock(pvd, ShapedRecipeBuilder.shaped(RecipeCategory.MISC, CEItems.ABYSS_ARROW, 4)::unlockedBy, Items.ARROW)
                .pattern(" X ").pattern("XAX").pattern(" X ")
                .define('X', CCItems.VOID_ESSENCE)
                .define('A', ItemTags.ARROWS)
                .save(pvd);
        unlock(pvd, ShapedRecipeBuilder.shaped(RecipeCategory.MISC, CEItems.GUARDIAN_ARROW, 4)::unlockedBy, Items.ARROW)
                .pattern(" X ").pattern("XAX").pattern(" X ")
                .define('X', CCItems.GUARDIAN_OCEAN_INGOT)
                .define('A', ItemTags.ARROWS)
                .save(pvd);
        unlock(pvd, ShapedRecipeBuilder.shaped(RecipeCategory.MISC, CEItems.VIRTUAL_GOLD_ARROW)::unlockedBy, Items.ARROW)
                .pattern(" X ").pattern("XAX").pattern(" X ")
                .define('X', CCItems.VIRTUAL_GOLD_NUGGET)
                .define('A', ItemTags.ARROWS)
                .save(pvd);
        unlock(pvd, ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, CEItems.BLACK_FLAME_ARROW, 2)::unlockedBy, Items.ARROW)
                .requires(ItemTags.ARROWS).requires(Items.TORCH).requires(Items.SOUL_SAND).requires(CCItems.DEATH_ESSENCE)
                .save(pvd);
        unlock(pvd, ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, CEItems.ENDER_ARROW)::unlockedBy, Items.ARROW)
                .requires(ItemTags.ARROWS).requires(Items.ENDER_PEARL)
                .save(pvd);
        // melee
        unlock(pvd, new CEForgeRecipeBuilder(Items.GOLDEN_SWORD, CEItems.UNDEAD_SWORD.asStack())
                .material(Items.EMERALD).material(Items.GLOWSTONE_DUST).material(Items.NETHERITE_SCRAP)::unlockedBy, Items.GOLDEN_SWORD)
                .save(pvd, getID(CEItems.UNDEAD_SWORD));
        unlock(pvd, new CEForgeRecipeBuilder(Items.IRON_SWORD, CEItems.BLANKING_DAGGER.asStack())
                .material(Items.CHARCOAL).material(Items.LAPIS_LAZULI).material(Items.ENDER_PEARL)
                .material(Items.OBSIDIAN).material(Items.IRON_INGOT)::unlockedBy, Items.IRON_SWORD)
                .save(pvd, getID(CEItems.BLANKING_DAGGER));
        unlock(pvd, new CEForgeRecipeBuilder(Items.STONE_SWORD, CEItems.ABYSS_WHISPER.asStack())
                .material(Items.NETHERITE_SCRAP).material(CCItems.TREASURE_FRAGMENT).material(CCItems.VIRTUAL_GOLD_NUGGET)
                .material(ItemTags.COALS)::unlockedBy, Items.STONE_SWORD)
                .save(pvd, getID(CEItems.ABYSS_WHISPER));
        unlock(pvd, new CEForgeRecipeBuilder(Items.STONE_SWORD, CEItems.TERRA_BROADSWORD.asStack())
                .material(Items.NETHERITE_SCRAP).material(CCItems.EARTH_CORE).material(Items.BOOK)
                .material(Items.OBSIDIAN)::unlockedBy, Items.STONE_SWORD)
                .save(pvd, getID(CEItems.TERRA_BROADSWORD));
        unlock(pvd, new CEForgeRecipeBuilder(Items.GOLDEN_SWORD, CEItems.BRIGHT_PROPHECY.asStack())
                .material(Items.GLOWSTONE).material(CCItems.FIRE_ESSENCE).material(Items.GOLD_INGOT)::unlockedBy, Items.GOLDEN_SWORD)
                .save(pvd, getID(CEItems.BRIGHT_PROPHECY));
        unlock(pvd, new CEForgeRecipeBuilder(Items.IRON_SWORD, CEItems.SHADY_DEAP.asStack())
                .material(Items.WITHER_SKELETON_SKULL).material(CCItems.TREASURE_FRAGMENT)
                .material(Items.COAL_BLOCK).material(CCItems.MIDNIGHT_FRAGMENT)::unlockedBy, Items.IRON_SWORD)
                .save(pvd, getID(CEItems.SHADY_DEAP));
        unlock(pvd, new CEForgeRecipeBuilder(Items.IRON_SWORD, CEItems.GIANT_SKELETON.asStack())
                .material(Items.BONE_BLOCK).material(Items.BONE_BLOCK).material(Items.BONE)
                .material(CCItems.DEATH_ESSENCE)::unlockedBy, Items.IRON_SWORD)
                .save(pvd, getID(CEItems.GIANT_SKELETON));
        unlock(pvd, new CEForgeRecipeBuilder(Items.IRON_SWORD, CEItems.BLOOD_BINDING.asStack())
                .material(Items.EXPERIENCE_BOTTLE).material(Items.ROTTEN_FLESH).material(CCItems.HEART_FRAGMENT)
                .material(Items.BLAZE_POWDER)::unlockedBy, Items.IRON_SWORD)
                .save(pvd, getID(CEItems.BLOOD_BINDING));
        unlock(pvd, new CEForgeRecipeBuilder(Items.GOLDEN_SWORD, CEItems.CRYSTAL_SWORD.asStack())
                .material(Items.AMETHYST_SHARD).material(Items.AMETHYST_BLOCK).material(CCItems.TREASURE_FRAGMENT)
                .material(Items.IRON_INGOT)::unlockedBy, Items.GOLDEN_SWORD)
                .save(pvd, getID(CEItems.CRYSTAL_SWORD));
        unlock(pvd, new CEForgeRecipeBuilder(Items.GOLDEN_SWORD, CEItems.HEAVEN_GIFT.asStack())
                .material(Items.GOLDEN_APPLE).material(Items.GLOWSTONE_DUST).material(CCItems.PURE_NETHER_STAR)
                .material(Items.GOLD_INGOT)::unlockedBy, Items.GOLDEN_SWORD)
                .save(pvd, getID(CEItems.HEAVEN_GIFT));
        unlock(pvd, new CEForgeRecipeBuilder(Items.GOLDEN_SWORD, CEItems.AVARICE_BLADE.asStack())
                .material(Items.GOLDEN_APPLE).material(Items.GOLD_BLOCK).material(CCItems.TREASURE_FRAGMENT)
                .material(Items.EMERALD)::unlockedBy, Items.GOLDEN_SWORD)
                .save(pvd, getID(CEItems.AVARICE_BLADE));
        unlock(pvd, new CEForgeRecipeBuilder(Items.DIAMOND_SWORD, CEItems.JAZZ_DAGGER.asStack())
                .material(Items.AMETHYST_SHARD).material(Items.BOOK).material(Items.GLOW_INK_SAC)
                .material(CCItems.TREASURE_FRAGMENT)::unlockedBy, Items.DIAMOND_SWORD)
                .save(pvd, getID(CEItems.JAZZ_DAGGER));
        // upgrade
        unlock(pvd, new CEForgeRecipeBuilder(CETagGen.UPGRADEABLE_MELEE, ItemStack.EMPTY)
                .material(Items.IRON_INGOT).material(Items.COPPER_INGOT).material(Items.COPPER_INGOT)
                .material(Items.FLINT).material(Items.GUNPOWDER).material(Items.GUNPOWDER)
                ::unlockedBy, Items.IRON_INGOT).isUpgrade(5)
                .save(pvd, CelestialEquipments.loc("upgrade/melee_upgrade_5"));
        unlock(pvd, new CEForgeRecipeBuilder(CETagGen.UPGRADEABLE_MELEE, ItemStack.EMPTY)
                .material(Items.GOLD_NUGGET).material(Items.LAPIS_LAZULI).material(Items.REDSTONE)
                .material(Items.BONE).material(Items.BLAZE_POWDER)::unlockedBy,
                Items.IRON_INGOT).isUpgrade(10)
                .save(pvd, CelestialEquipments.loc("upgrade/melee_upgrade_10"));
        unlock(pvd, new CEForgeRecipeBuilder(CETagGen.UPGRADEABLE_MELEE, ItemStack.EMPTY)
                .material(Items.GOLD_INGOT).material(Items.AMETHYST_SHARD).material(Items.BONE_BLOCK)
                .material(Items.NETHERITE_SCRAP)::unlockedBy, Items.IRON_INGOT).isUpgrade(20)
                .save(pvd, CelestialEquipments.loc("upgrade/melee_upgrade_20"));
        // bow
        unlock(pvd, new CEForgeRecipeBuilder(Items.BOW, CEItems.ELVEN_BOW.asStack())
                .material(Items.EMERALD).material(CCItems.TREASURE_FRAGMENT).material(Items.LEATHER)
                .material(Items.VINE).material(ItemTags.LEAVES)::unlockedBy, Items.BOW)
                .save(pvd, getID(CEItems.ELVEN_BOW));
        unlock(pvd, new CEForgeRecipeBuilder(Items.BOW, CEItems.SUN_FLAME.asStack())
                .material(Items.BLAZE_POWDER).material(CCItems.FIRE_ESSENCE).material(Items.OBSIDIAN)
                .material(Items.GOLD_INGOT).material(Items.LAVA_BUCKET)::unlockedBy, Items.BOW)
                .save(pvd, getID(CEItems.SUN_FLAME));
        unlock(pvd, new CEForgeRecipeBuilder(Items.BOW, CEItems.DARK_CROW.asStack())
                .material(Items.ENDER_PEARL).material(CCItems.MIDNIGHT_FRAGMENT).material(Items.LAPIS_LAZULI)
                .material(Items.QUARTZ)::unlockedBy, Items.BOW)
                .save(pvd, getID(CEItems.DARK_CROW));
        unlock(pvd, new CEForgeRecipeBuilder(Items.BOW, CEItems.EMERALD_WIND.asStack())
                .material(Items.ENDER_PEARL).material(Items.EMERALD).material(Items.GOLD_INGOT)
                .material(CCItems.TREASURE_FRAGMENT)::unlockedBy, Items.BOW)
                .save(pvd, getID(CEItems.EMERALD_WIND));
        unlock(pvd, new CEForgeRecipeBuilder(Items.BOW, CEItems.POLAR_SHADOW.asStack())
                .material(Items.FEATHER).material(Items.NETHERITE_SCRAP).material(Items.COPPER_INGOT)
                .material(CCItems.MIDNIGHT_FRAGMENT)::unlockedBy, Items.BOW)
                .save(pvd, getID(CEItems.POLAR_SHADOW));
        unlock(pvd, new CEForgeRecipeBuilder(Items.BOW, CEItems.HEAVEN_BOW.asStack())
                .material(Items.IRON_INGOT).material(Items.QUARTZ).material(Items.GOLD_INGOT)
                .material(CCItems.PURE_NETHER_STAR)::unlockedBy, Items.BOW)
                .save(pvd, getID(CEItems.HEAVEN_BOW));
        unlock(pvd, new CEForgeRecipeBuilder(Items.BOW, CEItems.FROZEN_INVASION.asStack())
                .material(Items.ICE).material(Items.QUARTZ).material(Items.DIAMOND)
                .material(CCItems.OCEAN_ESSENCE).material(Items.STRING)::unlockedBy, Items.BOW)
                .save(pvd, getID(CEItems.FROZEN_INVASION));
        // upgrade
        unlock(pvd, new CEForgeRecipeBuilder(CETagGen.UPGRADEABLE_BOW)
                .material(Items.BOOK).material(Items.GOLD_INGOT).material(Items.QUARTZ)
                .material(Items.FLINT).material(Items.GUNPOWDER)::unlockedBy, Items.GOLD_INGOT).isUpgrade(5)
                .save(pvd, CelestialEquipments.loc("upgrade/bow_upgrade_5"));
        unlock(pvd, new CEForgeRecipeBuilder(CETagGen.UPGRADEABLE_BOW)
                .material(Items.DIAMOND).material(Items.REDSTONE_BLOCK).material(Items.EXPERIENCE_BOTTLE)
                .material(Items.BOOK).material(Items.BLAZE_POWDER)::unlockedBy, Items.IRON_INGOT).isUpgrade(10)
                .save(pvd, CelestialEquipments.loc("upgrade/bow_upgrade_10"));
        unlock(pvd, new CEForgeRecipeBuilder(CETagGen.UPGRADEABLE_BOW)
                .material(Items.ENDER_PEARL).material(Items.AMETHYST_SHARD).material(CCItems.HEART_FRAGMENT)
                .material(Items.NETHERITE_SCRAP)::unlockedBy, Items.GOLD_INGOT).isUpgrade(20)
                .save(pvd, CelestialEquipments.loc("upgrade/bow_upgrade_20"));
        // crossbow
        unlock(pvd, new CEForgeRecipeBuilder(Items.CROSSBOW, CEItems.SAKURA_BLOOM.asStack())
                .material(Items.CHERRY_LEAVES).material(CCItems.SAKURA_STEEL).material(Items.VINE)
                .material(Items.EXPERIENCE_BOTTLE).material(Items.GOLD_NUGGET)::unlockedBy, Items.CROSSBOW)
                .save(pvd, getID(CEItems.SAKURA_BLOOM));
        unlock(pvd, new CEForgeRecipeBuilder(Items.CROSSBOW, CEItems.VIRTUAL_GOLD_CROSSBOW.asStack())
                .material(Items.OBSIDIAN).material(CCItems.VIRTUAL_GOLD_INGOT).material(Items.NETHERITE_SCRAP)
                .material(Items.EXPERIENCE_BOTTLE)::unlockedBy, Items.CROSSBOW)
                .save(pvd, getID(CEItems.VIRTUAL_GOLD_CROSSBOW));
        unlock(pvd, new CEForgeRecipeBuilder(Items.CROSSBOW, CEItems.SONIC_CROSSBOW.asStack())
                .material(Items.OBSIDIAN).material(Items.ECHO_SHARD).material(CCItems.MIDNIGHT_FRAGMENT)
                .material(Items.EXPERIENCE_BOTTLE)::unlockedBy, Items.CROSSBOW)
                .save(pvd, getID(CEItems.SONIC_CROSSBOW));
        // upgrade
        unlock(pvd, new CEForgeRecipeBuilder(CETagGen.UPGRADEABLE_CROSSBOW)
                .material(Items.BOOK).material(Items.STRING).material(Items.COPPER_INGOT)
                .material(Items.FLINT).material(Items.GUNPOWDER)::unlockedBy, Items.COPPER_INGOT).isUpgrade(5)
                .save(pvd, CelestialEquipments.loc("upgrade/crossbow_upgrade_5"));
        unlock(pvd, new CEForgeRecipeBuilder(CETagGen.UPGRADEABLE_CROSSBOW)
                .material(Items.LAPIS_LAZULI).material(Items.PAPER).material(Items.GOLD_BLOCK)
                .material(Items.GHAST_TEAR)::unlockedBy, Items.LAPIS_LAZULI).isUpgrade(10)
                .save(pvd, CelestialEquipments.loc("upgrade/crossbow_upgrade_10"));
        unlock(pvd, new CEForgeRecipeBuilder(CETagGen.UPGRADEABLE_CROSSBOW)
                .material(Items.ENDER_PEARL).material(Items.AMETHYST_SHARD).material(CCItems.MIDNIGHT_FRAGMENT)
                .material(Items.PHANTOM_MEMBRANE)::unlockedBy, Items.AMETHYST_SHARD).isUpgrade(20)
                .save(pvd, CelestialEquipments.loc("upgrade/crossbow_upgrade_20"));
        // digger
        unlock(pvd, new CEForgeRecipeBuilder(Items.STONE_PICKAXE, CEItems.GRAVEDIGGERS_HELPER.asStack())
                .material(Items.ENDER_PEARL).material(Items.IRON_INGOT).material(Items.REDSTONE)
                .material(Items.INK_SAC)::unlockedBy, Items.STONE_PICKAXE)
                .save(pvd, getID(CEItems.GRAVEDIGGERS_HELPER));
        unlock(pvd, new CEForgeRecipeBuilder(Items.GOLDEN_PICKAXE, CEItems.RADIANT_TREASURE.asStack())
                .material(Items.GOLD_BLOCK).material(Items.DIAMOND).material(Items.EXPERIENCE_BOTTLE)
                .material(CCItems.LIGHT_FRAGMENT)::unlockedBy, Items.GOLDEN_PICKAXE)
                .save(pvd, getID(CEItems.RADIANT_TREASURE));
        unlock(pvd, new CEForgeRecipeBuilder(Items.NETHERITE_AXE, CEItems.ENDER_THROWING_AXE.asStack())
                .material(Items.ENDER_PEARL).material(Items.CHORUS_FRUIT).material(Items.EXPERIENCE_BOTTLE)
                .material(CCItems.MIDNIGHT_FRAGMENT)::unlockedBy, Items.NETHERITE_AXE)
                .save(pvd, getID(CEItems.ENDER_THROWING_AXE));
        // upgrade
        unlock(pvd, new CEForgeRecipeBuilder(CETagGen.UPGRADEABLE_DIGGER)
                .material(Items.STONE_BRICKS).material(Items.COAL).material(Items.COPPER_INGOT)
                .material(Items.FLINT).material(Items.GUNPOWDER)::unlockedBy, Items.COPPER_INGOT).isUpgrade(5)
                .save(pvd, CelestialEquipments.loc("upgrade/digger_upgrade_5"));
        unlock(pvd, new CEForgeRecipeBuilder(CETagGen.UPGRADEABLE_DIGGER)
                .material(Items.REDSTONE_BLOCK).material(Items.BRICK).material(Items.BLACKSTONE)
                .material(Items.BLAZE_POWDER)::unlockedBy, Items.REDSTONE_BLOCK).isUpgrade(10)
                .save(pvd, CelestialEquipments.loc("upgrade/digger_upgrade_10"));
        unlock(pvd, new CEForgeRecipeBuilder(CETagGen.UPGRADEABLE_DIGGER)
                .material(Items.DIAMOND).material(Items.REDSTONE_BLOCK).material(CCItems.LIGHT_FRAGMENT)
                .material(Items.EXPERIENCE_BOTTLE)::unlockedBy, Items.DIAMOND).isUpgrade(20)
                .save(pvd, CelestialEquipments.loc("upgrade/digger_upgrade_20"));
        // trident
        unlock(pvd, new CEForgeRecipeBuilder(Items.IRON_INGOT, CEItems.OCEAN_TIDE.asStack())
                .material(Items.DIAMOND).material(Items.HEART_OF_THE_SEA).material(Items.LAPIS_LAZULI)
                .material(Items.ENCHANTED_BOOK)::unlockedBy, Items.IRON_INGOT)
                .save(pvd, getID(CEItems.OCEAN_TIDE));
        // upgrade
        unlock(pvd, new CEForgeRecipeBuilder(CETagGen.UPGRADEABLE_TRIDENTS)
                .material(Items.PUFFERFISH).material(Items.IRON_INGOT).material(Items.SUGAR)
                .material(Items.FLINT)::unlockedBy, Items.PUFFERFISH).isUpgrade(5)
                .save(pvd, CelestialEquipments.loc("upgrade/trident_upgrade_5"));
        unlock(pvd, new CEForgeRecipeBuilder(CETagGen.UPGRADEABLE_TRIDENTS)
                .material(Items.TROPICAL_FISH).material(Items.IRON_BLOCK).material(Items.GOLD_INGOT)
                .material(Items.GLASS)::unlockedBy, Items.TROPICAL_FISH).isUpgrade(10)
                .save(pvd, CelestialEquipments.loc("upgrade/trident_upgrade_10"));
        unlock(pvd, new CEForgeRecipeBuilder(CETagGen.UPGRADEABLE_TRIDENTS)
                .material(Items.DIAMOND).material(Items.BOOK).material(Items.SANDSTONE)
                .material(Items.NAUTILUS_SHELL)::unlockedBy, Items.DIAMOND).isUpgrade(20)
                .save(pvd, CelestialEquipments.loc("upgrade/trident_upgrade_20"));
    }

    public static ResourceLocation getID(ItemLike item, String path) {
        ResourceLocation id = RecipeBuilder.getDefaultRecipeId(item);
        return id.withPath(id.getPath() + path);
    }

    public static ResourceLocation getID(ItemLike item) {
        return getID(item, "");
    }
}
