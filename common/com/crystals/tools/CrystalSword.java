package com.crystals.tools;

import java.util.List;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumToolMaterial;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;

import com.crystals.CrystalMod;
import com.crystals.essence.Essence;

public class CrystalSword extends ItemSword {

    public CrystalSword(int ItemID, EnumToolMaterial material) {
        super(ItemID, material);
        this.setCreativeTab(CrystalMod.alchemyWeaponTab);
        setUnlocalizedName("CrystalSword");
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

                        if (Essence.essenceList[eID].essenceID == 0) {

                            information.add("Lightning Attack");

                        }

                        if (Essence.essenceList[eID].essenceID == 1) {

                            information.add("Fire Damage");

                        }
                        
                        if (Essence.essenceList[eID].essenceID == 2) {

                            information.add("Explosive Effect");

                        }
                        
                        if (Essence.essenceList[eID].essenceID == 3) {

                            information.add("Life Steal");

                        }
                        
                        if (Essence.essenceList[eID].essenceID == 4) {

                            information.add("Teleportation");

                        }
                    }
                }
            }
        } else {
            information.add("No Ability");
        }

    }

    @Override
    public void updateIcons(IconRegister iconRegister) {
        iconIndex = iconRegister.registerIcon("CrystalAlchemy:CrystalSword");
    }
}
