package com.crystals;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.EnumToolMaterial;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.EnumHelper;
import net.minecraftforge.common.MinecraftForge;

import com.crystals.blocks.MagicFuelBlock;
import com.crystals.diffuser.DiffuserTileEntity;
import com.crystals.imbuer.ImbuerTileEntity;
import com.crystals.infuser.InfuserTileEntity;
import com.crystals.items.MagicFuelCoal;
import com.crystals.ores.WorldGeneratorInertOre;
import com.crystals.ores.WorldGeneratorNetherInertOre;
import com.crystals.tabs.AlchemyCrystalTab;
import com.crystals.tabs.AlchemyFuelTab;
import com.crystals.tabs.AlchemyMachineTab;
import com.crystals.tabs.AlchemyWeaponTab;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.Init;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;

@Mod(modid = "CrystalAlchemy", name = "Crystal Alchemy", version = "0.03")
@NetworkMod(clientSideRequired = true, serverSideRequired = false, channels = { "CrystalMod" }, packetHandler = CrystalClientPacketHandler.class)
public class CrystalMod {

    @Instance
    public static CrystalMod instance = new CrystalMod();
    private GuiHandler guiHandler = new GuiHandler();
    private CrystalProxy proxy = new CrystalProxy();

    // Block ID Variables
    int MagicFuelBlockID = 525;
    int AlchemyBlockID = 526;
    int AlchemyBlockSuperID = 527;
    int AlchemyBlockEpicID = 528;

    // Ore ID Variables
    int InertOreID = 529;
    int InertOreNetherID = 530;

    // Container ID Variables
    int BlockInfuserID = 535;
    int BlockImbuerID = 536;
    int BlockDiffuserID = 537;

    // Item ID Variables
    int MagicFuelCoalID = 550;
    int MagicFuelCoalSuperID = 551;
    int InertCrystalID = 552;
    int InfusingStoneID = 553;
    int ImbuingStoneID = 554;
    int DiffusingStoneID = 555;

    // Tool ID Variables
    int CrystalSwordID = 570;
    int CrystalPickID = 571;
    int CrystalAxeID = 572;
    int CrystalSpadeID = 573;
    int CrystalHoeID = 574;

    // Essence ID Variables
    int BottleEssenceID = 559;
    int BasicEssenceID = 560;

    /*
     * //Block ID Variables int MagicFuelBlockID = 500; int AlchemyBlockID =
     * 501; int AlchemyBlockSuperID = 502; int AlchemyBlockEpicID = 503;
     * 
     * //Ore ID Variables int InertOreID = 525; int InertOreNetherID = 526;
     * 
     * //Container ID Variables int BlockInfuserID = 510; int BlockImbuerID =
     * 511; int BlockDiffuserID = 512;
     * 
     * //Item ID Variables int MagicFuelCoalID = 550; int MagicFuelCoalSuperID =
     * 551; int InertCrystalID = 552; int InfusionStoneID = 553; int
     * ImbuingStoneID = 554; int DiffusingStoneID = 555;
     * 
     * //Tool ID Variables int CrystalSwordID = 570; int CrystalPickID = 571;
     * int CrystalAxeID = 572; int CrystalSpadeID = 573; int CrystalHoeID = 574;
     */

    // Creative Tabs
    public static CreativeTabs alchemyFuelTab = new AlchemyFuelTab(
            "AlchemyFuel");
    public static CreativeTabs alchemyMachineTab = new AlchemyMachineTab(
            "AlchemyMachine");
    public static CreativeTabs alchemyCrystalTab = new AlchemyCrystalTab(
            "AlchemyCrystal");
    public static CreativeTabs alchemyWeaponTab = new AlchemyWeaponTab(
            "AlchemyWeapon");

    // Block Variables
    public static Block MagicFuelBlock;
    public static Block AlchemyBlock;
    public static Block AlchemyBlockSuper;
    public static Block AlchemyBlockEpic;

    // Ore Variables
    public static Block InertOre;
    public static Block InertOreNether;

    // Container Variables
    public static Block BlockInfuser;
    public static Block BlockImbuer;
    public static Block BlockDiffuser;

    // Item Variables
    public static Item MagicFuelCoal;
    public static Item MagicFuelCoalSuper;
    public static Item InfusingStone;
    public static Item ImbuingStone;
    public static Item DiffusingStone;
    public static Item InertCrystal;

    // Essence Variables
    public static Item BottleEssence;
    public static Item BasicEssence;

    // Tool Variables
    public static Item CrystalSword;
    public static Item CrystalPick;
    public static Item CrystalAxe;
    public static Item CrystalSpade;
    public static Item CrystalHoe;

    // Tool Material
    static EnumToolMaterial EnumToolMaterialCrystal = EnumHelper
            .addToolMaterial("Crystal", 1, 1024, 4.0F, 1, 15);

    // @SidedProxy(clientSide="com.crystals.ClientTextures", serverSide =
    // "com.crystals.CrystalProxy")
    // public static CrystalProxy proxy;

    @SuppressWarnings("deprecation")
    @Init
    public void load(FMLInitializationEvent event) {

        // Registering Hook Class
        MinecraftForge.EVENT_BUS.register(new CrystalHooks());

        // Item Creation
        MagicFuelCoal = new MagicFuelCoal(MagicFuelCoalID);
        MagicFuelCoalSuper = new com.crystals.items.MagicFuelCoalSuper(
                MagicFuelCoalSuperID);
        InfusingStone = new com.crystals.stones.InfusingStone(InfusingStoneID);
        ImbuingStone = new com.crystals.stones.ImbuingStone(ImbuingStoneID);
        DiffusingStone = new com.crystals.stones.DiffusingStone(
                DiffusingStoneID);

        // Tool Creation
        CrystalSword = new com.crystals.tools.CrystalSword(CrystalSwordID,
                EnumToolMaterialCrystal);
        CrystalPick = new com.crystals.tools.CrystalPick(CrystalPickID,
                EnumToolMaterialCrystal, 17, "Crystal Pickaxe");
        CrystalAxe = new com.crystals.tools.CrystalAxe(CrystalAxeID,
                EnumToolMaterialCrystal, 18, "Crystal Axe");
        CrystalSpade = new com.crystals.tools.CrystalSpade(CrystalSpadeID,
                EnumToolMaterialCrystal, 19, "Crystal Shovel");
        CrystalHoe = new com.crystals.tools.CrystalHoe(CrystalHoeID,
                EnumToolMaterialCrystal, 20, "Crystal Hoe");

        // Essence Creation
        BottleEssence = new com.crystals.essence.BottleEssence(BottleEssenceID);
        BasicEssence = new com.crystals.essence.BasicEssence(BasicEssenceID);

        // Imbued Crystal Creation
        InertCrystal = new com.crystals.stones.InertCrystal(InertCrystalID);

        // Item Registration
        LanguageRegistry.addName(MagicFuelCoal, "Alchemical Fuel");
        LanguageRegistry.addName(MagicFuelCoalSuper, "Infused Alchemical Fuel");
        LanguageRegistry.addName(InfusingStone, "Infusion Stone");
        LanguageRegistry.addName(ImbuingStone, "Imbuing Stone");
        LanguageRegistry.addName(DiffusingStone, "Diffusing Stone");

        // Essence Registration
        LanguageRegistry.addName(BottleEssence, "Empty Essence Bottle");
        // LanguageRegistry.addName(new ItemStack(CrystalMod.BasicEssence, 1,
        // 0), "Empty Essence Bottle");
        LanguageRegistry.addName(new ItemStack(CrystalMod.BasicEssence, 1, 1),
                "Luminous Essence");
        LanguageRegistry.addName(new ItemStack(CrystalMod.BasicEssence, 1, 2),
                "Explosive Essence");
        LanguageRegistry.addName(new ItemStack(CrystalMod.BasicEssence, 1, 3),
                "Fiery Essence");
        LanguageRegistry.addName(new ItemStack(CrystalMod.BasicEssence, 1, 4),
                "Life Essence");
        LanguageRegistry.addName(new ItemStack(CrystalMod.BasicEssence, 1, 5),
                "Energy Essence");
        LanguageRegistry.addName(new ItemStack(CrystalMod.BasicEssence, 1, 6),
                "Healing Essence");
        LanguageRegistry.addName(new ItemStack(CrystalMod.BasicEssence, 1, 7),
                "Lightning Essence");
        LanguageRegistry.addName(new ItemStack(CrystalMod.BasicEssence, 1, 8),
                "Fauna Essence");
        LanguageRegistry.addName(new ItemStack(CrystalMod.BasicEssence, 1, 9),
                "Growth Essence");
        LanguageRegistry.addName(new ItemStack(CrystalMod.BasicEssence, 1, 10),
                "Ender Essence");
        LanguageRegistry.addName(new ItemStack(CrystalMod.BasicEssence, 1, 11),
                "Nether Essence");
        LanguageRegistry.addName(new ItemStack(CrystalMod.BasicEssence, 1, 12),
                "Magic Essence");
        LanguageRegistry.addName(new ItemStack(CrystalMod.BasicEssence, 1, 13),
                "Void Essence");

        // Imbued Crystal Registration
        LanguageRegistry.addName(new ItemStack(CrystalMod.InertCrystal, 1, 0),
                "Inert Crystal");
        LanguageRegistry.addName(new ItemStack(CrystalMod.InertCrystal, 1, 1),
                "Luminous Crystal");
        LanguageRegistry.addName(new ItemStack(CrystalMod.InertCrystal, 1, 2),
                "Explosive Crystal");
        LanguageRegistry.addName(new ItemStack(CrystalMod.InertCrystal, 1, 3),
                "Firey Crystal");
        LanguageRegistry.addName(new ItemStack(CrystalMod.InertCrystal, 1, 4),
                "Life Crystal");
        LanguageRegistry.addName(new ItemStack(CrystalMod.InertCrystal, 1, 5),
                "Energy Crystal");
        LanguageRegistry.addName(new ItemStack(CrystalMod.InertCrystal, 1, 6),
                "Healing Crystal");
        LanguageRegistry.addName(new ItemStack(CrystalMod.InertCrystal, 1, 7),
                "Lightning Crystal");
        LanguageRegistry.addName(new ItemStack(CrystalMod.InertCrystal, 1, 8),
                "Fauna Crystal");
        LanguageRegistry.addName(new ItemStack(CrystalMod.InertCrystal, 1, 9),
                "Growth Crystal");
        LanguageRegistry.addName(new ItemStack(CrystalMod.InertCrystal, 1, 10),
                "Ender Crystal");
        LanguageRegistry.addName(new ItemStack(CrystalMod.InertCrystal, 1, 11),
                "Nether Crystal");
        LanguageRegistry.addName(new ItemStack(CrystalMod.InertCrystal, 1, 12),
                "Magic Crystal");
        LanguageRegistry.addName(new ItemStack(CrystalMod.InertCrystal, 1, 13),
                "Void Crystal");

        // Tool Registration
        LanguageRegistry.addName(CrystalSword, "Crystal Sword");
        LanguageRegistry.addName(CrystalPick, "Crystal Pickaxe");
        LanguageRegistry.addName(CrystalAxe, "Crystal Axe");
        LanguageRegistry.addName(CrystalSpade, "Crystal Shovel");
        LanguageRegistry.addName(CrystalHoe, "Crystal Hoe");

        // Block Creation
        MagicFuelBlock = new MagicFuelBlock(MagicFuelBlockID, Material.rock)
                .setHardness(5F).setLightValue(0.5F).setResistance(9F);
        InertOre = new com.crystals.ores.InertOre(InertOreID, 1, Material.rock)
                .setHardness(6F).setLightValue(0F).setResistance(3F);
        InertOreNether = new com.crystals.ores.NetherInertOre(InertOreNetherID,
                2, Material.rock).setHardness(4F).setLightValue(0F)
                .setResistance(3F);
        BlockInfuser = new com.crystals.infuser.BlockInfuser(BlockInfuserID,
                17, Material.rock).setHardness(6F).setResistance(6F);
        BlockImbuer = new com.crystals.imbuer.BlockImbuer(BlockImbuerID, 18,
                Material.rock).setHardness(6F).setResistance(6F);
        BlockDiffuser = new com.crystals.diffuser.BlockDiffuser(
                BlockDiffuserID, 19, Material.rock).setHardness(6F)
                .setResistance(6F);
        AlchemyBlock = new com.crystals.blocks.AlchemyBlock(AlchemyBlockID, 33,
                Material.rock).setHardness(4F).setLightValue(0.5F)
                .setResistance(5F);
        AlchemyBlockSuper = new com.crystals.blocks.AlchemyBlockSuper(
                AlchemyBlockSuperID, 34, Material.rock).setLightValue(0.5F)
                .setHardness(4F).setResistance(5F);
        AlchemyBlockEpic = new com.crystals.blocks.AlchemyBlockEpic(
                AlchemyBlockEpicID, 35, Material.rock).setLightValue(0.5F)
                .setHardness(4F).setResistance(5F);

        /*
         * Block Unlocalized name creation
         */

        MagicFuelBlock.setUnlocalizedName("MagicFuelBlock");
        InertOre.setUnlocalizedName("InertOre");
        InertOreNether.setUnlocalizedName("NetherInertOre");
        BlockInfuser.setUnlocalizedName("BlockInfuser");
        BlockDiffuser.setUnlocalizedName("BlockDiffuser");
        BlockImbuer.setUnlocalizedName("BlockImbuer");
        AlchemyBlock.setUnlocalizedName("AlchemyBlock");
        AlchemyBlockSuper.setUnlocalizedName("AlchemyBlockSuper");
        AlchemyBlockEpic.setUnlocalizedName("AlchemyBlockEpic");

        // Block registration
        GameRegistry.registerBlock(MagicFuelBlock);
        GameRegistry.registerBlock(AlchemyBlock);
        GameRegistry.registerBlock(AlchemyBlockSuper);
        GameRegistry.registerBlock(AlchemyBlockEpic);
        GameRegistry.registerBlock(BlockInfuser, "BlockInfuser");
        GameRegistry.registerBlock(BlockImbuer, "BlockImbuer");
        GameRegistry.registerBlock(BlockDiffuser, "BlockDiffuser");
        GameRegistry.registerBlock(InertOre);
        GameRegistry.registerBlock(InertOreNether);
        LanguageRegistry.addName(MagicFuelBlock, "Alchemical Fuel Block");
        LanguageRegistry.addName(AlchemyBlock, "Alchemy Block LV 1");
        LanguageRegistry.addName(AlchemyBlockSuper, "Alchemy Block LV 2");
        LanguageRegistry.addName(AlchemyBlockEpic, "Alchemy Block LV 3");
        LanguageRegistry.addName(InertOre, "Inert Ore");
        LanguageRegistry.addName(InertOreNether, "Nether Inert Ore");

        // Creative Tab Registration
        LanguageRegistry.instance().addStringLocalization(
                "itemGroup.AlchemyFuel", "en_US", "Alchemical Fuels and Misc");
        LanguageRegistry.instance().addStringLocalization(
                "itemGroup.AlchemyMachine", "en_US", "Alchemical Blocks");
        LanguageRegistry.instance().addStringLocalization(
                "itemGroup.AlchemyCrystal", "en_US", "Alchemical Crystals");
        LanguageRegistry.instance().addStringLocalization(
                "itemGroup.AlchemyWeapon", "en_US", "Alchemical Tools");

        // Container Registration
        GameRegistry.registerTileEntity(InfuserTileEntity.class, "Infuser");
        GameRegistry.registerTileEntity(ImbuerTileEntity.class, "Imbuer");
        GameRegistry.registerTileEntity(DiffuserTileEntity.class, "Diffuser");
        LanguageRegistry.addName(BlockInfuser, "Alchemical Infuser");
        LanguageRegistry.addName(BlockImbuer, "Alchemical Imbuer");
        LanguageRegistry.addName(BlockDiffuser, "Alchemical Diffuser");

        // WorldGenerator Registration
        GameRegistry.registerWorldGenerator(new WorldGeneratorInertOre());
        GameRegistry.registerWorldGenerator(new WorldGeneratorNetherInertOre());

        /*
         * Crafting Recipes
         */

        // Alchemical Fuel
        GameRegistry.addRecipe(new ItemStack(MagicFuelCoal, 4), new Object[] {
                "CCC", "CBC", "CCC", 'C', Item.coal, 'B', Item.blazePowder });

        // Alchemical Fuel Block
        GameRegistry.addRecipe(new ItemStack(MagicFuelBlock, 1), new Object[] {
                "FFF", "FOF", "FFF", 'F', MagicFuelCoalSuper, 'O',
                Block.obsidian });

        // Infusing Stone
        GameRegistry.addRecipe(new ItemStack(InfusingStone, 1), new Object[] {
                "GOG", "SIS", "GOG", 'G', Block.glass, 'O', Block.obsidian,
                'S', Item.slimeBall, 'I', InertCrystal });

        // Infusing Stone
        GameRegistry.addRecipe(new ItemStack(ImbuingStone, 1),
                new Object[] { "INI", "DAD", "INI", 'N',
                        CrystalMod.InfusingStone, 'D',
                        CrystalMod.DiffusingStone, 'A', Item.diamond, 'I',
                        InertCrystal });

        // Imbuing Stone
        GameRegistry.addRecipe(new ItemStack(DiffusingStone, 1), new Object[] {
                "GOG", "SIS", "GOG", 'G', Block.glass, 'O', Block.obsidian,
                'S', Item.enderPearl, 'I', InfusingStone });

        GameRegistry.addRecipe(new ItemStack(BottleEssence, 4), new Object[] {
                "GIG", "G G", "GGG", 'I', CrystalMod.InertCrystal, 'G',
                Block.glass, });

        /*
         * Alchemy Machines
         */

        GameRegistry.addRecipe(new ItemStack(BlockInfuser, 1), new Object[] {
                "ODO", "OEO", "OIO", 'O', Block.obsidian, 'E',
                CrystalMod.InfusingStone, 'I', CrystalMod.AlchemyBlock, 'D',
                Item.diamond });

        GameRegistry.addRecipe(new ItemStack(BlockDiffuser, 1), new Object[] {
                "ODO", "OEO", "OGO", 'O', Block.obsidian, 'E',
                CrystalMod.DiffusingStone, 'G', CrystalMod.AlchemyBlockSuper,
                'D', Item.diamond });

        GameRegistry.addRecipe(new ItemStack(BlockImbuer, 1), new Object[] {
                "ODO", "DED", "OBO", 'O', Block.obsidian, 'E',
                CrystalMod.ImbuingStone, 'B', CrystalMod.AlchemyBlockEpic, 'D',
                Item.diamond });

        /*
         * Alchemy Blocks
         */

        GameRegistry.addRecipe(new ItemStack(AlchemyBlock, 1), new Object[] {
                "ODO", "OEO", "OIO", 'O', Block.obsidian, 'E', Item.enderPearl,
                'I', Block.blockSteel, 'D', Item.diamond });

        GameRegistry.addRecipe(new ItemStack(AlchemyBlockSuper, 1),
                new Object[] { "ODO", "OEO", "OGO", 'O', Block.obsidian, 'E',
                        CrystalMod.AlchemyBlock, 'G', Block.blockGold, 'D',
                        Item.diamond });

        GameRegistry.addRecipe(new ItemStack(AlchemyBlockEpic, 1),
                new Object[] { "ODO", "OEO", "OBO", 'O', Block.obsidian, 'E',
                        CrystalMod.AlchemyBlockSuper, 'B', Block.blockDiamond,
                        'D', Item.diamond });

        /*
         * Crystal Tools
         */

        GameRegistry
                .addRecipe(new ItemStack(CrystalSword, 1), new Object[] { "I",
                        "I", "S", 'I', CrystalMod.InertCrystal, 'S',
                        Item.stick, });

        GameRegistry.addRecipe(new ItemStack(CrystalPick, 1),
                new Object[] { "III", "S", "S", 'I', CrystalMod.InertCrystal,
                        'S', Item.stick, });

        GameRegistry.addRecipe(new ItemStack(CrystalAxe, 1), new Object[] {
                "II", "SI", "S ", 'I', CrystalMod.InertCrystal, 'S',
                Item.stick, });

        GameRegistry.addRecipe(new ItemStack(CrystalAxe, 1), new Object[] {
                "II", "IS", " S", 'I', CrystalMod.InertCrystal, 'S',
                Item.stick, });

        GameRegistry
                .addRecipe(new ItemStack(CrystalSpade, 1), new Object[] { "I",
                        "S", "S", 'I', CrystalMod.InertCrystal, 'S',
                        Item.stick, });

        GameRegistry.addRecipe(new ItemStack(CrystalHoe, 1), new Object[] {
                "II", " S", " S", 'I', CrystalMod.InertCrystal, 'S',
                Item.stick, });

        GameRegistry.addRecipe(new ItemStack(CrystalHoe, 1), new Object[] {
                "II", "S ", "S ", 'I', CrystalMod.InertCrystal, 'S',
                Item.stick, });

        /*
         * Smelting Recipes
         */

        /*
         * Fuels
         */

        GameRegistry.registerFuelHandler(new CrystalFuel());
        NetworkRegistry.instance().registerGuiHandler(this, guiHandler);

        proxy.registerServerTickHandler();
    }

}
