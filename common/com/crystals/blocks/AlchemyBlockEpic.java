package com.crystals.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;

import com.crystals.CrystalMod;

public class AlchemyBlockEpic extends Block {

    public AlchemyBlockEpic(int par1, int par2, Material par3Material) {
        super(par1, par3Material);
        this.setCreativeTab(CrystalMod.alchemyMachineTab);
    }

    @Override
    public void registerIcons(IconRegister iconRegister) {
        blockIcon = iconRegister
                .registerIcon("CrystalAlchemy:AlchemyBlockEpic");
    }
}
