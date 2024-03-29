package com.crystals.recipes;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.minecraft.item.ItemStack;

import com.crystals.CrystalMod;

public class ImbuerRecipes {
    private static final ImbuerRecipes infusingBase = new ImbuerRecipes();

    /** The list of smelting results. */
    @SuppressWarnings("rawtypes")
    private Map infusingList = new HashMap();
    @SuppressWarnings("rawtypes")
    private Map crystalInfusingList = new HashMap();
    @SuppressWarnings("rawtypes")
    private Map experienceList = new HashMap();
    private HashMap<List<Integer>, ItemStack> metaInfusingList = new HashMap<List<Integer>, ItemStack>();
    private HashMap<List<Integer>, Float> metaExperience = new HashMap<List<Integer>, Float>();

    /**
     * Used to call methods addSmelting and getSmeltingResult.
     */
    public static final ImbuerRecipes infusing() {
        return infusingBase;
    }

    private ImbuerRecipes() {
        this.addInfusing(CrystalMod.BasicEssence.itemID, 1, new ItemStack(
                CrystalMod.InertCrystal, 1, 1), 0.7F);
        this.addInfusing(CrystalMod.BasicEssence.itemID, 2, new ItemStack(
                CrystalMod.InertCrystal, 1, 2), 0.7F);
        this.addInfusing(CrystalMod.BasicEssence.itemID, 3, new ItemStack(
                CrystalMod.InertCrystal, 1, 3), 0.7F);
        this.addInfusing(CrystalMod.CrystalSword.itemID, new ItemStack(
                CrystalMod.CrystalSword), 0.7F);
        this.addInfusing(CrystalMod.CrystalPick.itemID, new ItemStack(
                CrystalMod.CrystalPick), 0.7F);
        this.addInfusing(CrystalMod.CrystalSpade.itemID, new ItemStack(
                CrystalMod.CrystalSpade), 0.7F);
        this.addInfusing(CrystalMod.CrystalHoe.itemID, new ItemStack(
                CrystalMod.CrystalHoe), 0.7F);
        this.addInfusing(CrystalMod.CrystalAxe.itemID, new ItemStack(
                CrystalMod.CrystalAxe), 0.7F);
        
        //Secondary Crystals
        this.addCrystalInfusing(1, 2, new ItemStack(CrystalMod.InertCrystal, 1, 3), 0.7F);
        this.addCrystalInfusing(4, 5, new ItemStack(CrystalMod.InertCrystal, 1, 6), 0.7F);
        this.addCrystalInfusing(10, 11, new ItemStack(CrystalMod.InertCrystal, 1, 12), 0.7F);
        this.addCrystalInfusing(8, 4, new ItemStack(CrystalMod.InertCrystal, 1, 9), 0.7F);
        
        //Tertiary Crystals
        this.addCrystalInfusing(2, 3, new ItemStack(CrystalMod.InertCrystal, 1, 7), 0.7F);
        this.addCrystalInfusing(10, 12, new ItemStack(CrystalMod.InertCrystal, 1, 13), 0.7F);
    }

    /**
     * Adds an imbuing recipe.
     */
    @SuppressWarnings("unchecked")
    public void addInfusing(int par1, ItemStack par2ItemStack, float par3) {
        infusingList.put(Integer.valueOf(par1), par2ItemStack);
        experienceList.put(Integer.valueOf(par2ItemStack.itemID),
                Float.valueOf(par3));
    }
    
    @SuppressWarnings({ "unchecked", "rawtypes" })
    public void addCrystalInfusing(int firstMetadata, int secondMetadata, ItemStack par3ItemStack, float par4) {
        
        HashMap operators = new HashMap();
        operators.put(firstMetadata, secondMetadata);
        crystalInfusingList.put(operators, par3ItemStack);
        experienceList.put(Integer.valueOf(par3ItemStack.itemID),
                Float.valueOf(par4));
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
     * A metadata sensitive version of adding an imbuer recipe.
     */
    public void addInfusing(int itemID, int metadata, ItemStack itemstack,
            float experience) {
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
    
    @SuppressWarnings({ "rawtypes", "unchecked" })
    public ItemStack getCrystalInfusingResult(int firstResult, int secondResult) {
        
        HashMap check = new HashMap();
        check.put(firstResult, secondResult);
        HashMap revcheck = new HashMap();   
        revcheck.put(secondResult, firstResult);
        ItemStack ret = (ItemStack) crystalInfusingList.get(check);
        ItemStack revret = (ItemStack) crystalInfusingList.get(revcheck);
        if (ret != null || revret != null)
            if(ret == null){
                return revret;
            } else if (revret == null){
                return ret;
            }
        return null;
    }

    /**
     * Grabs the amount of base experience for this item to give when pulled
     * from the imbuer slot.
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
