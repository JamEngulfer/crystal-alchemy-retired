package com.crystals.diffuser;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.StatCollector;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class DiffuserGUI extends GuiContainer {

    private DiffuserTileEntity infuserInventory;

    public DiffuserGUI(InventoryPlayer player_inventory,
            DiffuserTileEntity tile_entity) {

        super(new ContainerDiffuser(tile_entity, player_inventory));
        infuserInventory = tile_entity;

    }

    @Override
    protected void drawGuiContainerForegroundLayer(int i, int j) {

        fontRenderer.drawString("Alchemical Diffuser", 6, 6, 0xffffff);
        fontRenderer.drawString(
                StatCollector.translateToLocal("container.inventory"), 6,
                ySize - 96 + 2, 0xffffff);
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float par1, int par2,
            int par3) {
        mc.renderEngine
                .getTexture("/CrystalAlchemy/textures/gui/DiffuserGUI.png");
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        mc.renderEngine
                .bindTexture("/mods/CrystalAlchemy/textures/gui/DiffuserGUI.png");
        int var5 = (width - xSize) / 2;
        int var6 = (height - ySize) / 2;
        this.drawTexturedModalRect(var5, var6, 0, 0, xSize, ySize);
        int var7;

        if (infuserInventory.isInfusing()) {
            var7 = infuserInventory.getInfuseTimeRemainingScaled(96);
            this.drawTexturedModalRect(var5 + 56, var6 + 36 + 12 - var7, 176,
                    12 - var7, 14, var7 + 2);
        }

        var7 = infuserInventory.getCookProgressScaled(24);
        this.drawTexturedModalRect(var5 + 79, var6 + 34, 176, 14, var7 + 1, 16);
    }
}
