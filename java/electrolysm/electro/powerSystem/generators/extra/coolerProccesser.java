package electrolysm.electro.powerSystem.generators.extra;

import electrolysm.electro.Electrolysm;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class coolerProccesser extends BlockContainer {

	public coolerProccesser() {
		super(Material.iron);
		
		this.setCreativeTab(Electrolysm.TabElectrolysm);
	}

	@Override
	public TileEntity createNewTileEntity(World world, int i) {
		return new TileEntityCoolerProcesser();
	}

}
