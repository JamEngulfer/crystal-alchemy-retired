package com.crystals.tabs;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;

import com.crystals.CrystalMod;

public class AlchemyFuelTab extends CreativeTabs {

    public AlchemyFuelTab(String label) {
        super(label);
    }

    @Override
    public ItemStack getIconItemStack() {
        return new ItemStack(CrystalMod.MagicFuelCoal);
    }

}
