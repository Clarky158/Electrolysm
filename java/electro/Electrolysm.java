/**
 *
 * @author Ben
 * TODO Fix NBT data saving in tile entity machines.
 */
package electro;

import java.io.File;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.registry.GameRegistry;
import electro.assemblySystem.*;
import electro.crafting.items.*;
import electro.handlers.*;
import electro.handlers.network.PacketHandler;
import electro.item.fuels.ItemImprovedCoal;
import electro.oreProccessing.*;
import electro.research.*;
import electro.research.crafting.ResearchCraftingHandler;
import electro.research.system.Research;
import electro.world.biome.*;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.world.WorldType;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.MinecraftForge;
import electro.block.ironFrames;
import electro.block.advMachines.charger;
import electro.block.advMachines.energiser;
import electro.block.advMachines.energisingRod;
import electro.block.advMachines.injectionArm;
import electro.block.advMachines.injector;
import electro.block.basic.blastBrick;
import electro.block.basic.blastDoor;
import electro.block.basic.blastGlass;
import electro.block.basic.blastProof;
import electro.block.basic.glassModifier;
import electro.block.basic.hammer;
import electro.block.basic.lightSource;
import electro.block.basic.modBlastGlass;
import electro.block.basic.stoneObsidian;
import electro.block.liquids.fluidStorage;
import electro.block.liquids.oil;
import electro.block.liquids.plasma;
import electro.block.machines.desk;
import electro.block.machines.researchDesk;
import electro.block.machines.workBench;
import electro.client.ClientProxy;
import electro.common.CommandStardate;
import electro.crafting.acidBurns;
//import assets.electrolysm.electro.handlers.CraftingHandler;
import electro.item.basic.drillCasing;
import electro.item.basic.plasmaDrill;
import electro.item.fuels.electroContain;
import electro.powerSystem.ItemWire;
import electro.powerSystem.basicStorage;
import electro.powerSystem.advancedStorage;
import electro.powerSystem.experimentalStorage;
import electro.powerSystem.endoCable;
import electro.powerSystem.energyMeter;
import electro.powerSystem.generators.generator;
import electro.powerSystem.generators.matterGen;
import electro.research.system.ResearchRegistry;
import electro.sciences.ItemArmorLab;
import electro.world.Scandium;
import electro.world.Yttrium;
import electro.world.chunkGraphite;
import electro.world.copperIngot;
import electro.world.copperOre;
import electro.world.graphite;
import electro.world.sulpherOre;
import electro.world.sulphur;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.registry.EntityRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraftforge.common.util.EnumHelper;

@Mod(modid = Referance.MOD_REF.MOD_ID, name = Referance.MOD_REF.MOD_ID, version = Referance.MOD_REF.VERSION)

public class Electrolysm
{
    private static String[] ContectedTexture = { "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", ""};

    public static CreativeTabs TabElectrolysm = new TabElectrolysm(CreativeTabs.getNextID(), "Electrolysm");
    //public static CreativeTabs TabElements = new TabElements(CreativeTabs.getNextID(), "Electrolysm|Elements & Wizardy");
    
    public static GUIHandler guiHandler = new GUIHandler();

    @Instance("Electrolysm")
    public static Electrolysm GUIInstance;
    //Basic Machines
    public static Block workBench = new workBench().setBlockName("workBech");
    public static Block desk = new desk().setBlockName("desk");
    //Advanced Machines
    public static Block injector = new injector().setBlockName("injector");
    public static Item injectionArm = new injectionArm();

    //Research System
    public static Block idifier = new idifier().setBlockName("id-ifier");
    public static Block researchDesk = new researchDesk().setBlockName("researchDesk");
    public static card card = new card();
    public static Item researchPaper = new researchPaper();
    public static Item knowledge = new knowledge();
    public static Item labCoat = new ItemArmorLab(2, "labCoat");
    public static Item labGoggles = new ItemArmorLab(0, "labGoggles");
    public static Item itemScanner = new scanner();
    public static Item researchDevice = new researchDevice();
    public static Block collector = new collector();
    public static Item reel = new ItemReel();
    //public static Item itemScanner = new itemScanner();
    //public static Block autoDesk = new autoDesk(IDHandler.research.autoDeskID, null);

    //World Generation
    public static Block graphite = new graphite().setBlockName("graphite");
    public static Item chunkGraphite = new chunkGraphite();
    public static Item copperIngot = new copperIngot();
    public static Block copperOre = new copperOre().setBlockName("copperOre");
    public static Block sulphurOre = new sulpherOre().setBlockName("sulfurOre");
    public static Item sulphur = new sulphur();
    public static Item ingots = new ingots();
    public static Item Scandium = new Scandium();
    public static Block Yttrium = new Yttrium().setBlockName("yttrium");

    //Biome
    public static Item spawnZS = new spawnZS();
    public static Block diseaseGrass = new diseasedGrass().setBlockName("diseasedGrass");
    public static final BiomeGenBase diseasedBiomeObj = new diseasedBiome(configHandler.biomeID);
    public BiomeGenBase diseasedBiome = diseasedBiomeObj;
    public static Block diseasedSapling = WorldGenDiseasedTree.initialiseTree("sapling");
    public static Block diseasedLog = WorldGenDiseasedTree.initialiseTree("log");
    public static Block diseasedLeaves = WorldGenDiseasedTree.initialiseTree("leaves");
    //See WorldGenDiseasedTree.java for tree blocks
    
    //Security
    public static Block blastProof = new blastProof().setBlockName("blastProof");
    public static Block blastBrick = new blastBrick().setBlockName("blastBrick");
    public static Block blastDoor = new blastDoor().setBlockName("blastDoor");
    public static Block blastGlass = new blastGlass(null, false, ContectedTexture).setBlockName("blastGlass");
    public static Block modBlastGlass = new modBlastGlass(null, false, ContectedTexture).setBlockName("blastGlassMod");
    public static Item glassModifire = new glassModifier();
    public static Block stoneObsidian = new stoneObsidian().setBlockName("stoneObsidian");
    //Tools
    public static Item hammer = new hammer();
    
    public static ItemArmor.ArmorMaterial PLASTIC = EnumHelper.addArmorMaterial("PLASTIC", 10, new int[]{1, 3, 2, 1}, 5);
    /*public static Item hazMatHat = new HazMap(configHandler.hazMat1ID, 0);
    public static Item hazMatChest = new HazMap(configHandler.hazMat2ID, 1);
    public static Item hazMatLegs = new HazMap(configHandler.hazMat3ID, 2);
    public static Item hazMatShoes = new HazMap(configHandler.hazMat4ID, 3);*/
    
    //Advanced atomics
    //Liquids
    public static Block plasma = new plasma().setBlockName("plasma");
    public static Item fluidStorage = new fluidStorage();
    public static Item fluidRegistry/* = new fluidRegistry(IDHandler.advAtomics.fluid.fluidRegistryID)*/;
    public static Block oil = new oil().setBlockName("oil");
    //Atomics
    //public static Item elementProof = new elementProof(IDHandler.elements.elementProofID);

    //Machines
    //public static Block quantumComp = new quantumComp(IDHandler.advMachines.quantumCompID, null);
    public static Block energiser = new energiser().setBlockName("energiser");
    public static Item energisingRod = new energisingRod();
    public static Block charger = new charger().setBlockName("charger");
    //Items
    //Tools
    public static Item plasmaDrill = new plasmaDrill();
    public static Item drillCasing = new drillCasing();

    //Power System
    public static Block endoCable = new endoCable().setBlockName("endoCable");
    public static Item ItemWire = new ItemWire();
    public static Item basicStorage = new basicStorage(1000, 0).setUnlocalizedName("basicStorage");
    public static Item advancedStorage = new advancedStorage(8000, 1).setUnlocalizedName("advancedStorage");
    public static Item experimentalStorage = new experimentalStorage(64000, 2).setUnlocalizedName("experimentalStorage");

    /*
    public static Block teslaTowerCore = new teslaTowerCore(configHandler.teslaCoreID, null);
    public static Block largeCopperCoil = new largeCopperCoil(configHandler.largeCopperCoilID, null,
            false, ContectedTexture);
    public static Item copperCoil = new copperCoil(IDHandler.powerGrid.copperCoilID);
    public static Block plug = new plug(configHandler.plugID, null);
    */public static Block generator = new generator().setBlockName("coalGen");
    public static Block matterGen = new matterGen().setBlockName("matterGen");
    public static Block coolerProccesser ;//= new coolerProccesser(configHandler.coolerProccesserID, null);
   // public static Block coolerPort = new coolerPort(configHandler.coolerPortID, null);
   // public static Block coolerFan = new coolerFan(configHandler.coolerFanID, null);

    /*
    public static Item crystal1 = new crystal(IDHandler.powerGrid.crystalID);
    public static Block wire = new wire(IDHandler.powerGrid.wireID, null);
    public static Block advWire = new wire(IDHandler.powerGrid.advWireID, null);
    public static Block earther = new earther(IDHandler.powerGrid.eartherID, null);
    public static Block advEarther = new advEarther(IDHandler.powerGrid.advEartherID, null);
    public static Item keyTransCoppier = new keyTransCoppier(IDHandler.powerGrid.keyTransCoppierID);
    public static Item ItemWire = new ItemWire(IDHandler.powerGrid.ItemWireID);
    */
    public static Item energyMeter = new energyMeter();
    //Random Blocks
    public static Block ironFrames = new ironFrames().setBlockName("ironFrames");

    //Ore Proccessing
    public static BlockContainer crusher = (BlockContainer) new crusher(false)
    				.setCreativeTab(TabElectrolysm).setBlockName("crusher");
    public static Block liquidiser = new liquidiser(false).setBlockName("liquidiser");
    public static Block electrolisisCore = new electrolisisCore().setBlockName("electrolysisCore");
    public static Block electrolChamber = new electrolChamber(null, false, ContectedTexture).setBlockName("electrolChamber");
    public static Block seperator = new seporator(false).setBlockName("seperator");
    public static BlockContainer smeltory = (BlockContainer) new smeltory(false).setCreativeTab(TabElectrolysm).setBlockName("smeltory");
    public static Item impureDusts = new impureDusts();
    public static Item dusts = new dusts();
    public static Item node = new node();
    public static Block sulpuricAcid = new sulphuricAcid().setBlockName("sulphuricAcid");
    public static Item neutraliser = new ItemNeutraliser();
    public static Block nettedBlock = new nettedBlock().setBlockName("nettedBlock");
    public static Item net = new net();
    public static Item crystal = new crystalOre();
    
    public static Block crusherActive = new crusher(true).setBlockName("crusherActive");
    public static Block smeltoryActive = new smeltory(true).setBlockName("smeltoryActive");

    // items for crafting
    public static Item diamondShard = new diamondShard();
    public static Item luminousRedstone = new luminousRedstone();
    public static Block BlockLumRed = new BlockLumRed().setBlockName("blockLumeRed");
    public static Item crystalBase = new crystalBase();
    public static Item ballOfPlastic = new ballOfPlastic();
    public static Item endoInsulator = new endoInsulator();
    public static Item microchipBoard = new microchipBoard();
    public static Item transistor = new transistor();
    public static Item CPU = new CPU();
    public static Item BasicMicrochip = new BasicMicrochip();
    public static Item advancedMicrochip = new advancedMicrochip();
    public static Item grindStone = new grindStone();
    public static Item inkAndQuill = new inkAndQuill();
    public static Item experimentalMicrochip = new experimentalMicrochip();
    public static Item advancedCPU = new advancedCPU();
    public static Item graphiteRod = new graphiteRod();
    public static Item electroMagnet = new ItemCrafting().setUnlocalizedName("electMag");
    public static Item film = new ItemCrafting().setUnlocalizedName("filmStrip");
    public static Item nitrocellulose = new ItemCrafting().setUnlocalizedName("nitrocellulose");
    public static Block nitrocelluloseBlock = new BlockCrafting(1F, "nitrocelluloseBlock", Material.cloth);
    /**
    *need to finish the nitrocellulose stuff!!
    *http://en.wikipedia.org/wiki/Nitrocellulose
    *http://en.wikipedia.org/wiki/Camphor
    *http://goo.gl/XWIi6Z
    */
    public static Potion acidBurns = new acidBurns(configHandler.acidBurnsID, true, 0);

    public static Block antiMatterCasing = new antiMatterCasing().setBlockName("antiMatterCasing");
    public static Block magnet = new magnet().setBlockName("magnet");
    //Fuels
    public static Item electroContain = new electroContain();
    public static Item improvedCoal = new ItemImprovedCoal();

    //Assembly Systems
    public static Block advancedCrafting = new advancedCrafting();
    public static Block roboticBase = new BlockCrafting(5F, "roboticBase", Material.iron).setBlockName("roboticBase");
    public static Block robotArm = new roboticArm();
    public static Item blueprint = new ItemBluePrint();
    public static Block matrix = new BlockMatrix();

    /*
    //Robots
    //Parts
    public static Item metalSheet = new metalSheet(IDHandler.robotics.metalSheetID);
    public static Item wire = new wire(IDHandler.robotics.wireID);
    public static Item servo = new servo(IDHandler.robotics.servoID);
    public static Item artMuscle = new artMuscle(IDHandler.robotics.artMuscleID);
    public static Item carbonBone = new carbonBone(IDHandler.robotics.carbonBoneID);
    //=====================Adv. Parts================================
    public static Item microController = new microCont(IDHandler.robotics.microContID);
    public static Item upgrade = new upgrade(IDHandler.robotics.upgradeID);
    public static Item silChip = new silChip(IDHandler.robotics.silChipID);
    public static Item ChipDup = new chipDup(IDHandler.robotics.chipDub);
    //===============================================================
    public static Item exoPlate = new ExoPlate(IDHandler.robotics.exoPlateID);
    public static Item bionicArm = new bionicArm(IDHandler.robotics.bionicArmID);
    public static Item bionicChest = new bionicChest(IDHandler.robotics.bionicChestID);
    public static Item bionicHead = new bionicHead(IDHandler.robotics.bionicHeadID);
    public static Item bionicLeg = new bionicLeg(IDHandler.robotics.bionicLegID);
    //====================Machines!==================================
    public static Block soldering = new soldering(IDHandler.robotics.machines.solderingID, null);
    public static Block partAssemb = new partAssemb(IDHandler.robotics.machines.partAssembID, null);
    */
    /*
     * ===============================================================================================================
     * ===============================================================================================================
     * 										Config (In game Stuff)
     * ===============================================================================================================
     * ===============================================================================================================
     */
    
    long startTime;
    
    @EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
    	startTime = System.currentTimeMillis();

    	//ResearchHandler.downloadLabSkin();
        File configFile = new File("config/Electrolysm/Electrolysm.cfg");
        new ResearchRegistry(true);
        PacketHandler.init();
        configHandler.init(configFile);
        VersionCheck.check();
        NewsCheck.check();
        BetaHandler.beta();
        DownloadHandler.downloadAndExtractResearchData();
        RegisterBlock.register();
        RegisterBlock.registerItems();
        new MultipartHandler();
        new ElectrolysmLootHandler();
        Names.addName();
        Register.addOreDictionary();
        TileEntityMappingHandler.addMappings();
        //GameRegistry.registerCraftingHandler(new CraftingHandler());
        BiomeDictionary.registerBiomeType(diseasedBiome);
        EntityRegistry.registerModEntity(EntityZombie_Scientist.class, "Zombie Scientist", 2, this, 80, 3, true);


        NetworkRegistry.INSTANCE.registerGuiHandler(this, new GUIHandler());
        MinecraftForge.EVENT_BUS.register(new ElectroEventHandler());

        FMLCommonHandler.instance().bus().register(new TickHandler());

        long duration = (System.currentTimeMillis() - startTime);
        //LoggerHandler.info("Electrolysm Started in " + duration + "ms");
    }

    @EventHandler
    public void init(FMLInitializationEvent event)
    {
        Crafting.addCrafting();
        Crafting.addFurnaceRecipes();
    }

    @SideOnly(Side.CLIENT)
    @EventHandler
    public void load(FMLInitializationEvent event)
    {
    	ClientProxy ClientProxy = new ClientProxy();
        ClientProxy.registerRenderThings();
    }

    //@ServerStarting
    public void serverLoad(FMLServerStartingEvent event)
    {
        event.registerServerCommand(new CommandStardate());
    }
}
