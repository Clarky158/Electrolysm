package electrolysm.electro.powerSystem;

import electrolysm.electro.Electrolysm;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import electrolysm.electro.powerSystem.te.TileEntityWire;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class experimentalCable extends BlockContainer
{
    public static experimentalCable instance;

    public experimentalCable()
    {
        super(Material.cloth);
        instance = this;
        this.setCreativeTab(Electrolysm.TabElectrolysm);
        this.setHardness(0.25F);
    }

    @Override
    public TileEntity createNewTileEntity(World world, int i)
    {
        return new TileEntityWire();
    }

    @SideOnly(Side.CLIENT)
    @Override
    public void registerBlockIcons(IIconRegister reg)
    {
        this.blockIcon = reg.registerIcon("electrolysm:" + "graphiteWire");
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
    public int damageDropped(int par1)
    {
        if (this.blockID == electrolysmCore.wire.blockID)
        {
            return 0;
        }
        else if (this.blockID == electrolysmCore.advWire.blockID)
        {
            return 1;
        }

        return 0;
    }*/
}