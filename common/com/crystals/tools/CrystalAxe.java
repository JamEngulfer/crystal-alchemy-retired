package com.crystals.tools;

import java.util.List;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumToolMaterial;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.world.World;

import com.crystals.CrystalMod;
import com.crystals.essence.Essence;

import cpw.mods.fml.common.network.Player;

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
    public boolean onBlockDestroyed(ItemStack item, World world, int blockID, int bX, int bY, int bZ, EntityLiving entity)
    {
        
        if(item.stackTagCompound != null){
            NBTTagList list = (NBTTagList) item.stackTagCompound
                    .getTag("essence");
            if(list != null){
                if(item.hasTagCompound()){
                    short eID = ((NBTTagCompound) list.tagAt(0))
                            .getShort("essenceID");
                    if (Essence.essenceList[eID] != null) {

                        if (Essence.essenceList[eID].essenceID == 5) {

                            if(entity instanceof EntityPlayer){
                                if(((EntityPlayer) entity).inventory.hasItem(Block.sapling.blockID)){
                                    if(blockID == Block.wood.blockID){
                                        boolean safe = false;

                                        int timeout = 27;
                                        int time = 0;

                                        while (safe == false && time < timeout){

                                            int x;
                                            int y;
                                            int z;

                                            int minX = (int) bX - 2;
                                            int maxX = (int) bX + 2;
                                            int minY = (int) bY - 2;
                                            int maxY = (int) bY + 2;
                                            int minZ = (int) bZ - 2;
                                            int maxZ = (int) bZ + 2;

                                            x = minX + (int)(Math.random() * ((maxX - minX) + 1));
                                            y = minY + (int)(Math.random() * ((maxY - minY) + 1));
                                            z = minZ + (int)(Math.random() * ((maxZ - minZ) + 1));

                                            if(world.isAirBlock(x, y, z) && ((world.getBlockId(x, y-1, z) == Block.dirt.blockID) || world.getBlockId(x, y-1, z) == Block.grass.blockID)){
                                                
                                                    if(!world.isRemote){
                                                        world.setBlock(x, y, z, Block.sapling.blockID);
                                                        
                                                        for(int i = 0; i < ((EntityPlayer) entity).inventory.mainInventory.length; i++){
                                                            
                                                            ItemStack items = ((EntityPlayer) entity).inventory.getStackInSlot(i);
                                                            if(items.itemID == Block.sapling.blockID){
                                                                
                                                                int stack = ((EntityPlayer) entity).inventory.mainInventory[i].stackSize -1;
                                                                if(stack == 0){
                                                                    ((EntityPlayer) entity).inventory.mainInventory[i] = null;
                                                                    break;
                                                                } else {
                                                                    ((EntityPlayer) entity).inventory.mainInventory[i].stackSize = stack;
                                                                    break;
                                                                }
                                                                
                                                            }
                                                            
                                                            
                                                        }
                                                    }
                                                    safe = true;
                                            }

                                            time++;
                                        }
                                    }

                                    return true;
                                }
                            }

                        }

                    }
                }
            }
        }

        return true;
    }

    public boolean canBlockBeBroken(int id){

        Block[] blockList = new Block[] {
                Block.planks, Block.bookShelf, Block.wood, Block.chest,
                Block.pumpkin, Block.pumpkinLantern, Block.leaves  
        };

        for(int i = 0; i < blockList.length; i++){
            if(blockList[i].blockID == id){
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean onItemUse(ItemStack item, EntityPlayer player, World world, int x, int y, int z, int side, float xFloat, float yFloat, float zFloat)
    {

        Random rand = new Random();
        if (!player.canPlayerEdit(x, y, z, side, item))
        {
            return false;
        }
        if(world.getBlockId(x, y, z) != Block.bedrock.blockID){
            if(item.stackTagCompound != null){
                NBTTagList list = (NBTTagList) item.stackTagCompound
                        .getTag("essence");
                if(list != null){
                    if(item.hasTagCompound()){
                        short eID = ((NBTTagCompound) list.tagAt(0))
                                .getShort("essenceID");
                        if (Essence.essenceList[eID] != null) {

                            if (Essence.essenceList[eID].essenceID == 4) {
                                if(canBlockBeBroken(world.getBlockId(x, y, z))){

                                    world.destroyBlock(x, y, z, false);

                                    for (int l = 0; l < 4; ++l)
                                    {
                                        double d0 = (double)((float)x + rand.nextFloat());
                                        double d1 = (double)((float)y + rand.nextFloat());
                                        double d2 = (double)((float)z + rand.nextFloat());
                                        double d3 = 0.0D;
                                        double d4 = 0.0D;
                                        double d5 = 0.0D;

                                        d3 = ((double)rand.nextFloat() - 0.5D) * 0.5D;
                                        d4 = ((double)rand.nextFloat() - 0.5D) * 0.5D;
                                        d5 = ((double)rand.nextFloat() - 0.5D) * 0.5D;

                                        world.spawnParticle("portal", d0, d1, d2, d3, d4, d5);
                                    }
                                }
                                return true;
                            }
                        }
                    }
                }
            }
        }

        return true;
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
