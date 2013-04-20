package com.crystals.tools;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumToolMaterial;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;

import com.crystals.CrystalMod;
import com.crystals.essence.Essence;

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

                        if (Essence.essenceList[eID].essenceID == 2) {

                            information.add("Explosive Digging");

                        }

                        if (Essence.essenceList[eID].essenceID == 4) {

                            information.add("Instant Break Void");

                        }
                    }
                }
            }
        } else {
            information.add("No Ability");
        }
    }
}
