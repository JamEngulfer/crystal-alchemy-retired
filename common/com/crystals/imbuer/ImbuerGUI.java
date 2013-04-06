package com.crystals.imbuer;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.StatCollector;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ImbuerGUI extends GuiContainer {

    private ImbuerTileEntity imbuerInventory;

    public ImbuerGUI(InventoryPlayer player_inventory,
            ImbuerTileEntity tile_entity) {

        super(new ContainerImbuer(tile_entity, player_inventory));
        imbuerInventory = tile_entity;

    }

    @Override
    protected void drawGuiContainerForegroundLayer(int i, int j) {

        fontRenderer.drawString("Alchemical Imbuer", 6, 6, 0xffffff);
        fontRenderer.drawString(
                StatCollector.translateToLocal("container.inventory"), 6,
                ySize - 96 + 2, 0xffffff);
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float par1, int par2,
            int par3) {
        // int var4 =
        // this.mc.renderEngine.getTexture("/CrystalAlchemy/textures/gui/ImbuerGUI.png");
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        mc.renderEngine
                .bindTexture("/mods/CrystalAlchemy/textures/gui/ImbuerGUI.png");
        int var5 = (width - xSize) / 2;
        int var6 = (height - ySize) / 2;
        this.drawTexturedModalRect(var5, var6, 0, 0, xSize, ySize);
        int var7;

        if (imbuerInventory.isImbuing()) {
            var7 = imbuerInventory.getImbueTimeRemainingScaled(12);
            this.drawTexturedModalRect(var5 + 56, var6 + 36 + 12 - var7, 176,
                    12 - var7, 14, var7 + 2);
        }

        var7 = imbuerInventory.getCookProgressScaled(24);
        this.drawTexturedModalRect(var5 + 79, var6 + 34, 176, 14, var7 + 1, 16);
    }
}
