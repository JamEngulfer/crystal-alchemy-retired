package com.crystals.tools;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.item.EnumToolMaterial;
import net.minecraft.item.ItemStack;

import com.crystals.CrystalMod;

public class CrystalPick extends CrystalToolBase {

    public static final Block[] blocksEffectiveAgainst = new Block[] {
            Block.cobblestone, Block.stoneDoubleSlab, Block.stoneSingleSlab,
            Block.stone, Block.sandStone, Block.cobblestoneMossy,
            Block.oreIron, Block.blockSteel, Block.oreCoal, Block.blockGold,
            Block.oreGold, Block.oreDiamond, Block.blockDiamond, Block.ice,
            Block.netherrack, Block.oreLapis, Block.blockLapis,
            Block.oreRedstone, Block.oreRedstoneGlowing, Block.rail,
            Block.railDetector, Block.railPowered, CrystalMod.AlchemyBlock,
            CrystalMod.AlchemyBlockEpic, CrystalMod.AlchemyBlockSuper,
            CrystalMod.BlockDiffuser, CrystalMod.BlockImbuer,
            CrystalMod.BlockInfuser, CrystalMod.MagicFuelBlock,
            CrystalMod.InertOre, CrystalMod.InertOreNether };

    public CrystalPick(int ItemID, EnumToolMaterial m, int tex, String name) {
        super(ItemID, 1, m, blocksEffectiveAgainst);
        this.setCreativeTab(CrystalMod.alchemyWeaponTab);
        setUnlocalizedName("CrystalPick");
    }

    @Override
    public boolean canHarvestBlock(Block par1Block) {
        return par1Block == Block.obsidian ? toolMaterial.getHarvestLevel() == 3
                : par1Block != Block.blockDiamond
                        && par1Block != Block.oreDiamond ? par1Block != Block.oreEmerald
                        && par1Block != Block.blockEmerald ? par1Block != Block.blockGold
                        && par1Block != Block.oreGold ? par1Block != Block.blockSteel
                        && par1Block != Block.oreIron ? par1Block != Block.blockLapis
                        && par1Block != Block.oreLapis ? par1Block != Block.oreRedstone
                        && par1Block != Block.oreRedstoneGlowing ? par1Block.blockMaterial == Material.rock ? true
                        : par1Block.blockMaterial == Material.iron ? true
                                : par1Block.blockMaterial == Material.anvil
                        : toolMaterial.getHarvestLevel() >= 2
                        : toolMaterial.getHarvestLevel() >= 1
                        : toolMaterial.getHarvestLevel() >= 1
                        : toolMaterial.getHarvestLevel() >= 2
                        : toolMaterial.getHarvestLevel() >= 2
                        : toolMaterial.getHarvestLevel() >= 2;
    }

    @Override
    public float getStrVsBlock(ItemStack item, Block par2Block) {
        return par2Block != null
                && (par2Block.blockMaterial == Material.iron
                        || par2Block.blockMaterial == Material.anvil || par2Block.blockMaterial == Material.rock) ? efficiencyOnProperMaterial
                : super.getStrVsBlock(item, par2Block);
    }

    @Override
    public void updateIcons(IconRegister iconRegister) {
        iconIndex = iconRegister.registerIcon("CrystalAlchemy:CrystalPick");
    }

}
