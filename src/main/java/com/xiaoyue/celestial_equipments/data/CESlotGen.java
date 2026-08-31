package com.xiaoyue.celestial_equipments.data;

import com.xiaoyue.celestial_equipments.CelestialEquipments;
import com.xiaoyue.celestial_invoker.invoker.tooltip.SubscribeTooltip;
import com.xiaoyue.celestial_invoker.invoker.tooltip.TooltipEntry;
import dev.xkmc.l2library.compat.curios.CurioEntityBuilder;
import dev.xkmc.l2library.compat.curios.CurioSlotBuilder;
import dev.xkmc.l2library.compat.curios.SlotCondition;
import dev.xkmc.l2library.serial.config.RecordDataProvider;
import net.minecraft.data.DataGenerator;
import net.minecraft.resources.ResourceLocation;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiConsumer;

public class CESlotGen extends RecordDataProvider {
    public CESlotGen(DataGenerator generator) {
        super(generator, "Celestial Equipments Slot Provider");
    }

    @SubscribeTooltip(key = "curios.identifier.feet")
    public static TooltipEntry feetNameTooltip = TooltipEntry.define("Feet");

    @SubscribeTooltip(key = "curios.modifiers.feet")
    public static TooltipEntry feetModifierTooltip = TooltipEntry.define("While wearing as feet: ");

    @Override
    public void add(BiConsumer<String, Record> map) {
        map.accept(CelestialEquipments.MODID + "/curios/entities/player_vanilla", new CurioEntityBuilder(
                new ArrayList<>(List.of(new ResourceLocation("player"))),
                new ArrayList<>(List.of("hands", "head")),
                SlotCondition.of()
        ));
        map.accept(CelestialEquipments.MODID + "/curios/entities/player", new CurioEntityBuilder(
                new ArrayList<>(List.of(new ResourceLocation("player"))),
                new ArrayList<>(List.of("feet")),
                SlotCondition.of()
        ));
        map.accept(CelestialEquipments.MODID + "/curios/slots/feet", new CurioSlotBuilder(180,
                new ResourceLocation("item/empty_armor_slot_boots").toString(), 1,
                CurioSlotBuilder.Operation.SET));
    }
}
