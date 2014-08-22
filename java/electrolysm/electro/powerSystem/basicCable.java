package electrolysm.electro.powerSystem;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import electrolysm.electro.Electrolysm;
import electrolysm.electro.powerSystem.te.TileEntityWire;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;


public class basicCable extends BlockContainer
{
    public basicCable()
    {
        super(Material.cloth);
        this.setCreativeTab(Electrolysm.TabElectrolysm);
        this.setHardness(5F);
        setHarvestLevel("pickaxe",1);
        setStepSound(soundTypeCloth);
    }

    @Override
    public TileEntity createNewTileEntity(World world, int i) {
        return new TileEntityWire();
    }

    @SideOnly(Side.CLIENT)
    @Override
    public void registerBlockIcons(IIconRegister reg)
    {
        this.blockIcon = reg.registerIcon("electrolysm:" + "copperWire");
    }

    @Override
    @SideOnly(Side.CLIENT)
    public IIcon getIcon(int side, int dmg)
    {
        return this.blockIcon;
    }

    @Override
    public int getRenderType()
    {
        return -1;
    }

    @Override
    public boolean isOpaqueCube()
    {
        return false;
    }

    public boolean renderAsNormalBlock()
    {
        return false;
    }

    @Override
    public void setBlockBoundsBasedOnState(IBlockAccess par1IBlockAccess, int x, int y, int z)
    {
        TileEntity tileEntity = par1IBlockAccess.getTileEntity(x, y, z);

        if (tileEntity instanceof TileEntityWire)
        {
            TileEntityWire te = (TileEntityWire) tileEntity;
            this.minX = te.getAdjConnections()[4] != null ? 0F : 0.3F;
            this.minY = te.getAdjConnections()[0] != null ? 0F : 0.3F;
            this.minZ = te.getAdjConnections()[2] != null ? 0F : 0.3F;
            this.maxX = te.getAdjConnections()[5] != null ? 1F : 0.7F;
            this.maxY = te.getAdjConnections()[1] != null ? 1F : 0.7F;
            this.maxZ = te.getAdjConnections()[3] != null ? 1F : 0.7F;
        }
    }
/*
    @Override
    public AxisAlignedBB getSelectedBoundingBoxFromPool(World world, int x, int y, int z) {
        float minX1 = 1F, minY1 = 1F, minZ1 = 1F;
        float maxX1 = 1F, maxY1 = 1F, maxZ1 = 1F;

        TileEntity tileEntity = world.getTileEntity(x, y, z);

        if (tileEntity instanceof TileEntityWire)
        {
            TileEntityWire te = (TileEntityWire) tileEntity;
            minX1 = te.getAdjConnections()[4] != null ? 0F : 0.3F;
            minY1 = te.getAdjConnections()[0] != null ? 0F : 0.3F;
            minZ1 = te.getAdjConnections()[2] != null ? 0F : 0.3F;
            maxX1 = te.getAdjConnections()[5] != null ? 1F : 0.7F;
            maxY1 = te.getAdjConnections()[1] != null ? 1F : 0.7F;
            maxZ1 = te.getAdjConnections()[3] != null ? 1F : 0.7F;
        }

        return AxisAlignedBB.getBoundingBox(minX1, minY1, minZ1, maxX1, maxY1, maxZ1);
    }


    @Override
    public AxisAlignedBB getCollisionBoundingBoxFromPool(World world, int x, int y, int z)
    {
        float minX1 = 1F, minY1 = 1F, minZ1 = 1F;
        float maxX1 = 1F, maxY1 = 1F, maxZ1 = 1F;

        TileEntity tileEntity = world.getTileEntity(x, y, z);

        if (tileEntity instanceof TileEntityWire)
        {
            TileEntityWire te = (TileEntityWire) tileEntity;
            minX1 = te.getAdjConnections()[4] != null ? 0F : 0.3F;
            minY1 = te.getAdjConnections()[0] != null ? 0F : 0.3F;
            minZ1 = te.getAdjConnections()[2] != null ? 0F : 0.3F;
            maxX1 = te.getAdjConnections()[5] != null ? 1F : 0.7F;
            maxY1 = te.getAdjConnections()[1] != null ? 1F : 0.7F;
            maxZ1 = te.getAdjConnections()[3] != null ? 1F : 0.7F;
        }

        return AxisAlignedBB.getBoundingBox(minX1, minY1, minZ1, maxX1, maxY1, maxZ1);
    }*/
}
