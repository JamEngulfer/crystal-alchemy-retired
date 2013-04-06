package com.crystals.infuser;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.ForgeDirection;

import com.crystals.CrystalFuel;
import com.crystals.recipes.InfuserRecipes;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class InfuserTileEntity extends TileEntity implements IInventory {

    private ItemStack[] inv;

    public int infuserFuelTime = 0;
    public int currentFuelTime = 0;
    public int infuseTime = 0;

    public InfuserTileEntity() {
        inv = new ItemStack[3];
    }

    @Override
    public int getSizeInventory() {
        return inv.length;
    }

    @Override
    public ItemStack getStackInSlot(int slotIndex) {
        return inv[slotIndex];
    }

    @Override
    public ItemStack decrStackSize(int slotIndex, int amount) {

        ItemStack stack = getStackInSlot(slotIndex);

        if (stack != null) {
            if (stack.stackSize <= amount) {
                setInventorySlotContents(slotIndex, null);
            } else {
                stack = stack.splitStack(amount);
                if (stack.stackSize == 0) {
                    setInventorySlotContents(slotIndex, null);
                }
            }
        }

        return stack;

    }

    @Override
    public ItemStack getStackInSlotOnClosing(int slotIndex) {

        ItemStack stack = getStackInSlot(slotIndex);

        if (stack != null) {
            setInventorySlotContents(slotIndex, null);
        }

        return stack;
    }

    @Override
    public void setInventorySlotContents(int slot, ItemStack stack) {
        inv[slot] = stack;

        if (stack != null && stack.stackSize > getInventoryStackLimit()) {
            stack.stackSize = getInventoryStackLimit();
        }

    }

    @Override
    public String getInvName() {
        return "infuser";
    }

    @Override
    public int getInventoryStackLimit() {

        return 64;
    }

    @Override
    public boolean isUseableByPlayer(EntityPlayer player) {
        return worldObj.getBlockTileEntity(xCoord, yCoord, zCoord) == this
                && player.getDistanceSq(xCoord + 0.5, yCoord + 0.5,
                        zCoord + 0.5) < 64;
    }

    @Override
    public void openChest() {
    }

    @Override
    public void closeChest() {
    }

    @Override
    public void readFromNBT(NBTTagCompound par1NBTTagCompound) {
        super.readFromNBT(par1NBTTagCompound);
        NBTTagList var2 = par1NBTTagCompound.getTagList("Items");
        inv = new ItemStack[this.getSizeInventory()];

        for (int var3 = 0; var3 < var2.tagCount(); ++var3) {
            NBTTagCompound var4 = (NBTTagCompound) var2.tagAt(var3);
            byte var5 = var4.getByte("Slot");

            if (var5 >= 0 && var5 < inv.length) {
                inv[var5] = ItemStack.loadItemStackFromNBT(var4);
            }
        }

        infuserFuelTime = par1NBTTagCompound.getShort("InfuseTime");
        infuseTime = par1NBTTagCompound.getShort("CookTime");
        currentFuelTime = getItemInfuseTime(inv[1]);
    }

    @Override
    public void writeToNBT(NBTTagCompound par1NBTTagCompound) {
        super.writeToNBT(par1NBTTagCompound);
        par1NBTTagCompound.setShort("InfuseTime", (short) infuserFuelTime);
        par1NBTTagCompound.setShort("CookTime", (short) infuseTime);
        NBTTagList var2 = new NBTTagList();

        for (int var3 = 0; var3 < inv.length; ++var3) {
            if (inv[var3] != null) {
                NBTTagCompound var4 = new NBTTagCompound();
                var4.setByte("Slot", (byte) var3);
                inv[var3].writeToNBT(var4);
                var2.appendTag(var4);
            }
        }

        par1NBTTagCompound.setTag("Items", var2);
    }

    public int getCookProgressScaled(int par1) {

        return infuseTime * par1 / 800;

    }

    @SideOnly(Side.CLIENT)
    public int getInfuseTimeRemainingScaled(int par1) {
        if (currentFuelTime == 0) {
            currentFuelTime = 800;
        }

        return infuserFuelTime * par1 / currentFuelTime;
    }

    public boolean isInfusing() {
        return infuserFuelTime > 0;
    }

    @Override
    public void updateEntity() {
        boolean var2 = false;

        if (infuserFuelTime > 0) {
            --infuserFuelTime;
        }

        if (!worldObj.isRemote) {
            if (infuserFuelTime == 0 && this.canInfuse()) {
                currentFuelTime = infuserFuelTime = getItemInfuseTime(inv[1]);

                if (infuserFuelTime > 0) {
                    var2 = true;

                    if (inv[1] != null) {
                        --inv[1].stackSize;

                        if (inv[1].stackSize == 0) {
                            inv[1] = inv[1].getItem().getContainerItemStack(
                                    inv[1]);
                        }
                    }
                }
            }

            if (this.isInfusing() && this.canInfuse()) {
                ++infuseTime;

                if (infuseTime == 800) {
                    infuseTime = 0;
                    this.infuseItem();
                    var2 = true;
                }
            } else {
                infuseTime = 0;
            }
        }

        if (var2) {
            this.onInventoryChanged();
        }
    }

    private boolean canInfuse() {
        if (inv[0] == null)
            return false;
        else {
            ItemStack var1 = InfuserRecipes.infusing()
                    .getInfusingResult(inv[0]);
            if (var1 == null)
                return false;
            if (inv[2] == null)
                return true;
            if (!inv[2].isItemEqual(var1))
                return false;
            int result = inv[2].stackSize + var1.stackSize;
            return result <= getInventoryStackLimit()
                    && result <= var1.getMaxStackSize();
        }
    }

    public void infuseItem() {
        if (this.canInfuse()) {
            ItemStack var1 = InfuserRecipes.infusing()
                    .getInfusingResult(inv[0]);

            if (inv[2] == null) {
                inv[2] = var1.copy();
            } else if (inv[2].isItemEqual(var1)) {
                inv[2].stackSize += var1.stackSize;
            }

            --inv[0].stackSize;

            if (inv[0].stackSize <= 0) {
                inv[0] = null;
            }
        }
    }

    public static int getItemInfuseTime(ItemStack par0ItemStack) {
        if (par0ItemStack == null)
            return 0;
        else
            return CrystalFuel.getInfuseTime(par0ItemStack);
    }

    public static boolean isItemFuel(ItemStack par0ItemStack) {
        return getItemInfuseTime(par0ItemStack) > 0;
    }

    public int getStartInventorySide(ForgeDirection side) {
        if (side == ForgeDirection.DOWN)
            return 1;
        if (side == ForgeDirection.UP)
            return 0;
        return 2;
    }

    public int getSizeInventorySide(ForgeDirection side) {
        return 1;
    }

    @Override
    public boolean isInvNameLocalized() {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean isStackValidForSlot(int i, ItemStack itemstack) {
        // TODO Auto-generated method stub
        return false;
    }

}
