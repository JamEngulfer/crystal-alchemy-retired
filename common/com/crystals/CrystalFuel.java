package com.crystals;

import net.minecraft.item.ItemStack;
import cpw.mods.fml.common.IFuelHandler;

public class CrystalFuel implements IFuelHandler {

    @Override
    public int getBurnTime(ItemStack fuel) {
        if (fuel.itemID == CrystalMod.MagicFuelCoal.itemID)
            return 800;
        else {
            if (fuel.itemID == CrystalMod.MagicFuelCoalSuper.itemID)
                return 1200;
            else {
                if (fuel.itemID == CrystalMod.MagicFuelBlock.blockID)
                    return 9600;
            }
        }
        return 0;
    }

    public static int getInfuseTime(ItemStack fuel) {
        if (fuel.itemID == CrystalMod.MagicFuelCoal.itemID)
            return 800 * 4;
        else {
            if (fuel.itemID == CrystalMod.MagicFuelCoalSuper.itemID)
                return 1200 * 4;
            else {
                if (fuel.itemID == CrystalMod.MagicFuelBlock.blockID)
                    return 9600 * 4;
            }
        }
        return 0;
    }

    public static int getImbueTime(ItemStack fuel) {
        if (fuel.itemID == CrystalMod.MagicFuelCoal.itemID)
            return 800 * 4;
        else {
            if (fuel.itemID == CrystalMod.MagicFuelCoalSuper.itemID)
                return 1200 * 4;
            else {
                if (fuel.itemID == CrystalMod.MagicFuelBlock.blockID)
                    return 9600 * 4;
            }
        }
        return 0;
    }

}
