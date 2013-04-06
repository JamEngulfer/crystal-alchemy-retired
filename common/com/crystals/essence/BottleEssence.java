package com.crystals.essence;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.item.Item;

import com.crystals.CrystalMod;

public class BottleEssence extends Item {

    public BottleEssence(int ItemID) {
        super(ItemID);
        setCreativeTab(CrystalMod.alchemyCrystalTab);
        setHasSubtypes(true);
        setMaxDamage(0);
        maxStackSize = 16;
        setUnlocalizedName("BottleEssence");
    }

    @Override
    public void updateIcons(IconRegister iconRegister) {
        iconIndex = iconRegister.registerIcon("CrystalAlchemy:EmptyEssence");
    }
}
