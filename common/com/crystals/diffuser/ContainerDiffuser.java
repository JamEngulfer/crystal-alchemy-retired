package com.crystals.diffuser;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ICrafting;
import net.minecraft.inventory.Slot;
import net.minecraft.inventory.SlotFurnace;
import net.minecraft.item.ItemStack;

import com.crystals.recipes.InfuserRecipes;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ContainerDiffuser extends Container {

    protected DiffuserTileEntity tile_entity;
    private int lastCookTime = 0;
    private int lastInfuseTime = 0;
    private int lastItemInfuseTime = 0;

    public ContainerDiffuser(DiffuserTileEntity tile_entity,
            InventoryPlayer player_inventory) {
        this.tile_entity = tile_entity;
        this.addSlotToContainer(new Slot(tile_entity, 0, 56, 17));
        this.addSlotToContainer(new Slot(tile_entity, 1, 56, 53));
        this.addSlotToContainer(new Slot(tile_entity, 3, 33, 35));
        this.addSlotToContainer(new SlotFurnace(player_inventory.player,
                tile_entity, 2, 116, 35));
        int var3;

        for (var3 = 0; var3 < 3; ++var3) {
            for (int var4 = 0; var4 < 9; ++var4) {
                this.addSlotToContainer(new Slot(player_inventory, var4 + var3
                        * 9 + 9, 8 + var4 * 18, 84 + var3 * 18));
            }
        }

        for (var3 = 0; var3 < 9; ++var3) {
            this.addSlotToContainer(new Slot(player_inventory, var3,
                    8 + var3 * 18, 142));
        }

    }

    @Override
    public void addCraftingToCrafters(ICrafting par1ICrafting) {
        super.addCraftingToCrafters(par1ICrafting);
        par1ICrafting.sendProgressBarUpdate(this, 0, tile_entity.infuseTime);
        par1ICrafting.sendProgressBarUpdate(this, 1,
                tile_entity.infuserFuelTime);
        par1ICrafting.sendProgressBarUpdate(this, 2,
                tile_entity.currentFuelTime);
    }

    @Override
    public void detectAndSendChanges() {
        super.detectAndSendChanges();

        for (int var1 = 0; var1 < crafters.size(); ++var1) {
            ICrafting var2 = (ICrafting) crafters.get(var1);

            if (lastCookTime != tile_entity.infuseTime) {
                var2.sendProgressBarUpdate(this, 0, tile_entity.infuseTime);
            }

            if (lastInfuseTime != tile_entity.infuserFuelTime) {
                var2.sendProgressBarUpdate(this, 1, tile_entity.infuserFuelTime);
            }

            if (lastItemInfuseTime != tile_entity.currentFuelTime) {
                var2.sendProgressBarUpdate(this, 2, tile_entity.currentFuelTime);
            }
        }

        lastCookTime = tile_entity.infuseTime;
        lastInfuseTime = tile_entity.infuserFuelTime;
        lastItemInfuseTime = tile_entity.currentFuelTime;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void updateProgressBar(int par1, int par2) {
        if (par1 == 0) {
            tile_entity.infuseTime = par2;
        }

        if (par1 == 1) {
            tile_entity.infuserFuelTime = par2;
        }

        if (par1 == 2) {
            tile_entity.currentFuelTime = par2;
        }
    }

    @Override
    public boolean canInteractWith(EntityPlayer par1EntityPlayer) {
        return tile_entity.isUseableByPlayer(par1EntityPlayer);
    }

    @Override
    public ItemStack transferStackInSlot(EntityPlayer par1EntityPlayer, int par2) {
        ItemStack var3 = null;
        Slot var4 = (Slot) inventorySlots.get(par2);

        if (var4 != null && var4.getHasStack()) {
            ItemStack var5 = var4.getStack();
            var3 = var5.copy();

            if (par2 == 2) {
                if (!this.mergeItemStack(var5, 3, 39, true))
                    return null;

                var4.onSlotChange(var5, var3);
            } else if (par2 != 1 && par2 != 0) {
                if (InfuserRecipes.infusing().getInfusingResult(var5) != null) {
                    if (!this.mergeItemStack(var5, 0, 1, false))
                        return null;
                } else if (DiffuserTileEntity.isItemFuel(var5)) {
                    if (!this.mergeItemStack(var5, 1, 2, false))
                        return null;
                } else if (par2 >= 3 && par2 < 30) {
                    if (!this.mergeItemStack(var5, 30, 39, false))
                        return null;
                } else if (par2 >= 30 && par2 < 39
                        && !this.mergeItemStack(var5, 3, 30, false))
                    return null;
            } else if (!this.mergeItemStack(var5, 3, 39, false))
                return null;

            if (var5.stackSize == 0) {
                var4.putStack((ItemStack) null);
            } else {
                var4.onSlotChanged();
            }

            if (var5.stackSize == var3.stackSize)
                return null;

            var4.onPickupFromSlot(par1EntityPlayer, var5);
        }

        return var3;
    }

}
