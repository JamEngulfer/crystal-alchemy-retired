package com.crystals.tools;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumToolMaterial;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;

import com.crystals.CrystalMod;
import com.crystals.essence.Essence;

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
    
    @SuppressWarnings({ "rawtypes", "unchecked" })
    @Override
    public void addInformation(ItemStack itemStack, EntityPlayer player,
            List information, boolean advancedTooltip) {
        ItemStack item = itemStack;
        if (item.stackTagCompound != null) {

            NBTTagList list = (NBTTagList) item.stackTagCompound
                    .getTag("essence");
            if (list != null) {

                if (item.hasTagCompound()) {

                    short eID = ((NBTTagCompound) list.tagAt(0))
                            .getShort("essenceID");

                    if (Essence.essenceList[eID] != null) {

                        if (Essence.essenceList[eID].essenceID == 4) {

                            information.add("Void Effect");

                        }

                        if (Essence.essenceList[eID].essenceID == 5) {

                            information.add("Replanting Effect");

                        }
                    }
                }
            }
        } else {
            information.add("No Ability");
        }

    }
}
