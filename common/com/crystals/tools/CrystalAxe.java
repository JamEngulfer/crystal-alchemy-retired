package com.crystals.tools;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.item.EnumToolMaterial;
import net.minecraft.item.ItemStack;

import com.crystals.CrystalMod;

public class CrystalAxe extends CrystalToolBase {

    public static final Block[] blocksEffectiveAgainst = new Block[] {
            Block.planks, Block.bookShelf, Block.wood, Block.chest,
            Block.stoneDoubleSlab, Block.stoneSingleSlab, Block.pumpkin,
            Block.pumpkinLantern };

    public CrystalAxe(int ItemID, EnumToolMaterial m, int tex, String name) {
        super(ItemID, 1, m, blocksEffectiveAgainst);
        this.setCreativeTab(CrystalMod.alchemyWeaponTab);
        setUnlocalizedName("CrystalAxe");
    }

    @Override
    public float getStrVsBlock(ItemStack par1ItemStack, Block par2Block) {
        return par2Block != null
                && (par2Block.blockMaterial == Material.wood
                        || par2Block.blockMaterial == Material.plants || par2Block.blockMaterial == Material.vine) ? efficiencyOnProperMaterial
                : super.getStrVsBlock(par1ItemStack, par2Block);
    }

    @Override
    public void updateIcons(IconRegister iconRegister) {
        iconIndex = iconRegister.registerIcon("CrystalAlchemy:CrystalAxe");
    }
}
