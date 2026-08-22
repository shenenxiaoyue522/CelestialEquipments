package com.xiaoyue.celestial_equipments.register;

import com.tterrag.registrate.util.entry.RegistryEntry;
import com.tterrag.registrate.util.nullness.NonNullSupplier;
import com.xiaoyue.celestial_core.content.generic.CelestialEffect;
import com.xiaoyue.celestial_equipments.CelestialEquipments;
import com.xiaoyue.celestial_equipments.content.effects.AquaFun;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;

public class CEEffects {

    public static final RegistryEntry<AquaFun> AQUA_FUN = effect("aqua_fun", AquaFun::new, "Improve swimming speed");
    public static final RegistryEntry<CelestialEffect> MORTAL_WOUND = effect("mortal_wound",
            () -> new CelestialEffect(MobEffectCategory.NEUTRAL, 0xffbb382d), "Prevents health regeneration");

    private static <T extends MobEffect> RegistryEntry<T> effect(String name, NonNullSupplier<T> sup, String desc) {
        return CelestialEquipments.EXTRA.simpleEffect(name, sup, desc).register();
    }

    public static void register() {
    }
}
