package com.crystals.items;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.item.Item;

import com.crystals.CrystalMod;

public class MagicFuelCoalSuper extends Item {

    public MagicFuelCoalSuper(int par1) {
        super(par1);
        maxStackSize = 64;
        this.setCreativeTab(CrystalMod.alchemyFuelTab);
        setUnlocalizedName("MagicFuelCoalSuper");
    }

    @Override
    public void updateIcons(IconRegister iconRegister) {
        iconIndex = iconRegister
                .registerIcon("CrystalAlchemy:AlchemicalCoalSuper");
    }
}
