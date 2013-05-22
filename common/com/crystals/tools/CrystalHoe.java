package com.crystals.tools;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumToolMaterial;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.Event.Result;
import net.minecraftforge.event.entity.player.UseHoeEvent;

import com.crystals.CrystalMod;
import com.crystals.essence.Essence;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class CrystalHoe extends Item {

    protected EnumToolMaterial theToolMaterial;

    public CrystalHoe(int ItemID, EnumToolMaterial m, int tex, String name) {
        super(ItemID);
        theToolMaterial = m;
        maxStackSize = 1;
        this.setMaxDamage(m.getMaxUses());
        this.setCreativeTab(CrystalMod.alchemyWeaponTab);
        setUnlocalizedName("CrystalHoe");
    }

    @Override
    public boolean onItemUse(ItemStack par1ItemStack,
            EntityPlayer par2EntityPlayer, World par3World, int par4, int par5,
            int par6, int par7, float par8, float par9, float par10) {
        if (!par2EntityPlayer.canPlayerEdit(par4, par5, par6, par7,
                par1ItemStack))
            return false;
        else {
            UseHoeEvent event = new UseHoeEvent(par2EntityPlayer,
                    par1ItemStack, par3World, par4, par5, par6);
            if (MinecraftForge.EVENT_BUS.post(event))
                return false;
            if (event.getResult() == Result.ALLOW) {
                par1ItemStack.damageItem(1, par2EntityPlayer);
                return true;
            }
            int var11 = par3World.getBlockId(par4, par5, par6);
            int var12 = par3World.getBlockId(par4, par5 + 1, par6);
            if ((par7 == 0 || var12 != 0 || var11 != Block.grass.blockID)
                    && var11 != Block.dirt.blockID && var11 != Block.crops.blockID 
                    && var11 != Block.potato.blockID && var11 != Block.carrot.blockID)
                return false;
            else if (var11 == Block.grass.blockID || var11 == Block.dirt.blockID){
                Block var13 = Block.tilledField;
                par3World.playSoundEffect(par4 + 0.5F, par5 + 0.5F,
                        par6 + 0.5F, var13.stepSound.getStepSound(),
                        (var13.stepSound.getVolume() + 1.0F) / 2.0F,
                        var13.stepSound.getPitch() * 0.8F);
                if (par3World.isRemote)
                    return true;
                else {
                    par3World.setBlock(par4, par5, par6, var13.blockID);
                    par1ItemStack.damageItem(1, par2EntityPlayer);
                    return true;
                }
            } else if(var11 == Block.crops.blockID){
                Block var13 = Block.crops;
                par3World.playSoundEffect(par4 + 0.5F, par5 + 0.5F,
                        par6 + 0.5F, var13.stepSound.getStepSound(),
                        (var13.stepSound.getVolume() + 1.0F) / 2.0F,
                        var13.stepSound.getPitch() * 0.8F);
                
                if(getActiveEssence(par1ItemStack) == 3){
                    int weight = 10;

                    int random = 0 + (int)(Math.random() * ((100 - 0) + 1));
                    if(random < weight){
                        if(par2EntityPlayer.getFoodStats().getFoodLevel() < 20){
                            par2EntityPlayer.getFoodStats().setFoodLevel(par2EntityPlayer.getFoodStats().getFoodLevel() + 1);
                            par1ItemStack.damageItem(2, par2EntityPlayer);
                        }
                    }
                }

                if (par3World.isRemote)
                    return true;
                else if(getActiveEssence(par1ItemStack) == 5){

                    par3World.setBlock(par4, par5, par6, var13.blockID, 7, 0x04);
                    par1ItemStack.damageItem(2, par2EntityPlayer);
                    return true;
                }
            } else if(var11 == Block.potato.blockID){
                Block var13 = Block.potato;
                par3World.playSoundEffect(par4 + 0.5F, par5 + 0.5F,
                        par6 + 0.5F, var13.stepSound.getStepSound(),
                        (var13.stepSound.getVolume() + 1.0F) / 2.0F,
                        var13.stepSound.getPitch() * 0.8F);

                if(getActiveEssence(par1ItemStack) == 3){
                    int weight = 10;

                    int random = 0 + (int)(Math.random() * ((100 - 0) + 1));
                    if(random < weight){
                        if(par2EntityPlayer.getFoodStats().getFoodLevel() < 20){
                            par2EntityPlayer.getFoodStats().setFoodLevel(par2EntityPlayer.getFoodStats().getFoodLevel() + 1);
                            par1ItemStack.damageItem(2, par2EntityPlayer);
                        }
                    }
                }

                if (par3World.isRemote)
                    return true;
                else if(getActiveEssence(par1ItemStack) == 5){

                    par3World.setBlock(par4, par5, par6, var13.blockID, 7, 0x04);
                    par1ItemStack.damageItem(2, par2EntityPlayer);
                    return true;
                }
            } else if(var11 == Block.carrot.blockID){
                Block var13 = Block.carrot;
                par3World.playSoundEffect(par4 + 0.5F, par5 + 0.5F,
                        par6 + 0.5F, var13.stepSound.getStepSound(),
                        (var13.stepSound.getVolume() + 1.0F) / 2.0F,
                        var13.stepSound.getPitch() * 0.8F);

                if(getActiveEssence(par1ItemStack) == 3){
                    int weight = 10;

                    int random = 0 + (int)(Math.random() * ((100 - 0) + 1));
                    if(random < weight){
                        if(par2EntityPlayer.getFoodStats().getFoodLevel() < 20){
                            par2EntityPlayer.getFoodStats().setFoodLevel(par2EntityPlayer.getFoodStats().getFoodLevel() + 1);
                            par1ItemStack.damageItem(2, par2EntityPlayer);
                        }
                    }
                }

                if (par3World.isRemote)
                    return true;
                else if(getActiveEssence(par1ItemStack) == 5){

                    par3World.setBlock(par4, par5, par6, var13.blockID, 7, 0x04);
                    par1ItemStack.damageItem(2, par2EntityPlayer);
                    return true;
                }
            } 
        }
        return true;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public boolean isFull3D() {
        return true;
    }

    public String func_77842_f() {
        return theToolMaterial.toString();
    }

    @Override
    public void registerIcons(IconRegister iconRegister) {
        itemIcon = iconRegister.registerIcon("CrystalAlchemy:CrystalHoe");
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

                        if (Essence.essenceList[eID].essenceID == 3) {

                            information.add("Food Consumption");

                        }

                        if (Essence.essenceList[eID].essenceID == 5) {

                            information.add("Instant Growth");

                        }
                    }
                }
            }
        } else {
            information.add("No Ability");
        }

    }

    public int getActiveEssence(ItemStack itemStack) {
        ItemStack item = itemStack;
        if (item.stackTagCompound != null) {

            NBTTagList list = (NBTTagList) item.stackTagCompound
                    .getTag("essence");
            if (list != null) {

                if (item.hasTagCompound()) {

                    short eID = ((NBTTagCompound) list.tagAt(0))
                            .getShort("essenceID");

                    return (int) eID;
                }
            }
        }

        return -1;

    }
}
