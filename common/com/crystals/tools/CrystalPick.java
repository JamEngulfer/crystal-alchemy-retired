package com.crystals.tools;

import java.util.List;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumToolMaterial;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.world.World;

import com.crystals.CrystalMod;
import com.crystals.essence.Essence;

public class CrystalPick extends CrystalToolBase {

    public static final Block[] blocksEffectiveAgainst = new Block[] {
            Block.cobblestone, Block.stoneDoubleSlab, Block.stoneSingleSlab,
            Block.stone, Block.sandStone, Block.cobblestoneMossy,
            Block.oreIron, Block.blockIron, Block.oreCoal, Block.blockGold,
            Block.oreGold, Block.oreDiamond, Block.blockDiamond, Block.ice,
            Block.netherrack, Block.oreLapis, Block.blockLapis,
            Block.oreRedstone, Block.oreRedstoneGlowing, Block.rail,
            Block.railDetector, Block.railPowered, CrystalMod.AlchemyBlock,
            CrystalMod.AlchemyBlockEpic, CrystalMod.AlchemyBlockSuper,
            CrystalMod.BlockDiffuser, CrystalMod.BlockImbuer,
            CrystalMod.BlockInfuser, CrystalMod.MagicFuelBlock,
            CrystalMod.InertOre, CrystalMod.InertOreNether };

    public CrystalPick(int ItemID, EnumToolMaterial m, int tex, String name) {
        super(ItemID, 1, m, blocksEffectiveAgainst);
        this.setCreativeTab(CrystalMod.alchemyWeaponTab);
        setUnlocalizedName("CrystalPick");
    }

    @Override
    public boolean canHarvestBlock(Block par1Block) {
        return par1Block == Block.obsidian ? toolMaterial.getHarvestLevel() == 3
                : par1Block != Block.blockDiamond
                        && par1Block != Block.oreDiamond ? par1Block != Block.oreEmerald
                        && par1Block != Block.blockEmerald ? par1Block != Block.blockGold
                        && par1Block != Block.oreGold ? par1Block != Block.blockIron
                        && par1Block != Block.oreIron ? par1Block != Block.blockLapis
                        && par1Block != Block.oreLapis ? par1Block != Block.oreRedstone
                        && par1Block != Block.oreRedstoneGlowing ? par1Block.blockMaterial == Material.rock ? true
                        : par1Block.blockMaterial == Material.iron ? true
                                : par1Block.blockMaterial == Material.anvil
                        : toolMaterial.getHarvestLevel() >= 2
                        : toolMaterial.getHarvestLevel() >= 1
                        : toolMaterial.getHarvestLevel() >= 1
                        : toolMaterial.getHarvestLevel() >= 2
                        : toolMaterial.getHarvestLevel() >= 2
                        : toolMaterial.getHarvestLevel() >= 2;
    }

    @Override
    public float getStrVsBlock(ItemStack item, Block par2Block) {
        return par2Block != null
                && (par2Block.blockMaterial == Material.iron
                        || par2Block.blockMaterial == Material.anvil || par2Block.blockMaterial == Material.rock) ? efficiencyOnProperMaterial
                : super.getStrVsBlock(item, par2Block);
    }

    @Override
    public void registerIcons(IconRegister iconRegister) {
        itemIcon = iconRegister.registerIcon("CrystalAlchemy:CrystalPick");
    }
    
    public boolean onBlockDestroyed(ItemStack item, World world, int blockID, int x, int y, int z, EntityLiving entity)
    {
        if(item.stackTagCompound != null){
            NBTTagList list = (NBTTagList) item.stackTagCompound
                    .getTag("essence");
            if(list != null){
                if(item.hasTagCompound()){
                    short eID = ((NBTTagCompound) list.tagAt(0))
                            .getShort("essenceID");
                    if (Essence.essenceList[eID] != null) {
                        
                        if (Essence.essenceList[eID].essenceID == 2) {
                            world.createExplosion(entity, x, y, z, 3F, true);
                        }
                        
                        if (Essence.essenceList[eID].essenceID == 3) {
                            
                            int weight = 20;

                            int random = 0 + (int)(Math.random() * ((100 - 0) + 1));
                            if(random < weight){
                                if(entity.getHealth() < 18){
                                    entity.setEntityHealth(entity.getHealth() + 2);
                                }
                                
                                if(entity.getHealth() < 20 && entity.getHealth() > 18){
                                    entity.setEntityHealth(entity.getHealth() + 1);
                                }
                            }
                        }
                    }
                }
            }
        }
        
        return true;
    }
    
    public boolean canBlockBeBroken(int id){
        
        Block[] blockList = new Block[] {
                Block.cobblestone, Block.stoneDoubleSlab, Block.stoneSingleSlab,
                Block.stone, Block.sandStone, Block.cobblestoneMossy,
                Block.oreIron, Block.blockIron, Block.oreCoal, Block.blockGold,
                Block.oreGold, Block.oreDiamond, Block.blockDiamond, Block.ice,
                Block.netherrack, Block.oreLapis, Block.blockLapis,
                Block.oreRedstone, Block.oreRedstoneGlowing, Block.rail,
                Block.railDetector, Block.railPowered, CrystalMod.AlchemyBlock,
                CrystalMod.AlchemyBlockEpic, CrystalMod.AlchemyBlockSuper,
                CrystalMod.BlockDiffuser, CrystalMod.BlockImbuer,
                CrystalMod.BlockInfuser, CrystalMod.MagicFuelBlock,
                CrystalMod.InertOre, CrystalMod.InertOreNether 
        };
        
        for(int i = 0; i < blockList.length; i++){
            if(blockList[i].blockID == id){
                return true;
            }
        }
        return false;
    }
    
    public boolean canBlockHeal(int id){
        
        Block[] blockList = new Block[] {
                Block.oreIron, Block.oreCoal, 
                Block.oreGold, Block.oreDiamond,
                Block.oreLapis, Block.blockLapis,
                Block.oreRedstone, Block.oreRedstoneGlowing,
                CrystalMod.InertOre, CrystalMod.InertOreNether 
        };
        
        for(int i = 0; i < blockList.length; i++){
            if(blockList[i].blockID == id){
                return true;
            }
        }
        return false;
    }
    
    @Override
    public boolean onItemUse(ItemStack item, EntityPlayer player, World world, int x, int y, int z, int side, float xFloat, float yFloat, float zFloat)
    {

        Random rand = new Random();
        if (!player.canPlayerEdit(x, y, z, side, item))
        {
            return false;
        }
        if(world.getBlockId(x, y, z) != Block.bedrock.blockID){
            if(item.stackTagCompound != null){
                NBTTagList list = (NBTTagList) item.stackTagCompound
                        .getTag("essence");
                if(list != null){
                    if(item.hasTagCompound()){
                        short eID = ((NBTTagCompound) list.tagAt(0))
                                .getShort("essenceID");
                        if (Essence.essenceList[eID] != null) {

                            if (Essence.essenceList[eID].essenceID == 4) {
                                if(canBlockBeBroken(world.getBlockId(x, y, z))){
                                    world.destroyBlock(x, y, z, false);

                                    for (int l = 0; l < 4; ++l)
                                    {
                                        double d0 = (double)((float)x + rand.nextFloat());
                                        double d1 = (double)((float)y + rand.nextFloat());
                                        double d2 = (double)((float)z + rand.nextFloat());
                                        double d3 = 0.0D;
                                        double d4 = 0.0D;
                                        double d5 = 0.0D;

                                        d3 = ((double)rand.nextFloat() - 0.5D) * 0.5D;
                                        d4 = ((double)rand.nextFloat() - 0.5D) * 0.5D;
                                        d5 = ((double)rand.nextFloat() - 0.5D) * 0.5D;

                                        world.spawnParticle("portal", d0, d1, d2, d3, d4, d5);
                                    }
                                }
                                return true;
                            }
                        }
                    }
                }
            }
        }

        return true;
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

                        if (Essence.essenceList[eID].essenceID == 2) {

                            information.add("Explosive Effect");

                        }

                        if (Essence.essenceList[eID].essenceID == 3) {

                            information.add("Healing Effect");

                        }
                        
                        if (Essence.essenceList[eID].essenceID == 4) {

                            information.add("Instant Break Void");

                        }
                    }
                }
            }
        } else {
            information.add("No Ability");
        }

    }
}
