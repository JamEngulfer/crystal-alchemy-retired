package com.crystals.diffuser;

import java.util.Random;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Icon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import com.crystals.CrystalMod;

public class BlockDiffuser extends BlockContainer {

    private Icon[] icons = new Icon[3];

    public BlockDiffuser(int par1, int par2, Material par3Material) {
        super(par1, par3Material);

        this.setCreativeTab(CrystalMod.alchemyMachineTab);

    }

    @Override
    public boolean onBlockActivated(World world, int x, int y, int z,
            EntityPlayer player, int i, float f, float g, float t) {

        TileEntity tile_entity = world.getBlockTileEntity(x, y, z);

        if (tile_entity == null || player.isSneaking())
            return false;
        player.openGui(CrystalMod.instance, 2, world, x, y, z);
        return true;

    }

    @Override
    public void breakBlock(World world, int x, int y, int z, int i, int j) {
        dropItems(world, x, y, z);
        super.breakBlock(world, x, y, z, i, j);
    }

    private void dropItems(World world, int x, int y, int z) {
        Random rand = new Random();

        TileEntity tile_entity = world.getBlockTileEntity(x, y, z);

        if (!(tile_entity instanceof IInventory))
            return;

        IInventory inventory = (IInventory) tile_entity;

        for (int i = 0; i < inventory.getSizeInventory(); i++) {
            ItemStack item = inventory.getStackInSlot(i);

            if (item != null && item.stackSize > 0) {
                float rx = rand.nextFloat() * 0.6F + 0.1F;
                float ry = rand.nextFloat() * 0.6F + 0.1F;
                float rz = rand.nextFloat() * 0.6F + 0.1F;

                EntityItem entity_item = new EntityItem(world, x + rx, y + ry,
                        z + rz, new ItemStack(item.itemID, item.stackSize,
                                item.getItemDamage()));

                /*
                 * if(item.hasTagCompound()){
                 * entity_item.item.setTagCompound((NBTTagCompound)
                 * item.getTagCompound().copy()); }
                 */

                float factor = 0.5F;

                entity_item.motionX = rand.nextGaussian() * factor;
                entity_item.motionY = rand.nextGaussian() * factor + 0.2F;
                entity_item.motionZ = rand.nextGaussian() * factor;
                world.spawnEntityInWorld(entity_item);
                item.stackSize = 0;
            }
        }
    }

    @Override
    public TileEntity createNewTileEntity(World var1) {
        return new DiffuserTileEntity();
    }

    @Override
    public Icon getBlockTexture(IBlockAccess world, int x, int y, int z,
            int blockSide) {
        switch (blockSide) {
            case 0:
                return icons[0];
            case 1:
                return icons[1];
            default:
                return icons[2];
        }
    }

    @Override
    public Icon getIcon(int blockSide, int blockMeta) {
        switch (blockSide) {
            case 0:
                return icons[0];
            case 1:
                return icons[1];
            default:
                return icons[2];
        }
    }

    @Override
    public void registerIcons(IconRegister iconRegister) {
        icons[0] = iconRegister.registerIcon("CrystalAlchemy:DiffuserBottom");
        icons[1] = iconRegister.registerIcon("CrystalAlchemy:DiffuserTop");
        icons[2] = iconRegister.registerIcon("CrystalAlchemy:DiffuserSide");
    }
}
