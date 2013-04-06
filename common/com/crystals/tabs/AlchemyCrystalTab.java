package com.crystals.tabs;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;

import com.crystals.CrystalMod;

public class AlchemyCrystalTab extends CreativeTabs {

    public AlchemyCrystalTab(String label) {
        super(label);
    }

    @Override
    public ItemStack getIconItemStack() {
        return new ItemStack(CrystalMod.InertCrystal);
    }

}
