package com.xiaoyue.celestial_equipments.compat;

import com.xiaoyue.celestial_equipments.CelestialEquipments;
import com.xiaoyue.celestial_equipments.content.container.CEForgeTableScreen;
import com.xiaoyue.celestial_equipments.register.CEBlocks;
import com.xiaoyue.celestial_equipments.register.CERecipes;
import dev.xkmc.l2library.util.Proxy;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.registration.IGuiHandlerRegistration;
import mezz.jei.api.registration.IRecipeCatalystRegistration;
import mezz.jei.api.registration.IRecipeCategoryRegistration;
import mezz.jei.api.registration.IRecipeRegistration;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.Level;

@JeiPlugin
public class CEJeiPlugin implements IModPlugin {

    public static final ResourceLocation ID = CelestialEquipments.loc("main");
    public final ForgeRecipeCategory CE_FORGE = new ForgeRecipeCategory();

    @Override
    public ResourceLocation getPluginUid() {
        return ID;
    }

    @Override
    public void registerCategories(IRecipeCategoryRegistration registration) {
        registration.addRecipeCategories(CE_FORGE.init(registration.getJeiHelpers()));
    }

    @Override
    public void registerRecipes(IRecipeRegistration registration) {
        Level level = Proxy.getClientWorld();
        assert level != null;
        registration.addRecipes(CE_FORGE.getRecipeType(), level.getRecipeManager().getAllRecipesFor(CERecipes.RT_FORGE.get()));
    }

    @Override
    public void registerRecipeCatalysts(IRecipeCatalystRegistration registration) {
        registration.addRecipeCatalyst(CEBlocks.ASSEMBLY_TABLE.asStack(), CE_FORGE.getRecipeType());
    }

    @Override
    public void registerGuiHandlers(IGuiHandlerRegistration registration) {
        registration.addRecipeClickArea(CEForgeTableScreen.class, 110, 32, 24, 17, CE_FORGE.getRecipeType());
    }
}
