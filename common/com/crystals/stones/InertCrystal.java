package com.crystals.stones;

import java.util.List;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;

import com.crystals.CrystalMod;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class InertCrystal extends Item {

    private Icon[] icons = new Icon[3];

    public InertCrystal(int par1) {
        super(par1);
        setCreativeTab(CrystalMod.alchemyCrystalTab);
        setHasSubtypes(true);
        setMaxDamage(0);
        maxStackSize = 16;
    }

    @SideOnly(Side.CLIENT)
    @Override
    public Icon getIconFromDamage(int i) {
        return icons[i];
    }

    @Override
    public String getUnlocalizedName(ItemStack stack) {
        switch (stack.getItemDamage()) {
            case 0:
                return "InertCrystal";
            case 1:
                return "LuminousCrystal";
            case 2:
                return "ExplosiveCrystal";
            default:
                return "UnknownCrystal";
        }
    }

    @Override
    public void updateIcons(IconRegister iconRegister) {
        // int damage =
        icons[0] = iconRegister.registerIcon("CrystalAlchemy:InertCrystal");
        icons[1] = iconRegister.registerIcon("CrystalAlchemy:LuminousCrystal");
        icons[2] = iconRegister.registerIcon("CrystalAlchemy:ExplosiveCrystal");
    }

    public String getItemNameIS(ItemStack is) {
        switch (is.getItemDamage()) {
            case 0:
                return "Inert Crystal";
            case 1:
                return "Luminous Crystal";
            case 2:
                return "Explosive Crystal";
            default:
                return "Unknown Crystal";
        }
    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
    @Override
    @SideOnly(Side.CLIENT)
    public void getSubItems(int ItemID, CreativeTabs tab, List itemList) {
        for (int i = 0; i < 3; i++) {
            itemList.add(new ItemStack(ItemID, 1, i));
        }
    }
}
