package assets.electrolysm.electro.handlers;

import java.util.ArrayList;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;
import assets.electrolysm.electro.electrolysmCore;
import cpw.mods.fml.common.registry.GameRegistry;

public class Crafting {

	
	
	private static ItemStack ingotCopper = new ItemStack(electrolysmCore.copperIngot);

	public static void addCrafting() {
	
		GameRegistry.addRecipe(new ItemStack(electrolysmCore.researchDesk),
				" X ", "YZY", "BMB",
				Character.valueOf('X'), Item.book,
				Character.valueOf('Y'), Item.ingotIron,
				Character.valueOf('Z'), Block.enchantmentTable,
				Character.valueOf('B'), Block.workbench,
				Character.valueOf('M'), Item.diamond);
		
		GameRegistry.addRecipe(new ItemStack(electrolysmCore.workBench),
				"YXY", "Y Y", "Y Y",
				Character.valueOf('X'), Block.workbench,
				Character.valueOf('Y'), Item.ingotIron);
		
		GameRegistry.addShapedRecipe(new ItemStack(electrolysmCore.card, 1, 1),
				"XYX",
				Character.valueOf('Y'), new ItemStack(Item.paper),
				Character.valueOf('X'), new ItemStack(Item.stick));

		for(int i = 1; i <= 9; i++)
		{
			ItemStack cardLevels = new ItemStack(electrolysmCore.card, 1, i);
			
			GameRegistry.addRecipe(new ItemStack(electrolysmCore.card, 1, i + 1),
					"XXX", "XZX", "XXX",
					Character.valueOf('X'), electrolysmCore.knowledge,
					Character.valueOf('Z'), cardLevels);
		}
		
		GameRegistry.addRecipe(new ItemStack(electrolysmCore.blastDoor),
				"XX", "XX", "XX",
				Character.valueOf('X'), electrolysmCore.blastProof);
		
		GameRegistry.addRecipe(new ItemStack(electrolysmCore.blastGlass, 4),
				"XYX", "Y Y", "XYX",
				Character.valueOf('X'), electrolysmCore.blastProof,
				Character.valueOf('Y'), Block.glass);
		
		
		GameRegistry.addRecipe(new ItemStack(electrolysmCore.drillCasing),
				" XY", "XZX", "BX ",
				Character.valueOf('X'), Item.ingotIron,
				Character.valueOf('Y'), Item.diamond,
				Character.valueOf('Z'), Block.blockDiamond,
				Character.valueOf('B'), Block.tnt);
		
		GameRegistry.addRecipe(new ItemStack(electrolysmCore.fluidStorage, 4),
				" X ", "Y Y", " Y ",
				Character.valueOf('X'), electrolysmCore.blastGlass,
				Character.valueOf('Y'), electrolysmCore.blastProof);
		
		
		GameRegistry.addRecipe(new ItemStack(electrolysmCore.injector),
				"XMX", "BYN", "XMX", 
				Character.valueOf('X'), electrolysmCore.graphite,
				Character.valueOf('Y'), electrolysmCore.blastProof,
				Character.valueOf('B'), Item.bucketEmpty,
				Character.valueOf('N'), electrolysmCore.injectionArm,
				Character.valueOf('M'), Item.diamond);
		
		GameRegistry.addRecipe(new ItemStack(electrolysmCore.energiser),
				"XDX", "IYB", "XDX",
				Character.valueOf('X'), electrolysmCore.blastProof,
				Character.valueOf('Y'), electrolysmCore.graphite,
				Character.valueOf('B'), Item.bucketEmpty,
				Character.valueOf('I'), electrolysmCore.energisingRod,
				Character.valueOf('D'), Item.diamond);
		
		GameRegistry.addRecipe(new ItemStack(electrolysmCore.injectionArm),
				"GBG", "CIC", " I ",
				Character.valueOf('G'), Block.glowStone,
				Character.valueOf('B'), Item.bucketEmpty,
				Character.valueOf('C'), electrolysmCore.chunkGraphite,
				Character.valueOf('I'), Item.ingotIron);
		
		GameRegistry.addRecipe(new ItemStack(electrolysmCore.energisingRod),
				"ZXZ", " X ", " Y ",
				Character.valueOf('X'), Item.ingotGold,
				Character.valueOf('Y'), electrolysmCore.chunkGraphite,
				Character.valueOf('Z'), electrolysmCore.graphite);
		
		GameRegistry.addShapelessRecipe(new ItemStack(electrolysmCore.chunkGraphite, 9), 
				new ItemStack(electrolysmCore.graphite));
		
		GameRegistry.addRecipe(new ItemStack(electrolysmCore.graphite),
				"XXX", "XXX", "XXX",
				Character.valueOf('X'), electrolysmCore.chunkGraphite);
		

		GameRegistry.addRecipe(new ItemStack(electrolysmCore.blastProof, 2),
				"XYX", "YZY", "XYX",
				Character.valueOf('X'), Item.ingotIron,
				Character.valueOf('Y'), electrolysmCore.stoneObsidian,
				Character.valueOf('Z'), new ItemStack(electrolysmCore.researchPaper, 1, 0));  
		
		GameRegistry.addRecipe(new ItemStack(electrolysmCore.stoneObsidian, 8),
				"XXX", "XYX", "XXX",
				Character.valueOf('X'), Block.stone,
				Character.valueOf('Y'), Block.obsidian);
		
		GameRegistry.addRecipe(new ItemStack(electrolysmCore.glassModifire),
				"XGX", " Y ", "Y",
				Character.valueOf('X'), Item.ingotIron,
				Character.valueOf('G'), Item.ingotGold,
				Character.valueOf('Y'), Item.stick);
/*
		GameRegistry.addRecipe(new ItemStack(electrolysmCore.copperwire) ,
				"WWW" , "CCC" , "WWW",
				Character.valueOf('W'), Block.cloth,
				Character.valueOf('C'), ingotCopper) ;
				
		*/
		GameRegistry.addRecipe(new ItemStack(electrolysmCore.electrolChamber, 4), 
				"XYX", "YDY", "XYX",
				Character.valueOf('X'), Item.ingotIron,
				Character.valueOf('Y'), Item.ingotGold,
				Character.valueOf('D'), Item.diamond);
		
		GameRegistry.addRecipe(new ItemStack(electrolysmCore.electrolisisCore),
				"G G", "YXY", "RYR",
				Character.valueOf('G'), Item.ingotGold,
				Character.valueOf('Y'), electrolysmCore.electrolChamber,
				Character.valueOf('X'), Item.cauldron,
				Character.valueOf('R'), Item.redstone);
		
		GameRegistry.addRecipe(new ItemStack(electrolysmCore.node),
				"XYX", "XYX", " Z ",
				Character.valueOf('X'), Item.ingotIron,
				Character.valueOf('Y'), electrolysmCore.graphite,
				Character.valueOf('Z'), electrolysmCore.chunkGraphite);
		
		GameRegistry.addRecipe(new ItemStack(Block.tnt),
				"XYX", "XXX", "XYX",
				Character.valueOf('X'), electrolysmCore.sulphur,
				Character.valueOf('Y'), Block.sand);
		
		GameRegistry.addShapelessRecipe(new ItemStack(electrolysmCore.luminousRedstone, 2), 
				new ItemStack(Item.redstone),
				new ItemStack(Item.redstone),
				new ItemStack(Item.glowstone),
				new ItemStack(electrolysmCore.sulphur));
		
		GameRegistry.addShapelessRecipe(new ItemStack(electrolysmCore.diamondShard, 4),
				new ItemStack(electrolysmCore.hammer),
				new ItemStack(electrolysmCore.fluidStorage, 1, 2),
				new ItemStack(Item.diamond));
		
		GameRegistry.addRecipe(new ItemStack(electrolysmCore.hammer),
				"XYX", " Z ", " Z ",
				Character.valueOf('X'), Item.ingotIron,
				Character.valueOf('Y'), Item.ingotGold,
				Character.valueOf('Z'), Block.stone);
		
		GameRegistry.addRecipe(new ItemStack(electrolysmCore.crystalBase),
				"XYX", "YGY", "XYX",
				Character.valueOf('X'), electrolysmCore.diamondShard,
				Character.valueOf('Y'), electrolysmCore.BlockLumRed,
				Character.valueOf('G'), Item.diamond);
		
		GameRegistry.addRecipe(new ItemStack(electrolysmCore.crystal1),
				" X ", "SYS", "QRQ",
				Character.valueOf('X'), Block.glowStone,
				Character.valueOf('S'), electrolysmCore.diamondShard,
				Character.valueOf('Y'), electrolysmCore.crystalBase,
				Character.valueOf('Q'), Block.blockNetherQuartz,
				Character.valueOf('R'), Item.redstone);
		
		GameRegistry.addShapelessRecipe(new ItemStack(electrolysmCore.BlockLumRed),
				new ItemStack(electrolysmCore.luminousRedstone),
				new ItemStack(electrolysmCore.luminousRedstone),
				new ItemStack(electrolysmCore.luminousRedstone),
				new ItemStack(electrolysmCore.luminousRedstone));
		
		GameRegistry.addRecipe(new ItemStack(electrolysmCore.ironFrames, 2),
				"X X", " Y ", "X X",
				Character.valueOf('X'), Item.ingotIron,
				Character.valueOf('Y'), electrolysmCore.copperCoil);
		
			GameRegistry.addRecipe(new ItemStack(electrolysmCore.plug),
					" C ", "LBL", "CRC",
					Character.valueOf('C'), electrolysmCore.copperCoil,
					Character.valueOf('L'), electrolysmCore.luminousRedstone,
					Character.valueOf('R'), Item.redstone,
					Character.valueOf('B'), Block.blockGold);
			
			GameRegistry.addRecipe(new ItemStack(electrolysmCore.copperCoil, 2),
					"XXX", "XYX", "XXX",
					Character.valueOf('Y'), Item.ingotIron,
					Character.valueOf('X'), ingotCopper);
			
			GameRegistry.addRecipe(new ItemStack(electrolysmCore.energyMeter),
					"C C", "IRI", "IGI",
					Character.valueOf('C'), ingotCopper,
					Character.valueOf('I'), Item.ingotIron,
					Character.valueOf('R'), Item.redstone,
					Character.valueOf('G'), Item.ingotGold);
			
		GameRegistry.addRecipe(new ItemStack(electrolysmCore.largeCopperCoil),
				"XXX", "XYX", "XXX",
				Character.valueOf('X'), electrolysmCore.copperCoil,
				Character.valueOf('Y'), Item.ingotIron);
		
		GameRegistry.addRecipe(new ItemStack(electrolysmCore.generator),
				"III", "IRI", "FFF",
				Character.valueOf('F'), Block.furnaceIdle,
				Character.valueOf('R'), Item.redstone,
				Character.valueOf('I'), Item.ingotIron);
		
		GameRegistry.addShapelessRecipe(new ItemStack(electrolysmCore.luminousRedstone, 4), 
				new ItemStack(electrolysmCore.BlockLumRed));
		
		GameRegistry.addShapelessRecipe(new ItemStack(electrolysmCore.BlockLumRed),
				new ItemStack(Block.blockRedstone),
				new ItemStack(Block.glowStone),
				new ItemStack(electrolysmCore.sulphur));
		
		GameRegistry.addShapedRecipe(new ItemStack(electrolysmCore.wire, 28),
				"WWW", "GGG", "WWW",
				Character.valueOf('W'), electrolysmCore.endoInsulator,
				Character.valueOf('G'), electrolysmCore.graphite);
		
		GameRegistry.addShapelessRecipe(new ItemStack(electrolysmCore.endoInsulator, 6), 
				new ItemStack(electrolysmCore.ballOfPlastic),
				new ItemStack(electrolysmCore.ballOfPlastic),
				new ItemStack(Item.enderPearl));
	}

}
