package electro;

import java.io.File;

import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.item.Item;
import net.minecraftforge.common.Configuration;
import electro.handlers.BetaHandler;
import electro.handlers.IDHandler;
import electro.oreProccessing.crusher;
import electro.oreProccessing.dusts;
import electro.oreProccessing.electrolChamber;
import electro.oreProccessing.electrolPort;
import electro.oreProccessing.electrolisisCore;
import electro.oreProccessing.impureDusts;
import electro.oreProccessing.liquidiser;
import electro.oreProccessing.node;
import electro.oreProccessing.seporator;
import electro.oreProccessing.smeltory;
import electro.oreProccessing.sulphuricAcid;
import electro.powerSystem.energyMeter;
import electro.powerSystem.generators.generator;
import electro.powerSystem.generators.matterGen;

public class configHandler
{
    static
    {
        File configFile = new File("config/Electrolysm/Electrolysm.cfg");
        init(configFile);
    }
    public static String BLOCK_CAT = "BLOCKS";
    public static String ITEM_CAT = "ITEMS";
    public static String OTHER_CAT = "OTHER";
    public static int connectedTexturesMode = 2;
    public static String modID;
    public static int modIDInt;
    public static boolean idSet;
    public static boolean idSetD;
    //MultiPart Compaterbility
    public static boolean multipart;
    
    //IDs - Blocks
    public static int workBenchID;
    public static int deskID;
    public static int injectorID;
    public static int researchDeskID;
    public static int graphiteID;
    public static int copperOreID;
    public static int sulphurOreID;
    public static int diseaseGrassID;
    public static int blastProofID;
    public static int blastDoorID;
    public static int blastGlassID;
    public static int modBlastGlassID;
    public static int stoneObsidianID;
    public static int plasmaID;
    public static int energiserID;
    public static int teslaCoreID;
    public static int largeCopperCoilID;
    public static int plugID;
    public static int generatorID;
    public static int matterGenID;
    public static int ironFrameID;
    public static int crusherID;
    public static int liquidizerID;
    public static int electrolysisCoreID;
    public static int electrolChamberID;
    public static int seperatorID;
    public static int smelteryID;
    public static int sulphuricAcidID;
    public static int lumRedBlockID;
    public static int oilID;
    public static int idifierID;
    public static int YttriumID;
    public static int blastBrickID;
    public static int chargerID;
    public static int endoCableID;
    public static int nettedBlockID;
    public static int crusherActiveID;
    public static int smeltoryActiveID;
    public static int lumeRedBlockID;
    public static int lightSourceID;
    public static int antiMatterCasingID;
    public static int magnetID;
    public static int coolerProccesserID;
    public static int coolerPortID;
    public static int coolerFanID;
    public static int treeLogID;
    public static int treeLeavesID;
    public static int treeSaplingID;
    
    //IDs - Items
    public static int ballOfPlasticID;
    public static int injectionArmID;
    public static int cardID;
    public static int researchPaperID;
    public static int knowledgeID;
    public static int chunkGraphiteID;
    public static int copperIngotID;
    public static int sulfurID;
    public static int ingotsID;
    public static int ScandiumID;
    public static int electroContainID;
    public static int ballPlasticID;
    public static int endoInsulatorID;
    public static int microBoardID;
    public static int transistorID;
    public static int CPUID;
    public static int basicMicroChipID;
    public static int advMicrochipID;
    public static int grindStoneID;
    public static int inkQuillID;
    public static int expMicrochipID;
    public static int advCPUID;
    public static int graphiteRodID;
    public static int electroMagnetID;
    public static int diamondShardID;
    public static int luminousRedID;
    public static int crystalBaseID;
    public static int netID;
    public static int oreCrystalsID;
    public static int impureDustsID;
    public static int dustsID;
    public static int nodeID;
    public static int energyMeterID;
    public static int spawnZSID;
    public static int glassModifierID;
    public static int hammerID;
    public static int fluidStorageID;
    public static int plasmaDrillID;
    public static int drillCasingID;
    public static int energisingRodID;
    public static int ItemWireID;
    public static int hazMat1ID;
    public static int hazMat2ID;
    public static int hazMat3ID;
    public static int hazMat4ID;
    
    public static int battery1ID;
    public static int battery2ID;
    public static int battery3ID;
    public static int battery4ID;

    public static int labCoatID;
    public static int labGogglesID;
    public static int itemScannerID;
    
    public static int biomeID;
	public static int acidBurnsID;
    
    //etc. Do them in order so you dont get confused
    
    static 
    {
    	File configFile = new File("config/Electrolysm/Electrolysm.cfg");
        init(configFile);
    }

    public static void init(File file)
    {
        Configuration config = new Configuration(file);
        config.load();
        modID = (config.get("debuging", "Mod_ID", "Gamer").toString());
        modIDInt = (config.get("debuging", "modID as int", BetaHandler.setID(), "ModID").getInt());
        idSet = (config.get("debuging", "IDSet", idSetD).getBoolean(false));
        //IDs - Blocks
        workBenchID = config.getBlock("workBenchID", IDHandler.machines.basic.workBenchID).getInt(IDHandler.machines.basic.workBenchID);
        deskID = config.getBlock("DeskID", IDHandler.machines.basic.deskID).getInt(IDHandler.machines.basic.deskID);
        injectorID = config.getBlock("injectorID", IDHandler.advMachines.injectorID).getInt(IDHandler.advMachines.injectorID);
        researchDeskID = config.getBlock("researchDeskID", IDHandler.machines.basic.researchDeskID).getInt(IDHandler.machines.basic.researchDeskID);
        graphiteID = config.getBlock("graphiteID", IDHandler.worldGenOres.graphiteID).getInt(IDHandler.worldGenOres.graphiteID);
        copperOreID = config.getBlock("coppweOreID", IDHandler.worldGenOres.copperOreID).getInt(IDHandler.worldGenOres.copperOreID);
        sulphurOreID = config.getBlock("sulphurOreID", IDHandler.worldGenOres.sulphurOreID).getInt(IDHandler.worldGenOres.sulphurOreID);
        diseaseGrassID = config.get(config.CATEGORY_BLOCK, "diseaseGrassID", IDHandler.basic.diseasedGrassID).getInt(IDHandler.basic.diseasedGrassID);
        blastProofID = config.getBlock("blastProofID", IDHandler.basic.blastProofID).getInt(IDHandler.basic.blastProofID);
        blastDoorID = config.getBlock("balstDoorID", IDHandler.basic.blastDoorID).getInt(IDHandler.basic.blastDoorID);
        blastGlassID = config.getBlock("blastGlassID", IDHandler.basic.blastGlassID).getInt(IDHandler.basic.blastGlassID);
        modBlastGlassID = config.getBlock("modBlastGlassID", IDHandler.basic.modBlastGlassID).getInt(IDHandler.basic.modBlastGlassID);
        stoneObsidianID = config.getBlock("stoneObsidianID", IDHandler.basic.stoneObsidianID).getInt(IDHandler.basic.stoneObsidianID);
        plasmaID = config.getBlock("plasmaID", IDHandler.advAtomics.plasmaID).getInt(IDHandler.advAtomics.plasmaID);
        energiserID = config.getBlock("energiserID", IDHandler.advMachines.energiserID).getInt(IDHandler.advMachines.energiserID);
        teslaCoreID = config.getBlock("tesleTowerCoreID", IDHandler.powerGrid.teslaCoreID).getInt(IDHandler.powerGrid.teslaCoreID);
        largeCopperCoilID = config.getBlock("largeCopperCoilID", IDHandler.powerGrid.largeCopperCoilID).getInt(IDHandler.powerGrid.largeCopperCoilID);
        plugID = config.getBlock("plaugID", IDHandler.powerGrid.plugID).getInt(IDHandler.powerGrid.plugID);
        generatorID = config.getBlock("generatorID", IDHandler.powerGrid.generatorID).getInt(IDHandler.powerGrid.generatorID);
        matterGenID = config.getBlock("matterGeneratorID", IDHandler.powerGrid.genMatterID).getInt(IDHandler.powerGrid.genMatterID);
        ironFrameID = config.getBlock("ironFrameID", IDHandler.blocks.ironFramesID).getInt(IDHandler.blocks.ironFramesID);
        crusherID = config.getBlock("crusherID", IDHandler.oreProccessing.crusherID).getInt(IDHandler.oreProccessing.crusherID);
        liquidizerID = config.getBlock("liquidizerID", IDHandler.oreProccessing.liquidiserID).getInt(IDHandler.oreProccessing.liquidiserID);
        electrolysisCoreID = config.getBlock("electrolysisCoreID", IDHandler.oreProccessing.electrolisisCoreID).getInt(IDHandler.oreProccessing.electrolisisCoreID);
        electrolChamberID = config.getBlock("electrolChamberID", IDHandler.oreProccessing.electrolChamberID).getInt(IDHandler.oreProccessing.electrolChamberID);
        seperatorID = config.getBlock("seperatorID", IDHandler.oreProccessing.seporatorID).getInt(IDHandler.oreProccessing.seporatorID);
        smelteryID = config.getBlock("smelteryID", IDHandler.oreProccessing.smelteryID).getInt(IDHandler.oreProccessing.smelteryID);
        sulphuricAcidID = config.getBlock("sulphuricAcidID", IDHandler.oreProccessing.sulphuricAcidID).getInt(IDHandler.oreProccessing.sulphuricAcidID);
        lumRedBlockID = config.getBlock("lumRedBlockID", IDHandler.craftingItems.BlockLumRedID).getInt(IDHandler.craftingItems.BlockLumRedID);
        oilID = config.getBlock("oilID", IDHandler.advAtomics.oilID).getInt(IDHandler.advAtomics.oilID);
        idifierID = config.getBlock("id-ifierID", IDHandler.research.idifierID).getInt();
        YttriumID = config.getBlock("YttriumID", IDHandler.worldGenOres.YttriumID).getInt();
        blastBrickID = config.getBlock("blastBrickID", IDHandler.craftingItems.blastBrickID).getInt();
        chargerID = config.getBlock("chargerID", IDHandler.advMachines.chargerID).getInt();
        endoCableID = config.getBlock("endoCableID", IDHandler.powerGrid.wireID).getInt();
        nettedBlockID = config.getBlock("nettedBlockID", IDHandler.oreProccessing.nettedBlockID).getInt();
        crusherActiveID = config.getBlock("crusherActiveID", IDHandler.oreProccessing.crusherActiveID).getInt();
        smeltoryActiveID = config.getBlock("smeltoryActiveID", IDHandler.oreProccessing.smeltoryActiveID).getInt();
        lumeRedBlockID = config.getBlock("lumeRedBlockID", IDHandler.craftingItems.BlockLumRedID).getInt();
        lightSourceID = config.getBlock("lightSourceID", IDHandler.craftingItems.lightSourceID).getInt();
        antiMatterCasingID = config.getBlock("antiMatterCasingID", IDHandler.craftingItems.antiMatterCasingID).getInt();
        magnetID = config.getBlock("magnetID", IDHandler.craftingItems.magnetID).getInt();
        coolerPortID = config.getBlock("coolerPortID", IDHandler.powerGrid.extra.coolerPortID).getInt();
        coolerProccesserID = config.getBlock("coolerProccesserID", IDHandler.powerGrid.extra.coolerProccesserID).getInt();
        coolerFanID = config.getBlock("coolerFanID", IDHandler.powerGrid.extra.coolerFanID).getInt();
        treeLogID = config.getBlock("treeLogID", IDHandler.basic.treeLogID).getInt();
        treeLeavesID = config.getBlock("treeLeavesID", IDHandler.basic.treeLeavesID).getInt();
        treeSaplingID = config.getBlock("treeSaplingID", IDHandler.basic.treeSaplingID).getInt();
        
        //Items
        ballOfPlasticID = config.getItem("ballOfPlasticID", IDHandler.craftingItems.ballOfPlasticID).getInt(IDHandler.craftingItems.ballOfPlasticID);
        injectionArmID = config.getItem("injectionArmID", IDHandler.advMachines.injectionArmID).getInt();
        cardID = config.getItem("cardID", IDHandler.research.cardID).getInt();
        researchPaperID = config.getItem("researchPaperID", IDHandler.research.paperID).getInt();
        knowledgeID = config.getItem("knowledgeID", IDHandler.research.knowledgeID).getInt();
        chunkGraphiteID = config.getItem("chunkGraphiteID", IDHandler.worldGenOres.chuckGraphiteID).getInt();
        copperIngotID = config.getItem("copperIngotID", IDHandler.worldGenOres.copperIngotID).getInt();
        sulfurID  = config.getItem("sulfurID", IDHandler.worldGenOres.sulphurID).getInt();
        ingotsID = config.getItem("ingotsID", IDHandler.worldGenOres.modIngotID).getInt();
        ScandiumID = config.getItem("ScandiumID", IDHandler.worldGenOres.ScandiumID).getInt();
        electroContainID = config.getItem("electroContainID", IDHandler.fuels.electroContainID).getInt();
        ballPlasticID = config.getItem("ballPlasticID", IDHandler.craftingItems.ballOfPlasticID).getInt();
        endoInsulatorID = config.getItem("endoInsulatorID", IDHandler.craftingItems.endoInsulatorID).getInt();
        microBoardID = config.getItem("microBoardID", IDHandler.craftingItems.microchipBoardID).getInt();
        transistorID = config.getItem("transistorID", IDHandler.craftingItems.transistorID).getInt();
        CPUID = config.getItem("CPUID", IDHandler.craftingItems.CPUID).getInt();
        basicMicroChipID = config.getItem("basicMicroChipID", IDHandler.craftingItems.BasicMicrochipID).getInt();
        advMicrochipID = config.getItem("advMicrochipID", IDHandler.craftingItems.advancedMicrochipID).getInt();
        grindStoneID = config.getItem("grindStoneID", IDHandler.craftingItems.grindStoneID).getInt();
        inkQuillID = config.getItem("inkQuillID", IDHandler.craftingItems.inkAndQuillID).getInt();
        expMicrochipID = config.getItem("expMicrochipID", IDHandler.craftingItems.experimentalMicrochipID).getInt();
        advCPUID = config.getItem("advCPUID", IDHandler.craftingItems.advancedCPUID).getInt();
        graphiteRodID = config.getItem("graphiteRodID", IDHandler.craftingItems.graphiteRodID).getInt();
        electroMagnetID = config.getItem("electroMagnetID", IDHandler.craftingItems.electroMagnetID).getInt();
        diamondShardID = config.getItem("diamondShardID", IDHandler.craftingItems.diamondShardID).getInt();
        luminousRedID = config.getItem("luminousRedID", IDHandler.craftingItems.luminousRedstoneID).getInt();
        crystalBaseID = config.getItem("crystalBaseID", IDHandler.craftingItems.crystalBaseID).getInt();
        netID = config.getItem("netID", IDHandler.oreProccessing.netID).getInt();
        oreCrystalsID = config.getItem("oreCrystalsID", IDHandler.oreProccessing.crystalID).getInt();
        impureDustsID = config.getItem("impureDustsID", IDHandler.oreProccessing.impureDustsID).getInt();
        dustsID = config.getItem("dustsID", IDHandler.oreProccessing.dustsID).getInt();
        nodeID = config.getItem("nodeID", IDHandler.oreProccessing.nodeID).getInt();
        energyMeterID = config.getItem("energyMeterID", IDHandler.powerGrid.energyMeterID).getInt();
        spawnZSID = config.getItem("spawnZSID", IDHandler.basic.spawnZSID).getInt();
        glassModifierID = config.getItem("glassModifierID", IDHandler.basic.glassModifierID).getInt();
        hammerID = config.getItem("hammerID", IDHandler.basic.hammerID).getInt();
        fluidStorageID = config.getItem("fluidStorageID", IDHandler.worldGenOres.ScandiumID).getInt();
        plasmaDrillID = config.getItem("plasmaDrillID", IDHandler.tools.plasmaDrillID).getInt();
        drillCasingID = config.getItem("drillCasingID", IDHandler.tools.drillCasingID).getInt();
        energisingRodID = config.getItem("energisingRodID", IDHandler.advMachines.energisingRodID).getInt();
        ItemWireID = config.getItem("ItemWireID", IDHandler.powerGrid.ItemWireID).getInt();
        hazMat1ID = config.getItem("hazMatID", IDHandler.tools.hazMat1ID).getInt();
        hazMat2ID = config.getItem("hazMatID", IDHandler.tools.hazMat2ID).getInt();
        hazMat3ID = config.getItem("hazMatID", IDHandler.tools.hazMat3ID).getInt();
        hazMat4ID = config.getItem("hazMatID", IDHandler.tools.hazMat4ID).getInt();
        battery1ID = config.getItem("battery1ID", IDHandler.powerGrid.battery1ID).getInt();
        battery2ID = config.getItem("battery2ID", IDHandler.powerGrid.battery2ID).getInt();
        battery3ID = config.getItem("battery3ID", IDHandler.powerGrid.battery3ID).getInt();
        battery4ID = config.getItem("battery4ID", IDHandler.powerGrid.battery4ID).getInt();
        labCoatID = config.getItem("labCoatID", IDHandler.research.labCoatID).getInt();
        labGogglesID = config.getItem("labGogglesID", IDHandler.research.labGogglesID).getInt();
        itemScannerID = config.getItem("itemScannerID", IDHandler.research.itemScannerID).getInt();

        //Other
        multipart = config.get("OTHER", "addMultiparts", true).getBoolean(true);
        biomeID = config.get("OTHER", "biomeID", IDHandler.basic.biomeID).getInt();
        acidBurnsID = config.get("OTHER", "acidBurnsID", 31).getInt();
        
        config.save();
    }
}