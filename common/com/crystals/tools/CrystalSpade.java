package com.crystals.tools;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.item.EnumToolMaterial;

import com.crystals.CrystalMod;

public class CrystalSpade extends CrystalToolBase {

    public static final Block[] blocksEffectiveAgainst = new Block[] {
            Block.grass, Block.dirt, Block.sand, Block.gravel, Block.snow,
            Block.blockSnow, Block.blockClay, Block.tilledField,
            Block.slowSand, Block.mycelium };

    public CrystalSpade(int ItemID, EnumToolMaterial m, int tex, String name) {
        super(ItemID, 1, m, blocksEffectiveAgainst);
        this.setCreativeTab(CrystalMod.alchemyWeaponTab);
        setUnlocalizedName("CrystalSpade");
    }

    @Override
    public boolean canHarvestBlock(Block block) {
        return block == Block.snow ? true : block == Block.blockSnow;
    }

    @Override
    public void updateIcons(IconRegister iconRegister) {
        iconIndex = iconRegister.registerIcon("CrystalAlchemy:CrystalSpade");
    }

}
