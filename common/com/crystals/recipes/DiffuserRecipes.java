package com.crystals.recipes;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import com.crystals.CrystalMod;

public class DiffuserRecipes {
    private static final DiffuserRecipes infusingBase = new DiffuserRecipes();

    /** The list of smelting results. */
    @SuppressWarnings("rawtypes")
    private Map infusingList = new HashMap();
    @SuppressWarnings("rawtypes")
    private Map experienceList = new HashMap();
    private HashMap<List<Integer>, ItemStack> metaInfusingList = new HashMap<List<Integer>, ItemStack>();
    private HashMap<List<Integer>, Float> metaExperience = new HashMap<List<Integer>, Float>();

    /**
     * Used to call methods addSmelting and getSmeltingResult.
     */
    public static final DiffuserRecipes infusing() {
        return infusingBase;
    }

    private DiffuserRecipes() {
        this.addInfusing(Item.lightStoneDust.itemID, new ItemStack(
                CrystalMod.InertCrystal, 1, 1), 0.7F);
        this.addInfusing(Item.gunpowder.itemID, new ItemStack(
                CrystalMod.InertCrystal, 1, 2), 0.7F);
        this.addInfusing(Item.appleGold.itemID, new ItemStack(
                CrystalMod.InertCrystal, 1, 4), 0.7F);
        this.addInfusing(Item.seeds.itemID, new ItemStack(
                CrystalMod.InertCrystal, 1, 8), 0.7F);
        this.addInfusing(Block.sapling.blockID, new ItemStack(
                CrystalMod.InertCrystal, 1, 8), 0.7F);
        this.addInfusing(Item.enderPearl.itemID, new ItemStack(
                CrystalMod.InertCrystal, 1, 10), 0.7F);
        this.addInfusing(Item.netherStar.itemID, new ItemStack(
                CrystalMod.InertCrystal, 1, 11), 0.7F);
    }

    /**
     * Adds a smelting recipe.
     */
    @SuppressWarnings("unchecked")
    public void addInfusing(int par1, ItemStack par2ItemStack, float par3) {
        infusingList.put(Integer.valueOf(par1), par2ItemStack);
        experienceList.put(Integer.valueOf(par2ItemStack.itemID),
                Float.valueOf(par3));
    }

    /**
     * Returns the infusing result of an item. Deprecated in favor of a metadata
     * sensitive version
     */
    @Deprecated
    public ItemStack getSmeltingResult(int par1) {
        return (ItemStack) infusingList.get(Integer.valueOf(par1));
    }

    @SuppressWarnings("rawtypes")
    public Map getSmeltingList() {
        return infusingList;
    }

    @Deprecated
    // In favor of ItemStack sensitive version
    public float getExperience(int par1) {
        return experienceList.containsKey(Integer.valueOf(par1)) ? ((Float) experienceList
                .get(Integer.valueOf(par1))).floatValue() : 0.0F;
    }

    /**
     * A metadata sensitive version of adding a furnace recipe.
     */
    public void addInfusing(int itemID, int metadata, int stone,
            ItemStack itemstack, float experience) {
        metaInfusingList.put(Arrays.asList(itemID, metadata), itemstack);
        metaExperience.put(Arrays.asList(itemID, metadata), experience);
    }

    /**
     * Used to get the resulting ItemStack form a source ItemStack
     * 
     * @param item
     *            The Source ItemStack
     * @return The result ItemStack
     */
    public ItemStack getInfusingResult(ItemStack item) {
        if (item == null)
            return null;
        ItemStack ret = metaInfusingList.get(Arrays.asList(item.itemID,
                item.getItemDamage()));
        if (ret != null)
            return ret;
        return (ItemStack) infusingList.get(Integer.valueOf(item.itemID));
    }

    /**
     * Grabs the amount of base experience for this item to give when pulled
     * from the furnace slot.
     */
    public float getExperience(ItemStack item) {
        if (item == null || item.getItem() == null)
            return 0;
        float ret = item.getItem().getSmeltingExperience(item);
        if (ret < 0
                && metaExperience.containsKey(Arrays.asList(item.itemID,
                        item.getItemDamage()))) {
            ret = metaExperience.get(Arrays.asList(item.itemID,
                    item.getItemDamage()));
        }
        if (ret < 0 && experienceList.containsKey(item.itemID)) {
            ret = ((Float) experienceList.get(item.itemID)).floatValue();
        }
        return ret < 0 ? 0 : ret;
    }

    public Map<List<Integer>, ItemStack> getMetaInfusingList() {
        return metaInfusingList;
    }
}
