package com.xiaoyue.celestial_equipments.data;

import com.tterrag.registrate.providers.RegistrateRecipeProvider;
import com.xiaoyue.celestial_core.register.CCItems;
import com.xiaoyue.celestial_equipments.CelestialEquipments;
import com.xiaoyue.celestial_equipments.content.recipe.CEForgeRecipeBuilder;
import com.xiaoyue.celestial_equipments.register.CEBlocks;
import com.xiaoyue.celestial_equipments.register.CEItems;
import net.minecraft.data.recipes.RecipeBuilder;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.ItemLike;

import static com.xiaoyue.celestial_invoker.content.ancillary.BindingHandler.unlock;

public class CERecipeGen {

    public static void onRecipeGen(RegistrateRecipeProvider pvd) {
        // based
        unlock(pvd, ShapedRecipeBuilder.shaped(RecipeCategory.MISC, CEBlocks.ASSEMBLY_TABLE)::unlockedBy, Items.SMITHING_TABLE)
                .pattern(" X ").pattern("BCB").pattern(" X ")
                .define('X', Items.GOLD_INGOT)
                .define('B', Items.IRON_NUGGET)
                .define('C', Items.SMITHING_TABLE)
                .save(pvd);
        // melee
        unlock(pvd, new CEForgeRecipeBuilder(Items.GOLDEN_SWORD, CEItems.UNDEAD_SWORD.asStack())
                .material(Items.EMERALD).material(Items.GLOWSTONE_DUST).material(Items.NETHERITE_SCRAP)::unlockedBy, Items.GOLDEN_SWORD)
                .save(pvd, getID(CEItems.UNDEAD_SWORD));
        unlock(pvd, new CEForgeRecipeBuilder(Items.IRON_SWORD, CEItems.BLANKING_DAGGER.asStack())
                .material(Items.CHARCOAL).material(Items.LAPIS_LAZULI).material(Items.ENDER_PEARL)::unlockedBy, Items.IRON_SWORD)
                .save(pvd, getID(CEItems.BLANKING_DAGGER));
        unlock(pvd, new CEForgeRecipeBuilder(Items.STONE_SWORD, CEItems.ABYSS_ARROW.asStack())
                .material(Items.NETHERITE_SCRAP).material(CCItems.TREASURE_FRAGMENT).material(Items.ENDER_EYE)
                .material(ItemTags.COALS)::unlockedBy, Items.STONE_SWORD)
                .save(pvd, getID(CEItems.ABYSS_WHISPER));
        unlock(pvd, new CEForgeRecipeBuilder(Items.STONE_SWORD, CEItems.TERRA_BROADSWORD.asStack())
                .material(Items.NETHERITE_SCRAP).material(CCItems.EARTH_CORE).material(Items.BOOK)
                .material(Items.OBSERVER)::unlockedBy, Items.STONE_SWORD)
                .save(pvd, getID(CEItems.TERRA_BROADSWORD));
        unlock(pvd, new CEForgeRecipeBuilder(Items.GOLDEN_SWORD, CEItems.BRIGHT_PROPHECY.asStack())
                .material(Items.GLOWSTONE).material(CCItems.FIRE_ESSENCE).material(Items.GOLD_INGOT)::unlockedBy, Items.GOLDEN_SWORD)
                .save(pvd, getID(CEItems.BRIGHT_PROPHECY));
        unlock(pvd, new CEForgeRecipeBuilder(Items.IRON_SWORD, CEItems.SHADY_DEAP.asStack())
                .material(Items.WITHER_SKELETON_SKULL).material(CCItems.TREASURE_FRAGMENT)::unlockedBy, Items.IRON_SWORD)
                .save(pvd, getID(CEItems.SHADY_DEAP));
        unlock(pvd, new CEForgeRecipeBuilder(Items.IRON_SWORD, CEItems.GIANT_SKELETON.asStack())
                .material(Items.BONE_BLOCK).material(Items.BONE_BLOCK).material(Items.BONE)
                .material(CCItems.DEATH_ESSENCE)::unlockedBy, Items.IRON_SWORD)
                .save(pvd, getID(CEItems.GIANT_SKELETON));
        unlock(pvd, new CEForgeRecipeBuilder(Items.IRON_SWORD, CEItems.BLOOD_BINDING.asStack())
                .material(Items.EXPERIENCE_BOTTLE).material(Items.ROTTEN_FLESH).material(Items.ROTTEN_FLESH)
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
                .material(Items.AMETHYST_SHARD).material(Items.BOOK)
                .material(CCItems.TREASURE_FRAGMENT)::unlockedBy, Items.DIAMOND_SWORD)
                .save(pvd, getID(CEItems.JAZZ_DAGGER));
        // upgrade
        unlock(pvd, new CEForgeRecipeBuilder(CETagGen.CELESTIAL_MELEE, ItemStack.EMPTY)
                .material(Items.IRON_INGOT).material(Items.COPPER_INGOT).material(Items.COPPER_INGOT)
                .material(Items.FLINT).material(Items.GUNPOWDER).material(Items.GUNPOWDER)
                ::unlockedBy, Items.IRON_INGOT).isUpgrade(5)
                .save(pvd, CelestialEquipments.loc("upgrade/melee_upgrade_5"));
        unlock(pvd, new CEForgeRecipeBuilder(CETagGen.CELESTIAL_MELEE, ItemStack.EMPTY)
                .material(Items.DIAMOND).material(Items.LAPIS_LAZULI).material(Items.REDSTONE)
                .material(Items.BONE).material(Items.BLAZE_POWDER)::unlockedBy,
                Items.IRON_INGOT).isUpgrade(15)
                .save(pvd, CelestialEquipments.loc("upgrade/melee_upgrade_15"));
        unlock(pvd, new CEForgeRecipeBuilder(CETagGen.CELESTIAL_MELEE, ItemStack.EMPTY)
                .material(Items.GOLD_INGOT).material(Items.AMETHYST_SHARD).material(CCItems.DEATH_ESSENCE)
                .material(Items.NETHERITE_SCRAP)::unlockedBy, Items.IRON_INGOT).isUpgrade(20)
                .save(pvd, CelestialEquipments.loc("upgrade/melee_upgrade_20"));
        // bow
        unlock(pvd, new CEForgeRecipeBuilder(Items.BOW, CEItems.ELVEN_BOW.asStack())
                .material(Items.EMERALD).material(CCItems.TREASURE_FRAGMENT).material(Items.LEATHER)::unlockedBy, Items.BOW)
                .save(pvd, getID(CEItems.ELVEN_BOW));
        unlock(pvd, new CEForgeRecipeBuilder(Items.BOW, CEItems.SUN_FLAME.asStack())
                .material(Items.BLAZE_POWDER).material(CCItems.FIRE_ESSENCE).material(Items.OBSERVER)
                .material(Items.GOLD_INGOT)::unlockedBy, Items.BOW)
                .save(pvd, getID(CEItems.SUN_FLAME));
        unlock(pvd, new CEForgeRecipeBuilder(Items.BOW, CEItems.DARK_CROW.asStack())
                .material(Items.ENDER_PEARL).material(CCItems.MIDNIGHT_FRAGMENT).material(Items.LAPIS_LAZULI)
                .material(Items.QUARTZ)::unlockedBy, Items.BOW)
                .save(pvd, getID(CEItems.DARK_CROW));
        unlock(pvd, new CEForgeRecipeBuilder(Items.BOW, CEItems.EMERALD_WIND.asStack())
                .material(Items.ENDER_PEARL).material(Items.NETHERITE_SCRAP).material(Items.DIAMOND)
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
                .material(CCItems.OCEAN_ESSENCE)::unlockedBy, Items.BOW)
                .save(pvd, getID(CEItems.FROZEN_INVASION));
        // upgrade
        unlock(pvd, new CEForgeRecipeBuilder(CETagGen.CELESTIAL_BOW, ItemStack.EMPTY)
                .material(Items.BOOK).material(Items.GOLD_INGOT).material(Items.QUARTZ)
                .material(Items.FLINT).material(Items.GUNPOWDER)::unlockedBy, Items.GOLD_INGOT).isUpgrade(5)
                .save(pvd, CelestialEquipments.loc("upgrade/bow_upgrade_5"));
        unlock(pvd, new CEForgeRecipeBuilder(CETagGen.CELESTIAL_BOW, ItemStack.EMPTY)
                .material(Items.DIAMOND).material(Items.REDSTONE_BLOCK).material(Items.EXPERIENCE_BOTTLE)
                .material(Items.BOOK).material(Items.BLAZE_POWDER)::unlockedBy, Items.IRON_INGOT).isUpgrade(15)
                .save(pvd, CelestialEquipments.loc("upgrade/bow_upgrade_15"));
        unlock(pvd, new CEForgeRecipeBuilder(CETagGen.CELESTIAL_BOW, ItemStack.EMPTY)
                .material(Items.ENDER_PEARL).material(Items.AMETHYST_SHARD).material(CCItems.HEART_FRAGMENT)
                .material(Items.NETHERITE_SCRAP)::unlockedBy, Items.GOLD_INGOT).isUpgrade(20)
                .save(pvd, CelestialEquipments.loc("upgrade/bow_upgrade_20"));
    }

    public static ResourceLocation getID(ItemLike item, String path) {
        ResourceLocation id = RecipeBuilder.getDefaultRecipeId(item);
        return id.withPath(id.getPath() + path);
    }

    public static ResourceLocation getID(ItemLike item) {
        return getID(item, "");
    }
}
