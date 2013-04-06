package com.crystals;

import net.minecraft.entity.Entity;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.world.World;
import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.event.entity.player.AttackEntityEvent;

import com.crystals.essence.Essence;

public class CrystalHooks {

    @ForgeSubscribe
    public void attackEntity(AttackEntityEvent event) {
        EntityPlayer player = event.entityPlayer;
        World worldT = event.target.worldObj;
        ItemStack item = player.getCurrentEquippedItem();
        Entity target = event.target;

        if (item != null) {
            if (item.itemID == CrystalMod.CrystalSword.itemID) {

                if (item.stackTagCompound != null) {

                    NBTTagList list = (NBTTagList) item.stackTagCompound
                            .getTag("essence");
                    if (list != null) {

                        if (item.hasTagCompound()) {

                            short eID = ((NBTTagCompound) list.tagAt(0))
                                    .getShort("essenceID");

                            if (Essence.essenceList[eID] != null) {

                                if (Essence.essenceList[eID].essenceID == 0) {

                                    worldT.spawnEntityInWorld(new EntityLightningBolt(
                                            worldT, target.posX, target.posY,
                                            target.posZ));
                                }

                                if (Essence.essenceList[eID].essenceID == 1) {

                                    target.setFire(2);
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}