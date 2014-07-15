package electro.handlers;

import electro.machines.assemblySystem.crafting.TileEntityMatrix;
import electro.machines.assemblySystem.inventory.TileEntityRobotArm;
import electro.machines.advMachines.te.TileEntityCharger;
import electro.machines.advMachines.te.TileEntityEnergiser;
import electro.machines.advMachines.te.TileEntityInjector;
import electro.misc.block.basic.te.TileEntityBlastDoor;
import electro.research.machines.tile.TileEntityDesk;
import electro.research.machines.tile.TileEntityResearchDesk;
import electro.research.machines.tile.TileEntityWorkBench;
import electro.misc.block.te.TileEntityIronFrame;
import cpw.mods.fml.common.registry.GameRegistry;
import electro.oreProccessing.te.TileEntityCrusher;
import electro.oreProccessing.te.TileEntityElectrolisisCore;
import electro.oreProccessing.te.TileEntityLiquidiser;
import electro.oreProccessing.te.TileEntitySmeltory;
import electro.powerSystem.generators.te.TileEntityGeneratorAntimatter;
import electro.powerSystem.generators.te.TileEntityGeneratorCoal;
import electro.powerSystem.te.TileEntityWire;
import electro.research.te.TileEntityCollector;
import electro.sciences.alloyFurnace.TileEntityAlloyFurnace;

/**
 * Created by Clarky158 on 26/01/14.
 */
public class TileEntityMappingHandler
{
    public static void addMappings()
    {
        GameRegistry.registerTileEntity(TileEntityAlloyFurnace.class, "TileEntityAlloyFurnace");
        GameRegistry.registerTileEntity(TileEntityBlastDoor.class, "TileEntityBlastDoor");
        GameRegistry.registerTileEntity(TileEntityCrusher.class, "TileEntityCrusher");
        GameRegistry.registerTileEntity(TileEntityElectrolisisCore.class, "TileEntityElectrolysisCore");
        GameRegistry.registerTileEntity(TileEntityEnergiser.class, "TileEntityEnergiser");
        GameRegistry.registerTileEntity(TileEntityInjector.class, "TileEntityInjector");
        GameRegistry.registerTileEntity(TileEntityIronFrame.class, "TileEntityIronFrame");
        GameRegistry.registerTileEntity(TileEntityResearchDesk.class, "TileEntityResearchDesk");
        GameRegistry.registerTileEntity(TileEntityWorkBench.class, "TileEntityWorkBench");
        GameRegistry.registerTileEntity(TileEntityLiquidiser.class, "TileEntityLiquidiser");
        GameRegistry.registerTileEntity(TileEntitySmeltory.class, "TileEntitySmeltory");
        GameRegistry.registerTileEntity(TileEntityWire.class, "TileEntityWire");
        GameRegistry.registerTileEntity(TileEntityGeneratorCoal.class, "TileEntityGeneratorCoal");
        GameRegistry.registerTileEntity(TileEntityGeneratorAntimatter.class, "TileEntityGeneratorAntimatter");
        GameRegistry.registerTileEntity(TileEntityDesk.class, "TileEntityDesk");
        GameRegistry.registerTileEntity(TileEntityCharger.class, "TileEntityCharge");
        GameRegistry.registerTileEntity(TileEntityCollector.class, "TileEntityCollector");
        GameRegistry.registerTileEntity(TileEntityMatrix.class, "TileEntityMatrix");
//        /GameRegistry.registerTileEntity(TileEntityMatrixMachine.class, "TileEntityMatrixMachine");
        GameRegistry.registerTileEntity(TileEntityRobotArm.class, "TileEntityRoboticArm");
    }
}
