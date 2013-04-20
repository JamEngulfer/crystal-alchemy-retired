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

    private Icon[] icons = new Icon[64];

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
                return "Inert Crystal";
            case 1:
                return "Luminous Crystal";
            case 2:
                return "Explosive Crystal";
            case 3:
                return "Firey Crystal";
            case 4:
                return "Life Crystal";
            case 5:
                return "Energy Crystal";
            case 6:
                return "Healing Crystal";
            case 7:
                return "Lightning Crystal";
            case 8:
                return "Fauna Crystal";
            case 9:
                return "Growth Crystal";
            case 10:
                return "Ender Crystal";
            case 11:
                return "Nether Crystal";
            case 12:
                return "Magic Crystal";
            case 13:
                return "Void Crystal";
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
        icons[3] = iconRegister.registerIcon("CrystalAlchemy:FireyCrystal");
        icons[4] = iconRegister.registerIcon("CrystalAlchemy:LifeCrystal");
        icons[5] = iconRegister.registerIcon("CrystalAlchemy:EnergyCrystal");
        icons[6] = iconRegister.registerIcon("CrystalAlchemy:HealingCrystal");
        icons[7] = iconRegister.registerIcon("CrystalAlchemy:LightningCrystal");
        icons[8] = iconRegister.registerIcon("CrystalAlchemy:FaunaCrystal");
        icons[9] = iconRegister.registerIcon("CrystalAlchemy:GrowthCrystal");
        icons[10] = iconRegister.registerIcon("CrystalAlchemy:EnderCrystal");
        icons[11] = iconRegister.registerIcon("CrystalAlchemy:NetherCrystal");
        icons[12] = iconRegister.registerIcon("CrystalAlchemy:MagicCrystal");
        icons[13] = iconRegister.registerIcon("CrystalAlchemy:VoidCrystal");
    }

    public String getItemNameIS(ItemStack is) {
        switch (is.getItemDamage()) {
            case 0:
                return "Inert Crystal";
            case 1:
                return "Luminous Crystal";
            case 2:
                return "Explosive Crystal";
            case 3:
                return "Firey Crystal";
            case 4:
                return "Life Crystal";
            case 5:
                return "Energy Crystal";
            case 6:
                return "Healing Crystal";
            case 7:
                return "Lightning Crystal";
            case 8:
                return "Fauna Crystal";
            case 9:
                return "Growth Crystal";
            case 10:
                return "Ender Crystal";
            case 11:
                return "Nether Crystal";
            case 12:
                return "Magic Crystal";
            case 13:
                return "Void Crystal";
            default:
                return "Unknown Crystal";
        }
    }
    
    @Override
    public boolean hasEffect(ItemStack item){
        switch(item.getItemDamage()){
            case 5:
                return true;
            case 7:
                return true;
            case 12:
                return true;
            default:
                return false;
        }
    }
    
    @SuppressWarnings({ "rawtypes", "unchecked" })
    @Override
    @SideOnly(Side.CLIENT)
    public void getSubItems(int ItemID, CreativeTabs tab, List itemList) {
        for (int i = 0; i < 14; i++) {
            itemList.add(new ItemStack(ItemID, 1, i));
        }
    }
}
