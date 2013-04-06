package com.crystals.tabs;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;

import com.crystals.CrystalMod;

public class AlchemyWeaponTab extends CreativeTabs {

    public AlchemyWeaponTab(String label) {
        super(label);
    }

    @Override
    public ItemStack getIconItemStack() {
        return new ItemStack(CrystalMod.CrystalSword);
    }

}
