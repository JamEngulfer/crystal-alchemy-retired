package com.crystals.tabs;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;

import com.crystals.CrystalMod;

public class AlchemyMachineTab extends CreativeTabs {

    public AlchemyMachineTab(String label) {
        super(label);
    }

    @Override
    public ItemStack getIconItemStack() {
        return new ItemStack(CrystalMod.BlockInfuser);
    }

}
