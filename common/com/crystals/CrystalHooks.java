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

                                    target.setFire(5);
                                }

                                if (Essence.essenceList[eID].essenceID == 2) {

                                    worldT.createExplosion(player, target.posX, target.posY + 1, target.posZ, 1F, false);
                                    
                                }
                                
                                if (Essence.essenceList[eID].essenceID == 3) {
                                    
                                    int weight = 33;

                                    int random = 0 + (int)(Math.random() * ((100 - 0) + 1));
                                    if(random < weight){
                                        if(player.getHealth() < 20){
                                            player.setEntityHealth(player.getHealth() + 1);
                                        }
                                    }
                                }

                                if (Essence.essenceList[eID].essenceID == 4) {

                                    //Min + (int)(Math.random() * ((Max - Min) + 1))
                                    //sqrt(x^2+y^2+z^2)

                                    boolean safe = false;

                                    while (safe == false){
                                        
                                        int x;
                                        int y;
                                        int z;

                                        int minX = (int) player.posX - 16;
                                        int maxX = (int) player.posX + 16;
                                        int minY = (int) player.posY - 16;
                                        int maxY = (int) player.posY + 16;
                                        int minZ = (int) player.posZ - 16;
                                        int maxZ = (int) player.posZ + 16;
                                        
                                        x = minX + (int)(Math.random() * ((maxX - minX) + 1));
                                        y = minY + (int)(Math.random() * ((maxY - minY) + 1));
                                        z = minZ + (int)(Math.random() * ((maxZ - minZ) + 1));
                                        
                                        if(worldT.isAirBlock(x, y, z) && worldT.isAirBlock(x, (y + 1), z) && !worldT.isAirBlock(x, (y - 1), z)){
                                            if(Math.sqrt(x^2+y^2+z^2) > 6){
                                                event.target.setPosition((double) x, (double) (y+0.75), (double) z);
                                                safe = true;
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}