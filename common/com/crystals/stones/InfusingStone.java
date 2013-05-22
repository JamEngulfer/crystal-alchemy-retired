package com.crystals.stones;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import com.crystals.CrystalMod;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class InfusingStone extends Item {

    public InfusingStone(int par1) {
        super(par1);
        maxStackSize = 1;
        this.setCreativeTab(CrystalMod.alchemyFuelTab);
        setUnlocalizedName("InfusingStone");
    }

    @Override
    @SideOnly(Side.CLIENT)
    public EnumRarity getRarity(ItemStack par1) {
        return EnumRarity.rare;
    }

    @Override
    public void registerIcons(IconRegister iconRegister) {
        itemIcon = iconRegister.registerIcon("CrystalAlchemy:InfusingStone");
    }

}
