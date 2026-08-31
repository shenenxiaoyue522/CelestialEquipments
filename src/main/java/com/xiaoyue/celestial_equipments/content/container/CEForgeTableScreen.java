package com.xiaoyue.celestial_equipments.content.container;

import com.xiaoyue.celestial_equipments.CelestialEquipments;
import com.xiaoyue.celestial_equipments.content.library.network.CEFMenuSyncPayload;
import com.xiaoyue.celestial_equipments.register.CEBlocks;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;

public class CEForgeTableScreen extends AbstractContainerScreen<CEForgeTableMenu> {

    public static final Component TITLE = CEBlocks.ASSEMBLY_TABLE.get().getName();
    public static final ResourceLocation TEXTURE = CelestialEquipments.loc("textures/gui/equipment_forge_table.png");

    public CEForgeTableScreen(CEForgeTableMenu pMenu, Inventory inv, Component pTitle) {
        super(pMenu, inv, pTitle);
    }

    @Override
    protected void init() {
        super.init();
        new CEFMenuSyncPayload().toServer();
    }

    @Override
    public void render(GuiGraphics pGuiGraphics, int pMouseX, int pMouseY, float pPartialTick) {
        renderBackground(pGuiGraphics);
        renderBg(pGuiGraphics, pPartialTick, pMouseX, pMouseY);
        super.render(pGuiGraphics, pMouseX, pMouseY, pPartialTick);
        renderTooltip(pGuiGraphics, pMouseX, pMouseY);
    }

    @Override
    protected void renderBg(GuiGraphics guiGraphics, float pPartialTick, int pMouseX, int pMouseY) {
        int x = this.leftPos;
        int y = (this.height - this.imageHeight) / 2;
        guiGraphics.blit(TEXTURE, x, y, 0, 0, this.imageWidth, this.imageHeight);
    }

    @Override
    public void onClose() {
        super.onClose();
        new CEFMenuSyncPayload().toServer();
    }
}
