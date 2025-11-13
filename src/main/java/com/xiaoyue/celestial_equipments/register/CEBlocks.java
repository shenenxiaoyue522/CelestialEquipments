package com.xiaoyue.celestial_equipments.register;

import com.tterrag.registrate.util.entry.BlockEntry;
import com.xiaoyue.celestial_equipments.CelestialEquipments;
import com.xiaoyue.celestial_equipments.content.container.CEForgeTableMenu;
import com.xiaoyue.celestial_invoker.content.generic.shared.HasMenuBlock;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.level.block.SoundType;

public class CEBlocks {

    public static final BlockEntry<HasMenuBlock> ASSEMBLY_TABLE;

    static {
        ASSEMBLY_TABLE = CelestialEquipments.REGISTRATE.block("assembly_table",
                prop -> new HasMenuBlock(prop.sound(SoundType.WOOD).strength(3f).ignitedByLava(), (id, inv, p) ->
                        new CEForgeTableMenu(id, inv, ContainerLevelAccess.create(p.level(), p.getOnPos()))))
                .blockstate((ctx, pvd) -> pvd.simpleBlock(ctx.get(),
                        pvd.models().cubeBottomTop(ctx.getName(), CelestialEquipments.loc("block/assembly_table_side"),
                                CelestialEquipments.loc("block/assembly_table_bottom"), CelestialEquipments.loc("block/assembly_table_top"))))
                .lang("Equipment Assembly Table").tag(BlockTags.MINEABLE_WITH_PICKAXE)
                .simpleItem().register();
    }

    public static void register() {

    }
}
