package com.crystals.essence;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;

public class EssenceHandler {

    public static void addEssence(Essence essence, ItemStack item) {
        if (item.stackTagCompound == null) {
            item.setTagCompound(new NBTTagCompound());
        }

        if (!item.stackTagCompound.hasKey("essence")) {
            item.stackTagCompound.setTag("essence", new NBTTagList("essence"));
        }

        NBTTagList list = (NBTTagList) item.stackTagCompound.getTag("essence");
        NBTTagCompound compound = new NBTTagCompound();
        compound.setShort("essenceID", (short) essence.essenceID);
        list.appendTag(compound);
    }

    public static boolean isEssenceApplied(ItemStack item) {
        return item.stackTagCompound != null
                && item.stackTagCompound.hasKey("essence");
    }

}
