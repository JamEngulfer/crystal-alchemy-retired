package com.crystals.items;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.item.Item;

import com.crystals.CrystalMod;

public class MagicFuelCoal extends Item {

    public MagicFuelCoal(int par1) {
        super(par1);
        maxStackSize = 64;
        this.setCreativeTab(CrystalMod.alchemyFuelTab);
        setUnlocalizedName("MagicFuelCoal");
    }

    @Override
    public void registerIcons(IconRegister iconRegister) {
        itemIcon = iconRegister.registerIcon("CrystalAlchemy:AlchemicalCoal");
    }
}
