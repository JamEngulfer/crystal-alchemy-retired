package com.crystals.ores;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

import com.crystals.CrystalMod;

public class NetherInertOre extends Block {

    public NetherInertOre(int par1, int par2, Material par3Material) {
        super(par1, par3Material);
        this.setCreativeTab(CrystalMod.alchemyFuelTab);
    }

    /**
     * Returns the ID of the items to drop on destruction.
     */
    @Override
    public int idDropped(int par1, Random par2Random, int par3) {
        return CrystalMod.InertCrystal.itemID;
    }

    @Override
    public void dropBlockAsItemWithChance(World par1World, int par2, int par3,
            int par4, int par5, float par6, int par7) {
        super.dropBlockAsItemWithChance(par1World, par2, par3, par4, par5,
                par6, par7);

        if (this.idDropped(par5, par1World.rand, par7) != blockID) {
            int var8 = 0;

            var8 = MathHelper.getRandomIntegerInRange(par1World.rand, 3, 5);

            this.dropXpOnBlockBreak(par1World, par2, par3, par4, var8);
        }
    }

    @Override
    public void registerIcons(IconRegister iconRegister) {
        blockIcon = iconRegister.registerIcon("CrystalAlchemy:NetherInertOre");
    }
}
