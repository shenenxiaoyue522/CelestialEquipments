package com.xiaoyue.celestial_equipments.register;

import com.tterrag.registrate.util.entry.ItemEntry;
import com.tterrag.registrate.util.nullness.NonNullFunction;
import com.xiaoyue.celestial_equipments.CelestialEquipments;
import com.xiaoyue.celestial_equipments.content.builder.MeleeWeaponBuilder;
import com.xiaoyue.celestial_equipments.generic.SimpleMeleeWeapon;
import dev.xkmc.l2damagetracker.contents.attack.DamageModifier;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.entity.MobType;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;

public class CEItems {

    public static final ItemEntry<SimpleMeleeWeapon> UNDEAD_SWORD = melee("undead_sword", p -> MeleeWeaponBuilder
            .builder().onHurtTarget((a, c) -> {
                if (c.getAttackTarget().getMobType().equals(MobType.UNDEAD)) {
                    c.addHurtModifier(DamageModifier.multBase(0.3f));
                }
            }).build(p.rarity(Rarity.UNCOMMON)));

    public static <T extends Item> ItemEntry<T> melee(String id, NonNullFunction<Item.Properties, T> factory) {
        return CelestialEquipments.REGISTRATE.item(id, factory)
                .model((ctx, pvd) ->
                        pvd.handheld(UNDEAD_SWORD, CelestialEquipments.loc("item/melee/")))
                .tag(ItemTags.SWORDS)
                .register();
    }

    public static void register() {
    }
}
