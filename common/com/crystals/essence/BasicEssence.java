package com.crystals.essence;

import java.util.List;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;

import com.crystals.CrystalMod;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BasicEssence extends Item {

    public Icon[] icons = new Icon[3];

    public BasicEssence(int ItemID) {
        super(ItemID);
        setCreativeTab(CrystalMod.alchemyCrystalTab);
        setHasSubtypes(true);
        setMaxDamage(0);
        maxStackSize = 1;
        setUnlocalizedName("BasicEssence");
    }

    @Override
    @SideOnly(Side.CLIENT)
    public Icon getIconFromDamage(int i) {
        return icons[i];
    }

    @SideOnly(Side.CLIENT)
    @Override
    public void updateIcons(IconRegister iconRegister) {
        // icons[0] = iconRegister.registerIcon("CrystalAlchemy:EmptyBottle");
        icons[1] = iconRegister.registerIcon("CrystalAlchemy:LuminousEssence");
        icons[2] = iconRegister.registerIcon("CrystalAlchemy:ExplosiveEssence");
    }

    @Override
    public String getUnlocalizedName(ItemStack stack) {
        switch (stack.getItemDamage()) {
            case 1:
                return "LuminousEssence";
            case 2:
                return "ExplosiveEssence";
            default:
                return "UnknownEssence";
        }
    }

    public String getItemNameIS(ItemStack is) {
        switch (is.getItemDamage()) {
        // case 0 : return "Empty Essence Bottle";
            case 1:
                return "Luminous Essence";
            case 2:
                return "Explosive Essence";
            default:
                return "Unidentified Essence";
        }

        // return "Empty Essence Bottle";
    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
    @Override
    @SideOnly(Side.CLIENT)
    public void getSubItems(int ItemID, CreativeTabs tab, List itemList) {
        for (int i = 1; i < 3; i++) {
            itemList.add(new ItemStack(ItemID, 1, i));
        }
    }

}
