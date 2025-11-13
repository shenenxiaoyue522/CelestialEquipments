package com.xiaoyue.celestial_equipments.compat;

import com.xiaoyue.celestial_equipments.CelestialEquipments;
import com.xiaoyue.celestial_equipments.content.container.CEForgeTableScreen;
import com.xiaoyue.celestial_equipments.register.CERecipes;
import dev.xkmc.l2library.util.Proxy;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.registration.IGuiHandlerRegistration;
import mezz.jei.api.registration.IRecipeCategoryRegistration;
import mezz.jei.api.registration.IRecipeRegistration;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.Level;

@JeiPlugin
public class CEJeiPlugin implements IModPlugin {

    public static final ResourceLocation ID = CelestialEquipments.loc("main");
    public final CEForgeRecipeCategory CE_FORGE = new CEForgeRecipeCategory();

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
        registration.addRecipes(CE_FORGE.getRecipeType(), level.getRecipeManager().getAllRecipesFor(CERecipes.RT_CE_FORGE.get()));
    }

    @Override
    public void registerGuiHandlers(IGuiHandlerRegistration registration) {
        registration.addRecipeClickArea(CEForgeTableScreen.class, 110, 32, 24, 17, CE_FORGE.getRecipeType());
    }
}
