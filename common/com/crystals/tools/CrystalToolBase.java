package com.crystals.tools;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.item.EnumToolMaterial;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeHooks;

import com.crystals.CrystalMod;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class CrystalToolBase extends Item {

    private Block[] blocksEffectiveAgainst;
    public float efficiencyOnProperMaterial = 0.4F;
    public int damageVsEntity;

    protected EnumToolMaterial toolMaterial;

    public CrystalToolBase(int ItemID, int Damage, EnumToolMaterial m,
            Block[] ea) {
        super(ItemID);
        toolMaterial = m;
        blocksEffectiveAgainst = ea;
        maxStackSize = 1;
        this.setMaxDamage(m.getMaxUses());
        efficiencyOnProperMaterial = m.getEfficiencyOnProperMaterial();
        damageVsEntity = Damage + m.getDamageVsEntity();
        this.setCreativeTab(CrystalMod.alchemyWeaponTab);
    }

    @Override
    public float getStrVsBlock(ItemStack item, Block block) {
        for (int i = 0; i < blocksEffectiveAgainst.length; i++) {
            if (blocksEffectiveAgainst[i] == block)
                return efficiencyOnProperMaterial;
        }
        return 1.0F;
    }

    @Override
    public boolean hitEntity(ItemStack item, EntityLiving entity1,
            EntityLiving entity2) {
        item.damageItem(2, entity2);
        return true;
    }

    @Override
    public boolean onBlockDestroyed(ItemStack item, World world, int block,
            int x, int y, int z, EntityLiving entity) {
        if (Block.blocksList[block].getBlockHardness(world, x, y, z) != 0.0D) {
            item.damageItem(1, entity);
        }
        return true;
    }

    @Override
    public int getDamageVsEntity(Entity entity) {
        return damageVsEntity;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public boolean isFull3D() {
        return true;
    }

    @Override
    public int getItemEnchantability() {
        return toolMaterial.getEnchantability();
    }

    public String getToolMaterialName() {
        return toolMaterial.toString();
    }

    @Override
    public boolean getIsRepairable(ItemStack item1, ItemStack item2) {
        return toolMaterial.getToolCraftingMaterial() == item2.itemID ? true
                : super.getIsRepairable(item1, item2);
    }

    @Override
    public float getStrVsBlock(ItemStack stack, Block block, int metadata) {
        if (ForgeHooks.isToolEffective(stack, block, metadata))
            return efficiencyOnProperMaterial;
        return getStrVsBlock(stack, block);
    }
}
