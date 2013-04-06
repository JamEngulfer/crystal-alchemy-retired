package com.crystals;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

import com.crystals.diffuser.ContainerDiffuser;
import com.crystals.diffuser.DiffuserGUI;
import com.crystals.diffuser.DiffuserTileEntity;
import com.crystals.imbuer.ContainerImbuer;
import com.crystals.imbuer.ImbuerGUI;
import com.crystals.imbuer.ImbuerTileEntity;
import com.crystals.infuser.ContainerInfuser;
import com.crystals.infuser.InfuserGUI;
import com.crystals.infuser.InfuserTileEntity;

import cpw.mods.fml.common.network.IGuiHandler;

public class GuiHandler implements IGuiHandler {

    @Override
    public Object getServerGuiElement(int id, EntityPlayer player, World world,
            int x, int y, int z) {

        TileEntity tile_entity = world.getBlockTileEntity(x, y, z);

        if (tile_entity instanceof InfuserTileEntity)
            return new ContainerInfuser((InfuserTileEntity) tile_entity,
                    player.inventory);

        if (tile_entity instanceof ImbuerTileEntity)
            return new ContainerImbuer((ImbuerTileEntity) tile_entity,
                    player.inventory);

        if (tile_entity instanceof DiffuserTileEntity)
            return new ContainerDiffuser((DiffuserTileEntity) tile_entity,
                    player.inventory);
        return tile_entity;

    }

    @Override
    public Object getClientGuiElement(int id, EntityPlayer player, World world,
            int x, int y, int z) {

        TileEntity tile_entity = world.getBlockTileEntity(x, y, z);

        if (tile_entity instanceof InfuserTileEntity)
            return new InfuserGUI(player.inventory,
                    (InfuserTileEntity) tile_entity);

        if (tile_entity instanceof ImbuerTileEntity)
            return new ImbuerGUI(player.inventory,
                    (ImbuerTileEntity) tile_entity);

        if (tile_entity instanceof DiffuserTileEntity)
            return new DiffuserGUI(player.inventory,
                    (DiffuserTileEntity) tile_entity);

        return null;
    }

}
