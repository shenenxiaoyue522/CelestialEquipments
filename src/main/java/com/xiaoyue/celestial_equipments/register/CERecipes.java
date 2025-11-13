package com.xiaoyue.celestial_equipments.register;

import com.tterrag.registrate.util.entry.RegistryEntry;
import com.xiaoyue.celestial_equipments.CelestialEquipments;
import com.xiaoyue.celestial_equipments.content.recipe.CEForgeRecipe;
import dev.xkmc.l2library.serial.recipe.BaseRecipe;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraftforge.registries.ForgeRegistries;

public class CERecipes {

    public static final RegistryEntry<RecipeType<CEForgeRecipe>> RT_CE_FORGE = CelestialEquipments.REGISTRATE.recipe("ce_forge_recipe");

    public static final RegistryEntry<BaseRecipe.RecType<CEForgeRecipe, CEForgeRecipe, CEForgeRecipe.Inv>> RS_CE_FORGE = CelestialEquipments.REGISTRATE
            .simple("ce_forge_recipe", ForgeRegistries.Keys.RECIPE_SERIALIZERS, () -> new BaseRecipe.RecType<>(CEForgeRecipe.class, RT_CE_FORGE));

    public static void register() {

    }
}
