package com.xiaoyue.celestial_equipments.content.container;

import com.xiaoyue.celestial_equipments.content.recipes.CEForgeRecipe;
import com.xiaoyue.celestial_equipments.register.CEMenus;
import com.xiaoyue.celestial_equipments.register.CERecipes;
import com.xiaoyue.celestial_invoker.content.generic.shared.IControlSlotMenu;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.protocol.game.ClientboundContainerSetSlotPacket;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.Container;
import net.minecraft.world.Containers;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.LevelEvent;

public class CEForgeTableMenu extends AbstractContainerMenu implements IControlSlotMenu {

    public final ContainerLevelAccess access;
    public final CEForgeRecipe.Inv craftInv;
    public final Player player;

    public CEForgeTableMenu(int pContainerId, Inventory inv, ContainerLevelAccess access) {
        super(CEMenus.EQUIPMENT_FORGE_TABLE_MENU.get(), pContainerId);
        this.access = access;
        this.craftInv = new CEForgeRecipe.Inv();
        this.player = inv.player;
        addSlot(slot(craftInv, 0, 16, 36));
        int index = 1;
        for(int row = 0; row < 3; ++row) {
            for(int col = 0; col < 3; ++col) {
                this.addSlot(slot(this.craftInv, index++, 59 + col * 18, 18 + row * 18));
            }
        }
        addSlot(slot(craftInv, 10, 143, 36));
        addPlayerSlots(inv);
    }

    public static CEForgeTableMenu fromNetwork(MenuType<?> type, int windowId, Inventory inv, FriendlyByteBuf buf) {
        return new CEForgeTableMenu(windowId, inv, ContainerLevelAccess.NULL);
    }

    private void addPlayerSlots(Container playerInv) {
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 9; col++) {
                addSlot(new Slot(playerInv, col + row * 9 + 9, 8 + col * 18, 84 + row * 18));
            }
        }
        for (int col = 0; col < 9; col++) {
            addSlot(new Slot(playerInv, col, 8 + col * 18, 142));
        }
    }

    private void crafting(Level level) {
        if (!level.isClientSide()) {
            ItemStack result = ItemStack.EMPTY;
            CEForgeRecipe.Inv recipeInv = new CEForgeRecipe.Inv();
            for (int i = 1; i < 9; i++) {
                recipeInv.addItem(craftInv.getItem(i));
            }
            var opt = level.getRecipeManager().getRecipeFor(CERecipes.RT_CE_FORGE.get(), recipeInv, level);
            if (opt.isPresent()) {
                CEForgeRecipe recipe = opt.get();
                if (recipe.matchesInput(craftInv.getItem(0))) {
                    result = recipe.assemble(craftInv.getItem(0));
                }
            }
            craftInv.setItem(10, result.copy());
            var packet = new ClientboundContainerSetSlotPacket(this.containerId, this.incrementStateId(), 10, result);
            ((ServerPlayer) player).connection.send(packet);
        }
    }

    @Override
    public void onSlotChanged(Slot slot) {
        if (!isOutputSlot(slot.container, slot.getSlotIndex())) {
            access.execute((level, pos) -> crafting(level));
        }
    }

    private boolean isOutputSlot(Container inv, int slot) {
        return inv.equals(craftInv) && slot == 10;
    }

    @Override
    public void onTake(Slot slot, Player player, ItemStack stack) {
        if (isOutputSlot(slot.container, slot.getSlotIndex())) {
            for (int i = 0; i < craftInv.getContainerSize(); i++) {
                ItemStack invItem = craftInv.getItem(i);
                invItem.shrink(1);
                craftInv.setItem(i, invItem.copy());
            }
            player.level().levelEvent(LevelEvent.SOUND_ANVIL_USED, player.getOnPos(), 0);
        }
    }

    @Override
    public boolean mayPlace(Slot slot, ItemStack stack) {
        return !isOutputSlot(slot.container, slot.getSlotIndex());
    }

    @Override
    public int getMaxStackSize(Slot slot, ItemStack stack) {
        return 64;
    }

    @Override
    public ItemStack quickMoveStack(Player player, int index) {
        ItemStack stack = ItemStack.EMPTY;
        Slot slot = slots.get(index);
        if (slot.hasItem()) {
            ItemStack slotItem = slot.getItem();
            stack = slotItem;
            if (index >= 0 && index < 11) {
                if (!moveItemStackTo(slotItem, 11, 47, true)) {
                    return ItemStack.EMPTY;
                }
            } else if (index >= 11 && index < 38) {
                if (!moveItemStackTo(slotItem, 0, 11, false)) {
                    if (!moveItemStackTo(slotItem, 38, 47, false)) {
                        return ItemStack.EMPTY;
                    }
                }
            } else if (index >= 38 && index < 47) {
                if (!moveItemStackTo(slotItem, 0, 11, false)) {
                    if (!moveItemStackTo(slotItem, 11, 37, false)) {
                        return ItemStack.EMPTY;
                    }
                }
            }
            if (slotItem.isEmpty()) {
                slot.set(ItemStack.EMPTY);
            } else {
                slot.setChanged();
            }
            if (slotItem.getCount() == stack.getCount()) {
                stack = ItemStack.EMPTY;
            }
        }
        return stack;
    }

    @Override
    public boolean stillValid(Player player) {
        return true;
    }

    @Override
    public void removed(Player pPlayer) {
        super.removed(pPlayer);
        craftInv.setItem(10, ItemStack.EMPTY);
        Containers.dropContents(pPlayer.level(), pPlayer.getOnPos(), craftInv);
    }
}
