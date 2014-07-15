package electro.misc.block;

import electro.Electrolysm;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import electro.misc.block.te.TileEntityIronFrame;

public class ironFrames extends BlockContainer
{
    public ironFrames()
    {
        super(Material.iron);
        this.setCreativeTab(Electrolysm.TabElectrolysm);
        this.setHardness(3F);
    }

    @Override
    public TileEntity createNewTileEntity(World world, int i)
    {
        // TODO Auto-generated method stub
        return new TileEntityIronFrame();
    }

    public boolean isOpaqueCube()
    {
        return false;
    }

    public boolean renderAsNormalBlock()
    {
        return false;
    }

    public int getRenderType()
    {
        return -1;
    }
}
