package assets.electrolysm.electro.block.advMachines.te;

import assets.electrolysm.api.powerSystem.usageMachine.TileEntityEnergyMachine;

public class TileEntityCharger extends TileEntityEnergyMachine 
{
	@Override
	public int getActivationEnergy()
	{
		return 250;
	}
}
