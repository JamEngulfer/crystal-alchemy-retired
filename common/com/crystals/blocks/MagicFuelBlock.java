package com.crystals.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;

import com.crystals.CrystalMod;

public class MagicFuelBlock extends Block {

    public MagicFuelBlock(int par1, Material m) {
        super(par1, Material.rock);
        this.setCreativeTab(CrystalMod.alchemyMachineTab);
    }

    @Override
    public void registerIcons(IconRegister iconRegister) {
        blockIcon = iconRegister
                .registerIcon("CrystalAlchemy:AlchemyFuelBlock");
    }
}