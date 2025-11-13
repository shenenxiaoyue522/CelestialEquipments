package com.xiaoyue.celestial_equipments.register;

import com.tterrag.registrate.util.entry.MenuEntry;
import com.xiaoyue.celestial_equipments.CelestialEquipments;
import com.xiaoyue.celestial_equipments.content.container.CEForgeTableMenu;
import com.xiaoyue.celestial_equipments.content.container.CEForgeTableScreen;

public class CEMenus {

    public static final MenuEntry<CEForgeTableMenu> EQUIPMENT_FORGE_TABLE_MENU;

    static {
        EQUIPMENT_FORGE_TABLE_MENU = CelestialEquipments.REGISTRATE.menu("equipment_forge_table",
                CEForgeTableMenu::fromNetwork, () -> CEForgeTableScreen::new).register();
    }

    public static void register() {

    }
}
