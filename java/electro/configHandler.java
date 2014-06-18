package electro;

import java.io.File;

import electro.handlers.BetaHandler;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.item.Item;
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
import net.minecraftforge.common.config.Configuration;

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

        //Other
        multipart = config.get("OTHER", "addMultiparts", true).getBoolean(true);
        biomeID = config.get("OTHER", "biomeID", 20).getInt();
        acidBurnsID = config.get("OTHER", "acidBurnsID", 31).getInt();
        
        config.save();
    }
}