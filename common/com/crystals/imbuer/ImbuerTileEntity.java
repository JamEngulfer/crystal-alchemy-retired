package com.crystals.imbuer;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.ForgeDirection;

import com.crystals.CrystalFuel;
import com.crystals.CrystalMod;
import com.crystals.essence.Essence;
import com.crystals.essence.EssenceHandler;
import com.crystals.recipes.ImbuerRecipes;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ImbuerTileEntity extends TileEntity implements IInventory {

    private ItemStack[] inv;

    public int imbuerFuelTime = 0;
    public int currentFuelTime = 0;
    public int imbueTime = 0;

    public ImbuerTileEntity() {
        inv = new ItemStack[4];
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
        return "imbuer";
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

        imbuerFuelTime = par1NBTTagCompound.getShort("ImbueTime");
        imbueTime = par1NBTTagCompound.getShort("CookTime");
        currentFuelTime = getItemImbueTime(inv[1]);
    }

    @Override
    public void writeToNBT(NBTTagCompound par1NBTTagCompound) {
        super.writeToNBT(par1NBTTagCompound);
        par1NBTTagCompound.setShort("ImbueTime", (short) imbuerFuelTime);
        par1NBTTagCompound.setShort("CookTime", (short) imbueTime);
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

        return imbueTime * par1 / 600;

    }

    @SideOnly(Side.CLIENT)
    public int getImbueTimeRemainingScaled(int par1) {
        if (currentFuelTime == 0) {
            currentFuelTime = 600;
        }

        return imbuerFuelTime * par1 / currentFuelTime;
    }

    @Override
    public void updateEntity() {
        boolean var2 = false;

        if (imbuerFuelTime > 0) {
            --imbuerFuelTime;
        }

        if (!worldObj.isRemote) {
            if (imbuerFuelTime == 0 && this.canImbue()) {
                currentFuelTime = imbuerFuelTime = getItemImbueTime(inv[1]);

                if (imbuerFuelTime > 0) {
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

            if (this.isImbuing() && this.canImbue()) {
                ++imbueTime;

                if (imbueTime == 600) {
                    imbueTime = 0;
                    this.imbueItem();
                    var2 = true;
                }
            } else {
                imbueTime = 0;
            }
        }

        if (var2) {
            this.onInventoryChanged();
        }
    }

    public boolean isImbuing() {
        return imbuerFuelTime > 0;
    }

    private boolean canImbue() {
        if (inv[0] == null || inv[3] == null)
            return false;
        else if (inv[3].itemID != CrystalMod.InertCrystal.itemID)
            return false;
        else {
            if(inv[3].itemID == CrystalMod.InertCrystal.itemID && inv[0].itemID == CrystalMod.InertCrystal.itemID){
                ItemStack result = ImbuerRecipes.infusing().getCrystalInfusingResult(inv[0].getItemDamage(), inv[3].getItemDamage());
                if(result != null){
                    return true;
                } else {
                    return false;
                }
            } else {
                ItemStack var1 = ImbuerRecipes.infusing().getInfusingResult(inv[0]);
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
    }

    public void imbueItem() {
        if (this.canImbue()) {
            if((inv[3].itemID == CrystalMod.InertCrystal.itemID && inv[0].itemID == CrystalMod.InertCrystal.itemID) && ImbuerRecipes.infusing().getCrystalInfusingResult(inv[0].getItemDamage(), inv[3].getItemDamage()) != null){
                ItemStack var1 = ImbuerRecipes.infusing().getCrystalInfusingResult(inv[0].getItemDamage(), inv[3].getItemDamage());

                if (inv[2] == null) {
                    inv[2] = var1.copy();
                } else if (inv[2].isItemEqual(var1)) {
                    inv[2].stackSize += var1.stackSize;
                }

                --inv[0].stackSize;
                --inv[3].stackSize;

                if (inv[0].stackSize <= 0) {
                    inv[0] = null;
                }

                if (inv[3].stackSize <= 0) {
                    inv[3] = null;
                }
            } else {
                ItemStack var1 = ImbuerRecipes.infusing().getInfusingResult(inv[0]);
                ItemStack crystal = inv[3];
                ItemStack operative = inv[0];

                if (inv[2] == null) {
                    inv[2] = var1.copy();
                } else if (inv[2].isItemEqual(var1)) {
                    inv[2].stackSize += var1.stackSize;
                }

                --inv[0].stackSize;
                --inv[3].stackSize;

                if (inv[0].stackSize <= 0) {
                    inv[0] = null;
                }

                if (inv[3].stackSize <= 0) {
                    inv[3] = null;
                }

                if (crystal.itemID == CrystalMod.InertCrystal.itemID
                        && operative.itemID == CrystalMod.CrystalSword.itemID) {
                    // ItemStack item = this.inv[3];
                    int meta = crystal.getItemDamage();

                    if (meta == 1) {
                      //Essence essence = Essence.essenceList[0];
                      //EssenceHandler.addEssence(essence, inv[2]);
                    }

                    if (meta == 2) {
                        Essence essence = Essence.essenceList[2];
                        EssenceHandler.addEssence(essence, inv[2]);
                    }

                    if (meta == 3) {
                        Essence essence = Essence.essenceList[1];
                        EssenceHandler.addEssence(essence, inv[2]);
                    }
                    
                    if (meta == 4) {
                        //Essence essence = Essence.essenceList[1];
                        //EssenceHandler.addEssence(essence, inv[2]);
                    }
                    
                    if (meta == 5) {
                        //Essence essence = Essence.essenceList[1];
                        //EssenceHandler.addEssence(essence, inv[2]);
                    }
                    
                    if (meta == 6) {
                        Essence essence = Essence.essenceList[3];
                        EssenceHandler.addEssence(essence, inv[2]);
                    }
                    
                    if (meta == 7) {
                        Essence essence = Essence.essenceList[0];
                        EssenceHandler.addEssence(essence, inv[2]);
                    }
                    
                    if (meta == 8) {
                        //Essence essence = Essence.essenceList[1];
                        //EssenceHandler.addEssence(essence, inv[2]);
                    }
                    
                    if (meta == 9) {
                        //Essence essence = Essence.essenceList[5];
                        //EssenceHandler.addEssence(essence, inv[2]);
                    }
                    
                    if (meta == 10) {
                        //Essence essence = Essence.essenceList[1];
                        //EssenceHandler.addEssence(essence, inv[2]);
                    }
                    
                    if (meta == 11) {
                        //Essence essence = Essence.essenceList[1];
                        //EssenceHandler.addEssence(essence, inv[2]);
                    }
                    
                    if (meta == 12) {
                        //Essence essence = Essence.essenceList[1];
                        //EssenceHandler.addEssence(essence, inv[2]);
                    }
                    
                    if (meta == 13){
                        Essence essence = Essence.essenceList[4];
                        EssenceHandler.addEssence(essence, inv[2]);
                    }
                }
                
                if (crystal.itemID == CrystalMod.InertCrystal.itemID
                        && operative.itemID == CrystalMod.CrystalPick.itemID) {
                    // ItemStack item = this.inv[3];
                    int meta = crystal.getItemDamage();

                    if (meta == 1) {
                      //Essence essence = Essence.essenceList[0];
                      //EssenceHandler.addEssence(essence, inv[2]);
                    }

                    if (meta == 2) {
                        Essence essence = Essence.essenceList[2];
                        EssenceHandler.addEssence(essence, inv[2]);
                    }

                    if (meta == 3) {
                        //Essence essence = Essence.essenceList[1];
                        //EssenceHandler.addEssence(essence, inv[2]);
                    }
                    
                    if (meta == 4) {
                        //Essence essence = Essence.essenceList[1];
                        //EssenceHandler.addEssence(essence, inv[2]);
                    }
                    
                    if (meta == 5) {
                        //Essence essence = Essence.essenceList[1];
                        //EssenceHandler.addEssence(essence, inv[2]);
                    }
                    
                    if (meta == 6) {
                        Essence essence = Essence.essenceList[3];
                        EssenceHandler.addEssence(essence, inv[2]);
                    }
                    
                    if (meta == 7) {
                        //Essence essence = Essence.essenceList[0];
                        //EssenceHandler.addEssence(essence, inv[2]);
                    }
                    
                    if (meta == 8) {
                        //Essence essence = Essence.essenceList[1];
                        //EssenceHandler.addEssence(essence, inv[2]);
                    }
                    
                    if (meta == 9) {
                        //Essence essence = Essence.essenceList[5];
                        //EssenceHandler.addEssence(essence, inv[2]);
                    }
                    
                    if (meta == 10) {
                        //Essence essence = Essence.essenceList[1];
                        //EssenceHandler.addEssence(essence, inv[2]);
                    }
                    
                    if (meta == 11) {
                        //Essence essence = Essence.essenceList[1];
                        //EssenceHandler.addEssence(essence, inv[2]);
                    }
                    
                    if (meta == 12) {
                        //Essence essence = Essence.essenceList[1];
                        //EssenceHandler.addEssence(essence, inv[2]);
                    }
                    
                    if (meta == 13){
                        Essence essence = Essence.essenceList[4];
                        EssenceHandler.addEssence(essence, inv[2]);
                    }
                }
                
                if (crystal.itemID == CrystalMod.InertCrystal.itemID
                        && operative.itemID == CrystalMod.CrystalAxe.itemID) {
                    // ItemStack item = this.inv[3];
                    int meta = crystal.getItemDamage();

                    if (meta == 1) {
                      //Essence essence = Essence.essenceList[0];
                      //EssenceHandler.addEssence(essence, inv[2]);
                    }

                    if (meta == 2) {
                        //Essence essence = Essence.essenceList[2];
                        //EssenceHandler.addEssence(essence, inv[2]);
                    }

                    if (meta == 3) {
                        //Essence essence = Essence.essenceList[1];
                        //EssenceHandler.addEssence(essence, inv[2]);
                    }
                    
                    if (meta == 4) {
                        //Essence essence = Essence.essenceList[1];
                        //EssenceHandler.addEssence(essence, inv[2]);
                    }
                    
                    if (meta == 5) {
                        //Essence essence = Essence.essenceList[1];
                        //EssenceHandler.addEssence(essence, inv[2]);
                    }
                    
                    if (meta == 6) {
                        //Essence essence = Essence.essenceList[3];
                        //EssenceHandler.addEssence(essence, inv[2]);
                    }
                    
                    if (meta == 7) {
                        //Essence essence = Essence.essenceList[0];
                        //EssenceHandler.addEssence(essence, inv[2]);
                    }
                    
                    if (meta == 8) {
                        //Essence essence = Essence.essenceList[5];
                        //EssenceHandler.addEssence(essence, inv[2]);
                    }
                    
                    if (meta == 9) {
                        Essence essence = Essence.essenceList[5];
                        EssenceHandler.addEssence(essence, inv[2]);
                    }
                    
                    if (meta == 10) {
                        //Essence essence = Essence.essenceList[1];
                        //EssenceHandler.addEssence(essence, inv[2]);
                    }
                    
                    if (meta == 11) {
                        //Essence essence = Essence.essenceList[1];
                        //EssenceHandler.addEssence(essence, inv[2]);
                    }
                    
                    if (meta == 12) {
                        //Essence essence = Essence.essenceList[1];
                        //EssenceHandler.addEssence(essence, inv[2]);
                    }
                    
                    if (meta == 13){
                        Essence essence = Essence.essenceList[4];
                        EssenceHandler.addEssence(essence, inv[2]);
                    }
                }
            }
        }
    }

    public static int getItemImbueTime(ItemStack par0ItemStack) {
        if (par0ItemStack == null)
            return 0;
        else
            return CrystalFuel.getImbueTime(par0ItemStack);
    }

    public static boolean isItemFuel(ItemStack par0ItemStack) {
        return getItemImbueTime(par0ItemStack) > 0;
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
