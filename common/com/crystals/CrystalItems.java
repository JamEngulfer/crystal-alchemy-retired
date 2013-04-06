package com.crystals;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.item.Item;
import net.minecraft.util.Icon;

public abstract class CrystalItems extends Item {

    public Icon[] icons;

    public abstract String[] getTextureNames();

    public CrystalItems(int ItemID) {
        super(ItemID);
    }

    @Override
    public Icon getIconFromDamage(int par1) {
        return icons[par1];
    }

    @Override
    public void updateIcons(IconRegister iconRegister) {
        String[] textureNames = getTextureNames();
        icons = new Icon[textureNames.length];

        for (int i = 0; i < icons.length; ++i) {
            icons[i] = iconRegister.registerIcon("aitsgeology:"
                    + textureNames[i]);
        }
    }

}
